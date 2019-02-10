package fr.insee.vtl.test;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.vtl.VtlBaseListener;
import fr.insee.vtl.VtlLexer;
import fr.insee.vtl.VtlParser;
import fr.insee.vtl.VtlParser.CalcClauseContext;
import fr.insee.vtl.VtlParser.CalcClauseItemContext;
import fr.insee.vtl.VtlParser.ExprContext;

public class StatementListener extends VtlBaseListener {

	protected static Logger logger = LogManager.getLogger();

    @Override
    public void enterStatement(VtlParser.StatementContext context) {

    	logger.info("Entering 'statement' rule, text is " + context.getText());
    	this.logContext(context);
    }

    @Override
    public void exitStatement(VtlParser.StatementContext context) {

    	logger.info("Exiting 'statement' rule");
    }

    @Override
    public void enterExpr(ExprContext context) {

    	logger.info("Entering 'expr' rule, text is " + context.getText());
    	this.logContext(context);
    }

    @Override
    public void exitExpr(ExprContext context) {

    	logger.info("Exiting 'expr' rule");
    }

    @Override
    public void enterCalcClause(CalcClauseContext context) {

    	logger.info("Entering 'calcClause' rule, text is " + context.getText());
    	this.logContext(context);
    }

    @Override
    public void exitCalcClause(CalcClauseContext context) {

    	logger.info("Exiting 'calcClause' rule");
    }

    @Override
    public void enterCalcClauseItem(CalcClauseItemContext context) {

    	logger.info("Entering 'calcClauseItem' rule, text is " + context.getText());
    	this.logContext(context);
    }

    @Override
    public void exitCalcClauseItem(CalcClauseItemContext context) {

    	logger.info("Exiting 'calcClauseItem' rule");
    }

    private void logContext(ParserRuleContext context) {

    	logger.info("The statement correspond to tokens " + context.getSourceInterval().toString());
    	for (int childIndex = 0; childIndex < context.getChildCount(); childIndex++) {
    		ParseTree childTree = context.getChild(childIndex);
    		logger.info("Child number " + childIndex + " has type " + childTree.getClass() + " and text " + childTree.getText());
    	}
    	for (TerminalNode terminal : context.getTokens(VtlLexer.ASSIGN)) logger.info("Terminal assignments: " + terminal.getText());
    }
}
