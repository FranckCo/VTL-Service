package fr.insee.vtl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TreeTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {

        // Start the server
        server = Main.startServer();
        // Create the client and web target
        Client client = ClientBuilder.newClient();
        target = client.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the tree corresponding to the test expression is sent in the response.
     */
    @Test
    public void testGetIt() {

    	String expression = "DS_r := DS_1[calc Me_2 := upper(Me_1)]";
    	String expected = "(start (statement (varID DS_r) := (expr (exprAtom (ref (varID DS_1))) [ (datasetClause (calcClause calc (calcClauseItem (componentID Me_2) := (calcExpr (expr (exprAtom upper ( (expr (exprAtom (ref (varID Me_1)))) ))))))) ])) <EOF>)";
        String responseMsg = target.path("tree").queryParam("expression", expression).request().get(String.class);
        assertEquals(expected, responseMsg);
    }
}
