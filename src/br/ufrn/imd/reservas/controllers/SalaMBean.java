package br.ufrn.imd.reservas.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.reservas.dominio.Reserva;
import br.ufrn.imd.reservas.dominio.Sala;
import br.ufrn.imd.reservas.repositorios.SalaRepositorio;

@Named
@SessionScoped
public class SalaMBean implements Serializable {
	
	@Inject
	private SalaRepositorio salaRepositorio;
	
	private DataModel<Sala> salasModel;
	
	private Sala sala;
	
	public SalaMBean () {
	}
	
	public String novaSala() {
		sala = new Sala();
		return "/pages/sala/form.jsf";
	}	
	
	public String cadastrarSala() {
		salaRepositorio.adicionar(sala);
		FacesMessage msg = new FacesMessage("Sala cadastrada.");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage("", msg);
		sala = new Sala();
		salasModel = new ListDataModel<Sala> (salaRepositorio.listarSalas());
		return "/pages/sala/form.jsf";
	}
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public String listaSalas() {
		salasModel = new ListDataModel<Sala> (salaRepositorio.listarSalas());
		return "/pages/sala/list.jsf";
	}

	public DataModel<Sala> getSalasModel() {
		salasModel = new ListDataModel<Sala> (salaRepositorio.listarSalas());
		return salasModel;
	}

	public void setSalasModel(DataModel<Sala> salasModel) {
		this.salasModel = salasModel;
	}
}
