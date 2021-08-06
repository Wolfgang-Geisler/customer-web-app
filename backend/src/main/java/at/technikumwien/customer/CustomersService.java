package at.technikumwien.customer;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomersService {
	private static final Logger LOGGER = Logger.getLogger(CustomersService.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	
	public Customers findById(long id) {
		LOGGER.info("findById() >> id=" + id);
		
		var customers = em.find(Customers.class, id);
		if (customers == null) {
			throw new EntityNotFoundException();
		}
		
		return customers;
	}

	public List<Customers> findAll() {
		LOGGER.info("findAll()");
		
		return em.createNamedQuery("Customers.selectAll", Customers.class)
			.getResultList();
	}
	
	public void removeById(long id) {
		LOGGER.info("removeById() >> id=" + id);

		var customers = findById(id);
		em.remove(customers);	// managed customers required
	}
	
	public Customers save(Customers customers) {
		LOGGER.info("save() >> customers=" + customers);

		if (customers.getId() == null) {
			// create
			em.persist(customers);
			return customers;
		}
		else {
			// update
			return em.merge(customers);
		}
	}
}




