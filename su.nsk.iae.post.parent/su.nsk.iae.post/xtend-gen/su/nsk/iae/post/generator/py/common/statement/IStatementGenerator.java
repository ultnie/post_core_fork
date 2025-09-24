package su.nsk.iae.post.generator.py.common.statement;

import su.nsk.iae.post.generator.py.common.ProcessGenerator;
import su.nsk.iae.post.generator.py.common.ProgramGenerator;
import su.nsk.iae.post.generator.py.common.StateGenerator;
import su.nsk.iae.post.generator.py.common.StatementListGenerator;
import su.nsk.iae.post.poST.Statement;

@SuppressWarnings("all")
public abstract class IStatementGenerator {
  protected ProgramGenerator program;

  protected ProcessGenerator process;

  protected StateGenerator state;

  protected StatementListGenerator context;

  public IStatementGenerator(final ProgramGenerator program, final ProcessGenerator process, final StateGenerator state, final StatementListGenerator context) {
    this.program = program;
    this.process = process;
    this.state = state;
    this.context = context;
  }

  public abstract boolean checkStatement(final Statement statement);

  public abstract String generateStatement(final Statement statement);
}
