package br.com.caelum.ingresso.descontos;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.model.descontos.DescontoParaEstudantes;
import br.com.caelum.ingresso.model.descontos.SemDesconto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;

public class DescontoTest {

    Sala sala;
    Filme filme;
    Sessao sessao;

    @Before
    public void inicializa(){
        sala = new Sala("IMAX", BigDecimal.valueOf(10));
        filme = new Filme("Star Wars", Duration.ofMinutes(120), "Ação", BigDecimal.valueOf(15));
        sessao = new Sessao(LocalTime.now(), sala, filme);
    }

    @Test
    public void deveConcederDescontoDe30PorCento(){
        Ingresso ingresso = new Ingresso(sessao, new DescontoParaBancos());
        BigDecimal precoEsperado = BigDecimal.valueOf(17.5);
        Assert.assertEquals(precoEsperado.setScale(2, RoundingMode.HALF_UP), ingresso.getPreco());
    }

    @Test
    public void deveConcederDescontoDe50PorCento(){
        Ingresso ingresso = new Ingresso(sessao, new DescontoParaEstudantes());
        BigDecimal precoEsperado = BigDecimal.valueOf(12.5);
        Assert.assertEquals(precoEsperado.setScale(2, RoundingMode.HALF_UP), ingresso.getPreco());
    }

    @Test
    public void naoDeveConcederDesconto(){
        Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
        BigDecimal precoEsperado = BigDecimal.valueOf(25);
        Assert.assertEquals(precoEsperado.setScale(2, RoundingMode.HALF_UP), ingresso.getPreco());
    }

}
