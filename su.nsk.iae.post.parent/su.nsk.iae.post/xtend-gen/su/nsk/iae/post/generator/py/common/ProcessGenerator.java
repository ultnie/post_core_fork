package su.nsk.iae.post.generator.py.common;

import com.google.common.base.Objects;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import su.nsk.iae.post.generator.py.common.util.GeneratorUtil;
import su.nsk.iae.post.generator.py.common.vars.InputOutputVarHelper;
import su.nsk.iae.post.generator.py.common.vars.InputVarHelper;
import su.nsk.iae.post.generator.py.common.vars.OutputVarHelper;
import su.nsk.iae.post.generator.py.common.vars.SimpleVarHelper;
import su.nsk.iae.post.generator.py.common.vars.TempVarHelper;
import su.nsk.iae.post.generator.py.common.vars.VarHelper;
import su.nsk.iae.post.generator.py.common.vars.data.VarData;
import su.nsk.iae.post.generator.py.configuration.ConfigurationGenerator;
import su.nsk.iae.post.poST.InputOutputVarDeclaration;
import su.nsk.iae.post.poST.InputVarDeclaration;
import su.nsk.iae.post.poST.OutputVarDeclaration;
import su.nsk.iae.post.poST.State;
import su.nsk.iae.post.poST.TempVarDeclaration;
import su.nsk.iae.post.poST.VarDeclaration;

@SuppressWarnings("all")
public class ProcessGenerator {
  private ProgramGenerator program;

  private su.nsk.iae.post.poST.Process process;

  private boolean active;

  private VarHelper inVarList = new InputVarHelper();

  private VarHelper outVarList = new OutputVarHelper();

  private VarHelper inOutVarList = new InputOutputVarHelper();

  private VarHelper varList = new SimpleVarHelper();

  private VarHelper tempVarList = new TempVarHelper();

  private List<StateGenerator> stateList = new LinkedList<StateGenerator>();

  public ProcessGenerator(final ProgramGenerator program, final su.nsk.iae.post.poST.Process process) {
    this(program, process, false);
  }

  public ProcessGenerator(final ProgramGenerator program, final su.nsk.iae.post.poST.Process process, final boolean active) {
    this.program = program;
    this.process = process;
    this.active = active;
    final Consumer<State> _function = (State s) -> {
      StateGenerator _stateGenerator = new StateGenerator(program, this, s);
      this.stateList.add(_stateGenerator);
    };
    process.getStates().stream().forEach(_function);
  }

  public String generateBody(final VarHelper pInVars, final VarHelper pOutVars, final VarHelper pInOutVars, final VarHelper pExVars, final VarHelper pVars, final VarHelper pTVars, final ConfigurationGenerator conf) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("startTime = \"\"");
    _builder.newLine();
    _builder.append("global pStates");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class States(enum.Enum):");
    _builder.newLine();
    {
      for(final StateGenerator s : this.stateList) {
        _builder.append("\t");
        String _name = s.getName();
        _builder.append(_name, "\t");
        _builder.append(" = enum.auto()");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("Stop = 254");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Error = 255");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("def set_next(self):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global Vars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("self.curState = self.States(self.curState.value + 1)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("pStates[f\'{self.name()}_state\'] = self.States(self.curState).name");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("def name(self):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return self.__class__.__name__");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("curState = ");
    {
      if (this.active) {
        _builder.append("States(1)");
      } else {
        _builder.append("States.Stop");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("def run(self):");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global inVars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global outVars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global inOutVars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global Vars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global exVars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global globVars");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global pStates");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global processesDict");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("global _global_time");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("match self.curState:");
    _builder.newLine();
    {
      for(final StateGenerator s_1 : this.stateList) {
        _builder.append("\t\t");
        _builder.append("case self.States.");
        String _name_1 = s_1.getName();
        _builder.append(_name_1, "\t\t");
        _builder.append(":");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("self.startTime = ");
        String _generateGlobalTime = GeneratorUtil.generateGlobalTime();
        _builder.append(_generateGlobalTime, "\t\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("pTimes[\'");
        String _name_2 = this.getName();
        _builder.append(_name_2, "\t\t\t");
        _builder.append("_time\'] = self.startTime");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        String _generateBody = s_1.generateBody();
        _builder.append(_generateBody, "\t\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("case _:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("pass");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }

  public String getName() {
    return this.process.getName();
  }

  public boolean containsVar(final String name) {
    return ((((this.varList.contains(name) || this.tempVarList.contains(name)) || 
      this.inVarList.contains(name)) || this.outVarList.contains(name)) || this.inOutVarList.contains(name));
  }

  public String generateSetState(final String stateName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      final Function1<StateGenerator, Boolean> _function = (StateGenerator it) -> {
        String _name = it.getName();
        return Boolean.valueOf(Objects.equal(_name, stateName));
      };
      boolean _hasTimeout = IterableExtensions.<StateGenerator>findFirst(this.stateList, _function).hasTimeout();
      if (_hasTimeout) {
        _builder.append("setVariable(\'");
        String _generateTimeoutName = GeneratorUtil.generateTimeoutName(this);
        _builder.append(_generateTimeoutName);
        _builder.append("\', ");
        String _generateGlobalTime = GeneratorUtil.generateGlobalTime();
        _builder.append(_generateGlobalTime);
        _builder.append(")");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("set_state(self.States.");
    _builder.append(stateName);
    _builder.append(", self)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }

  public String generateNextState(final StateGenerator state) {
    int _indexOf = this.stateList.indexOf(state);
    int _plus = (_indexOf + 1);
    int _size = this.stateList.size();
    boolean _lessThan = (_plus < _size);
    if (_lessThan) {
      int _indexOf_1 = this.stateList.indexOf(state);
      int _plus_1 = (_indexOf_1 + 1);
      final StateGenerator s = this.stateList.get(_plus_1);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("self.set_next()");
      _builder.newLine();
      return _builder.toString();
    }
    final StateGenerator s_1 = this.stateList.get(0);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("start(self)");
    _builder_1.newLine();
    return _builder_1.toString();
  }

  public String generateStart() {
    StringConcatenation _builder = new StringConcatenation();
    {
      List<VarData> _list = this.varList.getList();
      for(final VarData v : _list) {
        {
          if (((v.getValue() != null) && (!v.isConstant()))) {
            _builder.append("setVariable(\'");
            String _generateVarName = GeneratorUtil.generateVarName(this, v.getName());
            _builder.append(_generateVarName);
            _builder.append("\', ");
            String _value = v.getValue();
            _builder.append(_value);
            _builder.append(")");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      boolean _hasTimeout = this.stateList.get(0).hasTimeout();
      if (_hasTimeout) {
        _builder.append("setVariable(\'");
        String _generateTimeoutName = GeneratorUtil.generateTimeoutName(this);
        _builder.append(_generateTimeoutName);
        _builder.append("\', ");
        String _generateGlobalTime = GeneratorUtil.generateGlobalTime();
        _builder.append(_generateGlobalTime);
        _builder.append(")");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("start(processesDict[\'");
    String _name = this.getName();
    _builder.append(_name);
    _builder.append("\'])");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }

  public CharSequence generateStop() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("stop(processesDict[\'");
    String _name = this.getName();
    _builder.append(_name);
    _builder.append("\'])");
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  public CharSequence generateError() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("error(processesDict[\'");
    String _name = this.getName();
    _builder.append(_name);
    _builder.append("\'])");
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  public boolean isTemplate() {
    return (((!this.process.getProcInVars().isEmpty()) || (!this.process.getProcOutVars().isEmpty())) || (!this.process.getProcInOutVars().isEmpty()));
  }

  public void prepareStateVars(final boolean templateProcess) {
    for (int i = 0; (i < this.stateList.size()); i++) {
      this.program.addVar(GeneratorUtil.generateEnumStateConstant(this, this.stateList.get(i).getName()), "INT", Integer.valueOf(i).toString(), true);
    }
    if (((templateProcess && this.active) || ((!templateProcess) && this.program.isFirstProcess(this)))) {
      this.program.addVar(GeneratorUtil.generateEnumName(this), "INT", GeneratorUtil.generateEnumStateConstant(this, this.stateList.get(0).getName()));
    } else {
      this.program.addVar(GeneratorUtil.generateEnumName(this), "INT", GeneratorUtil.generateStopConstant());
    }
  }

  public void prepareTimeVars() {
    boolean _hasTimeouts = this.hasTimeouts();
    if (_hasTimeouts) {
      this.program.addVar(GeneratorUtil.generateTimeoutName(this), "TIME");
    }
  }

  public void prepareProcessVars() {
    this.prepareVars();
    this.prepareTempVars();
    boolean _isTemplate = this.isTemplate();
    if (_isTemplate) {
      this.prepareInVars();
      this.prepareOutVars();
      this.prepareInOutVars();
    }
  }

  private void prepareVars() {
    final Consumer<VarDeclaration> _function = (VarDeclaration varDecl) -> {
      this.varList.add(varDecl);
      this.program.addVar(varDecl, GeneratorUtil.generateVarName(this, ""));
    };
    this.process.getProcVars().stream().forEach(_function);
  }

  private void prepareTempVars() {
    final Consumer<TempVarDeclaration> _function = (TempVarDeclaration varDecl) -> {
      this.tempVarList.add(varDecl);
      this.program.addTempVar(varDecl, GeneratorUtil.generateVarName(this, ""));
    };
    this.process.getProcTempVars().stream().forEach(_function);
  }

  private void prepareInVars() {
    final Consumer<InputVarDeclaration> _function = (InputVarDeclaration varDecl) -> {
      this.inVarList.add(varDecl);
      this.program.addInVar(varDecl, GeneratorUtil.generateVarName(this, ""));
    };
    this.process.getProcInVars().stream().forEach(_function);
  }

  private void prepareOutVars() {
    final Consumer<OutputVarDeclaration> _function = (OutputVarDeclaration varDecl) -> {
      this.outVarList.add(varDecl);
      this.program.addOutVar(varDecl, GeneratorUtil.generateVarName(this, ""));
    };
    this.process.getProcOutVars().stream().forEach(_function);
  }

  private void prepareInOutVars() {
    final Consumer<InputOutputVarDeclaration> _function = (InputOutputVarDeclaration varDecl) -> {
      this.inOutVarList.add(varDecl);
      this.program.addInOutVar(varDecl, GeneratorUtil.generateVarName(this, ""));
    };
    this.process.getProcInOutVars().stream().forEach(_function);
  }

  private boolean hasTimeouts() {
    final Predicate<StateGenerator> _function = (StateGenerator x) -> {
      return x.hasTimeout();
    };
    return this.stateList.stream().anyMatch(_function);
  }
}
