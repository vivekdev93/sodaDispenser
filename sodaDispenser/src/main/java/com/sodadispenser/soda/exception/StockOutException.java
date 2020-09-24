package com.sodadispenser.soda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockOutException extends Exception {

    public StockOutException(String s) {
        super(s);
    }
}
