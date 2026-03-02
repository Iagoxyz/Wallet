package tech.backend.wallet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.backend.wallet.exception.WalletException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(WalletException.class)
    public ProblemDetail handlerWalletException(WalletException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var fieldErros = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Your request parameters didn't valitade.");
        pb.setProperty("invalid-params", fieldErros);

        return pb;
    }


    private record InvalidParam(String name, String reason) {}
}
