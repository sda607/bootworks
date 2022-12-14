package com.shop.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.shop.config.BaseEntity;
import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter @Setter
@Entity
public class Item extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;		//자동 순번
	
	@Column(nullable = false, length = 50)
	private String itemNm;	//상품 이름
	
	@Column(nullable = false)
	private int price;		//상품 가격

	@Column(nullable = false)
	private int stockNumber;//재고 수량
	
	@Lob	//Large object
	@Column(nullable = false)
	private String itemDetail;//상세 설명
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;
	
	//상품 수정 메서드
	public void updateItem(ItemFormDto itemFormDto) {
		this.itemNm = itemFormDto.getItemNm();
		this.price = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus= itemFormDto.getItemSellStatus();
		
	}
	
	
}
