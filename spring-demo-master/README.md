# Price Comparator Backend
A Spring Boot REST API

---
## Features Implemented

### 1. **Import Product Prices from CSV**
- Load product price data (with quantities and units) from multiple stores.

### 2. **Shopping Basket Optimizer**
- Submit a list of product IDs and quantities.
- Returns a per-store optimized basket with the lowest total price.
- Applies active discounts automatically.

### 3. **Discounts**
- Store discounts with active periods and percentages.
- View "Best Discounts" (top 10) active discounts.
- View newly added discounts (last 24h).

### 4. **Price History API**
- Returns price evolution over time for a product (optionally filtered by store).
- Optionally includes store name for graph plotting.

### 5. **Product Substitutes & Recommendations**
- For a given product, returns cheaper alternatives in the same category.
- Based on unit price (Ron/L, Ron/Kg, etc).

### 6. **Custom Price Alerts**
- Users can register price alerts per product.
- Automatically triggers an alert when price drops below a given threshold.
- Alerts are triggered immediately after price updates.

---

## How to Build and Run the Application

### Requirements:
- Java 8
- Maven

### Run locally:
```bash
# clone the repo
https://github.com/dabidnuna/Price-Comparator-Backend.git

# I used PostgreSQL for the database, so edit the password in application.properties to match your database

# navigate into the project
cd Price-Comparator-Backend

# build the project
mvn clean install

# run the app
mvn spring-boot:run
```

### Swagger UI:
After starting the app, open:
```
http://localhost:8080/swagger-ui.html
```

---

## Assumptions & Simplifications
- Product data is uniquely identified by a `productId`.
- A price is valid for a single store on a specific date.
- Discounts are stored separately and linked to a product+store.
- Only the latest price per product+store is considered current.
- User management/authentication is not implemented (alerts use email only).

---

## Project Structure (src/main/java)

```
ro.tuc.ds2020
├── controllers         # REST controllers
├── dtos                # Request/response DTOs
├── entities            # JPA entities
├── repositories        # Spring Data JPA repositories
├── services            # Business logic and alert triggers
├── PriceComparatorApp  # Main application class
```

---
## Architecture

This project follows a clean, modular **3-layer architecture**:

- **Controller Layer**: Exposes REST API endpoints and handles HTTP requests/responses.
- **Service Layer**: Contains business logic such as:
  - Optimizing shopping baskets
  - Triggering alerts
  - Filtering discounts or price history
- **Repository Layer**: Abstracts access to the database using Spring Data JPA.
- **Entity Layer**: Defines the data model stored in the database (Product, PriceEntry, Discount, etc.).
- **DTO Layer**: Used for input/output objects to keep API and database cleanly separated.

The project is designed to be:
- Extensible (e.g. for authentication, scheduled jobs)
- Testable (each layer can be tested independently)
- Maintainable (clear separation of concerns)

---

## API Endpoints (with examples)

### 1. Daily Shopping Basket Monitoring (Optimize Shopping Basket)
```http
POST /api/shopping/optimize
```
```json
[
  { "productId": "P001", "quantity": 2 },
  { "productId": "P007", "quantity": 1 }
]
```

---

### 2. Best Discounts
```http
GET /api/discounts/best
```

### 3. New Discounts
```http
GET /api/discounts/new
```

### 4. Dynamic Price History Graphs
```http
GET /api/prices/history/graph?productId=P001
GET /api/prices/history/graph/stores?productId=P001
```

### 5. Product Substitutes & Recommendations
```http
GET /api/recommendations/better-alternatives?productId=P001
```

### 6. Custom Price Alert
##### 6.1. Create Price Alert
```http
POST /api/alerts
```
```json
{
  "productId": "P001",
  "userEmail": "david@example.com",
  "targetPrice": 7.50
}
```
#### 6.2. Add new price for the product
```http
POST http://localhost:8080/api/prices/add
```
```json

{
  "productId": "P001",
  "storeName": "Lidl",
  "price": 7.40,
  "currency": "RON"
}
```
#### 6.3. Check Triggered Alerts
```http
GET /api/alerts/triggered
```

---

## Final Notes
- All endpoints can be tested from Swagger UI.
- CSV imports can be done manually or automated in future iterations.
- Alerts, discounts and prices are easily extensible for user authentication or notification via email.

---
