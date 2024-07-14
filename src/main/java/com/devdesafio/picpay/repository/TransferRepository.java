package com.devdesafio.picpay.repository;

import com.devdesafio.picpay.entites.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
