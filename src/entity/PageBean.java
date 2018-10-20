package entity;

import java.util.List;

/**
 * @author DrafY
 * 2018年10月15日-下午4:16:53
 */
public class PageBean<T> {

	public PageBean() {
		// TODO Auto-generated constructor stub
	}
	
	public PageBean(int a,int b,int c){
		super();
		this.pageNo = a;
		this.pageSize = b;
		this.totalCount = c;
		this.totalPage = (c/b)+(c%b>0?1:0);
		System.out.println(totalPage);
	}
	
	private List<T> pageContent;
	
	private Integer pageNo;
	
	private Integer pageSize;
	
	private Integer totalCount;
	
	private Integer totalPage;
	
	

	public List<T> getPageContent() {
		return pageContent;
	}

	
	public void setPageContent(List<T> pageContent) {
		this.pageContent = pageContent;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "PageBean [页号=" + pageNo + ", 页长=" + pageSize + ", 总数=" + totalCount + ", 总页="
				+ totalPage + "]";
	}
	
	
}
