package su.nsk.iae.post.generator.py.common.util

import java.util.function.Function
import su.nsk.iae.post.generator.py.common.ProcessGenerator
import su.nsk.iae.post.generator.py.common.vars.VarHelper
import su.nsk.iae.post.generator.py.common.vars.data.VarData
import su.nsk.iae.post.poST.AddExpression
import su.nsk.iae.post.poST.AddOperator
import su.nsk.iae.post.poST.AndExpression
import su.nsk.iae.post.poST.ArrayVariable
import su.nsk.iae.post.poST.CompExpression
import su.nsk.iae.post.poST.CompOperator
import su.nsk.iae.post.poST.Constant
import su.nsk.iae.post.poST.EquExpression
import su.nsk.iae.post.poST.EquOperator
import su.nsk.iae.post.poST.Expression
import su.nsk.iae.post.poST.IntegerLiteral
import su.nsk.iae.post.poST.MulExpression
import su.nsk.iae.post.poST.MulOperator
import su.nsk.iae.post.poST.PowerExpression
import su.nsk.iae.post.poST.PrimaryExpression
import su.nsk.iae.post.poST.Process
import su.nsk.iae.post.poST.ProcessStatusExpression
import su.nsk.iae.post.poST.RealLiteral
import su.nsk.iae.post.poST.SignedInteger
import su.nsk.iae.post.poST.SymbolicVariable
import su.nsk.iae.post.poST.UnaryExpression
import su.nsk.iae.post.poST.UnaryOperator
import su.nsk.iae.post.poST.XorExpression
import su.nsk.iae.post.poST.ParamAssignmentElements
import su.nsk.iae.post.poST.ParamAssignment
import su.nsk.iae.post.poST.AssignmentType
import java.util.stream.Collectors

class GeneratorUtil {
	
	static def String generateStopConstant() {
		return '''_STOP'''
	}
	
	static def String generateErrorConstant() {
		return '''_ERROR'''
	}
	
	static def String generateGlobalTime() {
		return '''_global_time'''
	}
	
	static def String generateVarName(String process, String variable) {
		return '''_p_«process»_v_«variable»'''
	}
	
	static def String generateVarName(ProcessGenerator process, String variable) {
		return '''_p_«process.name»_v_«variable»'''
	}
	
	static def String generateTimeoutName(ProcessGenerator process) {
		return '''_g_p_«process.name»_time'''
	}
	
	static def String generateEnumName(Process process) {
		return '''_g_p_«process.name»_state'''
	}
	
	static def String generateEnumName(ProcessGenerator process) {
		return '''_g_p_«process.name»_state'''
	}
	
	static def String generateEnumStateConstant(ProcessGenerator process, String name) {
		return '''_P_«process.name.toUpperCase»_S_«name.toUpperCase»'''
	}
	
	static def String generateConstant(Constant constant) {
		if (constant.num !== null) {
			val num = constant.num
			if (num instanceof IntegerLiteral) {
				return '''«IF num.type !== null»«ENDIF»«num.value.generateSignedInteger»'''
			}
			val dNum = num as RealLiteral
			return '''«IF dNum.type !== null»«ENDIF»«IF dNum.RSig»-«ENDIF»«dNum.value»'''
		}
		if (constant.time !== null) {
			//return '''timedelta(«parseInterval(constant.time.interval)»)'''
			return parseInterval(constant.time.interval)
		}
		if (constant.oth.equals('TRUE')) {
			return 'MuteBool(True)'
		}
		if (constant.oth.equals('FALSE')) {
			return 'MuteBool(False)'
		}
		return constant.oth
	}
	
	static def String parseInterval(String interval) {
		var days = 0
		var hours = 0
		var minutes = 0
		var seconds = 0
		var milliseconds = 0
		var pos = 0
		if (interval.contains("d")) {
			days = Integer.parseInt(interval.substring(0, interval.indexOf("d")))*86400000;
			pos = interval.indexOf("d")
		}
		if (interval.contains("h")) {
			hours = Integer.parseInt(interval.substring(pos, interval.indexOf("h")))*3600000;
			pos = interval.indexOf("h")
		}
		if (interval.contains("m")) {
			if (interval.indexOf("m") != interval.indexOf("ms")) {
				minutes = Integer.parseInt(interval.substring(pos, interval.indexOf("m")))*60000;
				pos = interval.indexOf("m")
			}
		}
		if (interval.contains("s")) {
			if (interval.indexOf("s")-1 != interval.indexOf("ms")) {
				seconds = Integer.parseInt(interval.substring(pos, interval.indexOf("s")))*1000;
				pos = interval.indexOf("s")
			}
		}
		if (interval.contains("ms")) {
			milliseconds = Integer.parseInt(interval.substring(pos, interval.indexOf("ms")))
		}
		val result = days+hours+minutes+seconds+milliseconds
		return String.valueOf(result)
	}
	
	
//	static def String parseInterval(String interval) {
//		var days = ""
//		var hours = ""
//		var minutes = ""
//		var seconds = ""
//		var milliseconds = ""
//		var pos = 0
//		if (interval.contains("d")) {
//			days = '''days=«interval.substring(0, interval.indexOf("d"))», '''
//			pos = interval.indexOf("d")
//		}
//		if (interval.contains("h")) {
//			hours = '''hours=«interval.substring(pos, interval.indexOf("h"))», '''
//			pos = interval.indexOf("h")
//		}
//		if (interval.contains("m")) {
//			if (interval.indexOf("m") != interval.indexOf("ms")) {
//				minutes = '''minutes=«interval.substring(pos, interval.indexOf("m"))», '''
//				pos = interval.indexOf("m")
//			}
//		}
//		if (interval.contains("s")) {
//			if (interval.indexOf("s")-1 != interval.indexOf("ms")) {
//				seconds = '''seconds=«interval.substring(pos, interval.indexOf("s"))», '''
//				pos = interval.indexOf("s")
//			}
//		}
//		if (interval.contains("ms")) {
//			milliseconds = '''milliseconds=«interval.substring(pos, interval.indexOf("ms"))»'''
//		}
//		val result = days+hours+minutes+seconds+milliseconds
//		if (!result.isEmpty()) {
//			if(result.substring(result.length()-2) == ', ') {
//				return result.substring(0, result.length()-2)
//			}
//		}
//		return result
//	}
	
	static def String generateSignedInteger(SignedInteger sint) {
		return '''«IF sint.ISig»-«ENDIF»«sint.value»''' 
	}
	
	static def String generateVars(VarHelper helper) '''
		«IF !helper.list.empty»
			«FOR v : helper.list»
				«v.generateSingleDeclaration»«v.generateValue»
				«IF helper.type == "VAR_INPUT"»
					inVars['«v.generateSingleDeclaration»'] = «v.generateSingleDeclaration»
				«ELSEIF helper.type == "VAR_OUTPUT"»
					outVars['«v.generateSingleDeclaration»'] = «v.generateSingleDeclaration»
				«ELSEIF helper.type == "VAR_IN_OUT"»
					inOutVars['«v.generateSingleDeclaration»'] = «v.generateSingleDeclaration»
				«ELSEIF helper.type == "VAR_EXTERNAL"»
					exVars['«v.generateSingleDeclaration»'] = «v.generateSingleDeclaration»
				«ELSEIF helper.type == "VAR"»
					Vars['«v.generateSingleDeclaration»'] = «v.generateSingleDeclaration»
				«ELSEIF helper.type == "VAR_TEMP"»
					tempVars['«v.generateSingleDeclaration»'] = «v.generateSingleDeclaration»
				«ELSEIF helper.type == "VAR_GLOBAL"»
					globVars['«v.generateSingleDeclaration»'] = «v.generateSingleDeclaration»
				«ENDIF»
			«ENDFOR»
					
		«ENDIF»
	'''
	
	static def String generateVarDecl(VarHelper helper) '''
		«IF !helper.list.empty»
			«FOR v : helper.list»
				global «v.generateSingleDeclaration»
			«ENDFOR»
				
		«ENDIF»
	'''
	
	static def String generateVarList(VarHelper helper) '''
		«IF !helper.list.empty»
			«FOR v : helper.list»
				'«v.generateSingleDeclaration»':«v.generateSingleDeclaration»,
			«ENDFOR»
			
		«ENDIF»
	'''
	
	private static def String generateSingleDeclaration(VarData data) {
		return '''«data.name»'''
	}
	
	private static def String generateValue(VarData v) {
		if ((v.value === null) && (v.arraValues === null)) {
			if (v.type == "BOOL") {
				return ''' = MuteBool(False)'''	
			}
			if (v.array) {
				return ''' = []'''	
			}
			if (v.type == "INT" || v.type == "SINT" || v.type == "DINT" || v.type == "LINT" || v.type == "UINT" || v.type == "USINT" || v.type == "UDINT" || v.type == "ULINT" || v.type == "REAL" || v.type == "LREAL") {
				return " = MuteNum(0)"
			}
			if (v.type == "BYTE" || v.type == "WORD" || v.type == "DWORD" || v.type == "LWORD") {
				//Ïðîâåðèòü
				return " = MuteBytes(b'')"
			}
			if (v.type == "TIME") {
				return " = 0"
			}
			if (v.type == "HEX_INT") {
				return ''' = MuteNum(0x0)'''
			}
			if (v.type == "OCT_INT") {
				return ''' = MuteNum(0o0)'''
			}
			if (v.type == "BIN_INT") {
				return ''' = MuteNum(0b0)'''
			}
		}
		if (v.array) {
			return ''' = [«v.arraValues.join(', ')»]'''	
		}
		if (v.type == "BYTE" || v.type == "WORD" || v.type == "DWORD" || v.type == "LWORD") {
			return ''' = b'«v.value»'''
		}
		if (v.type == "HEX_INT") {
			return ''' = 0x«v.value»'''
		}
		if (v.type == "OCT_INT") {
			return ''' = 0o«v.value»'''
		}
		if (v.type == "BIN_INT") {
			return ''' = 0b«v.value»'''
		}
		if (v.type == "BOOL") {
			if (v.value == "TRUE")
				return ''' = True'''
			if (v.value == "FALSE")
				return ''' = False'''
		}
		return ''' = «v.value»'''
	}
	
	static def String generateExpression(Expression exp) {
		return generateExpression(exp, null, null, null)
	}
	
	static def String generateExpression(Expression exp, 
		Function<SymbolicVariable, String> gVar,
		Function<ArrayVariable, String> gArray,
		Function<ProcessStatusExpression, String> gPStatus) {
		switch exp {
			PrimaryExpression: {
				if (exp.const !== null) {
					return exp.const.generateConstant
				} else if (exp.variable !== null) {
					if (gVar !== null) {
						return gVar.apply(exp.variable)
					}
					return exp.variable.name
				} else if (exp.array !== null) {
					if (gArray !== null) {
						return gArray.apply(exp.array)
					}
					return '''«exp.array.variable.name»[«exp.array.index.generateExpression(gVar, gArray, gPStatus)»]'''
				} else if (exp.procStatus !== null) {
					if (gPStatus !== null) {
						return gPStatus.apply(exp.procStatus)
					}
					return ''''''
				} else if (exp.funCall !== null) {
					return '''«exp.funCall.function.name»(«exp.funCall.args.generateParamAssignmentElements([x | x.generateExpression(gVar, gArray, gPStatus)])»)'''
				} else {
					return '''(«exp.nestExpr.generateExpression(gVar, gArray, gPStatus)»)'''
				}
			}
			UnaryExpression:
				return '''«IF exp.unOp == UnaryOperator.NOT»not «ELSEIF exp.unOp == UnaryOperator.UNMINUS»-«ENDIF»«exp.right.generateExpression(gVar, gArray, gPStatus)»'''
			PowerExpression:
				return '''«exp.left.generateExpression(gVar, gArray, gPStatus)» ** «exp.right.generateExpression(gVar, gArray, gPStatus)»'''
			MulExpression:
				return '''«exp.left.generateExpression(gVar, gArray, gPStatus)» «exp.mulOp.generateMulOperators» «exp.right.generateExpression(gVar, gArray, gPStatus)»'''
			AddExpression:
				return '''«exp.left.generateExpression(gVar, gArray, gPStatus)» «IF exp.addOp == AddOperator.PLUS»+«ELSE»-«ENDIF» «exp.right.generateExpression(gVar, gArray, gPStatus)»'''
			EquExpression:
				return '''«exp.left.generateExpression(gVar, gArray, gPStatus)» «exp.equOp.generateEquOperators» «exp.right.generateExpression(gVar, gArray, gPStatus)»'''
			CompExpression:
				return '''«exp.left.generateExpression(gVar, gArray, gPStatus)» «IF exp.compOp == CompOperator.EQUAL»==«ELSE»!=«ENDIF» «exp.right.generateExpression(gVar, gArray, gPStatus)»'''
			AndExpression:
				return '''«exp.left.generateExpression(gVar, gArray, gPStatus)» and «exp.right.generateExpression(gVar, gArray, gPStatus)»'''
			XorExpression:
				return '''«exp.left.generateExpression(gVar, gArray, gPStatus)» ^ «exp.right.generateExpression(gVar, gArray, gPStatus)»'''
			Expression:
				return '''«exp.left.generateExpression(gVar, gArray, gPStatus)» or «exp.right.generateExpression(gVar, gArray, gPStatus)»'''
		}
	}
	
	static def String generateParamAssignmentElements(ParamAssignmentElements elements) {
		return generateParamAssignmentElements(elements, [x | x.generateExpression])
	}
	
	static def String generateParamAssignmentElements(ParamAssignmentElements elements, Function<Expression, String> gExp) {
		return elements.elements.stream.map([x | x.generateParamAssignment(gExp)]).collect(Collectors.toList).join(", ")
	}
	
	private static def String generateParamAssignment(ParamAssignment ele, Function<Expression, String> gExp) {
		return '''«ele.variable.name» «IF ele.assig == AssignmentType.IN»=«ELSE»=>«ENDIF» «gExp.apply(ele.value)»'''
	}
	
	private static def String generateEquOperators(EquOperator op) {
		switch op {
			case EquOperator.LESS:
				return '''<'''
			case EquOperator.LESS_EQU:
				return '''<='''
			case EquOperator.GREATER:
				return '''>'''
			case EquOperator.GREATER_EQU:
				return '''>='''
		}
	}
	
	private static def String generateMulOperators(MulOperator op) {
		switch op {
			case MulOperator.MUL:
				return '''*'''
			case MulOperator.DIV:
				return '''/'''
			case MulOperator.MOD:
				return '''%'''
		}
	}
	
}
