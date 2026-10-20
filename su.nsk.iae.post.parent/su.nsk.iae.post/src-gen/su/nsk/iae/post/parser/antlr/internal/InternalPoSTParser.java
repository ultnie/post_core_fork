package su.nsk.iae.post.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import su.nsk.iae.post.services.PoSTGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPoSTParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_SINGLE_DECLARATION", "RULE_INTERVAL_DECLARATION", "RULE_PRIORITY_DECLARATION", "RULE_INTEGER", "RULE_OR_OPERATOR", "RULE_XOR_OPERATOR", "RULE_AND_OPERATOR", "RULE_POWER_OPERATOR", "RULE_INLINE_PHRASE", "RULE_DIRECT_VARIABLE", "RULE_TIME_PREF_LITERAL", "RULE_INTERVAL", "RULE_BIT_STRING_TYPE_NAME", "RULE_TIME_TYPE_NAME", "RULE_STRING_TYPE_NAME", "RULE_REAL_TYPE_NAME", "RULE_SIGNED_INTEGER_TYPE_NAME", "RULE_UNSIGNED_INTEGER_TYPE_NAME", "RULE_STRING_LITERAL", "RULE_BINARY_INTEGER", "RULE_OCTAL_INTEGER", "RULE_HEX_INTEGER", "RULE_BOOLEAN_LITERAL", "RULE_REAL", "RULE_DIRECT_TYPE_PREFIX", "RULE_DIRECT_SIZE_PREFIX", "RULE_DIGIT", "RULE_BIT", "RULE_OCTAL_DIGIT", "RULE_HEX_DIGIT", "RULE_LETTER", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'CONFIGURATION'", "'END_CONFIGURATION'", "'RESOURCE'", "'ON'", "'END_RESOURCE'", "';'", "'TASK'", "'('", "')'", "':='", "','", "'PROGRAM'", "'WITH'", "':'", "'PROCESS'", "'ACTIVE'", "'END_PROGRAM'", "'FUNCTION_BLOCK'", "'END_FUNCTION_BLOCK'", "'FUNCTION'", "'END_FUNCTION'", "'END_PROCESS'", "'STATE'", "'LOOPED'", "'END_STATE'", "'SET'", "'NEXT'", "'IN'", "'INACTIVE'", "'STOP'", "'ERROR'", "'START'", "'RESTART'", "'TIMEOUT'", "'THEN'", "'END_TIMEOUT'", "'RESET'", "'TIMER'", "'#dT'", "'IF'", "'ELSIF'", "'ELSE'", "'END_IF'", "'CASE'", "'OF'", "'END_CASE'", "'FOR'", "'DO'", "'END_FOR'", "'TO'", "'BY'", "'WHILE'", "'END_WHILE'", "'REPEAT'", "'UNTIL'", "'END_REPEAT'", "'RETURN'", "'EXIT'", "'VAR_INPUT'", "'END_VAR'", "'VAR_OUTPUT'", "'VAR_IN_OUT'", "'VAR'", "'CONSTANT'", "'VAR_TEMP'", "'VAR_EXTERNAL'", "'AT'", "'VAR_GLOBAL'", "'VAR_PROCESS'", "'['", "']'", "'ARRAY'", "'*'", "'..'", "'-'", "'#'", "'=>'", "'='", "'<>'", "'<'", "'>'", "'<='", "'>='", "'+'", "'/'", "'MOD'", "'NOT'"
    };
    public static final int T__50=50;
    public static final int RULE_PRIORITY_DECLARATION=7;
    public static final int RULE_INTERVAL=16;
    public static final int RULE_BIT=32;
    public static final int RULE_SINGLE_DECLARATION=5;
    public static final int T__59=59;
    public static final int RULE_SIGNED_INTEGER_TYPE_NAME=21;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int RULE_REAL_TYPE_NAME=20;
    public static final int RULE_BOOLEAN_LITERAL=27;
    public static final int RULE_REAL=28;
    public static final int RULE_DIGIT=31;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=36;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__126=126;
    public static final int T__63=63;
    public static final int T__125=125;
    public static final int T__64=64;
    public static final int RULE_BIT_STRING_TYPE_NAME=17;
    public static final int T__65=65;
    public static final int RULE_DIRECT_VARIABLE=14;
    public static final int RULE_OR_OPERATOR=9;
    public static final int RULE_XOR_OPERATOR=10;
    public static final int RULE_HEX_INTEGER=26;
    public static final int RULE_TIME_TYPE_NAME=18;
    public static final int RULE_STRING_TYPE_NAME=19;
    public static final int RULE_OCTAL_DIGIT=33;
    public static final int RULE_AND_OPERATOR=11;
    public static final int RULE_LETTER=35;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int RULE_HEX_DIGIT=34;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int RULE_INTERVAL_DECLARATION=6;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int T__90=90;
    public static final int T__99=99;
    public static final int T__95=95;
    public static final int RULE_UNSIGNED_INTEGER_TYPE_NAME=22;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int RULE_DIRECT_SIZE_PREFIX=30;
    public static final int T__122=122;
    public static final int T__70=70;
    public static final int T__121=121;
    public static final int T__71=71;
    public static final int T__124=124;
    public static final int T__72=72;
    public static final int T__123=123;
    public static final int T__120=120;
    public static final int RULE_DIRECT_TYPE_PREFIX=29;
    public static final int RULE_POWER_OPERATOR=12;
    public static final int RULE_STRING_LITERAL=23;
    public static final int RULE_BINARY_INTEGER=24;
    public static final int RULE_SL_COMMENT=37;
    public static final int RULE_INLINE_PHRASE=13;
    public static final int RULE_TIME_PREF_LITERAL=15;
    public static final int T__77=77;
    public static final int T__119=119;
    public static final int RULE_OCTAL_INTEGER=25;
    public static final int T__78=78;
    public static final int T__118=118;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int T__115=115;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__114=114;
    public static final int T__75=75;
    public static final int T__117=117;
    public static final int T__76=76;
    public static final int T__116=116;
    public static final int T__80=80;
    public static final int T__111=111;
    public static final int T__81=81;
    public static final int T__110=110;
    public static final int T__82=82;
    public static final int T__113=113;
    public static final int T__83=83;
    public static final int T__112=112;
    public static final int RULE_WS=38;
    public static final int RULE_ANY_OTHER=39;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
    public static final int T__109=109;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int RULE_INTEGER=8;
    public static final int T__86=86;
    public static final int T__106=106;
    public static final int T__87=87;
    public static final int T__105=105;

    // delegates
    // delegators


        public InternalPoSTParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalPoSTParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalPoSTParser.tokenNames; }
    public String getGrammarFileName() { return "InternalPoST.g"; }



     	private PoSTGrammarAccess grammarAccess;

        public InternalPoSTParser(TokenStream input, PoSTGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected PoSTGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalPoST.g:65:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalPoST.g:65:46: (iv_ruleModel= ruleModel EOF )
            // InternalPoST.g:66:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalPoST.g:72:1: ruleModel returns [EObject current=null] : ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )* ) ) ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_imports_1_0 = null;

        EObject lv_conf_2_0 = null;

        EObject lv_globVars_3_0 = null;

        EObject lv_programs_4_0 = null;

        EObject lv_fbs_5_0 = null;

        EObject lv_funs_6_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:78:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )* ) ) ) )
            // InternalPoST.g:79:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )* ) ) )
            {
            // InternalPoST.g:79:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )* ) ) )
            // InternalPoST.g:80:3: ( ( ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )* ) )
            {
            // InternalPoST.g:80:3: ( ( ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )* ) )
            // InternalPoST.g:81:4: ( ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )* )
            {
             
            			  getUnorderedGroupHelper().enter(grammarAccess.getModelAccess().getUnorderedGroup());
            			
            // InternalPoST.g:84:4: ( ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )* )
            // InternalPoST.g:85:5: ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )*
            {
            // InternalPoST.g:85:5: ( ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) ) | ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) ) )*
            loop5:
            do {
                int alt5=7;
                int LA5_0 = input.LA(1);

                if ( LA5_0 == RULE_INLINE_PHRASE && getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 0) ) {
                    alt5=1;
                }
                else if ( LA5_0 == 40 && getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 1) ) {
                    alt5=2;
                }
                else if ( LA5_0 == 107 && getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 2) ) {
                    alt5=3;
                }
                else if ( LA5_0 == 51 && getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 3) ) {
                    alt5=4;
                }
                else if ( LA5_0 == 57 && getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 4) ) {
                    alt5=5;
                }
                else if ( LA5_0 == 59 && getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 5) ) {
                    alt5=6;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalPoST.g:86:3: ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) )
            	    {
            	    // InternalPoST.g:86:3: ({...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) ) )
            	    // InternalPoST.g:87:4: {...}? => ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleModel", "getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 0)");
            	    }
            	    // InternalPoST.g:87:99: ( ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) ) )
            	    // InternalPoST.g:88:5: ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getModelAccess().getUnorderedGroup(), 0);
            	    				
            	    // InternalPoST.g:91:8: ({...}? => ( (lv_imports_1_0= ruleInline_code ) ) )
            	    // InternalPoST.g:91:9: {...}? => ( (lv_imports_1_0= ruleInline_code ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleModel", "true");
            	    }
            	    // InternalPoST.g:91:18: ( (lv_imports_1_0= ruleInline_code ) )
            	    // InternalPoST.g:91:19: (lv_imports_1_0= ruleInline_code )
            	    {
            	    // InternalPoST.g:91:19: (lv_imports_1_0= ruleInline_code )
            	    // InternalPoST.g:92:9: lv_imports_1_0= ruleInline_code
            	    {

            	    									newCompositeNode(grammarAccess.getModelAccess().getImportsInline_codeParserRuleCall_0_0());
            	    								
            	    pushFollow(FOLLOW_3);
            	    lv_imports_1_0=ruleInline_code();

            	    state._fsp--;


            	    									if (current==null) {
            	    										current = createModelElementForParent(grammarAccess.getModelRule());
            	    									}
            	    									set(
            	    										current,
            	    										"imports",
            	    										lv_imports_1_0,
            	    										"su.nsk.iae.post.PoST.Inline_code");
            	    									afterParserOrEnumRuleCall();
            	    								

            	    }


            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getModelAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalPoST.g:114:3: ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) )
            	    {
            	    // InternalPoST.g:114:3: ({...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) ) )
            	    // InternalPoST.g:115:4: {...}? => ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleModel", "getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 1)");
            	    }
            	    // InternalPoST.g:115:99: ( ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) ) )
            	    // InternalPoST.g:116:5: ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) )
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getModelAccess().getUnorderedGroup(), 1);
            	    				
            	    // InternalPoST.g:119:8: ({...}? => ( (lv_conf_2_0= ruleConfiguration ) ) )
            	    // InternalPoST.g:119:9: {...}? => ( (lv_conf_2_0= ruleConfiguration ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleModel", "true");
            	    }
            	    // InternalPoST.g:119:18: ( (lv_conf_2_0= ruleConfiguration ) )
            	    // InternalPoST.g:119:19: (lv_conf_2_0= ruleConfiguration )
            	    {
            	    // InternalPoST.g:119:19: (lv_conf_2_0= ruleConfiguration )
            	    // InternalPoST.g:120:9: lv_conf_2_0= ruleConfiguration
            	    {

            	    									newCompositeNode(grammarAccess.getModelAccess().getConfConfigurationParserRuleCall_1_0());
            	    								
            	    pushFollow(FOLLOW_3);
            	    lv_conf_2_0=ruleConfiguration();

            	    state._fsp--;


            	    									if (current==null) {
            	    										current = createModelElementForParent(grammarAccess.getModelRule());
            	    									}
            	    									set(
            	    										current,
            	    										"conf",
            	    										lv_conf_2_0,
            	    										"su.nsk.iae.post.PoST.Configuration");
            	    									afterParserOrEnumRuleCall();
            	    								

            	    }


            	    }


            	    }

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getModelAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalPoST.g:142:3: ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) )
            	    {
            	    // InternalPoST.g:142:3: ({...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ ) )
            	    // InternalPoST.g:143:4: {...}? => ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleModel", "getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 2)");
            	    }
            	    // InternalPoST.g:143:99: ( ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+ )
            	    // InternalPoST.g:144:5: ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getModelAccess().getUnorderedGroup(), 2);
            	    				
            	    // InternalPoST.g:147:8: ({...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) ) )+
            	    int cnt1=0;
            	    loop1:
            	    do {
            	        int alt1=2;
            	        int LA1_0 = input.LA(1);

            	        if ( (LA1_0==107) ) {
            	            int LA1_2 = input.LA(2);

            	            if ( ((true)) ) {
            	                alt1=1;
            	            }


            	        }


            	        switch (alt1) {
            	    	case 1 :
            	    	    // InternalPoST.g:147:9: {...}? => ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleModel", "true");
            	    	    }
            	    	    // InternalPoST.g:147:18: ( (lv_globVars_3_0= ruleGlobalVarDeclaration ) )
            	    	    // InternalPoST.g:147:19: (lv_globVars_3_0= ruleGlobalVarDeclaration )
            	    	    {
            	    	    // InternalPoST.g:147:19: (lv_globVars_3_0= ruleGlobalVarDeclaration )
            	    	    // InternalPoST.g:148:9: lv_globVars_3_0= ruleGlobalVarDeclaration
            	    	    {

            	    	    									newCompositeNode(grammarAccess.getModelAccess().getGlobVarsGlobalVarDeclarationParserRuleCall_2_0());
            	    	    								
            	    	    pushFollow(FOLLOW_3);
            	    	    lv_globVars_3_0=ruleGlobalVarDeclaration();

            	    	    state._fsp--;


            	    	    									if (current==null) {
            	    	    										current = createModelElementForParent(grammarAccess.getModelRule());
            	    	    									}
            	    	    									add(
            	    	    										current,
            	    	    										"globVars",
            	    	    										lv_globVars_3_0,
            	    	    										"su.nsk.iae.post.PoST.GlobalVarDeclaration");
            	    	    									afterParserOrEnumRuleCall();
            	    	    								

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt1 >= 1 ) break loop1;
            	                EarlyExitException eee =
            	                    new EarlyExitException(1, input);
            	                throw eee;
            	        }
            	        cnt1++;
            	    } while (true);

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getModelAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalPoST.g:170:3: ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) )
            	    {
            	    // InternalPoST.g:170:3: ({...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ ) )
            	    // InternalPoST.g:171:4: {...}? => ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleModel", "getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 3)");
            	    }
            	    // InternalPoST.g:171:99: ( ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+ )
            	    // InternalPoST.g:172:5: ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getModelAccess().getUnorderedGroup(), 3);
            	    				
            	    // InternalPoST.g:175:8: ({...}? => ( (lv_programs_4_0= ruleProgram ) ) )+
            	    int cnt2=0;
            	    loop2:
            	    do {
            	        int alt2=2;
            	        int LA2_0 = input.LA(1);

            	        if ( (LA2_0==51) ) {
            	            int LA2_2 = input.LA(2);

            	            if ( ((true)) ) {
            	                alt2=1;
            	            }


            	        }


            	        switch (alt2) {
            	    	case 1 :
            	    	    // InternalPoST.g:175:9: {...}? => ( (lv_programs_4_0= ruleProgram ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleModel", "true");
            	    	    }
            	    	    // InternalPoST.g:175:18: ( (lv_programs_4_0= ruleProgram ) )
            	    	    // InternalPoST.g:175:19: (lv_programs_4_0= ruleProgram )
            	    	    {
            	    	    // InternalPoST.g:175:19: (lv_programs_4_0= ruleProgram )
            	    	    // InternalPoST.g:176:9: lv_programs_4_0= ruleProgram
            	    	    {

            	    	    									newCompositeNode(grammarAccess.getModelAccess().getProgramsProgramParserRuleCall_3_0());
            	    	    								
            	    	    pushFollow(FOLLOW_3);
            	    	    lv_programs_4_0=ruleProgram();

            	    	    state._fsp--;


            	    	    									if (current==null) {
            	    	    										current = createModelElementForParent(grammarAccess.getModelRule());
            	    	    									}
            	    	    									add(
            	    	    										current,
            	    	    										"programs",
            	    	    										lv_programs_4_0,
            	    	    										"su.nsk.iae.post.PoST.Program");
            	    	    									afterParserOrEnumRuleCall();
            	    	    								

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt2 >= 1 ) break loop2;
            	                EarlyExitException eee =
            	                    new EarlyExitException(2, input);
            	                throw eee;
            	        }
            	        cnt2++;
            	    } while (true);

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getModelAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalPoST.g:198:3: ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) )
            	    {
            	    // InternalPoST.g:198:3: ({...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ ) )
            	    // InternalPoST.g:199:4: {...}? => ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 4) ) {
            	        throw new FailedPredicateException(input, "ruleModel", "getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 4)");
            	    }
            	    // InternalPoST.g:199:99: ( ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+ )
            	    // InternalPoST.g:200:5: ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getModelAccess().getUnorderedGroup(), 4);
            	    				
            	    // InternalPoST.g:203:8: ({...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) ) )+
            	    int cnt3=0;
            	    loop3:
            	    do {
            	        int alt3=2;
            	        int LA3_0 = input.LA(1);

            	        if ( (LA3_0==57) ) {
            	            int LA3_2 = input.LA(2);

            	            if ( ((true)) ) {
            	                alt3=1;
            	            }


            	        }


            	        switch (alt3) {
            	    	case 1 :
            	    	    // InternalPoST.g:203:9: {...}? => ( (lv_fbs_5_0= ruleFunctionBlock ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleModel", "true");
            	    	    }
            	    	    // InternalPoST.g:203:18: ( (lv_fbs_5_0= ruleFunctionBlock ) )
            	    	    // InternalPoST.g:203:19: (lv_fbs_5_0= ruleFunctionBlock )
            	    	    {
            	    	    // InternalPoST.g:203:19: (lv_fbs_5_0= ruleFunctionBlock )
            	    	    // InternalPoST.g:204:9: lv_fbs_5_0= ruleFunctionBlock
            	    	    {

            	    	    									newCompositeNode(grammarAccess.getModelAccess().getFbsFunctionBlockParserRuleCall_4_0());
            	    	    								
            	    	    pushFollow(FOLLOW_3);
            	    	    lv_fbs_5_0=ruleFunctionBlock();

            	    	    state._fsp--;


            	    	    									if (current==null) {
            	    	    										current = createModelElementForParent(grammarAccess.getModelRule());
            	    	    									}
            	    	    									add(
            	    	    										current,
            	    	    										"fbs",
            	    	    										lv_fbs_5_0,
            	    	    										"su.nsk.iae.post.PoST.FunctionBlock");
            	    	    									afterParserOrEnumRuleCall();
            	    	    								

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt3 >= 1 ) break loop3;
            	                EarlyExitException eee =
            	                    new EarlyExitException(3, input);
            	                throw eee;
            	        }
            	        cnt3++;
            	    } while (true);

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getModelAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalPoST.g:226:3: ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) )
            	    {
            	    // InternalPoST.g:226:3: ({...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ ) )
            	    // InternalPoST.g:227:4: {...}? => ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 5) ) {
            	        throw new FailedPredicateException(input, "ruleModel", "getUnorderedGroupHelper().canSelect(grammarAccess.getModelAccess().getUnorderedGroup(), 5)");
            	    }
            	    // InternalPoST.g:227:99: ( ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+ )
            	    // InternalPoST.g:228:5: ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+
            	    {

            	    					getUnorderedGroupHelper().select(grammarAccess.getModelAccess().getUnorderedGroup(), 5);
            	    				
            	    // InternalPoST.g:231:8: ({...}? => ( (lv_funs_6_0= ruleFunction ) ) )+
            	    int cnt4=0;
            	    loop4:
            	    do {
            	        int alt4=2;
            	        int LA4_0 = input.LA(1);

            	        if ( (LA4_0==59) ) {
            	            int LA4_2 = input.LA(2);

            	            if ( ((true)) ) {
            	                alt4=1;
            	            }


            	        }


            	        switch (alt4) {
            	    	case 1 :
            	    	    // InternalPoST.g:231:9: {...}? => ( (lv_funs_6_0= ruleFunction ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        throw new FailedPredicateException(input, "ruleModel", "true");
            	    	    }
            	    	    // InternalPoST.g:231:18: ( (lv_funs_6_0= ruleFunction ) )
            	    	    // InternalPoST.g:231:19: (lv_funs_6_0= ruleFunction )
            	    	    {
            	    	    // InternalPoST.g:231:19: (lv_funs_6_0= ruleFunction )
            	    	    // InternalPoST.g:232:9: lv_funs_6_0= ruleFunction
            	    	    {

            	    	    									newCompositeNode(grammarAccess.getModelAccess().getFunsFunctionParserRuleCall_5_0());
            	    	    								
            	    	    pushFollow(FOLLOW_3);
            	    	    lv_funs_6_0=ruleFunction();

            	    	    state._fsp--;


            	    	    									if (current==null) {
            	    	    										current = createModelElementForParent(grammarAccess.getModelRule());
            	    	    									}
            	    	    									add(
            	    	    										current,
            	    	    										"funs",
            	    	    										lv_funs_6_0,
            	    	    										"su.nsk.iae.post.PoST.Function");
            	    	    									afterParserOrEnumRuleCall();
            	    	    								

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt4 >= 1 ) break loop4;
            	                EarlyExitException eee =
            	                    new EarlyExitException(4, input);
            	                throw eee;
            	        }
            	        cnt4++;
            	    } while (true);

            	     
            	    					getUnorderedGroupHelper().returnFromSelection(grammarAccess.getModelAccess().getUnorderedGroup());
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }

             
            			  getUnorderedGroupHelper().leave(grammarAccess.getModelAccess().getUnorderedGroup());
            			

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleConfiguration"
    // InternalPoST.g:264:1: entryRuleConfiguration returns [EObject current=null] : iv_ruleConfiguration= ruleConfiguration EOF ;
    public final EObject entryRuleConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfiguration = null;


        try {
            // InternalPoST.g:264:54: (iv_ruleConfiguration= ruleConfiguration EOF )
            // InternalPoST.g:265:2: iv_ruleConfiguration= ruleConfiguration EOF
            {
             newCompositeNode(grammarAccess.getConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConfiguration=ruleConfiguration();

            state._fsp--;

             current =iv_ruleConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConfiguration"


    // $ANTLR start "ruleConfiguration"
    // InternalPoST.g:271:1: ruleConfiguration returns [EObject current=null] : (otherlv_0= 'CONFIGURATION' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_confGlobVars_2_0= ruleGlobalVarDeclaration ) ) | ( (lv_resources_3_0= ruleResource ) ) )* otherlv_4= 'END_CONFIGURATION' ) ;
    public final EObject ruleConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_4=null;
        EObject lv_confGlobVars_2_0 = null;

        EObject lv_resources_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:277:2: ( (otherlv_0= 'CONFIGURATION' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_confGlobVars_2_0= ruleGlobalVarDeclaration ) ) | ( (lv_resources_3_0= ruleResource ) ) )* otherlv_4= 'END_CONFIGURATION' ) )
            // InternalPoST.g:278:2: (otherlv_0= 'CONFIGURATION' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_confGlobVars_2_0= ruleGlobalVarDeclaration ) ) | ( (lv_resources_3_0= ruleResource ) ) )* otherlv_4= 'END_CONFIGURATION' )
            {
            // InternalPoST.g:278:2: (otherlv_0= 'CONFIGURATION' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_confGlobVars_2_0= ruleGlobalVarDeclaration ) ) | ( (lv_resources_3_0= ruleResource ) ) )* otherlv_4= 'END_CONFIGURATION' )
            // InternalPoST.g:279:3: otherlv_0= 'CONFIGURATION' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_confGlobVars_2_0= ruleGlobalVarDeclaration ) ) | ( (lv_resources_3_0= ruleResource ) ) )* otherlv_4= 'END_CONFIGURATION'
            {
            otherlv_0=(Token)match(input,40,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getConfigurationAccess().getCONFIGURATIONKeyword_0());
            		
            // InternalPoST.g:283:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:284:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:284:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:285:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_1_0, grammarAccess.getConfigurationAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConfigurationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            // InternalPoST.g:301:3: ( ( (lv_confGlobVars_2_0= ruleGlobalVarDeclaration ) ) | ( (lv_resources_3_0= ruleResource ) ) )*
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==107) ) {
                    alt6=1;
                }
                else if ( (LA6_0==42) ) {
                    alt6=2;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalPoST.g:302:4: ( (lv_confGlobVars_2_0= ruleGlobalVarDeclaration ) )
            	    {
            	    // InternalPoST.g:302:4: ( (lv_confGlobVars_2_0= ruleGlobalVarDeclaration ) )
            	    // InternalPoST.g:303:5: (lv_confGlobVars_2_0= ruleGlobalVarDeclaration )
            	    {
            	    // InternalPoST.g:303:5: (lv_confGlobVars_2_0= ruleGlobalVarDeclaration )
            	    // InternalPoST.g:304:6: lv_confGlobVars_2_0= ruleGlobalVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getConfigurationAccess().getConfGlobVarsGlobalVarDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_5);
            	    lv_confGlobVars_2_0=ruleGlobalVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getConfigurationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"confGlobVars",
            	    							lv_confGlobVars_2_0,
            	    							"su.nsk.iae.post.PoST.GlobalVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalPoST.g:322:4: ( (lv_resources_3_0= ruleResource ) )
            	    {
            	    // InternalPoST.g:322:4: ( (lv_resources_3_0= ruleResource ) )
            	    // InternalPoST.g:323:5: (lv_resources_3_0= ruleResource )
            	    {
            	    // InternalPoST.g:323:5: (lv_resources_3_0= ruleResource )
            	    // InternalPoST.g:324:6: lv_resources_3_0= ruleResource
            	    {

            	    						newCompositeNode(grammarAccess.getConfigurationAccess().getResourcesResourceParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_5);
            	    lv_resources_3_0=ruleResource();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getConfigurationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"resources",
            	    							lv_resources_3_0,
            	    							"su.nsk.iae.post.PoST.Resource");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            otherlv_4=(Token)match(input,41,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getConfigurationAccess().getEND_CONFIGURATIONKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConfiguration"


    // $ANTLR start "entryRuleResource"
    // InternalPoST.g:350:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // InternalPoST.g:350:49: (iv_ruleResource= ruleResource EOF )
            // InternalPoST.g:351:2: iv_ruleResource= ruleResource EOF
            {
             newCompositeNode(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResource=ruleResource();

            state._fsp--;

             current =iv_ruleResource; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleResource"


    // $ANTLR start "ruleResource"
    // InternalPoST.g:357:1: ruleResource returns [EObject current=null] : (otherlv_0= 'RESOURCE' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'ON' ( (lv_type_3_0= RULE_ID ) ) ( (lv_resGlobVars_4_0= ruleGlobalVarDeclaration ) )* ( (lv_resStatement_5_0= ruleSingleResource ) ) otherlv_6= 'END_RESOURCE' ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_type_3_0=null;
        Token otherlv_6=null;
        EObject lv_resGlobVars_4_0 = null;

        EObject lv_resStatement_5_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:363:2: ( (otherlv_0= 'RESOURCE' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'ON' ( (lv_type_3_0= RULE_ID ) ) ( (lv_resGlobVars_4_0= ruleGlobalVarDeclaration ) )* ( (lv_resStatement_5_0= ruleSingleResource ) ) otherlv_6= 'END_RESOURCE' ) )
            // InternalPoST.g:364:2: (otherlv_0= 'RESOURCE' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'ON' ( (lv_type_3_0= RULE_ID ) ) ( (lv_resGlobVars_4_0= ruleGlobalVarDeclaration ) )* ( (lv_resStatement_5_0= ruleSingleResource ) ) otherlv_6= 'END_RESOURCE' )
            {
            // InternalPoST.g:364:2: (otherlv_0= 'RESOURCE' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'ON' ( (lv_type_3_0= RULE_ID ) ) ( (lv_resGlobVars_4_0= ruleGlobalVarDeclaration ) )* ( (lv_resStatement_5_0= ruleSingleResource ) ) otherlv_6= 'END_RESOURCE' )
            // InternalPoST.g:365:3: otherlv_0= 'RESOURCE' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'ON' ( (lv_type_3_0= RULE_ID ) ) ( (lv_resGlobVars_4_0= ruleGlobalVarDeclaration ) )* ( (lv_resStatement_5_0= ruleSingleResource ) ) otherlv_6= 'END_RESOURCE'
            {
            otherlv_0=(Token)match(input,42,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getResourceAccess().getRESOURCEKeyword_0());
            		
            // InternalPoST.g:369:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:370:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:370:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:371:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_name_1_0, grammarAccess.getResourceAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,43,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getResourceAccess().getONKeyword_2());
            		
            // InternalPoST.g:391:3: ( (lv_type_3_0= RULE_ID ) )
            // InternalPoST.g:392:4: (lv_type_3_0= RULE_ID )
            {
            // InternalPoST.g:392:4: (lv_type_3_0= RULE_ID )
            // InternalPoST.g:393:5: lv_type_3_0= RULE_ID
            {
            lv_type_3_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(lv_type_3_0, grammarAccess.getResourceAccess().getTypeIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"type",
            						lv_type_3_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            // InternalPoST.g:409:3: ( (lv_resGlobVars_4_0= ruleGlobalVarDeclaration ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==107) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalPoST.g:410:4: (lv_resGlobVars_4_0= ruleGlobalVarDeclaration )
            	    {
            	    // InternalPoST.g:410:4: (lv_resGlobVars_4_0= ruleGlobalVarDeclaration )
            	    // InternalPoST.g:411:5: lv_resGlobVars_4_0= ruleGlobalVarDeclaration
            	    {

            	    					newCompositeNode(grammarAccess.getResourceAccess().getResGlobVarsGlobalVarDeclarationParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_resGlobVars_4_0=ruleGlobalVarDeclaration();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getResourceRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resGlobVars",
            	    						lv_resGlobVars_4_0,
            	    						"su.nsk.iae.post.PoST.GlobalVarDeclaration");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalPoST.g:428:3: ( (lv_resStatement_5_0= ruleSingleResource ) )
            // InternalPoST.g:429:4: (lv_resStatement_5_0= ruleSingleResource )
            {
            // InternalPoST.g:429:4: (lv_resStatement_5_0= ruleSingleResource )
            // InternalPoST.g:430:5: lv_resStatement_5_0= ruleSingleResource
            {

            					newCompositeNode(grammarAccess.getResourceAccess().getResStatementSingleResourceParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_8);
            lv_resStatement_5_0=ruleSingleResource();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getResourceRule());
            					}
            					set(
            						current,
            						"resStatement",
            						lv_resStatement_5_0,
            						"su.nsk.iae.post.PoST.SingleResource");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,44,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getResourceAccess().getEND_RESOURCEKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleResource"


    // $ANTLR start "entryRuleSingleResource"
    // InternalPoST.g:455:1: entryRuleSingleResource returns [EObject current=null] : iv_ruleSingleResource= ruleSingleResource EOF ;
    public final EObject entryRuleSingleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingleResource = null;


        try {
            // InternalPoST.g:455:55: (iv_ruleSingleResource= ruleSingleResource EOF )
            // InternalPoST.g:456:2: iv_ruleSingleResource= ruleSingleResource EOF
            {
             newCompositeNode(grammarAccess.getSingleResourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSingleResource=ruleSingleResource();

            state._fsp--;

             current =iv_ruleSingleResource; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSingleResource"


    // $ANTLR start "ruleSingleResource"
    // InternalPoST.g:462:1: ruleSingleResource returns [EObject current=null] : ( () ( ( ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';' ) | ( ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';' ) )* ) ;
    public final EObject ruleSingleResource() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_tasks_1_0 = null;

        EObject lv_programConfs_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:468:2: ( ( () ( ( ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';' ) | ( ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';' ) )* ) )
            // InternalPoST.g:469:2: ( () ( ( ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';' ) | ( ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';' ) )* )
            {
            // InternalPoST.g:469:2: ( () ( ( ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';' ) | ( ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';' ) )* )
            // InternalPoST.g:470:3: () ( ( ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';' ) | ( ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';' ) )*
            {
            // InternalPoST.g:470:3: ()
            // InternalPoST.g:471:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSingleResourceAccess().getSingleResourceAction_0(),
            					current);
            			

            }

            // InternalPoST.g:477:3: ( ( ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';' ) | ( ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';' ) )*
            loop8:
            do {
                int alt8=3;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==46) ) {
                    alt8=1;
                }
                else if ( (LA8_0==51) ) {
                    alt8=2;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalPoST.g:478:4: ( ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';' )
            	    {
            	    // InternalPoST.g:478:4: ( ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';' )
            	    // InternalPoST.g:479:5: ( (lv_tasks_1_0= ruleTask ) ) otherlv_2= ';'
            	    {
            	    // InternalPoST.g:479:5: ( (lv_tasks_1_0= ruleTask ) )
            	    // InternalPoST.g:480:6: (lv_tasks_1_0= ruleTask )
            	    {
            	    // InternalPoST.g:480:6: (lv_tasks_1_0= ruleTask )
            	    // InternalPoST.g:481:7: lv_tasks_1_0= ruleTask
            	    {

            	    							newCompositeNode(grammarAccess.getSingleResourceAccess().getTasksTaskParserRuleCall_1_0_0_0());
            	    						
            	    pushFollow(FOLLOW_9);
            	    lv_tasks_1_0=ruleTask();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getSingleResourceRule());
            	    							}
            	    							add(
            	    								current,
            	    								"tasks",
            	    								lv_tasks_1_0,
            	    								"su.nsk.iae.post.PoST.Task");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }

            	    otherlv_2=(Token)match(input,45,FOLLOW_10); 

            	    					newLeafNode(otherlv_2, grammarAccess.getSingleResourceAccess().getSemicolonKeyword_1_0_1());
            	    				

            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalPoST.g:504:4: ( ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';' )
            	    {
            	    // InternalPoST.g:504:4: ( ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';' )
            	    // InternalPoST.g:505:5: ( (lv_programConfs_3_0= ruleProgramConfiguration ) ) otherlv_4= ';'
            	    {
            	    // InternalPoST.g:505:5: ( (lv_programConfs_3_0= ruleProgramConfiguration ) )
            	    // InternalPoST.g:506:6: (lv_programConfs_3_0= ruleProgramConfiguration )
            	    {
            	    // InternalPoST.g:506:6: (lv_programConfs_3_0= ruleProgramConfiguration )
            	    // InternalPoST.g:507:7: lv_programConfs_3_0= ruleProgramConfiguration
            	    {

            	    							newCompositeNode(grammarAccess.getSingleResourceAccess().getProgramConfsProgramConfigurationParserRuleCall_1_1_0_0());
            	    						
            	    pushFollow(FOLLOW_9);
            	    lv_programConfs_3_0=ruleProgramConfiguration();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getSingleResourceRule());
            	    							}
            	    							add(
            	    								current,
            	    								"programConfs",
            	    								lv_programConfs_3_0,
            	    								"su.nsk.iae.post.PoST.ProgramConfiguration");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }

            	    otherlv_4=(Token)match(input,45,FOLLOW_10); 

            	    					newLeafNode(otherlv_4, grammarAccess.getSingleResourceAccess().getSemicolonKeyword_1_1_1());
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSingleResource"


    // $ANTLR start "entryRuleTask"
    // InternalPoST.g:534:1: entryRuleTask returns [EObject current=null] : iv_ruleTask= ruleTask EOF ;
    public final EObject entryRuleTask() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTask = null;


        try {
            // InternalPoST.g:534:45: (iv_ruleTask= ruleTask EOF )
            // InternalPoST.g:535:2: iv_ruleTask= ruleTask EOF
            {
             newCompositeNode(grammarAccess.getTaskRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTask=ruleTask();

            state._fsp--;

             current =iv_ruleTask; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTask"


    // $ANTLR start "ruleTask"
    // InternalPoST.g:541:1: ruleTask returns [EObject current=null] : (otherlv_0= 'TASK' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_init_3_0= ruleTaskInitialization ) ) otherlv_4= ')' ) ;
    public final EObject ruleTask() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_init_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:547:2: ( (otherlv_0= 'TASK' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_init_3_0= ruleTaskInitialization ) ) otherlv_4= ')' ) )
            // InternalPoST.g:548:2: (otherlv_0= 'TASK' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_init_3_0= ruleTaskInitialization ) ) otherlv_4= ')' )
            {
            // InternalPoST.g:548:2: (otherlv_0= 'TASK' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_init_3_0= ruleTaskInitialization ) ) otherlv_4= ')' )
            // InternalPoST.g:549:3: otherlv_0= 'TASK' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_init_3_0= ruleTaskInitialization ) ) otherlv_4= ')'
            {
            otherlv_0=(Token)match(input,46,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getTaskAccess().getTASKKeyword_0());
            		
            // InternalPoST.g:553:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:554:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:554:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:555:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            					newLeafNode(lv_name_1_0, grammarAccess.getTaskAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTaskRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,47,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getTaskAccess().getLeftParenthesisKeyword_2());
            		
            // InternalPoST.g:575:3: ( (lv_init_3_0= ruleTaskInitialization ) )
            // InternalPoST.g:576:4: (lv_init_3_0= ruleTaskInitialization )
            {
            // InternalPoST.g:576:4: (lv_init_3_0= ruleTaskInitialization )
            // InternalPoST.g:577:5: lv_init_3_0= ruleTaskInitialization
            {

            					newCompositeNode(grammarAccess.getTaskAccess().getInitTaskInitializationParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_13);
            lv_init_3_0=ruleTaskInitialization();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTaskRule());
            					}
            					set(
            						current,
            						"init",
            						lv_init_3_0,
            						"su.nsk.iae.post.PoST.TaskInitialization");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,48,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getTaskAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTask"


    // $ANTLR start "entryRuleTaskInitialization"
    // InternalPoST.g:602:1: entryRuleTaskInitialization returns [EObject current=null] : iv_ruleTaskInitialization= ruleTaskInitialization EOF ;
    public final EObject entryRuleTaskInitialization() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTaskInitialization = null;


        try {
            // InternalPoST.g:602:59: (iv_ruleTaskInitialization= ruleTaskInitialization EOF )
            // InternalPoST.g:603:2: iv_ruleTaskInitialization= ruleTaskInitialization EOF
            {
             newCompositeNode(grammarAccess.getTaskInitializationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTaskInitialization=ruleTaskInitialization();

            state._fsp--;

             current =iv_ruleTaskInitialization; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTaskInitialization"


    // $ANTLR start "ruleTaskInitialization"
    // InternalPoST.g:609:1: ruleTaskInitialization returns [EObject current=null] : ( (this_SINGLE_DECLARATION_0= RULE_SINGLE_DECLARATION otherlv_1= ':=' ( (lv_single_2_0= ruleConstant ) ) ) | ( (this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) ) ) otherlv_6= ',' this_PRIORITY_DECLARATION_7= RULE_PRIORITY_DECLARATION otherlv_8= ':=' ( (lv_priority_9_0= RULE_INTEGER ) ) ) ) ;
    public final EObject ruleTaskInitialization() throws RecognitionException {
        EObject current = null;

        Token this_SINGLE_DECLARATION_0=null;
        Token otherlv_1=null;
        Token this_INTERVAL_DECLARATION_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token this_PRIORITY_DECLARATION_7=null;
        Token otherlv_8=null;
        Token lv_priority_9_0=null;
        EObject lv_single_2_0 = null;

        EObject lv_interval_5_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:615:2: ( ( (this_SINGLE_DECLARATION_0= RULE_SINGLE_DECLARATION otherlv_1= ':=' ( (lv_single_2_0= ruleConstant ) ) ) | ( (this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) ) ) otherlv_6= ',' this_PRIORITY_DECLARATION_7= RULE_PRIORITY_DECLARATION otherlv_8= ':=' ( (lv_priority_9_0= RULE_INTEGER ) ) ) ) )
            // InternalPoST.g:616:2: ( (this_SINGLE_DECLARATION_0= RULE_SINGLE_DECLARATION otherlv_1= ':=' ( (lv_single_2_0= ruleConstant ) ) ) | ( (this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) ) ) otherlv_6= ',' this_PRIORITY_DECLARATION_7= RULE_PRIORITY_DECLARATION otherlv_8= ':=' ( (lv_priority_9_0= RULE_INTEGER ) ) ) )
            {
            // InternalPoST.g:616:2: ( (this_SINGLE_DECLARATION_0= RULE_SINGLE_DECLARATION otherlv_1= ':=' ( (lv_single_2_0= ruleConstant ) ) ) | ( (this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) ) ) otherlv_6= ',' this_PRIORITY_DECLARATION_7= RULE_PRIORITY_DECLARATION otherlv_8= ':=' ( (lv_priority_9_0= RULE_INTEGER ) ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_SINGLE_DECLARATION) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_INTERVAL_DECLARATION) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalPoST.g:617:3: (this_SINGLE_DECLARATION_0= RULE_SINGLE_DECLARATION otherlv_1= ':=' ( (lv_single_2_0= ruleConstant ) ) )
                    {
                    // InternalPoST.g:617:3: (this_SINGLE_DECLARATION_0= RULE_SINGLE_DECLARATION otherlv_1= ':=' ( (lv_single_2_0= ruleConstant ) ) )
                    // InternalPoST.g:618:4: this_SINGLE_DECLARATION_0= RULE_SINGLE_DECLARATION otherlv_1= ':=' ( (lv_single_2_0= ruleConstant ) )
                    {
                    this_SINGLE_DECLARATION_0=(Token)match(input,RULE_SINGLE_DECLARATION,FOLLOW_14); 

                    				newLeafNode(this_SINGLE_DECLARATION_0, grammarAccess.getTaskInitializationAccess().getSINGLE_DECLARATIONTerminalRuleCall_0_0());
                    			
                    otherlv_1=(Token)match(input,49,FOLLOW_15); 

                    				newLeafNode(otherlv_1, grammarAccess.getTaskInitializationAccess().getColonEqualsSignKeyword_0_1());
                    			
                    // InternalPoST.g:626:4: ( (lv_single_2_0= ruleConstant ) )
                    // InternalPoST.g:627:5: (lv_single_2_0= ruleConstant )
                    {
                    // InternalPoST.g:627:5: (lv_single_2_0= ruleConstant )
                    // InternalPoST.g:628:6: lv_single_2_0= ruleConstant
                    {

                    						newCompositeNode(grammarAccess.getTaskInitializationAccess().getSingleConstantParserRuleCall_0_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_single_2_0=ruleConstant();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getTaskInitializationRule());
                    						}
                    						set(
                    							current,
                    							"single",
                    							lv_single_2_0,
                    							"su.nsk.iae.post.PoST.Constant");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:647:3: ( (this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) ) ) otherlv_6= ',' this_PRIORITY_DECLARATION_7= RULE_PRIORITY_DECLARATION otherlv_8= ':=' ( (lv_priority_9_0= RULE_INTEGER ) ) )
                    {
                    // InternalPoST.g:647:3: ( (this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) ) ) otherlv_6= ',' this_PRIORITY_DECLARATION_7= RULE_PRIORITY_DECLARATION otherlv_8= ':=' ( (lv_priority_9_0= RULE_INTEGER ) ) )
                    // InternalPoST.g:648:4: (this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) ) ) otherlv_6= ',' this_PRIORITY_DECLARATION_7= RULE_PRIORITY_DECLARATION otherlv_8= ':=' ( (lv_priority_9_0= RULE_INTEGER ) )
                    {
                    // InternalPoST.g:648:4: (this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) ) )
                    // InternalPoST.g:649:5: this_INTERVAL_DECLARATION_3= RULE_INTERVAL_DECLARATION otherlv_4= ':=' ( (lv_interval_5_0= ruleConstant ) )
                    {
                    this_INTERVAL_DECLARATION_3=(Token)match(input,RULE_INTERVAL_DECLARATION,FOLLOW_14); 

                    					newLeafNode(this_INTERVAL_DECLARATION_3, grammarAccess.getTaskInitializationAccess().getINTERVAL_DECLARATIONTerminalRuleCall_1_0_0());
                    				
                    otherlv_4=(Token)match(input,49,FOLLOW_15); 

                    					newLeafNode(otherlv_4, grammarAccess.getTaskInitializationAccess().getColonEqualsSignKeyword_1_0_1());
                    				
                    // InternalPoST.g:657:5: ( (lv_interval_5_0= ruleConstant ) )
                    // InternalPoST.g:658:6: (lv_interval_5_0= ruleConstant )
                    {
                    // InternalPoST.g:658:6: (lv_interval_5_0= ruleConstant )
                    // InternalPoST.g:659:7: lv_interval_5_0= ruleConstant
                    {

                    							newCompositeNode(grammarAccess.getTaskInitializationAccess().getIntervalConstantParserRuleCall_1_0_2_0());
                    						
                    pushFollow(FOLLOW_16);
                    lv_interval_5_0=ruleConstant();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getTaskInitializationRule());
                    							}
                    							set(
                    								current,
                    								"interval",
                    								lv_interval_5_0,
                    								"su.nsk.iae.post.PoST.Constant");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }


                    }

                    otherlv_6=(Token)match(input,50,FOLLOW_17); 

                    				newLeafNode(otherlv_6, grammarAccess.getTaskInitializationAccess().getCommaKeyword_1_1());
                    			
                    this_PRIORITY_DECLARATION_7=(Token)match(input,RULE_PRIORITY_DECLARATION,FOLLOW_14); 

                    				newLeafNode(this_PRIORITY_DECLARATION_7, grammarAccess.getTaskInitializationAccess().getPRIORITY_DECLARATIONTerminalRuleCall_1_2());
                    			
                    otherlv_8=(Token)match(input,49,FOLLOW_18); 

                    				newLeafNode(otherlv_8, grammarAccess.getTaskInitializationAccess().getColonEqualsSignKeyword_1_3());
                    			
                    // InternalPoST.g:689:4: ( (lv_priority_9_0= RULE_INTEGER ) )
                    // InternalPoST.g:690:5: (lv_priority_9_0= RULE_INTEGER )
                    {
                    // InternalPoST.g:690:5: (lv_priority_9_0= RULE_INTEGER )
                    // InternalPoST.g:691:6: lv_priority_9_0= RULE_INTEGER
                    {
                    lv_priority_9_0=(Token)match(input,RULE_INTEGER,FOLLOW_2); 

                    						newLeafNode(lv_priority_9_0, grammarAccess.getTaskInitializationAccess().getPriorityINTEGERTerminalRuleCall_1_4_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getTaskInitializationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"priority",
                    							lv_priority_9_0,
                    							"su.nsk.iae.post.PoST.INTEGER");
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTaskInitialization"


    // $ANTLR start "entryRuleProgramConfiguration"
    // InternalPoST.g:712:1: entryRuleProgramConfiguration returns [EObject current=null] : iv_ruleProgramConfiguration= ruleProgramConfiguration EOF ;
    public final EObject entryRuleProgramConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgramConfiguration = null;


        try {
            // InternalPoST.g:712:61: (iv_ruleProgramConfiguration= ruleProgramConfiguration EOF )
            // InternalPoST.g:713:2: iv_ruleProgramConfiguration= ruleProgramConfiguration EOF
            {
             newCompositeNode(grammarAccess.getProgramConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProgramConfiguration=ruleProgramConfiguration();

            state._fsp--;

             current =iv_ruleProgramConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProgramConfiguration"


    // $ANTLR start "ruleProgramConfiguration"
    // InternalPoST.g:719:1: ruleProgramConfiguration returns [EObject current=null] : (otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'WITH' ( (otherlv_3= RULE_ID ) ) )? otherlv_4= ':' ( (otherlv_5= RULE_ID ) ) (otherlv_6= '(' ( (lv_args_7_0= ruleProgramConfElements ) ) otherlv_8= ')' )? ) ;
    public final EObject ruleProgramConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_args_7_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:725:2: ( (otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'WITH' ( (otherlv_3= RULE_ID ) ) )? otherlv_4= ':' ( (otherlv_5= RULE_ID ) ) (otherlv_6= '(' ( (lv_args_7_0= ruleProgramConfElements ) ) otherlv_8= ')' )? ) )
            // InternalPoST.g:726:2: (otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'WITH' ( (otherlv_3= RULE_ID ) ) )? otherlv_4= ':' ( (otherlv_5= RULE_ID ) ) (otherlv_6= '(' ( (lv_args_7_0= ruleProgramConfElements ) ) otherlv_8= ')' )? )
            {
            // InternalPoST.g:726:2: (otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'WITH' ( (otherlv_3= RULE_ID ) ) )? otherlv_4= ':' ( (otherlv_5= RULE_ID ) ) (otherlv_6= '(' ( (lv_args_7_0= ruleProgramConfElements ) ) otherlv_8= ')' )? )
            // InternalPoST.g:727:3: otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= 'WITH' ( (otherlv_3= RULE_ID ) ) )? otherlv_4= ':' ( (otherlv_5= RULE_ID ) ) (otherlv_6= '(' ( (lv_args_7_0= ruleProgramConfElements ) ) otherlv_8= ')' )?
            {
            otherlv_0=(Token)match(input,51,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getProgramConfigurationAccess().getPROGRAMKeyword_0());
            		
            // InternalPoST.g:731:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:732:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:732:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:733:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(lv_name_1_0, grammarAccess.getProgramConfigurationAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProgramConfigurationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            // InternalPoST.g:749:3: (otherlv_2= 'WITH' ( (otherlv_3= RULE_ID ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==52) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalPoST.g:750:4: otherlv_2= 'WITH' ( (otherlv_3= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,52,FOLLOW_4); 

                    				newLeafNode(otherlv_2, grammarAccess.getProgramConfigurationAccess().getWITHKeyword_2_0());
                    			
                    // InternalPoST.g:754:4: ( (otherlv_3= RULE_ID ) )
                    // InternalPoST.g:755:5: (otherlv_3= RULE_ID )
                    {
                    // InternalPoST.g:755:5: (otherlv_3= RULE_ID )
                    // InternalPoST.g:756:6: otherlv_3= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getProgramConfigurationRule());
                    						}
                    					
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_20); 

                    						newLeafNode(otherlv_3, grammarAccess.getProgramConfigurationAccess().getTaskTaskCrossReference_2_1_0());
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,53,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getProgramConfigurationAccess().getColonKeyword_3());
            		
            // InternalPoST.g:772:3: ( (otherlv_5= RULE_ID ) )
            // InternalPoST.g:773:4: (otherlv_5= RULE_ID )
            {
            // InternalPoST.g:773:4: (otherlv_5= RULE_ID )
            // InternalPoST.g:774:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProgramConfigurationRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(otherlv_5, grammarAccess.getProgramConfigurationAccess().getProgramProgramCrossReference_4_0());
            				

            }


            }

            // InternalPoST.g:785:3: (otherlv_6= '(' ( (lv_args_7_0= ruleProgramConfElements ) ) otherlv_8= ')' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==47) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalPoST.g:786:4: otherlv_6= '(' ( (lv_args_7_0= ruleProgramConfElements ) ) otherlv_8= ')'
                    {
                    otherlv_6=(Token)match(input,47,FOLLOW_22); 

                    				newLeafNode(otherlv_6, grammarAccess.getProgramConfigurationAccess().getLeftParenthesisKeyword_5_0());
                    			
                    // InternalPoST.g:790:4: ( (lv_args_7_0= ruleProgramConfElements ) )
                    // InternalPoST.g:791:5: (lv_args_7_0= ruleProgramConfElements )
                    {
                    // InternalPoST.g:791:5: (lv_args_7_0= ruleProgramConfElements )
                    // InternalPoST.g:792:6: lv_args_7_0= ruleProgramConfElements
                    {

                    						newCompositeNode(grammarAccess.getProgramConfigurationAccess().getArgsProgramConfElementsParserRuleCall_5_1_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_args_7_0=ruleProgramConfElements();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getProgramConfigurationRule());
                    						}
                    						set(
                    							current,
                    							"args",
                    							lv_args_7_0,
                    							"su.nsk.iae.post.PoST.ProgramConfElements");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_8=(Token)match(input,48,FOLLOW_2); 

                    				newLeafNode(otherlv_8, grammarAccess.getProgramConfigurationAccess().getRightParenthesisKeyword_5_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProgramConfiguration"


    // $ANTLR start "entryRuleProgramConfElements"
    // InternalPoST.g:818:1: entryRuleProgramConfElements returns [EObject current=null] : iv_ruleProgramConfElements= ruleProgramConfElements EOF ;
    public final EObject entryRuleProgramConfElements() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgramConfElements = null;


        try {
            // InternalPoST.g:818:60: (iv_ruleProgramConfElements= ruleProgramConfElements EOF )
            // InternalPoST.g:819:2: iv_ruleProgramConfElements= ruleProgramConfElements EOF
            {
             newCompositeNode(grammarAccess.getProgramConfElementsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProgramConfElements=ruleProgramConfElements();

            state._fsp--;

             current =iv_ruleProgramConfElements; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProgramConfElements"


    // $ANTLR start "ruleProgramConfElements"
    // InternalPoST.g:825:1: ruleProgramConfElements returns [EObject current=null] : ( ( (lv_elements_0_0= ruleProgramConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleProgramConfElement ) ) )* ) ;
    public final EObject ruleProgramConfElements() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_elements_0_0 = null;

        EObject lv_elements_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:831:2: ( ( ( (lv_elements_0_0= ruleProgramConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleProgramConfElement ) ) )* ) )
            // InternalPoST.g:832:2: ( ( (lv_elements_0_0= ruleProgramConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleProgramConfElement ) ) )* )
            {
            // InternalPoST.g:832:2: ( ( (lv_elements_0_0= ruleProgramConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleProgramConfElement ) ) )* )
            // InternalPoST.g:833:3: ( (lv_elements_0_0= ruleProgramConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleProgramConfElement ) ) )*
            {
            // InternalPoST.g:833:3: ( (lv_elements_0_0= ruleProgramConfElement ) )
            // InternalPoST.g:834:4: (lv_elements_0_0= ruleProgramConfElement )
            {
            // InternalPoST.g:834:4: (lv_elements_0_0= ruleProgramConfElement )
            // InternalPoST.g:835:5: lv_elements_0_0= ruleProgramConfElement
            {

            					newCompositeNode(grammarAccess.getProgramConfElementsAccess().getElementsProgramConfElementParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_23);
            lv_elements_0_0=ruleProgramConfElement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProgramConfElementsRule());
            					}
            					add(
            						current,
            						"elements",
            						lv_elements_0_0,
            						"su.nsk.iae.post.PoST.ProgramConfElement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:852:3: (otherlv_1= ',' ( (lv_elements_2_0= ruleProgramConfElement ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==50) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalPoST.g:853:4: otherlv_1= ',' ( (lv_elements_2_0= ruleProgramConfElement ) )
            	    {
            	    otherlv_1=(Token)match(input,50,FOLLOW_22); 

            	    				newLeafNode(otherlv_1, grammarAccess.getProgramConfElementsAccess().getCommaKeyword_1_0());
            	    			
            	    // InternalPoST.g:857:4: ( (lv_elements_2_0= ruleProgramConfElement ) )
            	    // InternalPoST.g:858:5: (lv_elements_2_0= ruleProgramConfElement )
            	    {
            	    // InternalPoST.g:858:5: (lv_elements_2_0= ruleProgramConfElement )
            	    // InternalPoST.g:859:6: lv_elements_2_0= ruleProgramConfElement
            	    {

            	    						newCompositeNode(grammarAccess.getProgramConfElementsAccess().getElementsProgramConfElementParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_23);
            	    lv_elements_2_0=ruleProgramConfElement();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProgramConfElementsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_2_0,
            	    							"su.nsk.iae.post.PoST.ProgramConfElement");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProgramConfElements"


    // $ANTLR start "entryRuleProgramConfElement"
    // InternalPoST.g:881:1: entryRuleProgramConfElement returns [EObject current=null] : iv_ruleProgramConfElement= ruleProgramConfElement EOF ;
    public final EObject entryRuleProgramConfElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgramConfElement = null;


        try {
            // InternalPoST.g:881:59: (iv_ruleProgramConfElement= ruleProgramConfElement EOF )
            // InternalPoST.g:882:2: iv_ruleProgramConfElement= ruleProgramConfElement EOF
            {
             newCompositeNode(grammarAccess.getProgramConfElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProgramConfElement=ruleProgramConfElement();

            state._fsp--;

             current =iv_ruleProgramConfElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProgramConfElement"


    // $ANTLR start "ruleProgramConfElement"
    // InternalPoST.g:888:1: ruleProgramConfElement returns [EObject current=null] : (this_AttachVariableConfElement_0= ruleAttachVariableConfElement | this_TemplateProcessConfElement_1= ruleTemplateProcessConfElement ) ;
    public final EObject ruleProgramConfElement() throws RecognitionException {
        EObject current = null;

        EObject this_AttachVariableConfElement_0 = null;

        EObject this_TemplateProcessConfElement_1 = null;



        	enterRule();

        try {
            // InternalPoST.g:894:2: ( (this_AttachVariableConfElement_0= ruleAttachVariableConfElement | this_TemplateProcessConfElement_1= ruleTemplateProcessConfElement ) )
            // InternalPoST.g:895:2: (this_AttachVariableConfElement_0= ruleAttachVariableConfElement | this_TemplateProcessConfElement_1= ruleTemplateProcessConfElement )
            {
            // InternalPoST.g:895:2: (this_AttachVariableConfElement_0= ruleAttachVariableConfElement | this_TemplateProcessConfElement_1= ruleTemplateProcessConfElement )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                alt13=1;
            }
            else if ( (LA13_0==54) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalPoST.g:896:3: this_AttachVariableConfElement_0= ruleAttachVariableConfElement
                    {

                    			newCompositeNode(grammarAccess.getProgramConfElementAccess().getAttachVariableConfElementParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_AttachVariableConfElement_0=ruleAttachVariableConfElement();

                    state._fsp--;


                    			current = this_AttachVariableConfElement_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:905:3: this_TemplateProcessConfElement_1= ruleTemplateProcessConfElement
                    {

                    			newCompositeNode(grammarAccess.getProgramConfElementAccess().getTemplateProcessConfElementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_TemplateProcessConfElement_1=ruleTemplateProcessConfElement();

                    state._fsp--;


                    			current = this_TemplateProcessConfElement_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProgramConfElement"


    // $ANTLR start "entryRuleAttachVariableConfElement"
    // InternalPoST.g:917:1: entryRuleAttachVariableConfElement returns [EObject current=null] : iv_ruleAttachVariableConfElement= ruleAttachVariableConfElement EOF ;
    public final EObject entryRuleAttachVariableConfElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttachVariableConfElement = null;


        try {
            // InternalPoST.g:917:66: (iv_ruleAttachVariableConfElement= ruleAttachVariableConfElement EOF )
            // InternalPoST.g:918:2: iv_ruleAttachVariableConfElement= ruleAttachVariableConfElement EOF
            {
             newCompositeNode(grammarAccess.getAttachVariableConfElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAttachVariableConfElement=ruleAttachVariableConfElement();

            state._fsp--;

             current =iv_ruleAttachVariableConfElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttachVariableConfElement"


    // $ANTLR start "ruleAttachVariableConfElement"
    // InternalPoST.g:924:1: ruleAttachVariableConfElement returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) ) ) ;
    public final EObject ruleAttachVariableConfElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Enumerator lv_assig_1_0 = null;

        EObject lv_const_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:930:2: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) ) ) )
            // InternalPoST.g:931:2: ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) ) )
            {
            // InternalPoST.g:931:2: ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) ) )
            // InternalPoST.g:932:3: ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) )
            {
            // InternalPoST.g:932:3: ( (otherlv_0= RULE_ID ) )
            // InternalPoST.g:933:4: (otherlv_0= RULE_ID )
            {
            // InternalPoST.g:933:4: (otherlv_0= RULE_ID )
            // InternalPoST.g:934:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAttachVariableConfElementRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_24); 

            					newLeafNode(otherlv_0, grammarAccess.getAttachVariableConfElementAccess().getProgramVarSymbolicVariableCrossReference_0_0());
            				

            }


            }

            // InternalPoST.g:945:3: ( (lv_assig_1_0= ruleAssignmentType ) )
            // InternalPoST.g:946:4: (lv_assig_1_0= ruleAssignmentType )
            {
            // InternalPoST.g:946:4: (lv_assig_1_0= ruleAssignmentType )
            // InternalPoST.g:947:5: lv_assig_1_0= ruleAssignmentType
            {

            					newCompositeNode(grammarAccess.getAttachVariableConfElementAccess().getAssigAssignmentTypeEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_25);
            lv_assig_1_0=ruleAssignmentType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAttachVariableConfElementRule());
            					}
            					set(
            						current,
            						"assig",
            						lv_assig_1_0,
            						"su.nsk.iae.post.PoST.AssignmentType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:964:3: ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_ID) ) {
                alt14=1;
            }
            else if ( (LA14_0==RULE_INTEGER||LA14_0==RULE_TIME_PREF_LITERAL||(LA14_0>=RULE_REAL_TYPE_NAME && LA14_0<=RULE_REAL)||LA14_0==114) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // InternalPoST.g:965:4: ( (otherlv_2= RULE_ID ) )
                    {
                    // InternalPoST.g:965:4: ( (otherlv_2= RULE_ID ) )
                    // InternalPoST.g:966:5: (otherlv_2= RULE_ID )
                    {
                    // InternalPoST.g:966:5: (otherlv_2= RULE_ID )
                    // InternalPoST.g:967:6: otherlv_2= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttachVariableConfElementRule());
                    						}
                    					
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(otherlv_2, grammarAccess.getAttachVariableConfElementAccess().getAttVarSymbolicVariableCrossReference_2_0_0());
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:979:4: ( (lv_const_3_0= ruleConstant ) )
                    {
                    // InternalPoST.g:979:4: ( (lv_const_3_0= ruleConstant ) )
                    // InternalPoST.g:980:5: (lv_const_3_0= ruleConstant )
                    {
                    // InternalPoST.g:980:5: (lv_const_3_0= ruleConstant )
                    // InternalPoST.g:981:6: lv_const_3_0= ruleConstant
                    {

                    						newCompositeNode(grammarAccess.getAttachVariableConfElementAccess().getConstConstantParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_const_3_0=ruleConstant();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAttachVariableConfElementRule());
                    						}
                    						set(
                    							current,
                    							"const",
                    							lv_const_3_0,
                    							"su.nsk.iae.post.PoST.Constant");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttachVariableConfElement"


    // $ANTLR start "entryRuleTemplateProcessConfElement"
    // InternalPoST.g:1003:1: entryRuleTemplateProcessConfElement returns [EObject current=null] : iv_ruleTemplateProcessConfElement= ruleTemplateProcessConfElement EOF ;
    public final EObject entryRuleTemplateProcessConfElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateProcessConfElement = null;


        try {
            // InternalPoST.g:1003:67: (iv_ruleTemplateProcessConfElement= ruleTemplateProcessConfElement EOF )
            // InternalPoST.g:1004:2: iv_ruleTemplateProcessConfElement= ruleTemplateProcessConfElement EOF
            {
             newCompositeNode(grammarAccess.getTemplateProcessConfElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTemplateProcessConfElement=ruleTemplateProcessConfElement();

            state._fsp--;

             current =iv_ruleTemplateProcessConfElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTemplateProcessConfElement"


    // $ANTLR start "ruleTemplateProcessConfElement"
    // InternalPoST.g:1010:1: ruleTemplateProcessConfElement returns [EObject current=null] : (otherlv_0= 'PROCESS' ( (lv_active_1_0= 'ACTIVE' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (otherlv_4= RULE_ID ) ) (otherlv_5= '(' ( (lv_args_6_0= ruleTemplateProcessElements ) ) otherlv_7= ')' )? ) ;
    public final EObject ruleTemplateProcessConfElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_active_1_0=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_args_6_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:1016:2: ( (otherlv_0= 'PROCESS' ( (lv_active_1_0= 'ACTIVE' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (otherlv_4= RULE_ID ) ) (otherlv_5= '(' ( (lv_args_6_0= ruleTemplateProcessElements ) ) otherlv_7= ')' )? ) )
            // InternalPoST.g:1017:2: (otherlv_0= 'PROCESS' ( (lv_active_1_0= 'ACTIVE' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (otherlv_4= RULE_ID ) ) (otherlv_5= '(' ( (lv_args_6_0= ruleTemplateProcessElements ) ) otherlv_7= ')' )? )
            {
            // InternalPoST.g:1017:2: (otherlv_0= 'PROCESS' ( (lv_active_1_0= 'ACTIVE' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (otherlv_4= RULE_ID ) ) (otherlv_5= '(' ( (lv_args_6_0= ruleTemplateProcessElements ) ) otherlv_7= ')' )? )
            // InternalPoST.g:1018:3: otherlv_0= 'PROCESS' ( (lv_active_1_0= 'ACTIVE' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (otherlv_4= RULE_ID ) ) (otherlv_5= '(' ( (lv_args_6_0= ruleTemplateProcessElements ) ) otherlv_7= ')' )?
            {
            otherlv_0=(Token)match(input,54,FOLLOW_26); 

            			newLeafNode(otherlv_0, grammarAccess.getTemplateProcessConfElementAccess().getPROCESSKeyword_0());
            		
            // InternalPoST.g:1022:3: ( (lv_active_1_0= 'ACTIVE' ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==55) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalPoST.g:1023:4: (lv_active_1_0= 'ACTIVE' )
                    {
                    // InternalPoST.g:1023:4: (lv_active_1_0= 'ACTIVE' )
                    // InternalPoST.g:1024:5: lv_active_1_0= 'ACTIVE'
                    {
                    lv_active_1_0=(Token)match(input,55,FOLLOW_4); 

                    					newLeafNode(lv_active_1_0, grammarAccess.getTemplateProcessConfElementAccess().getActiveACTIVEKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getTemplateProcessConfElementRule());
                    					}
                    					setWithLastConsumed(current, "active", lv_active_1_0 != null, "ACTIVE");
                    				

                    }


                    }
                    break;

            }

            // InternalPoST.g:1036:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalPoST.g:1037:4: (lv_name_2_0= RULE_ID )
            {
            // InternalPoST.g:1037:4: (lv_name_2_0= RULE_ID )
            // InternalPoST.g:1038:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_20); 

            					newLeafNode(lv_name_2_0, grammarAccess.getTemplateProcessConfElementAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTemplateProcessConfElementRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,53,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getTemplateProcessConfElementAccess().getColonKeyword_3());
            		
            // InternalPoST.g:1058:3: ( (otherlv_4= RULE_ID ) )
            // InternalPoST.g:1059:4: (otherlv_4= RULE_ID )
            {
            // InternalPoST.g:1059:4: (otherlv_4= RULE_ID )
            // InternalPoST.g:1060:5: otherlv_4= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTemplateProcessConfElementRule());
            					}
            				
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(otherlv_4, grammarAccess.getTemplateProcessConfElementAccess().getProcessProcessCrossReference_4_0());
            				

            }


            }

            // InternalPoST.g:1071:3: (otherlv_5= '(' ( (lv_args_6_0= ruleTemplateProcessElements ) ) otherlv_7= ')' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==47) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalPoST.g:1072:4: otherlv_5= '(' ( (lv_args_6_0= ruleTemplateProcessElements ) ) otherlv_7= ')'
                    {
                    otherlv_5=(Token)match(input,47,FOLLOW_4); 

                    				newLeafNode(otherlv_5, grammarAccess.getTemplateProcessConfElementAccess().getLeftParenthesisKeyword_5_0());
                    			
                    // InternalPoST.g:1076:4: ( (lv_args_6_0= ruleTemplateProcessElements ) )
                    // InternalPoST.g:1077:5: (lv_args_6_0= ruleTemplateProcessElements )
                    {
                    // InternalPoST.g:1077:5: (lv_args_6_0= ruleTemplateProcessElements )
                    // InternalPoST.g:1078:6: lv_args_6_0= ruleTemplateProcessElements
                    {

                    						newCompositeNode(grammarAccess.getTemplateProcessConfElementAccess().getArgsTemplateProcessElementsParserRuleCall_5_1_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_args_6_0=ruleTemplateProcessElements();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getTemplateProcessConfElementRule());
                    						}
                    						set(
                    							current,
                    							"args",
                    							lv_args_6_0,
                    							"su.nsk.iae.post.PoST.TemplateProcessElements");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_7=(Token)match(input,48,FOLLOW_2); 

                    				newLeafNode(otherlv_7, grammarAccess.getTemplateProcessConfElementAccess().getRightParenthesisKeyword_5_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTemplateProcessConfElement"


    // $ANTLR start "entryRuleTemplateProcessElements"
    // InternalPoST.g:1104:1: entryRuleTemplateProcessElements returns [EObject current=null] : iv_ruleTemplateProcessElements= ruleTemplateProcessElements EOF ;
    public final EObject entryRuleTemplateProcessElements() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateProcessElements = null;


        try {
            // InternalPoST.g:1104:64: (iv_ruleTemplateProcessElements= ruleTemplateProcessElements EOF )
            // InternalPoST.g:1105:2: iv_ruleTemplateProcessElements= ruleTemplateProcessElements EOF
            {
             newCompositeNode(grammarAccess.getTemplateProcessElementsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTemplateProcessElements=ruleTemplateProcessElements();

            state._fsp--;

             current =iv_ruleTemplateProcessElements; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTemplateProcessElements"


    // $ANTLR start "ruleTemplateProcessElements"
    // InternalPoST.g:1111:1: ruleTemplateProcessElements returns [EObject current=null] : ( ( (lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement ) ) )* ) ;
    public final EObject ruleTemplateProcessElements() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_elements_0_0 = null;

        EObject lv_elements_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:1117:2: ( ( ( (lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement ) ) )* ) )
            // InternalPoST.g:1118:2: ( ( (lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement ) ) )* )
            {
            // InternalPoST.g:1118:2: ( ( (lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement ) ) )* )
            // InternalPoST.g:1119:3: ( (lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement ) ) )*
            {
            // InternalPoST.g:1119:3: ( (lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement ) )
            // InternalPoST.g:1120:4: (lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement )
            {
            // InternalPoST.g:1120:4: (lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement )
            // InternalPoST.g:1121:5: lv_elements_0_0= ruleTemplateProcessAttachVariableConfElement
            {

            					newCompositeNode(grammarAccess.getTemplateProcessElementsAccess().getElementsTemplateProcessAttachVariableConfElementParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_23);
            lv_elements_0_0=ruleTemplateProcessAttachVariableConfElement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTemplateProcessElementsRule());
            					}
            					add(
            						current,
            						"elements",
            						lv_elements_0_0,
            						"su.nsk.iae.post.PoST.TemplateProcessAttachVariableConfElement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:1138:3: (otherlv_1= ',' ( (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==50) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalPoST.g:1139:4: otherlv_1= ',' ( (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement ) )
            	    {
            	    otherlv_1=(Token)match(input,50,FOLLOW_4); 

            	    				newLeafNode(otherlv_1, grammarAccess.getTemplateProcessElementsAccess().getCommaKeyword_1_0());
            	    			
            	    // InternalPoST.g:1143:4: ( (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement ) )
            	    // InternalPoST.g:1144:5: (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement )
            	    {
            	    // InternalPoST.g:1144:5: (lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement )
            	    // InternalPoST.g:1145:6: lv_elements_2_0= ruleTemplateProcessAttachVariableConfElement
            	    {

            	    						newCompositeNode(grammarAccess.getTemplateProcessElementsAccess().getElementsTemplateProcessAttachVariableConfElementParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_23);
            	    lv_elements_2_0=ruleTemplateProcessAttachVariableConfElement();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getTemplateProcessElementsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_2_0,
            	    							"su.nsk.iae.post.PoST.TemplateProcessAttachVariableConfElement");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTemplateProcessElements"


    // $ANTLR start "entryRuleTemplateProcessAttachVariableConfElement"
    // InternalPoST.g:1167:1: entryRuleTemplateProcessAttachVariableConfElement returns [EObject current=null] : iv_ruleTemplateProcessAttachVariableConfElement= ruleTemplateProcessAttachVariableConfElement EOF ;
    public final EObject entryRuleTemplateProcessAttachVariableConfElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateProcessAttachVariableConfElement = null;


        try {
            // InternalPoST.g:1167:81: (iv_ruleTemplateProcessAttachVariableConfElement= ruleTemplateProcessAttachVariableConfElement EOF )
            // InternalPoST.g:1168:2: iv_ruleTemplateProcessAttachVariableConfElement= ruleTemplateProcessAttachVariableConfElement EOF
            {
             newCompositeNode(grammarAccess.getTemplateProcessAttachVariableConfElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTemplateProcessAttachVariableConfElement=ruleTemplateProcessAttachVariableConfElement();

            state._fsp--;

             current =iv_ruleTemplateProcessAttachVariableConfElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTemplateProcessAttachVariableConfElement"


    // $ANTLR start "ruleTemplateProcessAttachVariableConfElement"
    // InternalPoST.g:1174:1: ruleTemplateProcessAttachVariableConfElement returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) ) ) ;
    public final EObject ruleTemplateProcessAttachVariableConfElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Enumerator lv_assig_1_0 = null;

        EObject lv_const_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:1180:2: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) ) ) )
            // InternalPoST.g:1181:2: ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) ) )
            {
            // InternalPoST.g:1181:2: ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) ) )
            // InternalPoST.g:1182:3: ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) )
            {
            // InternalPoST.g:1182:3: ( (otherlv_0= RULE_ID ) )
            // InternalPoST.g:1183:4: (otherlv_0= RULE_ID )
            {
            // InternalPoST.g:1183:4: (otherlv_0= RULE_ID )
            // InternalPoST.g:1184:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTemplateProcessAttachVariableConfElementRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_24); 

            					newLeafNode(otherlv_0, grammarAccess.getTemplateProcessAttachVariableConfElementAccess().getProgramVarVariableCrossReference_0_0());
            				

            }


            }

            // InternalPoST.g:1195:3: ( (lv_assig_1_0= ruleAssignmentType ) )
            // InternalPoST.g:1196:4: (lv_assig_1_0= ruleAssignmentType )
            {
            // InternalPoST.g:1196:4: (lv_assig_1_0= ruleAssignmentType )
            // InternalPoST.g:1197:5: lv_assig_1_0= ruleAssignmentType
            {

            					newCompositeNode(grammarAccess.getTemplateProcessAttachVariableConfElementAccess().getAssigAssignmentTypeEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_25);
            lv_assig_1_0=ruleAssignmentType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTemplateProcessAttachVariableConfElementRule());
            					}
            					set(
            						current,
            						"assig",
            						lv_assig_1_0,
            						"su.nsk.iae.post.PoST.AssignmentType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:1214:3: ( ( (otherlv_2= RULE_ID ) ) | ( (lv_const_3_0= ruleConstant ) ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                alt18=1;
            }
            else if ( (LA18_0==RULE_INTEGER||LA18_0==RULE_TIME_PREF_LITERAL||(LA18_0>=RULE_REAL_TYPE_NAME && LA18_0<=RULE_REAL)||LA18_0==114) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalPoST.g:1215:4: ( (otherlv_2= RULE_ID ) )
                    {
                    // InternalPoST.g:1215:4: ( (otherlv_2= RULE_ID ) )
                    // InternalPoST.g:1216:5: (otherlv_2= RULE_ID )
                    {
                    // InternalPoST.g:1216:5: (otherlv_2= RULE_ID )
                    // InternalPoST.g:1217:6: otherlv_2= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getTemplateProcessAttachVariableConfElementRule());
                    						}
                    					
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(otherlv_2, grammarAccess.getTemplateProcessAttachVariableConfElementAccess().getAttVarVariableCrossReference_2_0_0());
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:1229:4: ( (lv_const_3_0= ruleConstant ) )
                    {
                    // InternalPoST.g:1229:4: ( (lv_const_3_0= ruleConstant ) )
                    // InternalPoST.g:1230:5: (lv_const_3_0= ruleConstant )
                    {
                    // InternalPoST.g:1230:5: (lv_const_3_0= ruleConstant )
                    // InternalPoST.g:1231:6: lv_const_3_0= ruleConstant
                    {

                    						newCompositeNode(grammarAccess.getTemplateProcessAttachVariableConfElementAccess().getConstConstantParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_const_3_0=ruleConstant();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getTemplateProcessAttachVariableConfElementRule());
                    						}
                    						set(
                    							current,
                    							"const",
                    							lv_const_3_0,
                    							"su.nsk.iae.post.PoST.Constant");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTemplateProcessAttachVariableConfElement"


    // $ANTLR start "entryRuleProgram"
    // InternalPoST.g:1253:1: entryRuleProgram returns [EObject current=null] : iv_ruleProgram= ruleProgram EOF ;
    public final EObject entryRuleProgram() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgram = null;


        try {
            // InternalPoST.g:1253:48: (iv_ruleProgram= ruleProgram EOF )
            // InternalPoST.g:1254:2: iv_ruleProgram= ruleProgram EOF
            {
             newCompositeNode(grammarAccess.getProgramRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProgram=ruleProgram();

            state._fsp--;

             current =iv_ruleProgram; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProgram"


    // $ANTLR start "ruleProgram"
    // InternalPoST.g:1260:1: ruleProgram returns [EObject current=null] : (otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_progInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_progOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_progVars_5_0= ruleVarDeclaration ) ) | ( (lv_progTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_progExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_PROGRAM' ) ;
    public final EObject ruleProgram() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_9=null;
        EObject lv_progInVars_2_0 = null;

        EObject lv_progOutVars_3_0 = null;

        EObject lv_progInOutVars_4_0 = null;

        EObject lv_progVars_5_0 = null;

        EObject lv_progTempVars_6_0 = null;

        EObject lv_progExternVars_7_0 = null;

        EObject lv_processes_8_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:1266:2: ( (otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_progInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_progOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_progVars_5_0= ruleVarDeclaration ) ) | ( (lv_progTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_progExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_PROGRAM' ) )
            // InternalPoST.g:1267:2: (otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_progInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_progOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_progVars_5_0= ruleVarDeclaration ) ) | ( (lv_progTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_progExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_PROGRAM' )
            {
            // InternalPoST.g:1267:2: (otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_progInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_progOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_progVars_5_0= ruleVarDeclaration ) ) | ( (lv_progTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_progExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_PROGRAM' )
            // InternalPoST.g:1268:3: otherlv_0= 'PROGRAM' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_progInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_progOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_progVars_5_0= ruleVarDeclaration ) ) | ( (lv_progTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_progExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_PROGRAM'
            {
            otherlv_0=(Token)match(input,51,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getProgramAccess().getPROGRAMKeyword_0());
            		
            // InternalPoST.g:1272:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:1273:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:1273:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:1274:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_27); 

            					newLeafNode(lv_name_1_0, grammarAccess.getProgramAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProgramRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            // InternalPoST.g:1290:3: ( ( (lv_progInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_progOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_progVars_5_0= ruleVarDeclaration ) ) | ( (lv_progTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_progExternVars_7_0= ruleExternalVarDeclaration ) ) )*
            loop19:
            do {
                int alt19=7;
                switch ( input.LA(1) ) {
                case 98:
                    {
                    alt19=1;
                    }
                    break;
                case 100:
                    {
                    alt19=2;
                    }
                    break;
                case 101:
                    {
                    alt19=3;
                    }
                    break;
                case 102:
                    {
                    alt19=4;
                    }
                    break;
                case 104:
                    {
                    alt19=5;
                    }
                    break;
                case 105:
                    {
                    alt19=6;
                    }
                    break;

                }

                switch (alt19) {
            	case 1 :
            	    // InternalPoST.g:1291:4: ( (lv_progInVars_2_0= ruleInputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1291:4: ( (lv_progInVars_2_0= ruleInputVarDeclaration ) )
            	    // InternalPoST.g:1292:5: (lv_progInVars_2_0= ruleInputVarDeclaration )
            	    {
            	    // InternalPoST.g:1292:5: (lv_progInVars_2_0= ruleInputVarDeclaration )
            	    // InternalPoST.g:1293:6: lv_progInVars_2_0= ruleInputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProgramAccess().getProgInVarsInputVarDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_27);
            	    lv_progInVars_2_0=ruleInputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProgramRule());
            	    						}
            	    						add(
            	    							current,
            	    							"progInVars",
            	    							lv_progInVars_2_0,
            	    							"su.nsk.iae.post.PoST.InputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalPoST.g:1311:4: ( (lv_progOutVars_3_0= ruleOutputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1311:4: ( (lv_progOutVars_3_0= ruleOutputVarDeclaration ) )
            	    // InternalPoST.g:1312:5: (lv_progOutVars_3_0= ruleOutputVarDeclaration )
            	    {
            	    // InternalPoST.g:1312:5: (lv_progOutVars_3_0= ruleOutputVarDeclaration )
            	    // InternalPoST.g:1313:6: lv_progOutVars_3_0= ruleOutputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProgramAccess().getProgOutVarsOutputVarDeclarationParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_27);
            	    lv_progOutVars_3_0=ruleOutputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProgramRule());
            	    						}
            	    						add(
            	    							current,
            	    							"progOutVars",
            	    							lv_progOutVars_3_0,
            	    							"su.nsk.iae.post.PoST.OutputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalPoST.g:1331:4: ( (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1331:4: ( (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration ) )
            	    // InternalPoST.g:1332:5: (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration )
            	    {
            	    // InternalPoST.g:1332:5: (lv_progInOutVars_4_0= ruleInputOutputVarDeclaration )
            	    // InternalPoST.g:1333:6: lv_progInOutVars_4_0= ruleInputOutputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProgramAccess().getProgInOutVarsInputOutputVarDeclarationParserRuleCall_2_2_0());
            	    					
            	    pushFollow(FOLLOW_27);
            	    lv_progInOutVars_4_0=ruleInputOutputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProgramRule());
            	    						}
            	    						add(
            	    							current,
            	    							"progInOutVars",
            	    							lv_progInOutVars_4_0,
            	    							"su.nsk.iae.post.PoST.InputOutputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalPoST.g:1351:4: ( (lv_progVars_5_0= ruleVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1351:4: ( (lv_progVars_5_0= ruleVarDeclaration ) )
            	    // InternalPoST.g:1352:5: (lv_progVars_5_0= ruleVarDeclaration )
            	    {
            	    // InternalPoST.g:1352:5: (lv_progVars_5_0= ruleVarDeclaration )
            	    // InternalPoST.g:1353:6: lv_progVars_5_0= ruleVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProgramAccess().getProgVarsVarDeclarationParserRuleCall_2_3_0());
            	    					
            	    pushFollow(FOLLOW_27);
            	    lv_progVars_5_0=ruleVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProgramRule());
            	    						}
            	    						add(
            	    							current,
            	    							"progVars",
            	    							lv_progVars_5_0,
            	    							"su.nsk.iae.post.PoST.VarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalPoST.g:1371:4: ( (lv_progTempVars_6_0= ruleTempVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1371:4: ( (lv_progTempVars_6_0= ruleTempVarDeclaration ) )
            	    // InternalPoST.g:1372:5: (lv_progTempVars_6_0= ruleTempVarDeclaration )
            	    {
            	    // InternalPoST.g:1372:5: (lv_progTempVars_6_0= ruleTempVarDeclaration )
            	    // InternalPoST.g:1373:6: lv_progTempVars_6_0= ruleTempVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProgramAccess().getProgTempVarsTempVarDeclarationParserRuleCall_2_4_0());
            	    					
            	    pushFollow(FOLLOW_27);
            	    lv_progTempVars_6_0=ruleTempVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProgramRule());
            	    						}
            	    						add(
            	    							current,
            	    							"progTempVars",
            	    							lv_progTempVars_6_0,
            	    							"su.nsk.iae.post.PoST.TempVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalPoST.g:1391:4: ( (lv_progExternVars_7_0= ruleExternalVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1391:4: ( (lv_progExternVars_7_0= ruleExternalVarDeclaration ) )
            	    // InternalPoST.g:1392:5: (lv_progExternVars_7_0= ruleExternalVarDeclaration )
            	    {
            	    // InternalPoST.g:1392:5: (lv_progExternVars_7_0= ruleExternalVarDeclaration )
            	    // InternalPoST.g:1393:6: lv_progExternVars_7_0= ruleExternalVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProgramAccess().getProgExternVarsExternalVarDeclarationParserRuleCall_2_5_0());
            	    					
            	    pushFollow(FOLLOW_27);
            	    lv_progExternVars_7_0=ruleExternalVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProgramRule());
            	    						}
            	    						add(
            	    							current,
            	    							"progExternVars",
            	    							lv_progExternVars_7_0,
            	    							"su.nsk.iae.post.PoST.ExternalVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // InternalPoST.g:1411:3: ( (lv_processes_8_0= ruleProcess ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==54) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalPoST.g:1412:4: (lv_processes_8_0= ruleProcess )
            	    {
            	    // InternalPoST.g:1412:4: (lv_processes_8_0= ruleProcess )
            	    // InternalPoST.g:1413:5: lv_processes_8_0= ruleProcess
            	    {

            	    					newCompositeNode(grammarAccess.getProgramAccess().getProcessesProcessParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_28);
            	    lv_processes_8_0=ruleProcess();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getProgramRule());
            	    					}
            	    					add(
            	    						current,
            	    						"processes",
            	    						lv_processes_8_0,
            	    						"su.nsk.iae.post.PoST.Process");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            otherlv_9=(Token)match(input,56,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getProgramAccess().getEND_PROGRAMKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProgram"


    // $ANTLR start "entryRuleFunctionBlock"
    // InternalPoST.g:1438:1: entryRuleFunctionBlock returns [EObject current=null] : iv_ruleFunctionBlock= ruleFunctionBlock EOF ;
    public final EObject entryRuleFunctionBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionBlock = null;


        try {
            // InternalPoST.g:1438:54: (iv_ruleFunctionBlock= ruleFunctionBlock EOF )
            // InternalPoST.g:1439:2: iv_ruleFunctionBlock= ruleFunctionBlock EOF
            {
             newCompositeNode(grammarAccess.getFunctionBlockRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunctionBlock=ruleFunctionBlock();

            state._fsp--;

             current =iv_ruleFunctionBlock; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunctionBlock"


    // $ANTLR start "ruleFunctionBlock"
    // InternalPoST.g:1445:1: ruleFunctionBlock returns [EObject current=null] : (otherlv_0= 'FUNCTION_BLOCK' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_fbInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_fbOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_fbVars_5_0= ruleVarDeclaration ) ) | ( (lv_fbTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_fbExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_FUNCTION_BLOCK' ) ;
    public final EObject ruleFunctionBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_9=null;
        EObject lv_fbInVars_2_0 = null;

        EObject lv_fbOutVars_3_0 = null;

        EObject lv_fbInOutVars_4_0 = null;

        EObject lv_fbVars_5_0 = null;

        EObject lv_fbTempVars_6_0 = null;

        EObject lv_fbExternVars_7_0 = null;

        EObject lv_processes_8_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:1451:2: ( (otherlv_0= 'FUNCTION_BLOCK' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_fbInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_fbOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_fbVars_5_0= ruleVarDeclaration ) ) | ( (lv_fbTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_fbExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_FUNCTION_BLOCK' ) )
            // InternalPoST.g:1452:2: (otherlv_0= 'FUNCTION_BLOCK' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_fbInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_fbOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_fbVars_5_0= ruleVarDeclaration ) ) | ( (lv_fbTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_fbExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_FUNCTION_BLOCK' )
            {
            // InternalPoST.g:1452:2: (otherlv_0= 'FUNCTION_BLOCK' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_fbInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_fbOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_fbVars_5_0= ruleVarDeclaration ) ) | ( (lv_fbTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_fbExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_FUNCTION_BLOCK' )
            // InternalPoST.g:1453:3: otherlv_0= 'FUNCTION_BLOCK' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_fbInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_fbOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_fbVars_5_0= ruleVarDeclaration ) ) | ( (lv_fbTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_fbExternVars_7_0= ruleExternalVarDeclaration ) ) )* ( (lv_processes_8_0= ruleProcess ) )* otherlv_9= 'END_FUNCTION_BLOCK'
            {
            otherlv_0=(Token)match(input,57,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getFunctionBlockAccess().getFUNCTION_BLOCKKeyword_0());
            		
            // InternalPoST.g:1457:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:1458:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:1458:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:1459:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_29); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFunctionBlockAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFunctionBlockRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            // InternalPoST.g:1475:3: ( ( (lv_fbInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_fbOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_fbVars_5_0= ruleVarDeclaration ) ) | ( (lv_fbTempVars_6_0= ruleTempVarDeclaration ) ) | ( (lv_fbExternVars_7_0= ruleExternalVarDeclaration ) ) )*
            loop21:
            do {
                int alt21=7;
                switch ( input.LA(1) ) {
                case 98:
                    {
                    alt21=1;
                    }
                    break;
                case 100:
                    {
                    alt21=2;
                    }
                    break;
                case 101:
                    {
                    alt21=3;
                    }
                    break;
                case 102:
                    {
                    alt21=4;
                    }
                    break;
                case 104:
                    {
                    alt21=5;
                    }
                    break;
                case 105:
                    {
                    alt21=6;
                    }
                    break;

                }

                switch (alt21) {
            	case 1 :
            	    // InternalPoST.g:1476:4: ( (lv_fbInVars_2_0= ruleInputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1476:4: ( (lv_fbInVars_2_0= ruleInputVarDeclaration ) )
            	    // InternalPoST.g:1477:5: (lv_fbInVars_2_0= ruleInputVarDeclaration )
            	    {
            	    // InternalPoST.g:1477:5: (lv_fbInVars_2_0= ruleInputVarDeclaration )
            	    // InternalPoST.g:1478:6: lv_fbInVars_2_0= ruleInputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionBlockAccess().getFbInVarsInputVarDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_29);
            	    lv_fbInVars_2_0=ruleInputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionBlockRule());
            	    						}
            	    						add(
            	    							current,
            	    							"fbInVars",
            	    							lv_fbInVars_2_0,
            	    							"su.nsk.iae.post.PoST.InputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalPoST.g:1496:4: ( (lv_fbOutVars_3_0= ruleOutputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1496:4: ( (lv_fbOutVars_3_0= ruleOutputVarDeclaration ) )
            	    // InternalPoST.g:1497:5: (lv_fbOutVars_3_0= ruleOutputVarDeclaration )
            	    {
            	    // InternalPoST.g:1497:5: (lv_fbOutVars_3_0= ruleOutputVarDeclaration )
            	    // InternalPoST.g:1498:6: lv_fbOutVars_3_0= ruleOutputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionBlockAccess().getFbOutVarsOutputVarDeclarationParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_29);
            	    lv_fbOutVars_3_0=ruleOutputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionBlockRule());
            	    						}
            	    						add(
            	    							current,
            	    							"fbOutVars",
            	    							lv_fbOutVars_3_0,
            	    							"su.nsk.iae.post.PoST.OutputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalPoST.g:1516:4: ( (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1516:4: ( (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration ) )
            	    // InternalPoST.g:1517:5: (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration )
            	    {
            	    // InternalPoST.g:1517:5: (lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration )
            	    // InternalPoST.g:1518:6: lv_fbInOutVars_4_0= ruleInputOutputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionBlockAccess().getFbInOutVarsInputOutputVarDeclarationParserRuleCall_2_2_0());
            	    					
            	    pushFollow(FOLLOW_29);
            	    lv_fbInOutVars_4_0=ruleInputOutputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionBlockRule());
            	    						}
            	    						add(
            	    							current,
            	    							"fbInOutVars",
            	    							lv_fbInOutVars_4_0,
            	    							"su.nsk.iae.post.PoST.InputOutputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalPoST.g:1536:4: ( (lv_fbVars_5_0= ruleVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1536:4: ( (lv_fbVars_5_0= ruleVarDeclaration ) )
            	    // InternalPoST.g:1537:5: (lv_fbVars_5_0= ruleVarDeclaration )
            	    {
            	    // InternalPoST.g:1537:5: (lv_fbVars_5_0= ruleVarDeclaration )
            	    // InternalPoST.g:1538:6: lv_fbVars_5_0= ruleVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionBlockAccess().getFbVarsVarDeclarationParserRuleCall_2_3_0());
            	    					
            	    pushFollow(FOLLOW_29);
            	    lv_fbVars_5_0=ruleVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionBlockRule());
            	    						}
            	    						add(
            	    							current,
            	    							"fbVars",
            	    							lv_fbVars_5_0,
            	    							"su.nsk.iae.post.PoST.VarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalPoST.g:1556:4: ( (lv_fbTempVars_6_0= ruleTempVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1556:4: ( (lv_fbTempVars_6_0= ruleTempVarDeclaration ) )
            	    // InternalPoST.g:1557:5: (lv_fbTempVars_6_0= ruleTempVarDeclaration )
            	    {
            	    // InternalPoST.g:1557:5: (lv_fbTempVars_6_0= ruleTempVarDeclaration )
            	    // InternalPoST.g:1558:6: lv_fbTempVars_6_0= ruleTempVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionBlockAccess().getFbTempVarsTempVarDeclarationParserRuleCall_2_4_0());
            	    					
            	    pushFollow(FOLLOW_29);
            	    lv_fbTempVars_6_0=ruleTempVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionBlockRule());
            	    						}
            	    						add(
            	    							current,
            	    							"fbTempVars",
            	    							lv_fbTempVars_6_0,
            	    							"su.nsk.iae.post.PoST.TempVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalPoST.g:1576:4: ( (lv_fbExternVars_7_0= ruleExternalVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1576:4: ( (lv_fbExternVars_7_0= ruleExternalVarDeclaration ) )
            	    // InternalPoST.g:1577:5: (lv_fbExternVars_7_0= ruleExternalVarDeclaration )
            	    {
            	    // InternalPoST.g:1577:5: (lv_fbExternVars_7_0= ruleExternalVarDeclaration )
            	    // InternalPoST.g:1578:6: lv_fbExternVars_7_0= ruleExternalVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionBlockAccess().getFbExternVarsExternalVarDeclarationParserRuleCall_2_5_0());
            	    					
            	    pushFollow(FOLLOW_29);
            	    lv_fbExternVars_7_0=ruleExternalVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionBlockRule());
            	    						}
            	    						add(
            	    							current,
            	    							"fbExternVars",
            	    							lv_fbExternVars_7_0,
            	    							"su.nsk.iae.post.PoST.ExternalVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            // InternalPoST.g:1596:3: ( (lv_processes_8_0= ruleProcess ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==54) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalPoST.g:1597:4: (lv_processes_8_0= ruleProcess )
            	    {
            	    // InternalPoST.g:1597:4: (lv_processes_8_0= ruleProcess )
            	    // InternalPoST.g:1598:5: lv_processes_8_0= ruleProcess
            	    {

            	    					newCompositeNode(grammarAccess.getFunctionBlockAccess().getProcessesProcessParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_30);
            	    lv_processes_8_0=ruleProcess();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFunctionBlockRule());
            	    					}
            	    					add(
            	    						current,
            	    						"processes",
            	    						lv_processes_8_0,
            	    						"su.nsk.iae.post.PoST.Process");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            otherlv_9=(Token)match(input,58,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getFunctionBlockAccess().getEND_FUNCTION_BLOCKKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunctionBlock"


    // $ANTLR start "entryRuleFunction"
    // InternalPoST.g:1623:1: entryRuleFunction returns [EObject current=null] : iv_ruleFunction= ruleFunction EOF ;
    public final EObject entryRuleFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunction = null;


        try {
            // InternalPoST.g:1623:49: (iv_ruleFunction= ruleFunction EOF )
            // InternalPoST.g:1624:2: iv_ruleFunction= ruleFunction EOF
            {
             newCompositeNode(grammarAccess.getFunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunction=ruleFunction();

            state._fsp--;

             current =iv_ruleFunction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunction"


    // $ANTLR start "ruleFunction"
    // InternalPoST.g:1630:1: ruleFunction returns [EObject current=null] : (otherlv_0= 'FUNCTION' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleDataTypeName ) ) ( ( (lv_funInVars_4_0= ruleInputVarDeclaration ) ) | ( (lv_funOutVars_5_0= ruleOutputVarDeclaration ) ) | ( (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration ) ) | ( (lv_funVars_7_0= ruleVarDeclaration ) ) )* ( (lv_statement_8_0= ruleStatementList ) ) otherlv_9= 'END_FUNCTION' ) ;
    public final EObject ruleFunction() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_type_3_0 = null;

        EObject lv_funInVars_4_0 = null;

        EObject lv_funOutVars_5_0 = null;

        EObject lv_funInOutVars_6_0 = null;

        EObject lv_funVars_7_0 = null;

        EObject lv_statement_8_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:1636:2: ( (otherlv_0= 'FUNCTION' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleDataTypeName ) ) ( ( (lv_funInVars_4_0= ruleInputVarDeclaration ) ) | ( (lv_funOutVars_5_0= ruleOutputVarDeclaration ) ) | ( (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration ) ) | ( (lv_funVars_7_0= ruleVarDeclaration ) ) )* ( (lv_statement_8_0= ruleStatementList ) ) otherlv_9= 'END_FUNCTION' ) )
            // InternalPoST.g:1637:2: (otherlv_0= 'FUNCTION' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleDataTypeName ) ) ( ( (lv_funInVars_4_0= ruleInputVarDeclaration ) ) | ( (lv_funOutVars_5_0= ruleOutputVarDeclaration ) ) | ( (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration ) ) | ( (lv_funVars_7_0= ruleVarDeclaration ) ) )* ( (lv_statement_8_0= ruleStatementList ) ) otherlv_9= 'END_FUNCTION' )
            {
            // InternalPoST.g:1637:2: (otherlv_0= 'FUNCTION' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleDataTypeName ) ) ( ( (lv_funInVars_4_0= ruleInputVarDeclaration ) ) | ( (lv_funOutVars_5_0= ruleOutputVarDeclaration ) ) | ( (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration ) ) | ( (lv_funVars_7_0= ruleVarDeclaration ) ) )* ( (lv_statement_8_0= ruleStatementList ) ) otherlv_9= 'END_FUNCTION' )
            // InternalPoST.g:1638:3: otherlv_0= 'FUNCTION' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (lv_type_3_0= ruleDataTypeName ) ) ( ( (lv_funInVars_4_0= ruleInputVarDeclaration ) ) | ( (lv_funOutVars_5_0= ruleOutputVarDeclaration ) ) | ( (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration ) ) | ( (lv_funVars_7_0= ruleVarDeclaration ) ) )* ( (lv_statement_8_0= ruleStatementList ) ) otherlv_9= 'END_FUNCTION'
            {
            otherlv_0=(Token)match(input,59,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getFunctionAccess().getFUNCTIONKeyword_0());
            		
            // InternalPoST.g:1642:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:1643:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:1643:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:1644:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_20); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFunctionAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFunctionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,53,FOLLOW_31); 

            			newLeafNode(otherlv_2, grammarAccess.getFunctionAccess().getColonKeyword_2());
            		
            // InternalPoST.g:1664:3: ( (lv_type_3_0= ruleDataTypeName ) )
            // InternalPoST.g:1665:4: (lv_type_3_0= ruleDataTypeName )
            {
            // InternalPoST.g:1665:4: (lv_type_3_0= ruleDataTypeName )
            // InternalPoST.g:1666:5: lv_type_3_0= ruleDataTypeName
            {

            					newCompositeNode(grammarAccess.getFunctionAccess().getTypeDataTypeNameParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_32);
            lv_type_3_0=ruleDataTypeName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFunctionRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_3_0,
            						"su.nsk.iae.post.PoST.DataTypeName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:1683:3: ( ( (lv_funInVars_4_0= ruleInputVarDeclaration ) ) | ( (lv_funOutVars_5_0= ruleOutputVarDeclaration ) ) | ( (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration ) ) | ( (lv_funVars_7_0= ruleVarDeclaration ) ) )*
            loop23:
            do {
                int alt23=5;
                switch ( input.LA(1) ) {
                case 98:
                    {
                    alt23=1;
                    }
                    break;
                case 100:
                    {
                    alt23=2;
                    }
                    break;
                case 101:
                    {
                    alt23=3;
                    }
                    break;
                case 102:
                    {
                    alt23=4;
                    }
                    break;

                }

                switch (alt23) {
            	case 1 :
            	    // InternalPoST.g:1684:4: ( (lv_funInVars_4_0= ruleInputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1684:4: ( (lv_funInVars_4_0= ruleInputVarDeclaration ) )
            	    // InternalPoST.g:1685:5: (lv_funInVars_4_0= ruleInputVarDeclaration )
            	    {
            	    // InternalPoST.g:1685:5: (lv_funInVars_4_0= ruleInputVarDeclaration )
            	    // InternalPoST.g:1686:6: lv_funInVars_4_0= ruleInputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionAccess().getFunInVarsInputVarDeclarationParserRuleCall_4_0_0());
            	    					
            	    pushFollow(FOLLOW_32);
            	    lv_funInVars_4_0=ruleInputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"funInVars",
            	    							lv_funInVars_4_0,
            	    							"su.nsk.iae.post.PoST.InputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalPoST.g:1704:4: ( (lv_funOutVars_5_0= ruleOutputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1704:4: ( (lv_funOutVars_5_0= ruleOutputVarDeclaration ) )
            	    // InternalPoST.g:1705:5: (lv_funOutVars_5_0= ruleOutputVarDeclaration )
            	    {
            	    // InternalPoST.g:1705:5: (lv_funOutVars_5_0= ruleOutputVarDeclaration )
            	    // InternalPoST.g:1706:6: lv_funOutVars_5_0= ruleOutputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionAccess().getFunOutVarsOutputVarDeclarationParserRuleCall_4_1_0());
            	    					
            	    pushFollow(FOLLOW_32);
            	    lv_funOutVars_5_0=ruleOutputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"funOutVars",
            	    							lv_funOutVars_5_0,
            	    							"su.nsk.iae.post.PoST.OutputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalPoST.g:1724:4: ( (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1724:4: ( (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration ) )
            	    // InternalPoST.g:1725:5: (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration )
            	    {
            	    // InternalPoST.g:1725:5: (lv_funInOutVars_6_0= ruleInputOutputVarDeclaration )
            	    // InternalPoST.g:1726:6: lv_funInOutVars_6_0= ruleInputOutputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionAccess().getFunInOutVarsInputOutputVarDeclarationParserRuleCall_4_2_0());
            	    					
            	    pushFollow(FOLLOW_32);
            	    lv_funInOutVars_6_0=ruleInputOutputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"funInOutVars",
            	    							lv_funInOutVars_6_0,
            	    							"su.nsk.iae.post.PoST.InputOutputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalPoST.g:1744:4: ( (lv_funVars_7_0= ruleVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1744:4: ( (lv_funVars_7_0= ruleVarDeclaration ) )
            	    // InternalPoST.g:1745:5: (lv_funVars_7_0= ruleVarDeclaration )
            	    {
            	    // InternalPoST.g:1745:5: (lv_funVars_7_0= ruleVarDeclaration )
            	    // InternalPoST.g:1746:6: lv_funVars_7_0= ruleVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getFunctionAccess().getFunVarsVarDeclarationParserRuleCall_4_3_0());
            	    					
            	    pushFollow(FOLLOW_32);
            	    lv_funVars_7_0=ruleVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getFunctionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"funVars",
            	    							lv_funVars_7_0,
            	    							"su.nsk.iae.post.PoST.VarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            // InternalPoST.g:1764:3: ( (lv_statement_8_0= ruleStatementList ) )
            // InternalPoST.g:1765:4: (lv_statement_8_0= ruleStatementList )
            {
            // InternalPoST.g:1765:4: (lv_statement_8_0= ruleStatementList )
            // InternalPoST.g:1766:5: lv_statement_8_0= ruleStatementList
            {

            					newCompositeNode(grammarAccess.getFunctionAccess().getStatementStatementListParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_33);
            lv_statement_8_0=ruleStatementList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFunctionRule());
            					}
            					set(
            						current,
            						"statement",
            						lv_statement_8_0,
            						"su.nsk.iae.post.PoST.StatementList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,60,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getFunctionAccess().getEND_FUNCTIONKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunction"


    // $ANTLR start "entryRuleProcess"
    // InternalPoST.g:1791:1: entryRuleProcess returns [EObject current=null] : iv_ruleProcess= ruleProcess EOF ;
    public final EObject entryRuleProcess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcess = null;


        try {
            // InternalPoST.g:1791:48: (iv_ruleProcess= ruleProcess EOF )
            // InternalPoST.g:1792:2: iv_ruleProcess= ruleProcess EOF
            {
             newCompositeNode(grammarAccess.getProcessRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcess=ruleProcess();

            state._fsp--;

             current =iv_ruleProcess; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcess"


    // $ANTLR start "ruleProcess"
    // InternalPoST.g:1798:1: ruleProcess returns [EObject current=null] : (otherlv_0= 'PROCESS' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_procInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_procOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_procProcessVars_5_0= ruleProcessVarDeclaration ) ) | ( (lv_procVars_6_0= ruleVarDeclaration ) ) | ( (lv_procTempVars_7_0= ruleTempVarDeclaration ) ) )* ( (lv_states_8_0= ruleState ) )* otherlv_9= 'END_PROCESS' ) ;
    public final EObject ruleProcess() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_9=null;
        EObject lv_procInVars_2_0 = null;

        EObject lv_procOutVars_3_0 = null;

        EObject lv_procInOutVars_4_0 = null;

        EObject lv_procProcessVars_5_0 = null;

        EObject lv_procVars_6_0 = null;

        EObject lv_procTempVars_7_0 = null;

        EObject lv_states_8_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:1804:2: ( (otherlv_0= 'PROCESS' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_procInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_procOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_procProcessVars_5_0= ruleProcessVarDeclaration ) ) | ( (lv_procVars_6_0= ruleVarDeclaration ) ) | ( (lv_procTempVars_7_0= ruleTempVarDeclaration ) ) )* ( (lv_states_8_0= ruleState ) )* otherlv_9= 'END_PROCESS' ) )
            // InternalPoST.g:1805:2: (otherlv_0= 'PROCESS' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_procInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_procOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_procProcessVars_5_0= ruleProcessVarDeclaration ) ) | ( (lv_procVars_6_0= ruleVarDeclaration ) ) | ( (lv_procTempVars_7_0= ruleTempVarDeclaration ) ) )* ( (lv_states_8_0= ruleState ) )* otherlv_9= 'END_PROCESS' )
            {
            // InternalPoST.g:1805:2: (otherlv_0= 'PROCESS' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_procInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_procOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_procProcessVars_5_0= ruleProcessVarDeclaration ) ) | ( (lv_procVars_6_0= ruleVarDeclaration ) ) | ( (lv_procTempVars_7_0= ruleTempVarDeclaration ) ) )* ( (lv_states_8_0= ruleState ) )* otherlv_9= 'END_PROCESS' )
            // InternalPoST.g:1806:3: otherlv_0= 'PROCESS' ( (lv_name_1_0= RULE_ID ) ) ( ( (lv_procInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_procOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_procProcessVars_5_0= ruleProcessVarDeclaration ) ) | ( (lv_procVars_6_0= ruleVarDeclaration ) ) | ( (lv_procTempVars_7_0= ruleTempVarDeclaration ) ) )* ( (lv_states_8_0= ruleState ) )* otherlv_9= 'END_PROCESS'
            {
            otherlv_0=(Token)match(input,54,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getProcessAccess().getPROCESSKeyword_0());
            		
            // InternalPoST.g:1810:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:1811:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:1811:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:1812:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_34); 

            					newLeafNode(lv_name_1_0, grammarAccess.getProcessAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProcessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            // InternalPoST.g:1828:3: ( ( (lv_procInVars_2_0= ruleInputVarDeclaration ) ) | ( (lv_procOutVars_3_0= ruleOutputVarDeclaration ) ) | ( (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration ) ) | ( (lv_procProcessVars_5_0= ruleProcessVarDeclaration ) ) | ( (lv_procVars_6_0= ruleVarDeclaration ) ) | ( (lv_procTempVars_7_0= ruleTempVarDeclaration ) ) )*
            loop24:
            do {
                int alt24=7;
                switch ( input.LA(1) ) {
                case 98:
                    {
                    alt24=1;
                    }
                    break;
                case 100:
                    {
                    alt24=2;
                    }
                    break;
                case 101:
                    {
                    alt24=3;
                    }
                    break;
                case 108:
                    {
                    alt24=4;
                    }
                    break;
                case 102:
                    {
                    alt24=5;
                    }
                    break;
                case 104:
                    {
                    alt24=6;
                    }
                    break;

                }

                switch (alt24) {
            	case 1 :
            	    // InternalPoST.g:1829:4: ( (lv_procInVars_2_0= ruleInputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1829:4: ( (lv_procInVars_2_0= ruleInputVarDeclaration ) )
            	    // InternalPoST.g:1830:5: (lv_procInVars_2_0= ruleInputVarDeclaration )
            	    {
            	    // InternalPoST.g:1830:5: (lv_procInVars_2_0= ruleInputVarDeclaration )
            	    // InternalPoST.g:1831:6: lv_procInVars_2_0= ruleInputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProcessAccess().getProcInVarsInputVarDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_procInVars_2_0=ruleInputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProcessRule());
            	    						}
            	    						add(
            	    							current,
            	    							"procInVars",
            	    							lv_procInVars_2_0,
            	    							"su.nsk.iae.post.PoST.InputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalPoST.g:1849:4: ( (lv_procOutVars_3_0= ruleOutputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1849:4: ( (lv_procOutVars_3_0= ruleOutputVarDeclaration ) )
            	    // InternalPoST.g:1850:5: (lv_procOutVars_3_0= ruleOutputVarDeclaration )
            	    {
            	    // InternalPoST.g:1850:5: (lv_procOutVars_3_0= ruleOutputVarDeclaration )
            	    // InternalPoST.g:1851:6: lv_procOutVars_3_0= ruleOutputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProcessAccess().getProcOutVarsOutputVarDeclarationParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_procOutVars_3_0=ruleOutputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProcessRule());
            	    						}
            	    						add(
            	    							current,
            	    							"procOutVars",
            	    							lv_procOutVars_3_0,
            	    							"su.nsk.iae.post.PoST.OutputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalPoST.g:1869:4: ( (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1869:4: ( (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration ) )
            	    // InternalPoST.g:1870:5: (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration )
            	    {
            	    // InternalPoST.g:1870:5: (lv_procInOutVars_4_0= ruleInputOutputVarDeclaration )
            	    // InternalPoST.g:1871:6: lv_procInOutVars_4_0= ruleInputOutputVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProcessAccess().getProcInOutVarsInputOutputVarDeclarationParserRuleCall_2_2_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_procInOutVars_4_0=ruleInputOutputVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProcessRule());
            	    						}
            	    						add(
            	    							current,
            	    							"procInOutVars",
            	    							lv_procInOutVars_4_0,
            	    							"su.nsk.iae.post.PoST.InputOutputVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalPoST.g:1889:4: ( (lv_procProcessVars_5_0= ruleProcessVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1889:4: ( (lv_procProcessVars_5_0= ruleProcessVarDeclaration ) )
            	    // InternalPoST.g:1890:5: (lv_procProcessVars_5_0= ruleProcessVarDeclaration )
            	    {
            	    // InternalPoST.g:1890:5: (lv_procProcessVars_5_0= ruleProcessVarDeclaration )
            	    // InternalPoST.g:1891:6: lv_procProcessVars_5_0= ruleProcessVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProcessAccess().getProcProcessVarsProcessVarDeclarationParserRuleCall_2_3_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_procProcessVars_5_0=ruleProcessVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProcessRule());
            	    						}
            	    						add(
            	    							current,
            	    							"procProcessVars",
            	    							lv_procProcessVars_5_0,
            	    							"su.nsk.iae.post.PoST.ProcessVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalPoST.g:1909:4: ( (lv_procVars_6_0= ruleVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1909:4: ( (lv_procVars_6_0= ruleVarDeclaration ) )
            	    // InternalPoST.g:1910:5: (lv_procVars_6_0= ruleVarDeclaration )
            	    {
            	    // InternalPoST.g:1910:5: (lv_procVars_6_0= ruleVarDeclaration )
            	    // InternalPoST.g:1911:6: lv_procVars_6_0= ruleVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProcessAccess().getProcVarsVarDeclarationParserRuleCall_2_4_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_procVars_6_0=ruleVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProcessRule());
            	    						}
            	    						add(
            	    							current,
            	    							"procVars",
            	    							lv_procVars_6_0,
            	    							"su.nsk.iae.post.PoST.VarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalPoST.g:1929:4: ( (lv_procTempVars_7_0= ruleTempVarDeclaration ) )
            	    {
            	    // InternalPoST.g:1929:4: ( (lv_procTempVars_7_0= ruleTempVarDeclaration ) )
            	    // InternalPoST.g:1930:5: (lv_procTempVars_7_0= ruleTempVarDeclaration )
            	    {
            	    // InternalPoST.g:1930:5: (lv_procTempVars_7_0= ruleTempVarDeclaration )
            	    // InternalPoST.g:1931:6: lv_procTempVars_7_0= ruleTempVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProcessAccess().getProcTempVarsTempVarDeclarationParserRuleCall_2_5_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_procTempVars_7_0=ruleTempVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProcessRule());
            	    						}
            	    						add(
            	    							current,
            	    							"procTempVars",
            	    							lv_procTempVars_7_0,
            	    							"su.nsk.iae.post.PoST.TempVarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            // InternalPoST.g:1949:3: ( (lv_states_8_0= ruleState ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==62) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalPoST.g:1950:4: (lv_states_8_0= ruleState )
            	    {
            	    // InternalPoST.g:1950:4: (lv_states_8_0= ruleState )
            	    // InternalPoST.g:1951:5: lv_states_8_0= ruleState
            	    {

            	    					newCompositeNode(grammarAccess.getProcessAccess().getStatesStateParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_35);
            	    lv_states_8_0=ruleState();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getProcessRule());
            	    					}
            	    					add(
            	    						current,
            	    						"states",
            	    						lv_states_8_0,
            	    						"su.nsk.iae.post.PoST.State");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            otherlv_9=(Token)match(input,61,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getProcessAccess().getEND_PROCESSKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcess"


    // $ANTLR start "entryRuleState"
    // InternalPoST.g:1976:1: entryRuleState returns [EObject current=null] : iv_ruleState= ruleState EOF ;
    public final EObject entryRuleState() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleState = null;


        try {
            // InternalPoST.g:1976:46: (iv_ruleState= ruleState EOF )
            // InternalPoST.g:1977:2: iv_ruleState= ruleState EOF
            {
             newCompositeNode(grammarAccess.getStateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleState=ruleState();

            state._fsp--;

             current =iv_ruleState; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleState"


    // $ANTLR start "ruleState"
    // InternalPoST.g:1983:1: ruleState returns [EObject current=null] : (otherlv_0= 'STATE' ( (lv_name_1_0= RULE_ID ) ) ( (lv_looped_2_0= 'LOOPED' ) )? ( (lv_statement_3_0= ruleStatementList ) ) ( (lv_timeout_4_0= ruleTimeoutStatement ) )? otherlv_5= 'END_STATE' ) ;
    public final EObject ruleState() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_looped_2_0=null;
        Token otherlv_5=null;
        EObject lv_statement_3_0 = null;

        EObject lv_timeout_4_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:1989:2: ( (otherlv_0= 'STATE' ( (lv_name_1_0= RULE_ID ) ) ( (lv_looped_2_0= 'LOOPED' ) )? ( (lv_statement_3_0= ruleStatementList ) ) ( (lv_timeout_4_0= ruleTimeoutStatement ) )? otherlv_5= 'END_STATE' ) )
            // InternalPoST.g:1990:2: (otherlv_0= 'STATE' ( (lv_name_1_0= RULE_ID ) ) ( (lv_looped_2_0= 'LOOPED' ) )? ( (lv_statement_3_0= ruleStatementList ) ) ( (lv_timeout_4_0= ruleTimeoutStatement ) )? otherlv_5= 'END_STATE' )
            {
            // InternalPoST.g:1990:2: (otherlv_0= 'STATE' ( (lv_name_1_0= RULE_ID ) ) ( (lv_looped_2_0= 'LOOPED' ) )? ( (lv_statement_3_0= ruleStatementList ) ) ( (lv_timeout_4_0= ruleTimeoutStatement ) )? otherlv_5= 'END_STATE' )
            // InternalPoST.g:1991:3: otherlv_0= 'STATE' ( (lv_name_1_0= RULE_ID ) ) ( (lv_looped_2_0= 'LOOPED' ) )? ( (lv_statement_3_0= ruleStatementList ) ) ( (lv_timeout_4_0= ruleTimeoutStatement ) )? otherlv_5= 'END_STATE'
            {
            otherlv_0=(Token)match(input,62,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getStateAccess().getSTATEKeyword_0());
            		
            // InternalPoST.g:1995:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalPoST.g:1996:4: (lv_name_1_0= RULE_ID )
            {
            // InternalPoST.g:1996:4: (lv_name_1_0= RULE_ID )
            // InternalPoST.g:1997:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_36); 

            					newLeafNode(lv_name_1_0, grammarAccess.getStateAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStateRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"su.nsk.iae.post.PoST.ID");
            				

            }


            }

            // InternalPoST.g:2013:3: ( (lv_looped_2_0= 'LOOPED' ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==63) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalPoST.g:2014:4: (lv_looped_2_0= 'LOOPED' )
                    {
                    // InternalPoST.g:2014:4: (lv_looped_2_0= 'LOOPED' )
                    // InternalPoST.g:2015:5: lv_looped_2_0= 'LOOPED'
                    {
                    lv_looped_2_0=(Token)match(input,63,FOLLOW_37); 

                    					newLeafNode(lv_looped_2_0, grammarAccess.getStateAccess().getLoopedLOOPEDKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getStateRule());
                    					}
                    					setWithLastConsumed(current, "looped", lv_looped_2_0 != null, "LOOPED");
                    				

                    }


                    }
                    break;

            }

            // InternalPoST.g:2027:3: ( (lv_statement_3_0= ruleStatementList ) )
            // InternalPoST.g:2028:4: (lv_statement_3_0= ruleStatementList )
            {
            // InternalPoST.g:2028:4: (lv_statement_3_0= ruleStatementList )
            // InternalPoST.g:2029:5: lv_statement_3_0= ruleStatementList
            {

            					newCompositeNode(grammarAccess.getStateAccess().getStatementStatementListParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_38);
            lv_statement_3_0=ruleStatementList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStateRule());
            					}
            					set(
            						current,
            						"statement",
            						lv_statement_3_0,
            						"su.nsk.iae.post.PoST.StatementList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:2046:3: ( (lv_timeout_4_0= ruleTimeoutStatement ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==73) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalPoST.g:2047:4: (lv_timeout_4_0= ruleTimeoutStatement )
                    {
                    // InternalPoST.g:2047:4: (lv_timeout_4_0= ruleTimeoutStatement )
                    // InternalPoST.g:2048:5: lv_timeout_4_0= ruleTimeoutStatement
                    {

                    					newCompositeNode(grammarAccess.getStateAccess().getTimeoutTimeoutStatementParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_39);
                    lv_timeout_4_0=ruleTimeoutStatement();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getStateRule());
                    					}
                    					set(
                    						current,
                    						"timeout",
                    						lv_timeout_4_0,
                    						"su.nsk.iae.post.PoST.TimeoutStatement");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,64,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getStateAccess().getEND_STATEKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleState"


    // $ANTLR start "entryRuleSetStateStatement"
    // InternalPoST.g:2073:1: entryRuleSetStateStatement returns [EObject current=null] : iv_ruleSetStateStatement= ruleSetStateStatement EOF ;
    public final EObject entryRuleSetStateStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetStateStatement = null;


        try {
            // InternalPoST.g:2073:58: (iv_ruleSetStateStatement= ruleSetStateStatement EOF )
            // InternalPoST.g:2074:2: iv_ruleSetStateStatement= ruleSetStateStatement EOF
            {
             newCompositeNode(grammarAccess.getSetStateStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetStateStatement=ruleSetStateStatement();

            state._fsp--;

             current =iv_ruleSetStateStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetStateStatement"


    // $ANTLR start "ruleSetStateStatement"
    // InternalPoST.g:2080:1: ruleSetStateStatement returns [EObject current=null] : ( () otherlv_1= 'SET' ( (otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) ) ) | ( (lv_next_4_0= 'NEXT' ) ) ) ) ;
    public final EObject ruleSetStateStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_next_4_0=null;


        	enterRule();

        try {
            // InternalPoST.g:2086:2: ( ( () otherlv_1= 'SET' ( (otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) ) ) | ( (lv_next_4_0= 'NEXT' ) ) ) ) )
            // InternalPoST.g:2087:2: ( () otherlv_1= 'SET' ( (otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) ) ) | ( (lv_next_4_0= 'NEXT' ) ) ) )
            {
            // InternalPoST.g:2087:2: ( () otherlv_1= 'SET' ( (otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) ) ) | ( (lv_next_4_0= 'NEXT' ) ) ) )
            // InternalPoST.g:2088:3: () otherlv_1= 'SET' ( (otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) ) ) | ( (lv_next_4_0= 'NEXT' ) ) )
            {
            // InternalPoST.g:2088:3: ()
            // InternalPoST.g:2089:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSetStateStatementAccess().getSetStateStatementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,65,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getSetStateStatementAccess().getSETKeyword_1());
            		
            // InternalPoST.g:2099:3: ( (otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) ) ) | ( (lv_next_4_0= 'NEXT' ) ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==62) ) {
                alt28=1;
            }
            else if ( (LA28_0==66) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // InternalPoST.g:2100:4: (otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) ) )
                    {
                    // InternalPoST.g:2100:4: (otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) ) )
                    // InternalPoST.g:2101:5: otherlv_2= 'STATE' ( (otherlv_3= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,62,FOLLOW_4); 

                    					newLeafNode(otherlv_2, grammarAccess.getSetStateStatementAccess().getSTATEKeyword_2_0_0());
                    				
                    // InternalPoST.g:2105:5: ( (otherlv_3= RULE_ID ) )
                    // InternalPoST.g:2106:6: (otherlv_3= RULE_ID )
                    {
                    // InternalPoST.g:2106:6: (otherlv_3= RULE_ID )
                    // InternalPoST.g:2107:7: otherlv_3= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getSetStateStatementRule());
                    							}
                    						
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_2); 

                    							newLeafNode(otherlv_3, grammarAccess.getSetStateStatementAccess().getStateStateCrossReference_2_0_1_0());
                    						

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:2120:4: ( (lv_next_4_0= 'NEXT' ) )
                    {
                    // InternalPoST.g:2120:4: ( (lv_next_4_0= 'NEXT' ) )
                    // InternalPoST.g:2121:5: (lv_next_4_0= 'NEXT' )
                    {
                    // InternalPoST.g:2121:5: (lv_next_4_0= 'NEXT' )
                    // InternalPoST.g:2122:6: lv_next_4_0= 'NEXT'
                    {
                    lv_next_4_0=(Token)match(input,66,FOLLOW_2); 

                    						newLeafNode(lv_next_4_0, grammarAccess.getSetStateStatementAccess().getNextNEXTKeyword_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSetStateStatementRule());
                    						}
                    						setWithLastConsumed(current, "next", lv_next_4_0 != null, "NEXT");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetStateStatement"


    // $ANTLR start "entryRuleProcessStatements"
    // InternalPoST.g:2139:1: entryRuleProcessStatements returns [EObject current=null] : iv_ruleProcessStatements= ruleProcessStatements EOF ;
    public final EObject entryRuleProcessStatements() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcessStatements = null;


        try {
            // InternalPoST.g:2139:58: (iv_ruleProcessStatements= ruleProcessStatements EOF )
            // InternalPoST.g:2140:2: iv_ruleProcessStatements= ruleProcessStatements EOF
            {
             newCompositeNode(grammarAccess.getProcessStatementsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcessStatements=ruleProcessStatements();

            state._fsp--;

             current =iv_ruleProcessStatements; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcessStatements"


    // $ANTLR start "ruleProcessStatements"
    // InternalPoST.g:2146:1: ruleProcessStatements returns [EObject current=null] : (this_StartProcessStatement_0= ruleStartProcessStatement | this_StopProcessStatement_1= ruleStopProcessStatement | this_ErrorProcessStatement_2= ruleErrorProcessStatement ) ;
    public final EObject ruleProcessStatements() throws RecognitionException {
        EObject current = null;

        EObject this_StartProcessStatement_0 = null;

        EObject this_StopProcessStatement_1 = null;

        EObject this_ErrorProcessStatement_2 = null;



        	enterRule();

        try {
            // InternalPoST.g:2152:2: ( (this_StartProcessStatement_0= ruleStartProcessStatement | this_StopProcessStatement_1= ruleStopProcessStatement | this_ErrorProcessStatement_2= ruleErrorProcessStatement ) )
            // InternalPoST.g:2153:2: (this_StartProcessStatement_0= ruleStartProcessStatement | this_StopProcessStatement_1= ruleStopProcessStatement | this_ErrorProcessStatement_2= ruleErrorProcessStatement )
            {
            // InternalPoST.g:2153:2: (this_StartProcessStatement_0= ruleStartProcessStatement | this_StopProcessStatement_1= ruleStopProcessStatement | this_ErrorProcessStatement_2= ruleErrorProcessStatement )
            int alt29=3;
            switch ( input.LA(1) ) {
            case 71:
            case 72:
                {
                alt29=1;
                }
                break;
            case 69:
                {
                alt29=2;
                }
                break;
            case 70:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // InternalPoST.g:2154:3: this_StartProcessStatement_0= ruleStartProcessStatement
                    {

                    			newCompositeNode(grammarAccess.getProcessStatementsAccess().getStartProcessStatementParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_StartProcessStatement_0=ruleStartProcessStatement();

                    state._fsp--;


                    			current = this_StartProcessStatement_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:2163:3: this_StopProcessStatement_1= ruleStopProcessStatement
                    {

                    			newCompositeNode(grammarAccess.getProcessStatementsAccess().getStopProcessStatementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_StopProcessStatement_1=ruleStopProcessStatement();

                    state._fsp--;


                    			current = this_StopProcessStatement_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalPoST.g:2172:3: this_ErrorProcessStatement_2= ruleErrorProcessStatement
                    {

                    			newCompositeNode(grammarAccess.getProcessStatementsAccess().getErrorProcessStatementParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ErrorProcessStatement_2=ruleErrorProcessStatement();

                    state._fsp--;


                    			current = this_ErrorProcessStatement_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcessStatements"


    // $ANTLR start "entryRuleProcessStatusExpression"
    // InternalPoST.g:2184:1: entryRuleProcessStatusExpression returns [EObject current=null] : iv_ruleProcessStatusExpression= ruleProcessStatusExpression EOF ;
    public final EObject entryRuleProcessStatusExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcessStatusExpression = null;


        try {
            // InternalPoST.g:2184:64: (iv_ruleProcessStatusExpression= ruleProcessStatusExpression EOF )
            // InternalPoST.g:2185:2: iv_ruleProcessStatusExpression= ruleProcessStatusExpression EOF
            {
             newCompositeNode(grammarAccess.getProcessStatusExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcessStatusExpression=ruleProcessStatusExpression();

            state._fsp--;

             current =iv_ruleProcessStatusExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcessStatusExpression"


    // $ANTLR start "ruleProcessStatusExpression"
    // InternalPoST.g:2191:1: ruleProcessStatusExpression returns [EObject current=null] : (otherlv_0= 'PROCESS' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'IN' otherlv_3= 'STATE' ( ( (lv_active_4_0= 'ACTIVE' ) ) | ( (lv_inactive_5_0= 'INACTIVE' ) ) | ( (lv_stop_6_0= 'STOP' ) ) | ( (lv_error_7_0= 'ERROR' ) ) ) ) ;
    public final EObject ruleProcessStatusExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_active_4_0=null;
        Token lv_inactive_5_0=null;
        Token lv_stop_6_0=null;
        Token lv_error_7_0=null;


        	enterRule();

        try {
            // InternalPoST.g:2197:2: ( (otherlv_0= 'PROCESS' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'IN' otherlv_3= 'STATE' ( ( (lv_active_4_0= 'ACTIVE' ) ) | ( (lv_inactive_5_0= 'INACTIVE' ) ) | ( (lv_stop_6_0= 'STOP' ) ) | ( (lv_error_7_0= 'ERROR' ) ) ) ) )
            // InternalPoST.g:2198:2: (otherlv_0= 'PROCESS' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'IN' otherlv_3= 'STATE' ( ( (lv_active_4_0= 'ACTIVE' ) ) | ( (lv_inactive_5_0= 'INACTIVE' ) ) | ( (lv_stop_6_0= 'STOP' ) ) | ( (lv_error_7_0= 'ERROR' ) ) ) )
            {
            // InternalPoST.g:2198:2: (otherlv_0= 'PROCESS' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'IN' otherlv_3= 'STATE' ( ( (lv_active_4_0= 'ACTIVE' ) ) | ( (lv_inactive_5_0= 'INACTIVE' ) ) | ( (lv_stop_6_0= 'STOP' ) ) | ( (lv_error_7_0= 'ERROR' ) ) ) )
            // InternalPoST.g:2199:3: otherlv_0= 'PROCESS' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'IN' otherlv_3= 'STATE' ( ( (lv_active_4_0= 'ACTIVE' ) ) | ( (lv_inactive_5_0= 'INACTIVE' ) ) | ( (lv_stop_6_0= 'STOP' ) ) | ( (lv_error_7_0= 'ERROR' ) ) )
            {
            otherlv_0=(Token)match(input,54,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getProcessStatusExpressionAccess().getPROCESSKeyword_0());
            		
            // InternalPoST.g:2203:3: ( (otherlv_1= RULE_ID ) )
            // InternalPoST.g:2204:4: (otherlv_1= RULE_ID )
            {
            // InternalPoST.g:2204:4: (otherlv_1= RULE_ID )
            // InternalPoST.g:2205:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProcessStatusExpressionRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_41); 

            					newLeafNode(otherlv_1, grammarAccess.getProcessStatusExpressionAccess().getProcessVariableCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,67,FOLLOW_42); 

            			newLeafNode(otherlv_2, grammarAccess.getProcessStatusExpressionAccess().getINKeyword_2());
            		
            otherlv_3=(Token)match(input,62,FOLLOW_43); 

            			newLeafNode(otherlv_3, grammarAccess.getProcessStatusExpressionAccess().getSTATEKeyword_3());
            		
            // InternalPoST.g:2224:3: ( ( (lv_active_4_0= 'ACTIVE' ) ) | ( (lv_inactive_5_0= 'INACTIVE' ) ) | ( (lv_stop_6_0= 'STOP' ) ) | ( (lv_error_7_0= 'ERROR' ) ) )
            int alt30=4;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt30=1;
                }
                break;
            case 68:
                {
                alt30=2;
                }
                break;
            case 69:
                {
                alt30=3;
                }
                break;
            case 70:
                {
                alt30=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // InternalPoST.g:2225:4: ( (lv_active_4_0= 'ACTIVE' ) )
                    {
                    // InternalPoST.g:2225:4: ( (lv_active_4_0= 'ACTIVE' ) )
                    // InternalPoST.g:2226:5: (lv_active_4_0= 'ACTIVE' )
                    {
                    // InternalPoST.g:2226:5: (lv_active_4_0= 'ACTIVE' )
                    // InternalPoST.g:2227:6: lv_active_4_0= 'ACTIVE'
                    {
                    lv_active_4_0=(Token)match(input,55,FOLLOW_2); 

                    						newLeafNode(lv_active_4_0, grammarAccess.getProcessStatusExpressionAccess().getActiveACTIVEKeyword_4_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getProcessStatusExpressionRule());
                    						}
                    						setWithLastConsumed(current, "active", lv_active_4_0 != null, "ACTIVE");
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:2240:4: ( (lv_inactive_5_0= 'INACTIVE' ) )
                    {
                    // InternalPoST.g:2240:4: ( (lv_inactive_5_0= 'INACTIVE' ) )
                    // InternalPoST.g:2241:5: (lv_inactive_5_0= 'INACTIVE' )
                    {
                    // InternalPoST.g:2241:5: (lv_inactive_5_0= 'INACTIVE' )
                    // InternalPoST.g:2242:6: lv_inactive_5_0= 'INACTIVE'
                    {
                    lv_inactive_5_0=(Token)match(input,68,FOLLOW_2); 

                    						newLeafNode(lv_inactive_5_0, grammarAccess.getProcessStatusExpressionAccess().getInactiveINACTIVEKeyword_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getProcessStatusExpressionRule());
                    						}
                    						setWithLastConsumed(current, "inactive", lv_inactive_5_0 != null, "INACTIVE");
                    					

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalPoST.g:2255:4: ( (lv_stop_6_0= 'STOP' ) )
                    {
                    // InternalPoST.g:2255:4: ( (lv_stop_6_0= 'STOP' ) )
                    // InternalPoST.g:2256:5: (lv_stop_6_0= 'STOP' )
                    {
                    // InternalPoST.g:2256:5: (lv_stop_6_0= 'STOP' )
                    // InternalPoST.g:2257:6: lv_stop_6_0= 'STOP'
                    {
                    lv_stop_6_0=(Token)match(input,69,FOLLOW_2); 

                    						newLeafNode(lv_stop_6_0, grammarAccess.getProcessStatusExpressionAccess().getStopSTOPKeyword_4_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getProcessStatusExpressionRule());
                    						}
                    						setWithLastConsumed(current, "stop", lv_stop_6_0 != null, "STOP");
                    					

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalPoST.g:2270:4: ( (lv_error_7_0= 'ERROR' ) )
                    {
                    // InternalPoST.g:2270:4: ( (lv_error_7_0= 'ERROR' ) )
                    // InternalPoST.g:2271:5: (lv_error_7_0= 'ERROR' )
                    {
                    // InternalPoST.g:2271:5: (lv_error_7_0= 'ERROR' )
                    // InternalPoST.g:2272:6: lv_error_7_0= 'ERROR'
                    {
                    lv_error_7_0=(Token)match(input,70,FOLLOW_2); 

                    						newLeafNode(lv_error_7_0, grammarAccess.getProcessStatusExpressionAccess().getErrorERRORKeyword_4_3_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getProcessStatusExpressionRule());
                    						}
                    						setWithLastConsumed(current, "error", lv_error_7_0 != null, "ERROR");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcessStatusExpression"


    // $ANTLR start "entryRuleStartProcessStatement"
    // InternalPoST.g:2289:1: entryRuleStartProcessStatement returns [EObject current=null] : iv_ruleStartProcessStatement= ruleStartProcessStatement EOF ;
    public final EObject entryRuleStartProcessStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStartProcessStatement = null;


        try {
            // InternalPoST.g:2289:62: (iv_ruleStartProcessStatement= ruleStartProcessStatement EOF )
            // InternalPoST.g:2290:2: iv_ruleStartProcessStatement= ruleStartProcessStatement EOF
            {
             newCompositeNode(grammarAccess.getStartProcessStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStartProcessStatement=ruleStartProcessStatement();

            state._fsp--;

             current =iv_ruleStartProcessStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStartProcessStatement"


    // $ANTLR start "ruleStartProcessStatement"
    // InternalPoST.g:2296:1: ruleStartProcessStatement returns [EObject current=null] : ( ( () (otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) ) ) | ( () otherlv_5= 'RESTART' ) ) ;
    public final EObject ruleStartProcessStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalPoST.g:2302:2: ( ( ( () (otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) ) ) | ( () otherlv_5= 'RESTART' ) ) )
            // InternalPoST.g:2303:2: ( ( () (otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) ) ) | ( () otherlv_5= 'RESTART' ) )
            {
            // InternalPoST.g:2303:2: ( ( () (otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) ) ) | ( () otherlv_5= 'RESTART' ) )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==71) ) {
                alt31=1;
            }
            else if ( (LA31_0==72) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // InternalPoST.g:2304:3: ( () (otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) ) )
                    {
                    // InternalPoST.g:2304:3: ( () (otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) ) )
                    // InternalPoST.g:2305:4: () (otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )
                    {
                    // InternalPoST.g:2305:4: ()
                    // InternalPoST.g:2306:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getStartProcessStatementAccess().getStartProcessStatementAction_0_0(),
                    						current);
                    				

                    }

                    // InternalPoST.g:2312:4: (otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )
                    // InternalPoST.g:2313:5: otherlv_1= 'START' otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,71,FOLLOW_44); 

                    					newLeafNode(otherlv_1, grammarAccess.getStartProcessStatementAccess().getSTARTKeyword_0_1_0());
                    				
                    otherlv_2=(Token)match(input,54,FOLLOW_4); 

                    					newLeafNode(otherlv_2, grammarAccess.getStartProcessStatementAccess().getPROCESSKeyword_0_1_1());
                    				
                    // InternalPoST.g:2321:5: ( (otherlv_3= RULE_ID ) )
                    // InternalPoST.g:2322:6: (otherlv_3= RULE_ID )
                    {
                    // InternalPoST.g:2322:6: (otherlv_3= RULE_ID )
                    // InternalPoST.g:2323:7: otherlv_3= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getStartProcessStatementRule());
                    							}
                    						
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_2); 

                    							newLeafNode(otherlv_3, grammarAccess.getStartProcessStatementAccess().getProcessVariableCrossReference_0_1_2_0());
                    						

                    }


                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:2337:3: ( () otherlv_5= 'RESTART' )
                    {
                    // InternalPoST.g:2337:3: ( () otherlv_5= 'RESTART' )
                    // InternalPoST.g:2338:4: () otherlv_5= 'RESTART'
                    {
                    // InternalPoST.g:2338:4: ()
                    // InternalPoST.g:2339:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getStartProcessStatementAccess().getStartProcessStatementAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_5=(Token)match(input,72,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getStartProcessStatementAccess().getRESTARTKeyword_1_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStartProcessStatement"


    // $ANTLR start "entryRuleStopProcessStatement"
    // InternalPoST.g:2354:1: entryRuleStopProcessStatement returns [EObject current=null] : iv_ruleStopProcessStatement= ruleStopProcessStatement EOF ;
    public final EObject entryRuleStopProcessStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStopProcessStatement = null;


        try {
            // InternalPoST.g:2354:61: (iv_ruleStopProcessStatement= ruleStopProcessStatement EOF )
            // InternalPoST.g:2355:2: iv_ruleStopProcessStatement= ruleStopProcessStatement EOF
            {
             newCompositeNode(grammarAccess.getStopProcessStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStopProcessStatement=ruleStopProcessStatement();

            state._fsp--;

             current =iv_ruleStopProcessStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStopProcessStatement"


    // $ANTLR start "ruleStopProcessStatement"
    // InternalPoST.g:2361:1: ruleStopProcessStatement returns [EObject current=null] : ( () otherlv_1= 'STOP' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )? ) ;
    public final EObject ruleStopProcessStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalPoST.g:2367:2: ( ( () otherlv_1= 'STOP' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )? ) )
            // InternalPoST.g:2368:2: ( () otherlv_1= 'STOP' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )? )
            {
            // InternalPoST.g:2368:2: ( () otherlv_1= 'STOP' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )? )
            // InternalPoST.g:2369:3: () otherlv_1= 'STOP' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )?
            {
            // InternalPoST.g:2369:3: ()
            // InternalPoST.g:2370:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStopProcessStatementAccess().getStopProcessStatementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,69,FOLLOW_45); 

            			newLeafNode(otherlv_1, grammarAccess.getStopProcessStatementAccess().getSTOPKeyword_1());
            		
            // InternalPoST.g:2380:3: (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==54) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalPoST.g:2381:4: otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,54,FOLLOW_4); 

                    				newLeafNode(otherlv_2, grammarAccess.getStopProcessStatementAccess().getPROCESSKeyword_2_0());
                    			
                    // InternalPoST.g:2385:4: ( (otherlv_3= RULE_ID ) )
                    // InternalPoST.g:2386:5: (otherlv_3= RULE_ID )
                    {
                    // InternalPoST.g:2386:5: (otherlv_3= RULE_ID )
                    // InternalPoST.g:2387:6: otherlv_3= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getStopProcessStatementRule());
                    						}
                    					
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(otherlv_3, grammarAccess.getStopProcessStatementAccess().getProcessVariableCrossReference_2_1_0());
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStopProcessStatement"


    // $ANTLR start "entryRuleErrorProcessStatement"
    // InternalPoST.g:2403:1: entryRuleErrorProcessStatement returns [EObject current=null] : iv_ruleErrorProcessStatement= ruleErrorProcessStatement EOF ;
    public final EObject entryRuleErrorProcessStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleErrorProcessStatement = null;


        try {
            // InternalPoST.g:2403:62: (iv_ruleErrorProcessStatement= ruleErrorProcessStatement EOF )
            // InternalPoST.g:2404:2: iv_ruleErrorProcessStatement= ruleErrorProcessStatement EOF
            {
             newCompositeNode(grammarAccess.getErrorProcessStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleErrorProcessStatement=ruleErrorProcessStatement();

            state._fsp--;

             current =iv_ruleErrorProcessStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleErrorProcessStatement"


    // $ANTLR start "ruleErrorProcessStatement"
    // InternalPoST.g:2410:1: ruleErrorProcessStatement returns [EObject current=null] : ( () otherlv_1= 'ERROR' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )? ) ;
    public final EObject ruleErrorProcessStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalPoST.g:2416:2: ( ( () otherlv_1= 'ERROR' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )? ) )
            // InternalPoST.g:2417:2: ( () otherlv_1= 'ERROR' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )? )
            {
            // InternalPoST.g:2417:2: ( () otherlv_1= 'ERROR' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )? )
            // InternalPoST.g:2418:3: () otherlv_1= 'ERROR' (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )?
            {
            // InternalPoST.g:2418:3: ()
            // InternalPoST.g:2419:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getErrorProcessStatementAccess().getErrorProcessStatementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,70,FOLLOW_45); 

            			newLeafNode(otherlv_1, grammarAccess.getErrorProcessStatementAccess().getERRORKeyword_1());
            		
            // InternalPoST.g:2429:3: (otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==54) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalPoST.g:2430:4: otherlv_2= 'PROCESS' ( (otherlv_3= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,54,FOLLOW_4); 

                    				newLeafNode(otherlv_2, grammarAccess.getErrorProcessStatementAccess().getPROCESSKeyword_2_0());
                    			
                    // InternalPoST.g:2434:4: ( (otherlv_3= RULE_ID ) )
                    // InternalPoST.g:2435:5: (otherlv_3= RULE_ID )
                    {
                    // InternalPoST.g:2435:5: (otherlv_3= RULE_ID )
                    // InternalPoST.g:2436:6: otherlv_3= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getErrorProcessStatementRule());
                    						}
                    					
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(otherlv_3, grammarAccess.getErrorProcessStatementAccess().getProcessVariableCrossReference_2_1_0());
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleErrorProcessStatement"


    // $ANTLR start "entryRuleTimeoutStatement"
    // InternalPoST.g:2452:1: entryRuleTimeoutStatement returns [EObject current=null] : iv_ruleTimeoutStatement= ruleTimeoutStatement EOF ;
    public final EObject entryRuleTimeoutStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimeoutStatement = null;


        try {
            // InternalPoST.g:2452:57: (iv_ruleTimeoutStatement= ruleTimeoutStatement EOF )
            // InternalPoST.g:2453:2: iv_ruleTimeoutStatement= ruleTimeoutStatement EOF
            {
             newCompositeNode(grammarAccess.getTimeoutStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTimeoutStatement=ruleTimeoutStatement();

            state._fsp--;

             current =iv_ruleTimeoutStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTimeoutStatement"


    // $ANTLR start "ruleTimeoutStatement"
    // InternalPoST.g:2459:1: ruleTimeoutStatement returns [EObject current=null] : (otherlv_0= 'TIMEOUT' ( ( (lv_const_1_0= ruleConstant ) ) | ( (otherlv_2= RULE_ID ) ) ) otherlv_3= 'THEN' ( (lv_statement_4_0= ruleStatementList ) ) otherlv_5= 'END_TIMEOUT' ) ;
    public final EObject ruleTimeoutStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_const_1_0 = null;

        EObject lv_statement_4_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:2465:2: ( (otherlv_0= 'TIMEOUT' ( ( (lv_const_1_0= ruleConstant ) ) | ( (otherlv_2= RULE_ID ) ) ) otherlv_3= 'THEN' ( (lv_statement_4_0= ruleStatementList ) ) otherlv_5= 'END_TIMEOUT' ) )
            // InternalPoST.g:2466:2: (otherlv_0= 'TIMEOUT' ( ( (lv_const_1_0= ruleConstant ) ) | ( (otherlv_2= RULE_ID ) ) ) otherlv_3= 'THEN' ( (lv_statement_4_0= ruleStatementList ) ) otherlv_5= 'END_TIMEOUT' )
            {
            // InternalPoST.g:2466:2: (otherlv_0= 'TIMEOUT' ( ( (lv_const_1_0= ruleConstant ) ) | ( (otherlv_2= RULE_ID ) ) ) otherlv_3= 'THEN' ( (lv_statement_4_0= ruleStatementList ) ) otherlv_5= 'END_TIMEOUT' )
            // InternalPoST.g:2467:3: otherlv_0= 'TIMEOUT' ( ( (lv_const_1_0= ruleConstant ) ) | ( (otherlv_2= RULE_ID ) ) ) otherlv_3= 'THEN' ( (lv_statement_4_0= ruleStatementList ) ) otherlv_5= 'END_TIMEOUT'
            {
            otherlv_0=(Token)match(input,73,FOLLOW_25); 

            			newLeafNode(otherlv_0, grammarAccess.getTimeoutStatementAccess().getTIMEOUTKeyword_0());
            		
            // InternalPoST.g:2471:3: ( ( (lv_const_1_0= ruleConstant ) ) | ( (otherlv_2= RULE_ID ) ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==RULE_INTEGER||LA34_0==RULE_TIME_PREF_LITERAL||(LA34_0>=RULE_REAL_TYPE_NAME && LA34_0<=RULE_REAL)||LA34_0==114) ) {
                alt34=1;
            }
            else if ( (LA34_0==RULE_ID) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // InternalPoST.g:2472:4: ( (lv_const_1_0= ruleConstant ) )
                    {
                    // InternalPoST.g:2472:4: ( (lv_const_1_0= ruleConstant ) )
                    // InternalPoST.g:2473:5: (lv_const_1_0= ruleConstant )
                    {
                    // InternalPoST.g:2473:5: (lv_const_1_0= ruleConstant )
                    // InternalPoST.g:2474:6: lv_const_1_0= ruleConstant
                    {

                    						newCompositeNode(grammarAccess.getTimeoutStatementAccess().getConstConstantParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_46);
                    lv_const_1_0=ruleConstant();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getTimeoutStatementRule());
                    						}
                    						set(
                    							current,
                    							"const",
                    							lv_const_1_0,
                    							"su.nsk.iae.post.PoST.Constant");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:2492:4: ( (otherlv_2= RULE_ID ) )
                    {
                    // InternalPoST.g:2492:4: ( (otherlv_2= RULE_ID ) )
                    // InternalPoST.g:2493:5: (otherlv_2= RULE_ID )
                    {
                    // InternalPoST.g:2493:5: (otherlv_2= RULE_ID )
                    // InternalPoST.g:2494:6: otherlv_2= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getTimeoutStatementRule());
                    						}
                    					
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_46); 

                    						newLeafNode(otherlv_2, grammarAccess.getTimeoutStatementAccess().getVariableSymbolicVariableCrossReference_1_1_0());
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,74,FOLLOW_47); 

            			newLeafNode(otherlv_3, grammarAccess.getTimeoutStatementAccess().getTHENKeyword_2());
            		
            // InternalPoST.g:2510:3: ( (lv_statement_4_0= ruleStatementList ) )
            // InternalPoST.g:2511:4: (lv_statement_4_0= ruleStatementList )
            {
            // InternalPoST.g:2511:4: (lv_statement_4_0= ruleStatementList )
            // InternalPoST.g:2512:5: lv_statement_4_0= ruleStatementList
            {

            					newCompositeNode(grammarAccess.getTimeoutStatementAccess().getStatementStatementListParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_48);
            lv_statement_4_0=ruleStatementList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTimeoutStatementRule());
            					}
            					set(
            						current,
            						"statement",
            						lv_statement_4_0,
            						"su.nsk.iae.post.PoST.StatementList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,75,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getTimeoutStatementAccess().getEND_TIMEOUTKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTimeoutStatement"


    // $ANTLR start "entryRuleResetTimerStatement"
    // InternalPoST.g:2537:1: entryRuleResetTimerStatement returns [EObject current=null] : iv_ruleResetTimerStatement= ruleResetTimerStatement EOF ;
    public final EObject entryRuleResetTimerStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResetTimerStatement = null;


        try {
            // InternalPoST.g:2537:60: (iv_ruleResetTimerStatement= ruleResetTimerStatement EOF )
            // InternalPoST.g:2538:2: iv_ruleResetTimerStatement= ruleResetTimerStatement EOF
            {
             newCompositeNode(grammarAccess.getResetTimerStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResetTimerStatement=ruleResetTimerStatement();

            state._fsp--;

             current =iv_ruleResetTimerStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleResetTimerStatement"


    // $ANTLR start "ruleResetTimerStatement"
    // InternalPoST.g:2544:1: ruleResetTimerStatement returns [EObject current=null] : ( () otherlv_1= 'RESET' otherlv_2= 'TIMER' ) ;
    public final EObject ruleResetTimerStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalPoST.g:2550:2: ( ( () otherlv_1= 'RESET' otherlv_2= 'TIMER' ) )
            // InternalPoST.g:2551:2: ( () otherlv_1= 'RESET' otherlv_2= 'TIMER' )
            {
            // InternalPoST.g:2551:2: ( () otherlv_1= 'RESET' otherlv_2= 'TIMER' )
            // InternalPoST.g:2552:3: () otherlv_1= 'RESET' otherlv_2= 'TIMER'
            {
            // InternalPoST.g:2552:3: ()
            // InternalPoST.g:2553:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getResetTimerStatementAccess().getResetTimerStatementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,76,FOLLOW_49); 

            			newLeafNode(otherlv_1, grammarAccess.getResetTimerStatementAccess().getRESETKeyword_1());
            		
            otherlv_2=(Token)match(input,77,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getResetTimerStatementAccess().getTIMERKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleResetTimerStatement"


    // $ANTLR start "entryRuleFBInvocation"
    // InternalPoST.g:2571:1: entryRuleFBInvocation returns [EObject current=null] : iv_ruleFBInvocation= ruleFBInvocation EOF ;
    public final EObject entryRuleFBInvocation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFBInvocation = null;


        try {
            // InternalPoST.g:2571:53: (iv_ruleFBInvocation= ruleFBInvocation EOF )
            // InternalPoST.g:2572:2: iv_ruleFBInvocation= ruleFBInvocation EOF
            {
             newCompositeNode(grammarAccess.getFBInvocationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFBInvocation=ruleFBInvocation();

            state._fsp--;

             current =iv_ruleFBInvocation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFBInvocation"


    // $ANTLR start "ruleFBInvocation"
    // InternalPoST.g:2578:1: ruleFBInvocation returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')' ) ;
    public final EObject ruleFBInvocation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_args_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:2584:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')' ) )
            // InternalPoST.g:2585:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')' )
            {
            // InternalPoST.g:2585:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')' )
            // InternalPoST.g:2586:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')'
            {
            // InternalPoST.g:2586:3: ( (otherlv_0= RULE_ID ) )
            // InternalPoST.g:2587:4: (otherlv_0= RULE_ID )
            {
            // InternalPoST.g:2587:4: (otherlv_0= RULE_ID )
            // InternalPoST.g:2588:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFBInvocationRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            					newLeafNode(otherlv_0, grammarAccess.getFBInvocationAccess().getFbSymbolicVariableCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,47,FOLLOW_50); 

            			newLeafNode(otherlv_1, grammarAccess.getFBInvocationAccess().getLeftParenthesisKeyword_1());
            		
            // InternalPoST.g:2603:3: ( (lv_args_2_0= ruleParamAssignmentElements ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==RULE_ID) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalPoST.g:2604:4: (lv_args_2_0= ruleParamAssignmentElements )
                    {
                    // InternalPoST.g:2604:4: (lv_args_2_0= ruleParamAssignmentElements )
                    // InternalPoST.g:2605:5: lv_args_2_0= ruleParamAssignmentElements
                    {

                    					newCompositeNode(grammarAccess.getFBInvocationAccess().getArgsParamAssignmentElementsParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_13);
                    lv_args_2_0=ruleParamAssignmentElements();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFBInvocationRule());
                    					}
                    					set(
                    						current,
                    						"args",
                    						lv_args_2_0,
                    						"su.nsk.iae.post.PoST.ParamAssignmentElements");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,48,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getFBInvocationAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFBInvocation"


    // $ANTLR start "entryRuleFunctionCall"
    // InternalPoST.g:2630:1: entryRuleFunctionCall returns [EObject current=null] : iv_ruleFunctionCall= ruleFunctionCall EOF ;
    public final EObject entryRuleFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionCall = null;


        try {
            // InternalPoST.g:2630:53: (iv_ruleFunctionCall= ruleFunctionCall EOF )
            // InternalPoST.g:2631:2: iv_ruleFunctionCall= ruleFunctionCall EOF
            {
             newCompositeNode(grammarAccess.getFunctionCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunctionCall=ruleFunctionCall();

            state._fsp--;

             current =iv_ruleFunctionCall; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunctionCall"


    // $ANTLR start "ruleFunctionCall"
    // InternalPoST.g:2637:1: ruleFunctionCall returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')' ) ;
    public final EObject ruleFunctionCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_args_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:2643:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')' ) )
            // InternalPoST.g:2644:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')' )
            {
            // InternalPoST.g:2644:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')' )
            // InternalPoST.g:2645:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '(' ( (lv_args_2_0= ruleParamAssignmentElements ) )? otherlv_3= ')'
            {
            // InternalPoST.g:2645:3: ( (otherlv_0= RULE_ID ) )
            // InternalPoST.g:2646:4: (otherlv_0= RULE_ID )
            {
            // InternalPoST.g:2646:4: (otherlv_0= RULE_ID )
            // InternalPoST.g:2647:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFunctionCallRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            					newLeafNode(otherlv_0, grammarAccess.getFunctionCallAccess().getFunctionFunctionCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,47,FOLLOW_50); 

            			newLeafNode(otherlv_1, grammarAccess.getFunctionCallAccess().getLeftParenthesisKeyword_1());
            		
            // InternalPoST.g:2662:3: ( (lv_args_2_0= ruleParamAssignmentElements ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==RULE_ID) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalPoST.g:2663:4: (lv_args_2_0= ruleParamAssignmentElements )
                    {
                    // InternalPoST.g:2663:4: (lv_args_2_0= ruleParamAssignmentElements )
                    // InternalPoST.g:2664:5: lv_args_2_0= ruleParamAssignmentElements
                    {

                    					newCompositeNode(grammarAccess.getFunctionCallAccess().getArgsParamAssignmentElementsParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_13);
                    lv_args_2_0=ruleParamAssignmentElements();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFunctionCallRule());
                    					}
                    					set(
                    						current,
                    						"args",
                    						lv_args_2_0,
                    						"su.nsk.iae.post.PoST.ParamAssignmentElements");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,48,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getFunctionCallAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunctionCall"


    // $ANTLR start "entryRuleParamAssignmentElements"
    // InternalPoST.g:2689:1: entryRuleParamAssignmentElements returns [EObject current=null] : iv_ruleParamAssignmentElements= ruleParamAssignmentElements EOF ;
    public final EObject entryRuleParamAssignmentElements() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParamAssignmentElements = null;


        try {
            // InternalPoST.g:2689:64: (iv_ruleParamAssignmentElements= ruleParamAssignmentElements EOF )
            // InternalPoST.g:2690:2: iv_ruleParamAssignmentElements= ruleParamAssignmentElements EOF
            {
             newCompositeNode(grammarAccess.getParamAssignmentElementsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParamAssignmentElements=ruleParamAssignmentElements();

            state._fsp--;

             current =iv_ruleParamAssignmentElements; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParamAssignmentElements"


    // $ANTLR start "ruleParamAssignmentElements"
    // InternalPoST.g:2696:1: ruleParamAssignmentElements returns [EObject current=null] : ( ( (lv_elements_0_0= ruleParamAssignment ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleParamAssignment ) ) )* ) ;
    public final EObject ruleParamAssignmentElements() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_elements_0_0 = null;

        EObject lv_elements_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:2702:2: ( ( ( (lv_elements_0_0= ruleParamAssignment ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleParamAssignment ) ) )* ) )
            // InternalPoST.g:2703:2: ( ( (lv_elements_0_0= ruleParamAssignment ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleParamAssignment ) ) )* )
            {
            // InternalPoST.g:2703:2: ( ( (lv_elements_0_0= ruleParamAssignment ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleParamAssignment ) ) )* )
            // InternalPoST.g:2704:3: ( (lv_elements_0_0= ruleParamAssignment ) ) (otherlv_1= ',' ( (lv_elements_2_0= ruleParamAssignment ) ) )*
            {
            // InternalPoST.g:2704:3: ( (lv_elements_0_0= ruleParamAssignment ) )
            // InternalPoST.g:2705:4: (lv_elements_0_0= ruleParamAssignment )
            {
            // InternalPoST.g:2705:4: (lv_elements_0_0= ruleParamAssignment )
            // InternalPoST.g:2706:5: lv_elements_0_0= ruleParamAssignment
            {

            					newCompositeNode(grammarAccess.getParamAssignmentElementsAccess().getElementsParamAssignmentParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_23);
            lv_elements_0_0=ruleParamAssignment();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParamAssignmentElementsRule());
            					}
            					add(
            						current,
            						"elements",
            						lv_elements_0_0,
            						"su.nsk.iae.post.PoST.ParamAssignment");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:2723:3: (otherlv_1= ',' ( (lv_elements_2_0= ruleParamAssignment ) ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==50) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalPoST.g:2724:4: otherlv_1= ',' ( (lv_elements_2_0= ruleParamAssignment ) )
            	    {
            	    otherlv_1=(Token)match(input,50,FOLLOW_4); 

            	    				newLeafNode(otherlv_1, grammarAccess.getParamAssignmentElementsAccess().getCommaKeyword_1_0());
            	    			
            	    // InternalPoST.g:2728:4: ( (lv_elements_2_0= ruleParamAssignment ) )
            	    // InternalPoST.g:2729:5: (lv_elements_2_0= ruleParamAssignment )
            	    {
            	    // InternalPoST.g:2729:5: (lv_elements_2_0= ruleParamAssignment )
            	    // InternalPoST.g:2730:6: lv_elements_2_0= ruleParamAssignment
            	    {

            	    						newCompositeNode(grammarAccess.getParamAssignmentElementsAccess().getElementsParamAssignmentParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_23);
            	    lv_elements_2_0=ruleParamAssignment();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getParamAssignmentElementsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_2_0,
            	    							"su.nsk.iae.post.PoST.ParamAssignment");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParamAssignmentElements"


    // $ANTLR start "entryRuleParamAssignment"
    // InternalPoST.g:2752:1: entryRuleParamAssignment returns [EObject current=null] : iv_ruleParamAssignment= ruleParamAssignment EOF ;
    public final EObject entryRuleParamAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParamAssignment = null;


        try {
            // InternalPoST.g:2752:56: (iv_ruleParamAssignment= ruleParamAssignment EOF )
            // InternalPoST.g:2753:2: iv_ruleParamAssignment= ruleParamAssignment EOF
            {
             newCompositeNode(grammarAccess.getParamAssignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParamAssignment=ruleParamAssignment();

            state._fsp--;

             current =iv_ruleParamAssignment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParamAssignment"


    // $ANTLR start "ruleParamAssignment"
    // InternalPoST.g:2759:1: ruleParamAssignment returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( (lv_value_2_0= ruleExpression ) ) ) ;
    public final EObject ruleParamAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Enumerator lv_assig_1_0 = null;

        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:2765:2: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( (lv_value_2_0= ruleExpression ) ) ) )
            // InternalPoST.g:2766:2: ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( (lv_value_2_0= ruleExpression ) ) )
            {
            // InternalPoST.g:2766:2: ( ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( (lv_value_2_0= ruleExpression ) ) )
            // InternalPoST.g:2767:3: ( (otherlv_0= RULE_ID ) ) ( (lv_assig_1_0= ruleAssignmentType ) ) ( (lv_value_2_0= ruleExpression ) )
            {
            // InternalPoST.g:2767:3: ( (otherlv_0= RULE_ID ) )
            // InternalPoST.g:2768:4: (otherlv_0= RULE_ID )
            {
            // InternalPoST.g:2768:4: (otherlv_0= RULE_ID )
            // InternalPoST.g:2769:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParamAssignmentRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_24); 

            					newLeafNode(otherlv_0, grammarAccess.getParamAssignmentAccess().getVariableSymbolicVariableCrossReference_0_0());
            				

            }


            }

            // InternalPoST.g:2780:3: ( (lv_assig_1_0= ruleAssignmentType ) )
            // InternalPoST.g:2781:4: (lv_assig_1_0= ruleAssignmentType )
            {
            // InternalPoST.g:2781:4: (lv_assig_1_0= ruleAssignmentType )
            // InternalPoST.g:2782:5: lv_assig_1_0= ruleAssignmentType
            {

            					newCompositeNode(grammarAccess.getParamAssignmentAccess().getAssigAssignmentTypeEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_51);
            lv_assig_1_0=ruleAssignmentType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParamAssignmentRule());
            					}
            					set(
            						current,
            						"assig",
            						lv_assig_1_0,
            						"su.nsk.iae.post.PoST.AssignmentType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:2799:3: ( (lv_value_2_0= ruleExpression ) )
            // InternalPoST.g:2800:4: (lv_value_2_0= ruleExpression )
            {
            // InternalPoST.g:2800:4: (lv_value_2_0= ruleExpression )
            // InternalPoST.g:2801:5: lv_value_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getParamAssignmentAccess().getValueExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParamAssignmentRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParamAssignment"


    // $ANTLR start "entryRuleExpression"
    // InternalPoST.g:2822:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalPoST.g:2822:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalPoST.g:2823:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalPoST.g:2829:1: ruleExpression returns [EObject current=null] : (this_XorExpression_0= ruleXorExpression ( () this_OR_OPERATOR_2= RULE_OR_OPERATOR ( (lv_right_3_0= ruleXorExpression ) ) )* ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        Token this_OR_OPERATOR_2=null;
        EObject this_XorExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:2835:2: ( (this_XorExpression_0= ruleXorExpression ( () this_OR_OPERATOR_2= RULE_OR_OPERATOR ( (lv_right_3_0= ruleXorExpression ) ) )* ) )
            // InternalPoST.g:2836:2: (this_XorExpression_0= ruleXorExpression ( () this_OR_OPERATOR_2= RULE_OR_OPERATOR ( (lv_right_3_0= ruleXorExpression ) ) )* )
            {
            // InternalPoST.g:2836:2: (this_XorExpression_0= ruleXorExpression ( () this_OR_OPERATOR_2= RULE_OR_OPERATOR ( (lv_right_3_0= ruleXorExpression ) ) )* )
            // InternalPoST.g:2837:3: this_XorExpression_0= ruleXorExpression ( () this_OR_OPERATOR_2= RULE_OR_OPERATOR ( (lv_right_3_0= ruleXorExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getExpressionAccess().getXorExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_52);
            this_XorExpression_0=ruleXorExpression();

            state._fsp--;


            			current = this_XorExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalPoST.g:2845:3: ( () this_OR_OPERATOR_2= RULE_OR_OPERATOR ( (lv_right_3_0= ruleXorExpression ) ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==RULE_OR_OPERATOR) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalPoST.g:2846:4: () this_OR_OPERATOR_2= RULE_OR_OPERATOR ( (lv_right_3_0= ruleXorExpression ) )
            	    {
            	    // InternalPoST.g:2846:4: ()
            	    // InternalPoST.g:2847:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getExpressionAccess().getExpressionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    this_OR_OPERATOR_2=(Token)match(input,RULE_OR_OPERATOR,FOLLOW_51); 

            	    				newLeafNode(this_OR_OPERATOR_2, grammarAccess.getExpressionAccess().getOR_OPERATORTerminalRuleCall_1_1());
            	    			
            	    // InternalPoST.g:2857:4: ( (lv_right_3_0= ruleXorExpression ) )
            	    // InternalPoST.g:2858:5: (lv_right_3_0= ruleXorExpression )
            	    {
            	    // InternalPoST.g:2858:5: (lv_right_3_0= ruleXorExpression )
            	    // InternalPoST.g:2859:6: lv_right_3_0= ruleXorExpression
            	    {

            	    						newCompositeNode(grammarAccess.getExpressionAccess().getRightXorExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_52);
            	    lv_right_3_0=ruleXorExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"su.nsk.iae.post.PoST.XorExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleXorExpression"
    // InternalPoST.g:2881:1: entryRuleXorExpression returns [EObject current=null] : iv_ruleXorExpression= ruleXorExpression EOF ;
    public final EObject entryRuleXorExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXorExpression = null;


        try {
            // InternalPoST.g:2881:54: (iv_ruleXorExpression= ruleXorExpression EOF )
            // InternalPoST.g:2882:2: iv_ruleXorExpression= ruleXorExpression EOF
            {
             newCompositeNode(grammarAccess.getXorExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXorExpression=ruleXorExpression();

            state._fsp--;

             current =iv_ruleXorExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXorExpression"


    // $ANTLR start "ruleXorExpression"
    // InternalPoST.g:2888:1: ruleXorExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () this_XOR_OPERATOR_2= RULE_XOR_OPERATOR ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleXorExpression() throws RecognitionException {
        EObject current = null;

        Token this_XOR_OPERATOR_2=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:2894:2: ( (this_AndExpression_0= ruleAndExpression ( () this_XOR_OPERATOR_2= RULE_XOR_OPERATOR ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // InternalPoST.g:2895:2: (this_AndExpression_0= ruleAndExpression ( () this_XOR_OPERATOR_2= RULE_XOR_OPERATOR ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // InternalPoST.g:2895:2: (this_AndExpression_0= ruleAndExpression ( () this_XOR_OPERATOR_2= RULE_XOR_OPERATOR ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // InternalPoST.g:2896:3: this_AndExpression_0= ruleAndExpression ( () this_XOR_OPERATOR_2= RULE_XOR_OPERATOR ( (lv_right_3_0= ruleAndExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getXorExpressionAccess().getAndExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_53);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;


            			current = this_AndExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalPoST.g:2904:3: ( () this_XOR_OPERATOR_2= RULE_XOR_OPERATOR ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==RULE_XOR_OPERATOR) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalPoST.g:2905:4: () this_XOR_OPERATOR_2= RULE_XOR_OPERATOR ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // InternalPoST.g:2905:4: ()
            	    // InternalPoST.g:2906:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXorExpressionAccess().getXorExpressionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    this_XOR_OPERATOR_2=(Token)match(input,RULE_XOR_OPERATOR,FOLLOW_51); 

            	    				newLeafNode(this_XOR_OPERATOR_2, grammarAccess.getXorExpressionAccess().getXOR_OPERATORTerminalRuleCall_1_1());
            	    			
            	    // InternalPoST.g:2916:4: ( (lv_right_3_0= ruleAndExpression ) )
            	    // InternalPoST.g:2917:5: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // InternalPoST.g:2917:5: (lv_right_3_0= ruleAndExpression )
            	    // InternalPoST.g:2918:6: lv_right_3_0= ruleAndExpression
            	    {

            	    						newCompositeNode(grammarAccess.getXorExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_53);
            	    lv_right_3_0=ruleAndExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXorExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"su.nsk.iae.post.PoST.AndExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXorExpression"


    // $ANTLR start "entryRuleAndExpression"
    // InternalPoST.g:2940:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // InternalPoST.g:2940:54: (iv_ruleAndExpression= ruleAndExpression EOF )
            // InternalPoST.g:2941:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
             newCompositeNode(grammarAccess.getAndExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;

             current =iv_ruleAndExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndExpression"


    // $ANTLR start "ruleAndExpression"
    // InternalPoST.g:2947:1: ruleAndExpression returns [EObject current=null] : (this_CompExpression_0= ruleCompExpression ( () this_AND_OPERATOR_2= RULE_AND_OPERATOR ( (lv_right_3_0= ruleCompExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token this_AND_OPERATOR_2=null;
        EObject this_CompExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:2953:2: ( (this_CompExpression_0= ruleCompExpression ( () this_AND_OPERATOR_2= RULE_AND_OPERATOR ( (lv_right_3_0= ruleCompExpression ) ) )* ) )
            // InternalPoST.g:2954:2: (this_CompExpression_0= ruleCompExpression ( () this_AND_OPERATOR_2= RULE_AND_OPERATOR ( (lv_right_3_0= ruleCompExpression ) ) )* )
            {
            // InternalPoST.g:2954:2: (this_CompExpression_0= ruleCompExpression ( () this_AND_OPERATOR_2= RULE_AND_OPERATOR ( (lv_right_3_0= ruleCompExpression ) ) )* )
            // InternalPoST.g:2955:3: this_CompExpression_0= ruleCompExpression ( () this_AND_OPERATOR_2= RULE_AND_OPERATOR ( (lv_right_3_0= ruleCompExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getAndExpressionAccess().getCompExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_54);
            this_CompExpression_0=ruleCompExpression();

            state._fsp--;


            			current = this_CompExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalPoST.g:2963:3: ( () this_AND_OPERATOR_2= RULE_AND_OPERATOR ( (lv_right_3_0= ruleCompExpression ) ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==RULE_AND_OPERATOR) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalPoST.g:2964:4: () this_AND_OPERATOR_2= RULE_AND_OPERATOR ( (lv_right_3_0= ruleCompExpression ) )
            	    {
            	    // InternalPoST.g:2964:4: ()
            	    // InternalPoST.g:2965:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAndExpressionAccess().getAndExpressionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    this_AND_OPERATOR_2=(Token)match(input,RULE_AND_OPERATOR,FOLLOW_51); 

            	    				newLeafNode(this_AND_OPERATOR_2, grammarAccess.getAndExpressionAccess().getAND_OPERATORTerminalRuleCall_1_1());
            	    			
            	    // InternalPoST.g:2975:4: ( (lv_right_3_0= ruleCompExpression ) )
            	    // InternalPoST.g:2976:5: (lv_right_3_0= ruleCompExpression )
            	    {
            	    // InternalPoST.g:2976:5: (lv_right_3_0= ruleCompExpression )
            	    // InternalPoST.g:2977:6: lv_right_3_0= ruleCompExpression
            	    {

            	    						newCompositeNode(grammarAccess.getAndExpressionAccess().getRightCompExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_54);
            	    lv_right_3_0=ruleCompExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAndExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"su.nsk.iae.post.PoST.CompExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAndExpression"


    // $ANTLR start "entryRuleCompExpression"
    // InternalPoST.g:2999:1: entryRuleCompExpression returns [EObject current=null] : iv_ruleCompExpression= ruleCompExpression EOF ;
    public final EObject entryRuleCompExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompExpression = null;


        try {
            // InternalPoST.g:2999:55: (iv_ruleCompExpression= ruleCompExpression EOF )
            // InternalPoST.g:3000:2: iv_ruleCompExpression= ruleCompExpression EOF
            {
             newCompositeNode(grammarAccess.getCompExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCompExpression=ruleCompExpression();

            state._fsp--;

             current =iv_ruleCompExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCompExpression"


    // $ANTLR start "ruleCompExpression"
    // InternalPoST.g:3006:1: ruleCompExpression returns [EObject current=null] : (this_EquExpression_0= ruleEquExpression ( () ( (lv_compOp_2_0= ruleCompOperator ) ) ( (lv_right_3_0= ruleEquExpression ) ) )* ) ;
    public final EObject ruleCompExpression() throws RecognitionException {
        EObject current = null;

        EObject this_EquExpression_0 = null;

        Enumerator lv_compOp_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3012:2: ( (this_EquExpression_0= ruleEquExpression ( () ( (lv_compOp_2_0= ruleCompOperator ) ) ( (lv_right_3_0= ruleEquExpression ) ) )* ) )
            // InternalPoST.g:3013:2: (this_EquExpression_0= ruleEquExpression ( () ( (lv_compOp_2_0= ruleCompOperator ) ) ( (lv_right_3_0= ruleEquExpression ) ) )* )
            {
            // InternalPoST.g:3013:2: (this_EquExpression_0= ruleEquExpression ( () ( (lv_compOp_2_0= ruleCompOperator ) ) ( (lv_right_3_0= ruleEquExpression ) ) )* )
            // InternalPoST.g:3014:3: this_EquExpression_0= ruleEquExpression ( () ( (lv_compOp_2_0= ruleCompOperator ) ) ( (lv_right_3_0= ruleEquExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getCompExpressionAccess().getEquExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_55);
            this_EquExpression_0=ruleEquExpression();

            state._fsp--;


            			current = this_EquExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalPoST.g:3022:3: ( () ( (lv_compOp_2_0= ruleCompOperator ) ) ( (lv_right_3_0= ruleEquExpression ) ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( ((LA41_0>=117 && LA41_0<=118)) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalPoST.g:3023:4: () ( (lv_compOp_2_0= ruleCompOperator ) ) ( (lv_right_3_0= ruleEquExpression ) )
            	    {
            	    // InternalPoST.g:3023:4: ()
            	    // InternalPoST.g:3024:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getCompExpressionAccess().getCompExpressionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalPoST.g:3030:4: ( (lv_compOp_2_0= ruleCompOperator ) )
            	    // InternalPoST.g:3031:5: (lv_compOp_2_0= ruleCompOperator )
            	    {
            	    // InternalPoST.g:3031:5: (lv_compOp_2_0= ruleCompOperator )
            	    // InternalPoST.g:3032:6: lv_compOp_2_0= ruleCompOperator
            	    {

            	    						newCompositeNode(grammarAccess.getCompExpressionAccess().getCompOpCompOperatorEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_51);
            	    lv_compOp_2_0=ruleCompOperator();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getCompExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"compOp",
            	    							lv_compOp_2_0,
            	    							"su.nsk.iae.post.PoST.CompOperator");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalPoST.g:3049:4: ( (lv_right_3_0= ruleEquExpression ) )
            	    // InternalPoST.g:3050:5: (lv_right_3_0= ruleEquExpression )
            	    {
            	    // InternalPoST.g:3050:5: (lv_right_3_0= ruleEquExpression )
            	    // InternalPoST.g:3051:6: lv_right_3_0= ruleEquExpression
            	    {

            	    						newCompositeNode(grammarAccess.getCompExpressionAccess().getRightEquExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_55);
            	    lv_right_3_0=ruleEquExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getCompExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"su.nsk.iae.post.PoST.EquExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCompExpression"


    // $ANTLR start "entryRuleEquExpression"
    // InternalPoST.g:3073:1: entryRuleEquExpression returns [EObject current=null] : iv_ruleEquExpression= ruleEquExpression EOF ;
    public final EObject entryRuleEquExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquExpression = null;


        try {
            // InternalPoST.g:3073:54: (iv_ruleEquExpression= ruleEquExpression EOF )
            // InternalPoST.g:3074:2: iv_ruleEquExpression= ruleEquExpression EOF
            {
             newCompositeNode(grammarAccess.getEquExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEquExpression=ruleEquExpression();

            state._fsp--;

             current =iv_ruleEquExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEquExpression"


    // $ANTLR start "ruleEquExpression"
    // InternalPoST.g:3080:1: ruleEquExpression returns [EObject current=null] : (this_AddExpression_0= ruleAddExpression ( () ( (lv_equOp_2_0= ruleEquOperator ) ) ( (lv_right_3_0= ruleAddExpression ) ) )* ) ;
    public final EObject ruleEquExpression() throws RecognitionException {
        EObject current = null;

        EObject this_AddExpression_0 = null;

        Enumerator lv_equOp_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3086:2: ( (this_AddExpression_0= ruleAddExpression ( () ( (lv_equOp_2_0= ruleEquOperator ) ) ( (lv_right_3_0= ruleAddExpression ) ) )* ) )
            // InternalPoST.g:3087:2: (this_AddExpression_0= ruleAddExpression ( () ( (lv_equOp_2_0= ruleEquOperator ) ) ( (lv_right_3_0= ruleAddExpression ) ) )* )
            {
            // InternalPoST.g:3087:2: (this_AddExpression_0= ruleAddExpression ( () ( (lv_equOp_2_0= ruleEquOperator ) ) ( (lv_right_3_0= ruleAddExpression ) ) )* )
            // InternalPoST.g:3088:3: this_AddExpression_0= ruleAddExpression ( () ( (lv_equOp_2_0= ruleEquOperator ) ) ( (lv_right_3_0= ruleAddExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getEquExpressionAccess().getAddExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_56);
            this_AddExpression_0=ruleAddExpression();

            state._fsp--;


            			current = this_AddExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalPoST.g:3096:3: ( () ( (lv_equOp_2_0= ruleEquOperator ) ) ( (lv_right_3_0= ruleAddExpression ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0>=119 && LA42_0<=122)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalPoST.g:3097:4: () ( (lv_equOp_2_0= ruleEquOperator ) ) ( (lv_right_3_0= ruleAddExpression ) )
            	    {
            	    // InternalPoST.g:3097:4: ()
            	    // InternalPoST.g:3098:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getEquExpressionAccess().getEquExpressionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalPoST.g:3104:4: ( (lv_equOp_2_0= ruleEquOperator ) )
            	    // InternalPoST.g:3105:5: (lv_equOp_2_0= ruleEquOperator )
            	    {
            	    // InternalPoST.g:3105:5: (lv_equOp_2_0= ruleEquOperator )
            	    // InternalPoST.g:3106:6: lv_equOp_2_0= ruleEquOperator
            	    {

            	    						newCompositeNode(grammarAccess.getEquExpressionAccess().getEquOpEquOperatorEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_51);
            	    lv_equOp_2_0=ruleEquOperator();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getEquExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"equOp",
            	    							lv_equOp_2_0,
            	    							"su.nsk.iae.post.PoST.EquOperator");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalPoST.g:3123:4: ( (lv_right_3_0= ruleAddExpression ) )
            	    // InternalPoST.g:3124:5: (lv_right_3_0= ruleAddExpression )
            	    {
            	    // InternalPoST.g:3124:5: (lv_right_3_0= ruleAddExpression )
            	    // InternalPoST.g:3125:6: lv_right_3_0= ruleAddExpression
            	    {

            	    						newCompositeNode(grammarAccess.getEquExpressionAccess().getRightAddExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_56);
            	    lv_right_3_0=ruleAddExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getEquExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"su.nsk.iae.post.PoST.AddExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEquExpression"


    // $ANTLR start "entryRuleAddExpression"
    // InternalPoST.g:3147:1: entryRuleAddExpression returns [EObject current=null] : iv_ruleAddExpression= ruleAddExpression EOF ;
    public final EObject entryRuleAddExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddExpression = null;


        try {
            // InternalPoST.g:3147:54: (iv_ruleAddExpression= ruleAddExpression EOF )
            // InternalPoST.g:3148:2: iv_ruleAddExpression= ruleAddExpression EOF
            {
             newCompositeNode(grammarAccess.getAddExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAddExpression=ruleAddExpression();

            state._fsp--;

             current =iv_ruleAddExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAddExpression"


    // $ANTLR start "ruleAddExpression"
    // InternalPoST.g:3154:1: ruleAddExpression returns [EObject current=null] : (this_MulExpression_0= ruleMulExpression ( () ( (lv_addOp_2_0= ruleAddOperator ) ) ( (lv_right_3_0= ruleMulExpression ) ) )* ) ;
    public final EObject ruleAddExpression() throws RecognitionException {
        EObject current = null;

        EObject this_MulExpression_0 = null;

        Enumerator lv_addOp_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3160:2: ( (this_MulExpression_0= ruleMulExpression ( () ( (lv_addOp_2_0= ruleAddOperator ) ) ( (lv_right_3_0= ruleMulExpression ) ) )* ) )
            // InternalPoST.g:3161:2: (this_MulExpression_0= ruleMulExpression ( () ( (lv_addOp_2_0= ruleAddOperator ) ) ( (lv_right_3_0= ruleMulExpression ) ) )* )
            {
            // InternalPoST.g:3161:2: (this_MulExpression_0= ruleMulExpression ( () ( (lv_addOp_2_0= ruleAddOperator ) ) ( (lv_right_3_0= ruleMulExpression ) ) )* )
            // InternalPoST.g:3162:3: this_MulExpression_0= ruleMulExpression ( () ( (lv_addOp_2_0= ruleAddOperator ) ) ( (lv_right_3_0= ruleMulExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getAddExpressionAccess().getMulExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_57);
            this_MulExpression_0=ruleMulExpression();

            state._fsp--;


            			current = this_MulExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalPoST.g:3170:3: ( () ( (lv_addOp_2_0= ruleAddOperator ) ) ( (lv_right_3_0= ruleMulExpression ) ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==114||LA43_0==123) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // InternalPoST.g:3171:4: () ( (lv_addOp_2_0= ruleAddOperator ) ) ( (lv_right_3_0= ruleMulExpression ) )
            	    {
            	    // InternalPoST.g:3171:4: ()
            	    // InternalPoST.g:3172:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAddExpressionAccess().getAddExpressionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalPoST.g:3178:4: ( (lv_addOp_2_0= ruleAddOperator ) )
            	    // InternalPoST.g:3179:5: (lv_addOp_2_0= ruleAddOperator )
            	    {
            	    // InternalPoST.g:3179:5: (lv_addOp_2_0= ruleAddOperator )
            	    // InternalPoST.g:3180:6: lv_addOp_2_0= ruleAddOperator
            	    {

            	    						newCompositeNode(grammarAccess.getAddExpressionAccess().getAddOpAddOperatorEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_51);
            	    lv_addOp_2_0=ruleAddOperator();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAddExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"addOp",
            	    							lv_addOp_2_0,
            	    							"su.nsk.iae.post.PoST.AddOperator");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalPoST.g:3197:4: ( (lv_right_3_0= ruleMulExpression ) )
            	    // InternalPoST.g:3198:5: (lv_right_3_0= ruleMulExpression )
            	    {
            	    // InternalPoST.g:3198:5: (lv_right_3_0= ruleMulExpression )
            	    // InternalPoST.g:3199:6: lv_right_3_0= ruleMulExpression
            	    {

            	    						newCompositeNode(grammarAccess.getAddExpressionAccess().getRightMulExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_57);
            	    lv_right_3_0=ruleMulExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAddExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"su.nsk.iae.post.PoST.MulExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAddExpression"


    // $ANTLR start "entryRuleMulExpression"
    // InternalPoST.g:3221:1: entryRuleMulExpression returns [EObject current=null] : iv_ruleMulExpression= ruleMulExpression EOF ;
    public final EObject entryRuleMulExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMulExpression = null;


        try {
            // InternalPoST.g:3221:54: (iv_ruleMulExpression= ruleMulExpression EOF )
            // InternalPoST.g:3222:2: iv_ruleMulExpression= ruleMulExpression EOF
            {
             newCompositeNode(grammarAccess.getMulExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMulExpression=ruleMulExpression();

            state._fsp--;

             current =iv_ruleMulExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMulExpression"


    // $ANTLR start "ruleMulExpression"
    // InternalPoST.g:3228:1: ruleMulExpression returns [EObject current=null] : (this_PowerExpression_0= rulePowerExpression ( () ( (lv_mulOp_2_0= ruleMulOperator ) ) ( (lv_right_3_0= rulePowerExpression ) ) )* ) ;
    public final EObject ruleMulExpression() throws RecognitionException {
        EObject current = null;

        EObject this_PowerExpression_0 = null;

        Enumerator lv_mulOp_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3234:2: ( (this_PowerExpression_0= rulePowerExpression ( () ( (lv_mulOp_2_0= ruleMulOperator ) ) ( (lv_right_3_0= rulePowerExpression ) ) )* ) )
            // InternalPoST.g:3235:2: (this_PowerExpression_0= rulePowerExpression ( () ( (lv_mulOp_2_0= ruleMulOperator ) ) ( (lv_right_3_0= rulePowerExpression ) ) )* )
            {
            // InternalPoST.g:3235:2: (this_PowerExpression_0= rulePowerExpression ( () ( (lv_mulOp_2_0= ruleMulOperator ) ) ( (lv_right_3_0= rulePowerExpression ) ) )* )
            // InternalPoST.g:3236:3: this_PowerExpression_0= rulePowerExpression ( () ( (lv_mulOp_2_0= ruleMulOperator ) ) ( (lv_right_3_0= rulePowerExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getMulExpressionAccess().getPowerExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_58);
            this_PowerExpression_0=rulePowerExpression();

            state._fsp--;


            			current = this_PowerExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalPoST.g:3244:3: ( () ( (lv_mulOp_2_0= ruleMulOperator ) ) ( (lv_right_3_0= rulePowerExpression ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==112||(LA44_0>=124 && LA44_0<=125)) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalPoST.g:3245:4: () ( (lv_mulOp_2_0= ruleMulOperator ) ) ( (lv_right_3_0= rulePowerExpression ) )
            	    {
            	    // InternalPoST.g:3245:4: ()
            	    // InternalPoST.g:3246:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getMulExpressionAccess().getMulExpressionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalPoST.g:3252:4: ( (lv_mulOp_2_0= ruleMulOperator ) )
            	    // InternalPoST.g:3253:5: (lv_mulOp_2_0= ruleMulOperator )
            	    {
            	    // InternalPoST.g:3253:5: (lv_mulOp_2_0= ruleMulOperator )
            	    // InternalPoST.g:3254:6: lv_mulOp_2_0= ruleMulOperator
            	    {

            	    						newCompositeNode(grammarAccess.getMulExpressionAccess().getMulOpMulOperatorEnumRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_51);
            	    lv_mulOp_2_0=ruleMulOperator();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMulExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"mulOp",
            	    							lv_mulOp_2_0,
            	    							"su.nsk.iae.post.PoST.MulOperator");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalPoST.g:3271:4: ( (lv_right_3_0= rulePowerExpression ) )
            	    // InternalPoST.g:3272:5: (lv_right_3_0= rulePowerExpression )
            	    {
            	    // InternalPoST.g:3272:5: (lv_right_3_0= rulePowerExpression )
            	    // InternalPoST.g:3273:6: lv_right_3_0= rulePowerExpression
            	    {

            	    						newCompositeNode(grammarAccess.getMulExpressionAccess().getRightPowerExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_58);
            	    lv_right_3_0=rulePowerExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMulExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"su.nsk.iae.post.PoST.PowerExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMulExpression"


    // $ANTLR start "entryRulePowerExpression"
    // InternalPoST.g:3295:1: entryRulePowerExpression returns [EObject current=null] : iv_rulePowerExpression= rulePowerExpression EOF ;
    public final EObject entryRulePowerExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePowerExpression = null;


        try {
            // InternalPoST.g:3295:56: (iv_rulePowerExpression= rulePowerExpression EOF )
            // InternalPoST.g:3296:2: iv_rulePowerExpression= rulePowerExpression EOF
            {
             newCompositeNode(grammarAccess.getPowerExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePowerExpression=rulePowerExpression();

            state._fsp--;

             current =iv_rulePowerExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePowerExpression"


    // $ANTLR start "rulePowerExpression"
    // InternalPoST.g:3302:1: rulePowerExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression ( () this_POWER_OPERATOR_2= RULE_POWER_OPERATOR ( (lv_right_3_0= ruleUnaryExpression ) ) )* ) ;
    public final EObject rulePowerExpression() throws RecognitionException {
        EObject current = null;

        Token this_POWER_OPERATOR_2=null;
        EObject this_UnaryExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3308:2: ( (this_UnaryExpression_0= ruleUnaryExpression ( () this_POWER_OPERATOR_2= RULE_POWER_OPERATOR ( (lv_right_3_0= ruleUnaryExpression ) ) )* ) )
            // InternalPoST.g:3309:2: (this_UnaryExpression_0= ruleUnaryExpression ( () this_POWER_OPERATOR_2= RULE_POWER_OPERATOR ( (lv_right_3_0= ruleUnaryExpression ) ) )* )
            {
            // InternalPoST.g:3309:2: (this_UnaryExpression_0= ruleUnaryExpression ( () this_POWER_OPERATOR_2= RULE_POWER_OPERATOR ( (lv_right_3_0= ruleUnaryExpression ) ) )* )
            // InternalPoST.g:3310:3: this_UnaryExpression_0= ruleUnaryExpression ( () this_POWER_OPERATOR_2= RULE_POWER_OPERATOR ( (lv_right_3_0= ruleUnaryExpression ) ) )*
            {

            			newCompositeNode(grammarAccess.getPowerExpressionAccess().getUnaryExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_59);
            this_UnaryExpression_0=ruleUnaryExpression();

            state._fsp--;


            			current = this_UnaryExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalPoST.g:3318:3: ( () this_POWER_OPERATOR_2= RULE_POWER_OPERATOR ( (lv_right_3_0= ruleUnaryExpression ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==RULE_POWER_OPERATOR) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalPoST.g:3319:4: () this_POWER_OPERATOR_2= RULE_POWER_OPERATOR ( (lv_right_3_0= ruleUnaryExpression ) )
            	    {
            	    // InternalPoST.g:3319:4: ()
            	    // InternalPoST.g:3320:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getPowerExpressionAccess().getPowerExpressionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    this_POWER_OPERATOR_2=(Token)match(input,RULE_POWER_OPERATOR,FOLLOW_51); 

            	    				newLeafNode(this_POWER_OPERATOR_2, grammarAccess.getPowerExpressionAccess().getPOWER_OPERATORTerminalRuleCall_1_1());
            	    			
            	    // InternalPoST.g:3330:4: ( (lv_right_3_0= ruleUnaryExpression ) )
            	    // InternalPoST.g:3331:5: (lv_right_3_0= ruleUnaryExpression )
            	    {
            	    // InternalPoST.g:3331:5: (lv_right_3_0= ruleUnaryExpression )
            	    // InternalPoST.g:3332:6: lv_right_3_0= ruleUnaryExpression
            	    {

            	    						newCompositeNode(grammarAccess.getPowerExpressionAccess().getRightUnaryExpressionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_59);
            	    lv_right_3_0=ruleUnaryExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPowerExpressionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"su.nsk.iae.post.PoST.UnaryExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePowerExpression"


    // $ANTLR start "entryRuleUnaryExpression"
    // InternalPoST.g:3354:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // InternalPoST.g:3354:56: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // InternalPoST.g:3355:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
             newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;

             current =iv_ruleUnaryExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnaryExpression"


    // $ANTLR start "ruleUnaryExpression"
    // InternalPoST.g:3361:1: ruleUnaryExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression | ( ( (lv_unOp_1_0= ruleUnaryOperator ) ) ( (lv_right_2_0= rulePrimaryExpression ) ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject this_PrimaryExpression_0 = null;

        Enumerator lv_unOp_1_0 = null;

        EObject lv_right_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3367:2: ( (this_PrimaryExpression_0= rulePrimaryExpression | ( ( (lv_unOp_1_0= ruleUnaryOperator ) ) ( (lv_right_2_0= rulePrimaryExpression ) ) ) ) )
            // InternalPoST.g:3368:2: (this_PrimaryExpression_0= rulePrimaryExpression | ( ( (lv_unOp_1_0= ruleUnaryOperator ) ) ( (lv_right_2_0= rulePrimaryExpression ) ) ) )
            {
            // InternalPoST.g:3368:2: (this_PrimaryExpression_0= rulePrimaryExpression | ( ( (lv_unOp_1_0= ruleUnaryOperator ) ) ( (lv_right_2_0= rulePrimaryExpression ) ) ) )
            int alt46=2;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case RULE_INTEGER:
            case RULE_TIME_PREF_LITERAL:
            case RULE_REAL_TYPE_NAME:
            case RULE_SIGNED_INTEGER_TYPE_NAME:
            case RULE_UNSIGNED_INTEGER_TYPE_NAME:
            case RULE_STRING_LITERAL:
            case RULE_BINARY_INTEGER:
            case RULE_OCTAL_INTEGER:
            case RULE_HEX_INTEGER:
            case RULE_BOOLEAN_LITERAL:
            case RULE_REAL:
            case 47:
            case 54:
            case 78:
                {
                alt46=1;
                }
                break;
            case 114:
                {
                switch ( input.LA(2) ) {
                case RULE_REAL:
                    {
                    alt46=1;
                    }
                    break;
                case RULE_INTEGER:
                    {
                    alt46=1;
                    }
                    break;
                case RULE_ID:
                case RULE_TIME_PREF_LITERAL:
                case RULE_REAL_TYPE_NAME:
                case RULE_SIGNED_INTEGER_TYPE_NAME:
                case RULE_UNSIGNED_INTEGER_TYPE_NAME:
                case RULE_STRING_LITERAL:
                case RULE_BINARY_INTEGER:
                case RULE_OCTAL_INTEGER:
                case RULE_HEX_INTEGER:
                case RULE_BOOLEAN_LITERAL:
                case 47:
                case 54:
                case 78:
                case 114:
                    {
                    alt46=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 2, input);

                    throw nvae;
                }

                }
                break;
            case 126:
                {
                alt46=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // InternalPoST.g:3369:3: this_PrimaryExpression_0= rulePrimaryExpression
                    {

                    			newCompositeNode(grammarAccess.getUnaryExpressionAccess().getPrimaryExpressionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_PrimaryExpression_0=rulePrimaryExpression();

                    state._fsp--;


                    			current = this_PrimaryExpression_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:3378:3: ( ( (lv_unOp_1_0= ruleUnaryOperator ) ) ( (lv_right_2_0= rulePrimaryExpression ) ) )
                    {
                    // InternalPoST.g:3378:3: ( ( (lv_unOp_1_0= ruleUnaryOperator ) ) ( (lv_right_2_0= rulePrimaryExpression ) ) )
                    // InternalPoST.g:3379:4: ( (lv_unOp_1_0= ruleUnaryOperator ) ) ( (lv_right_2_0= rulePrimaryExpression ) )
                    {
                    // InternalPoST.g:3379:4: ( (lv_unOp_1_0= ruleUnaryOperator ) )
                    // InternalPoST.g:3380:5: (lv_unOp_1_0= ruleUnaryOperator )
                    {
                    // InternalPoST.g:3380:5: (lv_unOp_1_0= ruleUnaryOperator )
                    // InternalPoST.g:3381:6: lv_unOp_1_0= ruleUnaryOperator
                    {

                    						newCompositeNode(grammarAccess.getUnaryExpressionAccess().getUnOpUnaryOperatorEnumRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_60);
                    lv_unOp_1_0=ruleUnaryOperator();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getUnaryExpressionRule());
                    						}
                    						set(
                    							current,
                    							"unOp",
                    							lv_unOp_1_0,
                    							"su.nsk.iae.post.PoST.UnaryOperator");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalPoST.g:3398:4: ( (lv_right_2_0= rulePrimaryExpression ) )
                    // InternalPoST.g:3399:5: (lv_right_2_0= rulePrimaryExpression )
                    {
                    // InternalPoST.g:3399:5: (lv_right_2_0= rulePrimaryExpression )
                    // InternalPoST.g:3400:6: lv_right_2_0= rulePrimaryExpression
                    {

                    						newCompositeNode(grammarAccess.getUnaryExpressionAccess().getRightPrimaryExpressionParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_right_2_0=rulePrimaryExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getUnaryExpressionRule());
                    						}
                    						set(
                    							current,
                    							"right",
                    							lv_right_2_0,
                    							"su.nsk.iae.post.PoST.PrimaryExpression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryExpression"


    // $ANTLR start "entryRuleTimeDeltaExpression"
    // InternalPoST.g:3422:1: entryRuleTimeDeltaExpression returns [EObject current=null] : iv_ruleTimeDeltaExpression= ruleTimeDeltaExpression EOF ;
    public final EObject entryRuleTimeDeltaExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimeDeltaExpression = null;


        try {
            // InternalPoST.g:3422:60: (iv_ruleTimeDeltaExpression= ruleTimeDeltaExpression EOF )
            // InternalPoST.g:3423:2: iv_ruleTimeDeltaExpression= ruleTimeDeltaExpression EOF
            {
             newCompositeNode(grammarAccess.getTimeDeltaExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTimeDeltaExpression=ruleTimeDeltaExpression();

            state._fsp--;

             current =iv_ruleTimeDeltaExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTimeDeltaExpression"


    // $ANTLR start "ruleTimeDeltaExpression"
    // InternalPoST.g:3429:1: ruleTimeDeltaExpression returns [EObject current=null] : ( () otherlv_1= '#dT' ) ;
    public final EObject ruleTimeDeltaExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalPoST.g:3435:2: ( ( () otherlv_1= '#dT' ) )
            // InternalPoST.g:3436:2: ( () otherlv_1= '#dT' )
            {
            // InternalPoST.g:3436:2: ( () otherlv_1= '#dT' )
            // InternalPoST.g:3437:3: () otherlv_1= '#dT'
            {
            // InternalPoST.g:3437:3: ()
            // InternalPoST.g:3438:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTimeDeltaExpressionAccess().getTimeDeltaExpressionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,78,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getTimeDeltaExpressionAccess().getDTKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTimeDeltaExpression"


    // $ANTLR start "entryRulePrimaryExpression"
    // InternalPoST.g:3452:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // InternalPoST.g:3452:58: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // InternalPoST.g:3453:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
             newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;

             current =iv_rulePrimaryExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimaryExpression"


    // $ANTLR start "rulePrimaryExpression"
    // InternalPoST.g:3459:1: rulePrimaryExpression returns [EObject current=null] : ( ( (lv_const_0_0= ruleConstant ) ) | ( (otherlv_1= RULE_ID ) ) | ( (lv_array_2_0= ruleArrayVariable ) ) | ( (lv_procStatus_3_0= ruleProcessStatusExpression ) ) | ( (lv_funCall_4_0= ruleFunctionCall ) ) | ( (lv_timeDelta_5_0= ruleTimeDeltaExpression ) ) | (otherlv_6= '(' ( (lv_nestExpr_7_0= ruleExpression ) ) otherlv_8= ')' ) ) ;
    public final EObject rulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_const_0_0 = null;

        EObject lv_array_2_0 = null;

        EObject lv_procStatus_3_0 = null;

        EObject lv_funCall_4_0 = null;

        EObject lv_timeDelta_5_0 = null;

        EObject lv_nestExpr_7_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3465:2: ( ( ( (lv_const_0_0= ruleConstant ) ) | ( (otherlv_1= RULE_ID ) ) | ( (lv_array_2_0= ruleArrayVariable ) ) | ( (lv_procStatus_3_0= ruleProcessStatusExpression ) ) | ( (lv_funCall_4_0= ruleFunctionCall ) ) | ( (lv_timeDelta_5_0= ruleTimeDeltaExpression ) ) | (otherlv_6= '(' ( (lv_nestExpr_7_0= ruleExpression ) ) otherlv_8= ')' ) ) )
            // InternalPoST.g:3466:2: ( ( (lv_const_0_0= ruleConstant ) ) | ( (otherlv_1= RULE_ID ) ) | ( (lv_array_2_0= ruleArrayVariable ) ) | ( (lv_procStatus_3_0= ruleProcessStatusExpression ) ) | ( (lv_funCall_4_0= ruleFunctionCall ) ) | ( (lv_timeDelta_5_0= ruleTimeDeltaExpression ) ) | (otherlv_6= '(' ( (lv_nestExpr_7_0= ruleExpression ) ) otherlv_8= ')' ) )
            {
            // InternalPoST.g:3466:2: ( ( (lv_const_0_0= ruleConstant ) ) | ( (otherlv_1= RULE_ID ) ) | ( (lv_array_2_0= ruleArrayVariable ) ) | ( (lv_procStatus_3_0= ruleProcessStatusExpression ) ) | ( (lv_funCall_4_0= ruleFunctionCall ) ) | ( (lv_timeDelta_5_0= ruleTimeDeltaExpression ) ) | (otherlv_6= '(' ( (lv_nestExpr_7_0= ruleExpression ) ) otherlv_8= ')' ) )
            int alt47=7;
            switch ( input.LA(1) ) {
            case RULE_INTEGER:
            case RULE_TIME_PREF_LITERAL:
            case RULE_REAL_TYPE_NAME:
            case RULE_SIGNED_INTEGER_TYPE_NAME:
            case RULE_UNSIGNED_INTEGER_TYPE_NAME:
            case RULE_STRING_LITERAL:
            case RULE_BINARY_INTEGER:
            case RULE_OCTAL_INTEGER:
            case RULE_HEX_INTEGER:
            case RULE_BOOLEAN_LITERAL:
            case RULE_REAL:
            case 114:
                {
                alt47=1;
                }
                break;
            case RULE_ID:
                {
                switch ( input.LA(2) ) {
                case 47:
                    {
                    alt47=5;
                    }
                    break;
                case 109:
                    {
                    alt47=3;
                    }
                    break;
                case EOF:
                case RULE_OR_OPERATOR:
                case RULE_XOR_OPERATOR:
                case RULE_AND_OPERATOR:
                case RULE_POWER_OPERATOR:
                case 45:
                case 48:
                case 50:
                case 74:
                case 84:
                case 87:
                case 89:
                case 90:
                case 95:
                case 110:
                case 112:
                case 113:
                case 114:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                    {
                    alt47=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 47, 2, input);

                    throw nvae;
                }

                }
                break;
            case 54:
                {
                alt47=4;
                }
                break;
            case 78:
                {
                alt47=6;
                }
                break;
            case 47:
                {
                alt47=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }

            switch (alt47) {
                case 1 :
                    // InternalPoST.g:3467:3: ( (lv_const_0_0= ruleConstant ) )
                    {
                    // InternalPoST.g:3467:3: ( (lv_const_0_0= ruleConstant ) )
                    // InternalPoST.g:3468:4: (lv_const_0_0= ruleConstant )
                    {
                    // InternalPoST.g:3468:4: (lv_const_0_0= ruleConstant )
                    // InternalPoST.g:3469:5: lv_const_0_0= ruleConstant
                    {

                    					newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getConstConstantParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_const_0_0=ruleConstant();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
                    					}
                    					set(
                    						current,
                    						"const",
                    						lv_const_0_0,
                    						"su.nsk.iae.post.PoST.Constant");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:3487:3: ( (otherlv_1= RULE_ID ) )
                    {
                    // InternalPoST.g:3487:3: ( (otherlv_1= RULE_ID ) )
                    // InternalPoST.g:3488:4: (otherlv_1= RULE_ID )
                    {
                    // InternalPoST.g:3488:4: (otherlv_1= RULE_ID )
                    // InternalPoST.g:3489:5: otherlv_1= RULE_ID
                    {

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getPrimaryExpressionRule());
                    					}
                    				
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_2); 

                    					newLeafNode(otherlv_1, grammarAccess.getPrimaryExpressionAccess().getVariableSymbolicVariableCrossReference_1_0());
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalPoST.g:3501:3: ( (lv_array_2_0= ruleArrayVariable ) )
                    {
                    // InternalPoST.g:3501:3: ( (lv_array_2_0= ruleArrayVariable ) )
                    // InternalPoST.g:3502:4: (lv_array_2_0= ruleArrayVariable )
                    {
                    // InternalPoST.g:3502:4: (lv_array_2_0= ruleArrayVariable )
                    // InternalPoST.g:3503:5: lv_array_2_0= ruleArrayVariable
                    {

                    					newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getArrayArrayVariableParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_array_2_0=ruleArrayVariable();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
                    					}
                    					set(
                    						current,
                    						"array",
                    						lv_array_2_0,
                    						"su.nsk.iae.post.PoST.ArrayVariable");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalPoST.g:3521:3: ( (lv_procStatus_3_0= ruleProcessStatusExpression ) )
                    {
                    // InternalPoST.g:3521:3: ( (lv_procStatus_3_0= ruleProcessStatusExpression ) )
                    // InternalPoST.g:3522:4: (lv_procStatus_3_0= ruleProcessStatusExpression )
                    {
                    // InternalPoST.g:3522:4: (lv_procStatus_3_0= ruleProcessStatusExpression )
                    // InternalPoST.g:3523:5: lv_procStatus_3_0= ruleProcessStatusExpression
                    {

                    					newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getProcStatusProcessStatusExpressionParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_procStatus_3_0=ruleProcessStatusExpression();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
                    					}
                    					set(
                    						current,
                    						"procStatus",
                    						lv_procStatus_3_0,
                    						"su.nsk.iae.post.PoST.ProcessStatusExpression");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalPoST.g:3541:3: ( (lv_funCall_4_0= ruleFunctionCall ) )
                    {
                    // InternalPoST.g:3541:3: ( (lv_funCall_4_0= ruleFunctionCall ) )
                    // InternalPoST.g:3542:4: (lv_funCall_4_0= ruleFunctionCall )
                    {
                    // InternalPoST.g:3542:4: (lv_funCall_4_0= ruleFunctionCall )
                    // InternalPoST.g:3543:5: lv_funCall_4_0= ruleFunctionCall
                    {

                    					newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFunCallFunctionCallParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_funCall_4_0=ruleFunctionCall();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
                    					}
                    					set(
                    						current,
                    						"funCall",
                    						lv_funCall_4_0,
                    						"su.nsk.iae.post.PoST.FunctionCall");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 6 :
                    // InternalPoST.g:3561:3: ( (lv_timeDelta_5_0= ruleTimeDeltaExpression ) )
                    {
                    // InternalPoST.g:3561:3: ( (lv_timeDelta_5_0= ruleTimeDeltaExpression ) )
                    // InternalPoST.g:3562:4: (lv_timeDelta_5_0= ruleTimeDeltaExpression )
                    {
                    // InternalPoST.g:3562:4: (lv_timeDelta_5_0= ruleTimeDeltaExpression )
                    // InternalPoST.g:3563:5: lv_timeDelta_5_0= ruleTimeDeltaExpression
                    {

                    					newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getTimeDeltaTimeDeltaExpressionParserRuleCall_5_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_timeDelta_5_0=ruleTimeDeltaExpression();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
                    					}
                    					set(
                    						current,
                    						"timeDelta",
                    						lv_timeDelta_5_0,
                    						"su.nsk.iae.post.PoST.TimeDeltaExpression");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 7 :
                    // InternalPoST.g:3581:3: (otherlv_6= '(' ( (lv_nestExpr_7_0= ruleExpression ) ) otherlv_8= ')' )
                    {
                    // InternalPoST.g:3581:3: (otherlv_6= '(' ( (lv_nestExpr_7_0= ruleExpression ) ) otherlv_8= ')' )
                    // InternalPoST.g:3582:4: otherlv_6= '(' ( (lv_nestExpr_7_0= ruleExpression ) ) otherlv_8= ')'
                    {
                    otherlv_6=(Token)match(input,47,FOLLOW_51); 

                    				newLeafNode(otherlv_6, grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_6_0());
                    			
                    // InternalPoST.g:3586:4: ( (lv_nestExpr_7_0= ruleExpression ) )
                    // InternalPoST.g:3587:5: (lv_nestExpr_7_0= ruleExpression )
                    {
                    // InternalPoST.g:3587:5: (lv_nestExpr_7_0= ruleExpression )
                    // InternalPoST.g:3588:6: lv_nestExpr_7_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getNestExprExpressionParserRuleCall_6_1_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_nestExpr_7_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPrimaryExpressionRule());
                    						}
                    						set(
                    							current,
                    							"nestExpr",
                    							lv_nestExpr_7_0,
                    							"su.nsk.iae.post.PoST.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_8=(Token)match(input,48,FOLLOW_2); 

                    				newLeafNode(otherlv_8, grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_6_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimaryExpression"


    // $ANTLR start "entryRuleStatementList"
    // InternalPoST.g:3614:1: entryRuleStatementList returns [EObject current=null] : iv_ruleStatementList= ruleStatementList EOF ;
    public final EObject entryRuleStatementList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatementList = null;


        try {
            // InternalPoST.g:3614:54: (iv_ruleStatementList= ruleStatementList EOF )
            // InternalPoST.g:3615:2: iv_ruleStatementList= ruleStatementList EOF
            {
             newCompositeNode(grammarAccess.getStatementListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStatementList=ruleStatementList();

            state._fsp--;

             current =iv_ruleStatementList; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStatementList"


    // $ANTLR start "ruleStatementList"
    // InternalPoST.g:3621:1: ruleStatementList returns [EObject current=null] : ( () ( (lv_statements_1_0= ruleStatement ) )* ) ;
    public final EObject ruleStatementList() throws RecognitionException {
        EObject current = null;

        EObject lv_statements_1_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3627:2: ( ( () ( (lv_statements_1_0= ruleStatement ) )* ) )
            // InternalPoST.g:3628:2: ( () ( (lv_statements_1_0= ruleStatement ) )* )
            {
            // InternalPoST.g:3628:2: ( () ( (lv_statements_1_0= ruleStatement ) )* )
            // InternalPoST.g:3629:3: () ( (lv_statements_1_0= ruleStatement ) )*
            {
            // InternalPoST.g:3629:3: ()
            // InternalPoST.g:3630:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStatementListAccess().getStatementListAction_0(),
            					current);
            			

            }

            // InternalPoST.g:3636:3: ( (lv_statements_1_0= ruleStatement ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==RULE_ID) ) {
                    int LA48_2 = input.LA(2);

                    if ( (LA48_2==47||LA48_2==49||LA48_2==109) ) {
                        alt48=1;
                    }


                }
                else if ( (LA48_0==RULE_INLINE_PHRASE||LA48_0==65||(LA48_0>=69 && LA48_0<=72)||LA48_0==76||LA48_0==79||LA48_0==83||LA48_0==86||LA48_0==91||LA48_0==93||(LA48_0>=96 && LA48_0<=97)) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalPoST.g:3637:4: (lv_statements_1_0= ruleStatement )
            	    {
            	    // InternalPoST.g:3637:4: (lv_statements_1_0= ruleStatement )
            	    // InternalPoST.g:3638:5: lv_statements_1_0= ruleStatement
            	    {

            	    					newCompositeNode(grammarAccess.getStatementListAccess().getStatementsStatementParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_61);
            	    lv_statements_1_0=ruleStatement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getStatementListRule());
            	    					}
            	    					add(
            	    						current,
            	    						"statements",
            	    						lv_statements_1_0,
            	    						"su.nsk.iae.post.PoST.Statement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStatementList"


    // $ANTLR start "entryRuleStatement"
    // InternalPoST.g:3659:1: entryRuleStatement returns [EObject current=null] : iv_ruleStatement= ruleStatement EOF ;
    public final EObject entryRuleStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatement = null;


        try {
            // InternalPoST.g:3659:50: (iv_ruleStatement= ruleStatement EOF )
            // InternalPoST.g:3660:2: iv_ruleStatement= ruleStatement EOF
            {
             newCompositeNode(grammarAccess.getStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStatement=ruleStatement();

            state._fsp--;

             current =iv_ruleStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStatement"


    // $ANTLR start "ruleStatement"
    // InternalPoST.g:3666:1: ruleStatement returns [EObject current=null] : ( (this_AssignmentStatement_0= ruleAssignmentStatement otherlv_1= ';' ) | this_SelectionStatement_2= ruleSelectionStatement | this_IterationStatement_3= ruleIterationStatement | this_Inline_code_4= ruleInline_code | (this_FBInvocation_5= ruleFBInvocation otherlv_6= ';' ) | (this_SubprogramControlStatement_7= ruleSubprogramControlStatement otherlv_8= ';' ) | (this_ExitStatement_9= ruleExitStatement otherlv_10= ';' ) | (this_ProcessStatements_11= ruleProcessStatements otherlv_12= ';' ) | (this_SetStateStatement_13= ruleSetStateStatement otherlv_14= ';' ) | (this_ResetTimerStatement_15= ruleResetTimerStatement otherlv_16= ';' ) ) ;
    public final EObject ruleStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        EObject this_AssignmentStatement_0 = null;

        EObject this_SelectionStatement_2 = null;

        EObject this_IterationStatement_3 = null;

        EObject this_Inline_code_4 = null;

        EObject this_FBInvocation_5 = null;

        EObject this_SubprogramControlStatement_7 = null;

        EObject this_ExitStatement_9 = null;

        EObject this_ProcessStatements_11 = null;

        EObject this_SetStateStatement_13 = null;

        EObject this_ResetTimerStatement_15 = null;



        	enterRule();

        try {
            // InternalPoST.g:3672:2: ( ( (this_AssignmentStatement_0= ruleAssignmentStatement otherlv_1= ';' ) | this_SelectionStatement_2= ruleSelectionStatement | this_IterationStatement_3= ruleIterationStatement | this_Inline_code_4= ruleInline_code | (this_FBInvocation_5= ruleFBInvocation otherlv_6= ';' ) | (this_SubprogramControlStatement_7= ruleSubprogramControlStatement otherlv_8= ';' ) | (this_ExitStatement_9= ruleExitStatement otherlv_10= ';' ) | (this_ProcessStatements_11= ruleProcessStatements otherlv_12= ';' ) | (this_SetStateStatement_13= ruleSetStateStatement otherlv_14= ';' ) | (this_ResetTimerStatement_15= ruleResetTimerStatement otherlv_16= ';' ) ) )
            // InternalPoST.g:3673:2: ( (this_AssignmentStatement_0= ruleAssignmentStatement otherlv_1= ';' ) | this_SelectionStatement_2= ruleSelectionStatement | this_IterationStatement_3= ruleIterationStatement | this_Inline_code_4= ruleInline_code | (this_FBInvocation_5= ruleFBInvocation otherlv_6= ';' ) | (this_SubprogramControlStatement_7= ruleSubprogramControlStatement otherlv_8= ';' ) | (this_ExitStatement_9= ruleExitStatement otherlv_10= ';' ) | (this_ProcessStatements_11= ruleProcessStatements otherlv_12= ';' ) | (this_SetStateStatement_13= ruleSetStateStatement otherlv_14= ';' ) | (this_ResetTimerStatement_15= ruleResetTimerStatement otherlv_16= ';' ) )
            {
            // InternalPoST.g:3673:2: ( (this_AssignmentStatement_0= ruleAssignmentStatement otherlv_1= ';' ) | this_SelectionStatement_2= ruleSelectionStatement | this_IterationStatement_3= ruleIterationStatement | this_Inline_code_4= ruleInline_code | (this_FBInvocation_5= ruleFBInvocation otherlv_6= ';' ) | (this_SubprogramControlStatement_7= ruleSubprogramControlStatement otherlv_8= ';' ) | (this_ExitStatement_9= ruleExitStatement otherlv_10= ';' ) | (this_ProcessStatements_11= ruleProcessStatements otherlv_12= ';' ) | (this_SetStateStatement_13= ruleSetStateStatement otherlv_14= ';' ) | (this_ResetTimerStatement_15= ruleResetTimerStatement otherlv_16= ';' ) )
            int alt49=10;
            alt49 = dfa49.predict(input);
            switch (alt49) {
                case 1 :
                    // InternalPoST.g:3674:3: (this_AssignmentStatement_0= ruleAssignmentStatement otherlv_1= ';' )
                    {
                    // InternalPoST.g:3674:3: (this_AssignmentStatement_0= ruleAssignmentStatement otherlv_1= ';' )
                    // InternalPoST.g:3675:4: this_AssignmentStatement_0= ruleAssignmentStatement otherlv_1= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getAssignmentStatementParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_9);
                    this_AssignmentStatement_0=ruleAssignmentStatement();

                    state._fsp--;


                    				current = this_AssignmentStatement_0;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_1=(Token)match(input,45,FOLLOW_2); 

                    				newLeafNode(otherlv_1, grammarAccess.getStatementAccess().getSemicolonKeyword_0_1());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:3689:3: this_SelectionStatement_2= ruleSelectionStatement
                    {

                    			newCompositeNode(grammarAccess.getStatementAccess().getSelectionStatementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_SelectionStatement_2=ruleSelectionStatement();

                    state._fsp--;


                    			current = this_SelectionStatement_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalPoST.g:3698:3: this_IterationStatement_3= ruleIterationStatement
                    {

                    			newCompositeNode(grammarAccess.getStatementAccess().getIterationStatementParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_IterationStatement_3=ruleIterationStatement();

                    state._fsp--;


                    			current = this_IterationStatement_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalPoST.g:3707:3: this_Inline_code_4= ruleInline_code
                    {

                    			newCompositeNode(grammarAccess.getStatementAccess().getInline_codeParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Inline_code_4=ruleInline_code();

                    state._fsp--;


                    			current = this_Inline_code_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalPoST.g:3716:3: (this_FBInvocation_5= ruleFBInvocation otherlv_6= ';' )
                    {
                    // InternalPoST.g:3716:3: (this_FBInvocation_5= ruleFBInvocation otherlv_6= ';' )
                    // InternalPoST.g:3717:4: this_FBInvocation_5= ruleFBInvocation otherlv_6= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getFBInvocationParserRuleCall_4_0());
                    			
                    pushFollow(FOLLOW_9);
                    this_FBInvocation_5=ruleFBInvocation();

                    state._fsp--;


                    				current = this_FBInvocation_5;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_6=(Token)match(input,45,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getStatementAccess().getSemicolonKeyword_4_1());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalPoST.g:3731:3: (this_SubprogramControlStatement_7= ruleSubprogramControlStatement otherlv_8= ';' )
                    {
                    // InternalPoST.g:3731:3: (this_SubprogramControlStatement_7= ruleSubprogramControlStatement otherlv_8= ';' )
                    // InternalPoST.g:3732:4: this_SubprogramControlStatement_7= ruleSubprogramControlStatement otherlv_8= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getSubprogramControlStatementParserRuleCall_5_0());
                    			
                    pushFollow(FOLLOW_9);
                    this_SubprogramControlStatement_7=ruleSubprogramControlStatement();

                    state._fsp--;


                    				current = this_SubprogramControlStatement_7;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_8=(Token)match(input,45,FOLLOW_2); 

                    				newLeafNode(otherlv_8, grammarAccess.getStatementAccess().getSemicolonKeyword_5_1());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalPoST.g:3746:3: (this_ExitStatement_9= ruleExitStatement otherlv_10= ';' )
                    {
                    // InternalPoST.g:3746:3: (this_ExitStatement_9= ruleExitStatement otherlv_10= ';' )
                    // InternalPoST.g:3747:4: this_ExitStatement_9= ruleExitStatement otherlv_10= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getExitStatementParserRuleCall_6_0());
                    			
                    pushFollow(FOLLOW_9);
                    this_ExitStatement_9=ruleExitStatement();

                    state._fsp--;


                    				current = this_ExitStatement_9;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_10=(Token)match(input,45,FOLLOW_2); 

                    				newLeafNode(otherlv_10, grammarAccess.getStatementAccess().getSemicolonKeyword_6_1());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalPoST.g:3761:3: (this_ProcessStatements_11= ruleProcessStatements otherlv_12= ';' )
                    {
                    // InternalPoST.g:3761:3: (this_ProcessStatements_11= ruleProcessStatements otherlv_12= ';' )
                    // InternalPoST.g:3762:4: this_ProcessStatements_11= ruleProcessStatements otherlv_12= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getProcessStatementsParserRuleCall_7_0());
                    			
                    pushFollow(FOLLOW_9);
                    this_ProcessStatements_11=ruleProcessStatements();

                    state._fsp--;


                    				current = this_ProcessStatements_11;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_12=(Token)match(input,45,FOLLOW_2); 

                    				newLeafNode(otherlv_12, grammarAccess.getStatementAccess().getSemicolonKeyword_7_1());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalPoST.g:3776:3: (this_SetStateStatement_13= ruleSetStateStatement otherlv_14= ';' )
                    {
                    // InternalPoST.g:3776:3: (this_SetStateStatement_13= ruleSetStateStatement otherlv_14= ';' )
                    // InternalPoST.g:3777:4: this_SetStateStatement_13= ruleSetStateStatement otherlv_14= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getSetStateStatementParserRuleCall_8_0());
                    			
                    pushFollow(FOLLOW_9);
                    this_SetStateStatement_13=ruleSetStateStatement();

                    state._fsp--;


                    				current = this_SetStateStatement_13;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_14=(Token)match(input,45,FOLLOW_2); 

                    				newLeafNode(otherlv_14, grammarAccess.getStatementAccess().getSemicolonKeyword_8_1());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalPoST.g:3791:3: (this_ResetTimerStatement_15= ruleResetTimerStatement otherlv_16= ';' )
                    {
                    // InternalPoST.g:3791:3: (this_ResetTimerStatement_15= ruleResetTimerStatement otherlv_16= ';' )
                    // InternalPoST.g:3792:4: this_ResetTimerStatement_15= ruleResetTimerStatement otherlv_16= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getResetTimerStatementParserRuleCall_9_0());
                    			
                    pushFollow(FOLLOW_9);
                    this_ResetTimerStatement_15=ruleResetTimerStatement();

                    state._fsp--;


                    				current = this_ResetTimerStatement_15;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_16=(Token)match(input,45,FOLLOW_2); 

                    				newLeafNode(otherlv_16, grammarAccess.getStatementAccess().getSemicolonKeyword_9_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStatement"


    // $ANTLR start "entryRuleInline_code"
    // InternalPoST.g:3809:1: entryRuleInline_code returns [EObject current=null] : iv_ruleInline_code= ruleInline_code EOF ;
    public final EObject entryRuleInline_code() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInline_code = null;


        try {
            // InternalPoST.g:3809:52: (iv_ruleInline_code= ruleInline_code EOF )
            // InternalPoST.g:3810:2: iv_ruleInline_code= ruleInline_code EOF
            {
             newCompositeNode(grammarAccess.getInline_codeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInline_code=ruleInline_code();

            state._fsp--;

             current =iv_ruleInline_code; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInline_code"


    // $ANTLR start "ruleInline_code"
    // InternalPoST.g:3816:1: ruleInline_code returns [EObject current=null] : ( () ( (lv_inline_code_1_0= RULE_INLINE_PHRASE ) )+ ) ;
    public final EObject ruleInline_code() throws RecognitionException {
        EObject current = null;

        Token lv_inline_code_1_0=null;


        	enterRule();

        try {
            // InternalPoST.g:3822:2: ( ( () ( (lv_inline_code_1_0= RULE_INLINE_PHRASE ) )+ ) )
            // InternalPoST.g:3823:2: ( () ( (lv_inline_code_1_0= RULE_INLINE_PHRASE ) )+ )
            {
            // InternalPoST.g:3823:2: ( () ( (lv_inline_code_1_0= RULE_INLINE_PHRASE ) )+ )
            // InternalPoST.g:3824:3: () ( (lv_inline_code_1_0= RULE_INLINE_PHRASE ) )+
            {
            // InternalPoST.g:3824:3: ()
            // InternalPoST.g:3825:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInline_codeAccess().getInline_codeAction_0(),
            					current);
            			

            }

            // InternalPoST.g:3831:3: ( (lv_inline_code_1_0= RULE_INLINE_PHRASE ) )+
            int cnt50=0;
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==RULE_INLINE_PHRASE) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // InternalPoST.g:3832:4: (lv_inline_code_1_0= RULE_INLINE_PHRASE )
            	    {
            	    // InternalPoST.g:3832:4: (lv_inline_code_1_0= RULE_INLINE_PHRASE )
            	    // InternalPoST.g:3833:5: lv_inline_code_1_0= RULE_INLINE_PHRASE
            	    {
            	    lv_inline_code_1_0=(Token)match(input,RULE_INLINE_PHRASE,FOLLOW_62); 

            	    					newLeafNode(lv_inline_code_1_0, grammarAccess.getInline_codeAccess().getInline_codeINLINE_PHRASETerminalRuleCall_1_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getInline_codeRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"inline_code",
            	    						lv_inline_code_1_0,
            	    						"su.nsk.iae.post.PoST.INLINE_PHRASE");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt50 >= 1 ) break loop50;
                        EarlyExitException eee =
                            new EarlyExitException(50, input);
                        throw eee;
                }
                cnt50++;
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInline_code"


    // $ANTLR start "entryRuleAssignmentStatement"
    // InternalPoST.g:3853:1: entryRuleAssignmentStatement returns [EObject current=null] : iv_ruleAssignmentStatement= ruleAssignmentStatement EOF ;
    public final EObject entryRuleAssignmentStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignmentStatement = null;


        try {
            // InternalPoST.g:3853:60: (iv_ruleAssignmentStatement= ruleAssignmentStatement EOF )
            // InternalPoST.g:3854:2: iv_ruleAssignmentStatement= ruleAssignmentStatement EOF
            {
             newCompositeNode(grammarAccess.getAssignmentStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssignmentStatement=ruleAssignmentStatement();

            state._fsp--;

             current =iv_ruleAssignmentStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignmentStatement"


    // $ANTLR start "ruleAssignmentStatement"
    // InternalPoST.g:3860:1: ruleAssignmentStatement returns [EObject current=null] : ( ( ( (otherlv_0= RULE_ID ) ) | ( (lv_array_1_0= ruleArrayVariable ) ) ) otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) ) ;
    public final EObject ruleAssignmentStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_array_1_0 = null;

        EObject lv_value_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3866:2: ( ( ( ( (otherlv_0= RULE_ID ) ) | ( (lv_array_1_0= ruleArrayVariable ) ) ) otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) ) )
            // InternalPoST.g:3867:2: ( ( ( (otherlv_0= RULE_ID ) ) | ( (lv_array_1_0= ruleArrayVariable ) ) ) otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) )
            {
            // InternalPoST.g:3867:2: ( ( ( (otherlv_0= RULE_ID ) ) | ( (lv_array_1_0= ruleArrayVariable ) ) ) otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) )
            // InternalPoST.g:3868:3: ( ( (otherlv_0= RULE_ID ) ) | ( (lv_array_1_0= ruleArrayVariable ) ) ) otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) )
            {
            // InternalPoST.g:3868:3: ( ( (otherlv_0= RULE_ID ) ) | ( (lv_array_1_0= ruleArrayVariable ) ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==RULE_ID) ) {
                int LA51_1 = input.LA(2);

                if ( (LA51_1==109) ) {
                    alt51=2;
                }
                else if ( (LA51_1==49) ) {
                    alt51=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 51, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // InternalPoST.g:3869:4: ( (otherlv_0= RULE_ID ) )
                    {
                    // InternalPoST.g:3869:4: ( (otherlv_0= RULE_ID ) )
                    // InternalPoST.g:3870:5: (otherlv_0= RULE_ID )
                    {
                    // InternalPoST.g:3870:5: (otherlv_0= RULE_ID )
                    // InternalPoST.g:3871:6: otherlv_0= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssignmentStatementRule());
                    						}
                    					
                    otherlv_0=(Token)match(input,RULE_ID,FOLLOW_14); 

                    						newLeafNode(otherlv_0, grammarAccess.getAssignmentStatementAccess().getVariableSymbolicVariableCrossReference_0_0_0());
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:3883:4: ( (lv_array_1_0= ruleArrayVariable ) )
                    {
                    // InternalPoST.g:3883:4: ( (lv_array_1_0= ruleArrayVariable ) )
                    // InternalPoST.g:3884:5: (lv_array_1_0= ruleArrayVariable )
                    {
                    // InternalPoST.g:3884:5: (lv_array_1_0= ruleArrayVariable )
                    // InternalPoST.g:3885:6: lv_array_1_0= ruleArrayVariable
                    {

                    						newCompositeNode(grammarAccess.getAssignmentStatementAccess().getArrayArrayVariableParserRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_14);
                    lv_array_1_0=ruleArrayVariable();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAssignmentStatementRule());
                    						}
                    						set(
                    							current,
                    							"array",
                    							lv_array_1_0,
                    							"su.nsk.iae.post.PoST.ArrayVariable");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,49,FOLLOW_51); 

            			newLeafNode(otherlv_2, grammarAccess.getAssignmentStatementAccess().getColonEqualsSignKeyword_1());
            		
            // InternalPoST.g:3907:3: ( (lv_value_3_0= ruleExpression ) )
            // InternalPoST.g:3908:4: (lv_value_3_0= ruleExpression )
            {
            // InternalPoST.g:3908:4: (lv_value_3_0= ruleExpression )
            // InternalPoST.g:3909:5: lv_value_3_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getAssignmentStatementAccess().getValueExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_3_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssignmentStatementRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_3_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignmentStatement"


    // $ANTLR start "entryRuleSelectionStatement"
    // InternalPoST.g:3930:1: entryRuleSelectionStatement returns [EObject current=null] : iv_ruleSelectionStatement= ruleSelectionStatement EOF ;
    public final EObject entryRuleSelectionStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelectionStatement = null;


        try {
            // InternalPoST.g:3930:59: (iv_ruleSelectionStatement= ruleSelectionStatement EOF )
            // InternalPoST.g:3931:2: iv_ruleSelectionStatement= ruleSelectionStatement EOF
            {
             newCompositeNode(grammarAccess.getSelectionStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSelectionStatement=ruleSelectionStatement();

            state._fsp--;

             current =iv_ruleSelectionStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSelectionStatement"


    // $ANTLR start "ruleSelectionStatement"
    // InternalPoST.g:3937:1: ruleSelectionStatement returns [EObject current=null] : (this_IfStatement_0= ruleIfStatement | this_CaseStatement_1= ruleCaseStatement ) ;
    public final EObject ruleSelectionStatement() throws RecognitionException {
        EObject current = null;

        EObject this_IfStatement_0 = null;

        EObject this_CaseStatement_1 = null;



        	enterRule();

        try {
            // InternalPoST.g:3943:2: ( (this_IfStatement_0= ruleIfStatement | this_CaseStatement_1= ruleCaseStatement ) )
            // InternalPoST.g:3944:2: (this_IfStatement_0= ruleIfStatement | this_CaseStatement_1= ruleCaseStatement )
            {
            // InternalPoST.g:3944:2: (this_IfStatement_0= ruleIfStatement | this_CaseStatement_1= ruleCaseStatement )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==79) ) {
                alt52=1;
            }
            else if ( (LA52_0==83) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // InternalPoST.g:3945:3: this_IfStatement_0= ruleIfStatement
                    {

                    			newCompositeNode(grammarAccess.getSelectionStatementAccess().getIfStatementParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IfStatement_0=ruleIfStatement();

                    state._fsp--;


                    			current = this_IfStatement_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:3954:3: this_CaseStatement_1= ruleCaseStatement
                    {

                    			newCompositeNode(grammarAccess.getSelectionStatementAccess().getCaseStatementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CaseStatement_1=ruleCaseStatement();

                    state._fsp--;


                    			current = this_CaseStatement_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSelectionStatement"


    // $ANTLR start "entryRuleIfStatement"
    // InternalPoST.g:3966:1: entryRuleIfStatement returns [EObject current=null] : iv_ruleIfStatement= ruleIfStatement EOF ;
    public final EObject entryRuleIfStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfStatement = null;


        try {
            // InternalPoST.g:3966:52: (iv_ruleIfStatement= ruleIfStatement EOF )
            // InternalPoST.g:3967:2: iv_ruleIfStatement= ruleIfStatement EOF
            {
             newCompositeNode(grammarAccess.getIfStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIfStatement=ruleIfStatement();

            state._fsp--;

             current =iv_ruleIfStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIfStatement"


    // $ANTLR start "ruleIfStatement"
    // InternalPoST.g:3973:1: ruleIfStatement returns [EObject current=null] : (otherlv_0= 'IF' ( (lv_mainCond_1_0= ruleExpression ) ) otherlv_2= 'THEN' ( (lv_mainStatement_3_0= ruleStatementList ) ) (otherlv_4= 'ELSIF' ( (lv_elseIfCond_5_0= ruleExpression ) ) otherlv_6= 'THEN' ( (lv_elseIfStatements_7_0= ruleStatementList ) ) )* (otherlv_8= 'ELSE' ( (lv_elseStatement_9_0= ruleStatementList ) ) )? otherlv_10= 'END_IF' ) ;
    public final EObject ruleIfStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_mainCond_1_0 = null;

        EObject lv_mainStatement_3_0 = null;

        EObject lv_elseIfCond_5_0 = null;

        EObject lv_elseIfStatements_7_0 = null;

        EObject lv_elseStatement_9_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:3979:2: ( (otherlv_0= 'IF' ( (lv_mainCond_1_0= ruleExpression ) ) otherlv_2= 'THEN' ( (lv_mainStatement_3_0= ruleStatementList ) ) (otherlv_4= 'ELSIF' ( (lv_elseIfCond_5_0= ruleExpression ) ) otherlv_6= 'THEN' ( (lv_elseIfStatements_7_0= ruleStatementList ) ) )* (otherlv_8= 'ELSE' ( (lv_elseStatement_9_0= ruleStatementList ) ) )? otherlv_10= 'END_IF' ) )
            // InternalPoST.g:3980:2: (otherlv_0= 'IF' ( (lv_mainCond_1_0= ruleExpression ) ) otherlv_2= 'THEN' ( (lv_mainStatement_3_0= ruleStatementList ) ) (otherlv_4= 'ELSIF' ( (lv_elseIfCond_5_0= ruleExpression ) ) otherlv_6= 'THEN' ( (lv_elseIfStatements_7_0= ruleStatementList ) ) )* (otherlv_8= 'ELSE' ( (lv_elseStatement_9_0= ruleStatementList ) ) )? otherlv_10= 'END_IF' )
            {
            // InternalPoST.g:3980:2: (otherlv_0= 'IF' ( (lv_mainCond_1_0= ruleExpression ) ) otherlv_2= 'THEN' ( (lv_mainStatement_3_0= ruleStatementList ) ) (otherlv_4= 'ELSIF' ( (lv_elseIfCond_5_0= ruleExpression ) ) otherlv_6= 'THEN' ( (lv_elseIfStatements_7_0= ruleStatementList ) ) )* (otherlv_8= 'ELSE' ( (lv_elseStatement_9_0= ruleStatementList ) ) )? otherlv_10= 'END_IF' )
            // InternalPoST.g:3981:3: otherlv_0= 'IF' ( (lv_mainCond_1_0= ruleExpression ) ) otherlv_2= 'THEN' ( (lv_mainStatement_3_0= ruleStatementList ) ) (otherlv_4= 'ELSIF' ( (lv_elseIfCond_5_0= ruleExpression ) ) otherlv_6= 'THEN' ( (lv_elseIfStatements_7_0= ruleStatementList ) ) )* (otherlv_8= 'ELSE' ( (lv_elseStatement_9_0= ruleStatementList ) ) )? otherlv_10= 'END_IF'
            {
            otherlv_0=(Token)match(input,79,FOLLOW_51); 

            			newLeafNode(otherlv_0, grammarAccess.getIfStatementAccess().getIFKeyword_0());
            		
            // InternalPoST.g:3985:3: ( (lv_mainCond_1_0= ruleExpression ) )
            // InternalPoST.g:3986:4: (lv_mainCond_1_0= ruleExpression )
            {
            // InternalPoST.g:3986:4: (lv_mainCond_1_0= ruleExpression )
            // InternalPoST.g:3987:5: lv_mainCond_1_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getIfStatementAccess().getMainCondExpressionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_46);
            lv_mainCond_1_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfStatementRule());
            					}
            					set(
            						current,
            						"mainCond",
            						lv_mainCond_1_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,74,FOLLOW_63); 

            			newLeafNode(otherlv_2, grammarAccess.getIfStatementAccess().getTHENKeyword_2());
            		
            // InternalPoST.g:4008:3: ( (lv_mainStatement_3_0= ruleStatementList ) )
            // InternalPoST.g:4009:4: (lv_mainStatement_3_0= ruleStatementList )
            {
            // InternalPoST.g:4009:4: (lv_mainStatement_3_0= ruleStatementList )
            // InternalPoST.g:4010:5: lv_mainStatement_3_0= ruleStatementList
            {

            					newCompositeNode(grammarAccess.getIfStatementAccess().getMainStatementStatementListParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_64);
            lv_mainStatement_3_0=ruleStatementList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfStatementRule());
            					}
            					set(
            						current,
            						"mainStatement",
            						lv_mainStatement_3_0,
            						"su.nsk.iae.post.PoST.StatementList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:4027:3: (otherlv_4= 'ELSIF' ( (lv_elseIfCond_5_0= ruleExpression ) ) otherlv_6= 'THEN' ( (lv_elseIfStatements_7_0= ruleStatementList ) ) )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==80) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // InternalPoST.g:4028:4: otherlv_4= 'ELSIF' ( (lv_elseIfCond_5_0= ruleExpression ) ) otherlv_6= 'THEN' ( (lv_elseIfStatements_7_0= ruleStatementList ) )
            	    {
            	    otherlv_4=(Token)match(input,80,FOLLOW_51); 

            	    				newLeafNode(otherlv_4, grammarAccess.getIfStatementAccess().getELSIFKeyword_4_0());
            	    			
            	    // InternalPoST.g:4032:4: ( (lv_elseIfCond_5_0= ruleExpression ) )
            	    // InternalPoST.g:4033:5: (lv_elseIfCond_5_0= ruleExpression )
            	    {
            	    // InternalPoST.g:4033:5: (lv_elseIfCond_5_0= ruleExpression )
            	    // InternalPoST.g:4034:6: lv_elseIfCond_5_0= ruleExpression
            	    {

            	    						newCompositeNode(grammarAccess.getIfStatementAccess().getElseIfCondExpressionParserRuleCall_4_1_0());
            	    					
            	    pushFollow(FOLLOW_46);
            	    lv_elseIfCond_5_0=ruleExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getIfStatementRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elseIfCond",
            	    							lv_elseIfCond_5_0,
            	    							"su.nsk.iae.post.PoST.Expression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_6=(Token)match(input,74,FOLLOW_63); 

            	    				newLeafNode(otherlv_6, grammarAccess.getIfStatementAccess().getTHENKeyword_4_2());
            	    			
            	    // InternalPoST.g:4055:4: ( (lv_elseIfStatements_7_0= ruleStatementList ) )
            	    // InternalPoST.g:4056:5: (lv_elseIfStatements_7_0= ruleStatementList )
            	    {
            	    // InternalPoST.g:4056:5: (lv_elseIfStatements_7_0= ruleStatementList )
            	    // InternalPoST.g:4057:6: lv_elseIfStatements_7_0= ruleStatementList
            	    {

            	    						newCompositeNode(grammarAccess.getIfStatementAccess().getElseIfStatementsStatementListParserRuleCall_4_3_0());
            	    					
            	    pushFollow(FOLLOW_64);
            	    lv_elseIfStatements_7_0=ruleStatementList();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getIfStatementRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elseIfStatements",
            	    							lv_elseIfStatements_7_0,
            	    							"su.nsk.iae.post.PoST.StatementList");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);

            // InternalPoST.g:4075:3: (otherlv_8= 'ELSE' ( (lv_elseStatement_9_0= ruleStatementList ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==81) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalPoST.g:4076:4: otherlv_8= 'ELSE' ( (lv_elseStatement_9_0= ruleStatementList ) )
                    {
                    otherlv_8=(Token)match(input,81,FOLLOW_65); 

                    				newLeafNode(otherlv_8, grammarAccess.getIfStatementAccess().getELSEKeyword_5_0());
                    			
                    // InternalPoST.g:4080:4: ( (lv_elseStatement_9_0= ruleStatementList ) )
                    // InternalPoST.g:4081:5: (lv_elseStatement_9_0= ruleStatementList )
                    {
                    // InternalPoST.g:4081:5: (lv_elseStatement_9_0= ruleStatementList )
                    // InternalPoST.g:4082:6: lv_elseStatement_9_0= ruleStatementList
                    {

                    						newCompositeNode(grammarAccess.getIfStatementAccess().getElseStatementStatementListParserRuleCall_5_1_0());
                    					
                    pushFollow(FOLLOW_66);
                    lv_elseStatement_9_0=ruleStatementList();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getIfStatementRule());
                    						}
                    						set(
                    							current,
                    							"elseStatement",
                    							lv_elseStatement_9_0,
                    							"su.nsk.iae.post.PoST.StatementList");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,82,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getIfStatementAccess().getEND_IFKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIfStatement"


    // $ANTLR start "entryRuleCaseStatement"
    // InternalPoST.g:4108:1: entryRuleCaseStatement returns [EObject current=null] : iv_ruleCaseStatement= ruleCaseStatement EOF ;
    public final EObject entryRuleCaseStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCaseStatement = null;


        try {
            // InternalPoST.g:4108:54: (iv_ruleCaseStatement= ruleCaseStatement EOF )
            // InternalPoST.g:4109:2: iv_ruleCaseStatement= ruleCaseStatement EOF
            {
             newCompositeNode(grammarAccess.getCaseStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCaseStatement=ruleCaseStatement();

            state._fsp--;

             current =iv_ruleCaseStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCaseStatement"


    // $ANTLR start "ruleCaseStatement"
    // InternalPoST.g:4115:1: ruleCaseStatement returns [EObject current=null] : (otherlv_0= 'CASE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'OF' ( (lv_caseElements_3_0= ruleCaseElement ) )* (otherlv_4= 'ELSE' ( (lv_elseStatement_5_0= ruleStatementList ) ) )? otherlv_6= 'END_CASE' ) ;
    public final EObject ruleCaseStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_cond_1_0 = null;

        EObject lv_caseElements_3_0 = null;

        EObject lv_elseStatement_5_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4121:2: ( (otherlv_0= 'CASE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'OF' ( (lv_caseElements_3_0= ruleCaseElement ) )* (otherlv_4= 'ELSE' ( (lv_elseStatement_5_0= ruleStatementList ) ) )? otherlv_6= 'END_CASE' ) )
            // InternalPoST.g:4122:2: (otherlv_0= 'CASE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'OF' ( (lv_caseElements_3_0= ruleCaseElement ) )* (otherlv_4= 'ELSE' ( (lv_elseStatement_5_0= ruleStatementList ) ) )? otherlv_6= 'END_CASE' )
            {
            // InternalPoST.g:4122:2: (otherlv_0= 'CASE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'OF' ( (lv_caseElements_3_0= ruleCaseElement ) )* (otherlv_4= 'ELSE' ( (lv_elseStatement_5_0= ruleStatementList ) ) )? otherlv_6= 'END_CASE' )
            // InternalPoST.g:4123:3: otherlv_0= 'CASE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'OF' ( (lv_caseElements_3_0= ruleCaseElement ) )* (otherlv_4= 'ELSE' ( (lv_elseStatement_5_0= ruleStatementList ) ) )? otherlv_6= 'END_CASE'
            {
            otherlv_0=(Token)match(input,83,FOLLOW_51); 

            			newLeafNode(otherlv_0, grammarAccess.getCaseStatementAccess().getCASEKeyword_0());
            		
            // InternalPoST.g:4127:3: ( (lv_cond_1_0= ruleExpression ) )
            // InternalPoST.g:4128:4: (lv_cond_1_0= ruleExpression )
            {
            // InternalPoST.g:4128:4: (lv_cond_1_0= ruleExpression )
            // InternalPoST.g:4129:5: lv_cond_1_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getCaseStatementAccess().getCondExpressionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_67);
            lv_cond_1_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCaseStatementRule());
            					}
            					set(
            						current,
            						"cond",
            						lv_cond_1_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,84,FOLLOW_68); 

            			newLeafNode(otherlv_2, grammarAccess.getCaseStatementAccess().getOFKeyword_2());
            		
            // InternalPoST.g:4150:3: ( (lv_caseElements_3_0= ruleCaseElement ) )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==RULE_ID||LA55_0==RULE_INTEGER||LA55_0==114) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // InternalPoST.g:4151:4: (lv_caseElements_3_0= ruleCaseElement )
            	    {
            	    // InternalPoST.g:4151:4: (lv_caseElements_3_0= ruleCaseElement )
            	    // InternalPoST.g:4152:5: lv_caseElements_3_0= ruleCaseElement
            	    {

            	    					newCompositeNode(grammarAccess.getCaseStatementAccess().getCaseElementsCaseElementParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_68);
            	    lv_caseElements_3_0=ruleCaseElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getCaseStatementRule());
            	    					}
            	    					add(
            	    						current,
            	    						"caseElements",
            	    						lv_caseElements_3_0,
            	    						"su.nsk.iae.post.PoST.CaseElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop55;
                }
            } while (true);

            // InternalPoST.g:4169:3: (otherlv_4= 'ELSE' ( (lv_elseStatement_5_0= ruleStatementList ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==81) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalPoST.g:4170:4: otherlv_4= 'ELSE' ( (lv_elseStatement_5_0= ruleStatementList ) )
                    {
                    otherlv_4=(Token)match(input,81,FOLLOW_69); 

                    				newLeafNode(otherlv_4, grammarAccess.getCaseStatementAccess().getELSEKeyword_4_0());
                    			
                    // InternalPoST.g:4174:4: ( (lv_elseStatement_5_0= ruleStatementList ) )
                    // InternalPoST.g:4175:5: (lv_elseStatement_5_0= ruleStatementList )
                    {
                    // InternalPoST.g:4175:5: (lv_elseStatement_5_0= ruleStatementList )
                    // InternalPoST.g:4176:6: lv_elseStatement_5_0= ruleStatementList
                    {

                    						newCompositeNode(grammarAccess.getCaseStatementAccess().getElseStatementStatementListParserRuleCall_4_1_0());
                    					
                    pushFollow(FOLLOW_70);
                    lv_elseStatement_5_0=ruleStatementList();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getCaseStatementRule());
                    						}
                    						set(
                    							current,
                    							"elseStatement",
                    							lv_elseStatement_5_0,
                    							"su.nsk.iae.post.PoST.StatementList");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,85,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getCaseStatementAccess().getEND_CASEKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCaseStatement"


    // $ANTLR start "entryRuleCaseElement"
    // InternalPoST.g:4202:1: entryRuleCaseElement returns [EObject current=null] : iv_ruleCaseElement= ruleCaseElement EOF ;
    public final EObject entryRuleCaseElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCaseElement = null;


        try {
            // InternalPoST.g:4202:52: (iv_ruleCaseElement= ruleCaseElement EOF )
            // InternalPoST.g:4203:2: iv_ruleCaseElement= ruleCaseElement EOF
            {
             newCompositeNode(grammarAccess.getCaseElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCaseElement=ruleCaseElement();

            state._fsp--;

             current =iv_ruleCaseElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCaseElement"


    // $ANTLR start "ruleCaseElement"
    // InternalPoST.g:4209:1: ruleCaseElement returns [EObject current=null] : ( ( (lv_caseList_0_0= ruleCaseList ) ) otherlv_1= ':' ( (lv_statement_2_0= ruleStatementList ) ) ) ;
    public final EObject ruleCaseElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_caseList_0_0 = null;

        EObject lv_statement_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4215:2: ( ( ( (lv_caseList_0_0= ruleCaseList ) ) otherlv_1= ':' ( (lv_statement_2_0= ruleStatementList ) ) ) )
            // InternalPoST.g:4216:2: ( ( (lv_caseList_0_0= ruleCaseList ) ) otherlv_1= ':' ( (lv_statement_2_0= ruleStatementList ) ) )
            {
            // InternalPoST.g:4216:2: ( ( (lv_caseList_0_0= ruleCaseList ) ) otherlv_1= ':' ( (lv_statement_2_0= ruleStatementList ) ) )
            // InternalPoST.g:4217:3: ( (lv_caseList_0_0= ruleCaseList ) ) otherlv_1= ':' ( (lv_statement_2_0= ruleStatementList ) )
            {
            // InternalPoST.g:4217:3: ( (lv_caseList_0_0= ruleCaseList ) )
            // InternalPoST.g:4218:4: (lv_caseList_0_0= ruleCaseList )
            {
            // InternalPoST.g:4218:4: (lv_caseList_0_0= ruleCaseList )
            // InternalPoST.g:4219:5: lv_caseList_0_0= ruleCaseList
            {

            					newCompositeNode(grammarAccess.getCaseElementAccess().getCaseListCaseListParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_20);
            lv_caseList_0_0=ruleCaseList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCaseElementRule());
            					}
            					set(
            						current,
            						"caseList",
            						lv_caseList_0_0,
            						"su.nsk.iae.post.PoST.CaseList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,53,FOLLOW_71); 

            			newLeafNode(otherlv_1, grammarAccess.getCaseElementAccess().getColonKeyword_1());
            		
            // InternalPoST.g:4240:3: ( (lv_statement_2_0= ruleStatementList ) )
            // InternalPoST.g:4241:4: (lv_statement_2_0= ruleStatementList )
            {
            // InternalPoST.g:4241:4: (lv_statement_2_0= ruleStatementList )
            // InternalPoST.g:4242:5: lv_statement_2_0= ruleStatementList
            {

            					newCompositeNode(grammarAccess.getCaseElementAccess().getStatementStatementListParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_statement_2_0=ruleStatementList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCaseElementRule());
            					}
            					set(
            						current,
            						"statement",
            						lv_statement_2_0,
            						"su.nsk.iae.post.PoST.StatementList");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCaseElement"


    // $ANTLR start "entryRuleCaseList"
    // InternalPoST.g:4263:1: entryRuleCaseList returns [EObject current=null] : iv_ruleCaseList= ruleCaseList EOF ;
    public final EObject entryRuleCaseList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCaseList = null;


        try {
            // InternalPoST.g:4263:49: (iv_ruleCaseList= ruleCaseList EOF )
            // InternalPoST.g:4264:2: iv_ruleCaseList= ruleCaseList EOF
            {
             newCompositeNode(grammarAccess.getCaseListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCaseList=ruleCaseList();

            state._fsp--;

             current =iv_ruleCaseList; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCaseList"


    // $ANTLR start "ruleCaseList"
    // InternalPoST.g:4270:1: ruleCaseList returns [EObject current=null] : ( ( (lv_caseListElement_0_0= ruleCaseListElement ) ) (otherlv_1= ',' ( (lv_caseListElement_2_0= ruleCaseListElement ) ) )* ) ;
    public final EObject ruleCaseList() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_caseListElement_0_0 = null;

        EObject lv_caseListElement_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4276:2: ( ( ( (lv_caseListElement_0_0= ruleCaseListElement ) ) (otherlv_1= ',' ( (lv_caseListElement_2_0= ruleCaseListElement ) ) )* ) )
            // InternalPoST.g:4277:2: ( ( (lv_caseListElement_0_0= ruleCaseListElement ) ) (otherlv_1= ',' ( (lv_caseListElement_2_0= ruleCaseListElement ) ) )* )
            {
            // InternalPoST.g:4277:2: ( ( (lv_caseListElement_0_0= ruleCaseListElement ) ) (otherlv_1= ',' ( (lv_caseListElement_2_0= ruleCaseListElement ) ) )* )
            // InternalPoST.g:4278:3: ( (lv_caseListElement_0_0= ruleCaseListElement ) ) (otherlv_1= ',' ( (lv_caseListElement_2_0= ruleCaseListElement ) ) )*
            {
            // InternalPoST.g:4278:3: ( (lv_caseListElement_0_0= ruleCaseListElement ) )
            // InternalPoST.g:4279:4: (lv_caseListElement_0_0= ruleCaseListElement )
            {
            // InternalPoST.g:4279:4: (lv_caseListElement_0_0= ruleCaseListElement )
            // InternalPoST.g:4280:5: lv_caseListElement_0_0= ruleCaseListElement
            {

            					newCompositeNode(grammarAccess.getCaseListAccess().getCaseListElementCaseListElementParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_23);
            lv_caseListElement_0_0=ruleCaseListElement();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCaseListRule());
            					}
            					add(
            						current,
            						"caseListElement",
            						lv_caseListElement_0_0,
            						"su.nsk.iae.post.PoST.CaseListElement");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:4297:3: (otherlv_1= ',' ( (lv_caseListElement_2_0= ruleCaseListElement ) ) )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==50) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // InternalPoST.g:4298:4: otherlv_1= ',' ( (lv_caseListElement_2_0= ruleCaseListElement ) )
            	    {
            	    otherlv_1=(Token)match(input,50,FOLLOW_72); 

            	    				newLeafNode(otherlv_1, grammarAccess.getCaseListAccess().getCommaKeyword_1_0());
            	    			
            	    // InternalPoST.g:4302:4: ( (lv_caseListElement_2_0= ruleCaseListElement ) )
            	    // InternalPoST.g:4303:5: (lv_caseListElement_2_0= ruleCaseListElement )
            	    {
            	    // InternalPoST.g:4303:5: (lv_caseListElement_2_0= ruleCaseListElement )
            	    // InternalPoST.g:4304:6: lv_caseListElement_2_0= ruleCaseListElement
            	    {

            	    						newCompositeNode(grammarAccess.getCaseListAccess().getCaseListElementCaseListElementParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_23);
            	    lv_caseListElement_2_0=ruleCaseListElement();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getCaseListRule());
            	    						}
            	    						add(
            	    							current,
            	    							"caseListElement",
            	    							lv_caseListElement_2_0,
            	    							"su.nsk.iae.post.PoST.CaseListElement");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCaseList"


    // $ANTLR start "entryRuleCaseListElement"
    // InternalPoST.g:4326:1: entryRuleCaseListElement returns [EObject current=null] : iv_ruleCaseListElement= ruleCaseListElement EOF ;
    public final EObject entryRuleCaseListElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCaseListElement = null;


        try {
            // InternalPoST.g:4326:56: (iv_ruleCaseListElement= ruleCaseListElement EOF )
            // InternalPoST.g:4327:2: iv_ruleCaseListElement= ruleCaseListElement EOF
            {
             newCompositeNode(grammarAccess.getCaseListElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCaseListElement=ruleCaseListElement();

            state._fsp--;

             current =iv_ruleCaseListElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCaseListElement"


    // $ANTLR start "ruleCaseListElement"
    // InternalPoST.g:4333:1: ruleCaseListElement returns [EObject current=null] : ( ( (lv_num_0_0= ruleSignedInteger ) ) | ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleCaseListElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_num_0_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4339:2: ( ( ( (lv_num_0_0= ruleSignedInteger ) ) | ( (otherlv_1= RULE_ID ) ) ) )
            // InternalPoST.g:4340:2: ( ( (lv_num_0_0= ruleSignedInteger ) ) | ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalPoST.g:4340:2: ( ( (lv_num_0_0= ruleSignedInteger ) ) | ( (otherlv_1= RULE_ID ) ) )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==RULE_INTEGER||LA58_0==114) ) {
                alt58=1;
            }
            else if ( (LA58_0==RULE_ID) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // InternalPoST.g:4341:3: ( (lv_num_0_0= ruleSignedInteger ) )
                    {
                    // InternalPoST.g:4341:3: ( (lv_num_0_0= ruleSignedInteger ) )
                    // InternalPoST.g:4342:4: (lv_num_0_0= ruleSignedInteger )
                    {
                    // InternalPoST.g:4342:4: (lv_num_0_0= ruleSignedInteger )
                    // InternalPoST.g:4343:5: lv_num_0_0= ruleSignedInteger
                    {

                    					newCompositeNode(grammarAccess.getCaseListElementAccess().getNumSignedIntegerParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_num_0_0=ruleSignedInteger();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getCaseListElementRule());
                    					}
                    					set(
                    						current,
                    						"num",
                    						lv_num_0_0,
                    						"su.nsk.iae.post.PoST.SignedInteger");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:4361:3: ( (otherlv_1= RULE_ID ) )
                    {
                    // InternalPoST.g:4361:3: ( (otherlv_1= RULE_ID ) )
                    // InternalPoST.g:4362:4: (otherlv_1= RULE_ID )
                    {
                    // InternalPoST.g:4362:4: (otherlv_1= RULE_ID )
                    // InternalPoST.g:4363:5: otherlv_1= RULE_ID
                    {

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getCaseListElementRule());
                    					}
                    				
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_2); 

                    					newLeafNode(otherlv_1, grammarAccess.getCaseListElementAccess().getVariableSymbolicVariableCrossReference_1_0());
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCaseListElement"


    // $ANTLR start "entryRuleIterationStatement"
    // InternalPoST.g:4378:1: entryRuleIterationStatement returns [EObject current=null] : iv_ruleIterationStatement= ruleIterationStatement EOF ;
    public final EObject entryRuleIterationStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIterationStatement = null;


        try {
            // InternalPoST.g:4378:59: (iv_ruleIterationStatement= ruleIterationStatement EOF )
            // InternalPoST.g:4379:2: iv_ruleIterationStatement= ruleIterationStatement EOF
            {
             newCompositeNode(grammarAccess.getIterationStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIterationStatement=ruleIterationStatement();

            state._fsp--;

             current =iv_ruleIterationStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIterationStatement"


    // $ANTLR start "ruleIterationStatement"
    // InternalPoST.g:4385:1: ruleIterationStatement returns [EObject current=null] : (this_ForStatement_0= ruleForStatement | this_WhileStatement_1= ruleWhileStatement | this_RepeatStatement_2= ruleRepeatStatement ) ;
    public final EObject ruleIterationStatement() throws RecognitionException {
        EObject current = null;

        EObject this_ForStatement_0 = null;

        EObject this_WhileStatement_1 = null;

        EObject this_RepeatStatement_2 = null;



        	enterRule();

        try {
            // InternalPoST.g:4391:2: ( (this_ForStatement_0= ruleForStatement | this_WhileStatement_1= ruleWhileStatement | this_RepeatStatement_2= ruleRepeatStatement ) )
            // InternalPoST.g:4392:2: (this_ForStatement_0= ruleForStatement | this_WhileStatement_1= ruleWhileStatement | this_RepeatStatement_2= ruleRepeatStatement )
            {
            // InternalPoST.g:4392:2: (this_ForStatement_0= ruleForStatement | this_WhileStatement_1= ruleWhileStatement | this_RepeatStatement_2= ruleRepeatStatement )
            int alt59=3;
            switch ( input.LA(1) ) {
            case 86:
                {
                alt59=1;
                }
                break;
            case 91:
                {
                alt59=2;
                }
                break;
            case 93:
                {
                alt59=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }

            switch (alt59) {
                case 1 :
                    // InternalPoST.g:4393:3: this_ForStatement_0= ruleForStatement
                    {

                    			newCompositeNode(grammarAccess.getIterationStatementAccess().getForStatementParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ForStatement_0=ruleForStatement();

                    state._fsp--;


                    			current = this_ForStatement_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:4402:3: this_WhileStatement_1= ruleWhileStatement
                    {

                    			newCompositeNode(grammarAccess.getIterationStatementAccess().getWhileStatementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_WhileStatement_1=ruleWhileStatement();

                    state._fsp--;


                    			current = this_WhileStatement_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalPoST.g:4411:3: this_RepeatStatement_2= ruleRepeatStatement
                    {

                    			newCompositeNode(grammarAccess.getIterationStatementAccess().getRepeatStatementParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_RepeatStatement_2=ruleRepeatStatement();

                    state._fsp--;


                    			current = this_RepeatStatement_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIterationStatement"


    // $ANTLR start "entryRuleForStatement"
    // InternalPoST.g:4423:1: entryRuleForStatement returns [EObject current=null] : iv_ruleForStatement= ruleForStatement EOF ;
    public final EObject entryRuleForStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForStatement = null;


        try {
            // InternalPoST.g:4423:53: (iv_ruleForStatement= ruleForStatement EOF )
            // InternalPoST.g:4424:2: iv_ruleForStatement= ruleForStatement EOF
            {
             newCompositeNode(grammarAccess.getForStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForStatement=ruleForStatement();

            state._fsp--;

             current =iv_ruleForStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForStatement"


    // $ANTLR start "ruleForStatement"
    // InternalPoST.g:4430:1: ruleForStatement returns [EObject current=null] : (otherlv_0= 'FOR' ( (otherlv_1= RULE_ID ) ) otherlv_2= ':=' ( (lv_forList_3_0= ruleForList ) ) otherlv_4= 'DO' ( (lv_statement_5_0= ruleStatementList ) ) otherlv_6= 'END_FOR' ) ;
    public final EObject ruleForStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_forList_3_0 = null;

        EObject lv_statement_5_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4436:2: ( (otherlv_0= 'FOR' ( (otherlv_1= RULE_ID ) ) otherlv_2= ':=' ( (lv_forList_3_0= ruleForList ) ) otherlv_4= 'DO' ( (lv_statement_5_0= ruleStatementList ) ) otherlv_6= 'END_FOR' ) )
            // InternalPoST.g:4437:2: (otherlv_0= 'FOR' ( (otherlv_1= RULE_ID ) ) otherlv_2= ':=' ( (lv_forList_3_0= ruleForList ) ) otherlv_4= 'DO' ( (lv_statement_5_0= ruleStatementList ) ) otherlv_6= 'END_FOR' )
            {
            // InternalPoST.g:4437:2: (otherlv_0= 'FOR' ( (otherlv_1= RULE_ID ) ) otherlv_2= ':=' ( (lv_forList_3_0= ruleForList ) ) otherlv_4= 'DO' ( (lv_statement_5_0= ruleStatementList ) ) otherlv_6= 'END_FOR' )
            // InternalPoST.g:4438:3: otherlv_0= 'FOR' ( (otherlv_1= RULE_ID ) ) otherlv_2= ':=' ( (lv_forList_3_0= ruleForList ) ) otherlv_4= 'DO' ( (lv_statement_5_0= ruleStatementList ) ) otherlv_6= 'END_FOR'
            {
            otherlv_0=(Token)match(input,86,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getForStatementAccess().getFORKeyword_0());
            		
            // InternalPoST.g:4442:3: ( (otherlv_1= RULE_ID ) )
            // InternalPoST.g:4443:4: (otherlv_1= RULE_ID )
            {
            // InternalPoST.g:4443:4: (otherlv_1= RULE_ID )
            // InternalPoST.g:4444:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getForStatementRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_14); 

            					newLeafNode(otherlv_1, grammarAccess.getForStatementAccess().getVariableSymbolicVariableCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,49,FOLLOW_51); 

            			newLeafNode(otherlv_2, grammarAccess.getForStatementAccess().getColonEqualsSignKeyword_2());
            		
            // InternalPoST.g:4459:3: ( (lv_forList_3_0= ruleForList ) )
            // InternalPoST.g:4460:4: (lv_forList_3_0= ruleForList )
            {
            // InternalPoST.g:4460:4: (lv_forList_3_0= ruleForList )
            // InternalPoST.g:4461:5: lv_forList_3_0= ruleForList
            {

            					newCompositeNode(grammarAccess.getForStatementAccess().getForListForListParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_73);
            lv_forList_3_0=ruleForList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForStatementRule());
            					}
            					set(
            						current,
            						"forList",
            						lv_forList_3_0,
            						"su.nsk.iae.post.PoST.ForList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,87,FOLLOW_74); 

            			newLeafNode(otherlv_4, grammarAccess.getForStatementAccess().getDOKeyword_4());
            		
            // InternalPoST.g:4482:3: ( (lv_statement_5_0= ruleStatementList ) )
            // InternalPoST.g:4483:4: (lv_statement_5_0= ruleStatementList )
            {
            // InternalPoST.g:4483:4: (lv_statement_5_0= ruleStatementList )
            // InternalPoST.g:4484:5: lv_statement_5_0= ruleStatementList
            {

            					newCompositeNode(grammarAccess.getForStatementAccess().getStatementStatementListParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_75);
            lv_statement_5_0=ruleStatementList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForStatementRule());
            					}
            					set(
            						current,
            						"statement",
            						lv_statement_5_0,
            						"su.nsk.iae.post.PoST.StatementList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,88,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getForStatementAccess().getEND_FORKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForStatement"


    // $ANTLR start "entryRuleForList"
    // InternalPoST.g:4509:1: entryRuleForList returns [EObject current=null] : iv_ruleForList= ruleForList EOF ;
    public final EObject entryRuleForList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForList = null;


        try {
            // InternalPoST.g:4509:48: (iv_ruleForList= ruleForList EOF )
            // InternalPoST.g:4510:2: iv_ruleForList= ruleForList EOF
            {
             newCompositeNode(grammarAccess.getForListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForList=ruleForList();

            state._fsp--;

             current =iv_ruleForList; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForList"


    // $ANTLR start "ruleForList"
    // InternalPoST.g:4516:1: ruleForList returns [EObject current=null] : ( ( (lv_start_0_0= ruleExpression ) ) otherlv_1= 'TO' ( (lv_end_2_0= ruleExpression ) ) (otherlv_3= 'BY' ( (lv_step_4_0= ruleExpression ) ) )? ) ;
    public final EObject ruleForList() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_start_0_0 = null;

        EObject lv_end_2_0 = null;

        EObject lv_step_4_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4522:2: ( ( ( (lv_start_0_0= ruleExpression ) ) otherlv_1= 'TO' ( (lv_end_2_0= ruleExpression ) ) (otherlv_3= 'BY' ( (lv_step_4_0= ruleExpression ) ) )? ) )
            // InternalPoST.g:4523:2: ( ( (lv_start_0_0= ruleExpression ) ) otherlv_1= 'TO' ( (lv_end_2_0= ruleExpression ) ) (otherlv_3= 'BY' ( (lv_step_4_0= ruleExpression ) ) )? )
            {
            // InternalPoST.g:4523:2: ( ( (lv_start_0_0= ruleExpression ) ) otherlv_1= 'TO' ( (lv_end_2_0= ruleExpression ) ) (otherlv_3= 'BY' ( (lv_step_4_0= ruleExpression ) ) )? )
            // InternalPoST.g:4524:3: ( (lv_start_0_0= ruleExpression ) ) otherlv_1= 'TO' ( (lv_end_2_0= ruleExpression ) ) (otherlv_3= 'BY' ( (lv_step_4_0= ruleExpression ) ) )?
            {
            // InternalPoST.g:4524:3: ( (lv_start_0_0= ruleExpression ) )
            // InternalPoST.g:4525:4: (lv_start_0_0= ruleExpression )
            {
            // InternalPoST.g:4525:4: (lv_start_0_0= ruleExpression )
            // InternalPoST.g:4526:5: lv_start_0_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getForListAccess().getStartExpressionParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_76);
            lv_start_0_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForListRule());
            					}
            					set(
            						current,
            						"start",
            						lv_start_0_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,89,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getForListAccess().getTOKeyword_1());
            		
            // InternalPoST.g:4547:3: ( (lv_end_2_0= ruleExpression ) )
            // InternalPoST.g:4548:4: (lv_end_2_0= ruleExpression )
            {
            // InternalPoST.g:4548:4: (lv_end_2_0= ruleExpression )
            // InternalPoST.g:4549:5: lv_end_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getForListAccess().getEndExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_77);
            lv_end_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForListRule());
            					}
            					set(
            						current,
            						"end",
            						lv_end_2_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:4566:3: (otherlv_3= 'BY' ( (lv_step_4_0= ruleExpression ) ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==90) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalPoST.g:4567:4: otherlv_3= 'BY' ( (lv_step_4_0= ruleExpression ) )
                    {
                    otherlv_3=(Token)match(input,90,FOLLOW_51); 

                    				newLeafNode(otherlv_3, grammarAccess.getForListAccess().getBYKeyword_3_0());
                    			
                    // InternalPoST.g:4571:4: ( (lv_step_4_0= ruleExpression ) )
                    // InternalPoST.g:4572:5: (lv_step_4_0= ruleExpression )
                    {
                    // InternalPoST.g:4572:5: (lv_step_4_0= ruleExpression )
                    // InternalPoST.g:4573:6: lv_step_4_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getForListAccess().getStepExpressionParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_step_4_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getForListRule());
                    						}
                    						set(
                    							current,
                    							"step",
                    							lv_step_4_0,
                    							"su.nsk.iae.post.PoST.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForList"


    // $ANTLR start "entryRuleWhileStatement"
    // InternalPoST.g:4595:1: entryRuleWhileStatement returns [EObject current=null] : iv_ruleWhileStatement= ruleWhileStatement EOF ;
    public final EObject entryRuleWhileStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhileStatement = null;


        try {
            // InternalPoST.g:4595:55: (iv_ruleWhileStatement= ruleWhileStatement EOF )
            // InternalPoST.g:4596:2: iv_ruleWhileStatement= ruleWhileStatement EOF
            {
             newCompositeNode(grammarAccess.getWhileStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWhileStatement=ruleWhileStatement();

            state._fsp--;

             current =iv_ruleWhileStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWhileStatement"


    // $ANTLR start "ruleWhileStatement"
    // InternalPoST.g:4602:1: ruleWhileStatement returns [EObject current=null] : (otherlv_0= 'WHILE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'DO' ( (lv_statement_3_0= ruleStatementList ) ) otherlv_4= 'END_WHILE' ) ;
    public final EObject ruleWhileStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_cond_1_0 = null;

        EObject lv_statement_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4608:2: ( (otherlv_0= 'WHILE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'DO' ( (lv_statement_3_0= ruleStatementList ) ) otherlv_4= 'END_WHILE' ) )
            // InternalPoST.g:4609:2: (otherlv_0= 'WHILE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'DO' ( (lv_statement_3_0= ruleStatementList ) ) otherlv_4= 'END_WHILE' )
            {
            // InternalPoST.g:4609:2: (otherlv_0= 'WHILE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'DO' ( (lv_statement_3_0= ruleStatementList ) ) otherlv_4= 'END_WHILE' )
            // InternalPoST.g:4610:3: otherlv_0= 'WHILE' ( (lv_cond_1_0= ruleExpression ) ) otherlv_2= 'DO' ( (lv_statement_3_0= ruleStatementList ) ) otherlv_4= 'END_WHILE'
            {
            otherlv_0=(Token)match(input,91,FOLLOW_51); 

            			newLeafNode(otherlv_0, grammarAccess.getWhileStatementAccess().getWHILEKeyword_0());
            		
            // InternalPoST.g:4614:3: ( (lv_cond_1_0= ruleExpression ) )
            // InternalPoST.g:4615:4: (lv_cond_1_0= ruleExpression )
            {
            // InternalPoST.g:4615:4: (lv_cond_1_0= ruleExpression )
            // InternalPoST.g:4616:5: lv_cond_1_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getWhileStatementAccess().getCondExpressionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_73);
            lv_cond_1_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileStatementRule());
            					}
            					set(
            						current,
            						"cond",
            						lv_cond_1_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,87,FOLLOW_78); 

            			newLeafNode(otherlv_2, grammarAccess.getWhileStatementAccess().getDOKeyword_2());
            		
            // InternalPoST.g:4637:3: ( (lv_statement_3_0= ruleStatementList ) )
            // InternalPoST.g:4638:4: (lv_statement_3_0= ruleStatementList )
            {
            // InternalPoST.g:4638:4: (lv_statement_3_0= ruleStatementList )
            // InternalPoST.g:4639:5: lv_statement_3_0= ruleStatementList
            {

            					newCompositeNode(grammarAccess.getWhileStatementAccess().getStatementStatementListParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_79);
            lv_statement_3_0=ruleStatementList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileStatementRule());
            					}
            					set(
            						current,
            						"statement",
            						lv_statement_3_0,
            						"su.nsk.iae.post.PoST.StatementList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,92,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getWhileStatementAccess().getEND_WHILEKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWhileStatement"


    // $ANTLR start "entryRuleRepeatStatement"
    // InternalPoST.g:4664:1: entryRuleRepeatStatement returns [EObject current=null] : iv_ruleRepeatStatement= ruleRepeatStatement EOF ;
    public final EObject entryRuleRepeatStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRepeatStatement = null;


        try {
            // InternalPoST.g:4664:56: (iv_ruleRepeatStatement= ruleRepeatStatement EOF )
            // InternalPoST.g:4665:2: iv_ruleRepeatStatement= ruleRepeatStatement EOF
            {
             newCompositeNode(grammarAccess.getRepeatStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRepeatStatement=ruleRepeatStatement();

            state._fsp--;

             current =iv_ruleRepeatStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRepeatStatement"


    // $ANTLR start "ruleRepeatStatement"
    // InternalPoST.g:4671:1: ruleRepeatStatement returns [EObject current=null] : (otherlv_0= 'REPEAT' ( (lv_statement_1_0= ruleStatementList ) ) otherlv_2= 'UNTIL' ( (lv_cond_3_0= ruleExpression ) ) otherlv_4= 'END_REPEAT' ) ;
    public final EObject ruleRepeatStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_statement_1_0 = null;

        EObject lv_cond_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4677:2: ( (otherlv_0= 'REPEAT' ( (lv_statement_1_0= ruleStatementList ) ) otherlv_2= 'UNTIL' ( (lv_cond_3_0= ruleExpression ) ) otherlv_4= 'END_REPEAT' ) )
            // InternalPoST.g:4678:2: (otherlv_0= 'REPEAT' ( (lv_statement_1_0= ruleStatementList ) ) otherlv_2= 'UNTIL' ( (lv_cond_3_0= ruleExpression ) ) otherlv_4= 'END_REPEAT' )
            {
            // InternalPoST.g:4678:2: (otherlv_0= 'REPEAT' ( (lv_statement_1_0= ruleStatementList ) ) otherlv_2= 'UNTIL' ( (lv_cond_3_0= ruleExpression ) ) otherlv_4= 'END_REPEAT' )
            // InternalPoST.g:4679:3: otherlv_0= 'REPEAT' ( (lv_statement_1_0= ruleStatementList ) ) otherlv_2= 'UNTIL' ( (lv_cond_3_0= ruleExpression ) ) otherlv_4= 'END_REPEAT'
            {
            otherlv_0=(Token)match(input,93,FOLLOW_80); 

            			newLeafNode(otherlv_0, grammarAccess.getRepeatStatementAccess().getREPEATKeyword_0());
            		
            // InternalPoST.g:4683:3: ( (lv_statement_1_0= ruleStatementList ) )
            // InternalPoST.g:4684:4: (lv_statement_1_0= ruleStatementList )
            {
            // InternalPoST.g:4684:4: (lv_statement_1_0= ruleStatementList )
            // InternalPoST.g:4685:5: lv_statement_1_0= ruleStatementList
            {

            					newCompositeNode(grammarAccess.getRepeatStatementAccess().getStatementStatementListParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_81);
            lv_statement_1_0=ruleStatementList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRepeatStatementRule());
            					}
            					set(
            						current,
            						"statement",
            						lv_statement_1_0,
            						"su.nsk.iae.post.PoST.StatementList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,94,FOLLOW_51); 

            			newLeafNode(otherlv_2, grammarAccess.getRepeatStatementAccess().getUNTILKeyword_2());
            		
            // InternalPoST.g:4706:3: ( (lv_cond_3_0= ruleExpression ) )
            // InternalPoST.g:4707:4: (lv_cond_3_0= ruleExpression )
            {
            // InternalPoST.g:4707:4: (lv_cond_3_0= ruleExpression )
            // InternalPoST.g:4708:5: lv_cond_3_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getRepeatStatementAccess().getCondExpressionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_82);
            lv_cond_3_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRepeatStatementRule());
            					}
            					set(
            						current,
            						"cond",
            						lv_cond_3_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,95,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getRepeatStatementAccess().getEND_REPEATKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRepeatStatement"


    // $ANTLR start "entryRuleSubprogramControlStatement"
    // InternalPoST.g:4733:1: entryRuleSubprogramControlStatement returns [EObject current=null] : iv_ruleSubprogramControlStatement= ruleSubprogramControlStatement EOF ;
    public final EObject entryRuleSubprogramControlStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubprogramControlStatement = null;


        try {
            // InternalPoST.g:4733:67: (iv_ruleSubprogramControlStatement= ruleSubprogramControlStatement EOF )
            // InternalPoST.g:4734:2: iv_ruleSubprogramControlStatement= ruleSubprogramControlStatement EOF
            {
             newCompositeNode(grammarAccess.getSubprogramControlStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSubprogramControlStatement=ruleSubprogramControlStatement();

            state._fsp--;

             current =iv_ruleSubprogramControlStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSubprogramControlStatement"


    // $ANTLR start "ruleSubprogramControlStatement"
    // InternalPoST.g:4740:1: ruleSubprogramControlStatement returns [EObject current=null] : ( () otherlv_1= 'RETURN' ) ;
    public final EObject ruleSubprogramControlStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalPoST.g:4746:2: ( ( () otherlv_1= 'RETURN' ) )
            // InternalPoST.g:4747:2: ( () otherlv_1= 'RETURN' )
            {
            // InternalPoST.g:4747:2: ( () otherlv_1= 'RETURN' )
            // InternalPoST.g:4748:3: () otherlv_1= 'RETURN'
            {
            // InternalPoST.g:4748:3: ()
            // InternalPoST.g:4749:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSubprogramControlStatementAccess().getSubprogramControlStatementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,96,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getSubprogramControlStatementAccess().getRETURNKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSubprogramControlStatement"


    // $ANTLR start "entryRuleExitStatement"
    // InternalPoST.g:4763:1: entryRuleExitStatement returns [EObject current=null] : iv_ruleExitStatement= ruleExitStatement EOF ;
    public final EObject entryRuleExitStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExitStatement = null;


        try {
            // InternalPoST.g:4763:54: (iv_ruleExitStatement= ruleExitStatement EOF )
            // InternalPoST.g:4764:2: iv_ruleExitStatement= ruleExitStatement EOF
            {
             newCompositeNode(grammarAccess.getExitStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExitStatement=ruleExitStatement();

            state._fsp--;

             current =iv_ruleExitStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExitStatement"


    // $ANTLR start "ruleExitStatement"
    // InternalPoST.g:4770:1: ruleExitStatement returns [EObject current=null] : ( () otherlv_1= 'EXIT' ) ;
    public final EObject ruleExitStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalPoST.g:4776:2: ( ( () otherlv_1= 'EXIT' ) )
            // InternalPoST.g:4777:2: ( () otherlv_1= 'EXIT' )
            {
            // InternalPoST.g:4777:2: ( () otherlv_1= 'EXIT' )
            // InternalPoST.g:4778:3: () otherlv_1= 'EXIT'
            {
            // InternalPoST.g:4778:3: ()
            // InternalPoST.g:4779:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExitStatementAccess().getExitStatementAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,97,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getExitStatementAccess().getEXITKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExitStatement"


    // $ANTLR start "entryRuleSymbolicVariable"
    // InternalPoST.g:4793:1: entryRuleSymbolicVariable returns [EObject current=null] : iv_ruleSymbolicVariable= ruleSymbolicVariable EOF ;
    public final EObject entryRuleSymbolicVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSymbolicVariable = null;


        try {
            // InternalPoST.g:4793:57: (iv_ruleSymbolicVariable= ruleSymbolicVariable EOF )
            // InternalPoST.g:4794:2: iv_ruleSymbolicVariable= ruleSymbolicVariable EOF
            {
             newCompositeNode(grammarAccess.getSymbolicVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSymbolicVariable=ruleSymbolicVariable();

            state._fsp--;

             current =iv_ruleSymbolicVariable; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSymbolicVariable"


    // $ANTLR start "ruleSymbolicVariable"
    // InternalPoST.g:4800:1: ruleSymbolicVariable returns [EObject current=null] : ( (lv_name_0_0= RULE_ID ) ) ;
    public final EObject ruleSymbolicVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalPoST.g:4806:2: ( ( (lv_name_0_0= RULE_ID ) ) )
            // InternalPoST.g:4807:2: ( (lv_name_0_0= RULE_ID ) )
            {
            // InternalPoST.g:4807:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalPoST.g:4808:3: (lv_name_0_0= RULE_ID )
            {
            // InternalPoST.g:4808:3: (lv_name_0_0= RULE_ID )
            // InternalPoST.g:4809:4: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getSymbolicVariableAccess().getNameIDTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getSymbolicVariableRule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"su.nsk.iae.post.PoST.ID");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSymbolicVariable"


    // $ANTLR start "entryRuleSimpleSpecificationInit"
    // InternalPoST.g:4828:1: entryRuleSimpleSpecificationInit returns [EObject current=null] : iv_ruleSimpleSpecificationInit= ruleSimpleSpecificationInit EOF ;
    public final EObject entryRuleSimpleSpecificationInit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleSpecificationInit = null;


        try {
            // InternalPoST.g:4828:64: (iv_ruleSimpleSpecificationInit= ruleSimpleSpecificationInit EOF )
            // InternalPoST.g:4829:2: iv_ruleSimpleSpecificationInit= ruleSimpleSpecificationInit EOF
            {
             newCompositeNode(grammarAccess.getSimpleSpecificationInitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleSpecificationInit=ruleSimpleSpecificationInit();

            state._fsp--;

             current =iv_ruleSimpleSpecificationInit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleSpecificationInit"


    // $ANTLR start "ruleSimpleSpecificationInit"
    // InternalPoST.g:4835:1: ruleSimpleSpecificationInit returns [EObject current=null] : ( () ( (lv_type_1_0= ruleDataTypeName ) ) (otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) )? ) ;
    public final EObject ruleSimpleSpecificationInit() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_type_1_0 = null;

        EObject lv_value_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4841:2: ( ( () ( (lv_type_1_0= ruleDataTypeName ) ) (otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) )? ) )
            // InternalPoST.g:4842:2: ( () ( (lv_type_1_0= ruleDataTypeName ) ) (otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) )? )
            {
            // InternalPoST.g:4842:2: ( () ( (lv_type_1_0= ruleDataTypeName ) ) (otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) )? )
            // InternalPoST.g:4843:3: () ( (lv_type_1_0= ruleDataTypeName ) ) (otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) )?
            {
            // InternalPoST.g:4843:3: ()
            // InternalPoST.g:4844:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSimpleSpecificationInitAccess().getSimpleSpecificationInitAction_0(),
            					current);
            			

            }

            // InternalPoST.g:4850:3: ( (lv_type_1_0= ruleDataTypeName ) )
            // InternalPoST.g:4851:4: (lv_type_1_0= ruleDataTypeName )
            {
            // InternalPoST.g:4851:4: (lv_type_1_0= ruleDataTypeName )
            // InternalPoST.g:4852:5: lv_type_1_0= ruleDataTypeName
            {

            					newCompositeNode(grammarAccess.getSimpleSpecificationInitAccess().getTypeDataTypeNameParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_83);
            lv_type_1_0=ruleDataTypeName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSimpleSpecificationInitRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_1_0,
            						"su.nsk.iae.post.PoST.DataTypeName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:4869:3: (otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) ) )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==49) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalPoST.g:4870:4: otherlv_2= ':=' ( (lv_value_3_0= ruleExpression ) )
                    {
                    otherlv_2=(Token)match(input,49,FOLLOW_51); 

                    				newLeafNode(otherlv_2, grammarAccess.getSimpleSpecificationInitAccess().getColonEqualsSignKeyword_2_0());
                    			
                    // InternalPoST.g:4874:4: ( (lv_value_3_0= ruleExpression ) )
                    // InternalPoST.g:4875:5: (lv_value_3_0= ruleExpression )
                    {
                    // InternalPoST.g:4875:5: (lv_value_3_0= ruleExpression )
                    // InternalPoST.g:4876:6: lv_value_3_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getSimpleSpecificationInitAccess().getValueExpressionParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_value_3_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSimpleSpecificationInitRule());
                    						}
                    						set(
                    							current,
                    							"value",
                    							lv_value_3_0,
                    							"su.nsk.iae.post.PoST.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleSpecificationInit"


    // $ANTLR start "entryRuleVarList"
    // InternalPoST.g:4898:1: entryRuleVarList returns [EObject current=null] : iv_ruleVarList= ruleVarList EOF ;
    public final EObject entryRuleVarList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarList = null;


        try {
            // InternalPoST.g:4898:48: (iv_ruleVarList= ruleVarList EOF )
            // InternalPoST.g:4899:2: iv_ruleVarList= ruleVarList EOF
            {
             newCompositeNode(grammarAccess.getVarListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarList=ruleVarList();

            state._fsp--;

             current =iv_ruleVarList; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarList"


    // $ANTLR start "ruleVarList"
    // InternalPoST.g:4905:1: ruleVarList returns [EObject current=null] : ( ( (lv_vars_0_0= ruleSymbolicVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleSymbolicVariable ) ) )* ) ;
    public final EObject ruleVarList() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_vars_0_0 = null;

        EObject lv_vars_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4911:2: ( ( ( (lv_vars_0_0= ruleSymbolicVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleSymbolicVariable ) ) )* ) )
            // InternalPoST.g:4912:2: ( ( (lv_vars_0_0= ruleSymbolicVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleSymbolicVariable ) ) )* )
            {
            // InternalPoST.g:4912:2: ( ( (lv_vars_0_0= ruleSymbolicVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleSymbolicVariable ) ) )* )
            // InternalPoST.g:4913:3: ( (lv_vars_0_0= ruleSymbolicVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleSymbolicVariable ) ) )*
            {
            // InternalPoST.g:4913:3: ( (lv_vars_0_0= ruleSymbolicVariable ) )
            // InternalPoST.g:4914:4: (lv_vars_0_0= ruleSymbolicVariable )
            {
            // InternalPoST.g:4914:4: (lv_vars_0_0= ruleSymbolicVariable )
            // InternalPoST.g:4915:5: lv_vars_0_0= ruleSymbolicVariable
            {

            					newCompositeNode(grammarAccess.getVarListAccess().getVarsSymbolicVariableParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_23);
            lv_vars_0_0=ruleSymbolicVariable();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarListRule());
            					}
            					add(
            						current,
            						"vars",
            						lv_vars_0_0,
            						"su.nsk.iae.post.PoST.SymbolicVariable");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:4932:3: (otherlv_1= ',' ( (lv_vars_2_0= ruleSymbolicVariable ) ) )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==50) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // InternalPoST.g:4933:4: otherlv_1= ',' ( (lv_vars_2_0= ruleSymbolicVariable ) )
            	    {
            	    otherlv_1=(Token)match(input,50,FOLLOW_4); 

            	    				newLeafNode(otherlv_1, grammarAccess.getVarListAccess().getCommaKeyword_1_0());
            	    			
            	    // InternalPoST.g:4937:4: ( (lv_vars_2_0= ruleSymbolicVariable ) )
            	    // InternalPoST.g:4938:5: (lv_vars_2_0= ruleSymbolicVariable )
            	    {
            	    // InternalPoST.g:4938:5: (lv_vars_2_0= ruleSymbolicVariable )
            	    // InternalPoST.g:4939:6: lv_vars_2_0= ruleSymbolicVariable
            	    {

            	    						newCompositeNode(grammarAccess.getVarListAccess().getVarsSymbolicVariableParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_23);
            	    lv_vars_2_0=ruleSymbolicVariable();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getVarListRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_2_0,
            	    							"su.nsk.iae.post.PoST.SymbolicVariable");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop62;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarList"


    // $ANTLR start "entryRuleVarInitDeclaration"
    // InternalPoST.g:4961:1: entryRuleVarInitDeclaration returns [EObject current=null] : iv_ruleVarInitDeclaration= ruleVarInitDeclaration EOF ;
    public final EObject entryRuleVarInitDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarInitDeclaration = null;


        try {
            // InternalPoST.g:4961:59: (iv_ruleVarInitDeclaration= ruleVarInitDeclaration EOF )
            // InternalPoST.g:4962:2: iv_ruleVarInitDeclaration= ruleVarInitDeclaration EOF
            {
             newCompositeNode(grammarAccess.getVarInitDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarInitDeclaration=ruleVarInitDeclaration();

            state._fsp--;

             current =iv_ruleVarInitDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarInitDeclaration"


    // $ANTLR start "ruleVarInitDeclaration"
    // InternalPoST.g:4968:1: ruleVarInitDeclaration returns [EObject current=null] : ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( ( (lv_spec_2_0= ruleSimpleSpecificationInit ) ) | ( (lv_arrSpec_3_0= ruleArraySpecificationInit ) ) | ( (otherlv_4= RULE_ID ) ) ) ) ;
    public final EObject ruleVarInitDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        EObject lv_varList_0_0 = null;

        EObject lv_spec_2_0 = null;

        EObject lv_arrSpec_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:4974:2: ( ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( ( (lv_spec_2_0= ruleSimpleSpecificationInit ) ) | ( (lv_arrSpec_3_0= ruleArraySpecificationInit ) ) | ( (otherlv_4= RULE_ID ) ) ) ) )
            // InternalPoST.g:4975:2: ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( ( (lv_spec_2_0= ruleSimpleSpecificationInit ) ) | ( (lv_arrSpec_3_0= ruleArraySpecificationInit ) ) | ( (otherlv_4= RULE_ID ) ) ) )
            {
            // InternalPoST.g:4975:2: ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( ( (lv_spec_2_0= ruleSimpleSpecificationInit ) ) | ( (lv_arrSpec_3_0= ruleArraySpecificationInit ) ) | ( (otherlv_4= RULE_ID ) ) ) )
            // InternalPoST.g:4976:3: ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( ( (lv_spec_2_0= ruleSimpleSpecificationInit ) ) | ( (lv_arrSpec_3_0= ruleArraySpecificationInit ) ) | ( (otherlv_4= RULE_ID ) ) )
            {
            // InternalPoST.g:4976:3: ( (lv_varList_0_0= ruleVarList ) )
            // InternalPoST.g:4977:4: (lv_varList_0_0= ruleVarList )
            {
            // InternalPoST.g:4977:4: (lv_varList_0_0= ruleVarList )
            // InternalPoST.g:4978:5: lv_varList_0_0= ruleVarList
            {

            					newCompositeNode(grammarAccess.getVarInitDeclarationAccess().getVarListVarListParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_20);
            lv_varList_0_0=ruleVarList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarInitDeclarationRule());
            					}
            					set(
            						current,
            						"varList",
            						lv_varList_0_0,
            						"su.nsk.iae.post.PoST.VarList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,53,FOLLOW_84); 

            			newLeafNode(otherlv_1, grammarAccess.getVarInitDeclarationAccess().getColonKeyword_1());
            		
            // InternalPoST.g:4999:3: ( ( (lv_spec_2_0= ruleSimpleSpecificationInit ) ) | ( (lv_arrSpec_3_0= ruleArraySpecificationInit ) ) | ( (otherlv_4= RULE_ID ) ) )
            int alt63=3;
            switch ( input.LA(1) ) {
            case RULE_BIT_STRING_TYPE_NAME:
            case RULE_TIME_TYPE_NAME:
            case RULE_STRING_TYPE_NAME:
            case RULE_REAL_TYPE_NAME:
            case RULE_SIGNED_INTEGER_TYPE_NAME:
            case RULE_UNSIGNED_INTEGER_TYPE_NAME:
                {
                alt63=1;
                }
                break;
            case 111:
                {
                alt63=2;
                }
                break;
            case RULE_ID:
                {
                alt63=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // InternalPoST.g:5000:4: ( (lv_spec_2_0= ruleSimpleSpecificationInit ) )
                    {
                    // InternalPoST.g:5000:4: ( (lv_spec_2_0= ruleSimpleSpecificationInit ) )
                    // InternalPoST.g:5001:5: (lv_spec_2_0= ruleSimpleSpecificationInit )
                    {
                    // InternalPoST.g:5001:5: (lv_spec_2_0= ruleSimpleSpecificationInit )
                    // InternalPoST.g:5002:6: lv_spec_2_0= ruleSimpleSpecificationInit
                    {

                    						newCompositeNode(grammarAccess.getVarInitDeclarationAccess().getSpecSimpleSpecificationInitParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_spec_2_0=ruleSimpleSpecificationInit();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getVarInitDeclarationRule());
                    						}
                    						set(
                    							current,
                    							"spec",
                    							lv_spec_2_0,
                    							"su.nsk.iae.post.PoST.SimpleSpecificationInit");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:5020:4: ( (lv_arrSpec_3_0= ruleArraySpecificationInit ) )
                    {
                    // InternalPoST.g:5020:4: ( (lv_arrSpec_3_0= ruleArraySpecificationInit ) )
                    // InternalPoST.g:5021:5: (lv_arrSpec_3_0= ruleArraySpecificationInit )
                    {
                    // InternalPoST.g:5021:5: (lv_arrSpec_3_0= ruleArraySpecificationInit )
                    // InternalPoST.g:5022:6: lv_arrSpec_3_0= ruleArraySpecificationInit
                    {

                    						newCompositeNode(grammarAccess.getVarInitDeclarationAccess().getArrSpecArraySpecificationInitParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_arrSpec_3_0=ruleArraySpecificationInit();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getVarInitDeclarationRule());
                    						}
                    						set(
                    							current,
                    							"arrSpec",
                    							lv_arrSpec_3_0,
                    							"su.nsk.iae.post.PoST.ArraySpecificationInit");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalPoST.g:5040:4: ( (otherlv_4= RULE_ID ) )
                    {
                    // InternalPoST.g:5040:4: ( (otherlv_4= RULE_ID ) )
                    // InternalPoST.g:5041:5: (otherlv_4= RULE_ID )
                    {
                    // InternalPoST.g:5041:5: (otherlv_4= RULE_ID )
                    // InternalPoST.g:5042:6: otherlv_4= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVarInitDeclarationRule());
                    						}
                    					
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(otherlv_4, grammarAccess.getVarInitDeclarationAccess().getFbFunctionBlockCrossReference_2_2_0());
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarInitDeclaration"


    // $ANTLR start "entryRuleInputVarDeclaration"
    // InternalPoST.g:5058:1: entryRuleInputVarDeclaration returns [EObject current=null] : iv_ruleInputVarDeclaration= ruleInputVarDeclaration EOF ;
    public final EObject entryRuleInputVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInputVarDeclaration = null;


        try {
            // InternalPoST.g:5058:60: (iv_ruleInputVarDeclaration= ruleInputVarDeclaration EOF )
            // InternalPoST.g:5059:2: iv_ruleInputVarDeclaration= ruleInputVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getInputVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInputVarDeclaration=ruleInputVarDeclaration();

            state._fsp--;

             current =iv_ruleInputVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInputVarDeclaration"


    // $ANTLR start "ruleInputVarDeclaration"
    // InternalPoST.g:5065:1: ruleInputVarDeclaration returns [EObject current=null] : ( () otherlv_1= 'VAR_INPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) ;
    public final EObject ruleInputVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_vars_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5071:2: ( ( () otherlv_1= 'VAR_INPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) )
            // InternalPoST.g:5072:2: ( () otherlv_1= 'VAR_INPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            {
            // InternalPoST.g:5072:2: ( () otherlv_1= 'VAR_INPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            // InternalPoST.g:5073:3: () otherlv_1= 'VAR_INPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR'
            {
            // InternalPoST.g:5073:3: ()
            // InternalPoST.g:5074:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInputVarDeclarationAccess().getInputVarDeclarationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,98,FOLLOW_85); 

            			newLeafNode(otherlv_1, grammarAccess.getInputVarDeclarationAccess().getVAR_INPUTKeyword_1());
            		
            // InternalPoST.g:5084:3: ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==RULE_ID) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // InternalPoST.g:5085:4: ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';'
            	    {
            	    // InternalPoST.g:5085:4: ( (lv_vars_2_0= ruleVarInitDeclaration ) )
            	    // InternalPoST.g:5086:5: (lv_vars_2_0= ruleVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5086:5: (lv_vars_2_0= ruleVarInitDeclaration )
            	    // InternalPoST.g:5087:6: lv_vars_2_0= ruleVarInitDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getInputVarDeclarationAccess().getVarsVarInitDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_vars_2_0=ruleVarInitDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getInputVarDeclarationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_2_0,
            	    							"su.nsk.iae.post.PoST.VarInitDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_3=(Token)match(input,45,FOLLOW_85); 

            	    				newLeafNode(otherlv_3, grammarAccess.getInputVarDeclarationAccess().getSemicolonKeyword_2_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);

            otherlv_4=(Token)match(input,99,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getInputVarDeclarationAccess().getEND_VARKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInputVarDeclaration"


    // $ANTLR start "entryRuleOutputVarDeclaration"
    // InternalPoST.g:5117:1: entryRuleOutputVarDeclaration returns [EObject current=null] : iv_ruleOutputVarDeclaration= ruleOutputVarDeclaration EOF ;
    public final EObject entryRuleOutputVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputVarDeclaration = null;


        try {
            // InternalPoST.g:5117:61: (iv_ruleOutputVarDeclaration= ruleOutputVarDeclaration EOF )
            // InternalPoST.g:5118:2: iv_ruleOutputVarDeclaration= ruleOutputVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getOutputVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOutputVarDeclaration=ruleOutputVarDeclaration();

            state._fsp--;

             current =iv_ruleOutputVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOutputVarDeclaration"


    // $ANTLR start "ruleOutputVarDeclaration"
    // InternalPoST.g:5124:1: ruleOutputVarDeclaration returns [EObject current=null] : ( () otherlv_1= 'VAR_OUTPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) ;
    public final EObject ruleOutputVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_vars_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5130:2: ( ( () otherlv_1= 'VAR_OUTPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) )
            // InternalPoST.g:5131:2: ( () otherlv_1= 'VAR_OUTPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            {
            // InternalPoST.g:5131:2: ( () otherlv_1= 'VAR_OUTPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            // InternalPoST.g:5132:3: () otherlv_1= 'VAR_OUTPUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR'
            {
            // InternalPoST.g:5132:3: ()
            // InternalPoST.g:5133:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getOutputVarDeclarationAccess().getOutputVarDeclarationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,100,FOLLOW_85); 

            			newLeafNode(otherlv_1, grammarAccess.getOutputVarDeclarationAccess().getVAR_OUTPUTKeyword_1());
            		
            // InternalPoST.g:5143:3: ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==RULE_ID) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // InternalPoST.g:5144:4: ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';'
            	    {
            	    // InternalPoST.g:5144:4: ( (lv_vars_2_0= ruleVarInitDeclaration ) )
            	    // InternalPoST.g:5145:5: (lv_vars_2_0= ruleVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5145:5: (lv_vars_2_0= ruleVarInitDeclaration )
            	    // InternalPoST.g:5146:6: lv_vars_2_0= ruleVarInitDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getOutputVarDeclarationAccess().getVarsVarInitDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_vars_2_0=ruleVarInitDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOutputVarDeclarationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_2_0,
            	    							"su.nsk.iae.post.PoST.VarInitDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_3=(Token)match(input,45,FOLLOW_85); 

            	    				newLeafNode(otherlv_3, grammarAccess.getOutputVarDeclarationAccess().getSemicolonKeyword_2_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);

            otherlv_4=(Token)match(input,99,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getOutputVarDeclarationAccess().getEND_VARKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOutputVarDeclaration"


    // $ANTLR start "entryRuleInputOutputVarDeclaration"
    // InternalPoST.g:5176:1: entryRuleInputOutputVarDeclaration returns [EObject current=null] : iv_ruleInputOutputVarDeclaration= ruleInputOutputVarDeclaration EOF ;
    public final EObject entryRuleInputOutputVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInputOutputVarDeclaration = null;


        try {
            // InternalPoST.g:5176:66: (iv_ruleInputOutputVarDeclaration= ruleInputOutputVarDeclaration EOF )
            // InternalPoST.g:5177:2: iv_ruleInputOutputVarDeclaration= ruleInputOutputVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getInputOutputVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInputOutputVarDeclaration=ruleInputOutputVarDeclaration();

            state._fsp--;

             current =iv_ruleInputOutputVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInputOutputVarDeclaration"


    // $ANTLR start "ruleInputOutputVarDeclaration"
    // InternalPoST.g:5183:1: ruleInputOutputVarDeclaration returns [EObject current=null] : ( () otherlv_1= 'VAR_IN_OUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) ;
    public final EObject ruleInputOutputVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_vars_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5189:2: ( ( () otherlv_1= 'VAR_IN_OUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) )
            // InternalPoST.g:5190:2: ( () otherlv_1= 'VAR_IN_OUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            {
            // InternalPoST.g:5190:2: ( () otherlv_1= 'VAR_IN_OUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            // InternalPoST.g:5191:3: () otherlv_1= 'VAR_IN_OUT' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR'
            {
            // InternalPoST.g:5191:3: ()
            // InternalPoST.g:5192:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInputOutputVarDeclarationAccess().getInputOutputVarDeclarationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,101,FOLLOW_85); 

            			newLeafNode(otherlv_1, grammarAccess.getInputOutputVarDeclarationAccess().getVAR_IN_OUTKeyword_1());
            		
            // InternalPoST.g:5202:3: ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==RULE_ID) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // InternalPoST.g:5203:4: ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';'
            	    {
            	    // InternalPoST.g:5203:4: ( (lv_vars_2_0= ruleVarInitDeclaration ) )
            	    // InternalPoST.g:5204:5: (lv_vars_2_0= ruleVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5204:5: (lv_vars_2_0= ruleVarInitDeclaration )
            	    // InternalPoST.g:5205:6: lv_vars_2_0= ruleVarInitDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getInputOutputVarDeclarationAccess().getVarsVarInitDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_vars_2_0=ruleVarInitDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getInputOutputVarDeclarationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_2_0,
            	    							"su.nsk.iae.post.PoST.VarInitDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_3=(Token)match(input,45,FOLLOW_85); 

            	    				newLeafNode(otherlv_3, grammarAccess.getInputOutputVarDeclarationAccess().getSemicolonKeyword_2_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop66;
                }
            } while (true);

            otherlv_4=(Token)match(input,99,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getInputOutputVarDeclarationAccess().getEND_VARKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInputOutputVarDeclaration"


    // $ANTLR start "entryRuleVarDeclaration"
    // InternalPoST.g:5235:1: entryRuleVarDeclaration returns [EObject current=null] : iv_ruleVarDeclaration= ruleVarDeclaration EOF ;
    public final EObject entryRuleVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarDeclaration = null;


        try {
            // InternalPoST.g:5235:55: (iv_ruleVarDeclaration= ruleVarDeclaration EOF )
            // InternalPoST.g:5236:2: iv_ruleVarDeclaration= ruleVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarDeclaration=ruleVarDeclaration();

            state._fsp--;

             current =iv_ruleVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarDeclaration"


    // $ANTLR start "ruleVarDeclaration"
    // InternalPoST.g:5242:1: ruleVarDeclaration returns [EObject current=null] : ( () otherlv_1= 'VAR' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR' ) ;
    public final EObject ruleVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_const_2_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_vars_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5248:2: ( ( () otherlv_1= 'VAR' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR' ) )
            // InternalPoST.g:5249:2: ( () otherlv_1= 'VAR' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR' )
            {
            // InternalPoST.g:5249:2: ( () otherlv_1= 'VAR' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR' )
            // InternalPoST.g:5250:3: () otherlv_1= 'VAR' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR'
            {
            // InternalPoST.g:5250:3: ()
            // InternalPoST.g:5251:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVarDeclarationAccess().getVarDeclarationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,102,FOLLOW_86); 

            			newLeafNode(otherlv_1, grammarAccess.getVarDeclarationAccess().getVARKeyword_1());
            		
            // InternalPoST.g:5261:3: ( (lv_const_2_0= 'CONSTANT' ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==103) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalPoST.g:5262:4: (lv_const_2_0= 'CONSTANT' )
                    {
                    // InternalPoST.g:5262:4: (lv_const_2_0= 'CONSTANT' )
                    // InternalPoST.g:5263:5: lv_const_2_0= 'CONSTANT'
                    {
                    lv_const_2_0=(Token)match(input,103,FOLLOW_85); 

                    					newLeafNode(lv_const_2_0, grammarAccess.getVarDeclarationAccess().getConstCONSTANTKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getVarDeclarationRule());
                    					}
                    					setWithLastConsumed(current, "const", lv_const_2_0 != null, "CONSTANT");
                    				

                    }


                    }
                    break;

            }

            // InternalPoST.g:5275:3: ( ( (lv_vars_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' )*
            loop68:
            do {
                int alt68=2;
                int LA68_0 = input.LA(1);

                if ( (LA68_0==RULE_ID) ) {
                    alt68=1;
                }


                switch (alt68) {
            	case 1 :
            	    // InternalPoST.g:5276:4: ( (lv_vars_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';'
            	    {
            	    // InternalPoST.g:5276:4: ( (lv_vars_3_0= ruleVarInitDeclaration ) )
            	    // InternalPoST.g:5277:5: (lv_vars_3_0= ruleVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5277:5: (lv_vars_3_0= ruleVarInitDeclaration )
            	    // InternalPoST.g:5278:6: lv_vars_3_0= ruleVarInitDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getVarDeclarationAccess().getVarsVarInitDeclarationParserRuleCall_3_0_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_vars_3_0=ruleVarInitDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getVarDeclarationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_3_0,
            	    							"su.nsk.iae.post.PoST.VarInitDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_4=(Token)match(input,45,FOLLOW_85); 

            	    				newLeafNode(otherlv_4, grammarAccess.getVarDeclarationAccess().getSemicolonKeyword_3_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);

            otherlv_5=(Token)match(input,99,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getVarDeclarationAccess().getEND_VARKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarDeclaration"


    // $ANTLR start "entryRuleTempVarDeclaration"
    // InternalPoST.g:5308:1: entryRuleTempVarDeclaration returns [EObject current=null] : iv_ruleTempVarDeclaration= ruleTempVarDeclaration EOF ;
    public final EObject entryRuleTempVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTempVarDeclaration = null;


        try {
            // InternalPoST.g:5308:59: (iv_ruleTempVarDeclaration= ruleTempVarDeclaration EOF )
            // InternalPoST.g:5309:2: iv_ruleTempVarDeclaration= ruleTempVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getTempVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTempVarDeclaration=ruleTempVarDeclaration();

            state._fsp--;

             current =iv_ruleTempVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTempVarDeclaration"


    // $ANTLR start "ruleTempVarDeclaration"
    // InternalPoST.g:5315:1: ruleTempVarDeclaration returns [EObject current=null] : ( () otherlv_1= 'VAR_TEMP' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) ;
    public final EObject ruleTempVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_vars_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5321:2: ( ( () otherlv_1= 'VAR_TEMP' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) )
            // InternalPoST.g:5322:2: ( () otherlv_1= 'VAR_TEMP' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            {
            // InternalPoST.g:5322:2: ( () otherlv_1= 'VAR_TEMP' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            // InternalPoST.g:5323:3: () otherlv_1= 'VAR_TEMP' ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR'
            {
            // InternalPoST.g:5323:3: ()
            // InternalPoST.g:5324:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTempVarDeclarationAccess().getTempVarDeclarationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,104,FOLLOW_85); 

            			newLeafNode(otherlv_1, grammarAccess.getTempVarDeclarationAccess().getVAR_TEMPKeyword_1());
            		
            // InternalPoST.g:5334:3: ( ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';' )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==RULE_ID) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // InternalPoST.g:5335:4: ( (lv_vars_2_0= ruleVarInitDeclaration ) ) otherlv_3= ';'
            	    {
            	    // InternalPoST.g:5335:4: ( (lv_vars_2_0= ruleVarInitDeclaration ) )
            	    // InternalPoST.g:5336:5: (lv_vars_2_0= ruleVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5336:5: (lv_vars_2_0= ruleVarInitDeclaration )
            	    // InternalPoST.g:5337:6: lv_vars_2_0= ruleVarInitDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getTempVarDeclarationAccess().getVarsVarInitDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_vars_2_0=ruleVarInitDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getTempVarDeclarationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_2_0,
            	    							"su.nsk.iae.post.PoST.VarInitDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_3=(Token)match(input,45,FOLLOW_85); 

            	    				newLeafNode(otherlv_3, grammarAccess.getTempVarDeclarationAccess().getSemicolonKeyword_2_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);

            otherlv_4=(Token)match(input,99,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getTempVarDeclarationAccess().getEND_VARKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTempVarDeclaration"


    // $ANTLR start "entryRuleExternalVarInitDeclaration"
    // InternalPoST.g:5367:1: entryRuleExternalVarInitDeclaration returns [EObject current=null] : iv_ruleExternalVarInitDeclaration= ruleExternalVarInitDeclaration EOF ;
    public final EObject entryRuleExternalVarInitDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExternalVarInitDeclaration = null;


        try {
            // InternalPoST.g:5367:67: (iv_ruleExternalVarInitDeclaration= ruleExternalVarInitDeclaration EOF )
            // InternalPoST.g:5368:2: iv_ruleExternalVarInitDeclaration= ruleExternalVarInitDeclaration EOF
            {
             newCompositeNode(grammarAccess.getExternalVarInitDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExternalVarInitDeclaration=ruleExternalVarInitDeclaration();

            state._fsp--;

             current =iv_ruleExternalVarInitDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExternalVarInitDeclaration"


    // $ANTLR start "ruleExternalVarInitDeclaration"
    // InternalPoST.g:5374:1: ruleExternalVarInitDeclaration returns [EObject current=null] : ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( (lv_type_2_0= ruleDataTypeName ) ) ) ;
    public final EObject ruleExternalVarInitDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_varList_0_0 = null;

        AntlrDatatypeRuleToken lv_type_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5380:2: ( ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( (lv_type_2_0= ruleDataTypeName ) ) ) )
            // InternalPoST.g:5381:2: ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( (lv_type_2_0= ruleDataTypeName ) ) )
            {
            // InternalPoST.g:5381:2: ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( (lv_type_2_0= ruleDataTypeName ) ) )
            // InternalPoST.g:5382:3: ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= ':' ( (lv_type_2_0= ruleDataTypeName ) )
            {
            // InternalPoST.g:5382:3: ( (lv_varList_0_0= ruleVarList ) )
            // InternalPoST.g:5383:4: (lv_varList_0_0= ruleVarList )
            {
            // InternalPoST.g:5383:4: (lv_varList_0_0= ruleVarList )
            // InternalPoST.g:5384:5: lv_varList_0_0= ruleVarList
            {

            					newCompositeNode(grammarAccess.getExternalVarInitDeclarationAccess().getVarListVarListParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_20);
            lv_varList_0_0=ruleVarList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExternalVarInitDeclarationRule());
            					}
            					set(
            						current,
            						"varList",
            						lv_varList_0_0,
            						"su.nsk.iae.post.PoST.VarList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,53,FOLLOW_31); 

            			newLeafNode(otherlv_1, grammarAccess.getExternalVarInitDeclarationAccess().getColonKeyword_1());
            		
            // InternalPoST.g:5405:3: ( (lv_type_2_0= ruleDataTypeName ) )
            // InternalPoST.g:5406:4: (lv_type_2_0= ruleDataTypeName )
            {
            // InternalPoST.g:5406:4: (lv_type_2_0= ruleDataTypeName )
            // InternalPoST.g:5407:5: lv_type_2_0= ruleDataTypeName
            {

            					newCompositeNode(grammarAccess.getExternalVarInitDeclarationAccess().getTypeDataTypeNameParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_type_2_0=ruleDataTypeName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExternalVarInitDeclarationRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_2_0,
            						"su.nsk.iae.post.PoST.DataTypeName");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExternalVarInitDeclaration"


    // $ANTLR start "entryRuleExternalVarDeclaration"
    // InternalPoST.g:5428:1: entryRuleExternalVarDeclaration returns [EObject current=null] : iv_ruleExternalVarDeclaration= ruleExternalVarDeclaration EOF ;
    public final EObject entryRuleExternalVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExternalVarDeclaration = null;


        try {
            // InternalPoST.g:5428:63: (iv_ruleExternalVarDeclaration= ruleExternalVarDeclaration EOF )
            // InternalPoST.g:5429:2: iv_ruleExternalVarDeclaration= ruleExternalVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getExternalVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExternalVarDeclaration=ruleExternalVarDeclaration();

            state._fsp--;

             current =iv_ruleExternalVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExternalVarDeclaration"


    // $ANTLR start "ruleExternalVarDeclaration"
    // InternalPoST.g:5435:1: ruleExternalVarDeclaration returns [EObject current=null] : ( () otherlv_1= 'VAR_EXTERNAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleExternalVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR' ) ;
    public final EObject ruleExternalVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_const_2_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_vars_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5441:2: ( ( () otherlv_1= 'VAR_EXTERNAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleExternalVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR' ) )
            // InternalPoST.g:5442:2: ( () otherlv_1= 'VAR_EXTERNAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleExternalVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR' )
            {
            // InternalPoST.g:5442:2: ( () otherlv_1= 'VAR_EXTERNAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleExternalVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR' )
            // InternalPoST.g:5443:3: () otherlv_1= 'VAR_EXTERNAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( (lv_vars_3_0= ruleExternalVarInitDeclaration ) ) otherlv_4= ';' )* otherlv_5= 'END_VAR'
            {
            // InternalPoST.g:5443:3: ()
            // InternalPoST.g:5444:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExternalVarDeclarationAccess().getExternalVarDeclarationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,105,FOLLOW_86); 

            			newLeafNode(otherlv_1, grammarAccess.getExternalVarDeclarationAccess().getVAR_EXTERNALKeyword_1());
            		
            // InternalPoST.g:5454:3: ( (lv_const_2_0= 'CONSTANT' ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==103) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalPoST.g:5455:4: (lv_const_2_0= 'CONSTANT' )
                    {
                    // InternalPoST.g:5455:4: (lv_const_2_0= 'CONSTANT' )
                    // InternalPoST.g:5456:5: lv_const_2_0= 'CONSTANT'
                    {
                    lv_const_2_0=(Token)match(input,103,FOLLOW_85); 

                    					newLeafNode(lv_const_2_0, grammarAccess.getExternalVarDeclarationAccess().getConstCONSTANTKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getExternalVarDeclarationRule());
                    					}
                    					setWithLastConsumed(current, "const", lv_const_2_0 != null, "CONSTANT");
                    				

                    }


                    }
                    break;

            }

            // InternalPoST.g:5468:3: ( ( (lv_vars_3_0= ruleExternalVarInitDeclaration ) ) otherlv_4= ';' )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==RULE_ID) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // InternalPoST.g:5469:4: ( (lv_vars_3_0= ruleExternalVarInitDeclaration ) ) otherlv_4= ';'
            	    {
            	    // InternalPoST.g:5469:4: ( (lv_vars_3_0= ruleExternalVarInitDeclaration ) )
            	    // InternalPoST.g:5470:5: (lv_vars_3_0= ruleExternalVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5470:5: (lv_vars_3_0= ruleExternalVarInitDeclaration )
            	    // InternalPoST.g:5471:6: lv_vars_3_0= ruleExternalVarInitDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getExternalVarDeclarationAccess().getVarsExternalVarInitDeclarationParserRuleCall_3_0_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_vars_3_0=ruleExternalVarInitDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getExternalVarDeclarationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_3_0,
            	    							"su.nsk.iae.post.PoST.ExternalVarInitDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_4=(Token)match(input,45,FOLLOW_85); 

            	    				newLeafNode(otherlv_4, grammarAccess.getExternalVarDeclarationAccess().getSemicolonKeyword_3_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop71;
                }
            } while (true);

            otherlv_5=(Token)match(input,99,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getExternalVarDeclarationAccess().getEND_VARKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExternalVarDeclaration"


    // $ANTLR start "entryRuleGlobalVarInitDeclaration"
    // InternalPoST.g:5501:1: entryRuleGlobalVarInitDeclaration returns [EObject current=null] : iv_ruleGlobalVarInitDeclaration= ruleGlobalVarInitDeclaration EOF ;
    public final EObject entryRuleGlobalVarInitDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarInitDeclaration = null;


        try {
            // InternalPoST.g:5501:65: (iv_ruleGlobalVarInitDeclaration= ruleGlobalVarInitDeclaration EOF )
            // InternalPoST.g:5502:2: iv_ruleGlobalVarInitDeclaration= ruleGlobalVarInitDeclaration EOF
            {
             newCompositeNode(grammarAccess.getGlobalVarInitDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGlobalVarInitDeclaration=ruleGlobalVarInitDeclaration();

            state._fsp--;

             current =iv_ruleGlobalVarInitDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGlobalVarInitDeclaration"


    // $ANTLR start "ruleGlobalVarInitDeclaration"
    // InternalPoST.g:5508:1: ruleGlobalVarInitDeclaration returns [EObject current=null] : ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= 'AT' ( (lv_location_2_0= RULE_DIRECT_VARIABLE ) ) otherlv_3= ':' ( (lv_type_4_0= ruleDataTypeName ) ) ) ;
    public final EObject ruleGlobalVarInitDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_location_2_0=null;
        Token otherlv_3=null;
        EObject lv_varList_0_0 = null;

        AntlrDatatypeRuleToken lv_type_4_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5514:2: ( ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= 'AT' ( (lv_location_2_0= RULE_DIRECT_VARIABLE ) ) otherlv_3= ':' ( (lv_type_4_0= ruleDataTypeName ) ) ) )
            // InternalPoST.g:5515:2: ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= 'AT' ( (lv_location_2_0= RULE_DIRECT_VARIABLE ) ) otherlv_3= ':' ( (lv_type_4_0= ruleDataTypeName ) ) )
            {
            // InternalPoST.g:5515:2: ( ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= 'AT' ( (lv_location_2_0= RULE_DIRECT_VARIABLE ) ) otherlv_3= ':' ( (lv_type_4_0= ruleDataTypeName ) ) )
            // InternalPoST.g:5516:3: ( (lv_varList_0_0= ruleVarList ) ) otherlv_1= 'AT' ( (lv_location_2_0= RULE_DIRECT_VARIABLE ) ) otherlv_3= ':' ( (lv_type_4_0= ruleDataTypeName ) )
            {
            // InternalPoST.g:5516:3: ( (lv_varList_0_0= ruleVarList ) )
            // InternalPoST.g:5517:4: (lv_varList_0_0= ruleVarList )
            {
            // InternalPoST.g:5517:4: (lv_varList_0_0= ruleVarList )
            // InternalPoST.g:5518:5: lv_varList_0_0= ruleVarList
            {

            					newCompositeNode(grammarAccess.getGlobalVarInitDeclarationAccess().getVarListVarListParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_87);
            lv_varList_0_0=ruleVarList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGlobalVarInitDeclarationRule());
            					}
            					set(
            						current,
            						"varList",
            						lv_varList_0_0,
            						"su.nsk.iae.post.PoST.VarList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,106,FOLLOW_88); 

            			newLeafNode(otherlv_1, grammarAccess.getGlobalVarInitDeclarationAccess().getATKeyword_1());
            		
            // InternalPoST.g:5539:3: ( (lv_location_2_0= RULE_DIRECT_VARIABLE ) )
            // InternalPoST.g:5540:4: (lv_location_2_0= RULE_DIRECT_VARIABLE )
            {
            // InternalPoST.g:5540:4: (lv_location_2_0= RULE_DIRECT_VARIABLE )
            // InternalPoST.g:5541:5: lv_location_2_0= RULE_DIRECT_VARIABLE
            {
            lv_location_2_0=(Token)match(input,RULE_DIRECT_VARIABLE,FOLLOW_20); 

            					newLeafNode(lv_location_2_0, grammarAccess.getGlobalVarInitDeclarationAccess().getLocationDIRECT_VARIABLETerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGlobalVarInitDeclarationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"location",
            						lv_location_2_0,
            						"su.nsk.iae.post.PoST.DIRECT_VARIABLE");
            				

            }


            }

            otherlv_3=(Token)match(input,53,FOLLOW_31); 

            			newLeafNode(otherlv_3, grammarAccess.getGlobalVarInitDeclarationAccess().getColonKeyword_3());
            		
            // InternalPoST.g:5561:3: ( (lv_type_4_0= ruleDataTypeName ) )
            // InternalPoST.g:5562:4: (lv_type_4_0= ruleDataTypeName )
            {
            // InternalPoST.g:5562:4: (lv_type_4_0= ruleDataTypeName )
            // InternalPoST.g:5563:5: lv_type_4_0= ruleDataTypeName
            {

            					newCompositeNode(grammarAccess.getGlobalVarInitDeclarationAccess().getTypeDataTypeNameParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_type_4_0=ruleDataTypeName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGlobalVarInitDeclarationRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_4_0,
            						"su.nsk.iae.post.PoST.DataTypeName");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGlobalVarInitDeclaration"


    // $ANTLR start "entryRuleGlobalVarDeclaration"
    // InternalPoST.g:5584:1: entryRuleGlobalVarDeclaration returns [EObject current=null] : iv_ruleGlobalVarDeclaration= ruleGlobalVarDeclaration EOF ;
    public final EObject entryRuleGlobalVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarDeclaration = null;


        try {
            // InternalPoST.g:5584:61: (iv_ruleGlobalVarDeclaration= ruleGlobalVarDeclaration EOF )
            // InternalPoST.g:5585:2: iv_ruleGlobalVarDeclaration= ruleGlobalVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getGlobalVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGlobalVarDeclaration=ruleGlobalVarDeclaration();

            state._fsp--;

             current =iv_ruleGlobalVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGlobalVarDeclaration"


    // $ANTLR start "ruleGlobalVarDeclaration"
    // InternalPoST.g:5591:1: ruleGlobalVarDeclaration returns [EObject current=null] : ( () otherlv_1= 'VAR_GLOBAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' ) | ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' ) )* otherlv_7= 'END_VAR' ) ;
    public final EObject ruleGlobalVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_const_2_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_varsSimple_3_0 = null;

        EObject lv_varsAs_5_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5597:2: ( ( () otherlv_1= 'VAR_GLOBAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' ) | ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' ) )* otherlv_7= 'END_VAR' ) )
            // InternalPoST.g:5598:2: ( () otherlv_1= 'VAR_GLOBAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' ) | ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' ) )* otherlv_7= 'END_VAR' )
            {
            // InternalPoST.g:5598:2: ( () otherlv_1= 'VAR_GLOBAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' ) | ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' ) )* otherlv_7= 'END_VAR' )
            // InternalPoST.g:5599:3: () otherlv_1= 'VAR_GLOBAL' ( (lv_const_2_0= 'CONSTANT' ) )? ( ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' ) | ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' ) )* otherlv_7= 'END_VAR'
            {
            // InternalPoST.g:5599:3: ()
            // InternalPoST.g:5600:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getGlobalVarDeclarationAccess().getGlobalVarDeclarationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,107,FOLLOW_86); 

            			newLeafNode(otherlv_1, grammarAccess.getGlobalVarDeclarationAccess().getVAR_GLOBALKeyword_1());
            		
            // InternalPoST.g:5610:3: ( (lv_const_2_0= 'CONSTANT' ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==103) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalPoST.g:5611:4: (lv_const_2_0= 'CONSTANT' )
                    {
                    // InternalPoST.g:5611:4: (lv_const_2_0= 'CONSTANT' )
                    // InternalPoST.g:5612:5: lv_const_2_0= 'CONSTANT'
                    {
                    lv_const_2_0=(Token)match(input,103,FOLLOW_85); 

                    					newLeafNode(lv_const_2_0, grammarAccess.getGlobalVarDeclarationAccess().getConstCONSTANTKeyword_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getGlobalVarDeclarationRule());
                    					}
                    					setWithLastConsumed(current, "const", lv_const_2_0 != null, "CONSTANT");
                    				

                    }


                    }
                    break;

            }

            // InternalPoST.g:5624:3: ( ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' ) | ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' ) )*
            loop73:
            do {
                int alt73=3;
                alt73 = dfa73.predict(input);
                switch (alt73) {
            	case 1 :
            	    // InternalPoST.g:5625:4: ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' )
            	    {
            	    // InternalPoST.g:5625:4: ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' )
            	    // InternalPoST.g:5626:5: ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';'
            	    {
            	    // InternalPoST.g:5626:5: ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) )
            	    // InternalPoST.g:5627:6: (lv_varsSimple_3_0= ruleVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5627:6: (lv_varsSimple_3_0= ruleVarInitDeclaration )
            	    // InternalPoST.g:5628:7: lv_varsSimple_3_0= ruleVarInitDeclaration
            	    {

            	    							newCompositeNode(grammarAccess.getGlobalVarDeclarationAccess().getVarsSimpleVarInitDeclarationParserRuleCall_3_0_0_0());
            	    						
            	    pushFollow(FOLLOW_9);
            	    lv_varsSimple_3_0=ruleVarInitDeclaration();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getGlobalVarDeclarationRule());
            	    							}
            	    							add(
            	    								current,
            	    								"varsSimple",
            	    								lv_varsSimple_3_0,
            	    								"su.nsk.iae.post.PoST.VarInitDeclaration");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }

            	    otherlv_4=(Token)match(input,45,FOLLOW_85); 

            	    					newLeafNode(otherlv_4, grammarAccess.getGlobalVarDeclarationAccess().getSemicolonKeyword_3_0_1());
            	    				

            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalPoST.g:5651:4: ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' )
            	    {
            	    // InternalPoST.g:5651:4: ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' )
            	    // InternalPoST.g:5652:5: ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';'
            	    {
            	    // InternalPoST.g:5652:5: ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) )
            	    // InternalPoST.g:5653:6: (lv_varsAs_5_0= ruleGlobalVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5653:6: (lv_varsAs_5_0= ruleGlobalVarInitDeclaration )
            	    // InternalPoST.g:5654:7: lv_varsAs_5_0= ruleGlobalVarInitDeclaration
            	    {

            	    							newCompositeNode(grammarAccess.getGlobalVarDeclarationAccess().getVarsAsGlobalVarInitDeclarationParserRuleCall_3_1_0_0());
            	    						
            	    pushFollow(FOLLOW_9);
            	    lv_varsAs_5_0=ruleGlobalVarInitDeclaration();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getGlobalVarDeclarationRule());
            	    							}
            	    							add(
            	    								current,
            	    								"varsAs",
            	    								lv_varsAs_5_0,
            	    								"su.nsk.iae.post.PoST.GlobalVarInitDeclaration");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }

            	    otherlv_6=(Token)match(input,45,FOLLOW_85); 

            	    					newLeafNode(otherlv_6, grammarAccess.getGlobalVarDeclarationAccess().getSemicolonKeyword_3_1_1());
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);

            otherlv_7=(Token)match(input,99,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getGlobalVarDeclarationAccess().getEND_VARKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGlobalVarDeclaration"


    // $ANTLR start "entryRuleProcessVariable"
    // InternalPoST.g:5685:1: entryRuleProcessVariable returns [EObject current=null] : iv_ruleProcessVariable= ruleProcessVariable EOF ;
    public final EObject entryRuleProcessVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcessVariable = null;


        try {
            // InternalPoST.g:5685:56: (iv_ruleProcessVariable= ruleProcessVariable EOF )
            // InternalPoST.g:5686:2: iv_ruleProcessVariable= ruleProcessVariable EOF
            {
             newCompositeNode(grammarAccess.getProcessVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcessVariable=ruleProcessVariable();

            state._fsp--;

             current =iv_ruleProcessVariable; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcessVariable"


    // $ANTLR start "ruleProcessVariable"
    // InternalPoST.g:5692:1: ruleProcessVariable returns [EObject current=null] : ( (lv_name_0_0= RULE_ID ) ) ;
    public final EObject ruleProcessVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalPoST.g:5698:2: ( ( (lv_name_0_0= RULE_ID ) ) )
            // InternalPoST.g:5699:2: ( (lv_name_0_0= RULE_ID ) )
            {
            // InternalPoST.g:5699:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalPoST.g:5700:3: (lv_name_0_0= RULE_ID )
            {
            // InternalPoST.g:5700:3: (lv_name_0_0= RULE_ID )
            // InternalPoST.g:5701:4: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getProcessVariableAccess().getNameIDTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getProcessVariableRule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"su.nsk.iae.post.PoST.ID");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcessVariable"


    // $ANTLR start "entryRuleProcessVarList"
    // InternalPoST.g:5720:1: entryRuleProcessVarList returns [EObject current=null] : iv_ruleProcessVarList= ruleProcessVarList EOF ;
    public final EObject entryRuleProcessVarList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcessVarList = null;


        try {
            // InternalPoST.g:5720:55: (iv_ruleProcessVarList= ruleProcessVarList EOF )
            // InternalPoST.g:5721:2: iv_ruleProcessVarList= ruleProcessVarList EOF
            {
             newCompositeNode(grammarAccess.getProcessVarListRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcessVarList=ruleProcessVarList();

            state._fsp--;

             current =iv_ruleProcessVarList; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcessVarList"


    // $ANTLR start "ruleProcessVarList"
    // InternalPoST.g:5727:1: ruleProcessVarList returns [EObject current=null] : ( ( (lv_vars_0_0= ruleProcessVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleProcessVariable ) ) )* ) ;
    public final EObject ruleProcessVarList() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_vars_0_0 = null;

        EObject lv_vars_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5733:2: ( ( ( (lv_vars_0_0= ruleProcessVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleProcessVariable ) ) )* ) )
            // InternalPoST.g:5734:2: ( ( (lv_vars_0_0= ruleProcessVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleProcessVariable ) ) )* )
            {
            // InternalPoST.g:5734:2: ( ( (lv_vars_0_0= ruleProcessVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleProcessVariable ) ) )* )
            // InternalPoST.g:5735:3: ( (lv_vars_0_0= ruleProcessVariable ) ) (otherlv_1= ',' ( (lv_vars_2_0= ruleProcessVariable ) ) )*
            {
            // InternalPoST.g:5735:3: ( (lv_vars_0_0= ruleProcessVariable ) )
            // InternalPoST.g:5736:4: (lv_vars_0_0= ruleProcessVariable )
            {
            // InternalPoST.g:5736:4: (lv_vars_0_0= ruleProcessVariable )
            // InternalPoST.g:5737:5: lv_vars_0_0= ruleProcessVariable
            {

            					newCompositeNode(grammarAccess.getProcessVarListAccess().getVarsProcessVariableParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_23);
            lv_vars_0_0=ruleProcessVariable();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProcessVarListRule());
            					}
            					add(
            						current,
            						"vars",
            						lv_vars_0_0,
            						"su.nsk.iae.post.PoST.ProcessVariable");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:5754:3: (otherlv_1= ',' ( (lv_vars_2_0= ruleProcessVariable ) ) )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==50) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // InternalPoST.g:5755:4: otherlv_1= ',' ( (lv_vars_2_0= ruleProcessVariable ) )
            	    {
            	    otherlv_1=(Token)match(input,50,FOLLOW_4); 

            	    				newLeafNode(otherlv_1, grammarAccess.getProcessVarListAccess().getCommaKeyword_1_0());
            	    			
            	    // InternalPoST.g:5759:4: ( (lv_vars_2_0= ruleProcessVariable ) )
            	    // InternalPoST.g:5760:5: (lv_vars_2_0= ruleProcessVariable )
            	    {
            	    // InternalPoST.g:5760:5: (lv_vars_2_0= ruleProcessVariable )
            	    // InternalPoST.g:5761:6: lv_vars_2_0= ruleProcessVariable
            	    {

            	    						newCompositeNode(grammarAccess.getProcessVarListAccess().getVarsProcessVariableParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_23);
            	    lv_vars_2_0=ruleProcessVariable();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProcessVarListRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_2_0,
            	    							"su.nsk.iae.post.PoST.ProcessVariable");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcessVarList"


    // $ANTLR start "entryRuleProcessVarInitDeclaration"
    // InternalPoST.g:5783:1: entryRuleProcessVarInitDeclaration returns [EObject current=null] : iv_ruleProcessVarInitDeclaration= ruleProcessVarInitDeclaration EOF ;
    public final EObject entryRuleProcessVarInitDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcessVarInitDeclaration = null;


        try {
            // InternalPoST.g:5783:66: (iv_ruleProcessVarInitDeclaration= ruleProcessVarInitDeclaration EOF )
            // InternalPoST.g:5784:2: iv_ruleProcessVarInitDeclaration= ruleProcessVarInitDeclaration EOF
            {
             newCompositeNode(grammarAccess.getProcessVarInitDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcessVarInitDeclaration=ruleProcessVarInitDeclaration();

            state._fsp--;

             current =iv_ruleProcessVarInitDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcessVarInitDeclaration"


    // $ANTLR start "ruleProcessVarInitDeclaration"
    // InternalPoST.g:5790:1: ruleProcessVarInitDeclaration returns [EObject current=null] : ( ( (lv_varList_0_0= ruleProcessVarList ) ) otherlv_1= ':' ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleProcessVarInitDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_varList_0_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5796:2: ( ( ( (lv_varList_0_0= ruleProcessVarList ) ) otherlv_1= ':' ( (otherlv_2= RULE_ID ) ) ) )
            // InternalPoST.g:5797:2: ( ( (lv_varList_0_0= ruleProcessVarList ) ) otherlv_1= ':' ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalPoST.g:5797:2: ( ( (lv_varList_0_0= ruleProcessVarList ) ) otherlv_1= ':' ( (otherlv_2= RULE_ID ) ) )
            // InternalPoST.g:5798:3: ( (lv_varList_0_0= ruleProcessVarList ) ) otherlv_1= ':' ( (otherlv_2= RULE_ID ) )
            {
            // InternalPoST.g:5798:3: ( (lv_varList_0_0= ruleProcessVarList ) )
            // InternalPoST.g:5799:4: (lv_varList_0_0= ruleProcessVarList )
            {
            // InternalPoST.g:5799:4: (lv_varList_0_0= ruleProcessVarList )
            // InternalPoST.g:5800:5: lv_varList_0_0= ruleProcessVarList
            {

            					newCompositeNode(grammarAccess.getProcessVarInitDeclarationAccess().getVarListProcessVarListParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_20);
            lv_varList_0_0=ruleProcessVarList();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProcessVarInitDeclarationRule());
            					}
            					set(
            						current,
            						"varList",
            						lv_varList_0_0,
            						"su.nsk.iae.post.PoST.ProcessVarList");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,53,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getProcessVarInitDeclarationAccess().getColonKeyword_1());
            		
            // InternalPoST.g:5821:3: ( (otherlv_2= RULE_ID ) )
            // InternalPoST.g:5822:4: (otherlv_2= RULE_ID )
            {
            // InternalPoST.g:5822:4: (otherlv_2= RULE_ID )
            // InternalPoST.g:5823:5: otherlv_2= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProcessVarInitDeclarationRule());
            					}
            				
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_2, grammarAccess.getProcessVarInitDeclarationAccess().getProcessProcessCrossReference_2_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcessVarInitDeclaration"


    // $ANTLR start "entryRuleProcessVarDeclaration"
    // InternalPoST.g:5838:1: entryRuleProcessVarDeclaration returns [EObject current=null] : iv_ruleProcessVarDeclaration= ruleProcessVarDeclaration EOF ;
    public final EObject entryRuleProcessVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcessVarDeclaration = null;


        try {
            // InternalPoST.g:5838:62: (iv_ruleProcessVarDeclaration= ruleProcessVarDeclaration EOF )
            // InternalPoST.g:5839:2: iv_ruleProcessVarDeclaration= ruleProcessVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getProcessVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcessVarDeclaration=ruleProcessVarDeclaration();

            state._fsp--;

             current =iv_ruleProcessVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcessVarDeclaration"


    // $ANTLR start "ruleProcessVarDeclaration"
    // InternalPoST.g:5845:1: ruleProcessVarDeclaration returns [EObject current=null] : ( () otherlv_1= 'VAR_PROCESS' ( ( (lv_vars_2_0= ruleProcessVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) ;
    public final EObject ruleProcessVarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_vars_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5851:2: ( ( () otherlv_1= 'VAR_PROCESS' ( ( (lv_vars_2_0= ruleProcessVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' ) )
            // InternalPoST.g:5852:2: ( () otherlv_1= 'VAR_PROCESS' ( ( (lv_vars_2_0= ruleProcessVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            {
            // InternalPoST.g:5852:2: ( () otherlv_1= 'VAR_PROCESS' ( ( (lv_vars_2_0= ruleProcessVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR' )
            // InternalPoST.g:5853:3: () otherlv_1= 'VAR_PROCESS' ( ( (lv_vars_2_0= ruleProcessVarInitDeclaration ) ) otherlv_3= ';' )* otherlv_4= 'END_VAR'
            {
            // InternalPoST.g:5853:3: ()
            // InternalPoST.g:5854:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getProcessVarDeclarationAccess().getProcessVarDeclarationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,108,FOLLOW_85); 

            			newLeafNode(otherlv_1, grammarAccess.getProcessVarDeclarationAccess().getVAR_PROCESSKeyword_1());
            		
            // InternalPoST.g:5864:3: ( ( (lv_vars_2_0= ruleProcessVarInitDeclaration ) ) otherlv_3= ';' )*
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==RULE_ID) ) {
                    alt75=1;
                }


                switch (alt75) {
            	case 1 :
            	    // InternalPoST.g:5865:4: ( (lv_vars_2_0= ruleProcessVarInitDeclaration ) ) otherlv_3= ';'
            	    {
            	    // InternalPoST.g:5865:4: ( (lv_vars_2_0= ruleProcessVarInitDeclaration ) )
            	    // InternalPoST.g:5866:5: (lv_vars_2_0= ruleProcessVarInitDeclaration )
            	    {
            	    // InternalPoST.g:5866:5: (lv_vars_2_0= ruleProcessVarInitDeclaration )
            	    // InternalPoST.g:5867:6: lv_vars_2_0= ruleProcessVarInitDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getProcessVarDeclarationAccess().getVarsProcessVarInitDeclarationParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_9);
            	    lv_vars_2_0=ruleProcessVarInitDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getProcessVarDeclarationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"vars",
            	    							lv_vars_2_0,
            	    							"su.nsk.iae.post.PoST.ProcessVarInitDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_3=(Token)match(input,45,FOLLOW_85); 

            	    				newLeafNode(otherlv_3, grammarAccess.getProcessVarDeclarationAccess().getSemicolonKeyword_2_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop75;
                }
            } while (true);

            otherlv_4=(Token)match(input,99,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getProcessVarDeclarationAccess().getEND_VARKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcessVarDeclaration"


    // $ANTLR start "entryRuleArrayVariable"
    // InternalPoST.g:5897:1: entryRuleArrayVariable returns [EObject current=null] : iv_ruleArrayVariable= ruleArrayVariable EOF ;
    public final EObject entryRuleArrayVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayVariable = null;


        try {
            // InternalPoST.g:5897:54: (iv_ruleArrayVariable= ruleArrayVariable EOF )
            // InternalPoST.g:5898:2: iv_ruleArrayVariable= ruleArrayVariable EOF
            {
             newCompositeNode(grammarAccess.getArrayVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArrayVariable=ruleArrayVariable();

            state._fsp--;

             current =iv_ruleArrayVariable; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayVariable"


    // $ANTLR start "ruleArrayVariable"
    // InternalPoST.g:5904:1: ruleArrayVariable returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '[' ( (lv_index_2_0= ruleExpression ) ) otherlv_3= ']' ) ;
    public final EObject ruleArrayVariable() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_index_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5910:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '[' ( (lv_index_2_0= ruleExpression ) ) otherlv_3= ']' ) )
            // InternalPoST.g:5911:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '[' ( (lv_index_2_0= ruleExpression ) ) otherlv_3= ']' )
            {
            // InternalPoST.g:5911:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '[' ( (lv_index_2_0= ruleExpression ) ) otherlv_3= ']' )
            // InternalPoST.g:5912:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '[' ( (lv_index_2_0= ruleExpression ) ) otherlv_3= ']'
            {
            // InternalPoST.g:5912:3: ( (otherlv_0= RULE_ID ) )
            // InternalPoST.g:5913:4: (otherlv_0= RULE_ID )
            {
            // InternalPoST.g:5913:4: (otherlv_0= RULE_ID )
            // InternalPoST.g:5914:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getArrayVariableRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_89); 

            					newLeafNode(otherlv_0, grammarAccess.getArrayVariableAccess().getVariableSymbolicVariableCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,109,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getArrayVariableAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalPoST.g:5929:3: ( (lv_index_2_0= ruleExpression ) )
            // InternalPoST.g:5930:4: (lv_index_2_0= ruleExpression )
            {
            // InternalPoST.g:5930:4: (lv_index_2_0= ruleExpression )
            // InternalPoST.g:5931:5: lv_index_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getArrayVariableAccess().getIndexExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_90);
            lv_index_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getArrayVariableRule());
            					}
            					set(
            						current,
            						"index",
            						lv_index_2_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,110,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getArrayVariableAccess().getRightSquareBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayVariable"


    // $ANTLR start "entryRuleArraySpecificationInit"
    // InternalPoST.g:5956:1: entryRuleArraySpecificationInit returns [EObject current=null] : iv_ruleArraySpecificationInit= ruleArraySpecificationInit EOF ;
    public final EObject entryRuleArraySpecificationInit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArraySpecificationInit = null;


        try {
            // InternalPoST.g:5956:63: (iv_ruleArraySpecificationInit= ruleArraySpecificationInit EOF )
            // InternalPoST.g:5957:2: iv_ruleArraySpecificationInit= ruleArraySpecificationInit EOF
            {
             newCompositeNode(grammarAccess.getArraySpecificationInitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArraySpecificationInit=ruleArraySpecificationInit();

            state._fsp--;

             current =iv_ruleArraySpecificationInit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArraySpecificationInit"


    // $ANTLR start "ruleArraySpecificationInit"
    // InternalPoST.g:5963:1: ruleArraySpecificationInit returns [EObject current=null] : ( ( (lv_init_0_0= ruleArraySpecification ) ) (otherlv_1= ':=' ( (lv_values_2_0= ruleArrayInitialization ) ) )? ) ;
    public final EObject ruleArraySpecificationInit() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_init_0_0 = null;

        EObject lv_values_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:5969:2: ( ( ( (lv_init_0_0= ruleArraySpecification ) ) (otherlv_1= ':=' ( (lv_values_2_0= ruleArrayInitialization ) ) )? ) )
            // InternalPoST.g:5970:2: ( ( (lv_init_0_0= ruleArraySpecification ) ) (otherlv_1= ':=' ( (lv_values_2_0= ruleArrayInitialization ) ) )? )
            {
            // InternalPoST.g:5970:2: ( ( (lv_init_0_0= ruleArraySpecification ) ) (otherlv_1= ':=' ( (lv_values_2_0= ruleArrayInitialization ) ) )? )
            // InternalPoST.g:5971:3: ( (lv_init_0_0= ruleArraySpecification ) ) (otherlv_1= ':=' ( (lv_values_2_0= ruleArrayInitialization ) ) )?
            {
            // InternalPoST.g:5971:3: ( (lv_init_0_0= ruleArraySpecification ) )
            // InternalPoST.g:5972:4: (lv_init_0_0= ruleArraySpecification )
            {
            // InternalPoST.g:5972:4: (lv_init_0_0= ruleArraySpecification )
            // InternalPoST.g:5973:5: lv_init_0_0= ruleArraySpecification
            {

            					newCompositeNode(grammarAccess.getArraySpecificationInitAccess().getInitArraySpecificationParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_83);
            lv_init_0_0=ruleArraySpecification();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getArraySpecificationInitRule());
            					}
            					set(
            						current,
            						"init",
            						lv_init_0_0,
            						"su.nsk.iae.post.PoST.ArraySpecification");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:5990:3: (otherlv_1= ':=' ( (lv_values_2_0= ruleArrayInitialization ) ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==49) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalPoST.g:5991:4: otherlv_1= ':=' ( (lv_values_2_0= ruleArrayInitialization ) )
                    {
                    otherlv_1=(Token)match(input,49,FOLLOW_89); 

                    				newLeafNode(otherlv_1, grammarAccess.getArraySpecificationInitAccess().getColonEqualsSignKeyword_1_0());
                    			
                    // InternalPoST.g:5995:4: ( (lv_values_2_0= ruleArrayInitialization ) )
                    // InternalPoST.g:5996:5: (lv_values_2_0= ruleArrayInitialization )
                    {
                    // InternalPoST.g:5996:5: (lv_values_2_0= ruleArrayInitialization )
                    // InternalPoST.g:5997:6: lv_values_2_0= ruleArrayInitialization
                    {

                    						newCompositeNode(grammarAccess.getArraySpecificationInitAccess().getValuesArrayInitializationParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_values_2_0=ruleArrayInitialization();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getArraySpecificationInitRule());
                    						}
                    						set(
                    							current,
                    							"values",
                    							lv_values_2_0,
                    							"su.nsk.iae.post.PoST.ArrayInitialization");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArraySpecificationInit"


    // $ANTLR start "entryRuleArraySpecification"
    // InternalPoST.g:6019:1: entryRuleArraySpecification returns [EObject current=null] : iv_ruleArraySpecification= ruleArraySpecification EOF ;
    public final EObject entryRuleArraySpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArraySpecification = null;


        try {
            // InternalPoST.g:6019:59: (iv_ruleArraySpecification= ruleArraySpecification EOF )
            // InternalPoST.g:6020:2: iv_ruleArraySpecification= ruleArraySpecification EOF
            {
             newCompositeNode(grammarAccess.getArraySpecificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArraySpecification=ruleArraySpecification();

            state._fsp--;

             current =iv_ruleArraySpecification; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArraySpecification"


    // $ANTLR start "ruleArraySpecification"
    // InternalPoST.g:6026:1: ruleArraySpecification returns [EObject current=null] : (otherlv_0= 'ARRAY' otherlv_1= '[' ( ( (lv_interval_2_0= ruleArrayInterval ) ) | otherlv_3= '*' ) otherlv_4= ']' otherlv_5= 'OF' ( (lv_type_6_0= ruleDataTypeName ) ) ) ;
    public final EObject ruleArraySpecification() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_interval_2_0 = null;

        AntlrDatatypeRuleToken lv_type_6_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:6032:2: ( (otherlv_0= 'ARRAY' otherlv_1= '[' ( ( (lv_interval_2_0= ruleArrayInterval ) ) | otherlv_3= '*' ) otherlv_4= ']' otherlv_5= 'OF' ( (lv_type_6_0= ruleDataTypeName ) ) ) )
            // InternalPoST.g:6033:2: (otherlv_0= 'ARRAY' otherlv_1= '[' ( ( (lv_interval_2_0= ruleArrayInterval ) ) | otherlv_3= '*' ) otherlv_4= ']' otherlv_5= 'OF' ( (lv_type_6_0= ruleDataTypeName ) ) )
            {
            // InternalPoST.g:6033:2: (otherlv_0= 'ARRAY' otherlv_1= '[' ( ( (lv_interval_2_0= ruleArrayInterval ) ) | otherlv_3= '*' ) otherlv_4= ']' otherlv_5= 'OF' ( (lv_type_6_0= ruleDataTypeName ) ) )
            // InternalPoST.g:6034:3: otherlv_0= 'ARRAY' otherlv_1= '[' ( ( (lv_interval_2_0= ruleArrayInterval ) ) | otherlv_3= '*' ) otherlv_4= ']' otherlv_5= 'OF' ( (lv_type_6_0= ruleDataTypeName ) )
            {
            otherlv_0=(Token)match(input,111,FOLLOW_89); 

            			newLeafNode(otherlv_0, grammarAccess.getArraySpecificationAccess().getARRAYKeyword_0());
            		
            otherlv_1=(Token)match(input,109,FOLLOW_91); 

            			newLeafNode(otherlv_1, grammarAccess.getArraySpecificationAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalPoST.g:6042:3: ( ( (lv_interval_2_0= ruleArrayInterval ) ) | otherlv_3= '*' )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==RULE_ID||LA77_0==RULE_INTEGER||LA77_0==RULE_TIME_PREF_LITERAL||(LA77_0>=RULE_REAL_TYPE_NAME && LA77_0<=RULE_REAL)||LA77_0==47||LA77_0==54||LA77_0==78||LA77_0==114||LA77_0==126) ) {
                alt77=1;
            }
            else if ( (LA77_0==112) ) {
                alt77=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }
            switch (alt77) {
                case 1 :
                    // InternalPoST.g:6043:4: ( (lv_interval_2_0= ruleArrayInterval ) )
                    {
                    // InternalPoST.g:6043:4: ( (lv_interval_2_0= ruleArrayInterval ) )
                    // InternalPoST.g:6044:5: (lv_interval_2_0= ruleArrayInterval )
                    {
                    // InternalPoST.g:6044:5: (lv_interval_2_0= ruleArrayInterval )
                    // InternalPoST.g:6045:6: lv_interval_2_0= ruleArrayInterval
                    {

                    						newCompositeNode(grammarAccess.getArraySpecificationAccess().getIntervalArrayIntervalParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_90);
                    lv_interval_2_0=ruleArrayInterval();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getArraySpecificationRule());
                    						}
                    						set(
                    							current,
                    							"interval",
                    							lv_interval_2_0,
                    							"su.nsk.iae.post.PoST.ArrayInterval");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:6063:4: otherlv_3= '*'
                    {
                    otherlv_3=(Token)match(input,112,FOLLOW_90); 

                    				newLeafNode(otherlv_3, grammarAccess.getArraySpecificationAccess().getAsteriskKeyword_2_1());
                    			

                    }
                    break;

            }

            otherlv_4=(Token)match(input,110,FOLLOW_67); 

            			newLeafNode(otherlv_4, grammarAccess.getArraySpecificationAccess().getRightSquareBracketKeyword_3());
            		
            otherlv_5=(Token)match(input,84,FOLLOW_31); 

            			newLeafNode(otherlv_5, grammarAccess.getArraySpecificationAccess().getOFKeyword_4());
            		
            // InternalPoST.g:6076:3: ( (lv_type_6_0= ruleDataTypeName ) )
            // InternalPoST.g:6077:4: (lv_type_6_0= ruleDataTypeName )
            {
            // InternalPoST.g:6077:4: (lv_type_6_0= ruleDataTypeName )
            // InternalPoST.g:6078:5: lv_type_6_0= ruleDataTypeName
            {

            					newCompositeNode(grammarAccess.getArraySpecificationAccess().getTypeDataTypeNameParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_2);
            lv_type_6_0=ruleDataTypeName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getArraySpecificationRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_6_0,
            						"su.nsk.iae.post.PoST.DataTypeName");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArraySpecification"


    // $ANTLR start "entryRuleArrayInterval"
    // InternalPoST.g:6099:1: entryRuleArrayInterval returns [EObject current=null] : iv_ruleArrayInterval= ruleArrayInterval EOF ;
    public final EObject entryRuleArrayInterval() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayInterval = null;


        try {
            // InternalPoST.g:6099:54: (iv_ruleArrayInterval= ruleArrayInterval EOF )
            // InternalPoST.g:6100:2: iv_ruleArrayInterval= ruleArrayInterval EOF
            {
             newCompositeNode(grammarAccess.getArrayIntervalRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArrayInterval=ruleArrayInterval();

            state._fsp--;

             current =iv_ruleArrayInterval; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayInterval"


    // $ANTLR start "ruleArrayInterval"
    // InternalPoST.g:6106:1: ruleArrayInterval returns [EObject current=null] : ( ( (lv_start_0_0= ruleExpression ) ) otherlv_1= '..' ( (lv_end_2_0= ruleExpression ) ) ) ;
    public final EObject ruleArrayInterval() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_start_0_0 = null;

        EObject lv_end_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:6112:2: ( ( ( (lv_start_0_0= ruleExpression ) ) otherlv_1= '..' ( (lv_end_2_0= ruleExpression ) ) ) )
            // InternalPoST.g:6113:2: ( ( (lv_start_0_0= ruleExpression ) ) otherlv_1= '..' ( (lv_end_2_0= ruleExpression ) ) )
            {
            // InternalPoST.g:6113:2: ( ( (lv_start_0_0= ruleExpression ) ) otherlv_1= '..' ( (lv_end_2_0= ruleExpression ) ) )
            // InternalPoST.g:6114:3: ( (lv_start_0_0= ruleExpression ) ) otherlv_1= '..' ( (lv_end_2_0= ruleExpression ) )
            {
            // InternalPoST.g:6114:3: ( (lv_start_0_0= ruleExpression ) )
            // InternalPoST.g:6115:4: (lv_start_0_0= ruleExpression )
            {
            // InternalPoST.g:6115:4: (lv_start_0_0= ruleExpression )
            // InternalPoST.g:6116:5: lv_start_0_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getArrayIntervalAccess().getStartExpressionParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_92);
            lv_start_0_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getArrayIntervalRule());
            					}
            					set(
            						current,
            						"start",
            						lv_start_0_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,113,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getArrayIntervalAccess().getFullStopFullStopKeyword_1());
            		
            // InternalPoST.g:6137:3: ( (lv_end_2_0= ruleExpression ) )
            // InternalPoST.g:6138:4: (lv_end_2_0= ruleExpression )
            {
            // InternalPoST.g:6138:4: (lv_end_2_0= ruleExpression )
            // InternalPoST.g:6139:5: lv_end_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getArrayIntervalAccess().getEndExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_end_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getArrayIntervalRule());
            					}
            					set(
            						current,
            						"end",
            						lv_end_2_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayInterval"


    // $ANTLR start "entryRuleArrayInitialization"
    // InternalPoST.g:6160:1: entryRuleArrayInitialization returns [EObject current=null] : iv_ruleArrayInitialization= ruleArrayInitialization EOF ;
    public final EObject entryRuleArrayInitialization() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayInitialization = null;


        try {
            // InternalPoST.g:6160:60: (iv_ruleArrayInitialization= ruleArrayInitialization EOF )
            // InternalPoST.g:6161:2: iv_ruleArrayInitialization= ruleArrayInitialization EOF
            {
             newCompositeNode(grammarAccess.getArrayInitializationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArrayInitialization=ruleArrayInitialization();

            state._fsp--;

             current =iv_ruleArrayInitialization; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayInitialization"


    // $ANTLR start "ruleArrayInitialization"
    // InternalPoST.g:6167:1: ruleArrayInitialization returns [EObject current=null] : (otherlv_0= '[' ( (lv_elements_1_0= ruleExpression ) ) (otherlv_2= ',' ( (lv_elements_3_0= ruleExpression ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleArrayInitialization() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_elements_1_0 = null;

        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:6173:2: ( (otherlv_0= '[' ( (lv_elements_1_0= ruleExpression ) ) (otherlv_2= ',' ( (lv_elements_3_0= ruleExpression ) ) )* otherlv_4= ']' ) )
            // InternalPoST.g:6174:2: (otherlv_0= '[' ( (lv_elements_1_0= ruleExpression ) ) (otherlv_2= ',' ( (lv_elements_3_0= ruleExpression ) ) )* otherlv_4= ']' )
            {
            // InternalPoST.g:6174:2: (otherlv_0= '[' ( (lv_elements_1_0= ruleExpression ) ) (otherlv_2= ',' ( (lv_elements_3_0= ruleExpression ) ) )* otherlv_4= ']' )
            // InternalPoST.g:6175:3: otherlv_0= '[' ( (lv_elements_1_0= ruleExpression ) ) (otherlv_2= ',' ( (lv_elements_3_0= ruleExpression ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,109,FOLLOW_51); 

            			newLeafNode(otherlv_0, grammarAccess.getArrayInitializationAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalPoST.g:6179:3: ( (lv_elements_1_0= ruleExpression ) )
            // InternalPoST.g:6180:4: (lv_elements_1_0= ruleExpression )
            {
            // InternalPoST.g:6180:4: (lv_elements_1_0= ruleExpression )
            // InternalPoST.g:6181:5: lv_elements_1_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getArrayInitializationAccess().getElementsExpressionParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_93);
            lv_elements_1_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getArrayInitializationRule());
            					}
            					add(
            						current,
            						"elements",
            						lv_elements_1_0,
            						"su.nsk.iae.post.PoST.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalPoST.g:6198:3: (otherlv_2= ',' ( (lv_elements_3_0= ruleExpression ) ) )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==50) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // InternalPoST.g:6199:4: otherlv_2= ',' ( (lv_elements_3_0= ruleExpression ) )
            	    {
            	    otherlv_2=(Token)match(input,50,FOLLOW_51); 

            	    				newLeafNode(otherlv_2, grammarAccess.getArrayInitializationAccess().getCommaKeyword_2_0());
            	    			
            	    // InternalPoST.g:6203:4: ( (lv_elements_3_0= ruleExpression ) )
            	    // InternalPoST.g:6204:5: (lv_elements_3_0= ruleExpression )
            	    {
            	    // InternalPoST.g:6204:5: (lv_elements_3_0= ruleExpression )
            	    // InternalPoST.g:6205:6: lv_elements_3_0= ruleExpression
            	    {

            	    						newCompositeNode(grammarAccess.getArrayInitializationAccess().getElementsExpressionParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_93);
            	    lv_elements_3_0=ruleExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getArrayInitializationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_3_0,
            	    							"su.nsk.iae.post.PoST.Expression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop78;
                }
            } while (true);

            otherlv_4=(Token)match(input,110,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getArrayInitializationAccess().getRightSquareBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayInitialization"


    // $ANTLR start "entryRuleTimeLiteral"
    // InternalPoST.g:6231:1: entryRuleTimeLiteral returns [EObject current=null] : iv_ruleTimeLiteral= ruleTimeLiteral EOF ;
    public final EObject entryRuleTimeLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimeLiteral = null;


        try {
            // InternalPoST.g:6231:52: (iv_ruleTimeLiteral= ruleTimeLiteral EOF )
            // InternalPoST.g:6232:2: iv_ruleTimeLiteral= ruleTimeLiteral EOF
            {
             newCompositeNode(grammarAccess.getTimeLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTimeLiteral=ruleTimeLiteral();

            state._fsp--;

             current =iv_ruleTimeLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTimeLiteral"


    // $ANTLR start "ruleTimeLiteral"
    // InternalPoST.g:6238:1: ruleTimeLiteral returns [EObject current=null] : (this_TIME_PREF_LITERAL_0= RULE_TIME_PREF_LITERAL (otherlv_1= '-' )? ( (lv_interval_2_0= RULE_INTERVAL ) ) ) ;
    public final EObject ruleTimeLiteral() throws RecognitionException {
        EObject current = null;

        Token this_TIME_PREF_LITERAL_0=null;
        Token otherlv_1=null;
        Token lv_interval_2_0=null;


        	enterRule();

        try {
            // InternalPoST.g:6244:2: ( (this_TIME_PREF_LITERAL_0= RULE_TIME_PREF_LITERAL (otherlv_1= '-' )? ( (lv_interval_2_0= RULE_INTERVAL ) ) ) )
            // InternalPoST.g:6245:2: (this_TIME_PREF_LITERAL_0= RULE_TIME_PREF_LITERAL (otherlv_1= '-' )? ( (lv_interval_2_0= RULE_INTERVAL ) ) )
            {
            // InternalPoST.g:6245:2: (this_TIME_PREF_LITERAL_0= RULE_TIME_PREF_LITERAL (otherlv_1= '-' )? ( (lv_interval_2_0= RULE_INTERVAL ) ) )
            // InternalPoST.g:6246:3: this_TIME_PREF_LITERAL_0= RULE_TIME_PREF_LITERAL (otherlv_1= '-' )? ( (lv_interval_2_0= RULE_INTERVAL ) )
            {
            this_TIME_PREF_LITERAL_0=(Token)match(input,RULE_TIME_PREF_LITERAL,FOLLOW_94); 

            			newLeafNode(this_TIME_PREF_LITERAL_0, grammarAccess.getTimeLiteralAccess().getTIME_PREF_LITERALTerminalRuleCall_0());
            		
            // InternalPoST.g:6250:3: (otherlv_1= '-' )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==114) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // InternalPoST.g:6251:4: otherlv_1= '-'
                    {
                    otherlv_1=(Token)match(input,114,FOLLOW_95); 

                    				newLeafNode(otherlv_1, grammarAccess.getTimeLiteralAccess().getHyphenMinusKeyword_1());
                    			

                    }
                    break;

            }

            // InternalPoST.g:6256:3: ( (lv_interval_2_0= RULE_INTERVAL ) )
            // InternalPoST.g:6257:4: (lv_interval_2_0= RULE_INTERVAL )
            {
            // InternalPoST.g:6257:4: (lv_interval_2_0= RULE_INTERVAL )
            // InternalPoST.g:6258:5: lv_interval_2_0= RULE_INTERVAL
            {
            lv_interval_2_0=(Token)match(input,RULE_INTERVAL,FOLLOW_2); 

            					newLeafNode(lv_interval_2_0, grammarAccess.getTimeLiteralAccess().getIntervalINTERVALTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTimeLiteralRule());
            					}
            					setWithLastConsumed(
            						current,
            						"interval",
            						lv_interval_2_0,
            						"su.nsk.iae.post.PoST.INTERVAL");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTimeLiteral"


    // $ANTLR start "entryRuleDataTypeName"
    // InternalPoST.g:6278:1: entryRuleDataTypeName returns [String current=null] : iv_ruleDataTypeName= ruleDataTypeName EOF ;
    public final String entryRuleDataTypeName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDataTypeName = null;


        try {
            // InternalPoST.g:6278:52: (iv_ruleDataTypeName= ruleDataTypeName EOF )
            // InternalPoST.g:6279:2: iv_ruleDataTypeName= ruleDataTypeName EOF
            {
             newCompositeNode(grammarAccess.getDataTypeNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDataTypeName=ruleDataTypeName();

            state._fsp--;

             current =iv_ruleDataTypeName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDataTypeName"


    // $ANTLR start "ruleDataTypeName"
    // InternalPoST.g:6285:1: ruleDataTypeName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_NumericTypeName_0= ruleNumericTypeName | this_BIT_STRING_TYPE_NAME_1= RULE_BIT_STRING_TYPE_NAME | this_TIME_TYPE_NAME_2= RULE_TIME_TYPE_NAME | this_STRING_TYPE_NAME_3= RULE_STRING_TYPE_NAME ) ;
    public final AntlrDatatypeRuleToken ruleDataTypeName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BIT_STRING_TYPE_NAME_1=null;
        Token this_TIME_TYPE_NAME_2=null;
        Token this_STRING_TYPE_NAME_3=null;
        AntlrDatatypeRuleToken this_NumericTypeName_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:6291:2: ( (this_NumericTypeName_0= ruleNumericTypeName | this_BIT_STRING_TYPE_NAME_1= RULE_BIT_STRING_TYPE_NAME | this_TIME_TYPE_NAME_2= RULE_TIME_TYPE_NAME | this_STRING_TYPE_NAME_3= RULE_STRING_TYPE_NAME ) )
            // InternalPoST.g:6292:2: (this_NumericTypeName_0= ruleNumericTypeName | this_BIT_STRING_TYPE_NAME_1= RULE_BIT_STRING_TYPE_NAME | this_TIME_TYPE_NAME_2= RULE_TIME_TYPE_NAME | this_STRING_TYPE_NAME_3= RULE_STRING_TYPE_NAME )
            {
            // InternalPoST.g:6292:2: (this_NumericTypeName_0= ruleNumericTypeName | this_BIT_STRING_TYPE_NAME_1= RULE_BIT_STRING_TYPE_NAME | this_TIME_TYPE_NAME_2= RULE_TIME_TYPE_NAME | this_STRING_TYPE_NAME_3= RULE_STRING_TYPE_NAME )
            int alt80=4;
            switch ( input.LA(1) ) {
            case RULE_REAL_TYPE_NAME:
            case RULE_SIGNED_INTEGER_TYPE_NAME:
            case RULE_UNSIGNED_INTEGER_TYPE_NAME:
                {
                alt80=1;
                }
                break;
            case RULE_BIT_STRING_TYPE_NAME:
                {
                alt80=2;
                }
                break;
            case RULE_TIME_TYPE_NAME:
                {
                alt80=3;
                }
                break;
            case RULE_STRING_TYPE_NAME:
                {
                alt80=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;
            }

            switch (alt80) {
                case 1 :
                    // InternalPoST.g:6293:3: this_NumericTypeName_0= ruleNumericTypeName
                    {

                    			newCompositeNode(grammarAccess.getDataTypeNameAccess().getNumericTypeNameParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_NumericTypeName_0=ruleNumericTypeName();

                    state._fsp--;


                    			current.merge(this_NumericTypeName_0);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:6304:3: this_BIT_STRING_TYPE_NAME_1= RULE_BIT_STRING_TYPE_NAME
                    {
                    this_BIT_STRING_TYPE_NAME_1=(Token)match(input,RULE_BIT_STRING_TYPE_NAME,FOLLOW_2); 

                    			current.merge(this_BIT_STRING_TYPE_NAME_1);
                    		

                    			newLeafNode(this_BIT_STRING_TYPE_NAME_1, grammarAccess.getDataTypeNameAccess().getBIT_STRING_TYPE_NAMETerminalRuleCall_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalPoST.g:6312:3: this_TIME_TYPE_NAME_2= RULE_TIME_TYPE_NAME
                    {
                    this_TIME_TYPE_NAME_2=(Token)match(input,RULE_TIME_TYPE_NAME,FOLLOW_2); 

                    			current.merge(this_TIME_TYPE_NAME_2);
                    		

                    			newLeafNode(this_TIME_TYPE_NAME_2, grammarAccess.getDataTypeNameAccess().getTIME_TYPE_NAMETerminalRuleCall_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalPoST.g:6320:3: this_STRING_TYPE_NAME_3= RULE_STRING_TYPE_NAME
                    {
                    this_STRING_TYPE_NAME_3=(Token)match(input,RULE_STRING_TYPE_NAME,FOLLOW_2); 

                    			current.merge(this_STRING_TYPE_NAME_3);
                    		

                    			newLeafNode(this_STRING_TYPE_NAME_3, grammarAccess.getDataTypeNameAccess().getSTRING_TYPE_NAMETerminalRuleCall_3());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDataTypeName"


    // $ANTLR start "entryRuleNumericTypeName"
    // InternalPoST.g:6331:1: entryRuleNumericTypeName returns [String current=null] : iv_ruleNumericTypeName= ruleNumericTypeName EOF ;
    public final String entryRuleNumericTypeName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumericTypeName = null;


        try {
            // InternalPoST.g:6331:55: (iv_ruleNumericTypeName= ruleNumericTypeName EOF )
            // InternalPoST.g:6332:2: iv_ruleNumericTypeName= ruleNumericTypeName EOF
            {
             newCompositeNode(grammarAccess.getNumericTypeNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumericTypeName=ruleNumericTypeName();

            state._fsp--;

             current =iv_ruleNumericTypeName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNumericTypeName"


    // $ANTLR start "ruleNumericTypeName"
    // InternalPoST.g:6338:1: ruleNumericTypeName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IntegerTypeName_0= ruleIntegerTypeName | this_REAL_TYPE_NAME_1= RULE_REAL_TYPE_NAME ) ;
    public final AntlrDatatypeRuleToken ruleNumericTypeName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_REAL_TYPE_NAME_1=null;
        AntlrDatatypeRuleToken this_IntegerTypeName_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:6344:2: ( (this_IntegerTypeName_0= ruleIntegerTypeName | this_REAL_TYPE_NAME_1= RULE_REAL_TYPE_NAME ) )
            // InternalPoST.g:6345:2: (this_IntegerTypeName_0= ruleIntegerTypeName | this_REAL_TYPE_NAME_1= RULE_REAL_TYPE_NAME )
            {
            // InternalPoST.g:6345:2: (this_IntegerTypeName_0= ruleIntegerTypeName | this_REAL_TYPE_NAME_1= RULE_REAL_TYPE_NAME )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( ((LA81_0>=RULE_SIGNED_INTEGER_TYPE_NAME && LA81_0<=RULE_UNSIGNED_INTEGER_TYPE_NAME)) ) {
                alt81=1;
            }
            else if ( (LA81_0==RULE_REAL_TYPE_NAME) ) {
                alt81=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // InternalPoST.g:6346:3: this_IntegerTypeName_0= ruleIntegerTypeName
                    {

                    			newCompositeNode(grammarAccess.getNumericTypeNameAccess().getIntegerTypeNameParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IntegerTypeName_0=ruleIntegerTypeName();

                    state._fsp--;


                    			current.merge(this_IntegerTypeName_0);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:6357:3: this_REAL_TYPE_NAME_1= RULE_REAL_TYPE_NAME
                    {
                    this_REAL_TYPE_NAME_1=(Token)match(input,RULE_REAL_TYPE_NAME,FOLLOW_2); 

                    			current.merge(this_REAL_TYPE_NAME_1);
                    		

                    			newLeafNode(this_REAL_TYPE_NAME_1, grammarAccess.getNumericTypeNameAccess().getREAL_TYPE_NAMETerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNumericTypeName"


    // $ANTLR start "entryRuleIntegerTypeName"
    // InternalPoST.g:6368:1: entryRuleIntegerTypeName returns [String current=null] : iv_ruleIntegerTypeName= ruleIntegerTypeName EOF ;
    public final String entryRuleIntegerTypeName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIntegerTypeName = null;


        try {
            // InternalPoST.g:6368:55: (iv_ruleIntegerTypeName= ruleIntegerTypeName EOF )
            // InternalPoST.g:6369:2: iv_ruleIntegerTypeName= ruleIntegerTypeName EOF
            {
             newCompositeNode(grammarAccess.getIntegerTypeNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntegerTypeName=ruleIntegerTypeName();

            state._fsp--;

             current =iv_ruleIntegerTypeName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntegerTypeName"


    // $ANTLR start "ruleIntegerTypeName"
    // InternalPoST.g:6375:1: ruleIntegerTypeName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIGNED_INTEGER_TYPE_NAME_0= RULE_SIGNED_INTEGER_TYPE_NAME | this_UNSIGNED_INTEGER_TYPE_NAME_1= RULE_UNSIGNED_INTEGER_TYPE_NAME ) ;
    public final AntlrDatatypeRuleToken ruleIntegerTypeName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIGNED_INTEGER_TYPE_NAME_0=null;
        Token this_UNSIGNED_INTEGER_TYPE_NAME_1=null;


        	enterRule();

        try {
            // InternalPoST.g:6381:2: ( (this_SIGNED_INTEGER_TYPE_NAME_0= RULE_SIGNED_INTEGER_TYPE_NAME | this_UNSIGNED_INTEGER_TYPE_NAME_1= RULE_UNSIGNED_INTEGER_TYPE_NAME ) )
            // InternalPoST.g:6382:2: (this_SIGNED_INTEGER_TYPE_NAME_0= RULE_SIGNED_INTEGER_TYPE_NAME | this_UNSIGNED_INTEGER_TYPE_NAME_1= RULE_UNSIGNED_INTEGER_TYPE_NAME )
            {
            // InternalPoST.g:6382:2: (this_SIGNED_INTEGER_TYPE_NAME_0= RULE_SIGNED_INTEGER_TYPE_NAME | this_UNSIGNED_INTEGER_TYPE_NAME_1= RULE_UNSIGNED_INTEGER_TYPE_NAME )
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==RULE_SIGNED_INTEGER_TYPE_NAME) ) {
                alt82=1;
            }
            else if ( (LA82_0==RULE_UNSIGNED_INTEGER_TYPE_NAME) ) {
                alt82=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }
            switch (alt82) {
                case 1 :
                    // InternalPoST.g:6383:3: this_SIGNED_INTEGER_TYPE_NAME_0= RULE_SIGNED_INTEGER_TYPE_NAME
                    {
                    this_SIGNED_INTEGER_TYPE_NAME_0=(Token)match(input,RULE_SIGNED_INTEGER_TYPE_NAME,FOLLOW_2); 

                    			current.merge(this_SIGNED_INTEGER_TYPE_NAME_0);
                    		

                    			newLeafNode(this_SIGNED_INTEGER_TYPE_NAME_0, grammarAccess.getIntegerTypeNameAccess().getSIGNED_INTEGER_TYPE_NAMETerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:6391:3: this_UNSIGNED_INTEGER_TYPE_NAME_1= RULE_UNSIGNED_INTEGER_TYPE_NAME
                    {
                    this_UNSIGNED_INTEGER_TYPE_NAME_1=(Token)match(input,RULE_UNSIGNED_INTEGER_TYPE_NAME,FOLLOW_2); 

                    			current.merge(this_UNSIGNED_INTEGER_TYPE_NAME_1);
                    		

                    			newLeafNode(this_UNSIGNED_INTEGER_TYPE_NAME_1, grammarAccess.getIntegerTypeNameAccess().getUNSIGNED_INTEGER_TYPE_NAMETerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntegerTypeName"


    // $ANTLR start "entryRuleConstant"
    // InternalPoST.g:6402:1: entryRuleConstant returns [EObject current=null] : iv_ruleConstant= ruleConstant EOF ;
    public final EObject entryRuleConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstant = null;


        try {
            // InternalPoST.g:6402:49: (iv_ruleConstant= ruleConstant EOF )
            // InternalPoST.g:6403:2: iv_ruleConstant= ruleConstant EOF
            {
             newCompositeNode(grammarAccess.getConstantRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstant=ruleConstant();

            state._fsp--;

             current =iv_ruleConstant; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstant"


    // $ANTLR start "ruleConstant"
    // InternalPoST.g:6409:1: ruleConstant returns [EObject current=null] : ( ( (lv_num_0_0= ruleNumericLiteral ) ) | ( (lv_time_1_0= ruleTimeLiteral ) ) | ( (lv_str_2_0= RULE_STRING_LITERAL ) ) | ( ( (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL ) ) ) ) ;
    public final EObject ruleConstant() throws RecognitionException {
        EObject current = null;

        Token lv_str_2_0=null;
        Token lv_oth_3_1=null;
        Token lv_oth_3_2=null;
        Token lv_oth_3_3=null;
        Token lv_oth_3_4=null;
        EObject lv_num_0_0 = null;

        EObject lv_time_1_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:6415:2: ( ( ( (lv_num_0_0= ruleNumericLiteral ) ) | ( (lv_time_1_0= ruleTimeLiteral ) ) | ( (lv_str_2_0= RULE_STRING_LITERAL ) ) | ( ( (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL ) ) ) ) )
            // InternalPoST.g:6416:2: ( ( (lv_num_0_0= ruleNumericLiteral ) ) | ( (lv_time_1_0= ruleTimeLiteral ) ) | ( (lv_str_2_0= RULE_STRING_LITERAL ) ) | ( ( (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL ) ) ) )
            {
            // InternalPoST.g:6416:2: ( ( (lv_num_0_0= ruleNumericLiteral ) ) | ( (lv_time_1_0= ruleTimeLiteral ) ) | ( (lv_str_2_0= RULE_STRING_LITERAL ) ) | ( ( (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL ) ) ) )
            int alt84=4;
            switch ( input.LA(1) ) {
            case RULE_INTEGER:
            case RULE_REAL_TYPE_NAME:
            case RULE_SIGNED_INTEGER_TYPE_NAME:
            case RULE_UNSIGNED_INTEGER_TYPE_NAME:
            case RULE_REAL:
            case 114:
                {
                alt84=1;
                }
                break;
            case RULE_TIME_PREF_LITERAL:
                {
                alt84=2;
                }
                break;
            case RULE_STRING_LITERAL:
                {
                alt84=3;
                }
                break;
            case RULE_BINARY_INTEGER:
            case RULE_OCTAL_INTEGER:
            case RULE_HEX_INTEGER:
            case RULE_BOOLEAN_LITERAL:
                {
                alt84=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }

            switch (alt84) {
                case 1 :
                    // InternalPoST.g:6417:3: ( (lv_num_0_0= ruleNumericLiteral ) )
                    {
                    // InternalPoST.g:6417:3: ( (lv_num_0_0= ruleNumericLiteral ) )
                    // InternalPoST.g:6418:4: (lv_num_0_0= ruleNumericLiteral )
                    {
                    // InternalPoST.g:6418:4: (lv_num_0_0= ruleNumericLiteral )
                    // InternalPoST.g:6419:5: lv_num_0_0= ruleNumericLiteral
                    {

                    					newCompositeNode(grammarAccess.getConstantAccess().getNumNumericLiteralParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_num_0_0=ruleNumericLiteral();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConstantRule());
                    					}
                    					set(
                    						current,
                    						"num",
                    						lv_num_0_0,
                    						"su.nsk.iae.post.PoST.NumericLiteral");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:6437:3: ( (lv_time_1_0= ruleTimeLiteral ) )
                    {
                    // InternalPoST.g:6437:3: ( (lv_time_1_0= ruleTimeLiteral ) )
                    // InternalPoST.g:6438:4: (lv_time_1_0= ruleTimeLiteral )
                    {
                    // InternalPoST.g:6438:4: (lv_time_1_0= ruleTimeLiteral )
                    // InternalPoST.g:6439:5: lv_time_1_0= ruleTimeLiteral
                    {

                    					newCompositeNode(grammarAccess.getConstantAccess().getTimeTimeLiteralParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_time_1_0=ruleTimeLiteral();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConstantRule());
                    					}
                    					set(
                    						current,
                    						"time",
                    						lv_time_1_0,
                    						"su.nsk.iae.post.PoST.TimeLiteral");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalPoST.g:6457:3: ( (lv_str_2_0= RULE_STRING_LITERAL ) )
                    {
                    // InternalPoST.g:6457:3: ( (lv_str_2_0= RULE_STRING_LITERAL ) )
                    // InternalPoST.g:6458:4: (lv_str_2_0= RULE_STRING_LITERAL )
                    {
                    // InternalPoST.g:6458:4: (lv_str_2_0= RULE_STRING_LITERAL )
                    // InternalPoST.g:6459:5: lv_str_2_0= RULE_STRING_LITERAL
                    {
                    lv_str_2_0=(Token)match(input,RULE_STRING_LITERAL,FOLLOW_2); 

                    					newLeafNode(lv_str_2_0, grammarAccess.getConstantAccess().getStrSTRING_LITERALTerminalRuleCall_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getConstantRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"str",
                    						lv_str_2_0,
                    						"su.nsk.iae.post.PoST.STRING_LITERAL");
                    				

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalPoST.g:6476:3: ( ( (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL ) ) )
                    {
                    // InternalPoST.g:6476:3: ( ( (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL ) ) )
                    // InternalPoST.g:6477:4: ( (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL ) )
                    {
                    // InternalPoST.g:6477:4: ( (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL ) )
                    // InternalPoST.g:6478:5: (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL )
                    {
                    // InternalPoST.g:6478:5: (lv_oth_3_1= RULE_BINARY_INTEGER | lv_oth_3_2= RULE_OCTAL_INTEGER | lv_oth_3_3= RULE_HEX_INTEGER | lv_oth_3_4= RULE_BOOLEAN_LITERAL )
                    int alt83=4;
                    switch ( input.LA(1) ) {
                    case RULE_BINARY_INTEGER:
                        {
                        alt83=1;
                        }
                        break;
                    case RULE_OCTAL_INTEGER:
                        {
                        alt83=2;
                        }
                        break;
                    case RULE_HEX_INTEGER:
                        {
                        alt83=3;
                        }
                        break;
                    case RULE_BOOLEAN_LITERAL:
                        {
                        alt83=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 83, 0, input);

                        throw nvae;
                    }

                    switch (alt83) {
                        case 1 :
                            // InternalPoST.g:6479:6: lv_oth_3_1= RULE_BINARY_INTEGER
                            {
                            lv_oth_3_1=(Token)match(input,RULE_BINARY_INTEGER,FOLLOW_2); 

                            						newLeafNode(lv_oth_3_1, grammarAccess.getConstantAccess().getOthBINARY_INTEGERTerminalRuleCall_3_0_0());
                            					

                            						if (current==null) {
                            							current = createModelElement(grammarAccess.getConstantRule());
                            						}
                            						setWithLastConsumed(
                            							current,
                            							"oth",
                            							lv_oth_3_1,
                            							"su.nsk.iae.post.PoST.BINARY_INTEGER");
                            					

                            }
                            break;
                        case 2 :
                            // InternalPoST.g:6494:6: lv_oth_3_2= RULE_OCTAL_INTEGER
                            {
                            lv_oth_3_2=(Token)match(input,RULE_OCTAL_INTEGER,FOLLOW_2); 

                            						newLeafNode(lv_oth_3_2, grammarAccess.getConstantAccess().getOthOCTAL_INTEGERTerminalRuleCall_3_0_1());
                            					

                            						if (current==null) {
                            							current = createModelElement(grammarAccess.getConstantRule());
                            						}
                            						setWithLastConsumed(
                            							current,
                            							"oth",
                            							lv_oth_3_2,
                            							"su.nsk.iae.post.PoST.OCTAL_INTEGER");
                            					

                            }
                            break;
                        case 3 :
                            // InternalPoST.g:6509:6: lv_oth_3_3= RULE_HEX_INTEGER
                            {
                            lv_oth_3_3=(Token)match(input,RULE_HEX_INTEGER,FOLLOW_2); 

                            						newLeafNode(lv_oth_3_3, grammarAccess.getConstantAccess().getOthHEX_INTEGERTerminalRuleCall_3_0_2());
                            					

                            						if (current==null) {
                            							current = createModelElement(grammarAccess.getConstantRule());
                            						}
                            						setWithLastConsumed(
                            							current,
                            							"oth",
                            							lv_oth_3_3,
                            							"su.nsk.iae.post.PoST.HEX_INTEGER");
                            					

                            }
                            break;
                        case 4 :
                            // InternalPoST.g:6524:6: lv_oth_3_4= RULE_BOOLEAN_LITERAL
                            {
                            lv_oth_3_4=(Token)match(input,RULE_BOOLEAN_LITERAL,FOLLOW_2); 

                            						newLeafNode(lv_oth_3_4, grammarAccess.getConstantAccess().getOthBOOLEAN_LITERALTerminalRuleCall_3_0_3());
                            					

                            						if (current==null) {
                            							current = createModelElement(grammarAccess.getConstantRule());
                            						}
                            						setWithLastConsumed(
                            							current,
                            							"oth",
                            							lv_oth_3_4,
                            							"su.nsk.iae.post.PoST.BOOLEAN_LITERAL");
                            					

                            }
                            break;

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstant"


    // $ANTLR start "entryRuleSignedInteger"
    // InternalPoST.g:6545:1: entryRuleSignedInteger returns [EObject current=null] : iv_ruleSignedInteger= ruleSignedInteger EOF ;
    public final EObject entryRuleSignedInteger() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignedInteger = null;


        try {
            // InternalPoST.g:6545:54: (iv_ruleSignedInteger= ruleSignedInteger EOF )
            // InternalPoST.g:6546:2: iv_ruleSignedInteger= ruleSignedInteger EOF
            {
             newCompositeNode(grammarAccess.getSignedIntegerRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSignedInteger=ruleSignedInteger();

            state._fsp--;

             current =iv_ruleSignedInteger; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSignedInteger"


    // $ANTLR start "ruleSignedInteger"
    // InternalPoST.g:6552:1: ruleSignedInteger returns [EObject current=null] : ( ( (lv_iSig_0_0= '-' ) )? ( (lv_value_1_0= RULE_INTEGER ) ) ) ;
    public final EObject ruleSignedInteger() throws RecognitionException {
        EObject current = null;

        Token lv_iSig_0_0=null;
        Token lv_value_1_0=null;


        	enterRule();

        try {
            // InternalPoST.g:6558:2: ( ( ( (lv_iSig_0_0= '-' ) )? ( (lv_value_1_0= RULE_INTEGER ) ) ) )
            // InternalPoST.g:6559:2: ( ( (lv_iSig_0_0= '-' ) )? ( (lv_value_1_0= RULE_INTEGER ) ) )
            {
            // InternalPoST.g:6559:2: ( ( (lv_iSig_0_0= '-' ) )? ( (lv_value_1_0= RULE_INTEGER ) ) )
            // InternalPoST.g:6560:3: ( (lv_iSig_0_0= '-' ) )? ( (lv_value_1_0= RULE_INTEGER ) )
            {
            // InternalPoST.g:6560:3: ( (lv_iSig_0_0= '-' ) )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==114) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalPoST.g:6561:4: (lv_iSig_0_0= '-' )
                    {
                    // InternalPoST.g:6561:4: (lv_iSig_0_0= '-' )
                    // InternalPoST.g:6562:5: lv_iSig_0_0= '-'
                    {
                    lv_iSig_0_0=(Token)match(input,114,FOLLOW_18); 

                    					newLeafNode(lv_iSig_0_0, grammarAccess.getSignedIntegerAccess().getISigHyphenMinusKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getSignedIntegerRule());
                    					}
                    					setWithLastConsumed(current, "iSig", lv_iSig_0_0 != null, "-");
                    				

                    }


                    }
                    break;

            }

            // InternalPoST.g:6574:3: ( (lv_value_1_0= RULE_INTEGER ) )
            // InternalPoST.g:6575:4: (lv_value_1_0= RULE_INTEGER )
            {
            // InternalPoST.g:6575:4: (lv_value_1_0= RULE_INTEGER )
            // InternalPoST.g:6576:5: lv_value_1_0= RULE_INTEGER
            {
            lv_value_1_0=(Token)match(input,RULE_INTEGER,FOLLOW_2); 

            					newLeafNode(lv_value_1_0, grammarAccess.getSignedIntegerAccess().getValueINTEGERTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSignedIntegerRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_1_0,
            						"su.nsk.iae.post.PoST.INTEGER");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSignedInteger"


    // $ANTLR start "entryRuleNumericLiteral"
    // InternalPoST.g:6596:1: entryRuleNumericLiteral returns [EObject current=null] : iv_ruleNumericLiteral= ruleNumericLiteral EOF ;
    public final EObject entryRuleNumericLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumericLiteral = null;


        try {
            // InternalPoST.g:6596:55: (iv_ruleNumericLiteral= ruleNumericLiteral EOF )
            // InternalPoST.g:6597:2: iv_ruleNumericLiteral= ruleNumericLiteral EOF
            {
             newCompositeNode(grammarAccess.getNumericLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumericLiteral=ruleNumericLiteral();

            state._fsp--;

             current =iv_ruleNumericLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNumericLiteral"


    // $ANTLR start "ruleNumericLiteral"
    // InternalPoST.g:6603:1: ruleNumericLiteral returns [EObject current=null] : (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral ) ;
    public final EObject ruleNumericLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_IntegerLiteral_0 = null;

        EObject this_RealLiteral_1 = null;



        	enterRule();

        try {
            // InternalPoST.g:6609:2: ( (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral ) )
            // InternalPoST.g:6610:2: (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral )
            {
            // InternalPoST.g:6610:2: (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral )
            int alt86=2;
            switch ( input.LA(1) ) {
            case RULE_INTEGER:
            case RULE_SIGNED_INTEGER_TYPE_NAME:
            case RULE_UNSIGNED_INTEGER_TYPE_NAME:
                {
                alt86=1;
                }
                break;
            case 114:
                {
                int LA86_2 = input.LA(2);

                if ( (LA86_2==RULE_REAL) ) {
                    alt86=2;
                }
                else if ( (LA86_2==RULE_INTEGER) ) {
                    alt86=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 86, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_REAL_TYPE_NAME:
            case RULE_REAL:
                {
                alt86=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }

            switch (alt86) {
                case 1 :
                    // InternalPoST.g:6611:3: this_IntegerLiteral_0= ruleIntegerLiteral
                    {

                    			newCompositeNode(grammarAccess.getNumericLiteralAccess().getIntegerLiteralParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IntegerLiteral_0=ruleIntegerLiteral();

                    state._fsp--;


                    			current = this_IntegerLiteral_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalPoST.g:6620:3: this_RealLiteral_1= ruleRealLiteral
                    {

                    			newCompositeNode(grammarAccess.getNumericLiteralAccess().getRealLiteralParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_RealLiteral_1=ruleRealLiteral();

                    state._fsp--;


                    			current = this_RealLiteral_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNumericLiteral"


    // $ANTLR start "entryRuleIntegerLiteral"
    // InternalPoST.g:6632:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // InternalPoST.g:6632:55: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // InternalPoST.g:6633:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
             newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;

             current =iv_ruleIntegerLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntegerLiteral"


    // $ANTLR start "ruleIntegerLiteral"
    // InternalPoST.g:6639:1: ruleIntegerLiteral returns [EObject current=null] : ( ( ( (lv_type_0_0= ruleIntegerTypeName ) ) otherlv_1= '#' )? ( (lv_value_2_0= ruleSignedInteger ) ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_type_0_0 = null;

        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalPoST.g:6645:2: ( ( ( ( (lv_type_0_0= ruleIntegerTypeName ) ) otherlv_1= '#' )? ( (lv_value_2_0= ruleSignedInteger ) ) ) )
            // InternalPoST.g:6646:2: ( ( ( (lv_type_0_0= ruleIntegerTypeName ) ) otherlv_1= '#' )? ( (lv_value_2_0= ruleSignedInteger ) ) )
            {
            // InternalPoST.g:6646:2: ( ( ( (lv_type_0_0= ruleIntegerTypeName ) ) otherlv_1= '#' )? ( (lv_value_2_0= ruleSignedInteger ) ) )
            // InternalPoST.g:6647:3: ( ( (lv_type_0_0= ruleIntegerTypeName ) ) otherlv_1= '#' )? ( (lv_value_2_0= ruleSignedInteger ) )
            {
            // InternalPoST.g:6647:3: ( ( (lv_type_0_0= ruleIntegerTypeName ) ) otherlv_1= '#' )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( ((LA87_0>=RULE_SIGNED_INTEGER_TYPE_NAME && LA87_0<=RULE_UNSIGNED_INTEGER_TYPE_NAME)) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalPoST.g:6648:4: ( (lv_type_0_0= ruleIntegerTypeName ) ) otherlv_1= '#'
                    {
                    // InternalPoST.g:6648:4: ( (lv_type_0_0= ruleIntegerTypeName ) )
                    // InternalPoST.g:6649:5: (lv_type_0_0= ruleIntegerTypeName )
                    {
                    // InternalPoST.g:6649:5: (lv_type_0_0= ruleIntegerTypeName )
                    // InternalPoST.g:6650:6: lv_type_0_0= ruleIntegerTypeName
                    {

                    						newCompositeNode(grammarAccess.getIntegerLiteralAccess().getTypeIntegerTypeNameParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_96);
                    lv_type_0_0=ruleIntegerTypeName();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getIntegerLiteralRule());
                    						}
                    						set(
                    							current,
                    							"type",
                    							lv_type_0_0,
                    							"su.nsk.iae.post.PoST.IntegerTypeName");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_1=(Token)match(input,115,FOLLOW_97); 

                    				newLeafNode(otherlv_1, grammarAccess.getIntegerLiteralAccess().getNumberSignKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalPoST.g:6672:3: ( (lv_value_2_0= ruleSignedInteger ) )
            // InternalPoST.g:6673:4: (lv_value_2_0= ruleSignedInteger )
            {
            // InternalPoST.g:6673:4: (lv_value_2_0= ruleSignedInteger )
            // InternalPoST.g:6674:5: lv_value_2_0= ruleSignedInteger
            {

            					newCompositeNode(grammarAccess.getIntegerLiteralAccess().getValueSignedIntegerParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleSignedInteger();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIntegerLiteralRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"su.nsk.iae.post.PoST.SignedInteger");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntegerLiteral"


    // $ANTLR start "entryRuleRealLiteral"
    // InternalPoST.g:6695:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // InternalPoST.g:6695:52: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // InternalPoST.g:6696:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
             newCompositeNode(grammarAccess.getRealLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;

             current =iv_ruleRealLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRealLiteral"


    // $ANTLR start "ruleRealLiteral"
    // InternalPoST.g:6702:1: ruleRealLiteral returns [EObject current=null] : ( ( ( (lv_type_0_0= RULE_REAL_TYPE_NAME ) ) otherlv_1= '#' )? ( (lv_rSig_2_0= '-' ) )? ( (lv_value_3_0= RULE_REAL ) ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_type_0_0=null;
        Token otherlv_1=null;
        Token lv_rSig_2_0=null;
        Token lv_value_3_0=null;


        	enterRule();

        try {
            // InternalPoST.g:6708:2: ( ( ( ( (lv_type_0_0= RULE_REAL_TYPE_NAME ) ) otherlv_1= '#' )? ( (lv_rSig_2_0= '-' ) )? ( (lv_value_3_0= RULE_REAL ) ) ) )
            // InternalPoST.g:6709:2: ( ( ( (lv_type_0_0= RULE_REAL_TYPE_NAME ) ) otherlv_1= '#' )? ( (lv_rSig_2_0= '-' ) )? ( (lv_value_3_0= RULE_REAL ) ) )
            {
            // InternalPoST.g:6709:2: ( ( ( (lv_type_0_0= RULE_REAL_TYPE_NAME ) ) otherlv_1= '#' )? ( (lv_rSig_2_0= '-' ) )? ( (lv_value_3_0= RULE_REAL ) ) )
            // InternalPoST.g:6710:3: ( ( (lv_type_0_0= RULE_REAL_TYPE_NAME ) ) otherlv_1= '#' )? ( (lv_rSig_2_0= '-' ) )? ( (lv_value_3_0= RULE_REAL ) )
            {
            // InternalPoST.g:6710:3: ( ( (lv_type_0_0= RULE_REAL_TYPE_NAME ) ) otherlv_1= '#' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==RULE_REAL_TYPE_NAME) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalPoST.g:6711:4: ( (lv_type_0_0= RULE_REAL_TYPE_NAME ) ) otherlv_1= '#'
                    {
                    // InternalPoST.g:6711:4: ( (lv_type_0_0= RULE_REAL_TYPE_NAME ) )
                    // InternalPoST.g:6712:5: (lv_type_0_0= RULE_REAL_TYPE_NAME )
                    {
                    // InternalPoST.g:6712:5: (lv_type_0_0= RULE_REAL_TYPE_NAME )
                    // InternalPoST.g:6713:6: lv_type_0_0= RULE_REAL_TYPE_NAME
                    {
                    lv_type_0_0=(Token)match(input,RULE_REAL_TYPE_NAME,FOLLOW_96); 

                    						newLeafNode(lv_type_0_0, grammarAccess.getRealLiteralAccess().getTypeREAL_TYPE_NAMETerminalRuleCall_0_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getRealLiteralRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"type",
                    							lv_type_0_0,
                    							"su.nsk.iae.post.PoST.REAL_TYPE_NAME");
                    					

                    }


                    }

                    otherlv_1=(Token)match(input,115,FOLLOW_98); 

                    				newLeafNode(otherlv_1, grammarAccess.getRealLiteralAccess().getNumberSignKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalPoST.g:6734:3: ( (lv_rSig_2_0= '-' ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==114) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // InternalPoST.g:6735:4: (lv_rSig_2_0= '-' )
                    {
                    // InternalPoST.g:6735:4: (lv_rSig_2_0= '-' )
                    // InternalPoST.g:6736:5: lv_rSig_2_0= '-'
                    {
                    lv_rSig_2_0=(Token)match(input,114,FOLLOW_99); 

                    					newLeafNode(lv_rSig_2_0, grammarAccess.getRealLiteralAccess().getRSigHyphenMinusKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getRealLiteralRule());
                    					}
                    					setWithLastConsumed(current, "rSig", lv_rSig_2_0 != null, "-");
                    				

                    }


                    }
                    break;

            }

            // InternalPoST.g:6748:3: ( (lv_value_3_0= RULE_REAL ) )
            // InternalPoST.g:6749:4: (lv_value_3_0= RULE_REAL )
            {
            // InternalPoST.g:6749:4: (lv_value_3_0= RULE_REAL )
            // InternalPoST.g:6750:5: lv_value_3_0= RULE_REAL
            {
            lv_value_3_0=(Token)match(input,RULE_REAL,FOLLOW_2); 

            					newLeafNode(lv_value_3_0, grammarAccess.getRealLiteralAccess().getValueREALTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRealLiteralRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_3_0,
            						"su.nsk.iae.post.PoST.REAL");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRealLiteral"


    // $ANTLR start "ruleAssignmentType"
    // InternalPoST.g:6770:1: ruleAssignmentType returns [Enumerator current=null] : ( (enumLiteral_0= ':=' ) | (enumLiteral_1= '=>' ) ) ;
    public final Enumerator ruleAssignmentType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalPoST.g:6776:2: ( ( (enumLiteral_0= ':=' ) | (enumLiteral_1= '=>' ) ) )
            // InternalPoST.g:6777:2: ( (enumLiteral_0= ':=' ) | (enumLiteral_1= '=>' ) )
            {
            // InternalPoST.g:6777:2: ( (enumLiteral_0= ':=' ) | (enumLiteral_1= '=>' ) )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==49) ) {
                alt90=1;
            }
            else if ( (LA90_0==116) ) {
                alt90=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;
            }
            switch (alt90) {
                case 1 :
                    // InternalPoST.g:6778:3: (enumLiteral_0= ':=' )
                    {
                    // InternalPoST.g:6778:3: (enumLiteral_0= ':=' )
                    // InternalPoST.g:6779:4: enumLiteral_0= ':='
                    {
                    enumLiteral_0=(Token)match(input,49,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getInEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getAssignmentTypeAccess().getInEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:6786:3: (enumLiteral_1= '=>' )
                    {
                    // InternalPoST.g:6786:3: (enumLiteral_1= '=>' )
                    // InternalPoST.g:6787:4: enumLiteral_1= '=>'
                    {
                    enumLiteral_1=(Token)match(input,116,FOLLOW_2); 

                    				current = grammarAccess.getAssignmentTypeAccess().getOutEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getAssignmentTypeAccess().getOutEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignmentType"


    // $ANTLR start "ruleCompOperator"
    // InternalPoST.g:6797:1: ruleCompOperator returns [Enumerator current=null] : ( (enumLiteral_0= '=' ) | (enumLiteral_1= '<>' ) ) ;
    public final Enumerator ruleCompOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalPoST.g:6803:2: ( ( (enumLiteral_0= '=' ) | (enumLiteral_1= '<>' ) ) )
            // InternalPoST.g:6804:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '<>' ) )
            {
            // InternalPoST.g:6804:2: ( (enumLiteral_0= '=' ) | (enumLiteral_1= '<>' ) )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==117) ) {
                alt91=1;
            }
            else if ( (LA91_0==118) ) {
                alt91=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // InternalPoST.g:6805:3: (enumLiteral_0= '=' )
                    {
                    // InternalPoST.g:6805:3: (enumLiteral_0= '=' )
                    // InternalPoST.g:6806:4: enumLiteral_0= '='
                    {
                    enumLiteral_0=(Token)match(input,117,FOLLOW_2); 

                    				current = grammarAccess.getCompOperatorAccess().getEQUALEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getCompOperatorAccess().getEQUALEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:6813:3: (enumLiteral_1= '<>' )
                    {
                    // InternalPoST.g:6813:3: (enumLiteral_1= '<>' )
                    // InternalPoST.g:6814:4: enumLiteral_1= '<>'
                    {
                    enumLiteral_1=(Token)match(input,118,FOLLOW_2); 

                    				current = grammarAccess.getCompOperatorAccess().getNOT_EQUALEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getCompOperatorAccess().getNOT_EQUALEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCompOperator"


    // $ANTLR start "ruleEquOperator"
    // InternalPoST.g:6824:1: ruleEquOperator returns [Enumerator current=null] : ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) ) ;
    public final Enumerator ruleEquOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;


        	enterRule();

        try {
            // InternalPoST.g:6830:2: ( ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) ) )
            // InternalPoST.g:6831:2: ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) )
            {
            // InternalPoST.g:6831:2: ( (enumLiteral_0= '<' ) | (enumLiteral_1= '>' ) | (enumLiteral_2= '<=' ) | (enumLiteral_3= '>=' ) )
            int alt92=4;
            switch ( input.LA(1) ) {
            case 119:
                {
                alt92=1;
                }
                break;
            case 120:
                {
                alt92=2;
                }
                break;
            case 121:
                {
                alt92=3;
                }
                break;
            case 122:
                {
                alt92=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;
            }

            switch (alt92) {
                case 1 :
                    // InternalPoST.g:6832:3: (enumLiteral_0= '<' )
                    {
                    // InternalPoST.g:6832:3: (enumLiteral_0= '<' )
                    // InternalPoST.g:6833:4: enumLiteral_0= '<'
                    {
                    enumLiteral_0=(Token)match(input,119,FOLLOW_2); 

                    				current = grammarAccess.getEquOperatorAccess().getLESSEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getEquOperatorAccess().getLESSEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:6840:3: (enumLiteral_1= '>' )
                    {
                    // InternalPoST.g:6840:3: (enumLiteral_1= '>' )
                    // InternalPoST.g:6841:4: enumLiteral_1= '>'
                    {
                    enumLiteral_1=(Token)match(input,120,FOLLOW_2); 

                    				current = grammarAccess.getEquOperatorAccess().getGREATEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getEquOperatorAccess().getGREATEREnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalPoST.g:6848:3: (enumLiteral_2= '<=' )
                    {
                    // InternalPoST.g:6848:3: (enumLiteral_2= '<=' )
                    // InternalPoST.g:6849:4: enumLiteral_2= '<='
                    {
                    enumLiteral_2=(Token)match(input,121,FOLLOW_2); 

                    				current = grammarAccess.getEquOperatorAccess().getLESS_EQUEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getEquOperatorAccess().getLESS_EQUEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalPoST.g:6856:3: (enumLiteral_3= '>=' )
                    {
                    // InternalPoST.g:6856:3: (enumLiteral_3= '>=' )
                    // InternalPoST.g:6857:4: enumLiteral_3= '>='
                    {
                    enumLiteral_3=(Token)match(input,122,FOLLOW_2); 

                    				current = grammarAccess.getEquOperatorAccess().getGREATER_EQUEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getEquOperatorAccess().getGREATER_EQUEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEquOperator"


    // $ANTLR start "ruleAddOperator"
    // InternalPoST.g:6867:1: ruleAddOperator returns [Enumerator current=null] : ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) ) ;
    public final Enumerator ruleAddOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalPoST.g:6873:2: ( ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) ) )
            // InternalPoST.g:6874:2: ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) )
            {
            // InternalPoST.g:6874:2: ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) )
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==123) ) {
                alt93=1;
            }
            else if ( (LA93_0==114) ) {
                alt93=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }
            switch (alt93) {
                case 1 :
                    // InternalPoST.g:6875:3: (enumLiteral_0= '+' )
                    {
                    // InternalPoST.g:6875:3: (enumLiteral_0= '+' )
                    // InternalPoST.g:6876:4: enumLiteral_0= '+'
                    {
                    enumLiteral_0=(Token)match(input,123,FOLLOW_2); 

                    				current = grammarAccess.getAddOperatorAccess().getPLUSEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getAddOperatorAccess().getPLUSEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:6883:3: (enumLiteral_1= '-' )
                    {
                    // InternalPoST.g:6883:3: (enumLiteral_1= '-' )
                    // InternalPoST.g:6884:4: enumLiteral_1= '-'
                    {
                    enumLiteral_1=(Token)match(input,114,FOLLOW_2); 

                    				current = grammarAccess.getAddOperatorAccess().getMINUSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getAddOperatorAccess().getMINUSEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAddOperator"


    // $ANTLR start "ruleMulOperator"
    // InternalPoST.g:6894:1: ruleMulOperator returns [Enumerator current=null] : ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) | (enumLiteral_2= 'MOD' ) ) ;
    public final Enumerator ruleMulOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalPoST.g:6900:2: ( ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) | (enumLiteral_2= 'MOD' ) ) )
            // InternalPoST.g:6901:2: ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) | (enumLiteral_2= 'MOD' ) )
            {
            // InternalPoST.g:6901:2: ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) | (enumLiteral_2= 'MOD' ) )
            int alt94=3;
            switch ( input.LA(1) ) {
            case 112:
                {
                alt94=1;
                }
                break;
            case 124:
                {
                alt94=2;
                }
                break;
            case 125:
                {
                alt94=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }

            switch (alt94) {
                case 1 :
                    // InternalPoST.g:6902:3: (enumLiteral_0= '*' )
                    {
                    // InternalPoST.g:6902:3: (enumLiteral_0= '*' )
                    // InternalPoST.g:6903:4: enumLiteral_0= '*'
                    {
                    enumLiteral_0=(Token)match(input,112,FOLLOW_2); 

                    				current = grammarAccess.getMulOperatorAccess().getMULEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getMulOperatorAccess().getMULEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:6910:3: (enumLiteral_1= '/' )
                    {
                    // InternalPoST.g:6910:3: (enumLiteral_1= '/' )
                    // InternalPoST.g:6911:4: enumLiteral_1= '/'
                    {
                    enumLiteral_1=(Token)match(input,124,FOLLOW_2); 

                    				current = grammarAccess.getMulOperatorAccess().getDIVEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getMulOperatorAccess().getDIVEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalPoST.g:6918:3: (enumLiteral_2= 'MOD' )
                    {
                    // InternalPoST.g:6918:3: (enumLiteral_2= 'MOD' )
                    // InternalPoST.g:6919:4: enumLiteral_2= 'MOD'
                    {
                    enumLiteral_2=(Token)match(input,125,FOLLOW_2); 

                    				current = grammarAccess.getMulOperatorAccess().getMODEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getMulOperatorAccess().getMODEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMulOperator"


    // $ANTLR start "ruleUnaryOperator"
    // InternalPoST.g:6929:1: ruleUnaryOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'NOT' ) | (enumLiteral_1= '-' ) ) ;
    public final Enumerator ruleUnaryOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalPoST.g:6935:2: ( ( (enumLiteral_0= 'NOT' ) | (enumLiteral_1= '-' ) ) )
            // InternalPoST.g:6936:2: ( (enumLiteral_0= 'NOT' ) | (enumLiteral_1= '-' ) )
            {
            // InternalPoST.g:6936:2: ( (enumLiteral_0= 'NOT' ) | (enumLiteral_1= '-' ) )
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==126) ) {
                alt95=1;
            }
            else if ( (LA95_0==114) ) {
                alt95=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // InternalPoST.g:6937:3: (enumLiteral_0= 'NOT' )
                    {
                    // InternalPoST.g:6937:3: (enumLiteral_0= 'NOT' )
                    // InternalPoST.g:6938:4: enumLiteral_0= 'NOT'
                    {
                    enumLiteral_0=(Token)match(input,126,FOLLOW_2); 

                    				current = grammarAccess.getUnaryOperatorAccess().getNOTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getUnaryOperatorAccess().getNOTEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalPoST.g:6945:3: (enumLiteral_1= '-' )
                    {
                    // InternalPoST.g:6945:3: (enumLiteral_1= '-' )
                    // InternalPoST.g:6946:4: enumLiteral_1= '-'
                    {
                    enumLiteral_1=(Token)match(input,114,FOLLOW_2); 

                    				current = grammarAccess.getUnaryOperatorAccess().getUNMINUSEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getUnaryOperatorAccess().getUNMINUSEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnaryOperator"

    // Delegated rules


    protected DFA49 dfa49 = new DFA49(this);
    protected DFA73 dfa73 = new DFA73(this);
    static final String dfa_1s = "\14\uffff";
    static final String dfa_2s = "\1\4\1\57\12\uffff";
    static final String dfa_3s = "\1\141\1\155\12\uffff";
    static final String dfa_4s = "\2\uffff\1\2\1\3\1\4\1\6\1\7\1\10\1\11\1\12\1\1\1\5";
    static final String dfa_5s = "\14\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\10\uffff\1\4\63\uffff\1\10\3\uffff\4\7\3\uffff\1\11\2\uffff\1\2\3\uffff\1\2\2\uffff\1\3\4\uffff\1\3\1\uffff\1\3\2\uffff\1\5\1\6",
            "\1\13\1\uffff\1\12\73\uffff\1\12",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA49 extends DFA {

        public DFA49(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 49;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "3673:2: ( (this_AssignmentStatement_0= ruleAssignmentStatement otherlv_1= ';' ) | this_SelectionStatement_2= ruleSelectionStatement | this_IterationStatement_3= ruleIterationStatement | this_Inline_code_4= ruleInline_code | (this_FBInvocation_5= ruleFBInvocation otherlv_6= ';' ) | (this_SubprogramControlStatement_7= ruleSubprogramControlStatement otherlv_8= ';' ) | (this_ExitStatement_9= ruleExitStatement otherlv_10= ';' ) | (this_ProcessStatements_11= ruleProcessStatements otherlv_12= ';' ) | (this_SetStateStatement_13= ruleSetStateStatement otherlv_14= ';' ) | (this_ResetTimerStatement_15= ruleResetTimerStatement otherlv_16= ';' ) )";
        }
    }
    static final String dfa_7s = "\7\uffff";
    static final String dfa_8s = "\1\4\1\uffff\1\62\1\4\2\uffff\1\62";
    static final String dfa_9s = "\1\143\1\uffff\1\152\1\4\2\uffff\1\152";
    static final String dfa_10s = "\1\uffff\1\3\2\uffff\1\2\1\1\1\uffff";
    static final String dfa_11s = "\7\uffff}>";
    static final String[] dfa_12s = {
            "\1\2\136\uffff\1\1",
            "",
            "\1\3\2\uffff\1\5\64\uffff\1\4",
            "\1\6",
            "",
            "",
            "\1\3\2\uffff\1\5\64\uffff\1\4"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA73 extends DFA {

        public DFA73(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 73;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "()* loopback of 5624:3: ( ( ( (lv_varsSimple_3_0= ruleVarInitDeclaration ) ) otherlv_4= ';' ) | ( ( (lv_varsAs_5_0= ruleGlobalVarInitDeclaration ) ) otherlv_6= ';' ) )*";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0A08010000002002L,0x0000080000000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000060000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0008500000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0008400000000002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x000000001FF08100L,0x0004000000000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0030000000000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0040000000000010L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0002000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x000000001FF08110L,0x0004000000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0080000000000010L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0140000000000000L,0x0000037400000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0140000000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0440000000000000L,0x0000037400000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0440000000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x00000000007E0000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x1000000000002010L,0x00000077284891E2L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x6000000000000000L,0x0000117400000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x6000000000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x8000000000002010L,0x00000003284893E3L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000002010L,0x00000003284893E3L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000201L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0080000000000000L,0x0000000000000070L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000002010L,0x00000003284899E2L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0001000000000010L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x004080001FF08110L,0x4004000000004000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000002L,0x0060000000000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000000000002L,0x0780000000000000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000002L,0x0804000000000000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000002L,0x3001000000000000L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x004080001FF08110L,0x0004000000004000L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000002012L,0x00000003284891E2L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000002010L,0x00000003284F91E2L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000000000L,0x0000000000070000L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000000002010L,0x00000003284C91E2L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000000000600110L,0x0004000000220000L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000000000002010L,0x00000003286891E2L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000000000002010L,0x00000003284891E2L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000000000600110L,0x0004000000000000L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000000002010L,0x00000003294891E2L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000002010L,0x00000003384891E2L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000002010L,0x00000003684891E2L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x00000000007E0010L,0x0000800000000000L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000000010L,0x0000000800000000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000000000000010L,0x0000008800000000L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0x0000000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0x004080001FF08110L,0x4005000000004000L});
    public static final BitSet FOLLOW_92 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_93 = new BitSet(new long[]{0x0004000000000000L,0x0000400000000000L});
    public static final BitSet FOLLOW_94 = new BitSet(new long[]{0x0000000000010000L,0x0004000000000000L});
    public static final BitSet FOLLOW_95 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_96 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_97 = new BitSet(new long[]{0x0000000000600100L,0x0004000000000000L});
    public static final BitSet FOLLOW_98 = new BitSet(new long[]{0x0000000010000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_99 = new BitSet(new long[]{0x0000000010000000L});

}
