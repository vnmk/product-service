package com.myretail.redskyservice.client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Client that calls the product web service of redsky.target.com to get product
 * information.
 * 
 * @author VenkataRaidu
 *
 */
public class ProductServiceClient {

	private static final String REDSKY_PRODUCT_SERVICE_URL = "http://redsky.target.com/v2/pdp/";
	private static ProductServiceClient client = null;

	private ProductServiceClient() {
	}

	public static ProductServiceClient getInstance() {

		if (client == null)
			client = new ProductServiceClient();

		return client;
	}

	public JSONObject getProduct(long id) throws ParseException {

		JSONObject product = null;

		Client client = Client.create();
		String getProductRequest = REDSKY_PRODUCT_SERVICE_URL + "tcin/" + id;
		WebResource webResource = client.resource(getProductRequest);

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.getEntity(String.class);

		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(output);
			product = (JSONObject) json.get("product");
		
		} catch (ParseException e) {
			throw e;
		} finally {
			response.close();
		}

		return product;
	}

	

}
