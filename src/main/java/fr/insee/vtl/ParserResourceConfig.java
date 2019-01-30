package fr.insee.vtl;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("parser")
public class ParserResourceConfig extends ResourceConfig {
    public ParserResourceConfig() {
        packages("fr.insee.vtl");
    }
}
