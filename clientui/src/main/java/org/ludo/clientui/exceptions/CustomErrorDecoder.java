package org.ludo.clientui.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            ApiError error = mapper.readValue(response.body().asInputStream(), ApiError.class);

            switch (error.getMessage()){
                case"ReservationExceptions01":
                    return new ReservationExceptions("Un emprunt pour ce livre existe déjà pour cette utilisateur");

                case"ReservationExceptions02":
                    return new ReservationExceptions("Une réservation pour ce livre existe déjà pour cette utilisateur");

                case"ReservationExceptions03":
                    return new ReservationExceptions("La liste de réservations est complète");

                default: return defaultErrorDecoder.decode(methodKey, response);

            }
        } catch (IOException e) {
        e.printStackTrace();
    }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
