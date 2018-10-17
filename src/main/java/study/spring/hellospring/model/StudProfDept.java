package study.spring.hellospring.model;


public class StudProfDept extends Student {
	private String pname;
	private String dname;
	private int limitStart;
	private int listCount;
	
	
	public String getPname() {
		return pname;
	}
	public void setpname(String pname) {
		this.pname = pname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	@Override
	public String toString() {
		return "StudProfDept [pname=" + pname + ", dname=" + dname + ", limitStart=" + limitStart + ", listCount="
				+ listCount + "]";
	}
	
	
}
