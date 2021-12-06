# Developer Assessment

##Description
The assessment comes with 3 JSON files. The files contain data for products, orders and payments.

Write a program that retrieves the following information given the provided files:
- Amount paid per user.
- Amount that each users still owes.

##Requirements
- The program should be written in Java.
- Write production-ready code.
- Document how to run the application.
- Publish the source code into GitHub (using your own personal account) and share it with us.
-------------------------------------------------------------------------------------------------------------------------------

## Introduction
Given the CoffeeShop application has following data sets.
-Products : Drinks available in different sizes and their prices. 
-Orders : User orders.
-Payment : User payments

Application exposed following APIs.
	
	1. 	Get Payments : get consolidated list of payments made by all users
		REST API : /coffeeshopservice/v1/payments
		HTTP Method : GET
		Response content-type : application/json
			
	2. Get Payment by user : get total payment amount made by given user
		REST API : /coffeeshopservice/v1/payments/{user}
		HTTP Method : GET
		Response content-type : application/json
		
	3. Get Balance : get consolidated list of balance due on all users # negative balance refers to extra payment by user
		REST API : /coffeeshopservice/v1/balances
		HTTP Method : GET
		Response content-type : application/json
		
	4. Get Balance by user : get total balance due on given user # negative balance refers to extra payment by user
		REST API : /coffeeshopservice/v1/balances/{user}
		HTTP Method : GET
		Response content-type : application/json
		
		** Open api specification can be found in below path after running the application **
		url : http://localhost:8080/swagger-ui.html
		
## Getting Started

###Installation Process
Ready with latest version of GIT, maven, Java 8+.

### Latest release  
0.0.1

### run application on local environment

	- Clone coffeeshop repository using below url on your command line or Git Bash.
		clone url : https://github.com/shrikantshri/coffeeshop.git
	- go inside coffeeshop project
		cd coffeeshop
	- install maven project
		mvn clean install
	- go to local maven repository
		 \.m2\repository\com\assessment\coffeeshop\0.0.1-SNAPSHOT
	- run built jar file
		java -jar coffeeshop-0.0.1-SNAPSHOT.jar
	- Check command line console for application started message
		ex : Started CoffeeShopApplication ...
	- Go to any web browser or Rest client(like Postman)
		Access api as mentioned in Introduction tag of README.md
		
 
## Assumption
 * Created REST APIs for the given requirement to allow easy access to required APIs.
 * Created collection and individual URIs for Payments and Balances, to allow access to all payments and balances and individual payment and balance.
 * /coffeeshopservice/v1/balances/{user} and /coffeeshopservice/v1/pauments/{user} APIs will throw exception with HTTP 404 if user is not present in the system.

## Contribute
 * API specification can be improved by adding schemas and error scenarios.
 * Feel free to provide any feedback to improve the application.
 