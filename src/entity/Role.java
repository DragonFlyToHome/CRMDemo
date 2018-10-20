/**
 * 
 */
package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author DrafY
 * 2018年10月12日-下午2:38:20
 */
@Entity
@Table(name="t_role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer role_id;
	private String role_name;
	
	@ManyToMany(targetEntity=Admin.class,mappedBy="roles")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Admin> admins = new ArrayList<Admin>();
	
	/**
	 * 
	 */
	public Role() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param roleId
	 */
	public Role(Integer roleId) {
		// TODO Auto-generated constructor stub
		super();
		this.role_id = roleId;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public List<Admin> getAdmins() {
		return admins;
	}
	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}
	
	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name + "]";
	}
	
	
}
