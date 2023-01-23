package ru.luki.pp_315_boot_sec_rest_fetch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    //данной аннотацией отмечается метод ответственный
    // за обработку исключений
    public ResponseEntity<UserIncorrectData> handleException(
            NoSuchUserException exception){
        UserIncorrectData data=new UserIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(
            Exception exception){
        UserIncorrectData data=new UserIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);


    }
}
