/**
 * 
 */
package test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dao.AdminDao;
import dao.CustomerDao;
import dao.RoleDao;
import dao.impl.AdminDaoImpl;
import dao.impl.RoleDaoImpl;
import entity.Admin;
import entity.Customer;
import entity.Order;
import entity.Role;

/**
 * @author DrafY
 * 2018年10月12日-下午4:20:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})

public class TestDao {

	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
	
	@Autowired
	@Qualifier("customerDao")
	private CustomerDao customerDao;
	
	@Test
	//@Transactional
	public void test() {
		Admin admin = new Admin();
		admin.setUser_name("黄山");
		admin.setPassword("12345");
		Role role = new Role();
		role.setRole_id(2);
		admin.getRoles().add(roleDao.findRole(role));
		System.out.println(admin.getRoles().toString());
		//adminDao.saveAdmin(admin);
		//Admin admin2 = adminDao.findAdmin(admin);
		//System.out.println(admin2);
		System.out.println(adminDao.findRoleByAdmin(admin));
	}
	@Test
	public void test1() {
		Admin admin = new Admin();
		admin.setUser_name("黄山");
		admin.setPassword("12345");
		adminDao.saveAdmin(admin);
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role = roleDao.findRole(3);
		roles.add(role);
		adminDao.saveModule(admin, roles);
	} 
	@Test
	public void testCustomer() {
		Customer customer = new Customer();
		customer.setCustomer_id(1);
		customer.setCustomer_name("龙城");
		customer.setPhone_num("12136612");
		//customerDao.saveCostomer(customer);
		customerDao.updateCustomer(customer);
		System.out.println(customerDao.findCustomer(1));	
		System.out.println(customerDao.findOrderByCustomer(customer));
		System.out.println(customerDao.findOrderById(1));
	}
	@Test
	public void testCustomerOrder() {
		Customer customer = new Customer();
		customer.setCustomer_id(1);
		//customerDao.saveCostomer(customer);
		customer = customerDao.findCustomer(customer);
		System.out.println(customer);
		List<Order> orders = customer.getOrders();
		System.out.println(orders);
	}
}
