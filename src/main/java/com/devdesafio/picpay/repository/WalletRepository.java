package com.devdesafio.picpay.repository;

import com.devdesafio.picpay.entites.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByCpfCnpjOrEmail(String cpfCnpj, String email); //método que retorna a função do Cpf e Email unicos

}
