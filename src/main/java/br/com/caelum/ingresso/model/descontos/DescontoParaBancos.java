package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoParaBancos implements Desconto {
    @Override
    public BigDecimal aplicarDescontoSobre(BigDecimal preco) {
        return preco.multiply(BigDecimal.valueOf(0.7));
    }
}
