package br.ufrn.imd.reservas.repositorios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.reservas.dominio.Reserva;
import br.ufrn.imd.reservas.dominio.Sala;

@Stateless
public class SalaRepositorio {
	
	@PersistenceContext
    private EntityManager em;
	private List<Sala> salas;
	
	public Sala adicionar(Sala sala) {
		if(sala.getId() == 0)	
			em.persist(sala);
		else
			em.merge(sala);
		return sala;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sala> listarSalas() {
		salas = (List<Sala>) em.createQuery("select s from Sala s").getResultList();
		return salas;
	}	
	

	public List<Sala> getSalas() {
		return salas;
	}
	
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	
	@SuppressWarnings("unchecked")
	public  Sala getSala(int id) {		
		List<Sala> retorno = em.createQuery("from Sala u where u.id='" + id + "'")
				.getResultList();

		System.out.println(retorno.size());
		for (Sala u : retorno) {
			if (u.getId()==id) {
				return u;
			}
		}
		return null;
	}
}
