package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service // 빈으로 등록을 위한 어노테이션
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberRepository repository;
	
	@Override
	public Page<MemberDTO> getList(int pageNumber) {
		
    	int pageNum = (pageNumber == 0) ? 0 : pageNumber -1;
    	
    	Sort sort = Sort.by("regDate").descending();
    	
    	Pageable pageable = PageRequest.of(pageNum, 10, sort);
    	
    	Page<Member> entityPage = repository.findAll(pageable);
    	
    	Page<MemberDTO> dtoPage = entityPage.map( entity -> entityToDto(entity));
    	
    	return dtoPage;
		
	}

	@Override
	public boolean register(MemberDTO dto) {
		
		// 아이디 중복 여부를 확인
		String id = dto.getId();
		MemberDTO getDto = read(id);
		
		// 해당 아이디가 사용되고 있다면, 처리결과는 실패(false) 반환
		if (getDto != null) {
			System.out.println("사용중인 아이디입니다");
			return false;			
		}
		
		// 해당 아이디가 사용되지 않는다면, 회원 등록하고 처리결과는 성공(true) 반환
		Member entity = dtoToEntity(dto);
		repository.save(entity);
		
		return true;
		
	}

	@Override
	public MemberDTO read(String id) {
		
		Optional<Member> result = repository.findById(id);
		
		if (result.isPresent()) {
			Member member = result.get();
			return entityToDto(member);
		} else {
			return null;
			
		}
		
	}

}
