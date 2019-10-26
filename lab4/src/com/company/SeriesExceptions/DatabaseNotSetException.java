package com.company.SeriesExceptions;

public class DatabaseNotSetException extends Exception {
    public DatabaseNotSetException(String message) {
        super(message);
    }
}
