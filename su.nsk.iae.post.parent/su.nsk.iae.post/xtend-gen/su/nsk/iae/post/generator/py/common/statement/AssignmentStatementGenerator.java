package su.nsk.iae.post.generator.py.common.statement;

import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.ProcessGenerator;
import su.nsk.iae.post.generator.py.common.ProgramGenerator;
import su.nsk.iae.post.generator.py.common.StateGenerator;
import su.nsk.iae.post.generator.py.common.StatementListGenerator;
import su.nsk.iae.post.poST.AssignmentStatement;
import su.nsk.iae.post.poST.Statement;
import su.nsk.iae.post.poST.SymbolicVariable;

@SuppressWarnings("all")
public class AssignmentStatementGenerator extends IStatementGenerator {
  public AssignmentStatementGenerator(final ProgramGenerator program, final ProcessGenerator process, final StateGenerator state, final StatementListGenerator context) {
    super(program, process, state, context);
  }

  @Override
  public boolean checkStatement(final Statement statement) {
    return (statement instanceof AssignmentStatement);
  }

  @Override
  public String generateStatement(final Statement statement) {
    final AssignmentStatement s = ((AssignmentStatement) statement);
    SymbolicVariable _variable = s.getVariable();
    boolean _tripleNotEquals = (_variable != null);
    if (_tripleNotEquals) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("setVariable(\'");
      String _generateVar = this.context.generateVar(s.getVariable());
      _builder.append(_generateVar);
      _builder.append("\', ");
      String _generateExpression = this.context.generateExpression(s.getValue());
      _builder.append(_generateExpression);
      _builder.append(")");
      return _builder.toString();
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("try: ");
    _builder_1.newLine();
    _builder_1.append("\t");
    String _generateArray = this.context.generateArray(s.getArray());
    _builder_1.append(_generateArray, "\t");
    _builder_1.append(".__set__(");
    String _generateExpression_1 = this.context.generateExpression(s.getValue());
    _builder_1.append(_generateExpression_1, "\t");
    _builder_1.append(")");
    _builder_1.newLineIfNotEmpty();
    _builder_1.append("except: ");
    _builder_1.newLine();
    _builder_1.append("\t");
    String _generateArray_1 = this.context.generateArray(s.getArray());
    _builder_1.append(_generateArray_1, "\t");
    _builder_1.append(" = ");
    String _generateExpression_2 = this.context.generateExpression(s.getValue());
    _builder_1.append(_generateExpression_2, "\t");
    return _builder_1.toString();
  }
}
