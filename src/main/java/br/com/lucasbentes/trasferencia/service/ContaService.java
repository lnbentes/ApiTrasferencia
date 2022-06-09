package br.com.lucasbentes.trasferencia.service;

import br.com.lucasbentes.trasferencia.model.Cliente;
import br.com.lucasbentes.trasferencia.model.Conta;
import br.com.lucasbentes.trasferencia.repository.ClienteRepository;
import br.com.lucasbentes.trasferencia.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Conta> trasferenciaDinheiro(Optional<Cliente> clienteEnvia, Optional<Cliente> clienteRecebe, BigDecimal valor){
        Optional<Cliente> clienteE = clienteRepository.findByCpf(clienteEnvia.get().getCpf());
        Optional<Cliente> clienteR = clienteRepository.findByCpf(clienteRecebe.get().getCpf());

        if(clienteE.isPresent() && clienteR.isPresent()){
            if(clienteE.get().getCpf() != clienteR.get().getCpf()){
                Optional<Conta> contaE = contaRepository.findByCliente(clienteE);
            }
        }

        return Optional.empty();
    }

}
