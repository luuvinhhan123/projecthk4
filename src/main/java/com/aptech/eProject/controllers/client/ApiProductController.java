package com.aptech.eProject.controllers.client;

import com.aptech.eProject.controllers.ApiController;
import com.aptech.eProject.models.Product;
import com.aptech.eProject.responses.ApiResponse;
import com.aptech.eProject.responses.product.ProductResponse;
import com.aptech.eProject.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ApiProductController extends ApiController {
	@Autowired
	ApiResponse apiResponse;

	@Autowired
	ProductService productService;

	@GetMapping("")
	public ResponseEntity<?> getAll() throws Exception {
		try {
			List<ProductResponse> products = productService.searchAll("");

			return apiResponse.ok("ok", products);
		} catch (Exception e) {
			return apiResponse.failure(e.getMessage());
		}
	}
}
