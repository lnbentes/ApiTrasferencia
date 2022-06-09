package br.com.lucasbentes.trasferencia.service;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Optional;

import br.com.lucasbentes.trasferencia.model.Cliente;
import br.com.lucasbentes.trasferencia.model.ClienteLogin;
import br.com.lucasbentes.trasferencia.model.Conta;
import br.com.lucasbentes.trasferencia.repository.ClienteRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /* Metodo de Cadastro
    * 1. Verifica se a um cliente no banco de dados;
    * 2. Pega a senha colocada pelo cliente criptografo;
    * 3. Armazena os dados do cliente;
    * */
    public Optional<Cliente> cadastraCliente(Cliente cliente){
        if(clienteRepository.findByCpf(cliente.getCpf()).isPresent()){
            return Optional.empty();
        }
        cliente.setSenha(criptografarSenha(cliente.getSenha()));
        return Optional.of(clienteRepository.save(cliente));
    }

    /* Metodo de login
     * 1. Verifica se os dados do clienteLogin está presente no banco de dados;
     * 2. Se o cliente for localizado os dados dele será repassado para o clienteLogin;
     * 3. Se não retorna vazio;
     * */
    public Optional<ClienteLogin> autenticarCliente(Optional<ClienteLogin> clienteLogin){
        Optional<Cliente> cliente = clienteRepository.findByCpf(clienteLogin.get().getCpf());

        if(cliente.isPresent()) {
            if(comparaSenhas(clienteLogin.get().getSenha(), cliente.get().getSenha())) {

                clienteLogin.get().setIdCliente(cliente.get().getIdCliente());
                clienteLogin.get().setDataNascimento(cliente.get().getDataNascimento());
                clienteLogin.get().setCep(cliente.get().getCep());
                clienteLogin.get().setToken(geradorBasicToken(clienteLogin.get().getCpf(), clienteLogin.get().getSenha()));
                clienteLogin.get().setSenha(cliente.get().getSenha());
                clienteLogin.get().setTipo(cliente.get().getTipo());

                return clienteLogin;
            }
        }
        return Optional.empty();
    }

    /* Metodo de alterar a senha do cliente
     * 1. Verifica se os dados do clienteLogin está presente no banco de dados;
     * 2. Verifica se a nova senha e diferente da antiga
     * 3. Se for diferente, a nova senha e adicionada e salvada no banco de dados
     * */
    public Optional<Cliente> atualizarCliente (Cliente cliente) {

        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            Optional<Cliente> clienteAtualizado = clienteRepository.findByCpf(cliente.getCpf());

            if ((clienteAtualizado.isPresent()) && (clienteAtualizado.get().getCpf() != cliente.getCpf())) {
                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Já existente",null);
            }
            cliente.setSenha(criptografarSenha(cliente.getSenha()));
            return Optional.of(clienteRepository.save(cliente));
        }
        return Optional.empty();
    }


    // Metodos de criptografia, compara as senha e de gerar o token
    private String criptografarSenha(String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    private boolean comparaSenhas(String senhaEntrada, String senhaBanco){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaEntrada, senhaBanco);
    }

    private String geradorBasicToken(String usuario, String senha){
        String token = usuario + ":" + senha;
        // Pegar o token e converte para binario usando o protocolo us-ascii que informa que sao as letra de teclado
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(tokenBase64);
    }


}
