/**
 * 
 */
package dao;

import java.util.List;
import java.util.TreeMap;

import entity.Order;
import entity.Customer;

/**
 * @author DrafY
 * 2018年10月13日-下午2:38:02
 */
public interface CustomerDao {

	//新增客户
	void saveCustomer(Customer customer);
	
	//删除客户
	void deleteCustomer(Customer customer);
	void deleteCustomer(List<Customer> customer);
	
	//修改客户
	void updateCustomer(Customer customer);
	
	//查找客户
	Customer findCustomer(Customer customer);
	//查找客户
	Customer findCustomer(Integer customer_id);
	//查找客户
	List<Customer> findAllCustomer();
	
	//查找客户的订单
	List<Order> findOrderByCustomer(Customer customer);
	
	//根据ID查找订单
	Order findOrderById(Integer order_id);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	List<Customer> queryPartCustomersByPage(int pageNo, int pageSize);
}
