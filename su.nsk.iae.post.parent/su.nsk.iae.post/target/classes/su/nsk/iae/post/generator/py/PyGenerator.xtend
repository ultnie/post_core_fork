package su.nsk.iae.post.generator.py

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import su.nsk.iae.post.IDsmExecutor
import su.nsk.iae.post.generator.IPoSTGenerator
import su.nsk.iae.post.generator.py.common.ProgramGenerator
import su.nsk.iae.post.generator.py.common.vars.GlobalVarHelper
import su.nsk.iae.post.generator.py.common.vars.VarHelper
import su.nsk.iae.post.generator.py.configuration.ConfigurationGenerator
import su.nsk.iae.post.poST.ArrayVariable
import su.nsk.iae.post.poST.AssignmentStatement
import su.nsk.iae.post.poST.AttachVariableConfElement
import su.nsk.iae.post.poST.Constant
import su.nsk.iae.post.poST.ForStatement
import su.nsk.iae.post.poST.Model
import su.nsk.iae.post.poST.PrimaryExpression
import su.nsk.iae.post.poST.ProcessStatements
import su.nsk.iae.post.poST.ProcessStatusExpression
import su.nsk.iae.post.poST.SymbolicVariable
import su.nsk.iae.post.poST.TemplateProcessAttachVariableConfElement
import su.nsk.iae.post.poST.TemplateProcessConfElement
import su.nsk.iae.post.poST.TimeoutStatement
import su.nsk.iae.post.poST.Variable

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import static extension org.eclipse.xtext.EcoreUtil2.*
import static extension su.nsk.iae.post.generator.py.common.util.GeneratorUtil.*
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import su.nsk.iae.post.PoSTStandaloneSetup
import org.eclipse.emf.common.util.URI
import su.nsk.iae.post.generator.py.imports.ImportsGenerator

class PyGenerator implements IDsmExecutor, IPoSTGenerator {

	ConfigurationGenerator configuration = null
	ImportsGenerator importsGenerator = null
	VarHelper globVarList = new GlobalVarHelper
	List<ProgramGenerator> programs = new LinkedList

	override execute(String root, String fileName, Resource resource) {
		try {
			val fsa = PoSTStandaloneSetup.getInjector().getInstance(JavaIoFileSystemAccess);
			val generatePath = root + File.separator + "" + File.separator + fileName;
			fsa.setOutputPath(generatePath);

			model = resource.allContents.toIterable.filter(Model).get(0)
			beforeGenerate(resource, fsa, null);
			doGenerate(resource, fsa, null);
			afterGenerate(resource, fsa, null);
			return "Files generated in " + generatePath;
		} catch (Throwable th) {
			//Do nothing
		}
		return "Failure";
	}

	override setModel(Model model) {
		globVarList.clear()
		programs.clear()
		model.globVars.stream.forEach([v|globVarList.add(v)])
		if (model.imports !== null) {
			importsGenerator = new ImportsGenerator(model.imports);
		}
		if (model.conf !== null) {
			configuration = new ConfigurationGenerator(model.conf)
			configuration.resources.stream.map([res|res.resStatement.programConfs]).flatMap([res|res.stream]).
				forEach([ programConf |
					val program = programConf.program.copy()
					program.name = programConf.name.capitalizeFirst
					programs.add(new ProgramPOUGenerator(program, true))
				])
		} else {
			model.programs.stream.forEach([p|programs.add(new ProgramPOUGenerator(p, false))])
			model.fbs.stream.forEach([fb|programs.add(new FunctionBlockPOUGenerator(fb, false))])
		}
	}

	override beforeGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
		preparePrograms()
	}

	override doGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
		generateSingleFile(fsa, "", input.getURI)
	}

	override afterGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {}

	private def void generateSingleFile(IFileSystemAccess2 fsa, String path, URI source) {
		fsa.generateFile('''«path»''', generateSingleFileBody(source))
	}

//	private def void generateMultipleFiles(IFileSystemAccess2 fsa, String path) {
//		fsa.generateFile('''«path»GVL.''', ProgramGenerator.generateVar(globVarList))
//		for (c : programs) {
//			c.generate(fsa, path, configuration === null)
//		}
//	}
	private def String generateSingleFileBody(URI source) '''
		«IF importsGenerator !== null»
			«importsGenerator.generateImports»
		«ENDIF»
		import time
		import enum
		from MuteTypes import MuteBool, MuteNum, MuteStr, MuteBytes, get_value
		from datetime import timedelta
		
		
		inVars = {}
		outVars = {}
		inOutVars = {}
		Vars = {}
		exVars = {}
		globVars = {}
		tempVars = {}
		pStates = {}
		pTimes = {}
		
		
		«globVarList.generateVars»
		«/*
		«IF configuration !== null»
			«configuration.generateConfiguration»
		«ENDIF»
		*/»
		
		def setVariable (name, value):
			try:
				globals()[name].__set__(value)
			except:
				globals()[name] = value
			if name in outVars:
				try:
					outVars[name].__set__(value)
				except:
					outVars[name] = value
			if name in inOutVars:
				try:
					inOutVars[name].__set__(value)
				except:
					inOutVars[name] = value
			if name in exVars:
				try:
					exVars[name].__set__(value)
				except:
					exVars[name] = value
			if name in globVars:
				try:
					globVars[name].__set__(value)
				except:
					globVars[name] = value
			if name in Vars:
				try:
					Vars[name].__set__(value)
				except:
					Vars[name] = value
		
		«IF programs.isEmpty()»
			«generateEmptyTemplate()»
		«ENDIF»
		«FOR c : programs»
			«c.generateProgram(configuration, source)»
		«ENDFOR»
	'''
	
	private def String generateEmptyTemplate() 
	'''
	_global_time = 0
	Vars['_global_time'] = _global_time
		
	taskTime = 0
	
	emptyOut = MuteStr("Empty program")
	outVars['output'] = emptyOut
	
	def setVariable (name, value):
		try:
			globals()[name].__set__(value)
		except:
			globals()[name] = value
		if name in outVars:
			try:
				outVars[name].__set__(value)
			except:
				outVars[name] = value
		if name in inOutVars:
			try:
				inOutVars[name].__set__(value)
			except:
				inOutVars[name] = value
		if name in exVars:
			try:
				exVars[name].__set__(value)
			except:
				exVars[name] = value
		if name in globVars:
			try:
				globVars[name].__set__(value)
			except:
				globVars[name] = value
		if name in Vars:
			try:
				Vars[name].__set__(value)
			except:
				Vars[name] = value
	
	def start(p):
		global Vars
		p.curState = p.States(1)
		pStates[f'{p.name()}_state'] = p.States(p.curState).name
		setVariable(f'_g_p_{p.name()}_time', _global_time)
	
	def stop(p):
		global Vars
		p.curState = p.States(254)
		pStates[f'{p.name()}_state'] = p.States(p.curState).name
		setVariable(f'_g_p_{p.name()}_time', _global_time)
	
	
	def error(p):
		global Vars
		p.curState = p.States(255)
		pStates[f'{p.name()}_state'] = p.States(p.curState).name
		setVariable(f'_g_p_{p.name()}_time', _global_time)
	
	
	def set_state(state, p):
		global Vars
		p.curState = state
		pStates[f'{p.name()}_state'] = p.States(p.curState).name
		setVariable(f'_g_p_{p.name()}_time', _global_time)
	
	class Program:
		global taskTime
		processes = []
		
		
		def run_iter(self):
			global _global_time
			Vars['_global_time'] = _global_time


	def getVariable(name):
		return globals()[name]
	'''

	private def void preparePrograms() {
		if (configuration === null) {
			return
		}
		configuration.resources.stream.map([res|res.resStatement.programConfs]).flatMap([res|res.stream]).
			forEach([ programConf |
				if (programConf.args !== null) {
					val programConfName = programConf.name.capitalizeFirst
					val programGen = programs.stream.filter([x|x.name == programConfName]).findFirst.get
					programConf.args.elements.stream.forEach([ confElement |
						if (confElement instanceof TemplateProcessConfElement) {
							val process = confElement.process.copy
							process.name = confElement.name.capitalizeFirst
							confElement.args.elements.stream.forEach([e|e.changeAllVars(process)])
							programGen.addProcess(process, confElement.active)
						} else if (confElement instanceof AttachVariableConfElement) {
							confElement.changeAllVars(programGen.EObject)
						}
					])
				}
			])
	}

	def void changeAllVars(AttachVariableConfElement element, EObject root) {
		changeAllVars(element.programVar, element.attVar, element.const, root)
	}

	def void changeAllVars(TemplateProcessAttachVariableConfElement element, EObject root) {
		changeAllVars(element.programVar, element.attVar, element.const, root)
	}

	def void changeAllVars(Variable programVar, Variable attVar, Constant const, EObject root) {
		root.getAllContentsOfType(PrimaryExpression).stream.filter([ v |
			(v.variable !== null) && (v.variable.name == programVar.name)
		]).forEach([ v |
			if (attVar !== null) {
				v.variable = attVar as SymbolicVariable
			} else {
				v.variable = null
				v.const = const.copy
			}
		])
		root.getAllContentsOfType(AssignmentStatement).stream.filter([ v |
			(v.variable !== null) && (v.variable.name == programVar.name)
		]).forEach([ v |
			v.variable = attVar as SymbolicVariable
		])
		root.getAllContentsOfType(ForStatement).stream.filter([v|v.variable.name == programVar.name]).forEach([ v |
			v.variable = attVar as SymbolicVariable
		])
		root.getAllContentsOfType(ArrayVariable).stream.filter([v|v.variable.name == programVar.name]).forEach([ v |
			v.variable = attVar as SymbolicVariable
		])
		root.getAllContentsOfType(TimeoutStatement).stream.filter([ v |
			(v.variable !== null) && (v.variable.name == programVar.name)
		]).forEach([ v |
			v.variable = attVar as SymbolicVariable
		])
		root.getAllContentsOfType(ProcessStatements).stream.filter([ v |
			(v.process !== null) && (v.process.name == programVar.name)
		]).forEach([ v |
			v.process.name = attVar.name.capitalizeFirst
		])
		root.getAllContentsOfType(ProcessStatusExpression).stream.filter([ v |
			(v.process !== null) && (v.process.name == programVar.name)
		]).forEach([ v |
			v.process.name = attVar.name.capitalizeFirst
		])
	}

	private def String capitalizeFirst(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1)
	}
}
