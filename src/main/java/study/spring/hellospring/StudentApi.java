package study.spring.hellospring;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import study.spring.hellospring.model.Student;
import study.spring.hellospring.service.StudentService;
import study.spring.helper.WebHelper;

@Controller
public class StudentApi {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentApi.class);
	
	/** 사용하고자 하는 Helper + Service 객체 주입 설정 */
	@Autowired
	WebHelper web;
	
	@Autowired
	StudentService studentService;
	
	/** 학생 목록 Api */
	@ResponseBody
	@RequestMapping(value ="/student_api/StudentSelectListApi", method = RequestMethod.GET)
	public void StudentSelectListApi(Locale locale, Model model, HttpServletResponse response) {
		/** (1) WebHelper 초기화 + 컨텐츠 형식 지정 */
		web.init();
		response.setContentType("application/json");
		
		/** (2) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		List<Student> item = null;
		try {
			item = studentService.getStudentList(null);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		/** (3) 처리 결과를 JSON으로 출력하기 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rt", "OK");
		data.put("item", item);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 학생 상세 정보 조회 API */
	@ResponseBody
	@RequestMapping(value ="/student_api/StudentSelectItemApi", method = RequestMethod.GET)
	public void StudentSelectitemApi(Locale locale, Model model, HttpServletResponse response) {
		/** (1) WebHelper 초기화 + 컨텐츠 형식 지정 */
		web.init();
		response.setContentType("application/json");
		
		/** (2) 파라미터 받기 및 유효성 검사 */
		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);
		
		if(studno == 0) {
			web.printJsonRt("학생번호가 없습니다.");
			return;
		}
		
		Student student = new Student();
		student.setStudno(studno);
		
		/** (3) Service를 통한 SQL 수행 */
		Student item = null;
		try {
			item = studentService.getStudentItem(student);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		/** (4) 처리 결과를 JSON으로 출력하기 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rt", "OK");
		data.put("item", item);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
	}
	
	/** 학생 정보 등록 API */
	@ResponseBody
	@RequestMapping(value ="/student_api/StudentInsertApi", method = RequestMethod.POST)
	public void StudentInsertApi(Locale locale, Model model, HttpServletResponse response) {
		/** (1) WebHelper 초기화 + 컨텐츠 형식 지정 */
		web.init();
		response.setContentType("application/json");
		
		String name = web.getString("name");
		String userid = web.getString("userid");
		int grade = web.getInt("grade");
		String idnum = web.getString("idnum");
		String birthdate = web.getString("birthdate");
		String tel = web.getString("tel");
		int height = web.getInt("height");
		int weight = web.getInt("weight");
		int deptno = web.getInt("deptno");
		int profno = web.getInt("profno");
		
		logger.debug("name=" + name);
		logger.debug("userid=" + userid);
		logger.debug("grade=" + grade);
		logger.debug("idnum=" + idnum);
		logger.debug("birthdate=" + birthdate);
		logger.debug("tel=" + tel);
		logger.debug("height=" + height);
		logger.debug("weight=" + weight);
		logger.debug("deptno=" + deptno);
		logger.debug("profno=" + profno);
		
		if (name == null){
			web.printJsonRt("이름을 입력하세요.");
			return;
		}
		
		if (userid == null){
			web.printJsonRt("아이디를 입력하세요.");
			return;
		}
		
		if (grade == 0){
			web.printJsonRt("학년을 입력하세요.");
			return;
		}
		
		if (idnum == null){
			web.printJsonRt("주민등록번호를 입력하세요.");
			return;
		}
		
		if (birthdate == null){
			web.printJsonRt("생년월일을 입력하세요.");
			return;
		}
		
		if (tel == null){
			web.printJsonRt("전화번호를 입력하세요.");
			return;
		}
		
		if (height == 0){
			web.printJsonRt("키를 입력하세요.");
			return;
		}
		if (weight == 0){
			web.printJsonRt("몸무게를 입력하세요.");
			return;
		}
		
		if (deptno == 0){
			web.printJsonRt("학과번호를 입력하세요.");
			return;
		}
		
		if (profno == 0) {
			web.printJsonRt("교수번호를 입력하세요.");
		}
		
		Student student = new Student();
		student.setName(name);
		student.setuserid(userid);
		student.setGrade(grade);
		student.setIdnum(idnum);
		student.setBirthdate(birthdate);
		student.setTel(tel);
		student.setHeight(height);
		student.setWeight(weight);
		student.setDeptno(deptno);
		student.setProfno(profno);
		
		try {
			studentService.addStudent(student);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		web.printJsonRt("OK");
	}
	
	/** 학생 삭제 API */
	@ResponseBody
	@RequestMapping(value ="/student_api/StudentDeleteApi", method = RequestMethod.POST)
	public void StudentDeleteApi(Locale locale, Model model, HttpServletResponse response) {
	
		/** (1) WebHelper 초기화 + 컨텐츠 형식 지정 */
		web.init();
		response.setContentType("application/json");
		
		/** (2) 파라미터 받기 및 유효성 검사 */
		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);
		
		if(studno == 0) {
			web.printJsonRt("학생번호가 없습니다.");
			return;
		}
		
		Student student = new Student();
		student.setStudno(studno);
		
		
		try {
			studentService.deleteStudent(student);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		web.printJsonRt("OK");
	}
	
	/** 학생 수정 API */
	@ResponseBody
	@RequestMapping(value ="/student_api/StudentEditApi", method = RequestMethod.POST)
	public void StudentEditApi(Locale locale, Model model, HttpServletResponse response) {
		/** (1) WebHelper 초기화 + 컨텐츠 형식 지정 */
		web.init();
		response.setContentType("application/json");
		
		/** (2) 파라미터 받기 및 유효성 검사 */
		int studno = web.getInt("studno");
		String name = web.getString("name");
		String userid = web.getString("userid");
		int grade = web.getInt("grade");
		String idnum = web.getString("idnum");
		String birthdate = web.getString("birthdate");
		String tel = web.getString("tel");
		int height = web.getInt("height");
		int weight = web.getInt("weight");
		int deptno = web.getInt("deptno");
		int profno = web.getInt("profno");
		
		logger.debug("studno=" + studno);
		logger.debug("name=" + name);
		logger.debug("userid=" + userid);
		logger.debug("grade=" + grade);
		logger.debug("idnum=" + idnum);
		logger.debug("birthdate=" + birthdate);
		logger.debug("tel=" + tel);
		logger.debug("height=" + height);
		logger.debug("weight=" + weight);
		logger.debug("deptno=" + deptno);
		logger.debug("profno=" + profno);
		
		if (studno == 0) {
			web.printJsonRt("학생번호를 입력하세요.");
		}
		
		if (name == null){
			web.printJsonRt("이름을 입력하세요.");
			return;
		}
		
		if (userid == null){
			web.printJsonRt("아이디를 입력하세요.");
			return;
		}
		
		if (grade == 0){
			web.printJsonRt("학년을 입력하세요.");
			return;
		}
		
		if (idnum == null){
			web.printJsonRt("주민등록번호를 입력하세요.");
			return;
		}
		
		if (birthdate == null){
			web.printJsonRt("생년월일을 입력하세요.");
			return;
		}
		
		if (tel == null){
			web.printJsonRt("전화번호를 입력하세요.");
			return;
		}
		
		if (height == 0){
			web.printJsonRt("키를 입력하세요.");
			return;
		}
		if (weight == 0){
			web.printJsonRt("몸무게를 입력하세요.");
			return;
		}
		
		if (deptno == 0){
			web.printJsonRt("학과번호를 입력하세요.");
			return;
		}
		
		if (profno == 0) {
			web.printJsonRt("교수번호를 입력하세요.");
		}
		
		Student student = new Student();
		student.setStudno(studno);
		student.setName(name);
		student.setuserid(userid);
		student.setGrade(grade);
		student.setIdnum(idnum);
		student.setBirthdate(birthdate);
		student.setTel(tel);
		student.setHeight(height);
		student.setWeight(weight);
		student.setDeptno(deptno);
		student.setProfno(profno);
		
		/** (3) Service를 통한 SQL 수행 */
		try {
			studentService.editStudent(student);
		} catch (Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		web.printJsonRt("OK");
	}
}
