package fr.insee.vtl;

import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import fr.insee.vtl.VtlParser.StartContext;
import fr.insee.vtl.exception.VTLServiceException;

/**
 * Root resource (exposed at "vtl-parser" path)
 */
@Path("tree")
public class Tree {

	/**
	 * Method handling HTTP GET requests for "text/plain" media type.
	 * 
	 * @param expression A VTL 2.0 expression.
	 * @return The parse tree corresponding to the expression, returned as a text/plain response.
	 * @throws Exception
	 */
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getText(@QueryParam("expression") String expression) throws Exception {
		String tree = null;
		try {
			tree = getTree(URLDecoder.decode(expression, "UTF-8"));
		} catch (RecognitionException e) {
			throw new VTLServiceException(400, e.getOffendingState(), e.getExpectedTokens().getMinElement() + " "
					+ e.getExpectedTokens().getMaxElement() + "- " + e.getInputStream().size(), null);
		}
		// Return the parse tree as a string
		return tree;
	}

	@Consumes(MediaType.TEXT_PLAIN)
	@Produces("application/postscript")
	public String getPS(@QueryParam("expression") String expression) {
		return null;
		
	}

	/**
	 * Parses a VTL expression and returns the corresponding parse tree as a string.
	 * 
	 * @param expression The VTL 2.0 expression to parse.
	 * @return The parse tree as a string.
	 * @throws RecognitionException In case of problem while parsing the exception.
	 */
	private String getTree(String expression) throws RecognitionException {

		// Create a VTL lexer instance
		VtlLexer lexer = new VtlLexer(CharStreams.fromString(expression));
		// Create a VTL parser instance
		VtlParser parser = new VtlParser(new CommonTokenStream(lexer));
		// Create a context for rule "start" (root rule for VTL)
		StartContext context = parser.start();
		// Return parse tree as a string
		return context.toStringTree(parser);
	}
}
