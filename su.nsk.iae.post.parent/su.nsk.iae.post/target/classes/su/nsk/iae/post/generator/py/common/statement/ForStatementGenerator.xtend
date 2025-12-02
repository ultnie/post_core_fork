package su.nsk.iae.post.generator.py.common.statement

import su.nsk.iae.post.generator.py.common.ProcessGenerator
import su.nsk.iae.post.generator.py.common.ProgramGenerator
import su.nsk.iae.post.generator.py.common.StateGenerator
import su.nsk.iae.post.generator.py.common.StatementListGenerator
import su.nsk.iae.post.poST.ForStatement
import su.nsk.iae.post.poST.Statement

class ForStatementGenerator extends IStatementGenerator {
	
	new(ProgramGenerator program, ProcessGenerator process, StateGenerator state, StatementListGenerator context) {
		super(program, process, state, context)
	}
	
	override checkStatement(Statement statement) {
		return statement instanceof ForStatement
	}
	
	override generateStatement(Statement statement) {
		val s = statement as ForStatement
		return '''
			for «context.generateVar(s.variable)» in range(«context.generateExpression(s.forList.start)», «context.generateExpression(s.forList.end)»«IF s.forList.step !== null», «context.generateExpression(s.forList.step)»«ENDIF»):
				«context.generateStatementList(s.statement)»
		'''
	}
	
}