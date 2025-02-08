Our Entire project for OOP SE-2408 Group Name:The Algorithm Avengers
# Device Store management System - "The Algorithm Avengers"

##  What is this Application?
This is a **Java-based console application** that allows users to **buy electronic devices** and lets employees **manage orders, users, and devices**. It has features like **user authentication, order management, product browsing, and reviews.**

---

## **Features of the Application**
### **For Users (Customers)**
**Create an account** to start shopping  
**Log in** using email and password  
**Browse products** (Categories, Brands, Devices)  
**Place an order** by selecting a device  
**Write a review** after purchasing a product

### **For Employees (Admins)**
**Login with a secret password**  
**Manage users** (View, delete, update users)  
**Manage devices** (View all available devices)  
**View all orders** and **full order details**  
**View all reviews** written by customers

---

## **How to Set Up and Run the Application**
### **1️ Prerequisites**
- **Java Development Kit (JDK) 11+**
- **PostgreSQL Database**
- **JDBC Driver for PostgreSQL**
- **A Java IDE** (Eclipse, IntelliJ, VS Code) or Terminal

### **2 Setting Up the Database**
1. Open **PostgreSQL** and create a new database:
   ```sql
   CREATE DATABASE device_store;
## How to Use the Application
management.Main Menu
markdown
Копировать
Редактировать
Welcome to Device Store 'The Algorithm Avengers'
1. For Users
2. For Employees
0. Exit
   User Flow (Customer)
   Register or Login.
   After login, you can:
   View categories, brands, and devices.
   Make an order by entering a device ID.
   Write a review for purchased devices.
   ## Example: Making an Order

Enter email: user@mail.com
Enter password: password123
Login successful! Your user ID: 3

1. See all categories
2. See all brands
3. See all devices
4. Make an order
0. Go back
   Enter option: 4

Enter device ID to purchase: 8
Confirm purchase (yes/no): yes
Order created successfully!

Would you like to leave a review? (yes/no): yes
Enter device id: 8
Enter rating (1-5): 5
Share with comments: "Great product!"
Review added successfully!
Employee Flow (Admin)
Enter the Employee Password (0123456789 by default).
Access features like:
View all users.
View orders with details (buyer and items).
Manage devices and users.
Example: Viewing Full Order Details


Enter option: 9
Enter Order ID: 4
Order ID: 4
Date: 2025-01-19 11:45:00
Status: Pending
Total Price: 249.99

Buyer Details:
User ID: 4
Name: Dinara
Email: dinara.nur@mail.com

Order Items:
- Device ID: 8
  Name: AirPods Pro 2
  Quantity: 1
  Price: 249.99
  ## Technologies Used
  Java 11+
  PostgreSQL
  JDBC
  Object-Oriented Programming (OOP)
  Design Patterns: Singleton, Factory, Strategy
  Lambda Expressions for Sorting and Filtering
  Key Features in Code
  ## 1. Data Validation
   Checks email format and password strength before creating users.
   Ensures correct input for IDs and numerical values.
   Prevents invalid operations (e.g., placing an order with an invalid ID).
  ## 2. Design Patterns Used:

**Singleton Pattern → Used in DatabaseConnection for database access.**

**Factory Pattern → Used for creating different types of users.**

**Strategy Pattern → Used for dynamic selection of payment methods.**

## Future Improvements
Implement order history for users.
Add admin features for adding/removing devices.
Integrate payment gateway for real transactions.
## Authors
**-Kundyz**

**-Daniyar**

**-Yerzat**

**-Abai**








