package com.boot.entity;

import java.lang.reflect.Member;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter @Setter
public class Board {
	
	@Id
	private Long boardNum;
			
	private String title;
	
	private String content;
	
	private Member member;
	
	
	
}
