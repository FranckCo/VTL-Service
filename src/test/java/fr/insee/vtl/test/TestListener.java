package fr.insee.vtl.test;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import fr.insee.vtl.VtlLexer;
import fr.insee.vtl.VtlParser;

public class TestListener {

	static final String VTL_EXPRESSION = "DS_r := DS_1 [ calc Me_2 := upper ( Me_1 ) ]";
	static final String EXPECTED_TREE = "(start (statement (varID DS_r) := (expr (exprAtom (ref (varID DS_1))) [ (datasetClause (calcClause calc (calcClauseItem (componentID Me_2) := (calcExpr (expr (exprAtom upper ( (expr (exprAtom (ref (varID Me_1)))) ))))))) ])) <EOF>)";

	@Test
	public void testStatement() {

		VtlLexer lexer = new VtlLexer(CharStreams.fromString(VTL_EXPRESSION));
		VtlParser parser = new VtlParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.start();

		ParseTreeWalker walker = new ParseTreeWalker();
		StatementListener listener = new StatementListener();

		walker.walk(listener, tree);

	}

}
