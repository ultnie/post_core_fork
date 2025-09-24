package su.nsk.iae.post.generator.py.common.statement

import su.nsk.iae.post.generator.py.common.ProcessGenerator
import su.nsk.iae.post.generator.py.common.ProgramGenerator
import su.nsk.iae.post.generator.py.common.StateGenerator
import su.nsk.iae.post.generator.py.common.StatementListGenerator
import su.nsk.iae.post.poST.IfStatement
import su.nsk.iae.post.poST.Statement

class IfStatementGenerator extends IStatementGenerator {
	
	new(ProgramGenerator program, ProcessGenerator process, StateGenerator state, StatementListGenerator context) {
		super(program, process, state, context)
	}
	
	override checkStatement(Statement statement) {
		return statement instanceof IfStatement
	}
	
	override generateStatement(Statement statement) {
		val s = statement as IfStatement
		return '''
			if «context.generateExpression(s.mainCond)»:
				«context.generateStatementList(s.mainStatement)»
			«IF !s.elseIfCond.empty»
				«FOR i : 0..(s.elseIfCond.size - 1)»
					elif «context.generateExpression(s.elseIfCond.get(i))»:
						«context.generateStatementList(s.elseIfStatements.get(i))»
				«ENDFOR»
			«ENDIF»
			«IF s.elseStatement !== null»
				else:
					«context.generateStatementList(s.elseStatement)»
			«ENDIF»
		'''
	}
	
}