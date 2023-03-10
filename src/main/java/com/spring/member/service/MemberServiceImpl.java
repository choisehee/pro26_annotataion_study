package com.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;


//서비스 반환해주는 
@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl  implements MemberService{
       @Autowired
	   private MemberDAO memberDAO;
	     
	   @Override
	   public List listMembers() throws DataAccessException {
	      List membersList = null;
	      membersList = memberDAO.selectAllMemberList();
	      return membersList;
	   }

	   @Override
	   public int addMember(MemberVO memberVO) throws DataAccessException {
	     return memberDAO.insertMember(memberVO);
	   }


	   @Override
	   public int removeMember(String id) throws DataAccessException {
	      return memberDAO.deleteMember(id);
	   }

	@Override
	public int memberMod(MemberVO memberVO) throws DataAccessException {
		int result =memberDAO.memberMod(memberVO);
		return result;
	}
}
