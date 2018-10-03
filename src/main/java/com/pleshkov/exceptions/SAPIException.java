package com.pleshkov.exceptions;

/**
 * Ошибка сервиса
 * @author pleshkov on 03.10.2018.
 */
public class SAPIException extends Exception{
    public SAPIException(String message) {
        super(message);
    }

    public SAPIException(String message, Throwable cause){
        super(message, cause);
    }

    public SAPIException(Throwable cause){
        super(cause);
    }
}
