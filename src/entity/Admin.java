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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author DrafY
 * 2018年10月12日-上午11:50:16
 */
@Entity
@Table(name="t_admin")
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer admin_id;
	private String user_name;
	private String password;
	
	
	//只操作中间表不加Cascade,通过操作集合来操作中间表
	//多对多默认orphanRemoval=true
	@ManyToMany(targetEntity=Role.class)
	@JoinTable(name="t_module",
				joinColumns= {@JoinColumn(name="admin_id")},
				inverseJoinColumns={@JoinColumn(name="role_id")}
			)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> roles = new ArrayList<Role>();
	
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", user_name=" + user_name + ", password=" + password + "]";
	}
}
