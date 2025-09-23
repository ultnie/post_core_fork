package su.nsk.iae.post.generator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.GeneratorContext;
import org.eclipse.xtext.generator.GeneratorDelegate;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import su.nsk.iae.post.PoSTStandaloneSetup;

public class Main {

	public static void main(String[] args) {
		if (!Arrays.stream(args).anyMatch(x -> x.equals("-d"))) {
			singleExecutions(args);
		} else {
			service(args);
		}
	}

	private static void singleExecutions(String[] args) {
		if (args.length == 0) {
			System.err.println("Aborting: no path to poST file provided!");
			return;
		}
		Injector injector = new PoSTStandaloneSetup().createInjectorAndDoEMFRegistration();
		Main main = injector.getInstance(Main.class);
		String filename = Arrays.stream(args).filter(x -> x.startsWith("-o=")).map(x -> x.substring(3)).findFirst().orElse("poST_code.py");
		main.runGenerator(Arrays.stream(args).filter(x -> x.contains(".post")).findFirst().get(),
				Arrays.stream(args).anyMatch(x -> x.equals("-l")), filename);
	}

	private static boolean loop = true;

	private static void service(String[] args) {
		boolean local = Arrays.stream(args).anyMatch(x -> x.equals("-l"));
		Injector injector = new PoSTStandaloneSetup().createInjectorAndDoEMFRegistration();
		Main main = injector.getInstance(Main.class);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				loop = false;
			}
		});
		Scanner scanner = new Scanner(System.in);
		while (loop) {
			String file = scanner.next();
			String filename = scanner.nextLine().trim();
			if (filename.isEmpty()) {
				filename = "poST_code.py";
			}
			main.runGenerator(file, local, filename);
		}
		scanner.close();
	}

	@Inject
	private Provider<ResourceSet> resourceSetProvider;

	@Inject
	private IResourceValidator validator;

	@Inject
	private GeneratorDelegate generator;

	@Inject
	private JavaIoFileSystemAccess fileAccess;

	protected void runGenerator(String string, boolean local, String filename) {
		// Load the resource
		ResourceSet set = resourceSetProvider.get();
		Resource resource = set.getResource(URI.createFileURI(string), true);

		// Validate the resource
		List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		if (checkErrors(issues)) {
			printIssues(issues);
			return;
		}

		// Configure and start the generator
		if (filename == null || filename.isEmpty()) {
			filename = "poST_code.py";
		}
		fileAccess.setOutputPath("./" + filename);
		GeneratorContext context = new GeneratorContext();
		context.setCancelIndicator(CancelIndicator.NullImpl);
		try {
			generator.generate(resource, fileAccess, context);
		} catch (Exception e) {
			System.out.println("Generator error " + e.getMessage() + " ");
			e.printStackTrace(System.out);
			return;
		}

		System.out.println("Code generation finished.");
		printIssues(issues);
	}

	private boolean checkErrors(List<Issue> issues) {
		for (Issue issue : issues) {
			if (issue.getSeverity() == Severity.ERROR) {
				return true;
			}
		}
		return false;
	}

	private void printIssues(List<Issue> issues) {
		for (Issue issue : issues) {
			System.err.println(issue);
		}
	}
}