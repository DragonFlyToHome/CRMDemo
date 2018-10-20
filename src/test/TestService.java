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
import entity.Role;

/**
 * @author DrafY
 * 2018年10月12日-下午4:20:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class TestService {


	@Test
	//@Transactional
	public void test() {
		
	}
	@Test
	public void test1() {
		
	}
	@Test
	public void testCustomer() {
		
	}
}
