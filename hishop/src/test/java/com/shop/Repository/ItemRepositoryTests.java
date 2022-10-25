package com.shop.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.repository.ItemRepository;

@SpringBootTest
public class ItemRepositoryTests {

	@Autowired
	ItemRepository itemRepo;
	
	@Test
	public void createItemTest() {
		Item item = new Item();
		item.setItemNm("바지");
		item.setPrice(120000);
		item.setItemDetail("울 100%");
		item.setIntemSEllStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		Item savedItem = itemRepo.save(item);
		System.out.println(savedItem.toString());
	}
	
	//상품 10개 저장
	
	public void createItemList() {
		for(int i=1; i<=10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품 " + i);
			item.setPrice(120000);
			item.setItemDetail("테스트 상품 울 100% " + i);
			item.setIntemSEllStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepo.save(item);
		}
	}
	@Test
	public void findByItemNmTest() {
		this.createItemList();
		List<Item> itemList = itemRepo.findByItemNm("테스트 상품 4");
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	public void findByPriceLessThanOrderByPriceDescTest() {
		List<Item> itemList = itemRepo.findByPriceLessThanOrderByPriceDesc(10005);
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	
	
	
}
