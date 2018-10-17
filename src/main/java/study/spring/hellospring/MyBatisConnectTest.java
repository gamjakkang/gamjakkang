package study.spring.hellospring;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MyBatisConnectTest {
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testFactory() {
		System.out.println("---- MyBatis DATABASE 연결 성공 ----");
	}
}
