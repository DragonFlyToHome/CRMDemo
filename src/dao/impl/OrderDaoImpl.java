package dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import dao.OrderDao;
import entity.Customer;
import entity.Order;

@SuppressWarnings("unchecked")
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;


	@Override
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(order);
	}

	@Override
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(order);
	}

	@Override
	public List<Order> findOrdersByPage(Customer customer, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
		//where customer_id = customer.getCustomer_id()
		criteria.add(Restrictions.eq("customer", customer));
		return (List<Order>) hibernateTemplate.findByCriteria(criteria,
				(pageNo-1)*pageSize, pageSize);
	}

	@Override
	public int findTotalCount(Customer customer) {
		// TODO Auto-generated method stub

		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
			//Restrictions:封装查询条件
			criteria.add(Restrictions.eq("customer", customer))
			//setProjection:投影查询
			//Criteria通过投影查询进行聚合、分组
			.setProjection(Property.forName("order_id").count());
		return ((Long)(hibernateTemplate.findByCriteria(criteria)
				.get(0))).intValue();
	}

	@Override
	public Order findByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return hibernateTemplate.load(Order.class, orderId);
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(order);
	}

}
