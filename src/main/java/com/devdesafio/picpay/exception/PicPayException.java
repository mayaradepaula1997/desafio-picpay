package com.devdesafio.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;


    public class PicPayException extends RuntimeException{ //Essa é uma Exception padrão, que as putras exeçoes vão precisar extender

        public ProblemDetail toProblemDetail() {
            ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

            pb.setTitle("PicPay internal server error");

            return pb;
        }
    }


//ProblemDetail:padroniza como temos que retorna os erros padroes nos APIS

//O Spring vai pegar a exeção e vai jogar para a classe PicPayException e vai converte para o ProblemDetail