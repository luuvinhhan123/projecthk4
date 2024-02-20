package com.aptech.eProject.responses.product;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
public class ProductResponse {
	private int id;
	private String title;
}
