# Inventory Management Web Application

This is a web application for managing inventory using Java Spring Boot and MySQL. Users can add, delete, and update products and parts. This README file provides instructions on setting up the MySQL database and running the application using IntelliJ IDEA.

## Features
- Add, delete, and update products
- Add, delete, and update parts
- View product and part details
- Relationship between products and parts

## Prerequisites
- Java 11 or higher (SDK 17)
- Maven
- H2
- IntelliJ IDEA

## Getting Started

### Setting up IntelliJ IDEA

1. **Install IntelliJ IDEA:**
   Download and install IntelliJ IDEA from the official website: [IntelliJ IDEA Downloads](https://www.jetbrains.com/idea/download/).

2. **Import the project:**
   - Open IntelliJ IDEA.
   - Select `File -> New -> Project from Existing Sources`.
   - Choose the root directory of the project.
   - Select `Import project from external model` and choose `Maven`.
   - Click `Finish`.


3. **Run the application:**
   - Open the `ProjectCrudApplication.java` file located in `src/main/java/com/example/projectCRUD/`.
   - Right-click on the file and select `Run 'ProjectCrudApplication'`.

## API Endpoints

### Product Endpoints

- **Add a product:**
  - `POST /api/products`
  - Request body: JSON representation of the product

- **Delete a product:**
  - `DELETE /api/products/{id}`
  - Path variable: `id` (ID of the product to be deleted)

- **Update a product:**
  - `PUT /api/{productId}/addPart/{partId}`
  - Pdoduct update with parts 
  - Request body: JSON representation of the updated product

### Part Endpoints

- **Add a part:**
  - `POST /api/parts`
  - Request body: JSON representation of the part

- **Delete a part:**
  - `DELETE /api/parts/{id}`
  - Path variable: `id` (ID of the part to be deleted)

- **Update a part:**
  - `PUT /api/parts/{id}`
  - Path variable: `id` (ID of the part to be updated)
  - Request body: JSON representation of the updated part

## Built With
- Java Spring Boot
- H2
- Maven


## see the application
http://localhost:8080/index.html
simply type the URL to to see the application

![image](https://github.com/Madhunicka/Inventory_management_Mysql_SoringBoot_Java/assets/77634975/413c5a96-71d2-4244-8b5f-a61651664027)
![image](https://github.com/Madhunicka/Inventory_management_Mysql_SoringBoot_Java/assets/77634975/b7fae20c-a540-4572-8463-ec1b2af4b9d0)
![image](https://github.com/Madhunicka/Inventory_management_Mysql_SoringBoot_Java/assets/77634975/d7b9e6a3-ea77-4e22-a16f-dcb76be5ed03)
![image](https://github.com/Madhunicka/Inventory_management_Mysql_SoringBoot_Java/assets/77634975/3f59860e-769d-43ab-870b-d31288bd224f)
![image](https://github.com/Madhunicka/Inventory_management_Mysql_SoringBoot_Java/assets/77634975/04505922-96ab-46e8-a243-3aa0440b55ec)
![image](https://github.com/Madhunicka/Inventory_management_Mysql_SoringBoot_Java/assets/77634975/74df4977-7f0c-463d-9553-d3ef4956ae9d)
![image](https://github.com/Madhunicka/Inventory_management_Mysql_SoringBoot_Java/assets/77634975/db57a43f-0344-48aa-b9e4-79dca214f90c)


## Authors
- Madhunicka M. - Initial work

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments
- Thanks to the Spring Boot and MySQL communities for their excellent documentation and support.

---

Please replace `Madhunicka M.` with your actual name and customize any other details as necessary for your specific project.
