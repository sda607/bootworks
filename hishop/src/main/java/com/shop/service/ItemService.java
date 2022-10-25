package com.shop.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemRepository itemRepo;
	private final ItemImgService itemImgService;
	private final ItemImgRepository itemImgRepo;
	
	//상품 저장(상품, 이미지)
	public Long saveItem(ItemFormDto itemFormDto, 
				List<MultipartFile> itemImgFileList) throws IOException {
		
		//상품 등록
		Item item = itemFormDto.createItem();
		itemRepo.save(item);
		
		
		//이미지 등록
		for(int i=0; i<itemImgFileList.size(); i++) {
			ItemImg itemImg = new ItemImg();
			itemImg.setItem(item);
			
			if(i == 0) {	//첫번째 이미지일 경우 대표 상품 이미지 값을 "Y"로 세팅
				itemImg.setRepimgYn("Y");
			}else {
				itemImg.setRepimgYn("N");
			}
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
		}
		return item.getId();
	}
	
	//상세 보기(entity -> dto)
	public ItemFormDto getItemDtl(Long itemId) {
		List<ItemImg> itemImgList = 
				itemImgRepo.findByItemIdOrderByIdAsc(itemId);
		List<ItemImgDto> itemImgDtoList = new ArrayList<>();
		for(ItemImg itemImg : itemImgList) {
			ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
			itemImgDtoList.add(itemImgDto);
		}
		
		Item item = itemRepo.findById(itemId)
				.orElseThrow(EntityNotFoundException::new);
		ItemFormDto itemFormDto = ItemFormDto.of(item);
		itemFormDto.setItemImgDtoList(itemImgDtoList);
		
		return itemFormDto;
	}
	
	//상품 수정
	public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException {
		Item item = itemRepo.findById(itemFormDto.getId())
				.orElseThrow(EntityNotFoundException::new);
		
		item.updateItem(itemFormDto);	//상품 수정
		
		//상품 이미지 아이디 조회
		List<Long> itemImgIds = itemFormDto.getItemImgIds();
		
		//이미지 파일 리스트를 파라미터로 전달
		for(int i=0; i<itemImgFileList.size(); i++) {
			itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
		}
		
		return item.getId();
	}
	
	
	
	
}
