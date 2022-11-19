package su.nsk.iae.post.ui.wizard

import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.Platform
import org.eclipse.xtext.ui.wizard.template.FileTemplate
import org.eclipse.xtext.ui.wizard.template.IFileGenerator
import org.eclipse.xtext.ui.wizard.template.IFileTemplateProvider
import org.eclipse.xtext.util.Files

class PoSTFileTemplateProvider implements IFileTemplateProvider {
	override getFileTemplates() {
		#[new EmptyFile, new EmptyTemplateFile, new HandDryerFile]
	}
}

@FileTemplate(label="Empty File", icon="PoST_file.png", description="Create an empty poST file.")
final class EmptyFile {
	override generateFiles(IFileGenerator generator) {
		generator.generate('''�folder�/�name�.post''', '''''')
	}
}

@FileTemplate(label="Template Program", icon="PoST_file.png", description="Create an empty poST template.")
final class EmptyTemplateFile {
	override generateFiles(IFileGenerator generator) {
		val bundle = Platform.getBundle("su.nsk.iae.post.ui")
		val templateStream = FileLocator.resolve(FileLocator.find(bundle, new Path("/resources/examples/Template.post"), null)).openStream
		generator.generate('''�folder�/�name�.post''', Files.readStreamIntoString(templateStream))
	}
}

@FileTemplate(label="Hand Dryer Program", icon="PoST_file.png", description="Create a Hand Dryer poST program.")
final class HandDryerFile {
	override generateFiles(IFileGenerator generator) {
		val bundle = Platform.getBundle("su.nsk.iae.post.ui")
		val handDryerStream = FileLocator.resolve(FileLocator.find(bundle, new Path("/resources/examples/HandDryer.post"), null)).openStream
		generator.generate('''�folder�/�name�.post''', Files.readStreamIntoString(handDryerStream))
	}
}
