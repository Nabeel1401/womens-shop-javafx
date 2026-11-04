# Women's Shop — JavaFX Inventory Management

A Java desktop application for managing a women's clothing shop, built with **JavaFX, FXML, JDBC and MySQL**.

Developed as an academic project at **ESILV**, the application combines an interactive interface with object-oriented product models and a database-access layer. It supports three product categories: **Clothes, Shoes and Accessories**.

**Developer:** Nabeel Ahmad KAMALUDEEN

## Application Preview

<!-- Insert REPORT FIGURE 5.4.2 here: main interface after a purchase.
Save the screenshot as screenshots/overview.png -->

![Women's Shop application overview](screenshots/overview.png)

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


![Add product form](screenshots/add-product.png)

### 2. Edit Products

Update a product's name, purchase price and size.

The category and sale price remain fixed after creation, following the assignment's business rules.

<img width="1014" height="465" alt="image" src="https://github.com/user-attachments/assets/c10fbe24-e5e4-46d2-8c64-ae05fcf41467" />


### 3. Delete Products

Products can be deleted only when their stock is zero. A confirmation dialog helps prevent accidental deletion.

<img width="497" height="231" alt="image" src="https://github.com/user-attachments/assets/a2e5a0e0-43c9-4a5b-b1ce-135747c8c51d" />


<img width="467" height="226" alt="image" src="https://github.com/user-attachments/assets/eb2e1139-dd20-490c-96d9-48a82f22b871" />


### 4. Purchase Stock

Enter the quantity to purchase. The application checks the quantity and available capital before updating stock and financial values.

Purchases increase stock and cost while reducing available capital.

<img width="480" height="224" alt="image" src="https://github.com/user-attachments/assets/48047cc7-977f-400c-9887-875ac84c8f49" />


### 5. Sell Products

Sell items from available stock. Sales reduce stock and increase income and capital.

When a discount is active, the discounted price is used. Otherwise, the original sale price applies.

<!-- Insert REPORT FIGURE 5.5.1 here.
Save as screenshots/sell-dialog.png -->

![Sale quantity dialog](screenshots/sell-dialog.png)

<!-- Insert REPORT FIGURE 5.5.2 from SECTION 5.5 here:
the sales confirmation, NOT the category-filter screenshot.
Save as screenshots/sale-success.png -->

![Successful sale](screenshots/sale-success.png)

### 6. Filter by Category

Display all products or restrict the table to Clothes, Shoes or Accessories.

<!-- Insert the screenshot from REPORT SECTION 5.6 here.
Its caption is mistakenly numbered Fig 5.5.2 in the report.
Save as screenshots/category-filter.png -->

![Products filtered by category](screenshots/category-filter.png)

### 7. Apply and Remove Discounts

Each category has a predefined discount:

| Category | Discount |
|---|---:|
| Clothes | 30% |
| Shoes | 20% |
| Accessories | 50% |

Applying a discount updates the discount price without changing the original sale price. Removing it resets the discount price to zero.

<!-- Insert REPORT FIGURE 5.7.1 here.
Save as screenshots/before-discount.png -->

![Product before applying a discount](screenshots/before-discount.png)

<!-- Insert REPORT FIGURE 5.7.2 here.
Save as screenshots/after-discount.png -->

![Product after applying a discount](screenshots/after-discount.png)

### 8. Sort by Price

Display products in ascending order of sale price.

<!-- Insert REPORT FIGURE 5.8.1 here.
Save as screenshots/before-sorting.png -->

![Products before sorting](screenshots/before-sorting.png)

<!-- Insert REPORT FIGURE 5.8.2 here.
Save as screenshots/after-sorting.png -->

![Products sorted by sale price](screenshots/after-sorting.png)

### 9. Track Financial Values

The interface displays capital, income and cost, updating them after purchases and sales.

```text
Capital = Initial Capital + Income - Cost
```

The project scenario starts with **€30,000** in capital.

<!-- Insert REPORT FIGURE 5.9.1 here.
Save as screenshots/financial-overview.png -->

![Financial overview](screenshots/financial-overview.png)

<!-- Insert REPORT FIGURE 5.9.2 here.
Save as screenshots/financial-after-purchase.png -->

![Financial values after purchasing](screenshots/financial-after-purchase.png)

<!-- Insert REPORT FIGURE 5.9.3 here.
Save as screenshots/financial-after-sale.png -->

![Financial values after selling](screenshots/financial-after-sale.png)

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
