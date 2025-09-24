package su.nsk.iae.post.generator.py.configuration;

import org.eclipse.xtend2.lib.StringConcatenation;
import su.nsk.iae.post.generator.py.common.util.GeneratorUtil;
import su.nsk.iae.post.poST.Constant;
import su.nsk.iae.post.poST.Task;
import su.nsk.iae.post.poST.TaskInitialization;

@SuppressWarnings("all")
public class TaskGenerator {
  private Task task;

  public TaskGenerator(final Task task) {
    this.task = task;
  }

  public String generateTask() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("taskTime = ");
    String _generateFirstArg = this.generateFirstArg();
    _builder.append(_generateFirstArg);
    return _builder.toString();
  }

  private String generateFirstArg() {
    final TaskInitialization init = this.task.getInit();
    Constant _interval = init.getInterval();
    boolean _tripleNotEquals = (_interval != null);
    if (_tripleNotEquals) {
      StringConcatenation _builder = new StringConcatenation();
      String _generateConstant = GeneratorUtil.generateConstant(init.getInterval());
      _builder.append(_generateConstant);
      return _builder.toString();
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    String _generateConstant_1 = GeneratorUtil.generateConstant(init.getSingle());
    _builder_1.append(_generateConstant_1);
    return _builder_1.toString();
  }

  public String generateTaskTime() {
    final TaskInitialization init = this.task.getInit();
    Constant _interval = init.getInterval();
    boolean _tripleNotEquals = (_interval != null);
    if (_tripleNotEquals) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(" ");
      _builder.append("= ");
      String _generateConstant = GeneratorUtil.generateConstant(init.getInterval());
      _builder.append(_generateConstant, " ");
      return _builder.toString();
    }
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(" ");
    _builder_1.append("= None");
    return _builder_1.toString();
  }
}
