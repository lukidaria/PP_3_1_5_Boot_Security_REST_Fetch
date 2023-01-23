package ru.luki.pp_315_boot_sec_rest_fetch.exceptions;

public class NoSuchUserException extends RuntimeException{

    public NoSuchUserException(String message) {
        super(message);
    }
}
