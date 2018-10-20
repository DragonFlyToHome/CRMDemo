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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author DrafY
 * 2018年10月12日-上午11:54:00
 */
@Entity
@Table(name="t_customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customer_id;
	private String customer_name;
	private String phone_num;
	private String address;
	private String self_pai_pic;
	
	@OneToMany(targetEntity=Order.class,mappedBy="customer",orphanRemoval=true)
	@Cascade(CascadeType.DELETE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JSONField()
	private List<Order> orders = new ArrayList<Order>();
	
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSelf_pai_pic() {
		return self_pai_pic;
	}
	public void setSelf_pai_pic(String self_pai_pic) {
		this.self_pai_pic = self_pai_pic;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customer_name=" + customer_name + ", phone_num=" + phone_num
				+ ", address=" + address + ", self_pai_pic=" + self_pai_pic + "]";
	}
	
}
