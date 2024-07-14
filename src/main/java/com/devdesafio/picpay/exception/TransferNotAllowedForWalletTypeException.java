package com.devdesafio.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedForWalletTypeException extends PicPayException { //transferencia não permitida
                                                                                //para esse tipo de carteira

    @Override
    public ProblemDetail toProblemDetail(){

        ProblemDetail pb =  ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("This wallet type is noy allowed to transfer.");

        return pb;
    }
}
