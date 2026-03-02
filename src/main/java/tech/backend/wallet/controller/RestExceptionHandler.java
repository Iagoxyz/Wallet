package tech.backend.wallet.controller;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.backend.wallet.exception.WalletException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(WalletException.class)
    public ProblemDetail handlerWalletException(WalletException e) {
        return e.toProblemDetail();
    }
}
