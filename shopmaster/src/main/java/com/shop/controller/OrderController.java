package com.shop.controller;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.dto.OrderDto;
import com.shop.dto.OrderHistDto;
import com.shop.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class OrderController {

	private final OrderService orderService;
	
	 //주문하기
	   @PostMapping("/order")
	   public @ResponseBody ResponseEntity<?> order(@RequestBody @Valid OrderDto orderDto,
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
	         return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	      }
	      
	      
	      return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	      
	   }
	   //주문 내역 처리
	   @GetMapping({"/orders", "/orders/{page}"}) //상단 메뉴(주문 내역)
		public String orderHist(@PathVariable("page") Optional<Integer> page,
				Principal principal, Model model) {
			Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);
			//principal.getName() - email
			Page<OrderHistDto> orderHistDtoList =
					orderService.getOrderList(principal.getName(), pageable); //주문 내역 로직 호출
			//orderHist.html로 model 보냄
			model.addAttribute("orders", orderHistDtoList);
			model.addAttribute("page", pageable.getPageNumber());
			model.addAttribute("maxPage", 5);
			return "order/orderHist";
		}
	   
	   //주문 취소
	   @PostMapping("/order/{orderId}/cancle")
	   public @ResponseBody ResponseEntity<?> cancelOrder(
			   @PathVariable("orderId") Long orderId, Principal principal){
		   //검증이 false이면 에러 처리
		   if(!orderService.validateOrder(orderId, principal.getName())) {
			   return new ResponseEntity<String>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);
		   }
		   
		   orderService.cancelOrder(orderId);
		   return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	   }
	   
	   
	}