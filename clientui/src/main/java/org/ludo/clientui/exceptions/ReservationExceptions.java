package org.ludo.clientui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ReservationExceptions extends Exception {

    public ReservationExceptions(String s){
        super(s);
    }

}
