/**
 * 
 */
package service;


import entity.Customer;
import entity.Order;
import entity.OrderCustomer;
import entity.PageBean;

/**
 * @author DrafY
 * 2018年10月17日-下午5:36:47
 */
public interface OrderService {

		/**
		 * 查看某个客户的订单列表（分页）
		 * @param customer 某个客户
		 * @param pageNo 页码
		 * @param pageSize 页面大小
		 * @return 分页的所有数据
		 */
		PageBean<OrderCustomer> showOrdersByPage(
				Customer customer,Integer pageNo);
		
		/**
		 * 新增订单
		 * @param order
		 */
		void saveOrder(Order order);
		
		/**
		 * 展示订单详情
		 * @param orderId 主键
		 * @return 对应的Order对象
		 */
		Order showOrder(int orderId);
		
		/**
		 * 删除订单
		 * @param orderId 要删除的Order主键
		 */
		void deleteOrder(int orderId);
		
		/**
		 * 修改订单
		 * @param order 要修改的Order
		 */
		void updateOrder(Order order);

}
