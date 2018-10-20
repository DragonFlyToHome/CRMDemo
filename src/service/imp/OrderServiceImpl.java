/**
 * 
 */
package service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.OrderDao;
import entity.Customer;
import entity.Order;
import entity.OrderCustomer;
import entity.PageBean;
import service.OrderService;

/**
 * @author DrafY
 * 2018年10月17日-下午5:39:11
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	@Qualifier("orderDao")
	private OrderDao orderDao;
	
	private static final Integer PAGE_SIZE = 4;
	
	@Override
	public PageBean<OrderCustomer> showOrdersByPage(Customer customer, Integer pageNo) {
		// TODO Auto-generated method stub
		PageBean<OrderCustomer> pageBeanOrder = new PageBean<OrderCustomer>();
		pageBeanOrder.setPageNo(pageNo);
		int totalCount = orderDao.findTotalCount(customer);
		pageBeanOrder.setTotalCount(totalCount);
		int totalPages = (int) Math.ceil(
				totalCount*1.0 / PAGE_SIZE);
		pageBeanOrder.setTotalPage(totalPages);
		List<OrderCustomer> orderCustomers = new ArrayList<OrderCustomer>(); 
		//OrderCustomer orderCustomer = new OrderCustomer();	
		List<Order> Lorders = orderDao.findOrdersByPage(customer, pageNo, PAGE_SIZE);	
		for (Order order : Lorders) {
			OrderCustomer orderCustomer = new OrderCustomer();
			orderCustomer.setOrder_id(order.getOrder_id());
			orderCustomer.setAddress(customer.getAddress());
			orderCustomer.setTotal_money(order.getTotal_money());
			orderCustomer.setCustomer_name(customer.getCustomer_name());
			orderCustomers.add(orderCustomer);
		}
		pageBeanOrder.setPageContent(orderCustomers);
		return pageBeanOrder;
	}

	@Override
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.saveOrder(order);
	}

	@Override
	public Order showOrder(int orderId) {
		// TODO Auto-generated method stub
		return orderDao.findByOrderId(orderId);
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		Order order = showOrder(orderId);
		orderDao.deleteOrder(order);
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.updateOrder(order);
	}



}
