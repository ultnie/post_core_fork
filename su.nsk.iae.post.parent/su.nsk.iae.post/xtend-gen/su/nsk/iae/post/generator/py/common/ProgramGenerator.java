package su.nsk.iae.post.generator.py.common;

import com.google.common.base.Objects;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.io.IOUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import su.nsk.iae.post.generator.py.common.util.GeneratorUtil;
import su.nsk.iae.post.generator.py.common.vars.ExternalVarHelper;
import su.nsk.iae.post.generator.py.common.vars.InputOutputVarHelper;
import su.nsk.iae.post.generator.py.common.vars.InputVarHelper;
import su.nsk.iae.post.generator.py.common.vars.OutputVarHelper;
import su.nsk.iae.post.generator.py.common.vars.SimpleVarHelper;
import su.nsk.iae.post.generator.py.common.vars.TempVarHelper;
import su.nsk.iae.post.generator.py.common.vars.VarHelper;
import su.nsk.iae.post.generator.py.configuration.ConfigurationGenerator;

@SuppressWarnings("all")
public class ProgramGenerator {
  protected EObject object;

  protected String programName;

  protected String type;

  private boolean templateProcess;

  protected VarHelper inVarList = new InputVarHelper();

  protected VarHelper outVarList = new OutputVarHelper();

  protected VarHelper inOutVarList = new InputOutputVarHelper();

  protected VarHelper externalVarList = new ExternalVarHelper();

  protected VarHelper varList = new SimpleVarHelper();

  protected VarHelper tempVarList = new TempVarHelper();

  protected List<ProcessGenerator> processList = new LinkedList<ProcessGenerator>();

  protected List<String> confInVars;

  protected List<String> confOutVars;

  public ProgramGenerator(final boolean templateProcess) {
    this.templateProcess = templateProcess;
  }

  public String generateProgram(final ConfigurationGenerator configuration, final URI source) {
    this.prepareConfVars(source.devicePath());
    this.prepareProgramVars();
    return this.generateBody(configuration);
  }

  public Object prepareConfVars(final String path) {
    try {
      Object _xblockexpression = null;
      {
        int i = ((int) 0);
        String fileString = "";
        String programString = "";
        String item = "";
        FileInputStream inputStream = null;
        List<String> items = null;
        Object _xtrycatchfinallyexpression = null;
        try {
          ArrayList<String> _arrayList = new ArrayList<String>();
          this.confInVars = _arrayList;
          ArrayList<String> _arrayList_1 = new ArrayList<String>();
          this.confOutVars = _arrayList_1;
          FileInputStream _fileInputStream = new FileInputStream(path);
          inputStream = _fileInputStream;
          fileString = IOUtils.toString(inputStream);
          programString = fileString.substring(fileString.indexOf("PROGRAM"), fileString.indexOf("END_CONFIGURATION"));
          programString = programString.substring(programString.indexOf("PROCESS"));
          items = Arrays.<String>asList(programString.split(","));
          for (i = 0; (i < items.size()); i++) {
            boolean _contains = items.get(i).contains(":=");
            if (_contains) {
              item = items.get(i).substring(items.get(i).indexOf(":=")).replace(")", "").replace(" ", "").replaceAll("\\s.*", "");
              item = item.substring(2);
              if (((!Objects.equal(item, "TRUE")) && (!Objects.equal(item, "FALSE")))) {
                this.confInVars.add(item);
              }
            } else {
              boolean _contains_1 = items.get(i).contains("=>");
              if (_contains_1) {
                item = items.get(i).substring(items.get(i).indexOf("=>")).replace(")", "").replace(" ", "").replaceAll("\\s.*", "");
                item = item.substring(2);
                if (((!Objects.equal(item, "TRUE")) && (!Objects.equal(item, "FALSE")))) {
                  this.confOutVars.add(item);
                }
              }
            }
          }
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            _xtrycatchfinallyexpression = null;
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        } finally {
          inputStream.close();
        }
        _xblockexpression = _xtrycatchfinallyexpression;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  public String generateBody(final ConfigurationGenerator configuration) {
    StringConcatenation _builder = new StringConcatenation();
    String _generateVars = GeneratorUtil.generateVars(this.externalVarList);
    _builder.append(_generateVars);
    _builder.newLineIfNotEmpty();
    String _generateVars_1 = GeneratorUtil.generateVars(this.varList);
    _builder.append(_generateVars_1);
    _builder.newLineIfNotEmpty();
    String _generateVars_2 = GeneratorUtil.generateVars(this.tempVarList);
    _builder.append(_generateVars_2);
    _builder.newLineIfNotEmpty();
    _builder.append("taskTime = 0");
    _builder.newLine();
    _builder.newLine();
    {
      if (this.templateProcess) {
        String _generateConfVars = configuration.generateConfVars();
        _builder.append(_generateConfVars);
        _builder.newLineIfNotEmpty();
        String _generateResources = configuration.generateResources();
        _builder.append(_generateResources);
        _builder.newLineIfNotEmpty();
        {
          for(final String str : this.confInVars) {
            _builder.append("if \'");
            _builder.append(str);
            _builder.append("\' in globVars:");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("if not isinstance(");
            _builder.append(str, "\t");
            _builder.append(", list):");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("inVars[\'");
            _builder.append(str, "\t\t");
            _builder.append("\'] = ");
            _builder.append(str, "\t\t");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          for(final String str_1 : this.confOutVars) {
            _builder.append("if \'");
            _builder.append(str_1);
            _builder.append("\' in globVars:");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("if not isinstance(");
            _builder.append(str_1, "\t");
            _builder.append(", list):");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("outVars[\'");
            _builder.append(str_1, "\t\t");
            _builder.append("\'] = ");
            _builder.append(str_1, "\t\t");
            _builder.newLineIfNotEmpty();
          }
        }
      } else {
        String _generateVars_3 = GeneratorUtil.generateVars(this.inVarList);
        _builder.append(_generateVars_3);
        _builder.newLineIfNotEmpty();
        String _generateVars_4 = GeneratorUtil.generateVars(this.outVarList);
        _builder.append(_generateVars_4);
        _builder.newLineIfNotEmpty();
        String _generateVars_5 = GeneratorUtil.generateVars(this.inOutVarList);
        _builder.append(_generateVars_5);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("processesDict = {}");
    _builder.newLine();
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
    {
      for(final ProcessGenerator p : this.processList) {
        _builder.append("\t");
        _builder.append("class ");
        String _name = p.getName();
        _builder.append(_name, "\t");
        _builder.append(":");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        String _generateBody = p.generateBody(this.inVarList, this.outVarList, this.inOutVarList, this.externalVarList, this.varList, this.tempVarList, configuration);
        _builder.append(_generateBody, "\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _lowerCase = p.getName().toLowerCase();
        _builder.append(_lowerCase, "\t");
        _builder.append(" = ");
        String _name_1 = p.getName();
        _builder.append(_name_1, "\t");
        _builder.append("()");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("processes.append(");
        String _lowerCase_1 = p.getName().toLowerCase();
        _builder.append(_lowerCase_1, "\t");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("processesDict[\'");
        String _name_2 = p.getName();
        _builder.append(_name_2, "\t");
        _builder.append("\'] = ");
        String _lowerCase_2 = p.getName().toLowerCase();
        _builder.append(_lowerCase_2, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("pStates[\'");
        String _name_3 = p.getName();
        _builder.append(_name_3, "\t");
        _builder.append("_state\'] = ");
        String _lowerCase_3 = p.getName().toLowerCase();
        _builder.append(_lowerCase_3, "\t");
        _builder.append(".States(");
        String _lowerCase_4 = p.getName().toLowerCase();
        _builder.append(_lowerCase_4, "\t");
        _builder.append(".curState).name");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("pTimes[\'");
        String _name_4 = p.getName();
        _builder.append(_name_4, "\t");
        _builder.append("_time\'] = ");
        String _lowerCase_5 = p.getName().toLowerCase();
        _builder.append(_lowerCase_5, "\t");
        _builder.append(".startTime");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("start(processes[0])");
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
    _builder.append("\t\t");
    _builder.append("for process in self.processes:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("process.run()");
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

  public String getName() {
    return this.programName;
  }

  public EObject getEObject() {
    return this.object;
  }

  protected void parseProcesses(final EList<su.nsk.iae.post.poST.Process> processes) {
    final Consumer<su.nsk.iae.post.poST.Process> _function = (su.nsk.iae.post.poST.Process p) -> {
      final ProcessGenerator process = new ProcessGenerator(this, p);
      boolean _isTemplate = process.isTemplate();
      boolean _not = (!_isTemplate);
      if (_not) {
        this.processList.add(process);
      }
    };
    processes.stream().forEach(_function);
  }

  public void prepareProgramVars() {
    final Consumer<ProcessGenerator> _function = (ProcessGenerator x) -> {
      x.prepareProcessVars();
    };
    this.processList.stream().forEach(_function);
    this.addVar(GeneratorUtil.generateGlobalTime(), "TIME");
    final Consumer<ProcessGenerator> _function_1 = (ProcessGenerator x) -> {
      x.prepareTimeVars();
    };
    this.processList.stream().forEach(_function_1);
  }

  public void addProcess(final su.nsk.iae.post.poST.Process process) {
    ProcessGenerator _processGenerator = new ProcessGenerator(this, process);
    this.processList.add(_processGenerator);
  }

  public void addProcess(final su.nsk.iae.post.poST.Process process, final boolean active) {
    ProcessGenerator _processGenerator = new ProcessGenerator(this, process, active);
    this.processList.add(_processGenerator);
  }

  public void addVar(final EObject varDecl) {
    this.varList.add(varDecl);
  }

  public void addVar(final EObject varDecl, final String pref) {
    this.varList.add(varDecl, pref);
  }

  public void addVar(final String name, final String type) {
    this.varList.add(name, type);
  }

  public void addVar(final String name, final String type, final String value) {
    this.varList.add(name, type, value);
  }

  public void addVar(final String name, final String type, final String value, final boolean isConstant) {
    this.varList.add(name, type, value, isConstant);
  }

  public void addTempVar(final EObject varDecl) {
    this.tempVarList.add(varDecl);
  }

  public void addTempVar(final EObject varDecl, final String pref) {
    this.tempVarList.add(varDecl, pref);
  }

  public void addTempVar(final String name, final String type, final String value) {
    this.tempVarList.add(name, type, value);
  }

  public boolean isFirstProcess(final ProcessGenerator process) {
    ProcessGenerator _get = this.processList.get(0);
    return Objects.equal(_get, process);
  }

  public void addInVar(final EObject varDecl) {
    this.inVarList.add(varDecl);
  }

  public void addInVar(final EObject varDecl, final String pref) {
    this.inVarList.add(varDecl, pref);
  }

  public void addOutVar(final EObject varDecl) {
    this.outVarList.add(varDecl);
  }

  public void addOutVar(final EObject varDecl, final String pref) {
    this.outVarList.add(varDecl, pref);
  }

  public void addInOutVar(final EObject varDecl) {
    this.inOutVarList.add(varDecl);
  }

  public void addInOutVar(final EObject varDecl, final String pref) {
    this.inOutVarList.add(varDecl, pref);
  }

  public String generateProcessEnum(final String processName) {
    final Function1<ProcessGenerator, Boolean> _function = (ProcessGenerator it) -> {
      String _name = it.getName();
      return Boolean.valueOf(Objects.equal(_name, processName));
    };
    return GeneratorUtil.generateEnumName(IterableExtensions.<ProcessGenerator>findFirst(this.processList, _function));
  }

  public String generateProcessStart(final String processName) {
    final Function1<ProcessGenerator, Boolean> _function = (ProcessGenerator it) -> {
      String _name = it.getName();
      return Boolean.valueOf(Objects.equal(_name, processName));
    };
    return IterableExtensions.<ProcessGenerator>findFirst(this.processList, _function).generateStart();
  }

  public CharSequence generateProcessStop(final String processName) {
    final Function1<ProcessGenerator, Boolean> _function = (ProcessGenerator it) -> {
      String _name = it.getName();
      return Boolean.valueOf(Objects.equal(_name, processName));
    };
    return IterableExtensions.<ProcessGenerator>findFirst(this.processList, _function).generateStop();
  }

  public CharSequence generateProcessError(final String processName) {
    final Function1<ProcessGenerator, Boolean> _function = (ProcessGenerator it) -> {
      String _name = it.getName();
      return Boolean.valueOf(Objects.equal(_name, processName));
    };
    return IterableExtensions.<ProcessGenerator>findFirst(this.processList, _function).generateError();
  }
}
