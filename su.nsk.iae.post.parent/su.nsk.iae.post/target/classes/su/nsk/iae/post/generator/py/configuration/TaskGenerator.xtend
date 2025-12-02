package su.nsk.iae.post.generator.py.configuration

import su.nsk.iae.post.poST.Task

import static extension su.nsk.iae.post.generator.py.common.util.GeneratorUtil.*

class TaskGenerator {
	
	Task task
	
	new (Task task) {
		this.task = task
	}
	
	def String generateTask() {
		return '''taskTime = «generateFirstArg»'''
	}
	
	private def String generateFirstArg() {
		val init = task.init
		if (init.interval !== null) {
			return '''«init.interval.generateConstant»'''
		}
		return '''«init.single.generateConstant»'''
	}
	
	def String generateTaskTime() {
		val init = task.init
		if (init.interval !== null) {
			//Interval
			return ''' = «init.interval.generateConstant»'''
		}
		//Single
		return ''' = None'''
	}
	
}