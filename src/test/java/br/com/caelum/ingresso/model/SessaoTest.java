package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SessaoTest {
	
	Sala sala;
	Filme filme;
	BigDecimal valorTotalDaSala;

	@Before
	public void inicializa() {
		
		sala = new Sala("IMAX", BigDecimal.valueOf(10));
		filme = new Filme("Star Wars", Duration.ofMinutes(120), "SCI-FI", BigDecimal.valueOf(5));
		
		valorTotalDaSala = sala.getPreco().add(filme.getPreco());
		
	}
	
	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
		
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
		
		Assert.assertEquals(valorTotalDaSala.setScale(2, RoundingMode.HALF_UP), sessao.getPreco());
		
	}
}
