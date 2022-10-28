package com.boot.entity;

import javax.management.relation.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Entity
@Getter @Setter
public class member {

	@Id
	private String userid;
	
	private String password;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
