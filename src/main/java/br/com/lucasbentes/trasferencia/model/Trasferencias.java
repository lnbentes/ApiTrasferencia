package br.com.lucasbentes.trasferencia.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "trasferencias")
public class Trasferencias {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTrasferencias;
	
	@UpdateTimestamp
	private LocalDateTime date;
	
	@ManyToOne
	@JsonIgnoreProperties("trasferencias")
	private Conta conta;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTrasferencias == null) ? 0 : idTrasferencias.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trasferencias other = (Trasferencias) obj;
		if (idTrasferencias == null) {
			if (other.idTrasferencias != null)
				return false;
		} else if (!idTrasferencias.equals(other.idTrasferencias))
			return false;
		return true;
	}

	public Long getIdTrasferencias() {
		return idTrasferencias;
	}

	public void setIdTrasferencias(Long idTrasferencias) {
		this.idTrasferencias = idTrasferencias;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
