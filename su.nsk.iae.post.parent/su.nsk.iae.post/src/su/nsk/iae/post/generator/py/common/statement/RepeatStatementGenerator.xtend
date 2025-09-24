package su.nsk.iae.post.generator.py.common.statement

import su.nsk.iae.post.generator.py.common.ProcessGenerator
import su.nsk.iae.post.generator.py.common.ProgramGenerator
import su.nsk.iae.post.generator.py.common.StateGenerator
import su.nsk.iae.post.generator.py.common.StatementListGenerator
import su.nsk.iae.post.poST.RepeatStatement
import su.nsk.iae.post.poST.Statement

class RepeatStatementGenerator extends IStatementGenerator {
	
	new(ProgramGenerator program, ProcessGenerator process, StateGenerator state, StatementListGenerator context) {
		super(program, process, state, context)
	}
	
	override checkStatement(Statement statement) {
		return statement instanceof RepeatStatement
	}
	
	override generateStatement(Statement statement) {
		val s = statement as RepeatStatement
		return '''
			while True:
				«context.generateStatementList(s.statement)»
			if «context.generateExpression(s.cond)»:
				break
		'''
	}
	
}