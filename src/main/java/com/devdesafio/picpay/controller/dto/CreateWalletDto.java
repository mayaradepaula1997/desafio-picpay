package com.devdesafio.picpay.controller.dto;

import com.devdesafio.picpay.entites.Wallet;
import com.devdesafio.picpay.entites.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDto(@NotBlank String fullName,
                              @NotBlank String cpfCnpj,
                              @NotBlank String email,
                              @NotBlank String password,
                              @NotNull WalletType.Enum walletType) {



    public Wallet toWallet(){     //método para fazer a conversão de DTO para classe/entite

        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }
}

//@NotBlank: Vai validar para ver se o campo não esta vazio

//@NotNull:Não pode vim NULO, walletType não é uma String

