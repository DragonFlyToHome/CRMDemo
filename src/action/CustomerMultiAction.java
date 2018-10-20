package action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Customer;
import service.CustomerService;

/**
 * 新增客户，文件上传业务控制器
 * @author 丰源
 *
 */
@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerMultiAction extends ActionSupport implements ModelDriven<Customer>{

	@Autowired
	private CustomerService customerService;
	
	private Customer customer = new Customer();
	
	//源文件对象：Struts自动实例化
	private File selfPai;
	
	//源文件名：源文件对象+FileName
	private String selfPaiFileName;
	
	@Action(value="addcustomer",results={
			@Result(name="success",location="customermain",type="redirectAction")
	})
	public String uploadPic() throws IOException{
		//1.获取目标文件夹的绝对路径
		//获取pics的绝对路径
		ServletActionContext.getServletContext().getRealPath("pics");
		String destDirPath = "D:\\javawps\\CRMDemo\\WebContent\\pics";
		System.out.println(destDirPath);
		//2.创建目标文件夹
		File destDir = new File(destDirPath);
		if(!destDir.exists()){
			destDir.mkdirs();
		}
		//3.创建目标文件对象
		System.out.println("目标文件夹："+destDir);
		System.out.println("原文件名："+selfPaiFileName);
		File destFile = new File(destDir, selfPaiFileName);
		//4.拷贝文件
		FileUtils.copyFile(selfPai, destFile);
		
		//新增客户
		//把源文件名存入到数据库
		customer.setSelf_pai_pic(selfPaiFileName);
		if(customerService.findCustomerExist(customer)) {
			System.out.println("addCustomer：该客户已存在");
		}else {
			customerService.saveCustomer(customer);
		}
		return SUCCESS;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public File getSelfPai() {
		return selfPai;
	}

	public void setSelfPai(File selfPai) {
		this.selfPai = selfPai;
	}

	public String getSelfPaiFileName() {
		return selfPaiFileName;
	}

	public void setSelfPaiFileName(String selfPaiFileName) {
		this.selfPaiFileName = selfPaiFileName;
	}

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	

}
