package br.ufrn.imd.reservas.dominio;

import java.sql.Time;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufrn.imd.reservas.dominio.Usuario;

@Entity
public class Reserva {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RESERVA")
	@SequenceGenerator(name="SEQ_RESERVA", sequenceName="id_seq_reserva", allocationSize=1)
	private int id;
	
	private String codigo;
	
	@Temporal(TemporalType.DATE)
	private Date diaReserva;
	
	@Temporal(TemporalType.DATE)
	private Date horarioInicial;
	
	@Temporal(TemporalType.DATE)
	private Date horarioFinal;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuarioCadastro;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_sala")
	private Sala salacadastrada;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getDiaReserva() {
		return diaReserva;
	}

	public void setDiaReserva(Date diaReserva) {
		this.diaReserva = diaReserva;
	}

	public Date getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(Date horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public Date getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(Date horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Sala getSalacadastrada() {
		return salacadastrada;
	}

	public void setSalacadastrada(Sala salacadastrada) {
		this.salacadastrada = salacadastrada;
	}
}
