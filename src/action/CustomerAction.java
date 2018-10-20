/**
 * 
 */
package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Admin;
import entity.Customer;
import entity.PageBean;
import service.CustomerService;

/**
 * @author DrafY
 * 2018年10月16日-上午10:04:19
 */
@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport implements  ModelDriven<Customer>{

	
	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;
	
	private HttpSession session = ServletActionContext.getRequest().getSession();
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	
	private Customer customer = new Customer();
	
	private List<Customer> customers;
	
	private PageBean<Customer> usersPage = new PageBean<Customer>();
	
	private Integer pageNo = 1;
	
	@Action(value="customermain",results= {
			@Result(name="success",location="/index.jsp"),
			@Result(name="error",location="updateAdmin",type="redirectAction")
	})
	public String customerMain() {
		String pageNoUp = request.getParameter("pageNoUp");
		if(pageNoUp!=null) {
			pageNo = Integer.parseInt(pageNoUp);
		}
		List<Customer> customers = customerService.findAllCustomer();
		usersPage = customerService.showCustomersByPage(pageNo);
		return SUCCESS;
	}
	
	@Action(value="deletecustomer",results= {
			@Result(name="success",location="customermain",type="redirectAction"),
			@Result(name="error",location="updateAdmin",type="redirectAction")
	})
	public String deleteCustomer() {
		String targetcustomer = request.getParameter("targetcustomer");
		if (targetcustomer!=null) {
			customerService.deleteCustomerByCustomerName(Integer.parseInt(targetcustomer));
		}
		return SUCCESS;
	}
	
	@Action(value="updateCustomer",results= {
			@Result(name="success",location="customermain",type="redirectAction")
	})	
	public String updateCustomer() {
		for (Customer customer : customers) {
			customerService.saveCustomer(customer);
		}
		return SUCCESS;
	}
	
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public PageBean<Customer> getUsersPage() {
		return usersPage;
	}


	public void setUsersPage(PageBean<Customer> usersPage) {
		this.usersPage = usersPage;
	}

	
}
