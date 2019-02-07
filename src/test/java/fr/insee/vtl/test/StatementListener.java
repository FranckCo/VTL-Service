package fr.insee.vtl.test;

import fr.insee.vtl.VtlBaseListener;
import fr.insee.vtl.VtlParser;

public class StatementListener extends VtlBaseListener {

    @Override
    public void enterStatement(VtlParser.StatementContext context) {

    	System.out.println("Entering statement");
    }

    @Override
    public void exitStatement(VtlParser.StatementContext context) {

    	System.out.println("Exiting statement");
    }

}
