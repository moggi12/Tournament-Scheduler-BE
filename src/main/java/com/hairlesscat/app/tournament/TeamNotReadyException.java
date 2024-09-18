package com.tournament.app.tournament;

public class TeamNotReadyException extends Exception {
    public TeamNotReadyException(String message) {
        super(message);
    }
}
