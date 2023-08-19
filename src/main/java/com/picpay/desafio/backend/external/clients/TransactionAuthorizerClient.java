package com.picpay.desafio.backend.external.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.picpay.desafio.backend.domain.dtos.TransactionAuthorizationDTO;


@FeignClient(name = "Authorizer", url = "https://run.mocky.io")
public interface TransactionAuthorizerClient {

    @GetMapping(value = "/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6")
    TransactionAuthorizationDTO getAuthorization();
    
}
