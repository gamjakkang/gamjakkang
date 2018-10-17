package study.spring.hellospring;

import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import study.spring.helper.WebHelper;

/**
 * Spring은 하나의 컨트롤러가 하나 이상의 페이지를 표현할 수 있다.
 * 클래스는 폴더의 개념이 되고, 이안에서의 메서드들은 페이지의 개념이 된다.
 */
@Controller
public class ParamController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParamController.class);
	
	// WebHelper 객체 의존성 주입
	@Autowired
	WebHelper web;
	/** 첫 페이지의 역활을 하는 메서드 */
	
	@RequestMapping(value="/param/home.do", method =RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.debug("home is running...");
		// 화면에 표시할 View의 이림을 리턴한다.
		return "param/home";
	}
	
	/** GET 방식의 파라미터를 전송받는다. */
	// 메서드 파라미터 선언부에서 처리하고자 하는 GET파라미터를 추가적으로 나열한다.
	// --> @RequestParam(value="파라미터명", defaultValue='기본값") 데이터형 변수명
	@RequestMapping(value="/param/get.do", method = RequestMethod.GET)
	public ModelAndView get(Locale locale, Model model){
		logger.debug("get is running...");
		
		// WebHelper 초기화
		// --> 모든 컨트롤러 메서드 초기에 1회 호출한다.
		web.init();
		
		int answer = web.getInt("answer");
		
		if (answer == 0 ) {
			return web.redirect(null, "답변이 없습니다.");
		}
		
		String result = null;
		
		if(answer == 300) {
			result = "정답입니다.";
		} else {
			result = "오답입니다.";
		}
		
		// 파라미터값을 View 에게 전달한다.
		model.addAttribute("answer", answer);
		model.addAttribute("result", result);
		// 화면에 표시할 view의 이름을 리턴한다.
		return new ModelAndView("param/get");
	}
	/** POST 방식의 파라미터를 전송받는다. */
	// 추가적인 파라미터가 필요하면 콤마(,)로 구분하여 나열한다.
	@RequestMapping(value = "/param/post.do", method = RequestMethod.POST)
	public ModelAndView post(Locale locale, Model model) {
			
		logger.debug("post is running...");
		
		web.init();
		
		String userName = web.getString("user_name");
		int userAge = web.getInt("user_age");
		
		if(userName == null) {
			return web.redirect(null, "이름을 입력하세요.");
		}
		
		if(userAge == 0) {
			return web.redirect(null, "나이를 숫자로 입력하세요.");
		}
		
		//파라미터값을 View에게 전달한다.
		model.addAttribute("name", userName);
		model.addAttribute("age", userAge);
		
		return new ModelAndView ("param/post");
	}
	
}