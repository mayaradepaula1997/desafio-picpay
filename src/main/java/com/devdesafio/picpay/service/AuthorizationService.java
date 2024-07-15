package com.devdesafio.picpay.service;

import com.devdesafio.picpay.client.AuthorizationClient;
import com.devdesafio.picpay.client.dto.AuthorizationResponse;
import com.devdesafio.picpay.controller.dto.TransferDto;
import com.devdesafio.picpay.entites.Transfer;
import com.devdesafio.picpay.exception.PicPayException;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService {


    private AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }


    public boolean isAuthorized(TransferDto transfer){
    var resp = authorizationClient.isAuthorized();


     if (resp.getStatusCode().isError()){
         throw  new PicPayException();

     }

     return resp.getBody().authorized();



    }


}
