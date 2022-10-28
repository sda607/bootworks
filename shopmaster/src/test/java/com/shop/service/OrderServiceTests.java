package com.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.OrderDto;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.entity.OrderItem;
import com.shop.entity.Orders;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;

@Transactional
@SpringBootTest
public class OrderServiceTests {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepo;
	
	
	@Autowired ItemRepository itemRepo;
	 
	
	@Autowired
	MemberRepository memberRepo;
	
	public Item saveItem() {
		Item item = new Item();
		item.setItemNm("test상품");
		item.setPrice(10000);
		item.setItemDetail("test상품상세설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		return itemRepo.save(item);
		
	}
	//회원 저장
	public Member saveMember() {
		Member member = new Member();
		member.setEmail("Test@mave.com");
		return memberRepo.save(member);
	}
	
	@Test
	public void order() {
		Item item = new Item();
		Member member = new Member();
		
		OrderDto orderDto = new OrderDto();
		orderDto.setItemId(item.getId());
		orderDto.setCount(10);
		
		//주문할 id로 주문 객체 생성
		Long orderId = orderService.order(orderDto, member.getEmail());
		Orders order = orderRepo.findById(orderId)
				.orElseThrow(EntityNotFoundException::new);
		
		List<OrderItem> orderItem = order.getOrderItems();
		
		int totalPrice = orderDto.getCount() * item.getPrice();
		System.out.println(totalPrice);
		
		assertEquals(totalPrice, order.getTotalPrice());
	}
	
	
}
