package com.example.Friend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.Friend.controller.Auth.ErrorHandler.EmailAlreadyExistsException;
import com.example.Friend.controller.Auth.ErrorHandler.EmailNotFoundException;
import com.example.Friend.controller.Auth.ErrorHandler.InvalidPasswordException;
import com.example.Friend.controller.Auth.ErrorHandler.UsernameAlreadyExistsException;



@ControllerAdvice
public class GlobalExceptionHandler {

    

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        String errorMessage = "L'adresse e-mail existe déjà dans la database : " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        String errorMessage = "Le nom d'utilisateur existe déjà dans la base de données : " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<String> handleEmailNotFoundException(EmailNotFoundException ex) {
        String errorMessage = "L'adresse e-mail n'est pas dans la database : " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException ex) {
        String errorMessage = "Le mot de passe est invalide" + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }
}
