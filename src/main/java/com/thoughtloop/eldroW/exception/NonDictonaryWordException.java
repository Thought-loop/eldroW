package com.thoughtloop.eldroW.exception;

public class NonDictonaryWordException extends Exception{

    private static final long serialVersionUID = 1L;

    public NonDictonaryWordException(){ super("**The word is not on the approved list of words**");}

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}


