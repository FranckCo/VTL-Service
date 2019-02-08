package fr.insee.vtl.test;

import fr.insee.vtl.VtlBaseVisitor;
import fr.insee.vtl.VtlParser.StatementContext;

public class StatementVisitor extends VtlBaseVisitor<Integer> {

    @Override
    public Integer visitStatement(StatementContext context) {

    	System.out.println("Visiting 'statement' rule, text is " + context.getText());

    	return context.getChildCount();
    }
}
