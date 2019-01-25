package fr.insee.vtl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("tree")
public class Tree {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@QueryParam("expression") String expression) {

    	String tree = expression;

    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	// Create a lexer instance
    	Lexer lexer = null;
		try {
	    	Class<? extends Lexer> lexerClass = loader.loadClass("fr.insee.vtl.VtlLexer").asSubclass(Lexer.class);
			Constructor<? extends Lexer> lexerConstructor = lexerClass.getConstructor(CharStream.class);
			lexer = lexerConstructor.newInstance((CharStream)null);
		} catch (Exception e) {
			tree = "Error on lexer initialization: " + e.getMessage();
		}

		Class<? extends Parser> parserClass = null;
		Parser parser = null;
		try {
			parserClass = loader.loadClass("fr.insee.vtl.VtlParser").asSubclass(Parser.class);
			Constructor<? extends Parser> parserCtor = parserClass.getConstructor(TokenStream.class);
			parser = parserCtor.newInstance((TokenStream)null);
		} catch (Exception e) {
			tree = "Error on parser initialization: " + e.getMessage();
		}
        return tree;
    }
}
