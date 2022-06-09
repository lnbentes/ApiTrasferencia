package br.com.lucasbentes.trasferencia.repository;

import br.com.lucasbentes.trasferencia.model.Cliente;
import br.com.lucasbentes.trasferencia.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = false)
public interface ContaRepository extends JpaRepository<Conta, Long> {

    public Optional<Conta> findByTipo(String tipo);

    public Optional<Conta> findByCliente(Optional<Cliente> cliente);

    @Modifying
    @Query("update conta c set c.saldo = c.saldo + ?1 where c.id = ?2")
    void setFixedSaldoFor(double  quantidade, Long id);

    @Modifying
    @Query("update conta c set c.saldo = c.saldo - ?1 where c.id = ?2")
    void setFixedSaldo(double  quant, Long id);

}
