package com.pleshkov.exceptions;

/**
 * Ошибка, возникающая при парсинге XML
 * @author pleshkov on 03.10.2018.
 */
public class XMLParseException extends Exception {

    public XMLParseException(String message) {
        super(message);
    }

    public XMLParseException(String message, Throwable cause){
        super(message, cause);
    }

    public XMLParseException(Throwable cause){
        super(cause);
    }
}
