package com.guialvesdev.springfoodapi.controller;


import com.guialvesdev.springfoodapi.domain.model.Cozinha;
import com.guialvesdev.springfoodapi.domain.model.Restaurante;
import com.guialvesdev.springfoodapi.insfrastruture.repository.spec.RestauranteSpecs;
import com.guialvesdev.springfoodapi.repository.CozinhaRepository;
import com.guialvesdev.springfoodapi.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome){
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }


    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome){
        return cozinhaRepository.findByNome(nome);
    }


    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial,BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }


    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesPorNome(String nome){
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }



    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome){


        return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis()));
    }





}
