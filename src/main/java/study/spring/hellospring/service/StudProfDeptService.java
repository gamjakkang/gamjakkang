package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.StudProfDept;


public interface StudProfDeptService {
	
	public StudProfDept getStudProfDeptItem (StudProfDept student) 
			throws Exception;

	public List<StudProfDept> getStudProfDeptList (StudProfDept student) 
			throws Exception;
	
	public int getStudProfDeptCount (StudProfDept student) 
			throws Exception;
}
