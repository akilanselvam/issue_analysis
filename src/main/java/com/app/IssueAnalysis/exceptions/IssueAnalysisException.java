package com.app.IssueAnalysis.exceptions;

public class IssueAnalysisException extends RuntimeException {
    public IssueAnalysisException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public IssueAnalysisException(String exMessage) {
        super(exMessage);
    }
}
