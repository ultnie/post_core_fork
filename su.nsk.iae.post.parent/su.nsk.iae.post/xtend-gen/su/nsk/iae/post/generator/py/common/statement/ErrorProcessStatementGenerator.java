package su.nsk.iae.post.generator.py.common.statement;

import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.ProcessGenerator;
import su.nsk.iae.post.generator.py.common.ProgramGenerator;
import su.nsk.iae.post.generator.py.common.StateGenerator;
import su.nsk.iae.post.generator.py.common.StatementListGenerator;
import su.nsk.iae.post.poST.ErrorProcessStatement;
import su.nsk.iae.post.poST.Statement;
import su.nsk.iae.post.poST.Variable;

@SuppressWarnings("all")
public class ErrorProcessStatementGenerator extends IStatementGenerator {
  public ErrorProcessStatementGenerator(final ProgramGenerator program, final ProcessGenerator process, final StateGenerator state, final StatementListGenerator context) {
    super(program, process, state, context);
  }

  @Override
  public boolean checkStatement(final Statement statement) {
    return (statement instanceof ErrorProcessStatement);
  }

  @Override
  public String generateStatement(final Statement statement) {
    final ErrorProcessStatement s = ((ErrorProcessStatement) statement);
    StringConcatenation _builder = new StringConcatenation();
    {
      Variable _process = s.getProcess();
      boolean _tripleNotEquals = (_process != null);
      if (_tripleNotEquals) {
        CharSequence _generateProcessError = this.program.generateProcessError(s.getProcess().getName());
        _builder.append(_generateProcessError);
      } else {
        CharSequence _generateError = this.process.generateError();
        _builder.append(_generateError);
      }
    }
    return _builder.toString();
  }
}
