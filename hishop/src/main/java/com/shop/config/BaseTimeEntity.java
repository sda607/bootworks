package com.shop.config;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = {AuditingEntityListener.class}) //해당 클래스에 Auditing 기능을 포함
@MappedSuperclass										//부모 클래스를 상속받는 자식 클래스의 매핑 정보만 제공함
@Getter @Setter
public abstract class BaseTimeEntity {
	//Entity가 생성되어 저장될 때 시간이 자동 저장
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regTime;
	
	//조회한 Entity의 값을 변경할 때 시간이 자동 저장
	@LastModifiedDate
	private LocalDateTime updateTime;
	
	
	
}
