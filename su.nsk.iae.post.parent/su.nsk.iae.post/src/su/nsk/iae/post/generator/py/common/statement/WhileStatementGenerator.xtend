package su.nsk.iae.post.generator.py.common.statement

import su.nsk.iae.post.generator.py.common.ProcessGenerator
import su.nsk.iae.post.generator.py.common.ProgramGenerator
import su.nsk.iae.post.generator.py.common.StateGenerator
import su.nsk.iae.post.generator.py.common.StatementListGenerator
import su.nsk.iae.post.poST.Statement
import su.nsk.iae.post.poST.WhileStatement

class WhileStatementGenerator extends IStatementGenerator {
	
	new(ProgramGenerator program, ProcessGenerator process, StateGenerator state, StatementListGenerator context) {
		super(program, process, state, context)
	}
	
	override checkStatement(Statement statement) {
		return statement instanceof WhileStatement
	}
	
	override generateStatement(Statement statement) {
		val s = statement as WhileStatement
		return '''
			while «context.generateExpression(s.cond)»:
				«context.generateStatementList(s.statement)»
		'''
	}
	
}