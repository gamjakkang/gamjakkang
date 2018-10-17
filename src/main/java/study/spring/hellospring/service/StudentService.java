package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.Student;

public interface StudentService {
	/**
	 * 학생 등록하기
	 * @param student 저장할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	public void addStudent (Student student) throws Exception;
	
	/**
	 * 학생 목록 조회
	 * @return 조회 결과에 대한 컬렉션
	 * @throws Exception
	 */
	public List<Student> getStudentList(Student student) throws Exception;
	
	public void editStudent(Student student) throws Exception;
	
	public void deleteStudent(Student student) throws Exception;
	
	public Student getStudentItem(Student student) throws Exception;
	
	/**
	 * 전체 목록 수 조회
	 * @param student
	 * @return 조회 결과
	 * @throws Exception
	 */
	public int getStudentCount(Student student) throws Exception;
}
