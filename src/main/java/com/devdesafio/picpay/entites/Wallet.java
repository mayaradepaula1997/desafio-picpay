package com.devdesafio.picpay.entites;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="tb_wallet")
public class Wallet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name") //nome das colunas na tabela
    private String fullName;

    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @Column(name = "email", unique = true) //É uma campo unico na tabela
    private String email;

    @Column(name = "password")
    private  String password;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO; //maneira recomendada para armazenar valores monetarios, e ja iniciar com ZERO

    @ManyToOne                              //relacionamento entre tabelas
    @JoinColumn(name = "wallet_type_id") // coluna de relacionamento
    private WalletType walletType;

    public Wallet(){  // construtos vazio

    }

    public Wallet(String fullName, String cpfCnpj, String email, String password, WalletType walletType) {
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.walletType = walletType;           //construtor com os dados do DTO
    }

    public boolean isTransferAllowedForWalletType() {         //a transferência é permitida para o tipo de carteira
                                                              //método para saber se a transferencia é permitida ou não

        return this.walletType.equals(WalletType.Enum.USER.get());
    }



    public  boolean isBalancerEqualOrGreatherThan(BigDecimal value){   //método para verificar se o valor que se deseja transferir
                                                                       //não é menor que o saldo

        return this.balance.doubleValue() >= value.doubleValue();

    }

    public void debit(BigDecimal value) {  //método que subtrai o valor da transferencia
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) { //método que soma/adiciona o valor na conta que foi realizada a transferencia
        this.balance = this.balance.add(value);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFulName() {
        return fullName;
    }

    public void setFulName(String fulName) {
        this.fullName = fulName;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }


}
