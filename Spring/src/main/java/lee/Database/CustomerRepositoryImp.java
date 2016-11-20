package lee.Database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerRepositoryImp implements CustomerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  @Bean
	  public List<Customer> getAll() {
		  return entityManager.createQuery("Select u from Customer u").getResultList();
	  }
}
