package com.shop.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.dto.QMainItemDto;
import com.shop.entity.Item;
import com.shop.entity.QItemImg;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

	private JPAQueryFactory queryFatory;	//동적으로 쿼리 생성
	
	//생성자
	public ItemRepositoryCustomImpl(EntityManager em) {
		this.queryFatory = new JPAQueryFactory(em);
	}
	//1. 상품 판매 상태 조건이 전체(null)일 경우 null리턴
	//상품 판매 상태 조건이 판매중 or 품절 상태라면 해당 조건의 상품만 조회함
	private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
		return searchSellStatus == null ? null :
			QItem.item.itemSellStatus.eq(searchSellStatus);
		
	}
	
	//2. 등록일 기준 - SearchDateType 값에 따라서 dateTime 값은 이전 시간의 값으로 세팅후 해당시간
	// 이후로 등록된 상품만 조회함
		private BooleanExpression regDtsAfter(String searchDateType) {
			LocalDateTime dateTime = LocalDateTime.now();
			
			if(StringUtils.equals("all", searchDateType) || searchDateType == null) {
				return null;
			}else if(StringUtils.equals("1d", searchDateType)){
				dateTime = dateTime.minusDays(1);
			}else if(StringUtils.equals("1w", searchDateType)){
				dateTime = dateTime.minusWeeks(1);
			}else if(StringUtils.equals("1m", searchDateType)){
				dateTime = dateTime.minusMonths(1);
			}else if(StringUtils.equals("6m", searchDateType)){
				dateTime = dateTime.minusMonths(6);
			}
			
			return QItem.item.regTime.after(dateTime);
		}
	
	//3. searchBy 값에 따라서 상품명에 검색어를 포함하고 있는 상품 또는 상품 생성자의 아이디에 검색어를
	// 포함하고 있는 상품을 조회하도록 조건 값을 반환 함
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("itemNm", searchBy)) {
			return QItem.item.itemNm.like("%" + searchQuery + "%");
		}else if(StringUtils.equals("createdBy", searchBy)) {
			return QItem.item.itemNm.like("%" + searchQuery + "%");
		}
		return null;
	}
	
	
	
	@Override
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

		//검색한 리스트
		List<Item> content = queryFatory
				.select(QItem.item)
				.where(regDtsAfter(itemSearchDto.getSearchDataType()),
						searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
						searchByLike(itemSearchDto.getSearchBy(),
								itemSearchDto.getSearchBy()))
				.orderBy(QItem.item.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		//전체 개수
		long total = queryFatory.select(Wildcard.count).from(QItem.item)
				.where(regDtsAfter(itemSearchDto.getSearchDataType()),
						searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
						searchByLike(itemSearchDto.getSearchBy(),
								itemSearchDto.getSearchBy()))
				.fetchOne();
		//Page 인터페이스와 구현체인 PageImpl 객체로 반환함
		return new PageImpl<>(content, pageable, total);
	}
	
	//검색어가 null이 아니면 상품명에 해당 검색어가 포함되는 상품을 조회하는 조건을 반환
		private BooleanExpression itemNmLike(String searchQuery){
	        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%" + searchQuery + "%");
	    }
	
	
	//메인 페이지 상품 리스트
	@Override
	public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		QItem item = QItem.item;
		QItemImg itemImg = QItemImg.itemImg;
		
		//QmainItemDto의 생성자에서 반환할 값들을 넣어 줌
		List<MainItemDto> content = queryFatory
				.select(
						new QMainItemDto(
								item.id,
								item.itemNm,
								item.itemDetail,
								itemImg.imgUrl,
							    item.price))
				.from(itemImg)
				.join(itemImg.item, item)
				.where(itemImg.repimgYn.eq("Y"))
				.where(itemNmLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(Wildcard.count)
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
	}
				
		
		
		
		return null;
	}

}
