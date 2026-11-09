package su.nsk.iae.post.scoping

import java.util.ArrayList
import java.util.Collections
import java.util.stream.Collectors
import java.util.stream.Stream
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import su.nsk.iae.post.poST.FBInvocation
import su.nsk.iae.post.poST.Function
import su.nsk.iae.post.poST.FunctionBlock
import su.nsk.iae.post.poST.FunctionCall
import su.nsk.iae.post.poST.Library
import su.nsk.iae.post.poST.GlobalVarDeclaration
import su.nsk.iae.post.poST.Model
import su.nsk.iae.post.poST.PoSTPackage
import su.nsk.iae.post.poST.Process
import su.nsk.iae.post.poST.ProcessVariable
import su.nsk.iae.post.poST.Program
import su.nsk.iae.post.poST.ProgramConfiguration
import su.nsk.iae.post.poST.Resource
import su.nsk.iae.post.poST.SymbolicVariable
import su.nsk.iae.post.poST.TemplateProcessConfElement
import su.nsk.iae.post.poST.VarInitDeclaration

import static extension org.eclipse.xtext.EcoreUtil2.*
import jakarta.inject.Inject

class PoSTScopeProvider extends AbstractPoSTScopeProvider {
	
	val ePackage = PoSTPackage.eINSTANCE
	
	@Inject
	IQualifiedNameProvider qualifiedNameProvider

	override getScope(EObject context, EReference reference) {
		switch reference {
			case ePackage.primaryExpression_Variable,
			case ePackage.assignmentStatement_Variable,
			case ePackage.arrayVariable_Variable,
			case ePackage.forStatement_Variable:
				return context.scopeForStatementExpression_Variable
			case ePackage.functionCall_Function:
				return context.scopeForFunctionCall_Function
			case ePackage.varInitDeclaration_Fb:
				return context.scopeForVarInitDeclaration_Fb
			case ePackage.processVarInitDeclaration_Process:
				return context.scopeForProcessVarInitDeclaration_Process
			case ePackage.paramAssignment_Variable:
				return context.scopeForParamAssignment_Variable
			case ePackage.attachVariableConfElement_ProgramVar:
				return context.scopeForAttachVariableConfElement_ProgramVar
			case ePackage.templateProcessAttachVariableConfElement_ProgramVar: 
				return context.scopeForTemplateProcessAttachVariableConfElement_ProgramVar
			case ePackage.templateProcessConfElement_Process: 
				return context.scopeForTemplateProcessConfElement_Process
			case ePackage.processStatements_Process,
			case ePackage.processStatusExpression_Process:
				return context.scopeForProcessStatements_Process(reference)
			case ePackage.setStateStatement_State:
				return context.scopeForSetStateStatement_State
			case ePackage.programConfiguration_Task:
				return context.scopeForProgramConfiguration_Task
		}
		return super.getScope(context, reference)
	}
	
	private def IScope scopeFor(Iterable<? extends EObject> elements) {
		return Scopes.scopeFor(elements, [x | qualifiedNameProvider.getFullyQualifiedName(x)], IScope.NULLSCOPE)
	}

	private def IScope scopeForLocalBothNames(Iterable<? extends EObject> elements) {
		var IScope local = Scopes.scopeFor(elements, [x | qualifiedNameProvider.getFullyQualifiedName(x)], IScope.NULLSCOPE)
		return Scopes.scopeFor(elements, [x | x.simpleQualifiedName], local)
	}

	/**
	 * Creates a local scope containing both the short name (e.g. Square) and
	 * the library-qualified name (e.g. Math.Square), then falls back to the
	 * scope provided by Xtext. The latter is restricted to explicitly imported
	 * resources by ImportUriGlobalScopeProvider.
	 */
	private def IScope scopeForBothNames(Iterable<? extends EObject> elements, EObject context, EReference reference) {
		var IScope parent = super.getScope(context, reference)
		parent = Scopes.scopeFor(elements, [x | qualifiedNameProvider.getFullyQualifiedName(x)], parent)
		return Scopes.scopeFor(elements, [x | x.simpleQualifiedName], parent)
	}

	private def QualifiedName getSimpleQualifiedName(EObject ele) {
		switch ele {
			Function: return QualifiedName.create(ele.name)
			FunctionBlock: return QualifiedName.create(ele.name)
			Process: return QualifiedName.create(ele.name)
			ProcessVariable: return QualifiedName.create(ele.name)
			SymbolicVariable: return QualifiedName.create(ele.name)
			TemplateProcessConfElement: return QualifiedName.create(ele.name)
		}
		return qualifiedNameProvider.getFullyQualifiedName(ele)
	}
	
	private def IScope scopeForStatementExpression_Variable(EObject context) {
		val model = context.getContainerOfType(Model)
		val program = context.getContainerOfType(Program)
		val fb = context.getContainerOfType(FunctionBlock)
		val function = context.getContainerOfType(Function)
		val process = context.getContainerOfType(Process)
		return scopeForLocalBothNames(getAvailableVar(model, program, fb, function, process))
	}
	
	private def IScope scopeForFunctionCall_Function(EObject context) {
		val model = context.getContainerOfType(Model)
		val library = context.getContainerOfType(Library)
		val local = new ArrayList<Function>
		if (model !== null) {
			local.addAll(model.funs)
		}
		if (library !== null) {
			local.addAll(library.funs)
		}
		return scopeForBothNames(local, context, ePackage.functionCall_Function)
	}
	
	private def IScope scopeForVarInitDeclaration_Fb(EObject context) {
		val model = context.getContainerOfType(Model)
		val library = context.getContainerOfType(Library)
		val local = new ArrayList<FunctionBlock>
		if (model !== null) {
			local.addAll(model.fbs)
		}
		if (library !== null) {
			local.addAll(library.fbs)
		}
		return scopeForBothNames(local, context, ePackage.varInitDeclaration_Fb)
	}

	private def IScope scopeForProcessVarInitDeclaration_Process(EObject context) {
		val program = context.getContainerOfType(Program)
		val fb = context.getContainerOfType(FunctionBlock)
		val library = context.getContainerOfType(Library)
		val local = new ArrayList<Process>
		if (program !== null) {
			local.addAll(program.processes)
		}
		if (fb !== null) {
			local.addAll(fb.processes)
		}
		if (library !== null) {
			local.addAll(library.processes)
		}
		return scopeForBothNames(local, context, ePackage.processVarInitDeclaration_Process)
	}
	
	private def IScope scopeForParamAssignment_Variable(EObject context) {
		if (context.getContainerOfType(FunctionCall) !== null) {
			val function = context.getContainerOfType(FunctionCall).function
			return scopeForLocalBothNames(function.functionInOutVar)
		}
		val fbDecl = context.getContainerOfType(FBInvocation).fb
		val fb = fbDecl.getContainerOfType(VarInitDeclaration).fb
		if (fb !== null) {
			return scopeForLocalBothNames(fb.functionBlockInOutVar)
		}
		return scopeForLocalBothNames(Collections.emptyList)
	}
	
	private def IScope scopeForAttachVariableConfElement_ProgramVar(EObject context) {
		val programConf = context.getContainerOfType(ProgramConfiguration)
		return scopeForLocalBothNames(programConf.program.programInOutVar)
	}
	
	private def IScope scopeForTemplateProcessAttachVariableConfElement_ProgramVar(EObject context) {
		val processConf = context.getContainerOfType(TemplateProcessConfElement)
		return scopeForLocalBothNames(processConf.process.processTemplateVar)
	}
	
	private def IScope scopeForTemplateProcessConfElement_Process(EObject context) {
		val programConf = context.getContainerOfType(ProgramConfiguration)
		val library = context.getContainerOfType(Library)
		val local = new ArrayList<Process>
		if (programConf !== null && programConf.program !== null) {
			local.addAll(programConf.program.processes)
		}
		if (library !== null) {
			local.addAll(library.processes)
		}
		return scopeForBothNames(local, context, ePackage.templateProcessConfElement_Process)
	}
	
	private def IScope scopeForProcessStatements_Process(EObject context, EReference reference) {
		val process = context.getContainerOfType(Process)
		if (process === null) {
			return super.getScope(context, reference)
		}
		val program = process.getContainerOfType(Program)
		val fb = process.getContainerOfType(FunctionBlock)
		val library = process.getContainerOfType(Library)
		val res = new ArrayList<EObject>
		res.addAll(process.processProcessVar)
		if (program !== null) {
			res.addAll(program.processes)
		}
		if (fb !== null) {
			res.addAll(fb.processes)
		}
		if (library !== null) {
			res.addAll(library.processes)
		}
		return scopeForBothNames(res, context, reference)
	}
	
	private def IScope scopeForSetStateStatement_State(EObject context) {
		val process = context.getContainerOfType(Process)
		return scopeForLocalBothNames(process.states)
	}
	
	private def IScope scopeForProgramConfiguration_Task(EObject context) {
		val res = context.getContainerOfType(Resource)
		return scopeForLocalBothNames(res.resStatement.tasks)
	}
	
	private def getAvailableVar(Model model, Program program, FunctionBlock fb, Function function, Process process) {
		var Stream<SymbolicVariable> res = Stream.of()
		if (function !== null) {
			res = Stream.concat(res, Stream.concat(function.functionInOutVar.stream, function.functionVar.stream))
		}
		if (fb !== null) {
			res = Stream.concat(res, Stream.concat(fb.functionBlockInOutVar.stream, fb.functionBlockVar.stream))
		}
		if (process !== null) {
			res = Stream.concat(
				res,
				Stream.concat(
					process.processInOutVar.stream,
					process.processVar.stream
				)
			)
		}
		if (program !== null) {
			res = Stream.concat(
				res,
				Stream.concat(
					program.programInOutVar.stream,
					program.programVar.stream
				)
			)
		}
		res = Stream.concat(res, model.globVars.globalVars.stream)
		val conf = model.conf
		if (conf !== null) {
			val resources = model.conf.resources
			res = Stream.concat(
				res,
				Stream.concat(
					conf.confGlobVars.globalVars.stream,
					resources.stream.map([x | x.resGlobVars.globalVars]).flatMap([x | x.stream])
				)
			)
		}
		return res.collect(Collectors.toList)
	}
	
	private static def getGlobalVars(EList<GlobalVarDeclaration> list) {
		return Stream.concat(
			list.stream.map([x | x.varsSimple]).flatMap([x | x.stream]).map([x | x.varList.vars]),
			list.stream.map([x | x.varsAs]).flatMap([x | x.stream]).map([x | x.varList.vars])
		).flatMap([x | x.stream]).collect(Collectors.toList)
	}

	private static def getProgramInOutVar(Program program) {
		return Stream.concat(
			program.progInVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
			Stream.concat(
				program.progOutVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
				program.progInOutVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars])
			)
		).flatMap([x | x.stream]).collect(Collectors.toList)
	}
	
	private static def getFunctionBlockInOutVar(FunctionBlock fb) {
		return Stream.concat(
			fb.fbInVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
			Stream.concat(
				fb.fbOutVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
				fb.fbInOutVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars])
			)
		).flatMap([x | x.stream]).collect(Collectors.toList)
	}
	
	private static def getFunctionInOutVar(Function function) {
		return Stream.concat(
			function.funInVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
			Stream.concat(
				function.funOutVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
				function.funInOutVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars])
			)
		).flatMap([x | x.stream]).collect(Collectors.toList)
	}
	
	private static def getProgramVar(Program program) {
		return Stream.concat(
			program.progVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
			Stream.concat(
				program.progTempVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
				program.progExternVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars])
			)
		).flatMap([x | x.stream]).collect(Collectors.toList)
	}

	private static def getFunctionVar(Function function) {
		return Stream.concat(
			function.funVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]).flatMap([x | x.stream]),
			function.functionInOutVar.stream
		).collect(Collectors.toList)
	}

	private static def getFunctionBlockVar(FunctionBlock fb) {
		return Stream.concat(
			Stream.concat(
				fb.fbVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]).flatMap([x | x.stream]),
				fb.fbTempVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]).flatMap([x | x.stream])
			),
			Stream.concat(
				fb.fbExternVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]).flatMap([x | x.stream]),
				fb.functionBlockInOutVar.stream
			)
		).collect(Collectors.toList)
	}

	private static def getProcessTemplateVar(Process process) {
		return Stream.concat(
			process.processInOutVar.stream,
			process.processProcessVar.stream
		).collect(Collectors.toList)
	}
	
	private static def getProcessProcessVar(Process process) {
		return process.procProcessVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]).flatMap([x | x.stream]).collect(Collectors.toList)
	}
	
	private static def getProcessInOutVar(Process process) {
		return Stream.concat(
			process.procInVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
			Stream.concat(
				process.procOutVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
				process.procInOutVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars])
			)
		).flatMap([x | x.stream]).collect(Collectors.toList)
	}
	
	private static def getProcessVar(Process process) {
		return Stream.concat(
			process.procVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars]),
			process.procTempVars.stream.map([x | x.vars]).flatMap([x | x.stream]).map([x | x.varList.vars])
		).flatMap([x | x.stream]).collect(Collectors.toList)
	}
	
}
