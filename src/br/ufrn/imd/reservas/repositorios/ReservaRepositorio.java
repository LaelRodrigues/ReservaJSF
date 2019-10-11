package br.ufrn.imd.reservas.repositorios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.reservas.dominio.Reserva;


@Stateless
public class ReservaRepositorio {
	
	@PersistenceContext
    private EntityManager em;
	
	public Reserva adicionar(Reserva reserva) {
		if(reserva.getId() == 0)
			em.persist(reserva);
		else
			em.merge(reserva);
		return reserva;
	}
	
	public void remover(Reserva reserva) {
		reserva = em.find(Reserva.class, reserva.getId());
		em.remove(reserva);
	}
	
	@SuppressWarnings("unchecked")
	public List<Reserva> listarReservas() {
		return (List<Reserva>) em.createQuery("select r from Reserva r").getResultList();
	}	
	
}
