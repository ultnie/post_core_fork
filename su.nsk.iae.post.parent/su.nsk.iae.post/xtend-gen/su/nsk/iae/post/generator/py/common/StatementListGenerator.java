package su.nsk.iae.post.generator.py.common;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.statement.AssignmentStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.CaseStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.ErrorProcessStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.ExitStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.FBInvocationGenerator;
import su.nsk.iae.post.generator.py.common.statement.ForStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.IStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.IfStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.InlineStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.RepeatStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.ResetTimerStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.SetStateStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.StartProcessStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.StopProcessStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.SubprogramControlStatementGenerator;
import su.nsk.iae.post.generator.py.common.statement.WhileStatementGenerator;
import su.nsk.iae.post.generator.py.common.util.GeneratorUtil;
import su.nsk.iae.post.poST.ArrayVariable;
import su.nsk.iae.post.poST.Expression;
import su.nsk.iae.post.poST.ParamAssignmentElements;
import su.nsk.iae.post.poST.ProcessStatusExpression;
import su.nsk.iae.post.poST.Statement;
import su.nsk.iae.post.poST.StatementList;
import su.nsk.iae.post.poST.SymbolicVariable;

@SuppressWarnings("all")
public class StatementListGenerator {
  private ProgramGenerator program;

  private ProcessGenerator process;

  private StateGenerator state;

  private List<IStatementGenerator> statementGenerators;

  public StatementListGenerator(final ProgramGenerator program, final ProcessGenerator process, final StateGenerator state) {
    this.program = program;
    this.process = process;
    this.state = state;
    this.statementGenerators = this.initStatementGenerators();
  }

  public String generateStatementList(final StatementList statementList) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Statement> _statements = statementList.getStatements();
      for(final Statement s : _statements) {
        String _generateStatement = this.generateStatement(s);
        _builder.append(_generateStatement);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }

  public String generateStatement(final Statement statement) {
    for (final IStatementGenerator sg : this.statementGenerators) {
      boolean _checkStatement = sg.checkStatement(statement);
      if (_checkStatement) {
        return sg.generateStatement(statement);
      }
    }
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }

  public String generateExpression(final Expression exp) {
    final Function<SymbolicVariable, String> _function = (SymbolicVariable x) -> {
      return this.generateVar(x);
    };
    final Function<ArrayVariable, String> _function_1 = (ArrayVariable x) -> {
      return this.generateArray(x);
    };
    final Function<ProcessStatusExpression, String> _function_2 = (ProcessStatusExpression x) -> {
      return this.generateProcessStatus(x);
    };
    return GeneratorUtil.generateExpression(exp, _function, _function_1, _function_2);
  }

  public String generateParamAssignmentElements(final ParamAssignmentElements elements) {
    final Function<Expression, String> _function = (Expression x) -> {
      return this.generateExpression(x);
    };
    return GeneratorUtil.generateParamAssignmentElements(elements, _function);
  }

  public String generateVar(final SymbolicVariable varName) {
    boolean _containsVar = this.process.containsVar(varName.getName());
    if (_containsVar) {
      return GeneratorUtil.generateVarName(this.process, varName.getName());
    }
    return varName.getName();
  }

  public String generateArray(final ArrayVariable varDecl) {
    StringConcatenation _builder = new StringConcatenation();
    String _generateVar = this.generateVar(varDecl.getVariable());
    _builder.append(_generateVar);
    _builder.append("[");
    String _generateExpression = this.generateExpression(varDecl.getIndex());
    _builder.append(_generateExpression);
    _builder.append("]");
    return _builder.toString();
  }

  public String generateProcessStatus(final ProcessStatusExpression exp) {
    boolean _isActive = exp.isActive();
    if (_isActive) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("((processesDict[\'");
      String _name = exp.getProcess().getName();
      _builder.append(_name);
      _builder.append("\'].curState != processesDict[\'");
      String _name_1 = exp.getProcess().getName();
      _builder.append(_name_1);
      _builder.append("\'].States.Stop) and (processesDict[\'");
      String _name_2 = exp.getProcess().getName();
      _builder.append(_name_2);
      _builder.append("\'].curState != processesDict[\'");
      String _name_3 = exp.getProcess().getName();
      _builder.append(_name_3);
      _builder.append("\'].States.Error))");
      return _builder.toString();
    } else {
      boolean _isInactive = exp.isInactive();
      if (_isInactive) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("((processesDict[\'");
        String _name_4 = exp.getProcess().getName();
        _builder_1.append(_name_4);
        _builder_1.append("\'].curState == processesDict[\'");
        String _name_5 = exp.getProcess().getName();
        _builder_1.append(_name_5);
        _builder_1.append("\'].States.Stop) or (processesDict[\'");
        String _name_6 = exp.getProcess().getName();
        _builder_1.append(_name_6);
        _builder_1.append("\'].curState == processesDict[\'");
        String _name_7 = exp.getProcess().getName();
        _builder_1.append(_name_7);
        _builder_1.append("\'].States.Error))");
        return _builder_1.toString();
      } else {
        boolean _isStop = exp.isStop();
        if (_isStop) {
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("(processesDict[\'");
          String _name_8 = exp.getProcess().getName();
          _builder_2.append(_name_8);
          _builder_2.append("\'].curState == processesDict[\'");
          String _name_9 = exp.getProcess().getName();
          _builder_2.append(_name_9);
          _builder_2.append("\'].States.Stop)");
          return _builder_2.toString();
        }
      }
    }
    StringConcatenation _builder_3 = new StringConcatenation();
    _builder_3.append("(processesDict[\'");
    String _name_10 = exp.getProcess().getName();
    _builder_3.append(_name_10);
    _builder_3.append("\'].curState == processesDict[\'");
    String _name_11 = exp.getProcess().getName();
    _builder_3.append(_name_11);
    _builder_3.append("\'].States.Error)");
    return _builder_3.toString();
  }

  private List<IStatementGenerator> initStatementGenerators() {
    AssignmentStatementGenerator _assignmentStatementGenerator = new AssignmentStatementGenerator(this.program, this.process, this.state, this);
    IfStatementGenerator _ifStatementGenerator = new IfStatementGenerator(this.program, this.process, this.state, this);
    CaseStatementGenerator _caseStatementGenerator = new CaseStatementGenerator(this.program, this.process, this.state, this);
    ForStatementGenerator _forStatementGenerator = new ForStatementGenerator(this.program, this.process, this.state, this);
    WhileStatementGenerator _whileStatementGenerator = new WhileStatementGenerator(this.program, this.process, this.state, this);
    RepeatStatementGenerator _repeatStatementGenerator = new RepeatStatementGenerator(this.program, this.process, this.state, this);
    FBInvocationGenerator _fBInvocationGenerator = new FBInvocationGenerator(this.program, this.process, this.state, this);
    StartProcessStatementGenerator _startProcessStatementGenerator = new StartProcessStatementGenerator(this.program, this.process, this.state, this);
    StopProcessStatementGenerator _stopProcessStatementGenerator = new StopProcessStatementGenerator(this.program, this.process, this.state, this);
    ErrorProcessStatementGenerator _errorProcessStatementGenerator = new ErrorProcessStatementGenerator(this.program, this.process, this.state, this);
    SetStateStatementGenerator _setStateStatementGenerator = new SetStateStatementGenerator(this.program, this.process, this.state, this);
    ResetTimerStatementGenerator _resetTimerStatementGenerator = new ResetTimerStatementGenerator(this.program, this.process, this.state, this);
    SubprogramControlStatementGenerator _subprogramControlStatementGenerator = new SubprogramControlStatementGenerator(this.program, this.process, this.state, this);
    ExitStatementGenerator _exitStatementGenerator = new ExitStatementGenerator(this.program, this.process, this.state, this);
    InlineStatementGenerator _inlineStatementGenerator = new InlineStatementGenerator(this.program, this.process, this.state, this);
    return Arrays.<IStatementGenerator>asList(_assignmentStatementGenerator, _ifStatementGenerator, _caseStatementGenerator, _forStatementGenerator, _whileStatementGenerator, _repeatStatementGenerator, _fBInvocationGenerator, _startProcessStatementGenerator, _stopProcessStatementGenerator, _errorProcessStatementGenerator, _setStateStatementGenerator, _resetTimerStatementGenerator, _subprogramControlStatementGenerator, _exitStatementGenerator, _inlineStatementGenerator);
  }
}
