package com.devdesafio.picpay.controller;


import com.devdesafio.picpay.controller.dto.TransferDto;
import com.devdesafio.picpay.entites.Transfer;
import com.devdesafio.picpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto){

        //Transfer resp = transferService.transfer(dto);
        var resp = transferService.transfer(dto);

        return ResponseEntity.ok(resp);


    }
}
