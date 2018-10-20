/**
 * 
 */
package action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Admin;
import entity.Role;
import service.AdminService;

/**
 * @author DrafY
 * 2018年10月13日-下午5:17:45
 */
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class AdminAction extends ActionSupport implements  ModelDriven<Admin>{

	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;
	
	private HttpSession session = ServletActionContext.getRequest().getSession();

	private Admin admin = new Admin();
	
	private List<Integer> roleIds = new ArrayList<Integer>();
	
	private String loginError = "";
	
	
	@Action(value="regist",results={
			@Result(name="success",location="/login.jsp"),
			@Result(name="error",location="/regist.jsp")
			})
	public String regist() {
		try {
			adminService.regist(admin);
		} catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Action(value="login",results= {
			@Result(name="success",location="/welcome.jsp"),
			@Result(name="error",location="/login.jsp")
			
		})
	public String login() {
		Admin loginAdmin = adminService.login(admin);
		List<Role> rolein = adminService.showRoles();
		if (loginAdmin!=null) {
//			admin=loginAdmin;
//			System.out.println(admin);
			session.setAttribute("adminsession", loginAdmin);
			session.setAttribute("rolesin", rolein);
			return SUCCESS;
		} else {
			loginError="登录失败";
			return ERROR;	
		}
				
	}	
	/**
	 * 登出
	 */
	@Action(value="logout",results={
			@Result(name="success",location="/login.jsp")
	})
	public String logout(){
		session.removeAttribute("admin");
		session.invalidate();
		return SUCCESS;
	}
	
	@Action(value="updateAdmin",results={
			@Result(name="success",location="/welcome.jsp")
	})
	public String updateAdmin() {
		Admin loginAdmin = (Admin) session.getAttribute("adminsession");
		loginAdmin.setUser_name(admin.getUser_name());
		List<Role> roles = new ArrayList<Role>();	
		roles = loginAdmin.getRoles();
		if(!roleIds.isEmpty()) {
			roles = null;
			for (Integer id : roleIds) {
				roles.add(new Role(id));
			}
		}
		adminService.updateAdmin(loginAdmin,admin, roles);
		return SUCCESS;
	}

	@Action(value="goCustomer",results={
			@Result(name="success",location="/customer/customermain",type="redirectAction")
	})
	public String goCustomer() {
		return SUCCESS;
	}
	
	
	
	
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}

	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}

}
