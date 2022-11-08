package com.guialvesdev.springfoodapi.insfrastruture.repository.spec;

import com.guialvesdev.springfoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecs {

    public static Specification<Restaurante> comFreteGratis(){
        return new RestauranteComFreteGratisSpec();
    }


    public static Specification<Restaurante> comNomeSemelhante(String nome){
        return new RestauranteComNomeSemelhanteSpec(nome);
    }




}
