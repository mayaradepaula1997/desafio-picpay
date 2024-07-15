package com.devdesafio.picpay.client;


import com.devdesafio.picpay.client.dto.AuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(

        name = "AuthorizationClient",
        url = "${client.authorization-service.url}"  // pegar o valor que está em application.propertied
)
public interface AuthorizationClient {


    @GetMapping   //Esse métedo vai fazer um GET na URL e vai retorna dentro do ResponseEntity aquele Booleano
    ResponseEntity<AuthorizationResponse> isAuthorized();
}
