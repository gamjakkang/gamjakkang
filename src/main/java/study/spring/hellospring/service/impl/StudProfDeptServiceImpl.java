package study.spring.hellospring.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.hellospring.model.StudProfDept;
import study.spring.hellospring.service.StudProfDeptService;

@Service
public class StudProfDeptServiceImpl implements StudProfDeptService{

	@Autowired
	private SqlSession sqlSession;
	@Override
	public StudProfDept getStudProfDeptItem(StudProfDept student) throws Exception {
		StudProfDept result = null;
		
		try {
			result = sqlSession.selectOne("StudentJoinMapper.selectStudProfDeptItem", student);
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
	public List<StudProfDept> getStudProfDeptList(StudProfDept student) throws Exception {
		List<StudProfDept> result = null;
		
		try {
			result = sqlSession.selectList("StudentJoinMapper.selectStudProfDeptList", student);
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
	public int getStudProfDeptCount(StudProfDept student) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("StudentJoinMapper.selectStudProfDeptCount", student);
		} catch (Exception e) {
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}
}

