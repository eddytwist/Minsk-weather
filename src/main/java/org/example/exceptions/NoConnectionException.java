package org.example.exceptions;

/**
 * Can be thrown if the client has no connection.
 * @autor Eduard Ivanov
 * @version 1.0
 * @since 2020-10-09
 */
public class NoConnectionException extends Exception{
    public NoConnectionException(String message) {
        super(message);
    }
}
