package br.ufrn.imd.reservas.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrn.imd.reservas.dominio.Usuario;
import br.ufrn.imd.reservas.repositorios.UsuarioRepositorio;

@Named("usuarioMBean")
@SessionScoped
public class UsuarioMBean implements Serializable {
	
	@Inject
	private UsuarioRepositorio usuarioRepositorio;
	
	private Usuario usuario;
	
	private Usuario usuarioLogado;
		
	public UsuarioMBean() {
		usuario = new Usuario();
	}
	
	public String cadastrarUsuario() {
		usuarioRepositorio.adicionar(usuario);
		FacesMessage msg = new FacesMessage("Usuário cadastrado.");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage("", msg);
		usuario = new Usuario();
		return "/reserva/cadastro/cadastro.jsf";
	}

	public String logar() {
		Usuario usuarioBd = usuarioRepositorio.getUsuario(usuario.getLogin(), usuario.getSenha());
		if(usuarioBd != null) {
			usuarioLogado = usuarioBd;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
			return "/pages/index.jsf";
		}
		//não existe
		else {
			FacesMessage msg = new FacesMessage("Usuário não encontrado.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
			return null;
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
