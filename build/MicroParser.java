// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__29=1, T__28=2, T__27=3, T__26=4, T__25=5, T__24=6, T__23=7, T__22=8, 
		T__21=9, T__20=10, T__19=11, T__18=12, T__17=13, T__16=14, T__15=15, T__14=16, 
		T__13=17, T__12=18, T__11=19, T__10=20, T__9=21, T__8=22, T__7=23, T__6=24, 
		T__5=25, T__4=26, T__3=27, T__2=28, T__1=29, T__0=30, PROGRAM=31, IDENTIFIER=32, 
		INTLITERAL=33, STRINGLITERAL=34, FLOATLITERAL=35, COMMENT=36, WHITESPACES=37, 
		OPERATOR=38;
	public static final String[] tokenNames = {
		"<INVALID>", "','", "'ROF'", "'*'", "'-'", "'FI'", "'('", "'<'", "'READ'", 
		"'END'", "'!='", "'<='", "'ELSE'", "'VOID'", "'STRING'", "'IF'", "'FOR'", 
		"'WRITE'", "')'", "'+'", "'='", "';'", "'FUNCTION'", "'>'", "'FLOAT'", 
		"':='", "'RETURN'", "'/'", "'BEGIN'", "'>='", "'INT'", "'PROGRAM'", "IDENTIFIER", 
		"INTLITERAL", "STRINGLITERAL", "FLOATLITERAL", "COMMENT", "WHITESPACES", 
		"OPERATOR"
	};
	public static final int
		RULE_program = 0, RULE_id = 1, RULE_pgm_body = 2, RULE_decl = 3, RULE_string_decl = 4, 
		RULE_str = 5, RULE_var_decl = 6, RULE_any_type = 7, RULE_var_type = 8, 
		RULE_id_list = 9, RULE_id_tail = 10, RULE_param_decl_list = 11, RULE_param_decl = 12, 
		RULE_param_decl_tail = 13, RULE_func_declarations = 14, RULE_func_decl = 15, 
		RULE_func_body = 16, RULE_stmt_list = 17, RULE_stmt = 18, RULE_base_stmt = 19, 
		RULE_assign_stmt = 20, RULE_assign_expr = 21, RULE_read_stmt = 22, RULE_write_stmt = 23, 
		RULE_return_stmt = 24, RULE_expr = 25, RULE_expr_prefix = 26, RULE_factor = 27, 
		RULE_factor_prefix = 28, RULE_postfix_expr = 29, RULE_call_expr = 30, 
		RULE_expr_list = 31, RULE_expr_list_tail = 32, RULE_primary = 33, RULE_addop = 34, 
		RULE_mulop = 35, RULE_if_stmt = 36, RULE_else_part = 37, RULE_cond = 38, 
		RULE_compop = 39, RULE_init_stmt = 40, RULE_incr_stmt = 41, RULE_for_stmt = 42;
	public static final String[] ruleNames = {
		"program", "id", "pgm_body", "decl", "string_decl", "str", "var_decl", 
		"any_type", "var_type", "id_list", "id_tail", "param_decl_list", "param_decl", 
		"param_decl_tail", "func_declarations", "func_decl", "func_body", "stmt_list", 
		"stmt", "base_stmt", "assign_stmt", "assign_expr", "read_stmt", "write_stmt", 
		"return_stmt", "expr", "expr_prefix", "factor", "factor_prefix", "postfix_expr", 
		"call_expr", "expr_list", "expr_list_tail", "primary", "addop", "mulop", 
		"if_stmt", "else_part", "cond", "compop", "init_stmt", "incr_stmt", "for_stmt"
	};

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public MicroParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Pgm_bodyContext pgm_body() {
			return getRuleContext(Pgm_bodyContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); match(PROGRAM);
			setState(87); id();
			setState(88); match(28);
			setState(89); pgm_body();
			SemanticDataHandler.popCurrentScope();
			setState(91); match(9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MicroParser.IDENTIFIER, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pgm_bodyContext extends ParserRuleContext {
		public Func_declarationsContext func_declarations() {
			return getRuleContext(Func_declarationsContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Pgm_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pgm_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPgm_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPgm_body(this);
		}
	}

	public final Pgm_bodyContext pgm_body() throws RecognitionException {
		Pgm_bodyContext _localctx = new Pgm_bodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pgm_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			SemanticDataHandler.pushNewScope(SCOPE.GLOBAL, null);
			setState(96); decl();
			setState(97); func_declarations();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public String_declContext string_decl() {
			return getRuleContext(String_declContext.class,0);
		}
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			setState(106);
			switch (_input.LA(1)) {
			case 14:
				enterOuterAlt(_localctx, 1);
				{
				setState(99); string_decl();
				setState(100); decl();
				}
				break;
			case 24:
			case 30:
				enterOuterAlt(_localctx, 2);
				{
				setState(102); var_decl();
				setState(103); decl();
				}
				break;
			case 2:
			case 5:
			case 8:
			case 9:
			case 12:
			case 15:
			case 16:
			case 17:
			case 22:
			case 26:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_declContext extends ParserRuleContext {
		public IdContext id;
		public StrContext str;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public StrContext str() {
			return getRuleContext(StrContext.class,0);
		}
		public String_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterString_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitString_decl(this);
		}
	}

	public final String_declContext string_decl() throws RecognitionException {
		String_declContext _localctx = new String_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_string_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); match(14);
			setState(109); ((String_declContext)_localctx).id = id();
			setState(110); match(25);
			setState(111); ((String_declContext)_localctx).str = str();
			setState(112); match(21);
			SemanticDataHandler.addScopeRecord((((String_declContext)_localctx).id!=null?_input.getText(((String_declContext)_localctx).id.start,((String_declContext)_localctx).id.stop):null), "STRING");  SemanticDataHandler.setRecordValue((((String_declContext)_localctx).id!=null?_input.getText(((String_declContext)_localctx).id.start,((String_declContext)_localctx).id.stop):null), (((String_declContext)_localctx).str!=null?_input.getText(((String_declContext)_localctx).str.start,((String_declContext)_localctx).str.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(MicroParser.STRINGLITERAL, 0); }
		public StrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStr(this);
		}
	}

	public final StrContext str() throws RecognitionException {
		StrContext _localctx = new StrContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_str);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(STRINGLITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declContext extends ParserRuleContext {
		public Var_typeContext var_type;
		public Id_listContext id_list;
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitVar_decl(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_var_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); ((Var_declContext)_localctx).var_type = var_type();
			setState(118); ((Var_declContext)_localctx).id_list = id_list();
			setState(119); match(21);
			SemanticDataHandler.addScopeRecord((((Var_declContext)_localctx).id_list!=null?_input.getText(((Var_declContext)_localctx).id_list.start,((Var_declContext)_localctx).id_list.stop):null), (((Var_declContext)_localctx).var_type!=null?_input.getText(((Var_declContext)_localctx).var_type.start,((Var_declContext)_localctx).var_type.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Any_typeContext extends ParserRuleContext {
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Any_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAny_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAny_type(this);
		}
	}

	public final Any_typeContext any_type() throws RecognitionException {
		Any_typeContext _localctx = new Any_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_any_type);
		try {
			setState(124);
			switch (_input.LA(1)) {
			case 24:
			case 30:
				enterOuterAlt(_localctx, 1);
				{
				setState(122); var_type();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 2);
				{
				setState(123); match(13);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_typeContext extends ParserRuleContext {
		public Var_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterVar_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitVar_type(this);
		}
	}

	public final Var_typeContext var_type() throws RecognitionException {
		Var_typeContext _localctx = new Var_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_var_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_la = _input.LA(1);
			if ( !(_la==24 || _la==30) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_listContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_tailContext id_tail() {
			return getRuleContext(Id_tailContext.class,0);
		}
		public Id_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId_list(this);
		}
	}

	public final Id_listContext id_list() throws RecognitionException {
		Id_listContext _localctx = new Id_listContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_id_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); id();
			setState(129); id_tail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_tailContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_tailContext id_tail() {
			return getRuleContext(Id_tailContext.class,0);
		}
		public Id_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId_tail(this);
		}
	}

	public final Id_tailContext id_tail() throws RecognitionException {
		Id_tailContext _localctx = new Id_tailContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_id_tail);
		try {
			setState(136);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131); match(1);
				setState(132); id();
				setState(133); id_tail();
				}
				break;
			case 18:
			case 21:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_decl_listContext extends ParserRuleContext {
		public Param_decl_tailContext param_decl_tail() {
			return getRuleContext(Param_decl_tailContext.class,0);
		}
		public Param_declContext param_decl() {
			return getRuleContext(Param_declContext.class,0);
		}
		public Param_decl_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl_list(this);
		}
	}

	public final Param_decl_listContext param_decl_list() throws RecognitionException {
		Param_decl_listContext _localctx = new Param_decl_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_param_decl_list);
		try {
			setState(142);
			switch (_input.LA(1)) {
			case 24:
			case 30:
				enterOuterAlt(_localctx, 1);
				{
				setState(138); param_decl();
				setState(139); param_decl_tail();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_declContext extends ParserRuleContext {
		public Var_typeContext var_type;
		public IdContext id;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Param_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl(this);
		}
	}

	public final Param_declContext param_decl() throws RecognitionException {
		Param_declContext _localctx = new Param_declContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_param_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144); ((Param_declContext)_localctx).var_type = var_type();
			setState(145); ((Param_declContext)_localctx).id = id();
			SemanticDataHandler.addScopeRecord((((Param_declContext)_localctx).id!=null?_input.getText(((Param_declContext)_localctx).id.start,((Param_declContext)_localctx).id.stop):null), (((Param_declContext)_localctx).var_type!=null?_input.getText(((Param_declContext)_localctx).var_type.start,((Param_declContext)_localctx).var_type.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_decl_tailContext extends ParserRuleContext {
		public Param_decl_tailContext param_decl_tail() {
			return getRuleContext(Param_decl_tailContext.class,0);
		}
		public Param_declContext param_decl() {
			return getRuleContext(Param_declContext.class,0);
		}
		public Param_decl_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl_tail(this);
		}
	}

	public final Param_decl_tailContext param_decl_tail() throws RecognitionException {
		Param_decl_tailContext _localctx = new Param_decl_tailContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_param_decl_tail);
		try {
			setState(153);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148); match(1);
				setState(149); param_decl();
				setState(150); param_decl_tail();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_declarationsContext extends ParserRuleContext {
		public Func_declarationsContext func_declarations() {
			return getRuleContext(Func_declarationsContext.class,0);
		}
		public Func_declContext func_decl() {
			return getRuleContext(Func_declContext.class,0);
		}
		public Func_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_declarations(this);
		}
	}

	public final Func_declarationsContext func_declarations() throws RecognitionException {
		Func_declarationsContext _localctx = new Func_declarationsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_func_declarations);
		try {
			setState(159);
			switch (_input.LA(1)) {
			case 22:
				enterOuterAlt(_localctx, 1);
				{
				setState(155); func_decl();
				setState(156); func_declarations();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_declContext extends ParserRuleContext {
		public IdContext id;
		public Func_bodyContext func_body() {
			return getRuleContext(Func_bodyContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Param_decl_listContext param_decl_list() {
			return getRuleContext(Param_decl_listContext.class,0);
		}
		public Any_typeContext any_type() {
			return getRuleContext(Any_typeContext.class,0);
		}
		public Func_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_decl(this);
		}
	}

	public final Func_declContext func_decl() throws RecognitionException {
		Func_declContext _localctx = new Func_declContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_func_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); match(22);
			setState(162); any_type();
			setState(163); ((Func_declContext)_localctx).id = id();
			SemanticDataHandler.pushNewScope(SCOPE.FUNCTION, (((Func_declContext)_localctx).id!=null?_input.getText(((Func_declContext)_localctx).id.start,((Func_declContext)_localctx).id.stop):null));
			setState(165); match(6);
			setState(166); param_decl_list();
			setState(167); match(18);
			setState(168); match(28);
			setState(169); func_body();
			setState(170); match(9);
			SemanticDataHandler.popCurrentScope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_bodyContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Func_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_body(this);
		}
	}

	public final Func_bodyContext func_body() throws RecognitionException {
		Func_bodyContext _localctx = new Func_bodyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_func_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); decl();
			setState(174); stmt_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stmt_listContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public Stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStmt_list(this);
		}
	}

	public final Stmt_listContext stmt_list() throws RecognitionException {
		Stmt_listContext _localctx = new Stmt_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_stmt_list);
		try {
			setState(180);
			switch (_input.LA(1)) {
			case 8:
			case 15:
			case 16:
			case 17:
			case 26:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(176); stmt();
				setState(177); stmt_list();
				}
				break;
			case 2:
			case 5:
			case 9:
			case 12:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Base_stmtContext base_stmt() {
			return getRuleContext(Base_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_stmt);
		try {
			setState(185);
			switch (_input.LA(1)) {
			case 8:
			case 17:
			case 26:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(182); base_stmt();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 2);
				{
				setState(183); if_stmt();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 3);
				{
				setState(184); for_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_stmtContext extends ParserRuleContext {
		public Write_stmtContext write_stmt() {
			return getRuleContext(Write_stmtContext.class,0);
		}
		public Read_stmtContext read_stmt() {
			return getRuleContext(Read_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Assign_stmtContext assign_stmt() {
			return getRuleContext(Assign_stmtContext.class,0);
		}
		public Base_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterBase_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitBase_stmt(this);
		}
	}

	public final Base_stmtContext base_stmt() throws RecognitionException {
		Base_stmtContext _localctx = new Base_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_base_stmt);
		try {
			setState(191);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); assign_stmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); read_stmt();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 3);
				{
				setState(189); write_stmt();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 4);
				{
				setState(190); return_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_stmtContext extends ParserRuleContext {
		public Assign_exprContext assign_expr() {
			return getRuleContext(Assign_exprContext.class,0);
		}
		public Assign_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAssign_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAssign_stmt(this);
		}
	}

	public final Assign_stmtContext assign_stmt() throws RecognitionException {
		Assign_stmtContext _localctx = new Assign_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_assign_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); assign_expr();
			setState(194); match(21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_exprContext extends ParserRuleContext {
		public IdContext id;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Assign_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAssign_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAssign_expr(this);
		}
	}

	public final Assign_exprContext assign_expr() throws RecognitionException {
		Assign_exprContext _localctx = new Assign_exprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_assign_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196); ((Assign_exprContext)_localctx).id = id();
			 ASTStackHandler.addTermNode(ASTNodeType.IDENTIFIER, (((Assign_exprContext)_localctx).id!=null?_input.getText(((Assign_exprContext)_localctx).id.start,((Assign_exprContext)_localctx).id.stop):null));
			setState(198); match(25);
			ASTStackHandler.pushAssignmentTree(SemanticDataHandler.currentScope);
			setState(200); expr();
			ASTStackHandler.updateCurrTree();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Read_stmtContext extends ParserRuleContext {
		public Id_listContext id_list;
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Read_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterRead_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitRead_stmt(this);
		}
	}

	public final Read_stmtContext read_stmt() throws RecognitionException {
		Read_stmtContext _localctx = new Read_stmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_read_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); match(8);
			setState(204); match(6);
			setState(205); ((Read_stmtContext)_localctx).id_list = id_list();
			setState(206); match(18);
			ASTStackHandler.pushIOTree(IO_TYPE.READ, (((Read_stmtContext)_localctx).id_list!=null?_input.getText(((Read_stmtContext)_localctx).id_list.start,((Read_stmtContext)_localctx).id_list.stop):null), SemanticDataHandler.currentScope);
			setState(208); match(21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Write_stmtContext extends ParserRuleContext {
		public Id_listContext id_list;
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Write_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterWrite_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitWrite_stmt(this);
		}
	}

	public final Write_stmtContext write_stmt() throws RecognitionException {
		Write_stmtContext _localctx = new Write_stmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_write_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(17);
			setState(211); match(6);
			setState(212); ((Write_stmtContext)_localctx).id_list = id_list();
			setState(213); match(18);
			ASTStackHandler.pushIOTree(IO_TYPE.WRITE, (((Write_stmtContext)_localctx).id_list!=null?_input.getText(((Write_stmtContext)_localctx).id_list.start,((Write_stmtContext)_localctx).id_list.stop):null), SemanticDataHandler.currentScope);
			setState(215); match(21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitReturn_stmt(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_return_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217); match(26);
			setState(218); expr();
			setState(219); match(21);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Expr_prefixContext expr_prefix() {
			return getRuleContext(Expr_prefixContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); expr_prefix(0);
			setState(222); factor();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_prefixContext extends ParserRuleContext {
		public int _p;
		public AddopContext addop;
		public Expr_prefixContext expr_prefix() {
			return getRuleContext(Expr_prefixContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public AddopContext addop() {
			return getRuleContext(AddopContext.class,0);
		}
		public Expr_prefixContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr_prefixContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_prefix(this);
		}
	}

	public final Expr_prefixContext expr_prefix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr_prefixContext _localctx = new Expr_prefixContext(_ctx, _parentState, _p);
		Expr_prefixContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, RULE_expr_prefix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(232);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr_prefixContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr_prefix);
					setState(225);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(226); factor();
					setState(227); ((Expr_prefixContext)_localctx).addop = addop();
					ASTStackHandler.createArithmeticTree((((Expr_prefixContext)_localctx).addop!=null?_input.getText(((Expr_prefixContext)_localctx).addop.start,((Expr_prefixContext)_localctx).addop.stop):null)); ASTStackHandler.subExprStack.push("+");
					}
					} 
				}
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Postfix_exprContext postfix_expr() {
			return getRuleContext(Postfix_exprContext.class,0);
		}
		public Factor_prefixContext factor_prefix() {
			return getRuleContext(Factor_prefixContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235); factor_prefix(0);
			setState(236); postfix_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Factor_prefixContext extends ParserRuleContext {
		public int _p;
		public MulopContext mulop;
		public MulopContext mulop() {
			return getRuleContext(MulopContext.class,0);
		}
		public Postfix_exprContext postfix_expr() {
			return getRuleContext(Postfix_exprContext.class,0);
		}
		public Factor_prefixContext factor_prefix() {
			return getRuleContext(Factor_prefixContext.class,0);
		}
		public Factor_prefixContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Factor_prefixContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_factor_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFactor_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFactor_prefix(this);
		}
	}

	public final Factor_prefixContext factor_prefix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Factor_prefixContext _localctx = new Factor_prefixContext(_ctx, _parentState, _p);
		Factor_prefixContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, RULE_factor_prefix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(246);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Factor_prefixContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_factor_prefix);
					setState(239);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(240); postfix_expr();
					setState(241); ((Factor_prefixContext)_localctx).mulop = mulop();
					ASTStackHandler.createArithmeticTree((((Factor_prefixContext)_localctx).mulop!=null?_input.getText(((Factor_prefixContext)_localctx).mulop.start,((Factor_prefixContext)_localctx).mulop.stop):null)); ASTStackHandler.subExprStack.push("*"); /*ASTStackHandler.SubTreeBlockEnded = false;*/
					}
					} 
				}
				setState(248);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Postfix_exprContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public Call_exprContext call_expr() {
			return getRuleContext(Call_exprContext.class,0);
		}
		public Postfix_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfix_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPostfix_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPostfix_expr(this);
		}
	}

	public final Postfix_exprContext postfix_expr() throws RecognitionException {
		Postfix_exprContext _localctx = new Postfix_exprContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_postfix_expr);
		try {
			setState(253);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249); primary();
				ASTStackHandler.SubTreeBlock = false;
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(252); call_expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Call_exprContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public Call_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCall_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCall_expr(this);
		}
	}

	public final Call_exprContext call_expr() throws RecognitionException {
		Call_exprContext _localctx = new Call_exprContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_call_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255); id();
			setState(256); match(6);
			setState(257); expr_list();
			setState(258); match(18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_listContext extends ParserRuleContext {
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_list(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expr_list);
		try {
			setState(264);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(260); expr();
				setState(261); expr_list_tail();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_list_tailContext extends ParserRuleContext {
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_list_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_list_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_list_tail(this);
		}
	}

	public final Expr_list_tailContext expr_list_tail() throws RecognitionException {
		Expr_list_tailContext _localctx = new Expr_list_tailContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expr_list_tail);
		try {
			setState(271);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(266); match(1);
				setState(267); expr();
				setState(268); expr_list_tail();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public IdContext id;
		public Token INTLITERAL;
		public Token FLOATLITERAL;
		public TerminalNode FLOATLITERAL() { return getToken(MicroParser.FLOATLITERAL, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode INTLITERAL() { return getToken(MicroParser.INTLITERAL, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_primary);
		try {
			setState(287);
			switch (_input.LA(1)) {
			case 6:
				enterOuterAlt(_localctx, 1);
				{
				/*ASTStackHandler.SubTreeBlock = true; ASTStackHandler.SubTreeBlockEnded = false;*/ ASTStackHandler.subExprStack.push("(");
				setState(274); match(6);
				setState(275); expr();
				/*ASTStackHandler.SubTreeBlock = false; ASTStackHandler.SubTreeBlockEnded = true;*/
				setState(277); match(18);
				ASTStackHandler.subExprStack.push(")"); ASTStackHandler.updateCurrSubTree();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(280); ((PrimaryContext)_localctx).id = id();
				ASTStackHandler.addTermNode(ASTNodeType.IDENTIFIER, (((PrimaryContext)_localctx).id!=null?_input.getText(((PrimaryContext)_localctx).id.start,((PrimaryContext)_localctx).id.stop):null)); ASTStackHandler.updateCurrSubTree();
				}
				break;
			case INTLITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(283); ((PrimaryContext)_localctx).INTLITERAL = match(INTLITERAL);
				ASTStackHandler.addTermNode(ASTNodeType.LITERAL, (((PrimaryContext)_localctx).INTLITERAL!=null?((PrimaryContext)_localctx).INTLITERAL.getText():null)); ASTStackHandler.updateCurrSubTree();
				}
				break;
			case FLOATLITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(285); ((PrimaryContext)_localctx).FLOATLITERAL = match(FLOATLITERAL);
				ASTStackHandler.addTermNode(ASTNodeType.LITERAL, (((PrimaryContext)_localctx).FLOATLITERAL!=null?((PrimaryContext)_localctx).FLOATLITERAL.getText():null)); ASTStackHandler.updateCurrSubTree();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddopContext extends ParserRuleContext {
		public AddopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAddop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAddop(this);
		}
	}

	public final AddopContext addop() throws RecognitionException {
		AddopContext _localctx = new AddopContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_addop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if ( !(_la==4 || _la==19) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulopContext extends ParserRuleContext {
		public MulopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterMulop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitMulop(this);
		}
	}

	public final MulopContext mulop() throws RecognitionException {
		MulopContext _localctx = new MulopContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_mulop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			_la = _input.LA(1);
			if ( !(_la==3 || _la==27) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_stmtContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public Else_partContext else_part() {
			return getRuleContext(Else_partContext.class,0);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitIf_stmt(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_if_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293); match(15);
			SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null); ASTStackHandler.pushIFELSEStructureTree(ASTNodeType.IF, SemanticDataHandler.currentScope);
			setState(295); match(6);
			setState(296); cond();
			setState(297); match(18);
			setState(298); decl();
			SemanticDataHandler.popCurrentScope();
			setState(300); stmt_list();
			setState(301); else_part();
			setState(302); match(5);
			ASTStackHandler.pushIFELSEStructureTree(ASTNodeType.FI, null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Else_partContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Else_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterElse_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitElse_part(this);
		}
	}

	public final Else_partContext else_part() throws RecognitionException {
		Else_partContext _localctx = new Else_partContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_else_part);
		try {
			setState(312);
			switch (_input.LA(1)) {
			case 12:
				enterOuterAlt(_localctx, 1);
				{
				setState(305); match(12);
				SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null); ASTStackHandler.pushIFELSEStructureTree(ASTNodeType.ELSE, SemanticDataHandler.currentScope);
				setState(307); decl();
				setState(308); stmt_list();
				SemanticDataHandler.popCurrentScope();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public CompopContext compop;
		public CompopContext compop() {
			return getRuleContext(CompopContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCond(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314); expr();
			ASTStackHandler.setCondExpr("l", null);
			setState(316); ((CondContext)_localctx).compop = compop();
			ASTStackHandler.setCondExpr("c", (((CondContext)_localctx).compop!=null?_input.getText(((CondContext)_localctx).compop.start,((CondContext)_localctx).compop.stop):null));
			setState(318); expr();
			ASTStackHandler.setCondExpr("r", null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompopContext extends ParserRuleContext {
		public CompopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCompop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCompop(this);
		}
	}

	public final CompopContext compop() throws RecognitionException {
		CompopContext _localctx = new CompopContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_compop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 10) | (1L << 11) | (1L << 20) | (1L << 23) | (1L << 29))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Init_stmtContext extends ParserRuleContext {
		public Assign_exprContext assign_expr() {
			return getRuleContext(Assign_exprContext.class,0);
		}
		public Init_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterInit_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitInit_stmt(this);
		}
	}

	public final Init_stmtContext init_stmt() throws RecognitionException {
		Init_stmtContext _localctx = new Init_stmtContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_init_stmt);
		try {
			setState(327);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(323); assign_expr();
				/* System.out.println("Completes INIT "); */ ASTStackHandler.changeNodeType(ASTNodeType.FOR_INIT, false);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Incr_stmtContext extends ParserRuleContext {
		public Assign_exprContext assign_expr() {
			return getRuleContext(Assign_exprContext.class,0);
		}
		public Incr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterIncr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitIncr_stmt(this);
		}
	}

	public final Incr_stmtContext incr_stmt() throws RecognitionException {
		Incr_stmtContext _localctx = new Incr_stmtContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_incr_stmt);
		try {
			setState(333);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(329); assign_expr();
				/* System.out.println("Completes INCR"); */ ASTStackHandler.changeNodeType(ASTNodeType.FOR_INCR, false);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 2);
				{
				ASTStackHandler.changeNodeType(ASTNodeType.FOR_INCR, true);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_stmtContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Init_stmtContext init_stmt() {
			return getRuleContext(Init_stmtContext.class,0);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public Incr_stmtContext incr_stmt() {
			return getRuleContext(Incr_stmtContext.class,0);
		}
		public For_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFor_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFor_stmt(this);
		}
	}

	public final For_stmtContext for_stmt() throws RecognitionException {
		For_stmtContext _localctx = new For_stmtContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_for_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335); match(16);
			SemanticDataHandler.pushNewScope(SCOPE.BLOCK, null); ASTStackHandler.pushFORStructure(ASTNodeType.FOR, SemanticDataHandler.currentScope); /*System.out.println("Starts expr tree creations!")*/;
			setState(337); match(6);
			setState(338); init_stmt();
			setState(339); match(21);
			setState(340); cond();
			setState(341); match(21);
			setState(342); incr_stmt();
			setState(343); match(18);
			 /*System.out.println("Finishes exprs tress creation!");*/
			setState(345); decl();
			setState(346); stmt_list();
			SemanticDataHandler.popCurrentScope();
			setState(348); match(2);
			ASTStackHandler.pushFORStructure(ASTNodeType.ROF, SemanticDataHandler.currentScope); /*System.out.println("Completes ROF");*/
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 26: return expr_prefix_sempred((Expr_prefixContext)_localctx, predIndex);

		case 28: return factor_prefix_sempred((Factor_prefixContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean factor_prefix_sempred(Factor_prefixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean expr_prefix_sempred(Expr_prefixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3(\u0162\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5m\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\5\t\177\n\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\5\f\u008b\n\f\3\r\3\r\3\r\3\r\5\r\u0091\n\r\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\5\17\u009c\n\17\3\20\3\20\3\20\3\20\5\20\u00a2"+
		"\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\5\23\u00b7\n\23\3\24\3\24\3\24\5\24\u00bc"+
		"\n\24\3\25\3\25\3\25\3\25\5\25\u00c2\n\25\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\7\34\u00e9\n\34\f\34\16\34\u00ec\13\34\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u00f7\n\36\f\36\16\36\u00fa\13\36"+
		"\3\37\3\37\3\37\3\37\5\37\u0100\n\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\5!\u010b"+
		"\n!\3\"\3\"\3\"\3\"\3\"\5\"\u0112\n\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\5#\u0122\n#\3$\3$\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u013b\n\'\3(\3(\3(\3(\3(\3(\3(\3)\3"+
		")\3*\3*\3*\3*\5*\u014a\n*\3+\3+\3+\3+\5+\u0150\n+\3,\3,\3,\3,\3,\3,\3"+
		",\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\2-\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTV\2\6\4\2\32\32  \4\2\6\6\25\25"+
		"\4\2\5\5\35\35\7\2\t\t\f\r\26\26\31\31\37\37\u014e\2X\3\2\2\2\4_\3\2\2"+
		"\2\6a\3\2\2\2\bl\3\2\2\2\nn\3\2\2\2\fu\3\2\2\2\16w\3\2\2\2\20~\3\2\2\2"+
		"\22\u0080\3\2\2\2\24\u0082\3\2\2\2\26\u008a\3\2\2\2\30\u0090\3\2\2\2\32"+
		"\u0092\3\2\2\2\34\u009b\3\2\2\2\36\u00a1\3\2\2\2 \u00a3\3\2\2\2\"\u00af"+
		"\3\2\2\2$\u00b6\3\2\2\2&\u00bb\3\2\2\2(\u00c1\3\2\2\2*\u00c3\3\2\2\2,"+
		"\u00c6\3\2\2\2.\u00cd\3\2\2\2\60\u00d4\3\2\2\2\62\u00db\3\2\2\2\64\u00df"+
		"\3\2\2\2\66\u00e2\3\2\2\28\u00ed\3\2\2\2:\u00f0\3\2\2\2<\u00ff\3\2\2\2"+
		">\u0101\3\2\2\2@\u010a\3\2\2\2B\u0111\3\2\2\2D\u0121\3\2\2\2F\u0123\3"+
		"\2\2\2H\u0125\3\2\2\2J\u0127\3\2\2\2L\u013a\3\2\2\2N\u013c\3\2\2\2P\u0143"+
		"\3\2\2\2R\u0149\3\2\2\2T\u014f\3\2\2\2V\u0151\3\2\2\2XY\7!\2\2YZ\5\4\3"+
		"\2Z[\7\36\2\2[\\\5\6\4\2\\]\b\2\1\2]^\7\13\2\2^\3\3\2\2\2_`\7\"\2\2`\5"+
		"\3\2\2\2ab\b\4\1\2bc\5\b\5\2cd\5\36\20\2d\7\3\2\2\2ef\5\n\6\2fg\5\b\5"+
		"\2gm\3\2\2\2hi\5\16\b\2ij\5\b\5\2jm\3\2\2\2km\3\2\2\2le\3\2\2\2lh\3\2"+
		"\2\2lk\3\2\2\2m\t\3\2\2\2no\7\20\2\2op\5\4\3\2pq\7\33\2\2qr\5\f\7\2rs"+
		"\7\27\2\2st\b\6\1\2t\13\3\2\2\2uv\7$\2\2v\r\3\2\2\2wx\5\22\n\2xy\5\24"+
		"\13\2yz\7\27\2\2z{\b\b\1\2{\17\3\2\2\2|\177\5\22\n\2}\177\7\17\2\2~|\3"+
		"\2\2\2~}\3\2\2\2\177\21\3\2\2\2\u0080\u0081\t\2\2\2\u0081\23\3\2\2\2\u0082"+
		"\u0083\5\4\3\2\u0083\u0084\5\26\f\2\u0084\25\3\2\2\2\u0085\u0086\7\3\2"+
		"\2\u0086\u0087\5\4\3\2\u0087\u0088\5\26\f\2\u0088\u008b\3\2\2\2\u0089"+
		"\u008b\3\2\2\2\u008a\u0085\3\2\2\2\u008a\u0089\3\2\2\2\u008b\27\3\2\2"+
		"\2\u008c\u008d\5\32\16\2\u008d\u008e\5\34\17\2\u008e\u0091\3\2\2\2\u008f"+
		"\u0091\3\2\2\2\u0090\u008c\3\2\2\2\u0090\u008f\3\2\2\2\u0091\31\3\2\2"+
		"\2\u0092\u0093\5\22\n\2\u0093\u0094\5\4\3\2\u0094\u0095\b\16\1\2\u0095"+
		"\33\3\2\2\2\u0096\u0097\7\3\2\2\u0097\u0098\5\32\16\2\u0098\u0099\5\34"+
		"\17\2\u0099\u009c\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u0096\3\2\2\2\u009b"+
		"\u009a\3\2\2\2\u009c\35\3\2\2\2\u009d\u009e\5 \21\2\u009e\u009f\5\36\20"+
		"\2\u009f\u00a2\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u009d\3\2\2\2\u00a1\u00a0"+
		"\3\2\2\2\u00a2\37\3\2\2\2\u00a3\u00a4\7\30\2\2\u00a4\u00a5\5\20\t\2\u00a5"+
		"\u00a6\5\4\3\2\u00a6\u00a7\b\21\1\2\u00a7\u00a8\7\b\2\2\u00a8\u00a9\5"+
		"\30\r\2\u00a9\u00aa\7\24\2\2\u00aa\u00ab\7\36\2\2\u00ab\u00ac\5\"\22\2"+
		"\u00ac\u00ad\7\13\2\2\u00ad\u00ae\b\21\1\2\u00ae!\3\2\2\2\u00af\u00b0"+
		"\5\b\5\2\u00b0\u00b1\5$\23\2\u00b1#\3\2\2\2\u00b2\u00b3\5&\24\2\u00b3"+
		"\u00b4\5$\23\2\u00b4\u00b7\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b2\3\2"+
		"\2\2\u00b6\u00b5\3\2\2\2\u00b7%\3\2\2\2\u00b8\u00bc\5(\25\2\u00b9\u00bc"+
		"\5J&\2\u00ba\u00bc\5V,\2\u00bb\u00b8\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00ba\3\2\2\2\u00bc\'\3\2\2\2\u00bd\u00c2\5*\26\2\u00be\u00c2\5.\30\2"+
		"\u00bf\u00c2\5\60\31\2\u00c0\u00c2\5\62\32\2\u00c1\u00bd\3\2\2\2\u00c1"+
		"\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2)\3\2\2\2"+
		"\u00c3\u00c4\5,\27\2\u00c4\u00c5\7\27\2\2\u00c5+\3\2\2\2\u00c6\u00c7\5"+
		"\4\3\2\u00c7\u00c8\b\27\1\2\u00c8\u00c9\7\33\2\2\u00c9\u00ca\b\27\1\2"+
		"\u00ca\u00cb\5\64\33\2\u00cb\u00cc\b\27\1\2\u00cc-\3\2\2\2\u00cd\u00ce"+
		"\7\n\2\2\u00ce\u00cf\7\b\2\2\u00cf\u00d0\5\24\13\2\u00d0\u00d1\7\24\2"+
		"\2\u00d1\u00d2\b\30\1\2\u00d2\u00d3\7\27\2\2\u00d3/\3\2\2\2\u00d4\u00d5"+
		"\7\23\2\2\u00d5\u00d6\7\b\2\2\u00d6\u00d7\5\24\13\2\u00d7\u00d8\7\24\2"+
		"\2\u00d8\u00d9\b\31\1\2\u00d9\u00da\7\27\2\2\u00da\61\3\2\2\2\u00db\u00dc"+
		"\7\34\2\2\u00dc\u00dd\5\64\33\2\u00dd\u00de\7\27\2\2\u00de\63\3\2\2\2"+
		"\u00df\u00e0\5\66\34\2\u00e0\u00e1\58\35\2\u00e1\65\3\2\2\2\u00e2\u00ea"+
		"\b\34\1\2\u00e3\u00e4\6\34\2\3\u00e4\u00e5\58\35\2\u00e5\u00e6\5F$\2\u00e6"+
		"\u00e7\b\34\1\2\u00e7\u00e9\3\2\2\2\u00e8\u00e3\3\2\2\2\u00e9\u00ec\3"+
		"\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\67\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ed\u00ee\5:\36\2\u00ee\u00ef\5<\37\2\u00ef9\3\2\2\2"+
		"\u00f0\u00f8\b\36\1\2\u00f1\u00f2\6\36\3\3\u00f2\u00f3\5<\37\2\u00f3\u00f4"+
		"\5H%\2\u00f4\u00f5\b\36\1\2\u00f5\u00f7\3\2\2\2\u00f6\u00f1\3\2\2\2\u00f7"+
		"\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9;\3\2\2\2"+
		"\u00fa\u00f8\3\2\2\2\u00fb\u00fc\5D#\2\u00fc\u00fd\b\37\1\2\u00fd\u0100"+
		"\3\2\2\2\u00fe\u0100\5> \2\u00ff\u00fb\3\2\2\2\u00ff\u00fe\3\2\2\2\u0100"+
		"=\3\2\2\2\u0101\u0102\5\4\3\2\u0102\u0103\7\b\2\2\u0103\u0104\5@!\2\u0104"+
		"\u0105\7\24\2\2\u0105?\3\2\2\2\u0106\u0107\5\64\33\2\u0107\u0108\5B\""+
		"\2\u0108\u010b\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u0106\3\2\2\2\u010a\u0109"+
		"\3\2\2\2\u010bA\3\2\2\2\u010c\u010d\7\3\2\2\u010d\u010e\5\64\33\2\u010e"+
		"\u010f\5B\"\2\u010f\u0112\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u010c\3\2"+
		"\2\2\u0111\u0110\3\2\2\2\u0112C\3\2\2\2\u0113\u0114\b#\1\2\u0114\u0115"+
		"\7\b\2\2\u0115\u0116\5\64\33\2\u0116\u0117\b#\1\2\u0117\u0118\7\24\2\2"+
		"\u0118\u0119\b#\1\2\u0119\u0122\3\2\2\2\u011a\u011b\5\4\3\2\u011b\u011c"+
		"\b#\1\2\u011c\u0122\3\2\2\2\u011d\u011e\7#\2\2\u011e\u0122\b#\1\2\u011f"+
		"\u0120\7%\2\2\u0120\u0122\b#\1\2\u0121\u0113\3\2\2\2\u0121\u011a\3\2\2"+
		"\2\u0121\u011d\3\2\2\2\u0121\u011f\3\2\2\2\u0122E\3\2\2\2\u0123\u0124"+
		"\t\3\2\2\u0124G\3\2\2\2\u0125\u0126\t\4\2\2\u0126I\3\2\2\2\u0127\u0128"+
		"\7\21\2\2\u0128\u0129\b&\1\2\u0129\u012a\7\b\2\2\u012a\u012b\5N(\2\u012b"+
		"\u012c\7\24\2\2\u012c\u012d\5\b\5\2\u012d\u012e\b&\1\2\u012e\u012f\5$"+
		"\23\2\u012f\u0130\5L\'\2\u0130\u0131\7\7\2\2\u0131\u0132\b&\1\2\u0132"+
		"K\3\2\2\2\u0133\u0134\7\16\2\2\u0134\u0135\b\'\1\2\u0135\u0136\5\b\5\2"+
		"\u0136\u0137\5$\23\2\u0137\u0138\b\'\1\2\u0138\u013b\3\2\2\2\u0139\u013b"+
		"\3\2\2\2\u013a\u0133\3\2\2\2\u013a\u0139\3\2\2\2\u013bM\3\2\2\2\u013c"+
		"\u013d\5\64\33\2\u013d\u013e\b(\1\2\u013e\u013f\5P)\2\u013f\u0140\b(\1"+
		"\2\u0140\u0141\5\64\33\2\u0141\u0142\b(\1\2\u0142O\3\2\2\2\u0143\u0144"+
		"\t\5\2\2\u0144Q\3\2\2\2\u0145\u0146\5,\27\2\u0146\u0147\b*\1\2\u0147\u014a"+
		"\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0145\3\2\2\2\u0149\u0148\3\2\2\2\u014a"+
		"S\3\2\2\2\u014b\u014c\5,\27\2\u014c\u014d\b+\1\2\u014d\u0150\3\2\2\2\u014e"+
		"\u0150\b+\1\2\u014f\u014b\3\2\2\2\u014f\u014e\3\2\2\2\u0150U\3\2\2\2\u0151"+
		"\u0152\7\22\2\2\u0152\u0153\b,\1\2\u0153\u0154\7\b\2\2\u0154\u0155\5R"+
		"*\2\u0155\u0156\7\27\2\2\u0156\u0157\5N(\2\u0157\u0158\7\27\2\2\u0158"+
		"\u0159\5T+\2\u0159\u015a\7\24\2\2\u015a\u015b\b,\1\2\u015b\u015c\5\b\5"+
		"\2\u015c\u015d\5$\23\2\u015d\u015e\b,\1\2\u015e\u015f\7\4\2\2\u015f\u0160"+
		"\b,\1\2\u0160W\3\2\2\2\24l~\u008a\u0090\u009b\u00a1\u00b6\u00bb\u00c1"+
		"\u00ea\u00f8\u00ff\u010a\u0111\u0121\u013a\u0149\u014f";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}