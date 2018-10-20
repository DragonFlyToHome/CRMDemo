/**
 * 
 */
package dao.impl;

import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.CustomerDao;
import entity.Customer;
import entity.Order;

/**
 * @author DrafY
 * 2018年10月13日-下午2:48:32
 */
@Repository("customerDao")
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	
	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(customer.getCustomer_id()==null) {
			hibernateTemplate.save(customer);
		}else {
			updateCustomer(customer);
		}
		
	}
	
	@Override
	public Customer findCustomer(Integer customer_id) {
		// TODO Auto-generated method stub
		String hql = "from Customer where customer_id = ?";
		List<Customer> customers = (List<Customer>) hibernateTemplate.find(hql, customer_id);	
		return customers.isEmpty()?null:customers.get(0);
	}
	
	public Customer findCustomer(String customer_name) {
		String hql = "from Customer where customer_name = ?";
		List<Customer> customers = (List<Customer>) hibernateTemplate.find(hql, customer_name);	
		return customers.isEmpty()?null:customers.get(0);
	}


	/**
	 * 根据ID或姓名查询用户
	 */
	@Override
	public Customer findCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if (customer.getCustomer_id()!=null) {
			customer = findCustomer(customer.getCustomer_id());
		} else {
			customer = findCustomer(customer.getCustomer_name());
		}
		return customer;
	}
	
	/**
	 * 删除Customer
	 */
	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customer = findCustomer(customer);
		hibernateTemplate.delete(customer);
	}

	@Override
	public void deleteCustomer(List<Customer> customers) {
		// TODO Auto-generated method stub
		for (Customer customer : customers) {
			deleteCustomer(customer);
		}
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(customer);
	}

	@Override
	public List<Order> findOrderByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customer=findCustomer(customer);
		return customer.getOrders();
	}

	@Override
	public Order findOrderById(Integer order_id) {
		// TODO Auto-generated method stub
		return (Order)hibernateTemplate.get(Order.class, order_id);
	}

	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customers = (List<Customer>) hibernateTemplate.find("from Customer");
		return customers;
	}

	@Override
	public List<Customer> queryPartCustomersByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		List<Customer> partCustomers = (List<Customer>) hibernateTemplate.execute(new HibernateCallback<Object>() {

			@Override
			public List doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Customer";	
				return session.createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
			}
		});
		
		return partCustomers;
	}


}
