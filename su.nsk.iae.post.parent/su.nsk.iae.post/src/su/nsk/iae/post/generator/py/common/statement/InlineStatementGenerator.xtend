package su.nsk.iae.post.generator.py.common.statement

import su.nsk.iae.post.poST.Statement
import su.nsk.iae.post.generator.py.common.ProgramGenerator
import su.nsk.iae.post.generator.py.common.ProcessGenerator
import su.nsk.iae.post.generator.py.common.StateGenerator
import su.nsk.iae.post.generator.py.common.StatementListGenerator
import su.nsk.iae.post.poST.Inline_code

class InlineStatementGenerator extends IStatementGenerator {
	
	new(ProgramGenerator program, ProcessGenerator process, StateGenerator state, StatementListGenerator context) {
		super(program, process, state, context)
	}
	
	override checkStatement(Statement statement) {
		return statement instanceof Inline_code
	}
	
	override generateStatement(Statement statement) {
		val s = statement as Inline_code
		return '''
		«FOR p : s.inline_code»
			«p.replaceAll("^[$]", "").replaceAll("^[\\s]", "")»
		«ENDFOR»
		'''
	}
	
}