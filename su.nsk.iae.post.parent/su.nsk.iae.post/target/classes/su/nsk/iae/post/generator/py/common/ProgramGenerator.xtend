package su.nsk.iae.post.generator.py.common

import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.generator.IFileSystemAccess2
import su.nsk.iae.post.generator.py.common.vars.ExternalVarHelper
import su.nsk.iae.post.generator.py.common.vars.InputOutputVarHelper
import su.nsk.iae.post.generator.py.common.vars.InputVarHelper
import su.nsk.iae.post.generator.py.common.vars.OutputVarHelper
import su.nsk.iae.post.generator.py.common.vars.SimpleVarHelper
import su.nsk.iae.post.generator.py.common.vars.TempVarHelper
import su.nsk.iae.post.generator.py.common.vars.VarHelper
import su.nsk.iae.post.poST.Process

import static extension su.nsk.iae.post.generator.py.common.util.GeneratorUtil.*
import su.nsk.iae.post.generator.py.configuration.ConfigurationGenerator
import org.eclipse.emf.common.util.URI
import java.io.FileInputStream
import org.apache.commons.io.IOUtils
import java.util.Arrays
import java.util.ArrayList

class ProgramGenerator {
	
	protected EObject object
	protected String programName
	protected String type
	boolean templateProcess
	
	protected VarHelper inVarList = new InputVarHelper
	protected VarHelper outVarList = new OutputVarHelper
	protected VarHelper inOutVarList = new InputOutputVarHelper
	protected VarHelper externalVarList = new ExternalVarHelper
	protected VarHelper varList = new SimpleVarHelper
	protected VarHelper tempVarList = new TempVarHelper
	
	protected List<ProcessGenerator> processList = new LinkedList
	
	protected List<String> confInVars
	protected List<String> confOutVars
	
	new (boolean templateProcess) {
		this.templateProcess = templateProcess
	}
	
	def String generateProgram(ConfigurationGenerator configuration, URI source) {
		prepareConfVars(source.devicePath())
		prepareProgramVars
		return generateBody(configuration)
	}
	
	def prepareConfVars(String path) {
		var i = 0 as int
		var fileString = ""
		var programString = ""
		var item = ""
		var FileInputStream inputStream
		var List<String> items
		try {
			confInVars = new ArrayList<String>()
			confOutVars = new ArrayList<String>()
			inputStream = new FileInputStream(path)
			fileString = IOUtils.toString(inputStream)
			programString = fileString.substring(fileString.indexOf("PROGRAM"), fileString.indexOf("END_CONFIGURATION"))
			programString = programString.substring(programString.indexOf("PROCESS"))
			items = Arrays.asList(programString.split(','))
			for (i = 0; i<items.size(); i++) {
				if (items.get(i).contains(":=")) {
					item = items.get(i).substring(items.get(i).indexOf(":=")).replace(")", "").replace(" ", "").replaceAll("\\s.*", "")
					item = item.substring(2)
					if (item != "TRUE" && item != "FALSE")
						confInVars.add(item)
				}
				else if (items.get(i).contains("=>")) {
					item = items.get(i).substring(items.get(i).indexOf("=>")).replace(")", "").replace(" ", "").replaceAll("\\s.*", "")
					item = item.substring(2)
					if (item != "TRUE" && item != "FALSE")
						confOutVars.add(item)
				}
			}
		}
		catch (Exception e) {}
		finally {
			inputStream.close()
		}
	}
	
	def String generateBody(ConfigurationGenerator configuration)
	'''	
	«externalVarList.generateVars»
	«varList.generateVars»
	«tempVarList.generateVars»
	taskTime = 0
	
	«IF templateProcess» 
		«configuration.generateConfVars»
		«configuration.generateResources»
		«FOR str: confInVars»
			if '«str»' in globVars:
				if not isinstance(«str», list):
					inVars['«str»'] = «str»
		«ENDFOR»
		«FOR str: confOutVars»
			if '«str»' in globVars:
				if not isinstance(«str», list):
					outVars['«str»'] = «str»
		«ENDFOR»
	«ELSE»
		«inVarList.generateVars»
		«outVarList.generateVars»
		«inOutVarList.generateVars»
	«ENDIF»
	
	processesDict = {}
	
	
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

	«/*Vars = {«inVarList.generateVarList»«outVarList.generateVarList»«inOutVarList.generateVarList»«externalVarList.generateVarList»«varList.generateVarList»«tempVarList.generateVarList»}*/»
	class Program:
		global taskTime
		processes = []
		
		«FOR p : processList»
			class «p.getName»:
				«p.generateBody(inVarList, outVarList, inOutVarList, externalVarList, varList, tempVarList, configuration)»
			«p.getName.toLowerCase» = «p.getName()»()
			processes.append(«p.getName.toLowerCase»)
			processesDict['«p.getName()»'] = «p.getName.toLowerCase»
			pStates['«p.getName()»_state'] = «p.getName.toLowerCase».States(«p.getName.toLowerCase».curState).name
			pTimes['«p.getName()»_time'] = «p.getName.toLowerCase».startTime
			
		«ENDFOR»
		start(processes[0])
		
		def run_iter(self):
			global _global_time
			Vars['_global_time'] = _global_time
			for process in self.processes:
				process.run()
	
	
	def getVariable(name):
		return globals()[name]
	'''
	
	def String getName() {
		return programName
	}
	
	def EObject getEObject() {
		return object
	}
	
	protected def parseProcesses(EList<Process> processes) {
		processes.stream.forEach([p |
			val process = new ProcessGenerator(this, p)
			if (!process.isTemplate()) {
				processList.add(process)
			}
		])
	}
	
	def prepareProgramVars() {
		processList.stream.forEach([x | x.prepareProcessVars])
		addVar(generateGlobalTime, "TIME")
		processList.stream.forEach([x | x.prepareTimeVars])
	}
	
	def void addProcess(Process process) {
		processList.add(new ProcessGenerator(this, process))
	}
	
	def void addProcess(Process process, boolean active) {
		processList.add(new ProcessGenerator(this, process, active))
	}
	
	def void addVar(EObject varDecl) {
		varList.add(varDecl)
	}
	
	def void addVar(EObject varDecl, String pref) {
		varList.add(varDecl, pref)
	}
	
	def void addVar(String name, String type) {
		varList.add(name, type)
	}
	
	def void addVar(String name, String type, String value) {
		varList.add(name, type, value)
	}
	
	def void addVar(String name, String type, String value, boolean isConstant) {
		varList.add(name, type, value, isConstant)
	}
	
	def void addTempVar(EObject varDecl) {
		tempVarList.add(varDecl)
	}
	
	def void addTempVar(EObject varDecl, String pref) {
		tempVarList.add(varDecl, pref)
	}
	
	def void addTempVar(String name, String type, String value) {
		tempVarList.add(name, type, value)
	}
	
	def boolean isFirstProcess(ProcessGenerator process) {
		return processList.get(0) == process
	}
	
	def void addInVar(EObject varDecl) {
		inVarList.add(varDecl)
	}
	
	def void addInVar(EObject varDecl, String pref) {
		inVarList.add(varDecl, pref)
	}
	
	def void addOutVar(EObject varDecl) {
		outVarList.add(varDecl)
	}
	
	def void addOutVar(EObject varDecl, String pref) {
		outVarList.add(varDecl, pref)
	}
	
	def void addInOutVar(EObject varDecl) {
		inOutVarList.add(varDecl)
	}
	
	def void addInOutVar(EObject varDecl, String pref) {
		inOutVarList.add(varDecl, pref)
	}
	
	def String generateProcessEnum(String processName) {
		return processList.findFirst[name == processName].generateEnumName
	}
	
	def String generateProcessStart(String processName) {
		return processList.findFirst[name == processName].generateStart
	}
	
	def generateProcessStop(String processName) {
		return processList.findFirst[name == processName].generateStop
	}
	
	def generateProcessError(String processName) {
		return processList.findFirst[name == processName].generateError
	}
	
}
