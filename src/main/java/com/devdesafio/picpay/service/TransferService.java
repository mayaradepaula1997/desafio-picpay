package com.devdesafio.picpay.service;

import com.devdesafio.picpay.controller.dto.TransferDto;
import com.devdesafio.picpay.entites.Transfer;
import com.devdesafio.picpay.entites.Wallet;
import com.devdesafio.picpay.exception.InsufficientBalanceException;
import com.devdesafio.picpay.exception.TransferNotAllowedForWalletTypeException;
import com.devdesafio.picpay.exception.TransferNotAuthorizedException;
import com.devdesafio.picpay.exception.WalletNotFoundException;
import com.devdesafio.picpay.repository.TransferRepository;
import com.devdesafio.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;  //Antes de transferir precisamos verificae se a pessoa que quer transferir
                                                //e a pessoa que vai receber, existe em no BD


    public TransferService(TransferRepository transferRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService,
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    @Transactional //A tranferencia só ira acontecer, se tudo der certo, se não as mudanças seram desfeitas
    public Transfer transfer(TransferDto transferDto) {

       /* Wallet sender = walletRepository.findById(transferDto.payer())  //verificar se a pessoa que quer tranferir e a pessoa que vai receber o valor, existe na BD
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        Wallet receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));*/


        var sender = walletRepository.findById(transferDto.payer())  //verificar se a pessoa que quer tranferir e a pessoa que vai receber o valor, existe na BD
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));


        validateTransfer(transferDto, sender); //metodo criado para validar transferência


        sender.debit(transferDto.value()); //se passar por todas as validações, vai realizar o debito e crédito
        receiver.credit(transferDto.value());

        //Transfer transfer = new Transfer(sender,receiver,transferDto.value());

        var transfer = new Transfer(sender,receiver,transferDto.value());



        walletRepository.save(sender);
        walletRepository.save(receiver);
        //Transfer transferResult = transferRepository.save(transfer);
        var transferResult = transferRepository.save(transfer);

        //envio da notificação
        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }





    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) { //se a transferencia não é permitida para esse tipo de carteira
            throw new TransferNotAllowedForWalletTypeException();
        }


        if (!sender.isBalancerEqualOrGreatherThan(transferDto.value())) {  //se o saldo não for o suficiente a transferencia não é permitida
            throw new InsufficientBalanceException();
        }


        if (!authorizationService.isAuthorized(transferDto)) { //se a transferencia não tiver autorizada
            throw new TransferNotAuthorizedException();
        }

    }
}
