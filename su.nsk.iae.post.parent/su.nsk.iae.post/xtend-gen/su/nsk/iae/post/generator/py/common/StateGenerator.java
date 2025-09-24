package su.nsk.iae.post.generator.py.common;

import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.util.GeneratorUtil;
import su.nsk.iae.post.poST.State;
import su.nsk.iae.post.poST.SymbolicVariable;
import su.nsk.iae.post.poST.TimeoutStatement;

@SuppressWarnings("all")
public class StateGenerator {
  private ProcessGenerator process;

  private State state;

  private StatementListGenerator statementList;

  public StateGenerator(final ProgramGenerator program, final ProcessGenerator process, final State state) {
    this.process = process;
    this.state = state;
    StatementListGenerator _statementListGenerator = new StatementListGenerator(program, process, this);
    this.statementList = _statementListGenerator;
  }

  public String generateBody() {
    StringConcatenation _builder = new StringConcatenation();
    String _generateStatementList = this.statementList.generateStatementList(this.state.getStatement());
    _builder.append(_generateStatementList);
    _builder.newLineIfNotEmpty();
    {
      TimeoutStatement _timeout = this.state.getTimeout();
      boolean _tripleNotEquals = (_timeout != null);
      if (_tripleNotEquals) {
        String _generateTimeout = this.generateTimeout();
        _builder.append(_generateTimeout);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }

  public String getName() {
    return this.state.getName();
  }

  public boolean hasTimeout() {
    TimeoutStatement _timeout = this.state.getTimeout();
    return (_timeout != null);
  }

  private String generateTimeout() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("if (");
    String _generateGlobalTime = GeneratorUtil.generateGlobalTime();
    _builder.append(_generateGlobalTime);
    _builder.append(" - ");
    String _generateTimeoutName = GeneratorUtil.generateTimeoutName(this.process);
    _builder.append(_generateTimeoutName);
    _builder.append(") >= ");
    {
      SymbolicVariable _variable = this.state.getTimeout().getVariable();
      boolean _tripleNotEquals = (_variable != null);
      if (_tripleNotEquals) {
        String _generateVar = this.statementList.generateVar(this.state.getTimeout().getVariable());
        _builder.append(_generateVar);
      } else {
        String _generateConstant = GeneratorUtil.generateConstant(this.state.getTimeout().getConst());
        _builder.append(_generateConstant);
      }
    }
    _builder.append(":");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _generateStatementList = this.statementList.generateStatementList(this.state.getTimeout().getStatement());
    _builder.append(_generateStatementList, "\t");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
}
