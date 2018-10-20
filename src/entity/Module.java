/**
 * 
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author DrafY
 * 2018年10月12日-下午8:13:23
 */
@Entity
@Table(name="t_module")
public class Module {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer module_id;
	
	private Integer admin_id;
	
	private Integer role_id;

	public Integer getModule_id() {
		return module_id;
	}

	public void setModule_id(Integer mudole_id) {
		this.module_id = module_id;
	}

	public Integer getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	/**
	 * 
	 */
	public Module() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Mudule [mudule_id=" + module_id + ", admin_id=" + admin_id + ", role_id=" + role_id + "]";
	}
	
}
