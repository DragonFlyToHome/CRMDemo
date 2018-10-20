/**
 * 
 */
package dao;

import java.util.List;

import entity.Customer;
import entity.Order;

/**
 * @author DrafY
 * 2018年10月16日-上午10:02:46
 */
public interface OrderDao {

	/**
	 * @param order
	 */
	void saveOrder(Order order);

	/**
	 * @param order
	 */
	void deleteOrder(Order order);

	/**
	 * @param customer
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Order> findOrdersByPage(Customer customer, Integer pageNo, Integer pageSize);

	/**
	 * @param customer
	 * @return
	 */
	int findTotalCount(Customer customer);

	/**
	 * @param order
	 * @return
	 */
	Order findByOrderId(int orderId);
	
	void updateOrder(Order order);

}
