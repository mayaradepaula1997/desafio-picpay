package com.devdesafio.picpay.config;

import com.devdesafio.picpay.entites.WalletType;
import com.devdesafio.picpay.repository.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner { //vai executar esse metodo assim que o programa iniciar

    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) { //injeção de dependencia via construtor
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Enum.values())
                .forEach(walletType -> walletTypeRepository.save(walletType.get())); //Para cada valor do enum, ele chama walletTypeRepository.save()
                                                                                    // para salvar esses valores no banco de dados.

    }
}

//Quando o projeto inicializar, apartir da entidade WalletType, vou chamar o ENUM e pegar todos os valores de ENUM
//Transforma isso em uma strem e fazer o FOREACH, para cada valor do enum, chamar o método save do walletTypeRepository
// para salvar os dados no banco de dados.
//passando o walletType.get (método que criamos, que tranforma uma ENUM em uma classe)



// Ele injeta um repositório (WalletTypeRepository) e usa isso para salvar os valores de um enum (WalletType.Enum)
// no banco de dados durante a inicialização da aplicação.


//Adicionar novos valores ao enum WalletType.
// Enum resultará na inclusão automática desses novos valores no banco de dados na próxima inicialização da aplicação.
