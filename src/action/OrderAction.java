/**
 * 
 */
package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Customer;
import entity.MyPropertyFilter;
import entity.Order;
import entity.OrderCustomer;
import entity.PageBean;
import service.CustomerService;
import service.OrderService;

/**
 * @author DrafY
 * 2018年10月17日-下午4:37:30
 */
@Controller
@Namespace("/order")
@Scope("prototype")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport implements ModelDriven<Customer>{

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;
	
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	private Customer customer = new Customer();
	
	private PropertyFilter propertyFilter = 
			new MyPropertyFilter();
	
	private HttpServletResponse response = ServletActionContext.getResponse();
	
	@Action(value="showOrdersByPage")
	public void showOrdersByPage() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		request.setCharacterEncoding("utf-8");
		
		//获取Ajax传来的数据
		String Scustomer_id = request.getParameter("customer_id");
		String SpageNo = request.getParameter("pageNo");
		//System.out.println("requestAjax:"+Scustomer_id+":"+SpageNo);
		//查找到customer对应的orders，并封装为PageBean
		PageBean<OrderCustomer> orders = orderService.showOrdersByPage(
				customerService.findCustomerById(Integer.parseInt(Scustomer_id)), Integer.parseInt(SpageNo));	
		String ordersToJSON = JSON.toJSONString(orders,  propertyFilter , SerializerFeature.DisableCircularReferenceDetect);
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(ordersToJSON);
			//System.out.println("ordersToJSON:"+ordersToJSON);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Action(value="deleteOrder")
	public void DeleteOrder() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		request.setCharacterEncoding("utf-8");
		
		//获取Ajax传来的数据
		String Sorder_id = request.getParameter("orderId");
		orderService.deleteOrder(Integer.parseInt(Sorder_id));
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(SUCCESS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Action(value="showOrderDetail")
	public void showOrderDetail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		request.setCharacterEncoding("utf-8");
		String Sorder_id = request.getParameter("orderId");
		
		Order order = orderService.showOrder(Integer.parseInt(Sorder_id));
		String ordersToJSON = JSON.toJSONString(order,  propertyFilter , SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.println(ordersToJSON);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	@Action(value="updateOrder")
	public void updateOrder() throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
			// 以字节流的形势读取JSON数据
			BufferedReader br = ServletActionContext.getRequest().getReader();
			String jsonStr = null;
			while((jsonStr = br.readLine())!=null) {
				sb.append(jsonStr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = sb.toString();
		try {
			// 解码
			json = URLDecoder.decode(json, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Order updateOrder = JSON.parseObject(json, Order.class);
		/*Order oldOrder = orderService.findOrderById(updateOrder.getOrder_id());
		oldOrder.setTotal_money(updateOrder.getTotal_money());
		oldOrder.setCustomer(updateOrder.getCustomer());*/
		orderService.updateOrder(updateOrder);
		try {
			response.getWriter().print(SUCCESS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
	
}
