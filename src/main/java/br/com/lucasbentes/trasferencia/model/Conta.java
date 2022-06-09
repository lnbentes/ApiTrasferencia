package br.com.lucasbentes.trasferencia.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "conta")
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConta;
	
	private String tipo;
	
	private BigDecimal saldo;
	
	private BigDecimal limiteDeTrasferencia;
	
	@ManyToOne
	@JsonIgnoreProperties("conta")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "conta", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("conta")
	private List<Trasferencias> trasferencias;

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getLimiteDeTrasferencia() {
		return limiteDeTrasferencia;
	}

	public void setLimiteDeTrasferencia(BigDecimal limiteDeTrasferencia) {
		this.limiteDeTrasferencia = limiteDeTrasferencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Trasferencias> getTrasferencias() {
		return trasferencias;
	}

	public void setTrasferencias(List<Trasferencias> trasferencias) {
		this.trasferencias = trasferencias;
	}
}
