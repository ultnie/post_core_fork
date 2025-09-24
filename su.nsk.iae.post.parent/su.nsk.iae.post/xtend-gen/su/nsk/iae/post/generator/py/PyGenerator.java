package su.nsk.iae.post.generator.py;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import su.nsk.iae.post.IDsmExecutor;
import su.nsk.iae.post.PoSTStandaloneSetup;
import su.nsk.iae.post.generator.IPoSTGenerator;
import su.nsk.iae.post.generator.py.common.ProgramGenerator;
import su.nsk.iae.post.generator.py.common.util.GeneratorUtil;
import su.nsk.iae.post.generator.py.common.vars.GlobalVarHelper;
import su.nsk.iae.post.generator.py.common.vars.VarHelper;
import su.nsk.iae.post.generator.py.configuration.ConfigurationGenerator;
import su.nsk.iae.post.generator.py.imports.ImportsGenerator;
import su.nsk.iae.post.poST.ArrayVariable;
import su.nsk.iae.post.poST.AssignmentStatement;
import su.nsk.iae.post.poST.AttachVariableConfElement;
import su.nsk.iae.post.poST.Configuration;
import su.nsk.iae.post.poST.Constant;
import su.nsk.iae.post.poST.ForStatement;
import su.nsk.iae.post.poST.FunctionBlock;
import su.nsk.iae.post.poST.GlobalVarDeclaration;
import su.nsk.iae.post.poST.Inline_code;
import su.nsk.iae.post.poST.Model;
import su.nsk.iae.post.poST.PrimaryExpression;
import su.nsk.iae.post.poST.ProcessStatements;
import su.nsk.iae.post.poST.ProcessStatusExpression;
import su.nsk.iae.post.poST.Program;
import su.nsk.iae.post.poST.ProgramConfElement;
import su.nsk.iae.post.poST.ProgramConfElements;
import su.nsk.iae.post.poST.ProgramConfiguration;
import su.nsk.iae.post.poST.SymbolicVariable;
import su.nsk.iae.post.poST.TemplateProcessAttachVariableConfElement;
import su.nsk.iae.post.poST.TemplateProcessConfElement;
import su.nsk.iae.post.poST.TimeoutStatement;
import su.nsk.iae.post.poST.Variable;

@SuppressWarnings("all")
public class PyGenerator implements IDsmExecutor, IPoSTGenerator {
  private ConfigurationGenerator configuration = null;

  private ImportsGenerator importsGenerator = null;

  private VarHelper globVarList = new GlobalVarHelper();

  private List<ProgramGenerator> programs = new LinkedList<ProgramGenerator>();

  @Override
  public String execute(final String root, final String fileName, final Resource resource) {
    try {
      final JavaIoFileSystemAccess fsa = PoSTStandaloneSetup.getInjector().<JavaIoFileSystemAccess>getInstance(JavaIoFileSystemAccess.class);
      final String generatePath = ((((root + File.separator) + "") + File.separator) + fileName);
      fsa.setOutputPath(generatePath);
      this.setModel(((Model[])Conversions.unwrapArray((Iterables.<Model>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), Model.class)), Model.class))[0]);
      this.beforeGenerate(resource, fsa, null);
      this.doGenerate(resource, fsa, null);
      this.afterGenerate(resource, fsa, null);
      return ("Files generated in " + generatePath);
    } catch (final Throwable _t) {
      if (_t instanceof Throwable) {
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return "Failure";
  }

  @Override
  public void setModel(final Model model) {
    this.globVarList.clear();
    this.programs.clear();
    final Consumer<GlobalVarDeclaration> _function = (GlobalVarDeclaration v) -> {
      this.globVarList.add(v);
    };
    model.getGlobVars().stream().forEach(_function);
    Inline_code _imports = model.getImports();
    boolean _tripleNotEquals = (_imports != null);
    if (_tripleNotEquals) {
      Inline_code _imports_1 = model.getImports();
      ImportsGenerator _importsGenerator = new ImportsGenerator(_imports_1);
      this.importsGenerator = _importsGenerator;
    }
    Configuration _conf = model.getConf();
    boolean _tripleNotEquals_1 = (_conf != null);
    if (_tripleNotEquals_1) {
      Configuration _conf_1 = model.getConf();
      ConfigurationGenerator _configurationGenerator = new ConfigurationGenerator(_conf_1);
      this.configuration = _configurationGenerator;
      final Function<su.nsk.iae.post.poST.Resource, EList<ProgramConfiguration>> _function_1 = (su.nsk.iae.post.poST.Resource res) -> {
        return res.getResStatement().getProgramConfs();
      };
      final Function<EList<ProgramConfiguration>, Stream<ProgramConfiguration>> _function_2 = (EList<ProgramConfiguration> res) -> {
        return res.stream();
      };
      final Consumer<ProgramConfiguration> _function_3 = (ProgramConfiguration programConf) -> {
        final Program program = EcoreUtil.<Program>copy(programConf.getProgram());
        program.setName(this.capitalizeFirst(programConf.getName()));
        ProgramPOUGenerator _programPOUGenerator = new ProgramPOUGenerator(program, true);
        this.programs.add(_programPOUGenerator);
      };
      this.configuration.getResources().stream().<EList<ProgramConfiguration>>map(_function_1).<ProgramConfiguration>flatMap(_function_2).forEach(_function_3);
    } else {
      final Consumer<Program> _function_4 = (Program p) -> {
        ProgramPOUGenerator _programPOUGenerator = new ProgramPOUGenerator(p, false);
        this.programs.add(_programPOUGenerator);
      };
      model.getPrograms().stream().forEach(_function_4);
      final Consumer<FunctionBlock> _function_5 = (FunctionBlock fb) -> {
        FunctionBlockPOUGenerator _functionBlockPOUGenerator = new FunctionBlockPOUGenerator(fb, false);
        this.programs.add(_functionBlockPOUGenerator);
      };
      model.getFbs().stream().forEach(_function_5);
    }
  }

  @Override
  public void beforeGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    this.preparePrograms();
  }

  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    this.generateSingleFile(fsa, "", input.getURI());
  }

  @Override
  public void afterGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
  }

  private void generateSingleFile(final IFileSystemAccess2 fsa, final String path, final URI source) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(path);
    fsa.generateFile(_builder.toString(), this.generateSingleFileBody(source));
  }

  private String generateSingleFileBody(final URI source) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((this.importsGenerator != null)) {
        String _generateImports = this.importsGenerator.generateImports();
        _builder.append(_generateImports);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("import time");
    _builder.newLine();
    _builder.append("import enum");
    _builder.newLine();
    _builder.append("from MuteTypes import MuteBool, MuteNum, MuteStr, MuteBytes, get_value");
    _builder.newLine();
    _builder.append("from datetime import timedelta");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("inVars = {}");
    _builder.newLine();
    _builder.append("outVars = {}");
    _builder.newLine();
    _builder.append("inOutVars = {}");
    _builder.newLine();
    _builder.append("Vars = {}");
    _builder.newLine();
    _builder.append("exVars = {}");
    _builder.newLine();
    _builder.append("globVars = {}");
    _builder.newLine();
    _builder.append("tempVars = {}");
    _builder.newLine();
    _builder.append("pStates = {}");
    _builder.newLine();
    _builder.append("pTimes = {}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    String _generateVars = GeneratorUtil.generateVars(this.globVarList);
    _builder.append(_generateVars);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    _builder.append("def setVariable (name, value):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("globals()[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("globals()[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in outVars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("outVars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("outVars[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in inOutVars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("inOutVars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("inOutVars[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in exVars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("exVars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("exVars[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in globVars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("globVars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("globVars[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in Vars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Vars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Vars[name] = value");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _isEmpty = this.programs.isEmpty();
      if (_isEmpty) {
        String _generateEmptyTemplate = this.generateEmptyTemplate();
        _builder.append(_generateEmptyTemplate);
        _builder.newLineIfNotEmpty();
      }
    }
    {
      for(final ProgramGenerator c : this.programs) {
        String _generateProgram = c.generateProgram(this.configuration, source);
        _builder.append(_generateProgram);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }

  private String generateEmptyTemplate() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_global_time = 0");
    _builder.newLine();
    _builder.append("Vars[\'_global_time\'] = _global_time");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("taskTime = 0");
    _builder.newLine();
    _builder.newLine();
    _builder.append("emptyOut = MuteStr(\"Empty program\")");
    _builder.newLine();
    _builder.append("outVars[\'output\'] = emptyOut");
    _builder.newLine();
    _builder.newLine();
    _builder.append("def setVariable (name, value):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("globals()[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("globals()[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in outVars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("outVars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("outVars[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in inOutVars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("inOutVars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("inOutVars[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in exVars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("exVars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("exVars[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in globVars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("globVars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("globVars[name] = value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if name in Vars:");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Vars[name].__set__(value)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("except:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Vars[name] = value");
    _builder.newLine();
    _builder.newLine();
    _builder.append("def start(p):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global Vars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("p.curState = p.States(1)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("pStates[f\'{p.name()}_state\'] = p.States(p.curState).name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("setVariable(f\'_g_p_{p.name()}_time\', _global_time)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("def stop(p):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global Vars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("p.curState = p.States(254)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("pStates[f\'{p.name()}_state\'] = p.States(p.curState).name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("setVariable(f\'_g_p_{p.name()}_time\', _global_time)");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("def error(p):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global Vars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("p.curState = p.States(255)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("pStates[f\'{p.name()}_state\'] = p.States(p.curState).name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("setVariable(f\'_g_p_{p.name()}_time\', _global_time)");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("def set_state(state, p):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global Vars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("p.curState = state");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("pStates[f\'{p.name()}_state\'] = p.States(p.curState).name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("setVariable(f\'_g_p_{p.name()}_time\', _global_time)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Program:");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global taskTime");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("processes = []");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def run_iter(self):");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("global _global_time");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Vars[\'_global_time\'] = _global_time");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("def getVariable(name):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return globals()[name]");
    _builder.newLine();
    return _builder.toString();
  }

  private void preparePrograms() {
    if ((this.configuration == null)) {
      return;
    }
    final Function<su.nsk.iae.post.poST.Resource, EList<ProgramConfiguration>> _function = (su.nsk.iae.post.poST.Resource res) -> {
      return res.getResStatement().getProgramConfs();
    };
    final Function<EList<ProgramConfiguration>, Stream<ProgramConfiguration>> _function_1 = (EList<ProgramConfiguration> res) -> {
      return res.stream();
    };
    final Consumer<ProgramConfiguration> _function_2 = (ProgramConfiguration programConf) -> {
      ProgramConfElements _args = programConf.getArgs();
      boolean _tripleNotEquals = (_args != null);
      if (_tripleNotEquals) {
        final String programConfName = this.capitalizeFirst(programConf.getName());
        final Predicate<ProgramGenerator> _function_3 = (ProgramGenerator x) -> {
          String _name = x.getName();
          return Objects.equal(_name, programConfName);
        };
        final ProgramGenerator programGen = this.programs.stream().filter(_function_3).findFirst().get();
        final Consumer<ProgramConfElement> _function_4 = (ProgramConfElement confElement) -> {
          if ((confElement instanceof TemplateProcessConfElement)) {
            final su.nsk.iae.post.poST.Process process = EcoreUtil.<su.nsk.iae.post.poST.Process>copy(((TemplateProcessConfElement)confElement).getProcess());
            process.setName(this.capitalizeFirst(((TemplateProcessConfElement)confElement).getName()));
            final Consumer<TemplateProcessAttachVariableConfElement> _function_5 = (TemplateProcessAttachVariableConfElement e) -> {
              this.changeAllVars(e, process);
            };
            ((TemplateProcessConfElement)confElement).getArgs().getElements().stream().forEach(_function_5);
            programGen.addProcess(process, ((TemplateProcessConfElement)confElement).isActive());
          } else {
            if ((confElement instanceof AttachVariableConfElement)) {
              this.changeAllVars(((AttachVariableConfElement)confElement), programGen.getEObject());
            }
          }
        };
        programConf.getArgs().getElements().stream().forEach(_function_4);
      }
    };
    this.configuration.getResources().stream().<EList<ProgramConfiguration>>map(_function).<ProgramConfiguration>flatMap(_function_1).forEach(_function_2);
  }

  public void changeAllVars(final AttachVariableConfElement element, final EObject root) {
    this.changeAllVars(element.getProgramVar(), element.getAttVar(), element.getConst(), root);
  }

  public void changeAllVars(final TemplateProcessAttachVariableConfElement element, final EObject root) {
    this.changeAllVars(element.getProgramVar(), element.getAttVar(), element.getConst(), root);
  }

  public void changeAllVars(final Variable programVar, final Variable attVar, final Constant const_, final EObject root) {
    final Predicate<PrimaryExpression> _function = (PrimaryExpression v) -> {
      return ((v.getVariable() != null) && Objects.equal(v.getVariable().getName(), programVar.getName()));
    };
    final Consumer<PrimaryExpression> _function_1 = (PrimaryExpression v) -> {
      if ((attVar != null)) {
        v.setVariable(((SymbolicVariable) attVar));
      } else {
        v.setVariable(null);
        v.setConst(EcoreUtil.<Constant>copy(const_));
      }
    };
    EcoreUtil2.<PrimaryExpression>getAllContentsOfType(root, PrimaryExpression.class).stream().filter(_function).forEach(_function_1);
    final Predicate<AssignmentStatement> _function_2 = (AssignmentStatement v) -> {
      return ((v.getVariable() != null) && Objects.equal(v.getVariable().getName(), programVar.getName()));
    };
    final Consumer<AssignmentStatement> _function_3 = (AssignmentStatement v) -> {
      v.setVariable(((SymbolicVariable) attVar));
    };
    EcoreUtil2.<AssignmentStatement>getAllContentsOfType(root, AssignmentStatement.class).stream().filter(_function_2).forEach(_function_3);
    final Predicate<ForStatement> _function_4 = (ForStatement v) -> {
      String _name = v.getVariable().getName();
      String _name_1 = programVar.getName();
      return Objects.equal(_name, _name_1);
    };
    final Consumer<ForStatement> _function_5 = (ForStatement v) -> {
      v.setVariable(((SymbolicVariable) attVar));
    };
    EcoreUtil2.<ForStatement>getAllContentsOfType(root, ForStatement.class).stream().filter(_function_4).forEach(_function_5);
    final Predicate<ArrayVariable> _function_6 = (ArrayVariable v) -> {
      String _name = v.getVariable().getName();
      String _name_1 = programVar.getName();
      return Objects.equal(_name, _name_1);
    };
    final Consumer<ArrayVariable> _function_7 = (ArrayVariable v) -> {
      v.setVariable(((SymbolicVariable) attVar));
    };
    EcoreUtil2.<ArrayVariable>getAllContentsOfType(root, ArrayVariable.class).stream().filter(_function_6).forEach(_function_7);
    final Predicate<TimeoutStatement> _function_8 = (TimeoutStatement v) -> {
      return ((v.getVariable() != null) && Objects.equal(v.getVariable().getName(), programVar.getName()));
    };
    final Consumer<TimeoutStatement> _function_9 = (TimeoutStatement v) -> {
      v.setVariable(((SymbolicVariable) attVar));
    };
    EcoreUtil2.<TimeoutStatement>getAllContentsOfType(root, TimeoutStatement.class).stream().filter(_function_8).forEach(_function_9);
    final Predicate<ProcessStatements> _function_10 = (ProcessStatements v) -> {
      return ((v.getProcess() != null) && Objects.equal(v.getProcess().getName(), programVar.getName()));
    };
    final Consumer<ProcessStatements> _function_11 = (ProcessStatements v) -> {
      Variable _process = v.getProcess();
      _process.setName(this.capitalizeFirst(attVar.getName()));
    };
    EcoreUtil2.<ProcessStatements>getAllContentsOfType(root, ProcessStatements.class).stream().filter(_function_10).forEach(_function_11);
    final Predicate<ProcessStatusExpression> _function_12 = (ProcessStatusExpression v) -> {
      return ((v.getProcess() != null) && Objects.equal(v.getProcess().getName(), programVar.getName()));
    };
    final Consumer<ProcessStatusExpression> _function_13 = (ProcessStatusExpression v) -> {
      Variable _process = v.getProcess();
      _process.setName(this.capitalizeFirst(attVar.getName()));
    };
    EcoreUtil2.<ProcessStatusExpression>getAllContentsOfType(root, ProcessStatusExpression.class).stream().filter(_function_12).forEach(_function_13);
  }

  private String capitalizeFirst(final String str) {
    String _upperCase = str.substring(0, 1).toUpperCase();
    String _substring = str.substring(1);
    return (_upperCase + _substring);
  }
}
