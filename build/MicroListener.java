// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MicroParser}.
 */
public interface MicroListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MicroParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(@NotNull MicroParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(@NotNull MicroParser.KeywordContext ctx);
}