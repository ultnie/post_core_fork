package su.nsk.iae.post.generator.py.common.statement;

import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.ProcessGenerator;
import su.nsk.iae.post.generator.py.common.ProgramGenerator;
import su.nsk.iae.post.generator.py.common.StateGenerator;
import su.nsk.iae.post.generator.py.common.StatementListGenerator;
import su.nsk.iae.post.generator.py.common.util.GeneratorUtil;
import su.nsk.iae.post.poST.ResetTimerStatement;
import su.nsk.iae.post.poST.Statement;

@SuppressWarnings("all")
public class ResetTimerStatementGenerator extends IStatementGenerator {
  public ResetTimerStatementGenerator(final ProgramGenerator program, final ProcessGenerator process, final StateGenerator state, final StatementListGenerator context) {
    super(program, process, state, context);
  }

  @Override
  public boolean checkStatement(final Statement statement) {
    return (statement instanceof ResetTimerStatement);
  }

  @Override
  public String generateStatement(final Statement statement) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("setVariable(\'");
    String _generateTimeoutName = GeneratorUtil.generateTimeoutName(this.process);
    _builder.append(_generateTimeoutName);
    _builder.append("\', ");
    String _generateGlobalTime = GeneratorUtil.generateGlobalTime();
    _builder.append(_generateGlobalTime);
    _builder.append(")");
    return _builder.toString();
  }
}
