package fr.insee.vtl.test;

import java.util.Arrays;
import java.util.List;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import fr.insee.vtl.VtlLexer;
import fr.insee.vtl.VtlParser;
import fr.insee.vtl.VtlParser.StartContext;

public class TestParser {

	protected static Logger logger = LogManager.getLogger();
	VtlLexer lexer = null;
	VtlParser parser = null;

	@Test
	public void testTree() {

		final String vtlExpression = "X < A";

		// Create VTL lexer and parser instances
		lexer = new VtlLexer(CharStreams.fromString(vtlExpression));
		parser = new VtlParser(new CommonTokenStream(lexer));
		// Create a context for rule "start" (root rule for VTL)
		StartContext context = parser.start();
		// Parse tree as a string
		String tree = context.toStringTree(parser);

		logger.info(tree);
	}

	@Test
	public void testPostScript() {

		final String vtlExpression = "DS_r := DS_1 [ calc Me_2 := upper ( Me_1 ) ]";

		// Create VTL lexer and parser instances
		lexer = new VtlLexer(CharStreams.fromString(vtlExpression));
		parser = new VtlParser(new CommonTokenStream(lexer));
		// Create a context for rule "start" (root rule for VTL)
		StartContext context = parser.start();
		List<String> ruleNames = Arrays.asList(parser.getRuleNames());
		System.out.println(ruleNames);
		// Return tree as PostScript
		String psTree = Trees.getPS(context, ruleNames);

		System.out.println(psTree);
	}
	
}
