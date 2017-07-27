Project Information:
-------------------
RESTful service to be hosted by a company named myretail, that can retrieve product and price details by ID.
This service communicates with a web service hosted by redsky.target.com to get product information.
Also, this service gets the current price of products from NoSql database MongoDB.
This information is returned to the service clients in the form of JSON response.
Additionally, clients can call this service to update the current price of products.


Required tools for running the project:
--------------------------------------
1. Eclipse 4.7 or higher
2. MongoDB v 3.4.6
3. Firefox plugin RESTClient
4. Maven
5. Apache Tomcat 8.5
6. Java JDK 1.8


Building and running the Project using Maven
---------------------------------------

Follow these steps for both myretail-dao and product-service projects starting with dao project.

1) Right CLick on the project in eclipse . Run as Maven --> Clean.  
2) Run as Maven --> install.
 
Artifacts generated
-------------------

1. myretail-dao-1.0.jar
2. product-service.war

Deploying web service
----------------------------------------
Deploy the artifact product-service.war on the Tomcat server and start the server if not started.


Accessing myretail.com product service to get product information and updating product price.
-------------------------------------------------------------------------------------
1. To Get Product information
-----------------------------
1.1. Open RESTClient on Firefox.
1.2. Select GET request type.
1.3. Put this URL in the request: http://localhost:8080/product-service/services/productservice/products/13860428

Result (Example):
 ----------------
 
Following response will be displayed under the tab Response Body (Highlight)

    {
        "id": 13860428,
        "name": "The Big Lebowski (Blu-ray)",
        "current_price":
        {
            "value": 1650,
            "currency_code": "USD"
        }
    }

2. To update Product price value and currency code
-----------------------------------------------

2.1. Open RESTClient on Firefox.
2.2. Create Content-Type:application/json and select this type from menu.
2.3. Select Put request from menu.
2.4. Put the folowing URL in the request: http://localhost:8080/product-service/services/productservice/products
2.5. Enter the following sample Json object in the body.
	{
        "id": 13860428,
        "name": "The Big Lebowski (Blu-ray)",
        "current_price":
        {
            "value": 1850,
            "currency_code": "USD"
        }
    }
    
 2.6. Send the request.
 
 Result:
 -------
 1. Receive Ok response from the web service.
 2. Price updated in the Mongo Database as per the request data.
 
