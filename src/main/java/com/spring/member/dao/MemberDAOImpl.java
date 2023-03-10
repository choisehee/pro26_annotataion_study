package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.member.vo.MemberVO;

//이걸로 찾아 가기 때문에 꼭 입력이 필요하다 이름만 입력했지 사실상 밸류=""의 형태를 요약한 형태이다
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	// 세션으로 처리해줌 원래는 세션팩토리로 부르고 파라미터 하던걸 좀더 간단하게 데려온다


	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");

		return membersList;
	}

	public MemberVO findMemder(String id) throws DataAccessException {
		MemberVO memberVO=(MemberVO) sqlSession.selectOne("mapper.member.findMember", id);
		return memberVO;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public int memberMod(MemberVO memberVO) throws DataAccessException {

		int result = sqlSession.update("mapper.member.updateMember", memberVO);
		sqlSession.commit();
		return result;

	}

}
