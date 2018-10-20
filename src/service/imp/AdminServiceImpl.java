/**
 * 
 */
package service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AdminDao;
import dao.RoleDao;
import entity.Admin;
import entity.Role;
import service.AdminService;

/**
 * @author DrafY
 * 2018年10月12日-下午4:11:09
 */
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	@Qualifier("adminDao")
	private AdminDao adminDao;
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
	
	@Override
	public boolean regist(Admin admin) {
		// TODO Auto-generated method stub
		if (adminDao.findAdmin(admin)==null) {
			adminDao.saveAdmin(admin);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		if(adminDao.findAdmin(admin)==null) {
			System.err.println("找不到");
			return null;
		}else {
			return adminDao.findAdmin(admin);
		}	
	}

	@Override
	public Admin updateAdmin(Admin loginAdmin,Admin admin, List<Role> roles) {
		// TODO Auto-generated method stub
		loginAdmin.setUser_name(admin.getUser_name());
		loginAdmin.setPassword(admin.getPassword());
		loginAdmin.setRoles(roles);
		adminDao.updateAdmin(loginAdmin);
		return loginAdmin;
	}
	@Override
	public List<Role> showRoles(){
		
		return roleDao.findAllRole();
	}
	
}
