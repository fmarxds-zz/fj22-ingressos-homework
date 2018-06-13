package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.descontos.Desconto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class Ingresso {

    private Sessao sessao;
    private BigDecimal preco;

    public Ingresso(Sessao sessao, Desconto desconto) {
        this.sessao = sessao;
        this.preco = desconto.aplicarDescontoSobre(sessao.getPreco());
    }

    /** @deprecated Hibernate */
    public Ingresso(){}

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public BigDecimal getPreco() {
        return Optional.ofNullable(this.preco.setScale(2, RoundingMode.HALF_UP)).orElse(BigDecimal.ZERO);
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
