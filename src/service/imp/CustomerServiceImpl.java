/**
 * 
 */
package service.imp;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CustomerDao;
import entity.Customer;
import entity.Order;
import entity.PageBean;
import service.CustomerService;

/**
 * @author DrafY
 * 2018年10月16日-上午10:53:47
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	@Qualifier("customerDao")
	private CustomerDao customerDao;
	
	private static final int PAGE_SIZE = 4;
	
	@Override
	public List<Customer> findAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customers = customerDao.findAllCustomer();
		return customers;
	}

	@Override
	public int findOrderNumByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		List<Order> orders = customerDao.findOrderByCustomer(customer);
		return orders.size();
	}

	@Override
	public List<Order> findOrderByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.findOrderByCustomer(customer);
	}
	@Override
	public List<Order> findOrderByCustomer(int customer_id) {
		// TODO Auto-generated method stub
		return customerDao.findOrderByCustomer(customerDao.findCustomer(customer_id));
	}

	@Override
	public PageBean<Customer> showCustomersByPage(int pageNo) {
		// TODO Auto-generated method stub
		PageBean<Customer> customers = new PageBean<Customer>();
		//总顾客数量customerTotal
		List<Customer> customerTotal = customerDao.findAllCustomer();
		//给分页添加 总长 页长
		customers.setTotalCount(customerTotal.size());
		customers.setPageSize(PAGE_SIZE);
		//计算总页
		int totalPage = (int)Math.ceil(
				(double)customers.getTotalCount()/customers.getPageSize());
		customers.setTotalPage(totalPage);
		customers.setPageNo(pageNo);
		customers.setPageContent(
				customerDao.queryPartCustomersByPage(pageNo, PAGE_SIZE));
		return customers;
	}

	
	@Override
	public PageBean<Order> showOrdersByPage(int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomerByCustomerName(int customer_id) {
		// TODO Auto-generated method stub
		customerDao.deleteCustomer(customerDao.findCustomer(customer_id));
	}

	@Override
	public boolean findCustomerExist(Customer customer) {
		// TODO Auto-generated method stub
		Customer querycustomer = customerDao.findCustomer(customer);
		if (querycustomer!=null) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.saveCustomer(customer);
	}

	@Override
	public Customer findCustomerById(Integer customer_id) {
		// TODO Auto-generated method stub
		return customerDao.findCustomer(customer_id);
	}
	
	
	
}
