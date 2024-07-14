package com.devdesafio.picpay.entites;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_wallet_type")
public class WalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrition;

    public WalletType(){  // construtor vazio

    }

    public WalletType(Long id, String description) { //construtor com argumentos
        this.id = id;
        this.descrition = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }





    public enum Enum{   //Criar a função ENUM, porque a carteira possui dois tipos : Usuario e Logista

        USER(1L, "user"), // As opçoes de tipo de carteira
        MERCHANT(2L, "marchant");

        Enum(Long id, String description){
            this.id = id;
            this.description = description;

        }

        private Long id;
        private String description;


        public WalletType get(){  //Quando eu chamar o método "get", retorno o WalletType, passando o id e description
            return new WalletType(id, description);

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletType that = (WalletType) o;
        return Objects.equals(id, that.id) && Objects.equals(descrition, that.descrition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descrition);
    }
}
