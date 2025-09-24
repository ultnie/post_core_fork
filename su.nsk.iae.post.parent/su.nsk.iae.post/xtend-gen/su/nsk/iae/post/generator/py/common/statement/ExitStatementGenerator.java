package su.nsk.iae.post.generator.py.common.statement;

import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.ProcessGenerator;
import su.nsk.iae.post.generator.py.common.ProgramGenerator;
import su.nsk.iae.post.generator.py.common.StateGenerator;
import su.nsk.iae.post.generator.py.common.StatementListGenerator;
import su.nsk.iae.post.poST.ExitStatement;
import su.nsk.iae.post.poST.Statement;

@SuppressWarnings("all")
public class ExitStatementGenerator extends IStatementGenerator {
  public ExitStatementGenerator(final ProgramGenerator program, final ProcessGenerator process, final StateGenerator state, final StatementListGenerator context) {
    super(program, process, state, context);
  }

  @Override
  public boolean checkStatement(final Statement statement) {
    return (statement instanceof ExitStatement);
  }

  @Override
  public String generateStatement(final Statement statement) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("break");
    return _builder.toString();
  }
}
