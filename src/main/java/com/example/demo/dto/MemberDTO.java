package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

	String id; // 아이디
	
	String password; // 패스워드
	
	String name; // 이름
	
	LocalDateTime regDate; // 등록일
	
	LocalDateTime modDate; // 수정일
	
	
}
