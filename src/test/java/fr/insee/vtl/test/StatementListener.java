package fr.insee.vtl.test;

import fr.insee.vtl.VtlBaseListener;
import fr.insee.vtl.VtlParser;
import fr.insee.vtl.VtlParser.ExprContext;

public class StatementListener extends VtlBaseListener {

    @Override
    public void enterStatement(VtlParser.StatementContext context) {

    	System.out.println("Entering 'statement' rule, text is " + context.getText());
    }

    @Override
    public void exitStatement(VtlParser.StatementContext context) {

    	System.out.println("Exiting 'statement' rule");
    }

    @Override
    public void enterExpr(ExprContext context) {

    	System.out.println("Entering 'expr' rule, text is " + context.getText());
    }

    @Override
    public void exitExpr(ExprContext context) {

    	System.out.println("Exiting 'expr' rule");
    }

}
