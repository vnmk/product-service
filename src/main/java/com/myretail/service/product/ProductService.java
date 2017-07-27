package com.myretail.service.product;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * RESTful service that can retrieve product and price details when provided
 * with the product ID.This service also allow the clients to update the current
 * price of products in the myretail.com data store. Response will be returned
 * in JSON format.
 * 
 * @author VenkataRaidu
 *
 */

@Produces({ "application/json" })
public interface ProductService {

	/**
	 * Gets product information for the product ID
	 * 
	 * @param id
	 *            the product ID
	 * @return Product the product detail in JSON format
	 */
	@GET
	@Path("/products/{id}/")
	Product getProduct(@PathParam("id") long id);

	/**
	 * Updates the current price of the product and the currency code.
	 * 
	 * @param product
	 * @return Response OK response if updated. Otherwise, throw exception
	 */
	@PUT
	@Path("/products/")
	@Consumes("application/json")
	Response updateProduct(Product product);

}