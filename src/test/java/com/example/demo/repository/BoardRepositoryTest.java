package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;

	@Test
	public void 게시물30개추가() {

		Member member = Member.builder()
				  .id("user1")
				  .build();
		
		for (int i = 1; i <= 30; i++) {

			Board board = Board.builder().title(i + "번글").content("안녕하세요").writer(member).build();

			repository.save(board);

		}
	}
	
	@Test
	public void 페이지테스트() {
		
		// ascending:순정렬 decending:역정렬
		// no 필드값을 기준으로 역정렬: 게시판은 최신글이 맨 처음에 와야하기 때문
		Sort sort = Sort.by("no").ascending();
		
		//of메소드: PageRequest의 객체를 생성하는 함수
		Pageable pageable = PageRequest.of(0, 5, sort); //of(페이지번호, 데이터개수, 정렬)
		
		Page<Board> result = repository.findAll(pageable);
		
		List<Board> list = result.getContent();
		
		System.out.println(list);
	}
	
	@Test
	public void 게시물등록() {
		
		// 회원 엔티티 생성
		Member member = Member.builder()
							  .id("user1")
							  .build();
		// 회원테이블에 없는 아이디를 사용하면 에러남
		Board board = Board.builder()
						   .title("첫번째 글입니다")
						   .content("안녕하세요")
						   .writer(member)
						   .build();
		
		repository.save(board);
	}
	
	@Test
	public void 게시물조회() {
		
		// 쿼리가 내부적으로 join 처리함
		Optional<Board> result = repository.findById(3);
		
		if (result.isPresent()) {
			
			Board board = result.get();
			System.out.println(board);
			
		}
	}
	
	@Test
	public void 특정회원이작성한게시물삭제() {
		
		Member member = Member.builder().id("user1").build();
		
		repository.dedeleteWriter(member);
	}
}















