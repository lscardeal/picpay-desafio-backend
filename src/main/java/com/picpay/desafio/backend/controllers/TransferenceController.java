package com.picpay.desafio.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.desafio.backend.domain.dtos.transaction.TransferenceDTO;
import com.picpay.desafio.backend.domain.entity.transaction.Transference;
import com.picpay.desafio.backend.services.TransferenceService;

@RestController
@RequestMapping("/transference")
public class TransferenceController {

    @Autowired
    private TransferenceService service;

    public ResponseEntity<Transference> create(@RequestBody TransferenceDTO dto) {
        Transference transference = service.transfer(dto);
        return new ResponseEntity<Transference>(transference, HttpStatus.OK);
    }
    
}
