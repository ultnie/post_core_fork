package su.nsk.iae.post.generator.py.common.statement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.ProcessGenerator;
import su.nsk.iae.post.generator.py.common.ProgramGenerator;
import su.nsk.iae.post.generator.py.common.StateGenerator;
import su.nsk.iae.post.generator.py.common.StatementListGenerator;
import su.nsk.iae.post.poST.Inline_code;
import su.nsk.iae.post.poST.Statement;

@SuppressWarnings("all")
public class InlineStatementGenerator extends IStatementGenerator {
  public InlineStatementGenerator(final ProgramGenerator program, final ProcessGenerator process, final StateGenerator state, final StatementListGenerator context) {
    super(program, process, state, context);
  }

  @Override
  public boolean checkStatement(final Statement statement) {
    return (statement instanceof Inline_code);
  }

  @Override
  public String generateStatement(final Statement statement) {
    final Inline_code s = ((Inline_code) statement);
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<String> _inline_code = s.getInline_code();
      for(final String p : _inline_code) {
        String _replaceAll = p.replaceAll("^[$]", "").replaceAll("^[\\s]", "");
        _builder.append(_replaceAll);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
