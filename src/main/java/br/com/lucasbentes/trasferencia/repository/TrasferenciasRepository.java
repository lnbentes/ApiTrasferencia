package br.com.lucasbentes.trasferencia.repository;

import br.com.lucasbentes.trasferencia.model.Conta;
import br.com.lucasbentes.trasferencia.model.Trasferencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = false)
public interface TrasferenciasRepository extends JpaRepository<Trasferencias, Long> {

   public List<Trasferencias> findByContaIn(Conta conta);

}
