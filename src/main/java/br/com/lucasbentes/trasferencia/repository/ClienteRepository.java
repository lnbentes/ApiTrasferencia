package br.com.lucasbentes.trasferencia.repository;

import br.com.lucasbentes.trasferencia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findByNome(String nome); //pesquisa por nome.
    public List<Cliente> findByNomeContainingIgnoreCase(String nome); //pesquisa por nome. //like do SQL
    public Optional<Cliente> findByCpf(String cpf); //pesquisa pelo CPF.

    public Optional<Cliente> findById(Long id); //pesquisa pelo ID
}
