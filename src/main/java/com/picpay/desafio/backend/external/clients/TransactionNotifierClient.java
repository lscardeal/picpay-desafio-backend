package com.picpay.desafio.backend.external.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "Notifier", url = "http://o4d9z.mocklab.io")
public interface TransactionNotifierClient {

    @PostMapping(value = "/notify")
    void notifyTransaction();
    
}
