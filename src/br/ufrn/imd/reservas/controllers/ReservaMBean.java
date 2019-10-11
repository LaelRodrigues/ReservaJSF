package br.ufrn.imd.reservas.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.reservas.dominio.Reserva;
import br.ufrn.imd.reservas.dominio.Sala;
import br.ufrn.imd.reservas.repositorios.ReservaRepositorio;

@Named
@SessionScoped
public class ReservaMBean implements Serializable {
	
	private Reserva reserva;
	
	private Sala sala;
	
	private DataModel<Reserva> reservasModel;
	
	@Inject
	private UsuarioMBean usuarioMBean;
	
	@Inject
	private ReservaRepositorio reservaRepositorio;
	
	public String novaReserva() {
		reserva = new Reserva();
		reserva.setSalacadastrada(sala);
		return "/pages/reserva/form.jsf";
	}	
	public String listarReservas() {
		reservasModel = new ListDataModel<Reserva> (reservaRepositorio.listarReservas());
		return "/pages/reserva/list.jsf";
	}
	public String cadastrarReserva() {
		reserva.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		reservaRepositorio.adicionar(reserva);
		reserva = new Reserva();
		reserva.setSalacadastrada(sala);
		return "/pages/reserva/form.jsf";
	}
	public String removerMaterial() {
		Reserva materialRemovido = reservasModel.getRowData();
		reservaRepositorio.remover(materialRemovido);
		return listarReservas();
	}
	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public DataModel<Reserva> getReservasModel() {
		return reservasModel;
	}

	public void setReservasModel(DataModel<Reserva> reservasModel) {
		this.reservasModel = reservasModel;
	}
	
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
