package com.devdesafio.picpay.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferDto(@DecimalMin("0.01") @NotNull BigDecimal value,    //Valor que está sendo transferido
                          @NotNull Long payer,                              //Id de quem está fazendo o pagamento
                          @NotNull Long payee) {                            //Id de quem está recebendo o pagamento

                                                                            //@DecimalMin: Valor minino, uqe pode realizar a transferencia
}
