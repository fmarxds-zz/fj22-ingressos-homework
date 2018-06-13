package br.com.caelum.ingresso.rest;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ImdbClient {

    Logger logger = Logger.getLogger(DetalhesDoFilme.class);

    public Optional<DetalhesDoFilme> request(Filme filme){
        RestTemplate client = new RestTemplate();

        String titulo = filme.getNome().replace(" ", "+");
        String url = String.format("https://imdb-fj22.herokuapp.com/imdb?title=%s", titulo);

        try {
            DetalhesDoFilme detalhesDoFilme = client.getForObject(url, DetalhesDoFilme.class);
            return Optional.of(detalhesDoFilme);
        } catch (RestClientException e){
            logger.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

}
