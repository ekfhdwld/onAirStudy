package com.kh.onairstudy.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.onairstudy.member.model.dao.MemberDAO;
import com.kh.onairstudy.member.model.vo.Member;
import com.kh.onairstudy.studyroom.model.vo.StudyRoomLog;

/*@Transactional(propagation = Propagation.REQUIRED,
isolation = Isolation.READ_COMMITTED,
rollbackFor = Exception.class)*/

@Service
public class MemberServiceImpl implements MemberService {


	@Autowired
	private MemberDAO memberDAO;

	//01. 회원 로그인
	@Override
	public Member selectOneMember(String memberId) {
		return memberDAO.selectOneMember(memberId);
	}
	// 02. 회원등록
	@Override
	public int insertMember(Member member) {
		int result =0;
		//1.member insert
		result = memberDAO.insertMember(member);
		
		//2.스터디방에 member insert
		StudyRoomLog srlog = null;
		int category = member.getCategory();
		srlog.setSrNo(category);
		result = memberDAO.insertMemberToSr(srlog);
		System.out.println("category="+category);
		return result;
	}
	
	//신고 후 회원 블랙리스트 업데이트
	@Override
	public int updateBlack(String reportedMember) {
		return memberDAO.updateBlack(reportedMember);
	}
	//성실멤버 랭킹
	@Override
	public List<Member> selectDiligentMember() {
		return memberDAO.selectDiligentMember();
	}

	
	
	
	
}
