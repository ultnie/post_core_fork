from math import sin, tan, acos, radians, degrees
from datetime import date, datetime
import time
import enum
from MuteTypes import MuteBool, MuteNum, MuteStr, MuteBytes, get_value



inVars = {}
outVars = {}
inOutVars = {}
Vars = {}
exVars = {}
globVars = {}
tempVars = {}
pStates = {}
pTimes = {}


sunrise = MuteNum(0)
globVars['sunrise'] = sunrise
sunset = MuteNum(0)
globVars['sunset'] = sunset
date_iso = MuteStr("2026-06-23")
globVars['date_iso'] = date_iso
datetime_iso = MuteStr("2026-06-23T12:00:00")
globVars['datetime_iso'] = datetime_iso
timezone_offset = MuteNum(0)
globVars['timezone_offset'] = timezone_offset
latitude = MuteNum(0)
globVars['latitude'] = latitude
longitude = MuteNum(0)
globVars['longitude'] = longitude
		

_prev_global_time = None

def setVariable (name, value):
	try:
		globals()[name].__set__(value)
	except:
		globals()[name] = value
	if name in outVars:
		try:
			outVars[name].__set__(value)
		except:
			outVars[name] = value
	if name in inOutVars:
		try:
			inOutVars[name].__set__(value)
		except:
			inOutVars[name] = value
	if name in exVars:
		try:
			exVars[name].__set__(value)
		except:
			exVars[name] = value
	if name in globVars:
		try:
			globVars[name].__set__(value)
		except:
			globVars[name] = value
	if name in Vars:
		try:
			Vars[name].__set__(value)
		except:
			Vars[name] = value
			

def getTimeDelta():
    global _global_time
    global _prev_global_time

    if _prev_global_time is None:
        return 0

    return _global_time - _prev_global_time

_p_RiseAndDawn_v_h0 = MuteNum(0)
Vars['_p_RiseAndDawn_v_h0'] = _p_RiseAndDawn_v_h0
_p_RiseAndDawn_v_solar_noon = MuteNum(0)
Vars['_p_RiseAndDawn_v_solar_noon'] = _p_RiseAndDawn_v_solar_noon
_global_time = 0
Vars['_global_time'] = _global_time
_prev_global_time = 0
Vars['_prev_global_time'] = _prev_global_time
_dT = 0
Vars['_dT'] = _dT
		
taskTime = 0


processesDict = {}

def getTimeDelta():
	global _global_time
	global _prev_global_time
	
	if _prev_global_time is not None:
		return _global_time - _prev_global_time
	else:
		return 0

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


class Program:
	global taskTime
	processes = []
	
	class Initial:
		startTime = ""
		global pStates
		
		class States(enum.Enum):
			Initialize = enum.auto()
			Stop = 254
			Error = 255
			
		def set_next(self):
			global Vars
			self.curState = self.States(self.curState.value + 1)
			pStates[f'{self.name()}_state'] = self.States(self.curState).name
					
		def name(self):
			return self.__class__.__name__
			
		curState = States.Stop
		
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
				case self.States.Initialize:
					self.startTime = _global_time
					pTimes['Initial_time'] = self.startTime
					start(processesDict['RiseAndDawn'])
					stop(processesDict['Initial'])
				case _:
					pass
		
	initial = Initial()
	processes.append(initial)
	processesDict['Initial'] = initial
	pStates['Initial_state'] = initial.States(initial.curState).name
	pTimes['Initial_time'] = initial.startTime
	
	class RiseAndDawn:
		startTime = ""
		global pStates
		
		class States(enum.Enum):
			Calc = enum.auto()
			Stop = 254
			Error = 255
			
		def set_next(self):
			global Vars
			self.curState = self.States(self.curState.value + 1)
			pStates[f'{self.name()}_state'] = self.States(self.curState).name
					
		def name(self):
			return self.__class__.__name__
			
		curState = States.Stop
		
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
				case self.States.Calc:
					self.startTime = _global_time
					pTimes['RiseAndDawn_time'] = self.startTime
					n = date.fromisoformat(get_value(date_iso)).timetuple().tm_yday;
					h0 = degrees(acos(-tan(radians(latitude)) * tan(radians(23.44 * sin(radians((360 / 365) * (n - 81)))))));
					setVariable('_p_RiseAndDawn_v_solar_noon', 12 + timezone_offset - longitude / 15)
					setVariable('sunrise', _p_RiseAndDawn_v_solar_noon - _p_RiseAndDawn_v_h0 / 15)
					setVariable('sunset', _p_RiseAndDawn_v_solar_noon + _p_RiseAndDawn_v_h0 / 15)
				case _:
					pass
		
	riseanddawn = RiseAndDawn()
	processes.append(riseanddawn)
	processesDict['RiseAndDawn'] = riseanddawn
	pStates['RiseAndDawn_state'] = riseanddawn.States(riseanddawn.curState).name
	pTimes['RiseAndDawn_time'] = riseanddawn.startTime
	
	start(processes[0])
	
	def run_iter(self):
		global _global_time
		global _prev_global_time
		global _dT
		
		Vars['_global_time'] = _global_time
		setVariable('_dT', getTimeDelta())
		
		for process in self.processes:
			process.run()
		_prev_global_time = _global_time
		Vars['_prev_global_time'] = _prev_global_time


def getVariable(name):
	return globals()[name]
