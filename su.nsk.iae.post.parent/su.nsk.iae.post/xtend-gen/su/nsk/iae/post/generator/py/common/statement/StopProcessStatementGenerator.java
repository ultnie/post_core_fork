package su.nsk.iae.post.generator.py.common.statement;

import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.ProcessGenerator;
import su.nsk.iae.post.generator.py.common.ProgramGenerator;
import su.nsk.iae.post.generator.py.common.StateGenerator;
import su.nsk.iae.post.generator.py.common.StatementListGenerator;
import su.nsk.iae.post.poST.Statement;
import su.nsk.iae.post.poST.StopProcessStatement;
import su.nsk.iae.post.poST.Variable;

@SuppressWarnings("all")
public class StopProcessStatementGenerator extends IStatementGenerator {
  public StopProcessStatementGenerator(final ProgramGenerator program, final ProcessGenerator process, final StateGenerator state, final StatementListGenerator context) {
    super(program, process, state, context);
  }

  @Override
  public boolean checkStatement(final Statement statement) {
    return (statement instanceof StopProcessStatement);
  }

  @Override
  public String generateStatement(final Statement statement) {
    final StopProcessStatement s = ((StopProcessStatement) statement);
    StringConcatenation _builder = new StringConcatenation();
    {
      Variable _process = s.getProcess();
      boolean _tripleNotEquals = (_process != null);
      if (_tripleNotEquals) {
        CharSequence _generateProcessStop = this.program.generateProcessStop(s.getProcess().getName());
        _builder.append(_generateProcessStop);
      } else {
        CharSequence _generateStop = this.process.generateStop();
        _builder.append(_generateStop);
      }
    }
    return _builder.toString();
  }
}
