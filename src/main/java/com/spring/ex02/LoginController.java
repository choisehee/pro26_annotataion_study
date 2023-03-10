package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")
public class LoginController {

	@RequestMapping(value = { "/test/loginForm.do", "/test/loginForm2.do" }, method = { RequestMethod.GET })
	public ModelAndView loginForm(HttpServletRequest request,HttpServletResponse response)throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}	

	@RequestMapping(value = "/test/login.do", method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("result");
		String userID=request.getParameter("userID");
		String userName=request.getParameter("userName");
		mav.addObject("userID",userID);
		mav.addObject("userName",userName);
		return mav;
	}
	
//	로그인창 -이메일을 가져와서 콘솔에 찍어보기
	@RequestMapping(value = "/test/login2.do", 
			method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login2(
			@RequestParam("userID") String userID,
			
//			@RequestParam 적용 시 required 속성을 생략하면 기본값은 true임
//			required 속성을 true로 설정하면 메서드 호출 시 반드시 지정한 이름의 매개변수를전달해야함
			@RequestParam(value ="userName", required =true) String userName,
			
//			required 속성을 false로 설정하면 메서드 호출 시 지정한 이름의 매개변수가 전달되면 값을 저장하고 없으면 null을 할당함
//			falses는 없을때 널로 표시된다
			@RequestParam(value ="email", required = false)String email,
			HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("result");
		
//		getParameter관련 애너테이션을 사용하므로 더이상 필요하지 않음
//		String userID=request.getParameter("userID");
//		String userName=request.getParameter("userName");
		
		System.out.println("userID : "+userID);
		System.out.println("userName : "+userName);
		System.out.println("email : "+email);
		
		mav.addObject("userID",userID);
		mav.addObject("userName",userName);
		return mav;
	}
	
	
	@RequestMapping(value = "/test/login3.do", 
			method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login3(
			@RequestParam Map<String, String>info,
			HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		
		String userID=info.get("userID");
		String userName=info.get("userName");
		System.out.println("userID : "+userID);
		System.out.println("userName : "+userName);
		mav.addObject("info", info);
		mav.setViewName("result");
		
		System.out.println(info);
		
		return mav;
	}
	
	@RequestMapping(value = "/test/login4.do", 
			method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login4(
			@ModelAttribute("info")LoginVO loginVO,
			HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav=new ModelAndView();
		

		System.out.println("userID : "+loginVO.getUserID());
		System.out.println("userName : "+loginVO.getUserName());

		mav.setViewName("result");
		
		return mav;
	}
	
	//Model클래스의  addAttribute()메서드는  ModelAndView의 addObject()메서드와
	//같은 기능을 합니다 Model 클래스는 따로 뷰 정보를 전달할 필요가 없을 때 사용하면 편리함
	@RequestMapping(value = "/test/login5.do", 
			method = { RequestMethod.GET,RequestMethod.POST})
	public String login5(Model model,
			HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		
		request.setCharacterEncoding("utf-8");
		model.addAttribute("userID", "hong");
		model.addAttribute("userName", "홍길동");

		return "result";
	}


}
