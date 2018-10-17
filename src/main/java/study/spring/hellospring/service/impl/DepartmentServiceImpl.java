package study.spring.hellospring.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.hellospring.model.Department;
import study.spring.hellospring.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Department> getDepartmentList(Department department) throws Exception {
		List<Department> result = null;
		
		try {
			result = sqlSession.selectList("DepartmentMapper.selectDepartmentList", department);
			if (result == null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
		
			throw new Exception("데이터 조회에 실패했습니다.");
		} 
		
		return result;
	}
	@Override
	public void addDepartment(Department department) throws Exception {
		try {
			int result = sqlSession.insert("DepartmentMapper.insertDepartmentItem", department);
			if (result == 0) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			
			throw new Exception("저장된 데이터가 없습니다.");
		} catch (Exception e) {
			throw new Exception("데이터 저장에 실패했습니다.");
		}
	}
	@Override
	public void editDepartment(Department department) throws Exception {
		try {
			int result = sqlSession.update("DepartmentMapper.updateDepartmentItem", department);
			if (result == 0) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			
			throw new Exception("변경된 데이터가 없습니다.");
		} catch (Exception e) {
			
			throw new Exception("데이터 수정에 실패했습니다.");
		} 
	}
	@Override
	public void deleteDepartment(Department department) throws Exception {
		try {
			int result = sqlSession.delete("DepartmentMapper.deleteDepartmentItem", department);
			if (result == 0) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("삭제된 데이터가 없습니다.");
		} catch (Exception e) {
	
			throw new Exception("데이터 삭제에 실패했습니다.");
		}
	}
	@Override
	public Department getDepartmentItem(Department department) throws Exception {
		Department result = null;
		try {
			result = sqlSession.selectOne("DepartmentMapper.selectDepartmentItem", department);
			if (result == null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		return result;
	}
	
	@Override
	public int getDepartmentCount(Department department) throws Exception{
		int result =0;
		try {
			result = sqlSession.selectOne("DepartmentMapper.selectDepartmentCount", department);
		} catch (Exception e) {
			
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}
	@Override
	public List<Department> getDepartment_List(Department department) throws Exception {
		List<Department> result = null;
		
		try {
			result = sqlSession.selectList("DepartmentMapper.selectDepartment_List", department);
			if (result == null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
		
			throw new Exception("데이터 조회에 실패했습니다.");
		} 
		
		return result;
	}

}
