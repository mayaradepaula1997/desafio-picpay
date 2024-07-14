package com.devdesafio.picpay.entites;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "td_transfer")
public class Transfer {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne      // Varios Wallets só pode ter uma transação
    @JoinColumn(name = "wallet_sender_id")
    private Wallet sender; //quem esta enviando

    @ManyToOne
    @JoinColumn(name = "wallet_receiver_id")
    private Wallet receiver; //quem esta recebendo

    @Column(name = "value")
    private BigDecimal value; // valor da transferencia


    public Transfer(){

    }

    public Transfer(Wallet sender, Wallet receiver, BigDecimal value) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Wallet getSender() {
        return sender;
    }

    public void setSender(Wallet sender) {
        this.sender = sender;
    }

    public Wallet getReceiver() {
        return receiver;
    }

    public void setReceiver(Wallet receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
