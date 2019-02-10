package fr.insee.vtl.test;

import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import fr.insee.vtl.VtlLexer;

public class TestLexer {

	static final String VTL_EXPRESSION = "DS_r := DS_1 [ calc Me_2 := upper ( Me_1 ) ]";
	protected static Logger logger = LogManager.getLogger();
	VtlLexer lexer = null;

	@Before
	public void setUp() throws Exception {

		lexer = new VtlLexer(CharStreams.fromString(VTL_EXPRESSION));
	}

	@Test
	public void testTokens() {

		List<? extends Token> tokens = lexer.getAllTokens();

		// Example: [@-1,0:3='DS_r',<233>,1:0], interpretation: [?,start:stop='text',<type>,line:start]
		for (Token token : tokens) {
			String description = "Token '" + token.getText() + "', position: " + token.getStartIndex() + "-" + token.getStopIndex();
			String symbolicName = lexer.getVocabulary().getSymbolicName(token.getType());
			if (symbolicName != null) description += ", type: " + symbolicName;
			logger.info(token + "\t\t" + description);
		}
	}
}
