/**
 * 
 */
package dao;

import java.util.List;

import entity.Admin;
import entity.Module;
import entity.Role;

/**
 * @author DrafY
 * 2018年10月12日-下午2:48:43
 */
public interface AdminDao {

	void saveAdmin(Admin admin);
	
	//查询所有角色
	List<Role> findAllRoles();
	
	Admin findAdmin(Admin admin);
	
	Admin findAdminById(Integer admin_id);
	
	void deleteAdmin(Admin admin);
	
	void updateAdmin(Admin admin);

	//查询该管理员拥有的所有角色
	List<Role> findRoleByAdmin(Admin admin);
	//删除管理员拥有的角色
	void deleteModuleByAdmin(Admin admin);
	
	//新增管理员拥有的角色
	void saveModule(Admin admin,List<Role> roles);
}
