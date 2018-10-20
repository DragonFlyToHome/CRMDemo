/**
 * 
 */
package service;

import java.util.List;

import entity.Admin;
import entity.Role;

/**
 * @author DrafY
 * 2018年10月12日-下午4:10:33
 */
public interface AdminService {

	boolean regist(Admin admin);
	
	Admin login(Admin admin);
	
	List<Role> showRoles();

	/**
	 * @param loginAdmin
	 * @param admin
	 * @param roles
	 */
	Admin updateAdmin(Admin loginAdmin, Admin admin, List<Role> roles);
	
}
