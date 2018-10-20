/**
 * 
 */
package dao;

import java.util.List;

import entity.Admin;
import entity.Role;

/**
 * @author DrafY
 * 2018年10月12日-下午4:40:53
 */
public interface RoleDao {

	/**
	 * @param role
	 * @return
	 */
	List<Admin> findAdminByRole(Role role);

	/**
	 * @param role
	 * @return
	 */
	List<Role> findAllRole();

	/**
	 * @param role
	 * @return
	 */
	Role findRole(Role role);

	/**
	 * @param role_id
	 * @return
	 */
	Role findRole(Integer role_id);

	
}
