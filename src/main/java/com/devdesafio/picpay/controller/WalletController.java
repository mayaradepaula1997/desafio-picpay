package com.devdesafio.picpay.controller;


import com.devdesafio.picpay.controller.dto.CreateWalletDto;
import com.devdesafio.picpay.entites.Wallet;
import com.devdesafio.picpay.service.WallerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {


    private WallerService wallerService;

    public WalletController(WallerService wallerService) {
        this.wallerService = wallerService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> create(@RequestBody @Valid CreateWalletDto dto){

        Wallet wallet = wallerService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }
}
