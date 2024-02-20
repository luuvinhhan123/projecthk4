package com.aptech.eProject.services;

import com.aptech.eProject.models.Product;
import com.aptech.eProject.repositories.ProductRepository;
import com.aptech.eProject.responses.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Product findProductByTitle(String title) {
		return productRepository.findByTitle(title);
	}

	public Product findById(int id) {
		return productRepository.findById(id).get();
	}

	public Product update(Integer id, Product product) {
		try {
			Product exited = findById(id);
			if (exited != null) {
				product.setId(exited.getId());
				productRepository.save(product);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return product;
	}


	public Product create(Product product) {
		try {
			productRepository.save(product);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return product;
	}

	public Product detail(Integer id) {
		return productRepository.findById(id).get();
	}

	public boolean delete(Integer categoryId) {
		try {
			productRepository.deleteById(categoryId);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Search all project
	 *
	 * @return
	 */
	public List<ProductResponse> searchAll(String keyword) {
		List<Product> products = productRepository.findAll();
		List<ProductResponse> result = new ArrayList<>();

		for (Product item : products) {

			ProductResponse data = new ProductResponse();

			data.setId(item.getId());
			data.setTitle(item.getTitle());


			result.add(data);
		}

		return result;
	}

	/**
	 * @return
	 */
	public List<Product> searchProductByCategory(String categoryId) {
		return productRepository.findAll();
	}
}
