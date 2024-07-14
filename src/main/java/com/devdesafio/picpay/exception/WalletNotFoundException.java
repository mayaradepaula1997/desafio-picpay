package com.devdesafio.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends PicPayException{ //Exceção de carteira não encontrada


    private Long walletId;

    public WalletNotFoundException(Long walletId) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail(){

        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet not found");
        pb.setDetail("There is no wallet with id " + walletId + ".");

        return pb;


    }
}
