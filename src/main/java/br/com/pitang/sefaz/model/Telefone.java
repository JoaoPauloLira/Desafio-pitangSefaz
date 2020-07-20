package br.com.pitang.sefaz.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.pitang.sefaz.model.enums.Tipo;


@Embeddable
public class Telefone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int ddd;
	private String numero;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	public Telefone() {
	}
	
	public Telefone(int ddd, String numero, Tipo tipo) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
	}

	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ddd;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Telefone other = (Telefone) obj;
		if (ddd != other.ddd)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Telefone [ddd=" + ddd + ", numero=" + numero + ", tipo=" + tipo + "]";
	}
	
	
	
}
