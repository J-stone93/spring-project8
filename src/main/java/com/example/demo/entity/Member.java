package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity{

	// board 테이블에서 @ManyToOne으로 Member 테이블에서 @Id가 붙어진게 참조키가 된다
	@Id
	@Column(length = 50)
	String id;
	
	@Column(length = 200, nullable = false)
	String password;
	
	@Column(length = 100, nullable = false)
	String name;
}







