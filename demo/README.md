# Steps to Run the Application in Docker 
- Open terminal and go to the directory where your Spring Boot project (demo folder) is located.
- Run the following command to clean and package your Spring Boot project: ./mvnw clean package
- Create a Docker image by running the following command: docker build -t your-app-name .
- Start a Docker container with your application running on port 8080: docker run -p 8080:8080 your-app-name
- Use Postman or any HTTP client to send a POST request to http://localhost:8080/receipts/process.
### JSON Request Body 
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}
- After sending the request, you will receive an ID in the response. Copy the ID for the next step.
- Use the copied ID from the previous step to send a GET request to: http://localhost:8080/receipts/{ID}/points
- Replace {ID} with the actual ID returned in the previous response.
- You will receive the calculated reward points as the output.

# Access H2 console
- You also access the in memory database H2 using http://localhost:8080/h2-console
- In the H2 console, use the following JDBC URL and credentials:
- spring.datasource.url=jdbc:h2:mem:receiptProcessor
- spring.datasource.driver-class-name=org.h2.Driver
- spring.datasource.username=sa
- keep passwoed blank

# Stopping and Cleaning Up Docker Containers
- docker stop <container_id>
- docker rm <container_id>
- docker rmi your-app-name

