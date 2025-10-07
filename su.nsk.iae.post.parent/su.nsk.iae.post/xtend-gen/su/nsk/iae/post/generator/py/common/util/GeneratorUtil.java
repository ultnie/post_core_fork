package su.nsk.iae.post.generator.py.common.util;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import su.nsk.iae.post.generator.py.common.ProcessGenerator;
import su.nsk.iae.post.generator.py.common.vars.VarHelper;
import su.nsk.iae.post.generator.py.common.vars.data.VarData;
import su.nsk.iae.post.poST.AddExpression;
import su.nsk.iae.post.poST.AddOperator;
import su.nsk.iae.post.poST.AndExpression;
import su.nsk.iae.post.poST.ArrayVariable;
import su.nsk.iae.post.poST.AssignmentType;
import su.nsk.iae.post.poST.CompExpression;
import su.nsk.iae.post.poST.CompOperator;
import su.nsk.iae.post.poST.Constant;
import su.nsk.iae.post.poST.EquExpression;
import su.nsk.iae.post.poST.EquOperator;
import su.nsk.iae.post.poST.Expression;
import su.nsk.iae.post.poST.FunctionCall;
import su.nsk.iae.post.poST.IntegerLiteral;
import su.nsk.iae.post.poST.MulExpression;
import su.nsk.iae.post.poST.MulOperator;
import su.nsk.iae.post.poST.NumericLiteral;
import su.nsk.iae.post.poST.ParamAssignment;
import su.nsk.iae.post.poST.ParamAssignmentElements;
import su.nsk.iae.post.poST.PowerExpression;
import su.nsk.iae.post.poST.PrimaryExpression;
import su.nsk.iae.post.poST.ProcessStatusExpression;
import su.nsk.iae.post.poST.RealLiteral;
import su.nsk.iae.post.poST.SignedInteger;
import su.nsk.iae.post.poST.SymbolicVariable;
import su.nsk.iae.post.poST.TimeLiteral;
import su.nsk.iae.post.poST.UnaryExpression;
import su.nsk.iae.post.poST.UnaryOperator;
import su.nsk.iae.post.poST.XorExpression;

@SuppressWarnings("all")
public class GeneratorUtil {
  public static String generateStopConstant() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_STOP");
    return _builder.toString();
  }

  public static String generateErrorConstant() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_ERROR");
    return _builder.toString();
  }

  public static String generateGlobalTime() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_global_time");
    return _builder.toString();
  }

  public static String generateVarName(final String process, final String variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_p_");
    _builder.append(process);
    _builder.append("_v_");
    _builder.append(variable);
    return _builder.toString();
  }

  public static String generateVarName(final ProcessGenerator process, final String variable) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_p_");
    String _name = process.getName();
    _builder.append(_name);
    _builder.append("_v_");
    _builder.append(variable);
    return _builder.toString();
  }

  public static String generateTimeoutName(final ProcessGenerator process) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_g_p_");
    String _name = process.getName();
    _builder.append(_name);
    _builder.append("_time");
    return _builder.toString();
  }

  public static String generateEnumName(final su.nsk.iae.post.poST.Process process) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_g_p_");
    String _name = process.getName();
    _builder.append(_name);
    _builder.append("_state");
    return _builder.toString();
  }

  public static String generateEnumName(final ProcessGenerator process) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_g_p_");
    String _name = process.getName();
    _builder.append(_name);
    _builder.append("_state");
    return _builder.toString();
  }

  public static String generateEnumStateConstant(final ProcessGenerator process, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_P_");
    String _upperCase = process.getName().toUpperCase();
    _builder.append(_upperCase);
    _builder.append("_S_");
    String _upperCase_1 = name.toUpperCase();
    _builder.append(_upperCase_1);
    return _builder.toString();
  }

  public static String generateConstant(final Constant constant) {
    NumericLiteral _num = constant.getNum();
    boolean _tripleNotEquals = (_num != null);
    if (_tripleNotEquals) {
      final NumericLiteral num = constant.getNum();
      if ((num instanceof IntegerLiteral)) {
        StringConcatenation _builder = new StringConcatenation();
        {
          String _type = ((IntegerLiteral)num).getType();
          boolean _tripleNotEquals_1 = (_type != null);
          if (_tripleNotEquals_1) {
          }
        }
        String _generateSignedInteger = GeneratorUtil.generateSignedInteger(((IntegerLiteral)num).getValue());
        _builder.append(_generateSignedInteger);
        return _builder.toString();
      }
      final RealLiteral dNum = ((RealLiteral) num);
      StringConcatenation _builder_1 = new StringConcatenation();
      {
        String _type_1 = dNum.getType();
        boolean _tripleNotEquals_2 = (_type_1 != null);
        if (_tripleNotEquals_2) {
        }
      }
      {
        boolean _isRSig = dNum.isRSig();
        if (_isRSig) {
          _builder_1.append("-");
        }
      }
      String _value = dNum.getValue();
      _builder_1.append(_value);
      return _builder_1.toString();
    }
    TimeLiteral _time = constant.getTime();
    boolean _tripleNotEquals_3 = (_time != null);
    if (_tripleNotEquals_3) {
      return GeneratorUtil.parseInterval(constant.getTime().getInterval());
    }
    boolean _equals = constant.getOth().equals("TRUE");
    if (_equals) {
      return "MuteBool(True)";
    }
    boolean _equals_1 = constant.getOth().equals("FALSE");
    if (_equals_1) {
      return "MuteBool(False)";
    }
    return constant.getOth();
  }

  public static String parseInterval(final String interval) {
    int days = 0;
    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    int milliseconds = 0;
    int pos = 0;
    boolean _contains = interval.contains("d");
    if (_contains) {
      int _parseInt = Integer.parseInt(interval.substring(0, interval.indexOf("d")));
      int _multiply = (_parseInt * 86400000);
      days = _multiply;
      pos = interval.indexOf("d");
    }
    boolean _contains_1 = interval.contains("h");
    if (_contains_1) {
      int _parseInt_1 = Integer.parseInt(interval.substring(pos, interval.indexOf("h")));
      int _multiply_1 = (_parseInt_1 * 3600000);
      hours = _multiply_1;
      pos = interval.indexOf("h");
    }
    boolean _contains_2 = interval.contains("m");
    if (_contains_2) {
      int _indexOf = interval.indexOf("m");
      int _indexOf_1 = interval.indexOf("ms");
      boolean _notEquals = (_indexOf != _indexOf_1);
      if (_notEquals) {
        int _parseInt_2 = Integer.parseInt(interval.substring(pos, interval.indexOf("m")));
        int _multiply_2 = (_parseInt_2 * 60000);
        minutes = _multiply_2;
        pos = interval.indexOf("m");
      }
    }
    boolean _contains_3 = interval.contains("s");
    if (_contains_3) {
      int _indexOf_2 = interval.indexOf("s");
      int _minus = (_indexOf_2 - 1);
      int _indexOf_3 = interval.indexOf("ms");
      boolean _notEquals_1 = (_minus != _indexOf_3);
      if (_notEquals_1) {
        int _parseInt_3 = Integer.parseInt(interval.substring(pos, interval.indexOf("s")));
        int _multiply_3 = (_parseInt_3 * 1000);
        seconds = _multiply_3;
        pos = interval.indexOf("s");
      }
    }
    boolean _contains_4 = interval.contains("ms");
    if (_contains_4) {
      milliseconds = Integer.parseInt(interval.substring(pos, interval.indexOf("ms")));
    }
    final int result = ((((days + hours) + minutes) + seconds) + milliseconds);
    return String.valueOf(result);
  }

  public static String generateSignedInteger(final SignedInteger sint) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isISig = sint.isISig();
      if (_isISig) {
        _builder.append("-");
      }
    }
    String _value = sint.getValue();
    _builder.append(_value);
    return _builder.toString();
  }

  public static String generateVars(final VarHelper helper) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = helper.getList().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          List<VarData> _list = helper.getList();
          for(final VarData v : _list) {
            String _generateSingleDeclaration = GeneratorUtil.generateSingleDeclaration(v);
            _builder.append(_generateSingleDeclaration);
            String _generateValue = GeneratorUtil.generateValue(v);
            _builder.append(_generateValue);
            _builder.newLineIfNotEmpty();
            {
              String _type = helper.getType();
              boolean _equals = Objects.equals(_type, "VAR_INPUT");
              if (_equals) {
                _builder.append("inVars[\'");
                String _generateSingleDeclaration_1 = GeneratorUtil.generateSingleDeclaration(v);
                _builder.append(_generateSingleDeclaration_1);
                _builder.append("\'] = ");
                String _generateSingleDeclaration_2 = GeneratorUtil.generateSingleDeclaration(v);
                _builder.append(_generateSingleDeclaration_2);
                _builder.newLineIfNotEmpty();
              } else {
                String _type_1 = helper.getType();
                boolean _equals_1 = Objects.equals(_type_1, "VAR_OUTPUT");
                if (_equals_1) {
                  _builder.append("outVars[\'");
                  String _generateSingleDeclaration_3 = GeneratorUtil.generateSingleDeclaration(v);
                  _builder.append(_generateSingleDeclaration_3);
                  _builder.append("\'] = ");
                  String _generateSingleDeclaration_4 = GeneratorUtil.generateSingleDeclaration(v);
                  _builder.append(_generateSingleDeclaration_4);
                  _builder.newLineIfNotEmpty();
                } else {
                  String _type_2 = helper.getType();
                  boolean _equals_2 = Objects.equals(_type_2, "VAR_IN_OUT");
                  if (_equals_2) {
                    _builder.append("inOutVars[\'");
                    String _generateSingleDeclaration_5 = GeneratorUtil.generateSingleDeclaration(v);
                    _builder.append(_generateSingleDeclaration_5);
                    _builder.append("\'] = ");
                    String _generateSingleDeclaration_6 = GeneratorUtil.generateSingleDeclaration(v);
                    _builder.append(_generateSingleDeclaration_6);
                    _builder.newLineIfNotEmpty();
                  } else {
                    String _type_3 = helper.getType();
                    boolean _equals_3 = Objects.equals(_type_3, "VAR_EXTERNAL");
                    if (_equals_3) {
                      _builder.append("exVars[\'");
                      String _generateSingleDeclaration_7 = GeneratorUtil.generateSingleDeclaration(v);
                      _builder.append(_generateSingleDeclaration_7);
                      _builder.append("\'] = ");
                      String _generateSingleDeclaration_8 = GeneratorUtil.generateSingleDeclaration(v);
                      _builder.append(_generateSingleDeclaration_8);
                      _builder.newLineIfNotEmpty();
                    } else {
                      String _type_4 = helper.getType();
                      boolean _equals_4 = Objects.equals(_type_4, "VAR");
                      if (_equals_4) {
                        _builder.append("Vars[\'");
                        String _generateSingleDeclaration_9 = GeneratorUtil.generateSingleDeclaration(v);
                        _builder.append(_generateSingleDeclaration_9);
                        _builder.append("\'] = ");
                        String _generateSingleDeclaration_10 = GeneratorUtil.generateSingleDeclaration(v);
                        _builder.append(_generateSingleDeclaration_10);
                        _builder.newLineIfNotEmpty();
                      } else {
                        String _type_5 = helper.getType();
                        boolean _equals_5 = Objects.equals(_type_5, "VAR_TEMP");
                        if (_equals_5) {
                          _builder.append("tempVars[\'");
                          String _generateSingleDeclaration_11 = GeneratorUtil.generateSingleDeclaration(v);
                          _builder.append(_generateSingleDeclaration_11);
                          _builder.append("\'] = ");
                          String _generateSingleDeclaration_12 = GeneratorUtil.generateSingleDeclaration(v);
                          _builder.append(_generateSingleDeclaration_12);
                          _builder.newLineIfNotEmpty();
                        } else {
                          String _type_6 = helper.getType();
                          boolean _equals_6 = Objects.equals(_type_6, "VAR_GLOBAL");
                          if (_equals_6) {
                            _builder.append("globVars[\'");
                            String _generateSingleDeclaration_13 = GeneratorUtil.generateSingleDeclaration(v);
                            _builder.append(_generateSingleDeclaration_13);
                            _builder.append("\'] = ");
                            String _generateSingleDeclaration_14 = GeneratorUtil.generateSingleDeclaration(v);
                            _builder.append(_generateSingleDeclaration_14);
                            _builder.newLineIfNotEmpty();
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        _builder.append("\t\t");
        _builder.newLine();
      }
    }
    return _builder.toString();
  }

  public static String generateVarDecl(final VarHelper helper) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = helper.getList().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          List<VarData> _list = helper.getList();
          for(final VarData v : _list) {
            _builder.append("global ");
            String _generateSingleDeclaration = GeneratorUtil.generateSingleDeclaration(v);
            _builder.append(_generateSingleDeclaration);
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.newLine();
      }
    }
    return _builder.toString();
  }

  public static String generateVarList(final VarHelper helper) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = helper.getList().isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        {
          List<VarData> _list = helper.getList();
          for(final VarData v : _list) {
            _builder.append("\'");
            String _generateSingleDeclaration = GeneratorUtil.generateSingleDeclaration(v);
            _builder.append(_generateSingleDeclaration);
            _builder.append("\':");
            String _generateSingleDeclaration_1 = GeneratorUtil.generateSingleDeclaration(v);
            _builder.append(_generateSingleDeclaration_1);
            _builder.append(",");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.newLine();
      }
    }
    return _builder.toString();
  }

  private static String generateSingleDeclaration(final VarData data) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = data.getName();
    _builder.append(_name);
    return _builder.toString();
  }

  private static String generateValue(final VarData v) {
    if (((v.getValue() == null) && (v.getArraValues() == null))) {
      String _type = v.getType();
      boolean _equals = Objects.equals(_type, "BOOL");
      if (_equals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(" ");
        _builder.append("= MuteBool(False)");
        return _builder.toString();
      }
      boolean _isArray = v.isArray();
      if (_isArray) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(" ");
        _builder_1.append("= []");
        return _builder_1.toString();
      }
      if ((((((((((Objects.equals(v.getType(), "INT") || Objects.equals(v.getType(), "SINT")) || Objects.equals(v.getType(), "DINT")) || Objects.equals(v.getType(), "LINT")) || Objects.equals(v.getType(), "UINT")) || Objects.equals(v.getType(), "USINT")) || Objects.equals(v.getType(), "UDINT")) || Objects.equals(v.getType(), "ULINT")) || Objects.equals(v.getType(), "REAL")) || Objects.equals(v.getType(), "LREAL"))) {
        return " = MuteNum(0)";
      }
      if ((((Objects.equals(v.getType(), "BYTE") || Objects.equals(v.getType(), "WORD")) || Objects.equals(v.getType(), "DWORD")) || Objects.equals(v.getType(), "LWORD"))) {
        return " = MuteBytes(b\'\')";
      }
      String _type_1 = v.getType();
      boolean _equals_1 = Objects.equals(_type_1, "TIME");
      if (_equals_1) {
        return " = 0";
      }
      String _type_2 = v.getType();
      boolean _equals_2 = Objects.equals(_type_2, "HEX_INT");
      if (_equals_2) {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append(" ");
        _builder_2.append("= MuteNum(0x0)");
        return _builder_2.toString();
      }
      String _type_3 = v.getType();
      boolean _equals_3 = Objects.equals(_type_3, "OCT_INT");
      if (_equals_3) {
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append(" ");
        _builder_3.append("= MuteNum(0o0)");
        return _builder_3.toString();
      }
      String _type_4 = v.getType();
      boolean _equals_4 = Objects.equals(_type_4, "BIN_INT");
      if (_equals_4) {
        StringConcatenation _builder_4 = new StringConcatenation();
        _builder_4.append(" ");
        _builder_4.append("= MuteNum(0b0)");
        return _builder_4.toString();
      }
    }
    boolean _isArray_1 = v.isArray();
    if (_isArray_1) {
      StringConcatenation _builder_5 = new StringConcatenation();
      _builder_5.append(" ");
      _builder_5.append("= [");
      String _join = IterableExtensions.join(v.getArraValues(), ", ");
      _builder_5.append(_join, " ");
      _builder_5.append("]");
      return _builder_5.toString();
    }
    if ((((Objects.equals(v.getType(), "BYTE") || Objects.equals(v.getType(), "WORD")) || Objects.equals(v.getType(), "DWORD")) || Objects.equals(v.getType(), "LWORD"))) {
      StringConcatenation _builder_6 = new StringConcatenation();
      _builder_6.append(" ");
      _builder_6.append("= b\'");
      String _value = v.getValue();
      _builder_6.append(_value, " ");
      return _builder_6.toString();
    }
    String _type_5 = v.getType();
    boolean _equals_5 = Objects.equals(_type_5, "HEX_INT");
    if (_equals_5) {
      StringConcatenation _builder_7 = new StringConcatenation();
      _builder_7.append(" ");
      _builder_7.append("= 0x");
      String _value_1 = v.getValue();
      _builder_7.append(_value_1, " ");
      return _builder_7.toString();
    }
    String _type_6 = v.getType();
    boolean _equals_6 = Objects.equals(_type_6, "OCT_INT");
    if (_equals_6) {
      StringConcatenation _builder_8 = new StringConcatenation();
      _builder_8.append(" ");
      _builder_8.append("= 0o");
      String _value_2 = v.getValue();
      _builder_8.append(_value_2, " ");
      return _builder_8.toString();
    }
    String _type_7 = v.getType();
    boolean _equals_7 = Objects.equals(_type_7, "BIN_INT");
    if (_equals_7) {
      StringConcatenation _builder_9 = new StringConcatenation();
      _builder_9.append(" ");
      _builder_9.append("= 0b");
      String _value_3 = v.getValue();
      _builder_9.append(_value_3, " ");
      return _builder_9.toString();
    }
    String _type_8 = v.getType();
    boolean _equals_8 = Objects.equals(_type_8, "BOOL");
    if (_equals_8) {
      String _value_4 = v.getValue();
      boolean _equals_9 = Objects.equals(_value_4, "TRUE");
      if (_equals_9) {
        StringConcatenation _builder_10 = new StringConcatenation();
        _builder_10.append(" ");
        _builder_10.append("= True");
        return _builder_10.toString();
      }
      String _value_5 = v.getValue();
      boolean _equals_10 = Objects.equals(_value_5, "FALSE");
      if (_equals_10) {
        StringConcatenation _builder_11 = new StringConcatenation();
        _builder_11.append(" ");
        _builder_11.append("= False");
        return _builder_11.toString();
      }
    }
    StringConcatenation _builder_12 = new StringConcatenation();
    _builder_12.append(" ");
    _builder_12.append("= ");
    String _value_6 = v.getValue();
    _builder_12.append(_value_6, " ");
    return _builder_12.toString();
  }

  public static String generateExpression(final Expression exp) {
    return GeneratorUtil.generateExpression(exp, null, null, null);
  }

  public static String generateExpression(final Expression exp, final Function<SymbolicVariable, String> gVar, final Function<ArrayVariable, String> gArray, final Function<ProcessStatusExpression, String> gPStatus) {
    boolean _matched = false;
    if (exp instanceof PrimaryExpression) {
      _matched=true;
      Constant _const = ((PrimaryExpression)exp).getConst();
      boolean _tripleNotEquals = (_const != null);
      if (_tripleNotEquals) {
        return GeneratorUtil.generateConstant(((PrimaryExpression)exp).getConst());
      } else {
        SymbolicVariable _variable = ((PrimaryExpression)exp).getVariable();
        boolean _tripleNotEquals_1 = (_variable != null);
        if (_tripleNotEquals_1) {
          if ((gVar != null)) {
            return gVar.apply(((PrimaryExpression)exp).getVariable());
          }
          return ((PrimaryExpression)exp).getVariable().getName();
        } else {
          ArrayVariable _array = ((PrimaryExpression)exp).getArray();
          boolean _tripleNotEquals_2 = (_array != null);
          if (_tripleNotEquals_2) {
            if ((gArray != null)) {
              return gArray.apply(((PrimaryExpression)exp).getArray());
            }
            StringConcatenation _builder = new StringConcatenation();
            String _name = ((PrimaryExpression)exp).getArray().getVariable().getName();
            _builder.append(_name);
            _builder.append("[");
            String _generateExpression = GeneratorUtil.generateExpression(((PrimaryExpression)exp).getArray().getIndex(), gVar, gArray, gPStatus);
            _builder.append(_generateExpression);
            _builder.append("]");
            return _builder.toString();
          } else {
            ProcessStatusExpression _procStatus = ((PrimaryExpression)exp).getProcStatus();
            boolean _tripleNotEquals_3 = (_procStatus != null);
            if (_tripleNotEquals_3) {
              if ((gPStatus != null)) {
                return gPStatus.apply(((PrimaryExpression)exp).getProcStatus());
              }
              StringConcatenation _builder_1 = new StringConcatenation();
              return _builder_1.toString();
            } else {
              FunctionCall _funCall = ((PrimaryExpression)exp).getFunCall();
              boolean _tripleNotEquals_4 = (_funCall != null);
              if (_tripleNotEquals_4) {
                StringConcatenation _builder_2 = new StringConcatenation();
                String _name_1 = ((PrimaryExpression)exp).getFunCall().getFunction().getName();
                _builder_2.append(_name_1);
                _builder_2.append("(");
                final Function<Expression, String> _function = (Expression x) -> {
                  return GeneratorUtil.generateExpression(x, gVar, gArray, gPStatus);
                };
                String _generateParamAssignmentElements = GeneratorUtil.generateParamAssignmentElements(((PrimaryExpression)exp).getFunCall().getArgs(), _function);
                _builder_2.append(_generateParamAssignmentElements);
                _builder_2.append(")");
                return _builder_2.toString();
              } else {
                StringConcatenation _builder_3 = new StringConcatenation();
                _builder_3.append("(");
                String _generateExpression_1 = GeneratorUtil.generateExpression(((PrimaryExpression)exp).getNestExpr(), gVar, gArray, gPStatus);
                _builder_3.append(_generateExpression_1);
                _builder_3.append(")");
                return _builder_3.toString();
              }
            }
          }
        }
      }
    }
    if (!_matched) {
      if (exp instanceof UnaryExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        {
          UnaryOperator _unOp = ((UnaryExpression)exp).getUnOp();
          boolean _equals = Objects.equals(_unOp, UnaryOperator.NOT);
          if (_equals) {
            _builder.append("not ");
          } else {
            UnaryOperator _unOp_1 = ((UnaryExpression)exp).getUnOp();
            boolean _equals_1 = Objects.equals(_unOp_1, UnaryOperator.UNMINUS);
            if (_equals_1) {
              _builder.append("-");
            }
          }
        }
        String _generateExpression = GeneratorUtil.generateExpression(((UnaryExpression)exp).getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (exp instanceof PowerExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _generateExpression = GeneratorUtil.generateExpression(((PowerExpression)exp).getLeft(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        _builder.append(" ** ");
        String _generateExpression_1 = GeneratorUtil.generateExpression(((PowerExpression)exp).getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (exp instanceof MulExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _generateExpression = GeneratorUtil.generateExpression(((MulExpression)exp).getLeft(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        _builder.append(" ");
        String _generateMulOperators = GeneratorUtil.generateMulOperators(((MulExpression)exp).getMulOp());
        _builder.append(_generateMulOperators);
        _builder.append(" ");
        String _generateExpression_1 = GeneratorUtil.generateExpression(((MulExpression)exp).getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (exp instanceof AddExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _generateExpression = GeneratorUtil.generateExpression(((AddExpression)exp).getLeft(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        _builder.append(" ");
        {
          AddOperator _addOp = ((AddExpression)exp).getAddOp();
          boolean _equals = Objects.equals(_addOp, AddOperator.PLUS);
          if (_equals) {
            _builder.append("+");
          } else {
            _builder.append("-");
          }
        }
        _builder.append(" ");
        String _generateExpression_1 = GeneratorUtil.generateExpression(((AddExpression)exp).getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (exp instanceof EquExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _generateExpression = GeneratorUtil.generateExpression(((EquExpression)exp).getLeft(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        _builder.append(" ");
        String _generateEquOperators = GeneratorUtil.generateEquOperators(((EquExpression)exp).getEquOp());
        _builder.append(_generateEquOperators);
        _builder.append(" ");
        String _generateExpression_1 = GeneratorUtil.generateExpression(((EquExpression)exp).getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (exp instanceof CompExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _generateExpression = GeneratorUtil.generateExpression(((CompExpression)exp).getLeft(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        _builder.append(" ");
        {
          CompOperator _compOp = ((CompExpression)exp).getCompOp();
          boolean _equals = Objects.equals(_compOp, CompOperator.EQUAL);
          if (_equals) {
            _builder.append("==");
          } else {
            _builder.append("!=");
          }
        }
        _builder.append(" ");
        String _generateExpression_1 = GeneratorUtil.generateExpression(((CompExpression)exp).getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (exp instanceof AndExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _generateExpression = GeneratorUtil.generateExpression(((AndExpression)exp).getLeft(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        _builder.append(" and ");
        String _generateExpression_1 = GeneratorUtil.generateExpression(((AndExpression)exp).getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (exp instanceof XorExpression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _generateExpression = GeneratorUtil.generateExpression(((XorExpression)exp).getLeft(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        _builder.append(" ^ ");
        String _generateExpression_1 = GeneratorUtil.generateExpression(((XorExpression)exp).getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression_1);
        return _builder.toString();
      }
    }
    if (!_matched) {
      if (exp instanceof Expression) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _generateExpression = GeneratorUtil.generateExpression(exp.getLeft(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression);
        _builder.append(" or ");
        String _generateExpression_1 = GeneratorUtil.generateExpression(exp.getRight(), gVar, gArray, gPStatus);
        _builder.append(_generateExpression_1);
        return _builder.toString();
      }
    }
    return null;
  }

  public static String generateParamAssignmentElements(final ParamAssignmentElements elements) {
    final Function<Expression, String> _function = (Expression x) -> {
      return GeneratorUtil.generateExpression(x);
    };
    return GeneratorUtil.generateParamAssignmentElements(elements, _function);
  }

  public static String generateParamAssignmentElements(final ParamAssignmentElements elements, final Function<Expression, String> gExp) {
    final Function<ParamAssignment, String> _function = (ParamAssignment x) -> {
      return GeneratorUtil.generateParamAssignment(x, gExp);
    };
    return IterableExtensions.join(elements.getElements().stream().<String>map(_function).collect(Collectors.<String>toList()), ", ");
  }

  private static String generateParamAssignment(final ParamAssignment ele, final Function<Expression, String> gExp) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = ele.getVariable().getName();
    _builder.append(_name);
    _builder.append(" ");
    {
      AssignmentType _assig = ele.getAssig();
      boolean _equals = Objects.equals(_assig, AssignmentType.IN);
      if (_equals) {
        _builder.append("=");
      } else {
        _builder.append("=>");
      }
    }
    _builder.append(" ");
    String _apply = gExp.apply(ele.getValue());
    _builder.append(_apply);
    return _builder.toString();
  }

  private static String generateEquOperators(final EquOperator op) {
    if (op != null) {
      switch (op) {
        case LESS:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<");
          return _builder.toString();
        case LESS_EQU:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("<=");
          return _builder_1.toString();
        case GREATER:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append(">");
          return _builder_2.toString();
        case GREATER_EQU:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append(">=");
          return _builder_3.toString();
        default:
          break;
      }
    }
    return null;
  }

  private static String generateMulOperators(final MulOperator op) {
    if (op != null) {
      switch (op) {
        case MUL:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("*");
          return _builder.toString();
        case DIV:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("/");
          return _builder_1.toString();
        case MOD:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("%");
          return _builder_2.toString();
        default:
          break;
      }
    }
    return null;
  }
}
