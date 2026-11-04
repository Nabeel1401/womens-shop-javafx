# Women's Shop — JavaFX Inventory Management

A Java desktop application for managing a women's clothing shop, developed in **IntelliJ IDEA** using **JavaFX, FXML, JDBC and MySQL**.

Developed as an academic project at **ESILV**, the application combines an interactive interface with object-oriented product models and a database-access layer. It supports three product categories: **Clothes, Shoes and Accessories**.

**Developer:** Nabeel Ahmad KAMALUDEEN

## Technology Stack

| Area | Technologies |
|---|---|
| Language | Java |
| Desktop interface | JavaFX |
| Interface definition | FXML |
| Database | MySQL |
| Database connectivity | JDBC |
| Development environment | IntelliJ IDEA |
| Application structure | MVC principles and DAO pattern |

## Main Features

- Create, edit and delete products.
- Manage clothes, shoes and accessories.
- Purchase stock and sell available items.
- Apply and remove category-based discounts.
- Filter products by category.
- Sort products by sale price.
- Display capital, income and purchasing costs.
- Validate quantities, prices, sizes and stock restrictions.

## Feature Walkthrough

### 1. Add Products

Create products by entering their name, category, purchase price, sale price and size where applicable.

New products start with **zero stock**. Stock increases through the purchase operation.

<img width="1010" height="554" alt="image" src="https://github.com/user-attachments/assets/1349c061-21e5-444a-bade-ac7a83dfb0a1" />
<p align="center">
  <em>Add Product window showing fields for name, category, buy price, sell price and size</em>
</p>

### 2. Edit Products

Update a product's name, purchase price and size.

The category and sale price remain fixed after creation, following the assignment's business rules.

<img width="1014" height="465" alt="image" src="https://github.com/user-attachments/assets/c10fbe24-e5e4-46d2-8c64-ae05fcf41467" />
<p align="center">
  <em>Edit Product window with sale price and category disabled</em>
</p>

### 3. Delete Products

Products can be deleted only when their stock is zero. A confirmation dialog helps prevent accidental deletion.

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/118b42bc-8055-407e-b471-5ea9db8f334a" width="100%" alt="Delete confirmation">
      <br>
      <em>Confirmation dialog asking the user to confirm deletion of the selected product.</em>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/2f3b6c9f-4b9d-4866-a14f-a95cd61db631" width="100%" alt="Deletion successful">
      <br>
      <em>Message shown after the product is deleted successfully.</em>
    </td>
  </tr>
</table>

### 4. Purchase Stock

Enter the quantity to purchase. The application checks the quantity and available capital before updating stock and financial values.

Purchases increase stock and cost while reducing available capital.

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/0b0839c6-928b-418b-a5a7-30d67335fa01" width="100%" alt="Delete confirmation">
      <br>
      <em>Purchase dialog asking for quantity to purchase</em>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/a1a6938d-74f5-410d-886f-4217b151b62e" width="100%" alt="Deletion successful">
      <br>
      <em>Product stock and financial values updated after purchase</em>
    </td>
  </tr>
</table>


### 5. Sell Products

Sell items from available stock. Sales reduce stock and increase income and capital.

When a discount is active, the discounted price is used. Otherwise, the original sale price applies.

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/42172916-0443-43a7-b287-e70c4e7bbb14" width="100%" alt="Delete confirmation">
      <br>
      <em>Sell dialog asking for quantity to sell</em>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/47d9a5ab-3589-4406-a075-e87c6137d517" width="100%" alt="Deletion successful">
      <br>
      <em>Sell dialog showing the units sold</em>
    </td>
  </tr>
</table>

### 6. Filter by Category

Display all products or restrict the table to Clothes, Shoes or Accessories.

<img width="1020" height="407" alt="image" src="https://github.com/user-attachments/assets/1d3d6ef9-94fd-4af3-a774-c6eb1032edb0" />
<p align="center">
  <em>Product table filtered by selected category
</em>
</p>

### 7. Apply and Remove Discounts

Each category has a predefined discount:

| Category | Discount |
|---|---:|
| Clothes | 30% |
| Shoes | 20% |
| Accessories | 50% |

Applying a discount updates the discount price without changing the original sale price. Removing it resets the discount price to zero.

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/0d08dd98-545f-4b2b-b91d-a45e50fd419e" width="100%" alt="Delete confirmation">
      <br>
      <em>Product before applying the discount, where the discount price is zero</em>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/0e9d5933-f4d7-4c1d-93ed-d88324c44271" width="100%" alt="Deletion successful">
      <br>
      <em>Discount applied showing updated discount price while sale price remains unchanged/em>
    </td>
  </tr>
</table>

### 8. Sort by Price

Display products in ascending order of sale price.

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/93d87fa5-9b58-4ccb-b95c-5536aa3f4a34" width="100%" alt="Delete confirmation">
      <br>
      <em>Product list before sorting by price</em>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/bafe1498-0f10-4cb2-94fa-1fe28b7efd40" width="100%" alt="Deletion successful">
      <br>
      <em>Product list after sorting in ascending order by sale price/em>
    </td>
  </tr>
</table>

### 9. Track Financial Values

The interface displays capital, income and cost, updating them after purchases and sales.

```text
Capital = Initial Capital + Income - Cost
```

The project scenario starts with **€30,000** in capital.

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/9d3b2bb8-7c5b-4452-8db4-044303bfc56d" width="100%" alt="Delete confirmation">
      <br>
      <em>Financial section showing capital, income, and cost on the main interface</em>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/ac822f09-34a7-4f7f-af73-136eba8256d4" width="100%" alt="Deletion successful">
      <br>
      <em>Financial values after purchasing an item/em>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/577761de-c743-40b9-8403-a4c2ac4b011e" width="100%" alt="Deletion successful">
      <br>
      <em>Financial values after selling an item/em>
    </td>
  </tr>
</table>

## Object-Oriented Design

The project demonstrates the following Java concepts:

| Concept | Implementation |
|---|---|
| Abstraction | Abstract `Product` class containing shared product attributes and behaviour |
| Inheritance | `Clothes`, `Shoes` and `Accessories` extend `Product` |
| Encapsulation | Product fields are accessed through methods |
| Interfaces | `Discount` defines discount operations; `Comparable<Product>` defines price comparison |
| Polymorphism | Different product categories are handled through the `Product` type |
| Separation of responsibilities | Models, FXML views, controllers and database-access classes |

### Application Layers

- **Models:** product data, stock operations and business rules.
- **Views:** FXML files defining the interface.
- **Controllers:** user interactions and interface updates.
- **DAO:** database queries and product persistence.

## Source Structure

```text
src/
├── Main.java
└── womenshop/
    ├── controller/
    │   ├── AddProductController.java
    │   └── MainController.java
    ├── dao/
    │   ├── DBConnection.java
    │   └── ProductDAO.java
    ├── model/
    │   ├── Accessories.java
    │   ├── Clothes.java
    │   ├── Discount.java
    │   ├── Product.java
    │   └── Shoes.java
    └── view/
        ├── AddProductView.fxml
        └── MainView.fxml
```

`Main.java` declares the `womenshop` package and launches the JavaFX application.

## Database

The application connects to a MySQL database named `womenshop`.

Product data is stored in three tables:

- `clothes`
- `shoes`
- `accessories`

The tables store product identifiers, names, purchase prices, sale prices, discount prices, stock quantities and sizes where applicable.

Database operations are separated from the interface through `DBConnection` and `ProductDAO`.

> Product data is persisted in MySQL. Capital, income and cost are maintained in application memory and are not persisted between application sessions.

## Business Rules

- Product names must not be empty.
- Prices must not be negative.
- The product form rejects a purchase price greater than the sale price.
- Purchase and sale quantities must be positive.
- Sales cannot exceed available stock.
- Purchases cannot exceed available capital.
- Products with remaining stock cannot be deleted.
- Category and sale price cannot be changed through the edit form.
- Stock is managed through purchase and sale operations.
- Clothing sizes range from **34 to 54**.
- Shoe sizes range from **36 to 50**.

## Running Locally

This repository contains the application source code. It requires local JavaFX and MySQL configuration before it can run.

### Requirements

- JDK 21.
- JavaFX SDK 21.
- MySQL Server.
- MySQL Connector/J.
- IntelliJ IDEA or another Java development environment.

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/Nabeel1401/womens-shop-javafx.git
   cd womens-shop-javafx
   ```

2. Open the project in IntelliJ IDEA and select JDK 21.

3. Mark `src` as the Sources Root if necessary.

4. Add the JavaFX SDK libraries and MySQL Connector/J to the module dependencies.

5. Create the `womenshop` database and the tables expected by `ProductDAO`.

   **A database initialization script is not currently included.**

6. Configure the local database URL, username and password in `DBConnection.java`. Never commit real database credentials.

7. Create an Application run configuration with:

   ```text
   Main class: womenshop.Main
   ```

8. Set the VM options, replacing the example path with your JavaFX SDK location:

   ```text
   --module-path "PATH_TO_JAVAFX_SDK/lib" --add-modules javafx.controls,javafx.fxml
   ```

9. Ensure the FXML files are copied to the application output while preserving their `womenshop/view/` path.

10. Start MySQL, then run the application.

## Current Limitations

- Database creation and dependency configuration are manual.
- No Maven or Gradle build configuration is currently included.
- Financial totals are not persisted between application sessions.
- Screenshots show the application demonstrated in the project report; they do not represent a new verification of the current local setup.

## Author

**Nabeel Ahmad KAMALUDEEN**

- [GitHub](https://github.com/Nabeel1401)
- [LinkedIn](https://www.linkedin.com/in/nabeelahmad14)

Academic project developed at **ESILV**.
