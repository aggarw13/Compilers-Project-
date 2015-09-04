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
		KEYWORD=1, IDENTIFIER=2, INTLITERAL=3, STRINGLITERAL=4, FLOATLITERAL=5, 
		COMMENT=6, WHITESPACES=7, OPERATOR=8;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"KEYWORD", "IDENTIFIER", "INTLITERAL", "STRINGLITERAL", "FLOATLITERAL", 
		"COMMENT", "WHITESPACES", "OPERATOR"
	};
	public static final String[] ruleNames = {
		"DIGIT", "LETTER", "KEYWORD", "IDENTIFIER", "INTLITERAL", "STRINGLITERAL", 
		"FLOATLITERAL", "COMMENT", "WHITESPACES", "OPERATOR"
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
		case 7: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 8: WHITESPACES_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\n\u00b5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\5\4o\n\4\3\5\3\5\3\5\7\5t\n\5\f\5\16\5w\13\5\3\6\6\6z\n\6\r\6\16"+
		"\6{\3\7\3\7\7\7\u0080\n\7\f\7\16\7\u0083\13\7\3\7\3\7\3\b\7\b\u0088\n"+
		"\b\f\b\16\b\u008b\13\b\3\b\3\b\6\b\u008f\n\b\r\b\16\b\u0090\3\t\3\t\3"+
		"\t\3\t\7\t\u0097\n\t\f\t\16\t\u009a\13\t\3\t\3\t\3\t\5\t\u009f\n\t\3\t"+
		"\3\t\3\n\6\n\u00a4\n\n\r\n\16\n\u00a5\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u00b4\n\13\3\u0098\f\3\2\1\5\2\1\7\3\1"+
		"\t\4\1\13\5\1\r\6\1\17\7\1\21\b\2\23\t\3\25\n\1\3\2\7\4\2C\\c|\3\2$$\5"+
		"\2\13\f\17\17\"\"\6\2,-//\61\61>@\5\2*+..==\u00d1\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\3\27\3\2\2\2\5\31\3\2\2\2\7n\3\2\2\2\tp\3\2\2\2\13y\3\2\2"+
		"\2\r}\3\2\2\2\17\u0089\3\2\2\2\21\u0092\3\2\2\2\23\u00a3\3\2\2\2\25\u00b3"+
		"\3\2\2\2\27\30\4\62;\2\30\4\3\2\2\2\31\32\t\2\2\2\32\6\3\2\2\2\33\34\7"+
		"R\2\2\34\35\7T\2\2\35\36\7Q\2\2\36\37\7I\2\2\37 \7T\2\2 !\7C\2\2!o\7O"+
		"\2\2\"#\7D\2\2#$\7G\2\2$%\7I\2\2%&\7K\2\2&o\7P\2\2\'(\7G\2\2()\7P\2\2"+
		")o\7F\2\2*+\7K\2\2+,\7P\2\2,o\7V\2\2-.\7H\2\2./\7N\2\2/\60\7Q\2\2\60\61"+
		"\7C\2\2\61o\7V\2\2\62\63\7U\2\2\63\64\7V\2\2\64\65\7T\2\2\65\66\7K\2\2"+
		"\66\67\7P\2\2\67o\7I\2\289\7H\2\29:\7Q\2\2:o\7T\2\2;<\7T\2\2<=\7Q\2\2"+
		"=o\7H\2\2>?\7Y\2\2?@\7T\2\2@A\7K\2\2AB\7V\2\2Bo\7G\2\2CD\7T\2\2DE\7G\2"+
		"\2EF\7C\2\2Fo\7F\2\2GH\7H\2\2HI\7W\2\2IJ\7P\2\2JK\7E\2\2KL\7V\2\2LM\7"+
		"K\2\2MN\7Q\2\2No\7P\2\2OP\7X\2\2PQ\7Q\2\2QR\7K\2\2Ro\7F\2\2ST\7K\2\2T"+
		"o\7H\2\2UV\7G\2\2VW\7N\2\2WX\7U\2\2Xo\7G\2\2YZ\7H\2\2Zo\7K\2\2[\\\7E\2"+
		"\2\\]\7Q\2\2]^\7P\2\2^_\7V\2\2_`\7K\2\2`a\7P\2\2ab\7W\2\2bo\7G\2\2cd\7"+
		"D\2\2de\7T\2\2ef\7G\2\2fg\7C\2\2go\7M\2\2hi\7T\2\2ij\7G\2\2jk\7V\2\2k"+
		"l\7W\2\2lm\7T\2\2mo\7P\2\2n\33\3\2\2\2n\"\3\2\2\2n\'\3\2\2\2n*\3\2\2\2"+
		"n-\3\2\2\2n\62\3\2\2\2n8\3\2\2\2n;\3\2\2\2n>\3\2\2\2nC\3\2\2\2nG\3\2\2"+
		"\2nO\3\2\2\2nS\3\2\2\2nU\3\2\2\2nY\3\2\2\2n[\3\2\2\2nc\3\2\2\2nh\3\2\2"+
		"\2o\b\3\2\2\2pu\5\5\3\2qt\5\5\3\2rt\5\3\2\2sq\3\2\2\2sr\3\2\2\2tw\3\2"+
		"\2\2us\3\2\2\2uv\3\2\2\2v\n\3\2\2\2wu\3\2\2\2xz\5\3\2\2yx\3\2\2\2z{\3"+
		"\2\2\2{y\3\2\2\2{|\3\2\2\2|\f\3\2\2\2}\u0081\7$\2\2~\u0080\n\3\2\2\177"+
		"~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\7$\2\2\u0085\16\3\2\2\2"+
		"\u0086\u0088\5\3\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b\u0089\3\2\2\2\u008c"+
		"\u008e\7\60\2\2\u008d\u008f\5\3\2\2\u008e\u008d\3\2\2\2\u008f\u0090\3"+
		"\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\20\3\2\2\2\u0092"+
		"\u0093\7/\2\2\u0093\u0094\7/\2\2\u0094\u0098\3\2\2\2\u0095\u0097\13\2"+
		"\2\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0099\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0099\u009e\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009f\7\f"+
		"\2\2\u009c\u009d\7\17\2\2\u009d\u009f\7\f\2\2\u009e\u009b\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\b\t\2\2\u00a1\22\3\2\2"+
		"\2\u00a2\u00a4\t\4\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3"+
		"\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\n\3\2\u00a8"+
		"\24\3\2\2\2\u00a9\u00b4\t\5\2\2\u00aa\u00ab\7#\2\2\u00ab\u00b4\7?\2\2"+
		"\u00ac\u00ad\7>\2\2\u00ad\u00b4\7?\2\2\u00ae\u00af\7@\2\2\u00af\u00b4"+
		"\7?\2\2\u00b0\u00b1\7<\2\2\u00b1\u00b4\7?\2\2\u00b2\u00b4\t\6\2\2\u00b3"+
		"\u00a9\3\2\2\2\u00b3\u00aa\3\2\2\2\u00b3\u00ac\3\2\2\2\u00b3\u00ae\3\2"+
		"\2\2\u00b3\u00b0\3\2\2\2\u00b3\u00b2\3\2\2\2\u00b4\26\3\2\2\2\16\2nsu"+
		"{\u0081\u0089\u0090\u0098\u009e\u00a5\u00b3";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}