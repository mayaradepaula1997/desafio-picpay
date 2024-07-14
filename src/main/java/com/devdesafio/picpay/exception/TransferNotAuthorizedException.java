package com.devdesafio.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends PicPayException {   //Exceção de transferência não autorizada



    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer not authorized.");
        pb.setDetail("Authorization service not authorized this transfer.");

        return pb;
    }
}
