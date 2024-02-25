package com.luisilva.transactionschedulerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentAtTheDatabaseException extends RuntimeException {

    public NoContentAtTheDatabaseException(Class<?> aClass) {
        super("There is no content at the database of type: " + aClass.getName());
    }

    public NoContentAtTheDatabaseException(Class<?> aClass, int id) {
        super("There is no content at the database of type: " + aClass.getName() + " with client account id: " + id);
    }
}
