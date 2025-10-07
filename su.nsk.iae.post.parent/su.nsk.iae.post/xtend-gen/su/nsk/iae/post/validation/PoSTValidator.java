package su.nsk.iae.post.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;
import su.nsk.iae.post.poST.ArrayInterval;
import su.nsk.iae.post.poST.ArraySpecification;
import su.nsk.iae.post.poST.AssignmentStatement;
import su.nsk.iae.post.poST.AssignmentType;
import su.nsk.iae.post.poST.AttachVariableConfElement;
import su.nsk.iae.post.poST.CaseElement;
import su.nsk.iae.post.poST.Configuration;
import su.nsk.iae.post.poST.ErrorProcessStatement;
import su.nsk.iae.post.poST.Expression;
import su.nsk.iae.post.poST.ExternalVarDeclaration;
import su.nsk.iae.post.poST.ExternalVarInitDeclaration;
import su.nsk.iae.post.poST.FBInvocation;
import su.nsk.iae.post.poST.ForStatement;
import su.nsk.iae.post.poST.FunctionBlock;
import su.nsk.iae.post.poST.FunctionCall;
import su.nsk.iae.post.poST.GlobalVarDeclaration;
import su.nsk.iae.post.poST.GlobalVarInitDeclaration;
import su.nsk.iae.post.poST.IfStatement;
import su.nsk.iae.post.poST.InputOutputVarDeclaration;
import su.nsk.iae.post.poST.InputVarDeclaration;
import su.nsk.iae.post.poST.Model;
import su.nsk.iae.post.poST.OutputVarDeclaration;
import su.nsk.iae.post.poST.PoSTPackage;
import su.nsk.iae.post.poST.ProcessStatusExpression;
import su.nsk.iae.post.poST.ProcessVarDeclaration;
import su.nsk.iae.post.poST.ProcessVarInitDeclaration;
import su.nsk.iae.post.poST.ProcessVariable;
import su.nsk.iae.post.poST.Program;
import su.nsk.iae.post.poST.ProgramConfElement;
import su.nsk.iae.post.poST.ProgramConfElements;
import su.nsk.iae.post.poST.ProgramConfiguration;
import su.nsk.iae.post.poST.RepeatStatement;
import su.nsk.iae.post.poST.Resource;
import su.nsk.iae.post.poST.SetStateStatement;
import su.nsk.iae.post.poST.SimpleSpecificationInit;
import su.nsk.iae.post.poST.StartProcessStatement;
import su.nsk.iae.post.poST.Statement;
import su.nsk.iae.post.poST.StatementList;
import su.nsk.iae.post.poST.StopProcessStatement;
import su.nsk.iae.post.poST.SymbolicVariable;
import su.nsk.iae.post.poST.Task;
import su.nsk.iae.post.poST.TempVarDeclaration;
import su.nsk.iae.post.poST.TemplateProcessAttachVariableConfElement;
import su.nsk.iae.post.poST.TemplateProcessConfElement;
import su.nsk.iae.post.poST.TemplateProcessElements;
import su.nsk.iae.post.poST.TimeoutStatement;
import su.nsk.iae.post.poST.VarDeclaration;
import su.nsk.iae.post.poST.VarInitDeclaration;
import su.nsk.iae.post.poST.Variable;
import su.nsk.iae.post.poST.WhileStatement;

@SuppressWarnings("all")
public class PoSTValidator extends AbstractPoSTValidator {
  private final PoSTPackage ePackage = PoSTPackage.eINSTANCE;

  /**
   * ======================= START Variables Checks =======================
   */
  @Check
  public void checkSymbolicVariable_NameConflicts(final SymbolicVariable ele) {
    final Program program = EcoreUtil2.<Program>getContainerOfType(ele, Program.class);
    if ((program != null)) {
      final su.nsk.iae.post.poST.Process process = EcoreUtil2.<su.nsk.iae.post.poST.Process>getContainerOfType(ele, su.nsk.iae.post.poST.Process.class);
      if (((process != null) && this.checkNameRepetition(process, ele))) {
        this.error("Name error: Process already has a Variable with this name", this.ePackage.getVariable_Name());
        return;
      }
      boolean _checkNameRepetition = this.checkNameRepetition(program, ele);
      if (_checkNameRepetition) {
        this.error("Name error: Program already has a Variable with this name", this.ePackage.getVariable_Name());
        return;
      }
    }
    final Configuration configuration = EcoreUtil2.<Configuration>getContainerOfType(ele, Configuration.class);
    if ((configuration != null)) {
      final Resource resource = EcoreUtil2.<Resource>getContainerOfType(ele, Resource.class);
      if (((resource != null) && this.checkNameRepetition(resource, ele))) {
        this.error("Name error: Resource already has a Variable with this name", this.ePackage.getVariable_Name());
        return;
      }
      boolean _checkNameRepetition_1 = this.checkNameRepetition(configuration, ele);
      if (_checkNameRepetition_1) {
        this.error("Name error: Configuration already has a Variable with this name", this.ePackage.getVariable_Name());
        return;
      }
    }
    final Model model = EcoreUtil2.<Model>getContainerOfType(ele, Model.class);
    boolean _checkNameRepetition_2 = this.checkNameRepetition(model, ele);
    if (_checkNameRepetition_2) {
      this.error("Name error: Conflict with the name of a global Variable", this.ePackage.getVariable_Name());
      return;
    }
    if (((program != null) && (model.getConf() != null))) {
      boolean _checkNameRepetition_3 = this.checkNameRepetition(model.getConf(), ele);
      if (_checkNameRepetition_3) {
        this.error("Name error: Configuration already has a Variable with this name", this.ePackage.getVariable_Name());
        return;
      }
      final Predicate<Resource> _function = (Resource r) -> {
        return this.checkNameRepetition(r, ele);
      };
      boolean _anyMatch = model.getConf().getResources().stream().anyMatch(_function);
      if (_anyMatch) {
        this.error("Name error: Resource already has a Variable with this name", this.ePackage.getVariable_Name());
      }
    }
  }

  @Check
  public void checkProcessVariable_NameConflicts(final ProcessVariable ele) {
    final su.nsk.iae.post.poST.Process process = EcoreUtil2.<su.nsk.iae.post.poST.Process>getContainerOfType(ele, su.nsk.iae.post.poST.Process.class);
    if (((process != null) && this.checkNameRepetition(process, ele))) {
      this.error("Name error: Process already has a Process Variable with this name", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkSymbolicVariable_NeverUse(final SymbolicVariable ele) {
    final Model model = EcoreUtil2.<Model>getContainerOfType(ele, Model.class);
    boolean _hasCrossReferences = this.hasCrossReferences(model, ele);
    boolean _not = (!_hasCrossReferences);
    if (_not) {
      this.warning("Variable is never use", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkSimpleSpecificationInit_Init(final SimpleSpecificationInit ele) {
    Expression _value = ele.getValue();
    boolean _tripleNotEquals = (_value != null);
    if (_tripleNotEquals) {
      boolean _checkContainer = this.<InputVarDeclaration>checkContainer(ele, InputVarDeclaration.class);
      if (_checkContainer) {
        this.error("Initialization error: Input Variable cannot be initialized", 
          this.ePackage.getSimpleSpecificationInit_Value());
        return;
      }
      boolean _checkContainer_1 = this.<OutputVarDeclaration>checkContainer(ele, OutputVarDeclaration.class);
      if (_checkContainer_1) {
        this.error("Initialization error: Output Variable cannot be initialized", 
          this.ePackage.getSimpleSpecificationInit_Value());
        return;
      }
      boolean _checkContainer_2 = this.<InputOutputVarDeclaration>checkContainer(ele, InputOutputVarDeclaration.class);
      if (_checkContainer_2) {
        this.error("Initialization error: InputOutput Variable cannot be initialized", 
          this.ePackage.getSimpleSpecificationInit_Value());
        return;
      }
    }
  }

  @Check
  public void checkArraySpecification_Star(final ArraySpecification ele) {
    ArrayInterval _interval = ele.getInterval();
    boolean _tripleEquals = (_interval == null);
    if (_tripleEquals) {
      if (((!this.<InputVarDeclaration>checkContainer(ele, InputVarDeclaration.class)) && (!this.<InputOutputVarDeclaration>checkContainer(ele, InputOutputVarDeclaration.class)))) {
        this.error("Definition error: Arrays with variable length available only in VAR_INPUT", null);
      }
    }
  }

  /**
   * ======================= START Configuration Checks =======================
   */
  @Check
  public void checkResource_NameConflicts(final Resource ele) {
    final Configuration configuration = EcoreUtil2.<Configuration>getContainerOfType(ele, Configuration.class);
    if (((configuration != null) && this.checkNameRepetition(configuration, ele))) {
      this.error("Name error: Configuration already has a Resource with this name", 
        this.ePackage.getResource_Name());
    }
  }

  @Check
  public void checkTask_NameConflicts(final Task ele) {
    final Resource resource = EcoreUtil2.<Resource>getContainerOfType(ele, Resource.class);
    if (((resource != null) && this.checkNameRepetition(resource, ele))) {
      this.error("Name error: Resource already has a Task with this name", 
        this.ePackage.getTask_Name());
    }
  }

  @Check
  public void checkProgramConfiguration_NameConflicts(final ProgramConfiguration ele) {
    final Resource resource = EcoreUtil2.<Resource>getContainerOfType(ele, Resource.class);
    if (((resource != null) && this.checkNameRepetition(resource, ele))) {
      this.error("Name error: Resource already has a Attached Program with this name", 
        this.ePackage.getProgramConfiguration_Name());
    }
  }

  @Check
  public void checkProgramConfiguration_LowerCase(final ProgramConfiguration ele) {
    boolean _isLowerCase = Character.isLowerCase(ele.getName().charAt(0));
    boolean _not = (!_isLowerCase);
    if (_not) {
      this.warning("Template Process name should start with LowerCase", this.ePackage.getProgramConfiguration_Name());
    }
  }

  @Check
  public void checkProgramConfiguration_NumberOfArgs(final ProgramConfiguration ele) {
    ProgramConfElements _args = ele.getArgs();
    boolean _tripleEquals = (_args == null);
    if (_tripleEquals) {
      return;
    }
    final Program program = ele.getProgram();
    final Function<InputVarDeclaration, EList<VarInitDeclaration>> _function = (InputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_1 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_2 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_3 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count = program.getProgInVars().stream().<EList<VarInitDeclaration>>map(_function).<VarInitDeclaration>flatMap(_function_1).<EList<SymbolicVariable>>map(_function_2).<SymbolicVariable>flatMap(_function_3).count();
    final Function<OutputVarDeclaration, EList<VarInitDeclaration>> _function_4 = (OutputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_5 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_6 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_7 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count_1 = program.getProgOutVars().stream().<EList<VarInitDeclaration>>map(_function_4).<VarInitDeclaration>flatMap(_function_5).<EList<SymbolicVariable>>map(_function_6).<SymbolicVariable>flatMap(_function_7).count();
    long _plus = (_count + _count_1);
    final Function<InputOutputVarDeclaration, EList<VarInitDeclaration>> _function_8 = (InputOutputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_9 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_10 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_11 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count_2 = program.getProgInOutVars().stream().<EList<VarInitDeclaration>>map(_function_8).<VarInitDeclaration>flatMap(_function_9).<EList<SymbolicVariable>>map(_function_10).<SymbolicVariable>flatMap(_function_11).count();
    final long attachVars = (_plus + _count_2);
    final Predicate<ProgramConfElement> _function_12 = (ProgramConfElement x) -> {
      return (x instanceof AttachVariableConfElement);
    };
    final long programVars = ele.getArgs().getElements().stream().filter(_function_12).count();
    if ((attachVars != programVars)) {
      this.error("Attach error: Not all input and output Variables are used", 
        this.ePackage.getProgramConfiguration_Name());
    }
  }

  @Check
  public void checkAttachVariableConfElement_AttachBlockType(final AttachVariableConfElement ele) {
    if ((Objects.equals(ele.getAssig(), AssignmentType.IN) && (!this.<InputVarDeclaration>checkContainer(ele.getProgramVar(), InputVarDeclaration.class)))) {
      this.error("Attach error: Must be a input Variable", 
        this.ePackage.getAttachVariableConfElement_ProgramVar());
      return;
    }
    if ((Objects.equals(ele.getAssig(), AssignmentType.OUT) && (!this.<OutputVarDeclaration>checkContainer(ele.getProgramVar(), OutputVarDeclaration.class)))) {
      this.error("Attach error: Must be a output Variable", 
        this.ePackage.getAttachVariableConfElement_ProgramVar());
    }
  }

  @Check
  public void checkAttachVariableConfElement_AttachVarType(final AttachVariableConfElement ele) {
    if (((ele.getProgramVar() instanceof SymbolicVariable) && (ele.getAttVar() instanceof SymbolicVariable))) {
      String _varType = this.getVarType(ele.getProgramVar());
      String _varType_1 = this.getVarType(ele.getAttVar());
      boolean _notEquals = (!Objects.equals(_varType, _varType_1));
      if (_notEquals) {
        String _varType_2 = this.getVarType(ele.getProgramVar());
        String _plus = ("Attach error: Variable must be " + _varType_2);
        this.error(_plus, 
          this.ePackage.getAttachVariableConfElement_AttVar());
      }
    }
  }

  @Check
  public void checkTemplateProcessConfElement_NameConflicts(final TemplateProcessConfElement ele) {
    final ProgramConfiguration programConf = EcoreUtil2.<ProgramConfiguration>getContainerOfType(ele, ProgramConfiguration.class);
    if ((programConf != null)) {
      boolean _checkNameRepetition = this.checkNameRepetition(programConf, ele);
      if (_checkNameRepetition) {
        this.error("Name error: Program already has a Template Process with this name", 
          this.ePackage.getVariable_Name());
      }
      boolean _checkNameRepetition_1 = this.checkNameRepetition(programConf.getProgram(), ele);
      if (_checkNameRepetition_1) {
        this.error("Name error: Program already has a Process with this name", 
          this.ePackage.getVariable_Name());
      }
    }
  }

  @Check
  public void checkTemplateProcessConfElement_LowerCase(final TemplateProcessConfElement ele) {
    boolean _isLowerCase = Character.isLowerCase(ele.getName().charAt(0));
    boolean _not = (!_isLowerCase);
    if (_not) {
      this.warning("Template Process name should start with LowerCase", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkTemplateProcessConfElement_NumberOfArgs(final TemplateProcessConfElement ele) {
    TemplateProcessElements _args = ele.getArgs();
    boolean _tripleEquals = (_args == null);
    if (_tripleEquals) {
      return;
    }
    final su.nsk.iae.post.poST.Process process = ele.getProcess();
    final Function<InputVarDeclaration, EList<VarInitDeclaration>> _function = (InputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_1 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_2 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_3 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count = process.getProcInVars().stream().<EList<VarInitDeclaration>>map(_function).<VarInitDeclaration>flatMap(_function_1).<EList<SymbolicVariable>>map(_function_2).<SymbolicVariable>flatMap(_function_3).count();
    final Function<OutputVarDeclaration, EList<VarInitDeclaration>> _function_4 = (OutputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_5 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_6 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_7 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count_1 = process.getProcOutVars().stream().<EList<VarInitDeclaration>>map(_function_4).<VarInitDeclaration>flatMap(_function_5).<EList<SymbolicVariable>>map(_function_6).<SymbolicVariable>flatMap(_function_7).count();
    long _plus = (_count + _count_1);
    final Function<InputOutputVarDeclaration, EList<VarInitDeclaration>> _function_8 = (InputOutputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_9 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_10 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_11 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count_2 = process.getProcInOutVars().stream().<EList<VarInitDeclaration>>map(_function_8).<VarInitDeclaration>flatMap(_function_9).<EList<SymbolicVariable>>map(_function_10).<SymbolicVariable>flatMap(_function_11).count();
    long _plus_1 = (_plus + _count_2);
    final Function<ProcessVarDeclaration, EList<ProcessVarInitDeclaration>> _function_12 = (ProcessVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<ProcessVarInitDeclaration>, Stream<ProcessVarInitDeclaration>> _function_13 = (EList<ProcessVarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<ProcessVarInitDeclaration, EList<ProcessVariable>> _function_14 = (ProcessVarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<ProcessVariable>, Stream<ProcessVariable>> _function_15 = (EList<ProcessVariable> x) -> {
      return x.stream();
    };
    long _count_3 = process.getProcProcessVars().stream().<EList<ProcessVarInitDeclaration>>map(_function_12).<ProcessVarInitDeclaration>flatMap(_function_13).<EList<ProcessVariable>>map(_function_14).<ProcessVariable>flatMap(_function_15).count();
    final long attachVars = (_plus_1 + _count_3);
    final long programVars = ele.getArgs().getElements().stream().count();
    if ((attachVars != programVars)) {
      this.error("Process attach error: Not all input output and Process Variables are used", 
        this.ePackage.getTemplateProcessConfElement_Process());
    }
  }

  @Check
  public void checkTemplateProcessAttachVariableConfElement_AttachBlockType(final TemplateProcessAttachVariableConfElement ele) {
    final Variable programVar = ele.getProgramVar();
    final Variable attVar = ele.getAttVar();
    if ((programVar instanceof SymbolicVariable)) {
      if (((attVar != null) && (!(attVar instanceof SymbolicVariable)))) {
        this.error("Attach error: Attach Variable must be a Global Variable or a Constant", 
          this.ePackage.getTemplateProcessAttachVariableConfElement_AttVar());
        return;
      }
    }
    if ((programVar instanceof ProcessVariable)) {
      if ((attVar == null)) {
        this.error("Process attach error: Process attach Variable can\'t be a Constant", 
          this.ePackage.getTemplateProcessAttachVariableConfElement_AttVar());
        return;
      }
      if ((!(attVar instanceof TemplateProcessConfElement))) {
        this.error("Process attach error: Process attach Variable must be a Template Process", 
          this.ePackage.getTemplateProcessAttachVariableConfElement_AttVar());
        return;
      }
    }
  }

  @Check
  public void checkTemplateProcessAttachVariableConfElement_AttachVarType(final TemplateProcessAttachVariableConfElement ele) {
    if (((ele.getProgramVar() instanceof SymbolicVariable) && (ele.getAttVar() instanceof SymbolicVariable))) {
      Variable _programVar = ele.getProgramVar();
      final SymbolicVariable programVar = ((SymbolicVariable) _programVar);
      Variable _attVar = ele.getAttVar();
      final SymbolicVariable attVar = ((SymbolicVariable) _attVar);
      String _varType = this.getVarType(programVar);
      String _varType_1 = this.getVarType(attVar);
      boolean _notEquals = (!Objects.equals(_varType, _varType_1));
      if (_notEquals) {
        String _varType_2 = this.getVarType(programVar);
        String _plus = ("Attach error: Process Variable must be " + _varType_2);
        this.error(_plus, 
          this.ePackage.getTemplateProcessAttachVariableConfElement_AttVar());
      }
    }
    if (((ele.getProgramVar() instanceof ProcessVariable) && (ele.getAttVar() instanceof TemplateProcessConfElement))) {
      Variable _programVar_1 = ele.getProgramVar();
      final ProcessVariable programVar_1 = ((ProcessVariable) _programVar_1);
      Variable _attVar_1 = ele.getAttVar();
      final TemplateProcessConfElement attVar_1 = ((TemplateProcessConfElement) _attVar_1);
      String _name = EcoreUtil2.<ProcessVarInitDeclaration>getContainerOfType(programVar_1, ProcessVarInitDeclaration.class).getProcess().getName();
      String _name_1 = attVar_1.getProcess().getName();
      boolean _notEquals_1 = (!Objects.equals(_name, _name_1));
      if (_notEquals_1) {
        String _name_2 = attVar_1.getProcess().getName();
        String _plus_1 = ("Attach error: Process Variable must be " + _name_2);
        this.error(_plus_1, 
          this.ePackage.getTemplateProcessAttachVariableConfElement_AttVar());
      }
    }
  }

  /**
   * ======================= START poST Checks =======================
   */
  @Check
  public void checkProgram_NameConflicts(final Program ele) {
    final Model model = EcoreUtil2.<Model>getContainerOfType(ele, Model.class);
    boolean _checkNameRepetition = this.checkNameRepetition(model, ele);
    if (_checkNameRepetition) {
      this.error("Name error: Process or FunctionBlock with this name already exists", this.ePackage.getProgram_Name());
    }
  }

  @Check
  public void checkProgram_UpperCase(final Program ele) {
    boolean _isUpperCase = Character.isUpperCase(ele.getName().charAt(0));
    boolean _not = (!_isUpperCase);
    if (_not) {
      this.warning("Program name should start with UpperCase", this.ePackage.getProgram_Name());
    }
  }

  @Check
  public void checkProgram_Empty(final Program ele) {
    boolean _isEmpty = ele.getProcesses().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: Program can\'t be empty", this.ePackage.getProgram_Name());
    }
  }

  @Check
  public void checkFunctionBlock_NameConflicts(final FunctionBlock ele) {
    final Model model = EcoreUtil2.<Model>getContainerOfType(ele, Model.class);
    boolean _checkNameRepetition = this.checkNameRepetition(model, ele);
    if (_checkNameRepetition) {
      this.error("Name error: Process or FunctionBlock with this name already exists", this.ePackage.getFunctionBlock_Name());
    }
  }

  @Check
  public void checkFunctionBlock_UpperCase(final FunctionBlock ele) {
    boolean _isUpperCase = Character.isUpperCase(ele.getName().charAt(0));
    boolean _not = (!_isUpperCase);
    if (_not) {
      this.warning("FunctionBlock name should start with UpperCase", this.ePackage.getFunctionBlock_Name());
    }
  }

  @Check
  public void checkFunctionBlock_Empty(final FunctionBlock ele) {
    boolean _isEmpty = ele.getProcesses().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: FunctionBlock can\'t be empty", this.ePackage.getFunctionBlock_Name());
    }
  }

  @Check
  public void checkProcess_NameConflicts(final su.nsk.iae.post.poST.Process ele) {
    final Program program = EcoreUtil2.<Program>getContainerOfType(ele, Program.class);
    if (((program != null) && this.checkNameRepetition(program, ele))) {
      this.error("Name error: Program already has a Process with this name", this.ePackage.getVariable_Name());
      return;
    }
    final FunctionBlock fb = EcoreUtil2.<FunctionBlock>getContainerOfType(ele, FunctionBlock.class);
    boolean _checkNameRepetition = this.checkNameRepetition(fb, ele);
    if (_checkNameRepetition) {
      this.error("Name error: FunctionBlock already has a Process with this name", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkProcess_UpperCase(final su.nsk.iae.post.poST.Process ele) {
    boolean _isUpperCase = Character.isUpperCase(ele.getName().charAt(0));
    boolean _not = (!_isUpperCase);
    if (_not) {
      this.warning("Process name should start with UpperCase", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkProcess_Empty(final su.nsk.iae.post.poST.Process ele) {
    boolean _isEmpty = ele.getStates().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: Process can\'t be empty", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkProcess_Unreachable(final su.nsk.iae.post.poST.Process ele) {
    boolean _isTemplate = this.isTemplate(ele);
    if (_isTemplate) {
      return;
    }
    final Program program = EcoreUtil2.<Program>getContainerOfType(ele, Program.class);
    if ((program != null)) {
      int _indexOf = program.getProcesses().indexOf(ele);
      boolean _tripleEquals = (_indexOf == 0);
      if (_tripleEquals) {
        return;
      }
      boolean _checkProcessStart = this.<Statement>checkProcessStart(program.getProcesses(), ele);
      boolean _not = (!_checkProcessStart);
      if (_not) {
        this.warning("Process is unreachable", this.ePackage.getVariable_Name());
      }
      return;
    }
    final FunctionBlock fb = EcoreUtil2.<FunctionBlock>getContainerOfType(ele, FunctionBlock.class);
    int _indexOf_1 = fb.getProcesses().indexOf(ele);
    boolean _tripleEquals_1 = (_indexOf_1 == 0);
    if (_tripleEquals_1) {
      return;
    }
    boolean _checkProcessStart_1 = this.<Statement>checkProcessStart(fb.getProcesses(), ele);
    boolean _not_1 = (!_checkProcessStart_1);
    if (_not_1) {
      this.warning("Process is unreachable", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkState_NameConflicts(final su.nsk.iae.post.poST.State ele) {
    final su.nsk.iae.post.poST.Process process = EcoreUtil2.<su.nsk.iae.post.poST.Process>getContainerOfType(ele, su.nsk.iae.post.poST.Process.class);
    boolean _checkNameRepetition = this.checkNameRepetition(process, ele);
    if (_checkNameRepetition) {
      this.error("Name error: Process already has a State with this name", this.ePackage.getState_Name());
    }
  }

  @Check
  public void checkState_Empty(final su.nsk.iae.post.poST.State ele) {
    if ((ele.getStatement().getStatements().isEmpty() && (ele.getTimeout() == null))) {
      this.error("Statement error: State can\'t be empty", this.ePackage.getState_Name());
    }
  }

  @Check
  public void checkState_Unreachable(final su.nsk.iae.post.poST.State ele) {
    final su.nsk.iae.post.poST.Process process = EcoreUtil2.<su.nsk.iae.post.poST.Process>getContainerOfType(ele, su.nsk.iae.post.poST.Process.class);
    final int stateIndex = process.getStates().indexOf(ele);
    if ((((stateIndex == 0) || 
      this.<Statement>checkStateSet(process.getStates(), ele)) || 
      this.<Statement>checkStateSetNext(process.getStates(), (stateIndex - 1)))) {
      return;
    }
    this.warning("State is unreachable", this.ePackage.getState_Name());
  }

  @Check
  public void checkState_Looped(final su.nsk.iae.post.poST.State ele) {
    boolean check = (((this.<SetStateStatement>containsType(ele, SetStateStatement.class) || 
      this.<StartProcessStatement>containsType(ele, StartProcessStatement.class)) || 
      this.<StopProcessStatement>containsType(ele, StopProcessStatement.class)) || 
      this.<ErrorProcessStatement>containsType(ele, ErrorProcessStatement.class));
    TimeoutStatement _timeout = ele.getTimeout();
    boolean _tripleNotEquals = (_timeout != null);
    if (_tripleNotEquals) {
      check = (check || this.<SetStateStatement>containsType(ele.getTimeout(), SetStateStatement.class));
    }
    boolean _isLooped = ele.isLooped();
    if (_isLooped) {
      if (check) {
        this.warning("State mustn\'t be LOOPED", this.ePackage.getState_Name());
      }
    } else {
      if ((!check)) {
        this.warning("State should be LOOPED", this.ePackage.getState_Name());
      }
    }
  }

  @Check
  public void checkSetStateStatement_InvalidArgument(final SetStateStatement ele) {
    boolean _isNext = ele.isNext();
    if (_isNext) {
      return;
    }
    final su.nsk.iae.post.poST.Process process = EcoreUtil2.<su.nsk.iae.post.poST.Process>getContainerOfType(ele, su.nsk.iae.post.poST.Process.class);
    boolean _contains = process.getStates().contains(ele.getState());
    boolean _not = (!_contains);
    if (_not) {
      this.error("Name error: Process does not contain a State with this name", this.ePackage.getSetStateStatement_State());
      return;
    }
  }

  @Check
  public void checkSetStateStatement_Useless(final SetStateStatement ele) {
    final su.nsk.iae.post.poST.State state = EcoreUtil2.<su.nsk.iae.post.poST.State>getContainerOfType(ele, su.nsk.iae.post.poST.State.class);
    su.nsk.iae.post.poST.State _state = ele.getState();
    boolean _tripleEquals = (state == _state);
    if (_tripleEquals) {
      this.warning("Useless statement, use RESET TIMER", this.ePackage.getSetStateStatement_State());
    }
  }

  @Check
  public void checkSetStateStatement_Next(final SetStateStatement ele) {
    boolean _isNext = ele.isNext();
    boolean _not = (!_isNext);
    if (_not) {
      return;
    }
    final su.nsk.iae.post.poST.Process process = EcoreUtil2.<su.nsk.iae.post.poST.Process>getContainerOfType(ele, su.nsk.iae.post.poST.Process.class);
    final su.nsk.iae.post.poST.State state = EcoreUtil2.<su.nsk.iae.post.poST.State>getContainerOfType(ele, su.nsk.iae.post.poST.State.class);
    EList<su.nsk.iae.post.poST.State> _states = process.getStates();
    int _size = process.getStates().size();
    int _minus = (_size - 1);
    su.nsk.iae.post.poST.State _get = _states.get(_minus);
    boolean _tripleEquals = (_get == state);
    if (_tripleEquals) {
      this.error("Invalid statement: No next state in the Process", this.ePackage.getSetStateStatement_Next());
    }
  }

  @Check
  public void checkStartProcessStatement_InvalidArgument(final StartProcessStatement ele) {
    this.checkProcessStatement_InvalidArgument(ele, ele.getProcess());
  }

  @Check
  public void checkStopProcessStatement_InvalidArgument(final StopProcessStatement ele) {
    this.checkProcessStatement_InvalidArgument(ele, ele.getProcess());
  }

  @Check
  public void checkErrorProcessStatement_InvalidArgument(final ErrorProcessStatement ele) {
    this.checkProcessStatement_InvalidArgument(ele, ele.getProcess());
  }

  @Check
  public void checkProcessStatusExpression_InvalidArgument(final ProcessStatusExpression ele) {
    this.checkProcessStatement_InvalidArgument(ele, ele.getProcess());
  }

  @Check
  public void checkTimeoutStatement_Empty(final TimeoutStatement ele) {
    boolean _isEmpty = ele.getStatement().getStatements().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: No reaction on timeout", this.ePackage.getTimeoutStatement_Statement());
    }
  }

  private void checkProcessStatement_InvalidArgument(final EObject context, final Variable ele) {
    if ((ele == null)) {
      return;
    }
    if ((ele instanceof su.nsk.iae.post.poST.Process)) {
      final Program program = EcoreUtil2.<Program>getContainerOfType(ele, Program.class);
      boolean _contains = program.getProcesses().contains(ele);
      boolean _not = (!_contains);
      if (_not) {
        this.error("Name error: Program does not contain a Process with this name", this.ePackage.getProcessStatements_Process());
        return;
      }
    }
  }

  /**
   * ======================= START ST Checks =======================
   */
  @Check
  public void checkAssignmentStatement_ModifyInput(final AssignmentStatement ele) {
    final SymbolicVariable varEle = ele.getVariable();
    boolean _checkContainer = this.<InputVarDeclaration>checkContainer(varEle, InputVarDeclaration.class);
    if (_checkContainer) {
      this.warning("Modification of input Variable", this.ePackage.getAssignmentStatement_Variable());
      return;
    }
  }

  @Check
  public void checkAssignmentStatement_ModifyConst(final AssignmentStatement ele) {
    final SymbolicVariable varEle = ele.getVariable();
    final VarDeclaration varDecl = EcoreUtil2.<VarDeclaration>getContainerOfType(varEle, VarDeclaration.class);
    final GlobalVarDeclaration globDecl = EcoreUtil2.<GlobalVarDeclaration>getContainerOfType(varEle, GlobalVarDeclaration.class);
    final ExternalVarDeclaration extDecl = EcoreUtil2.<ExternalVarDeclaration>getContainerOfType(varEle, ExternalVarDeclaration.class);
    if (((((varDecl != null) && varDecl.isConst()) || ((globDecl != null) && globDecl.isConst())) || ((extDecl != null) && extDecl.isConst()))) {
      this.error("Assignment error: Couldn\'t modify constant Variable", this.ePackage.getAssignmentStatement_Variable());
    }
  }

  @Check
  public void checkIfStatement_Empty(final IfStatement ele) {
    boolean _isEmpty = ele.getMainStatement().getStatements().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: IF can\'t be empty", this.ePackage.getVariable_Name());
    }
    EList<StatementList> _elseIfStatements = ele.getElseIfStatements();
    for (final StatementList e : _elseIfStatements) {
      boolean _isEmpty_1 = e.getStatements().isEmpty();
      if (_isEmpty_1) {
        this.error("Statement error: ELSIF can\'t be empty", this.ePackage.getVariable_Name());
      }
    }
    if (((ele.getElseStatement() != null) && ele.getElseStatement().getStatements().isEmpty())) {
      this.error("Statement error: ELSE can\'t be empty", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkCaseElement_Empty(final CaseElement ele) {
    boolean _isEmpty = ele.getStatement().getStatements().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: CASE can\'t be empty", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkForStatement_Empty(final ForStatement ele) {
    boolean _isEmpty = ele.getStatement().getStatements().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: FOR can\'t be empty", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkWhileStatement_Empty(final WhileStatement ele) {
    boolean _isEmpty = ele.getStatement().getStatements().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: WHILE can\'t be empty", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkRepeatStatement_Empty(final RepeatStatement ele) {
    boolean _isEmpty = ele.getStatement().getStatements().isEmpty();
    if (_isEmpty) {
      this.error("Statement error: REPEAT can\'t be empty", this.ePackage.getVariable_Name());
    }
  }

  @Check
  public void checkFunctionCall_NumberOfArgs(final FunctionCall ele) {
    final su.nsk.iae.post.poST.Function function = ele.getFunction();
    final Function<InputVarDeclaration, EList<VarInitDeclaration>> _function = (InputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_1 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_2 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_3 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count = function.getFunInVars().stream().<EList<VarInitDeclaration>>map(_function).<VarInitDeclaration>flatMap(_function_1).<EList<SymbolicVariable>>map(_function_2).<SymbolicVariable>flatMap(_function_3).count();
    final Function<OutputVarDeclaration, EList<VarInitDeclaration>> _function_4 = (OutputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_5 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_6 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_7 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count_1 = function.getFunOutVars().stream().<EList<VarInitDeclaration>>map(_function_4).<VarInitDeclaration>flatMap(_function_5).<EList<SymbolicVariable>>map(_function_6).<SymbolicVariable>flatMap(_function_7).count();
    long _plus = (_count + _count_1);
    final Function<InputOutputVarDeclaration, EList<VarInitDeclaration>> _function_8 = (InputOutputVarDeclaration x) -> {
      return x.getVars();
    };
    final Function<EList<VarInitDeclaration>, Stream<VarInitDeclaration>> _function_9 = (EList<VarInitDeclaration> x) -> {
      return x.stream();
    };
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function_10 = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_11 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    long _count_2 = function.getFunInOutVars().stream().<EList<VarInitDeclaration>>map(_function_8).<VarInitDeclaration>flatMap(_function_9).<EList<SymbolicVariable>>map(_function_10).<SymbolicVariable>flatMap(_function_11).count();
    final long attachVars = (_plus + _count_2);
    final long programVars = ele.getArgs().getElements().stream().count();
    if ((attachVars != programVars)) {
      if (((!this.<InputVarDeclaration>checkContainer(ele, InputVarDeclaration.class)) && (!this.<InputOutputVarDeclaration>checkContainer(ele, InputOutputVarDeclaration.class)))) {
        this.error("Attach error: Not all input and output Variables are used", this.ePackage.getFunctionCall_Function());
      }
    }
  }

  @Check
  public void checkFBInvocation_InvalidArgument(final FBInvocation ele) {
    final VarInitDeclaration varDecl = EcoreUtil2.<VarInitDeclaration>getContainerOfType(ele.getFb(), VarInitDeclaration.class);
    if (((varDecl == null) || (varDecl.getFb() == null))) {
      this.error("Statement error: Must be FunctionBlock", this.ePackage.getFBInvocation_Fb());
    }
  }

  /**
   * ======================= END ST Checks =======================
   */
  private boolean hasCrossReferences(final EObject context, final EObject target) {
    final HashSet<EObject> targetSet = new HashSet<EObject>();
    targetSet.add(target);
    final ArrayList<EReference> res = new ArrayList<EReference>();
    final EcoreUtil2.ElementReferenceAcceptor _function = (EObject referrer, EObject referenced, EReference reference, int index) -> {
      res.add(reference);
    };
    final EcoreUtil2.ElementReferenceAcceptor acceptor = _function;
    EcoreUtil2.findCrossReferences(context, targetSet, acceptor);
    boolean _isEmpty = res.isEmpty();
    return (!_isEmpty);
  }

  private <T extends EObject> boolean checkContainer(final EObject ele, final Class<T> type) {
    T _containerOfType = EcoreUtil2.<T>getContainerOfType(ele, type);
    return (_containerOfType != null);
  }

  private <T extends Statement> boolean checkProcessStart(final EList<su.nsk.iae.post.poST.Process> processList, final su.nsk.iae.post.poST.Process ele) {
    final Predicate<su.nsk.iae.post.poST.Process> _function = (su.nsk.iae.post.poST.Process x) -> {
      return (x != ele);
    };
    final Predicate<su.nsk.iae.post.poST.Process> _function_1 = (su.nsk.iae.post.poST.Process x) -> {
      final Predicate<StartProcessStatement> _function_2 = (StartProcessStatement xx) -> {
        Variable _process = xx.getProcess();
        return (_process == ele);
      };
      return EcoreUtil2.<StartProcessStatement>getAllContentsOfType(x, StartProcessStatement.class).stream().anyMatch(_function_2);
    };
    return processList.stream().filter(_function).anyMatch(_function_1);
  }

  private <T extends Statement> boolean checkStateSet(final EList<su.nsk.iae.post.poST.State> stateList, final su.nsk.iae.post.poST.State ele) {
    final Predicate<su.nsk.iae.post.poST.State> _function = (su.nsk.iae.post.poST.State x) -> {
      return (x != ele);
    };
    final Predicate<su.nsk.iae.post.poST.State> _function_1 = (su.nsk.iae.post.poST.State x) -> {
      final Predicate<SetStateStatement> _function_2 = (SetStateStatement xx) -> {
        su.nsk.iae.post.poST.State _state = xx.getState();
        return (_state == ele);
      };
      return EcoreUtil2.<SetStateStatement>getAllContentsOfType(x, SetStateStatement.class).stream().anyMatch(_function_2);
    };
    return stateList.stream().filter(_function).anyMatch(_function_1);
  }

  private <T extends Statement> boolean checkStateSetNext(final EList<su.nsk.iae.post.poST.State> stateList, final int index) {
    final Predicate<SetStateStatement> _function = (SetStateStatement xx) -> {
      return xx.isNext();
    };
    return EcoreUtil2.<SetStateStatement>getAllContentsOfType(stateList.get(index), SetStateStatement.class).stream().anyMatch(_function);
  }

  private <T extends Statement> boolean containsType(final EObject ele, final Class<T> type) {
    int _size = EcoreUtil2.<T>getAllContentsOfType(ele, type).size();
    return (_size != 0);
  }

  private boolean isTemplate(final su.nsk.iae.post.poST.Process process) {
    return ((((!process.getProcInVars().isEmpty()) || (!process.getProcOutVars().isEmpty())) || (!process.getProcInOutVars().isEmpty())) || (!process.getProcProcessVars().isEmpty()));
  }

  private String getVarType(final SymbolicVariable ele) {
    final VarInitDeclaration simple = EcoreUtil2.<VarInitDeclaration>getContainerOfType(ele, VarInitDeclaration.class);
    if ((simple != null)) {
      SimpleSpecificationInit _spec = simple.getSpec();
      boolean _tripleNotEquals = (_spec != null);
      if (_tripleNotEquals) {
        return simple.getSpec().getType();
      }
      String _type = simple.getArrSpec().getInit().getType();
      return ("ARRAY." + _type);
    }
    final GlobalVarInitDeclaration global = EcoreUtil2.<GlobalVarInitDeclaration>getContainerOfType(ele, GlobalVarInitDeclaration.class);
    if ((global != null)) {
      return global.getType();
    }
    final ExternalVarInitDeclaration ext = EcoreUtil2.<ExternalVarInitDeclaration>getContainerOfType(ele, ExternalVarInitDeclaration.class);
    return ext.getType();
  }

  private boolean checkNameRepetition(final Model model, final Program ele) {
    return (model.getPrograms().stream().anyMatch(((Predicate<Program>) (Program x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    })) || 
      model.getFbs().stream().anyMatch(((Predicate<FunctionBlock>) (FunctionBlock x) -> {
        return x.getName().equals(ele.getName());
      })));
  }

  private boolean checkNameRepetition(final Model model, final FunctionBlock ele) {
    return (model.getPrograms().stream().anyMatch(((Predicate<Program>) (Program x) -> {
      return x.getName().equals(ele.getName());
    })) || 
      model.getFbs().stream().anyMatch(((Predicate<FunctionBlock>) (FunctionBlock x) -> {
        return ((x != ele) && x.getName().equals(ele.getName()));
      })));
  }

  private boolean checkNameRepetition(final Program program, final su.nsk.iae.post.poST.Process ele) {
    final Predicate<su.nsk.iae.post.poST.Process> _function = (su.nsk.iae.post.poST.Process x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return program.getProcesses().stream().anyMatch(_function);
  }

  private boolean checkNameRepetition(final su.nsk.iae.post.poST.Process process, final su.nsk.iae.post.poST.State ele) {
    final Predicate<su.nsk.iae.post.poST.State> _function = (su.nsk.iae.post.poST.State x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return process.getStates().stream().anyMatch(_function);
  }

  private boolean checkNameRepetition(final FunctionBlock fb, final su.nsk.iae.post.poST.Process ele) {
    final Predicate<su.nsk.iae.post.poST.Process> _function = (su.nsk.iae.post.poST.Process x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return fb.getProcesses().stream().anyMatch(_function);
  }

  private boolean checkNameRepetition(final Configuration configuration, final Resource ele) {
    final Predicate<Resource> _function = (Resource x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return configuration.getResources().stream().anyMatch(_function);
  }

  private boolean checkNameRepetition(final Resource resource, final Task ele) {
    final Predicate<Task> _function = (Task x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return resource.getResStatement().getTasks().stream().anyMatch(_function);
  }

  private boolean checkNameRepetition(final Resource resource, final ProgramConfiguration ele) {
    final Predicate<ProgramConfiguration> _function = (ProgramConfiguration x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return resource.getResStatement().getProgramConfs().stream().anyMatch(_function);
  }

  private boolean checkNameRepetition(final ProgramConfiguration programConf, final TemplateProcessConfElement ele) {
    final Predicate<ProgramConfElement> _function = (ProgramConfElement x) -> {
      return (x instanceof TemplateProcessConfElement);
    };
    final Predicate<ProgramConfElement> _function_1 = (ProgramConfElement x) -> {
      return ((x != ele) && ((TemplateProcessConfElement) x).getName().equals(ele.getName()));
    };
    return programConf.getArgs().getElements().stream().filter(_function).anyMatch(_function_1);
  }

  private boolean checkNameRepetition(final Program program, final TemplateProcessConfElement ele) {
    String _upperCase = ele.getName().substring(0, 1).toUpperCase();
    String _substring = ele.getName().substring(1);
    final String name = (_upperCase + _substring);
    final Predicate<su.nsk.iae.post.poST.Process> _function = (su.nsk.iae.post.poST.Process x) -> {
      return ((!this.isTemplate(x)) && x.getName().equals(name));
    };
    return program.getProcesses().stream().anyMatch(_function);
  }

  private boolean checkNameRepetition(final Model model, final SymbolicVariable ele) {
    return this.checkVarRepetition_GlobalVarDeclaration(model.getGlobVars(), ele);
  }

  private boolean checkNameRepetition(final Configuration configuration, final SymbolicVariable ele) {
    return this.checkVarRepetition_GlobalVarDeclaration(configuration.getConfGlobVars(), ele);
  }

  private boolean checkNameRepetition(final Resource resource, final SymbolicVariable ele) {
    return this.checkVarRepetition_GlobalVarDeclaration(resource.getResGlobVars(), ele);
  }

  private boolean checkNameRepetition(final Program program, final SymbolicVariable ele) {
    return (((((this.checkVarRepetition_InputVarDeclaration(program.getProgInVars(), ele) || 
      this.checkVarRepetition_OutputVarDeclaration(program.getProgOutVars(), ele)) || 
      this.checkVarRepetition_InputOutputVarDeclaration(program.getProgInOutVars(), ele)) || 
      this.checkVarRepetition_VarDeclaration(program.getProgVars(), ele)) || 
      this.checkVarRepetition_TempVarDeclaration(program.getProgTempVars(), ele)) || 
      this.checkVarRepetition_ExternalVarDeclaration(program.getProgExternVars(), ele));
  }

  private boolean checkNameRepetition(final su.nsk.iae.post.poST.Process process, final SymbolicVariable ele) {
    return ((((this.checkVarRepetition_InputVarDeclaration(process.getProcInVars(), ele) || 
      this.checkVarRepetition_OutputVarDeclaration(process.getProcOutVars(), ele)) || 
      this.checkVarRepetition_InputOutputVarDeclaration(process.getProcInOutVars(), ele)) || 
      this.checkVarRepetition_VarDeclaration(process.getProcVars(), ele)) || 
      this.checkVarRepetition_TempVarDeclaration(process.getProcTempVars(), ele));
  }

  private boolean checkNameRepetition(final su.nsk.iae.post.poST.Process process, final ProcessVariable ele) {
    return this.checkVarRepetition_ProcessVarDeclaration(process.getProcProcessVars(), ele);
  }

  private boolean checkVarRepetition_InputVarDeclaration(final EList<InputVarDeclaration> varList, final SymbolicVariable ele) {
    final Predicate<InputVarDeclaration> _function = (InputVarDeclaration x) -> {
      return this.checkVarRepetition_VarInitDeclaration(x.getVars(), ele);
    };
    return varList.stream().anyMatch(_function);
  }

  private boolean checkVarRepetition_OutputVarDeclaration(final EList<OutputVarDeclaration> varList, final SymbolicVariable ele) {
    final Predicate<OutputVarDeclaration> _function = (OutputVarDeclaration x) -> {
      return this.checkVarRepetition_VarInitDeclaration(x.getVars(), ele);
    };
    return varList.stream().anyMatch(_function);
  }

  private boolean checkVarRepetition_InputOutputVarDeclaration(final EList<InputOutputVarDeclaration> varList, final SymbolicVariable ele) {
    final Predicate<InputOutputVarDeclaration> _function = (InputOutputVarDeclaration x) -> {
      return this.checkVarRepetition_VarInitDeclaration(x.getVars(), ele);
    };
    return varList.stream().anyMatch(_function);
  }

  private boolean checkVarRepetition_VarDeclaration(final EList<VarDeclaration> varList, final SymbolicVariable ele) {
    final Predicate<VarDeclaration> _function = (VarDeclaration x) -> {
      return this.checkVarRepetition_VarInitDeclaration(x.getVars(), ele);
    };
    return varList.stream().anyMatch(_function);
  }

  private boolean checkVarRepetition_TempVarDeclaration(final EList<TempVarDeclaration> varList, final SymbolicVariable ele) {
    final Predicate<TempVarDeclaration> _function = (TempVarDeclaration x) -> {
      return this.checkVarRepetition_VarInitDeclaration(x.getVars(), ele);
    };
    return varList.stream().anyMatch(_function);
  }

  private boolean checkVarRepetition_ExternalVarDeclaration(final EList<ExternalVarDeclaration> varList, final SymbolicVariable ele) {
    final Predicate<ExternalVarDeclaration> _function = (ExternalVarDeclaration x) -> {
      return this.checkVarRepetition_ExternalVarInitDeclaration(x.getVars(), ele);
    };
    return varList.stream().anyMatch(_function);
  }

  private boolean checkVarRepetition_GlobalVarDeclaration(final EList<GlobalVarDeclaration> varList, final SymbolicVariable ele) {
    return (varList.stream().anyMatch(((Predicate<GlobalVarDeclaration>) (GlobalVarDeclaration x) -> {
      return this.checkVarRepetition_VarInitDeclaration(x.getVarsSimple(), ele);
    })) || 
      varList.stream().anyMatch(((Predicate<GlobalVarDeclaration>) (GlobalVarDeclaration x) -> {
        return this.checkVarRepetition_GlobalVarInitDeclaration(x.getVarsAs(), ele);
      })));
  }

  private boolean checkVarRepetition_ProcessVarDeclaration(final EList<ProcessVarDeclaration> varList, final ProcessVariable ele) {
    final Predicate<ProcessVarDeclaration> _function = (ProcessVarDeclaration x) -> {
      return this.checkVarRepetition_ProcessVarInitDeclaration(x.getVars(), ele);
    };
    return varList.stream().anyMatch(_function);
  }

  private boolean checkVarRepetition_VarInitDeclaration(final EList<VarInitDeclaration> varList, final SymbolicVariable ele) {
    final Function<VarInitDeclaration, EList<SymbolicVariable>> _function = (VarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_1 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    final Predicate<SymbolicVariable> _function_2 = (SymbolicVariable x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return varList.stream().<EList<SymbolicVariable>>map(_function).<SymbolicVariable>flatMap(_function_1).anyMatch(_function_2);
  }

  private boolean checkVarRepetition_ExternalVarInitDeclaration(final EList<ExternalVarInitDeclaration> varList, final SymbolicVariable ele) {
    final Function<ExternalVarInitDeclaration, EList<SymbolicVariable>> _function = (ExternalVarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_1 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    final Predicate<SymbolicVariable> _function_2 = (SymbolicVariable x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return varList.stream().<EList<SymbolicVariable>>map(_function).<SymbolicVariable>flatMap(_function_1).anyMatch(_function_2);
  }

  private boolean checkVarRepetition_GlobalVarInitDeclaration(final EList<GlobalVarInitDeclaration> varList, final SymbolicVariable ele) {
    final Function<GlobalVarInitDeclaration, EList<SymbolicVariable>> _function = (GlobalVarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<SymbolicVariable>, Stream<SymbolicVariable>> _function_1 = (EList<SymbolicVariable> x) -> {
      return x.stream();
    };
    final Predicate<SymbolicVariable> _function_2 = (SymbolicVariable x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return varList.stream().<EList<SymbolicVariable>>map(_function).<SymbolicVariable>flatMap(_function_1).anyMatch(_function_2);
  }

  private boolean checkVarRepetition_ProcessVarInitDeclaration(final EList<ProcessVarInitDeclaration> varList, final ProcessVariable ele) {
    final Function<ProcessVarInitDeclaration, EList<ProcessVariable>> _function = (ProcessVarInitDeclaration x) -> {
      return x.getVarList().getVars();
    };
    final Function<EList<ProcessVariable>, Stream<ProcessVariable>> _function_1 = (EList<ProcessVariable> x) -> {
      return x.stream();
    };
    final Predicate<ProcessVariable> _function_2 = (ProcessVariable x) -> {
      return ((x != ele) && x.getName().equals(ele.getName()));
    };
    return varList.stream().<EList<ProcessVariable>>map(_function).<ProcessVariable>flatMap(_function_1).anyMatch(_function_2);
  }
}
