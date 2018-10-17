package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.Department;


public interface DepartmentService {
	
	/**
	 * 학과 등록하기
	 * @param department 저장할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	public void addDepartment(Department department) throws Exception;
	/**
	 * 학과 목록 조회
	 * @return 조회 결과에 대한 컬렉션
	 * @throws Exception
	 */
	public List<Department> getDepartmentList(Department professor)throws Exception;
	
	/**
	 * 학과 전용 전체 목록조회
	 * @param professor
	 * @return
	 * @throws Exception
	 */
	public List<Department> getDepartment_List(Department department)throws Exception;
	
	/**
	 * 학과 수정하기
	 * @param department 수정할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	public void editDepartment(Department department) throws Exception;
	
	/**
	 * 학과 삭제하기
	 * @param department 삭제할 학과의 일련번호를 담고 있는 Beans
	 * @throws Exception
	 */
	public void deleteDepartment(Department department) throws Exception;
	
	/**
	 * 교수 상세 조회
	 * @param department 조회할 학과의 일련번호를 담고 있는 Beans
	 * @return 조회된 데이터가 저장된 Beans
	 * @throws Exception
	 */
	public Department getDepartmentItem(Department department) throws Exception;
	
	/**
	 * 전체 목록 수 조회
	 * @return 조회 겨로가
	 * @throws Exception
	 */
	public int getDepartmentCount(Department department) throws Exception;
}
