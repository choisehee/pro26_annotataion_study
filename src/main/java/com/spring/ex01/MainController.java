package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController")
@RequestMapping("/hi")
public class MainController {
	
//	main1.do또는 hihihih.do로 검색 가능하게 하는 부분
	@RequestMapping(value ={"/main1.do","hi"} ,method=RequestMethod.GET)
	ModelAndView main1(HttpServletRequest request, HttpServletResponse response)throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.addObject("test","안녕하세요");
		mav.setViewName("gggg");
		return mav;
	}
	
	@RequestMapping(value = {"/main2.do","ex"} ,method=RequestMethod.GET)
	ModelAndView main2(HttpServletRequest request, HttpServletResponse response)throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.addObject("extest","연습2");
		mav.setViewName("hihihih"); //보여줄 곳의 파일명
		return mav;
	}

}
