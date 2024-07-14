package com.devdesafio.picpay.service;

import com.devdesafio.picpay.controller.dto.CreateWalletDto;
import com.devdesafio.picpay.entites.Wallet;
import com.devdesafio.picpay.exception.WalletDataAlreadyExistsException;
import com.devdesafio.picpay.repository.WalletRepository;
import com.devdesafio.picpay.repository.WalletTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WallerService {


    private WalletRepository walletRepository;

    public WallerService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDb.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj ou Email já existe");
        }

        return walletRepository.save(dto.toWallet()); //toWallet: metodo que utilizamos do CreateWalletDto, para converte o DTO em entidade
    }
}