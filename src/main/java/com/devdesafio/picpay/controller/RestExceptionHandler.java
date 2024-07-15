package com.devdesafio.picpay.controller;


import com.devdesafio.picpay.exception.PicPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e){
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handMethodArgumentNotValidException(MethodArgumentNotValidException e){

         List fieldErros = e.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);


        pb.setTitle("Your request parameters didn't validate.");
        pb.setProperty("invalid-params", fieldErros);


        return pb;
    }

    private record InvalidParam(String name, String reason){}

}


//Dessa forma o Spring esta gerenciando as exceções, com isso podemos criar varias mensagens que exceçoes
//Sem precisar mexer no código



//RestExceptionHandler: Esta é a classe que atua como um manipulador global de exceções para todos os controladores REST da aplicação.