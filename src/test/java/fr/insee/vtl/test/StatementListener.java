package fr.insee.vtl.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.vtl.VtlBaseListener;
import fr.insee.vtl.VtlParser;
import fr.insee.vtl.VtlParser.ExprContext;

public class StatementListener extends VtlBaseListener {

	protected static Logger logger = LogManager.getLogger();

    @Override
    public void enterStatement(VtlParser.StatementContext context) {

    	logger.info("Entering 'statement' rule, text is " + context.getText());
    	logger.info(context.getSourceInterval().toString());
    }

    @Override
    public void exitStatement(VtlParser.StatementContext context) {

    	logger.info("Exiting 'statement' rule");
    }

    @Override
    public void enterExpr(ExprContext context) {

    	logger.info("Entering 'expr' rule, text is " + context.getText());
    }

    @Override
    public void exitExpr(ExprContext context) {

    	logger.info("Exiting 'expr' rule");
    }

}
