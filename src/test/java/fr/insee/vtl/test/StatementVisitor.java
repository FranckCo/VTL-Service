package fr.insee.vtl.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.vtl.VtlBaseVisitor;
import fr.insee.vtl.VtlParser.DatasetClauseContext;
import fr.insee.vtl.VtlParser.StatementContext;

public class StatementVisitor extends VtlBaseVisitor<Integer> {

	protected static Logger logger = LogManager.getLogger();

    @Override
    public Integer visitStatement(StatementContext context) {

    	logger.info("Visiting 'statement' rule, text is " + context.getText());

    	return context.getChildCount();
    }

    @Override
    public Integer visitDatasetClause(DatasetClauseContext context) {

    	logger.info("Visiting 'datasetClause' rule, text is " + context.getText());

    	return context.getChildCount();
    }
}
