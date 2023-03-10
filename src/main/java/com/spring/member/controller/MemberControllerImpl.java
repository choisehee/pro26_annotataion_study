package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.dao.MemberDAO;
import com.spring.member.dao.MemberDAOImpl;
import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;

//같은 프로젝트 내에서 컨트롤 애너테이션 이름을 동일하게 했을 때 에러가 나온다
@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	
//	생성자, 필드, 셋터 메서드, 메서드설정에 의존성 주입 
	
	@Autowired
	//@Autowired(의존성주입)은 객체 간의 의존성을 외부에서 주입하는 방식으로 코드의 가독성과 유지보수성을 높이는데 사용됩니다
	//@Autowired 어노테이션을 사용하면 스프링 컨테이너가 객체를 생성하여 자동으로 주입해주므로 코드의 가독성이 좋아지고 유지보수가 용이해집니다.
	private MemberService memberService;
	
	@Autowired
	//@Autowired을 사용함으로 이런식으로 부르던걸 bind(request, memberVO)안하고 작업 할수 있다
	MemberVO memberVO;

	@Override
	@RequestMapping(value = "/member/listMembers.do",method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/addMember.do",method = RequestMethod.POST)
	public ModelAndView addMember(
			@ModelAttribute("member")MemberVO member,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result=0;
		
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/member/removeMember.do",method = RequestMethod.GET)
	public ModelAndView removeMember(
			@RequestParam("id")String id,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		System.out.println("id : "+id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@RequestMapping(value = "/member/*Form.do",method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	public ModelAndView modMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		System.out.println(viewName);
		
		MemberDAO dao=new MemberDAOImpl();
		MemberVO memberVO = new MemberVO();
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		

//		bind(request, memberVO);
		dao.memberMod(memberVO);
		request.setAttribute("memberVO", memberVO);
		ModelAndView mav = new ModelAndView(viewName);
		
		mav.setViewName(viewName);
		return mav;
	}

	public ModelAndView modMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		
		MemberDAO memberDAO=new MemberDAOImpl();
		
		MemberVO memberVO = new MemberVO();
		request.setAttribute("memberVO", memberVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}	

}
