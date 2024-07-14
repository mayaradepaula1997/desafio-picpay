package com.devdesafio.picpay.repository;

import com.devdesafio.picpay.entites.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
