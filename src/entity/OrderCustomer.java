/**
 * 
 */
package entity;

import java.util.Date;

/**
 * @author DrafY
 * 2018年10月17日-下午8:57:21
 */
public class OrderCustomer {

	private Integer order_id;
	private String address;
	private Double total_money;
	private String customer_name;
	private Date purchase_date;
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getTotal_money() {
		return total_money;
	}
	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

}
