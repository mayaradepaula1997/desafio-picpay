package com.devdesafio.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends PicPayException{ //Exceção de saldo insuficiente

    @Override
    public ProblemDetail toProblemDetail(){

        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Insufficient balance");
        pb.setDetail("You cannot transfer a value bigger tran your current balance");

        return pb;
    }
}
