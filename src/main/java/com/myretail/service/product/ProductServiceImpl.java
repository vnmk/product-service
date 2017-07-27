package com.myretail.service.product;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.myretail.domain.product_price.ProductPrice;
import com.myretail.domain.product_price.ProductPriceDA;
import com.myretail.redskyservice.client.ProductServiceClient;

/**
 * Implementation class for the Product Service
 * 
 * @author VenkataRaidu
 *
 */
public class ProductServiceImpl implements ProductService {

	@Override
	public Product getProduct(long id) {

		// Gets product information from RESTful service of redsky.target.com
		JSONObject jsonProduct;
		try {
			jsonProduct = ProductServiceClient.getInstance().getProduct(id);
		} catch (ParseException e) {
			
			//TODO - log the exception.
			
			throw new InternalServerErrorException();
		}

		// If the above response is null, throw exception to the client
		if (jsonProduct == null) {
			throw new NotFoundException();
		}

		// Parsing the JSON object received
		JSONObject item = (JSONObject) jsonProduct.get("item");
		JSONObject description = (JSONObject) item.get("product_description");
		String title = description.get("title").toString();

		// Get product price from DAO layer
		ProductPrice price = ProductPriceDA.getInstance().getProductPrice(id);

		Product product = new Product();
		product.setId(id);
		product.setName(title.toString());
		product.setPrice(product.new CurrentPrice(price.getCurrentValue(), price.getCurrencyCode()));

		return product;

	}

	@Override
	public Response updateProduct(Product product) {

		// If the input is null, throw exception to the client
		if (product == null) {
			throw new NotFoundException();
		}

		ProductPrice price = new ProductPrice(product.getId(), product.getPrice().getValue(),
				product.getPrice().getCurrency());

		//Update product price in the database
		ProductPriceDA.getInstance().updateProductPrice(price);

		return Response.ok().build();
	}

}
