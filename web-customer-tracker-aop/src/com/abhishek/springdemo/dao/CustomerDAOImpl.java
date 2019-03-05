package com.abhishek.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abhishek.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the hibernate session factory
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//Get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery = session.createQuery("FROM Customer order by lastName", Customer.class);
		
		//retrieve the list by executing query
		List<Customer> customers = theQuery.getResultList();
		//return the result
		return customers;
		
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(theCustomer);		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Customer theCustomer = session.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		
		Customer theCustomer = session.load(Customer.class,theId);
		
		session.delete(theCustomer);
		
	}

	@Override
    public List<Customer> searchCustomers(String theSearchName) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);  
            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results   
        
        return customers;
        
    }

	

	

	
	

}
