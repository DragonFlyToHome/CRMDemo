/**
 * 
 */
package dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.AdminDao;
import entity.Admin;
import entity.Role;

/**
 * @author DrafY
 * 2018年10月12日-下午2:49:25
 */
@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {
	
	
	
	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	public void ddd() {
		
	}
	
	@Override
	public void saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(admin);
	}

	@Override
	public Admin findAdmin(Admin admin) {
		// TODO Auto-generated method stub
		String hql = "from Admin where user_name = ? and password = ? ";
		List<Admin> admins = (List<Admin>) hibernateTemplate.find(hql, admin.getUser_name(),admin.getPassword());
		return admins.isEmpty()?null:admins.get(0);		
	}

	@Override
	public Admin findAdminById(Integer admin_id) {
		Admin admin = hibernateTemplate.get(Admin.class, admin_id);
		
		return admin;
	}

	@Override
	public void deleteAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Admin admin2 = findAdmin(admin);
		hibernateTemplate.delete(admin2);
		
	}

	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		hibernateTemplate.update(admin);
	}

	@Override
	public List<Role> findRoleByAdmin(Admin admin) {
		// TODO Auto-generated method stub	
		admin = findAdmin(admin);
		Admin admin2 = hibernateTemplate.get(Admin.class, admin.getAdmin_id());
		List<Role> roles = admin2.getRoles();
		return roles;
	}

	@Override
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		List<Role> roles = (List<Role>) hibernateTemplate.find("from Role");
		return roles;
	}

	@Override
	public void deleteModuleByAdmin(Admin admin) {
		// TODO Auto-generated method stub
		admin = findAdmin(admin);
		admin.getRoles().clear();
	}

	@Override
	public void saveModule(Admin admin,List<Role> roles) {
		// TODO Auto-generated method stub
		admin = findAdmin(admin);
		List<Role> roles2 = admin.getRoles();
		for (Role role : roles2) {
			System.out.println(role.getRole_name());
		}
		admin.getRoles().addAll(roles);	
	}

	
}
