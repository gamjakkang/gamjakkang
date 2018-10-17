package study.spring.hellospring;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieController {
	/**
	 * 쿠키 저장을 위한 작성 페이지
	 * --> 이페이지를 "/cookie/write.do" URL에 GET 방식으로 노출시킨다.
	 */
	@RequestMapping(value = "/cookie/write.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response,
			@CookieValue(value="my_cookie", defaultValue="") String myCookie){
				
		/** 쿠키값 처리 */
		// 컨트롤러 메서드의 파라미터로 전달받을 경우 URLDecoding이 별도로 필요함
		try {
			myCookie = URLDecoder.decode(myCookie, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("my_cookie", myCookie);
			
		return "cookie/write";
	}
	
	/**
	 * 쿠키를 저장하기 위한 action 페이지
	 * --> 이페이지를 "/cookie/save.do" URL에 POST 방식으로 노출시킨다.
	 */
	@RequestMapping(value = "/cookie/write.do", method = RequestMethod.POST)
	public String cookieSave(Locale locale, Model model,
			HttpServletRequest reqeust, HttpServletResponse response,
			@RequestParam(value="memo", defaultValue="") String memo){
		
		/** 쿠키처리는 JSP의 기본 처리와 동일하다 */
		// 쿠키 저장을 위해서는 URLEncoding 처리가 필요하다.
		if(!memo.equals("")) {
			try {
				memo = URLEncoder.encode(memo, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		Cookie cookie = new Cookie("my_cookie", memo);	// 저장할 쿠키 객체 생성.
		cookie.setPath("/");	// 쿠키의 유효 경로 --> 사이트 전역에 대한 설정.
		
		if (memo.equals("")) {
			cookie.setMaxAge(0);	//쿠키 설정시간이 0이면 즉시 삭제된다.
		} else {
			cookie.setMaxAge(60);	//값이 있다면 60초 동안 쿠키 저장
		}
		
		response.addCookie(cookie); // 쿠지저장
		/** spring 방식의 페이지 이동 */
		String url = "/cookie/write.do";
		return "redirect:" + url;
	}
}
