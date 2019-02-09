package fr.insee.vtl.test;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import fr.insee.vtl.VtlLexer;
import fr.insee.vtl.VtlParser;

public class TestVisitor {

	static final String VTL_EXPRESSION = "DS_r := DS_1 [ calc Me_2 := upper ( Me_1 ) ]";
	static final String EXPECTED_TREE = "(start (statement (varID DS_r) := (expr (exprAtom (ref (varID DS_1))) [ (datasetClause (calcClause calc (calcClauseItem (componentID Me_2) := (calcExpr (expr (exprAtom upper ( (expr (exprAtom (ref (varID Me_1)))) ))))))) ])) <EOF>)";

	protected static Logger logger = LogManager.getLogger();

	@Test
	public void testStatement() {

		VtlLexer lexer = new VtlLexer(CharStreams.fromString(VTL_EXPRESSION));
		VtlParser parser = new VtlParser(new CommonTokenStream(lexer));

		StatementVisitor visitor = new StatementVisitor();

		logger.info("Number of statement children: " + visitor.visit(parser.statement()));

	}
}
