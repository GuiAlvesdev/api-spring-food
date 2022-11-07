package com.guialvesdev.springfoodapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guialvesdev.springfoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.guialvesdev.springfoodapi.domain.model.Cozinha;
import com.guialvesdev.springfoodapi.domain.model.Restaurante;
import com.guialvesdev.springfoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
        Restaurante restaurante = restauranteRepository.buscar(restauranteId);

        if(restaurante != null){
            return ResponseEntity.ok(restaurante);

        }
        return ResponseEntity.notFound().build();

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED);
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId @RequestBody  Restaurante restaurante){
        try{
            Restaurante restauranteAtual = RestauranteRepository.buscar(restauranteId);
            if(restauranteAtual != null){
                BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

                restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);

            }
            return ResponseEntity.notFound().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


        @PatchMapping("{restauranteId}")
        public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
            @RequestBody Map<String, Object> campos){
            Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

            if(restauranteAtual == null){
                return ResponseEntity.notFound().build();

            }

            merge(campos, restauranteAtual);
            return atualizar(restauranteId, restauranteAtual);


        }

        private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino){
            ObjectMapper objectMapper = new ObjectMapper();\
            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) ->{
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino,novoValor );


            });
        }








    }











}