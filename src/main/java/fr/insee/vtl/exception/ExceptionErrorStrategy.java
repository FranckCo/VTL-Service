package fr.insee.vtl.exception;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;

public class ExceptionErrorStrategy extends DefaultErrorStrategy {
	
    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        throw e;
    }
    
}