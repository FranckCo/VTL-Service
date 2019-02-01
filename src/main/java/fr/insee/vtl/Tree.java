package fr.insee.vtl;

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
	 * Method handling HTTP GET requests. The request should be made by the client
	 * as "text/plain" media type.
	 * 
	 * @param expression
	 *            A VTL 2.0 expression.
	 * @return The parse tree corresponding to the expression, returned as a
	 *         text/plain response.
	 * @throws Exception
	 */
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt(@QueryParam("expression") String expression) throws Exception {
		String tree = null;
		try {
			//Decode URL 
			String decodedExpression = java.net.URLDecoder.decode(expression, "UTF-8");
			// Create a VTL lexer instance
			VtlLexer lexer = new VtlLexer(CharStreams.fromString(decodedExpression));
			// Create a VTL parser instance
			VtlParser parser = new VtlParser(new CommonTokenStream(lexer));
			// Create a context for rule "start" (root rule for VTL)
			StartContext context = parser.start();
			// Parse tree as a string
			tree = context.toStringTree(parser);
		} catch (RecognitionException e) {
			throw new VTLServiceException(400, e.getOffendingState(), e.getExpectedTokens().getMinElement() + " "
					+ e.getExpectedTokens().getMaxElement() + "- " + e.getInputStream().size(), null);
		}
		// Return the parse tree as a string
		return tree;
	}
}
