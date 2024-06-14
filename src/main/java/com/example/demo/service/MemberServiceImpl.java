package com.example.demo.service;

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

}
