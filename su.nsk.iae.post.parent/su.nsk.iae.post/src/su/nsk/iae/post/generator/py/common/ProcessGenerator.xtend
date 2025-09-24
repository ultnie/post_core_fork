package su.nsk.iae.post.generator.py.common

import java.util.LinkedList
import java.util.List
import su.nsk.iae.post.generator.py.common.vars.InputOutputVarHelper
import su.nsk.iae.post.generator.py.common.vars.InputVarHelper
import su.nsk.iae.post.generator.py.common.vars.OutputVarHelper
import su.nsk.iae.post.generator.py.common.vars.SimpleVarHelper
import su.nsk.iae.post.generator.py.common.vars.TempVarHelper
import su.nsk.iae.post.generator.py.common.vars.VarHelper
import su.nsk.iae.post.poST.Process

import static extension su.nsk.iae.post.generator.py.common.util.GeneratorUtil.*
import su.nsk.iae.post.generator.py.configuration.ConfigurationGenerator

class ProcessGenerator {
	
	ProgramGenerator program
	Process process
	boolean active
	
	VarHelper inVarList = new InputVarHelper
	VarHelper outVarList = new OutputVarHelper
	VarHelper inOutVarList = new InputOutputVarHelper
	VarHelper varList = new SimpleVarHelper
	VarHelper tempVarList = new TempVarHelper
	
	List<StateGenerator> stateList = new LinkedList
	
	new(ProgramGenerator program, Process process) {
		this(program, process, false)
	}
	
	new(ProgramGenerator program, Process process, boolean active) {
		this.program = program
		this.process = process
		this.active = active
		process.states.stream.forEach([s | stateList.add(new StateGenerator(program, this, s))])
	}
	
	def String generateBody(VarHelper pInVars, VarHelper pOutVars, VarHelper pInOutVars, VarHelper pExVars, VarHelper pVars, VarHelper pTVars, ConfigurationGenerator conf) '''
		startTime = ""
		global pStates
		
		class States(enum.Enum):
			«FOR s : stateList»
				«s.getName» = enum.auto()
			«ENDFOR»
			Stop = 254
			Error = 255
			
		def set_next(self):
			global Vars
			self.curState = self.States(self.curState.value + 1)
			pStates[f'{self.name()}_state'] = self.States(self.curState).name
					
		def name(self):
			return self.__class__.__name__
			
		curState = «IF active»States(1)«ELSE»States.Stop«ENDIF»
		
		def run(self):
			global inVars
			global outVars
			global inOutVars
			global Vars
			global exVars
			global globVars
			global pStates
			global processesDict
			global _global_time
			match self.curState:
				«FOR s : stateList»
					case self.States.«s.getName»:
						self.startTime = «generateGlobalTime»
						pTimes['«name»_time'] = self.startTime
						«s.generateBody»
				«ENDFOR»
				case _:
					pass
		
	'''
	
	def String getName() {
		return process.name
	}
	
	def boolean containsVar(String name) {
		return varList.contains(name) || tempVarList.contains(name) || 
			   inVarList.contains(name) || outVarList.contains(name) || inOutVarList.contains(name)
	}
	
	def String generateSetState(String stateName) '''
		«IF stateList.findFirst[name == stateName].hasTimeout»setVariable('«generateTimeoutName»', «generateGlobalTime»)«ENDIF»
		set_state(self.States.«stateName», self)
	'''
	
	def String generateNextState(StateGenerator state) {
		if (stateList.indexOf(state) + 1 < stateList.size) {
			val s = stateList.get(stateList.indexOf(state) + 1)
			return '''
				self.set_next()
			'''
		}
		val s = stateList.get(0)
		return '''
			start(self)
		'''
	}
	
	def String generateStart() '''
		«FOR v : varList.list»
			«IF v.value !== null && ! v.isConstant»
				setVariable('«generateVarName(v.name)»', «v.value»)
			«ENDIF»
		«ENDFOR»
		«IF stateList.get(0).hasTimeout»setVariable('«generateTimeoutName»', «generateGlobalTime»)«ENDIF»
		start(processesDict['«name»'])
	'''
	
	def generateStop() '''
		stop(processesDict['«name»'])
	'''
	
	def generateError() '''
		error(processesDict['«name»'])
	'''
	
	def boolean isTemplate() {
		return (!process.procInVars.empty) || (!process.procOutVars.empty) || (!process.procInOutVars.empty)
	}
	
	def void prepareStateVars(boolean templateProcess) {
		for (var i = 0; i < stateList.size; i++) {
			program.addVar(generateEnumStateConstant(stateList.get(i).name), "INT", i.toString, true)
		}
		if ((templateProcess && active) || (!templateProcess && program.isFirstProcess(this))) {
			program.addVar(generateEnumName, "INT", generateEnumStateConstant(stateList.get(0).name))
		} else {
			program.addVar(generateEnumName, "INT", generateStopConstant)
		}
	}
	
	def void prepareTimeVars() {
		if (hasTimeouts) {
			program.addVar(generateTimeoutName, "TIME")
		}
	}
	
	def void prepareProcessVars() {
		prepareVars()
		prepareTempVars()
		if (isTemplate()) {
			prepareInVars()
			prepareOutVars()
			prepareInOutVars()
		}
	}
	
	private def void prepareVars() {
		process.procVars.stream.forEach([varDecl | 
			varList.add(varDecl)
			program.addVar(varDecl, generateVarName(""))
		])
	}
	
	private def void prepareTempVars() {
		process.procTempVars.stream.forEach([varDecl | 
			tempVarList.add(varDecl)
			program.addTempVar(varDecl, generateVarName(""))
		])
	}
	
	private def void prepareInVars() {
		process.procInVars.stream.forEach([varDecl | 
			inVarList.add(varDecl)
			program.addInVar(varDecl, generateVarName(""))
		])
	}
	
	private def void prepareOutVars() {
		process.procOutVars.stream.forEach([varDecl | 
			outVarList.add(varDecl)
			program.addOutVar(varDecl, generateVarName(""))
		])
	}
	
	private def void prepareInOutVars() {
		process.procInOutVars.stream.forEach([varDecl | 
			inOutVarList.add(varDecl)
			program.addInOutVar(varDecl, generateVarName(""))
		])
	}
	
	private def boolean hasTimeouts() {
		return stateList.stream.anyMatch([x | x.hasTimeout])
	}
}