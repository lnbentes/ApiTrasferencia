package br.com.lucasbentes.trasferencia.controller;

import br.com.lucasbentes.trasferencia.model.Cliente;
import br.com.lucasbentes.trasferencia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    //Metodo GET

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> getAll(){
        return ResponseEntity.ok(clienteRepository.findAll());
    }

}
