package com.shop.config;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@EntityListeners(value = {AuditingEntityListener.class}) //해당 클래스에 Auditing 기능을 포함
@MappedSuperclass	
@Getter
public abstract class BaseEntity extends BaseTimeEntity{

	@CreatedBy
	@Column(updatable = false)
	private String createdBy;	//등록자
	
	@LastModifiedBy
	private String modifidedBy;	//수정자
}
