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

import dao.RoleDao;
import entity.Admin;
import entity.Role;

/**
 * @author DrafY
 * 2018年10月12日-下午4:42:52
 */
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao{

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Admin> findAdminByRole(Role role) {
		// TODO Auto-generated method stub	select o from 权限 o where o.角色.id =``
		Role role2 = hibernateTemplate.get(Role.class, role.getRole_id());
		List<Admin> admins = role2.getAdmins();
		return admins;
	}

	@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		List<Role> roles = (List<Role>) hibernateTemplate.find("from Role");
		return roles;
	}
	
	@Override
	public Role findRole(Role role) {
		List<Role> roles = (List<Role>)hibernateTemplate.find("from Role where role_id = ? ",role.getRole_id());
		Role resultRole = (Role)(roles.isEmpty()?null:roles.get(0));
		return resultRole;
	}
	@Override
	public Role findRole(Integer role_id) {
		List<Role> roles = (List<Role>)hibernateTemplate.find("from Role where role_id = ? ",role_id);
		Role resultRole = (Role)(roles.isEmpty()?null:roles.get(0));
		return resultRole;
	}
	
	
}
