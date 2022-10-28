package com.shop.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.dto.OrderDto;
import com.shop.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class OrderController {

	private final OrderService orderService;
	
	 //주문하기
	   @PostMapping("/order")
	   public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto,
	         BindingResult bindingResult, Principal principal){
	      //유효성 검증
		   if(bindingResult.hasErrors()) {
			   StringBuilder sb = new StringBuilder();
			   List<FieldError> fieldErros = bindingResult.getFieldErrors();
			   for(FieldError fieldError : fieldErros) {
				   sb.append(fieldError.getDefaultMessage());
			   }
		   }
		   
		   
	      String email = principal.getName();	//로그인 회원
	      Long orderId;							//주문 번호
	      try {
	         orderId = orderService.order(orderDto, email);	//주문 로직 호출
	      }catch(Exception e) {
	         return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
	      }
	      
	      
	      return new ResponseEntity(orderId, HttpStatus.OK);
	      
	   }
	}