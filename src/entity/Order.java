/**
 * 
 */
package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author DrafY
 * 2018年10月12日-下午2:42:07
 */
@Entity
@Table(name="t_order")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer order_id;
	
	private Double total_money;
	private Date purchase_date;
	
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="customer_id",insertable=false,updatable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Customer customer;
	
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Double getTotal_money() {
		return total_money;
	}
	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", customer=" + customer + ", total_money=" + total_money
				+ ", purchase_date=" + purchase_date + "]";
	}
	
	
}
