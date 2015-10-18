// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"','", "'ROF'", "'*'", "'-'", "'FI'", "'('", "'<'", "'READ'", "'END'", 
		"'!='", "'<='", "'ELSE'", "'VOID'", "'STRING'", "'IF'", "'FOR'", "'WRITE'", 
		"')'", "'+'", "'='", "';'", "'FUNCTION'", "'>'", "'FLOAT'", "':='", "'RETURN'", 
		"'/'", "'BEGIN'", "'>='", "'INT'", "'PROGRAM'", "IDENTIFIER", "INTLITERAL", 
		"STRINGLITERAL", "FLOATLITERAL", "COMMENT", "WHITESPACES", "OPERATOR"
	};
	public static final String[] ruleNames = {
		"T__29", "T__28", "T__27", "T__26", "T__25", "T__24", "T__23", "T__22", 
		"T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", "T__14", 
		"T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", 
		"T__4", "T__3", "T__2", "T__1", "T__0", "DIGIT", "LETTER", "PROGRAM", 
		"IDENTIFIER", "INTLITERAL", "STRINGLITERAL", "FLOATLITERAL", "COMMENT", 
		"WHITESPACES", "OPERATOR"
	};


	public MicroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 37: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 38: WHITESPACES_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACES_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2(\u0114\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3"+
		"\36\3\37\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"#\3#\3#\7#\u00d3\n#\f#\16#\u00d6\13#\3$\6$\u00d9\n$\r$\16$\u00da\3%\3"+
		"%\7%\u00df\n%\f%\16%\u00e2\13%\3%\3%\3&\7&\u00e7\n&\f&\16&\u00ea\13&\3"+
		"&\3&\6&\u00ee\n&\r&\16&\u00ef\3\'\3\'\3\'\3\'\7\'\u00f6\n\'\f\'\16\'\u00f9"+
		"\13\'\3\'\3\'\3\'\5\'\u00fe\n\'\3\'\3\'\3(\6(\u0103\n(\r(\16(\u0104\3"+
		"(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0113\n)\3\u00f7*\3\3\1\5\4\1\7"+
		"\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33"+
		"\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1"+
		"\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?\2\1A\2\1C!\1E\"\1G#"+
		"\1I$\1K%\1M&\2O\'\3Q(\1\3\2\7\4\2C\\c|\3\2$$\5\2\13\f\17\17\"\"\6\2,-"+
		"//\61\61>@\5\2*+..==\u011f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2"+
		"\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3S\3\2\2\2"+
		"\5U\3\2\2\2\7Y\3\2\2\2\t[\3\2\2\2\13]\3\2\2\2\r`\3\2\2\2\17b\3\2\2\2\21"+
		"d\3\2\2\2\23i\3\2\2\2\25m\3\2\2\2\27p\3\2\2\2\31s\3\2\2\2\33x\3\2\2\2"+
		"\35}\3\2\2\2\37\u0084\3\2\2\2!\u0087\3\2\2\2#\u008b\3\2\2\2%\u0091\3\2"+
		"\2\2\'\u0093\3\2\2\2)\u0095\3\2\2\2+\u0097\3\2\2\2-\u0099\3\2\2\2/\u00a2"+
		"\3\2\2\2\61\u00a4\3\2\2\2\63\u00aa\3\2\2\2\65\u00ad\3\2\2\2\67\u00b4\3"+
		"\2\2\29\u00b6\3\2\2\2;\u00bc\3\2\2\2=\u00bf\3\2\2\2?\u00c3\3\2\2\2A\u00c5"+
		"\3\2\2\2C\u00c7\3\2\2\2E\u00cf\3\2\2\2G\u00d8\3\2\2\2I\u00dc\3\2\2\2K"+
		"\u00e8\3\2\2\2M\u00f1\3\2\2\2O\u0102\3\2\2\2Q\u0112\3\2\2\2ST\7.\2\2T"+
		"\4\3\2\2\2UV\7T\2\2VW\7Q\2\2WX\7H\2\2X\6\3\2\2\2YZ\7,\2\2Z\b\3\2\2\2["+
		"\\\7/\2\2\\\n\3\2\2\2]^\7H\2\2^_\7K\2\2_\f\3\2\2\2`a\7*\2\2a\16\3\2\2"+
		"\2bc\7>\2\2c\20\3\2\2\2de\7T\2\2ef\7G\2\2fg\7C\2\2gh\7F\2\2h\22\3\2\2"+
		"\2ij\7G\2\2jk\7P\2\2kl\7F\2\2l\24\3\2\2\2mn\7#\2\2no\7?\2\2o\26\3\2\2"+
		"\2pq\7>\2\2qr\7?\2\2r\30\3\2\2\2st\7G\2\2tu\7N\2\2uv\7U\2\2vw\7G\2\2w"+
		"\32\3\2\2\2xy\7X\2\2yz\7Q\2\2z{\7K\2\2{|\7F\2\2|\34\3\2\2\2}~\7U\2\2~"+
		"\177\7V\2\2\177\u0080\7T\2\2\u0080\u0081\7K\2\2\u0081\u0082\7P\2\2\u0082"+
		"\u0083\7I\2\2\u0083\36\3\2\2\2\u0084\u0085\7K\2\2\u0085\u0086\7H\2\2\u0086"+
		" \3\2\2\2\u0087\u0088\7H\2\2\u0088\u0089\7Q\2\2\u0089\u008a\7T\2\2\u008a"+
		"\"\3\2\2\2\u008b\u008c\7Y\2\2\u008c\u008d\7T\2\2\u008d\u008e\7K\2\2\u008e"+
		"\u008f\7V\2\2\u008f\u0090\7G\2\2\u0090$\3\2\2\2\u0091\u0092\7+\2\2\u0092"+
		"&\3\2\2\2\u0093\u0094\7-\2\2\u0094(\3\2\2\2\u0095\u0096\7?\2\2\u0096*"+
		"\3\2\2\2\u0097\u0098\7=\2\2\u0098,\3\2\2\2\u0099\u009a\7H\2\2\u009a\u009b"+
		"\7W\2\2\u009b\u009c\7P\2\2\u009c\u009d\7E\2\2\u009d\u009e\7V\2\2\u009e"+
		"\u009f\7K\2\2\u009f\u00a0\7Q\2\2\u00a0\u00a1\7P\2\2\u00a1.\3\2\2\2\u00a2"+
		"\u00a3\7@\2\2\u00a3\60\3\2\2\2\u00a4\u00a5\7H\2\2\u00a5\u00a6\7N\2\2\u00a6"+
		"\u00a7\7Q\2\2\u00a7\u00a8\7C\2\2\u00a8\u00a9\7V\2\2\u00a9\62\3\2\2\2\u00aa"+
		"\u00ab\7<\2\2\u00ab\u00ac\7?\2\2\u00ac\64\3\2\2\2\u00ad\u00ae\7T\2\2\u00ae"+
		"\u00af\7G\2\2\u00af\u00b0\7V\2\2\u00b0\u00b1\7W\2\2\u00b1\u00b2\7T\2\2"+
		"\u00b2\u00b3\7P\2\2\u00b3\66\3\2\2\2\u00b4\u00b5\7\61\2\2\u00b58\3\2\2"+
		"\2\u00b6\u00b7\7D\2\2\u00b7\u00b8\7G\2\2\u00b8\u00b9\7I\2\2\u00b9\u00ba"+
		"\7K\2\2\u00ba\u00bb\7P\2\2\u00bb:\3\2\2\2\u00bc\u00bd\7@\2\2\u00bd\u00be"+
		"\7?\2\2\u00be<\3\2\2\2\u00bf\u00c0\7K\2\2\u00c0\u00c1\7P\2\2\u00c1\u00c2"+
		"\7V\2\2\u00c2>\3\2\2\2\u00c3\u00c4\4\62;\2\u00c4@\3\2\2\2\u00c5\u00c6"+
		"\t\2\2\2\u00c6B\3\2\2\2\u00c7\u00c8\7R\2\2\u00c8\u00c9\7T\2\2\u00c9\u00ca"+
		"\7Q\2\2\u00ca\u00cb\7I\2\2\u00cb\u00cc\7T\2\2\u00cc\u00cd\7C\2\2\u00cd"+
		"\u00ce\7O\2\2\u00ceD\3\2\2\2\u00cf\u00d4\5A!\2\u00d0\u00d3\5A!\2\u00d1"+
		"\u00d3\5? \2\u00d2\u00d0\3\2\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2"+
		"\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5F\3\2\2\2\u00d6\u00d4"+
		"\3\2\2\2\u00d7\u00d9\5? \2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00dbH\3\2\2\2\u00dc\u00e0\7$\2\2\u00dd"+
		"\u00df\n\3\2\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3"+
		"\u00e4\7$\2\2\u00e4J\3\2\2\2\u00e5\u00e7\5? \2\u00e6\u00e5\3\2\2\2\u00e7"+
		"\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00eb\3\2"+
		"\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ed\7\60\2\2\u00ec\u00ee\5? \2\u00ed"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2"+
		"\2\2\u00f0L\3\2\2\2\u00f1\u00f2\7/\2\2\u00f2\u00f3\7/\2\2\u00f3\u00f7"+
		"\3\2\2\2\u00f4\u00f6\13\2\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f9\3\2\2\2"+
		"\u00f7\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fd\3\2\2\2\u00f9\u00f7"+
		"\3\2\2\2\u00fa\u00fe\7\f\2\2\u00fb\u00fc\7\17\2\2\u00fc\u00fe\7\f\2\2"+
		"\u00fd\u00fa\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100"+
		"\b\'\2\2\u0100N\3\2\2\2\u0101\u0103\t\4\2\2\u0102\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\3\2"+
		"\2\2\u0106\u0107\b(\3\2\u0107P\3\2\2\2\u0108\u0113\t\5\2\2\u0109\u010a"+
		"\7#\2\2\u010a\u0113\7?\2\2\u010b\u010c\7>\2\2\u010c\u0113\7?\2\2\u010d"+
		"\u010e\7@\2\2\u010e\u0113\7?\2\2\u010f\u0110\7<\2\2\u0110\u0113\7?\2\2"+
		"\u0111\u0113\t\6\2\2\u0112\u0108\3\2\2\2\u0112\u0109\3\2\2\2\u0112\u010b"+
		"\3\2\2\2\u0112\u010d\3\2\2\2\u0112\u010f\3\2\2\2\u0112\u0111\3\2\2\2\u0113"+
		"R\3\2\2\2\r\2\u00d2\u00d4\u00da\u00e0\u00e8\u00ef\u00f7\u00fd\u0104\u0112";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}