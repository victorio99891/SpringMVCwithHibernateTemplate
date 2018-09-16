package pl.wiktor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.wiktor.entity.Customer;
import pl.wiktor.enums.CustomerEnum;
import pl.wiktor.enums.OrderingEnum;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Customer> getSortedCustomers(CustomerEnum customerEnum, OrderingEnum orderingEnum) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> theQuery = currentSession.createQuery("from Customer order by " + customerEnum.toString().toLowerCase() + " " + orderingEnum.toString().toLowerCase(), Customer.class);
        return theQuery.getResultList();
    }

    @Override
    public Customer getCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Customer.class, id);
    }


    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        //THE SAME METHOD FOR SAVE OR UPDATE WHEN ENTITY HAVE PRIMARY KEY ITS UPDATED AND WHEN NOT THE ENTITY IS INSERTED INTO DB
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public void deleteUser(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(currentSession.get(Customer.class, id));
    }

    @Override
    public Long getNumberOfCustomers() {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Long) currentSession.createQuery("select count (cus.id) from Customer cus").uniqueResult();
    }

    @Override
    public List<Customer> getCustomersInRange(int beginIndex, int lastIndex) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);
        query.setFirstResult(beginIndex);
        query.setMaxResults(lastIndex);
        return query.getResultList();
    }

    @Override
    public List<Customer> getSortedPaginatedCustomers(CustomerEnum customerEnum, OrderingEnum orderingEnum, int beginIndex, int lastIndex) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> theQuery = currentSession.createQuery("from Customer order by " + customerEnum.toString().toLowerCase() + " " + orderingEnum.toString().toLowerCase(), Customer.class);
        theQuery.setFirstResult(beginIndex);
        theQuery.setMaxResults(lastIndex);
        return theQuery.getResultList();
    }

    @Override
    public List<Customer> getSearchResultCustomers(String constructedQuery) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> customersList = currentSession.createQuery(constructedQuery, Customer.class);
        return customersList.getResultList();
    }
}
