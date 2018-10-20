/**
 * 
 */
package service;

import java.util.List;
import java.util.TreeMap;

import entity.Customer;
import entity.Order;
import entity.PageBean;

/**
 * @author DrafY
 * 2018年10月13日-下午4:36:26
 */
public interface CustomerService {

	/**
	 * 查询全部的客户信息
	 * @return
	 */
	List<Customer> findAllCustomer();
	
	/**
	 * 查询每个客户的订单数
	 * @return
	 */
	int findOrderNumByCustomer(Customer customer);
	
	List<Order> findOrderByCustomer(Customer customer);
	List<Order> findOrderByCustomer(int customer_id);

	/**
	 * @param pageNo
	 * @param condition
	 * @return
	 */
	PageBean<Customer> showCustomersByPage(int pageNo);
	
	
	PageBean<Order> showOrdersByPage(int pageNo);
	/**
	 * 	删除
	 */
	void deleteCustomerByCustomerName(int customer_id);
	/**
	 * 查询改客户是否存在
	 * @param customer_id
	 * @return
	 */
	boolean findCustomerExist(Customer customer);
	
	/**
	 * 保存客户
	 */
	void saveCustomer(Customer customer);
	/**
	 * 根据ID查询顾客，持久化
	 * @return
	 */
	Customer findCustomerById(Integer customer_id);
}
