package su.nsk.iae.post.generator.py.imports

import su.nsk.iae.post.poST.Inline_code

class ImportsGenerator {
	
	Inline_code imports
	
	new(Inline_code code) {
		this.imports = code
	}
	
	def generateImports() {
		return '''
			«FOR p : imports.inline_code»
				«p.replaceAll("^[$]", "").replaceAll("^[\\s]", "")»
			«ENDFOR»
		'''
	}
	
}