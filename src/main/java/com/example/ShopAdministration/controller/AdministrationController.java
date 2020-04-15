package com.example.ShopAdministration.controller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.ShopAdministration.entity.Food;
import com.example.ShopAdministration.entity.Nonfood;
import com.example.ShopAdministration.entity.Product;
import com.example.ShopAdministration.entity.ProductList;

@Controller
@RequestMapping("/admin")
public class AdministrationController {
	
	@GetMapping("/home")
	public String home(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		ProductList list = restTemplate.getForObject("http://localhost:8090/shop/products/", ProductList.class);
		List<Product> theProducts = list.getList();
		model.addAttribute("products", theProducts);
		model.addAttribute("food", new Food());
		model.addAttribute("nonfood", new Nonfood());
		return "home";
	}
	
	@PostMapping("/addFoodToDB")
	public String addFoodToDB(@ModelAttribute("food") Food food) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<?> entity = new HttpEntity<Object>(food,headers);
	    ResponseEntity<Product> responseEntity = restTemplate.exchange("http://localhost:8090/shop/products/food", HttpMethod.POST, entity, Product.class);
		return "redirect:/admin/home";
	}
	
	@PostMapping("/addNonfoodToDB")
	public String addNonfoodToDB(@ModelAttribute("nonfood") Nonfood nonfood) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<?> entity = new HttpEntity<Object>(nonfood,headers);
	    ResponseEntity<Product> responseEntity = restTemplate.exchange("http://localhost:8090/shop/products/nonfood", HttpMethod.POST, entity, Product.class);
		return "redirect:/admin/home";
	}
	
	@GetMapping("/deleteProductFromDB")
	public String deleteProduct(@RequestParam("productId")int theId) {
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<?> entity = new HttpEntity<Object>(theId,headers);
	    ResponseEntity<Integer> responseEntity = restTemplate.exchange("http://localhost:8090/shop/products/" + theId, HttpMethod.DELETE, entity, Integer.class);
		return "redirect:/admin/home";
	}

}
