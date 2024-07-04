# Database Design



[TOC]



---

## Equirements Analysis

**Goal**: Design a database for an e-commerce platform, supporting user management, role management, product management, order management, category management, and message management. Specific requirements include:

- **User Management**: Users can register and log in, including information such as username, password, email, and phone number.
- **Role Management**: The system supports different roles (e.g., buyers, sellers), and users can have one or more roles.
- **Product Management**: Sellers can upload and manage products, including product name, description, price, status, etc.
- **Order Management**: Buyers can place orders, and the system records order information, including order date, status, etc.
- **Category Management**: Products can belong to one or more categories, supporting category management.
- **Message Management**: Users can send messages to each other, recording message content and status.

---

## Conceptual Design

**Entities and Relationships**:

- **Users**: User registration information
- **Roles**: System role information (e.g., buyer, seller)
- **UserRoles**: Many-to-many relationship between users and roles
- **Products**: Information about products uploaded by users
- **Orders**: Information about orders placed by users
- **Categories**: Information about product categories
- **ProductCategories**: Many-to-many relationship between products and categories
- **Messages**: Information about messages sent between users

---

## Table Explanations

### Users

- **UserID** (Primary Key)
- **Username** (Unique)
- **Password**
- **Email** (Unique)
- **PhoneNumber**
- **RegistrationDate**



### Roles

- RoleID (Primary Key)
- RoleName (e.g., "Buyer", "Seller")



### UserRoles

- UserID (Foreign Key, references UserID in Users table)
- RoleID (Foreign Key, references RoleID in Roles table)



### Products

- ProductID (Primary Key)
- ProductName
- Description
- Price
- UploadDate
- SellerID (Foreign Key, references UserID in Users table)
- ProductStatus (Available/Sold/Removed)



### Orders

- OrderID (Primary Key)
- BuyerID (Foreign Key, references UserID in Users table)
- ProductID (Foreign Key, references ProductID in Products table)
- OrderDate
- OrderStatus (Pending/Completed/Cancelled)



### Categories

- CategoryID (Primary Key)
- CategoryName



### ProductCategories

- ProductID (Foreign Key, references ProductID in Products table)
- CategoryID (Foreign Key, references CategoryID in Categories table)



### Messages

- MessageID (Primary Key)
- SenderID (Foreign Key, references UserID in Users table)
- ReceiverID (Foreign Key, references UserID in Users table)
- Content
- SendDate
- IsRead

---

## Maintenance and Optimization

### Backup and Recovery
Regular backups are essential to prevent data loss in case of system failures or errors. It's important to schedule regular backups and test the recovery process to ensure that backup data can be restored reliably.

### Index Optimization
Indexes improve the performance of database queries by enabling faster data retrieval. It's important to analyze query patterns and create indexes on columns frequently used in queries. Regular maintenance, such as rebuilding indexes, helps maintain their efficiency.

### Query Optimization
Optimizing database queries involves writing efficient SQL queries, avoiding unnecessary joins and subqueries, and using indexes effectively. It's also important to review and optimize the database schema to ensure it meets the application's performance requirements.



## Physical Design

---

-- Create Users table
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20),
    RegistrationDate DATETIME NOT NULL
);

-- Create Roles table
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY AUTO_INCREMENT,
    RoleName VARCHAR(255) NOT NULL
);

-- Create UserRoles table
CREATE TABLE UserRoles (
    UserID INT,
    RoleID INT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID),
    PRIMARY KEY (UserID, RoleID)
);

-- Create Products table
CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(255) NOT NULL,
    Description TEXT,
    Price DECIMAL(10, 2) NOT NULL,
    UploadDate DATETIME NOT NULL,
    SellerID INT,
    ProductStatus ENUM('Available', 'Sold', 'Removed') NOT NULL,
    FOREIGN KEY (SellerID) REFERENCES Users(UserID)
);

-- Create Orders table
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    BuyerID INT,
    ProductID INT,
    OrderDate DATETIME NOT NULL,
    OrderStatus ENUM('Pending', 'Completed', 'Cancelled') NOT NULL,
    FOREIGN KEY (BuyerID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Create Categories table
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryName VARCHAR(255) NOT NULL
);

-- Create ProductCategories table
CREATE TABLE ProductCategories (
    ProductID INT,
    CategoryID INT,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID),
    PRIMARY KEY (ProductID, CategoryID)
);

-- Create Messages table
CREATE TABLE Messages (
    MessageID INT PRIMARY KEY AUTO_INCREMENT,
    SenderID INT,
    ReceiverID INT,
    Content TEXT NOT NULL,
    SendDate DATETIME NOT NULL,
    IsRead BOOLEAN NOT NULL,
    FOREIGN KEY (SenderID) REFERENCES Users(UserID),
    FOREIGN KEY (ReceiverID) REFERENCES Users(UserID)
);



## Database deployment

---

![Database deployment](./image/DatabaseDeployment.png)



## Remote access

---

![Remote access](./image/RemoteAccess.png)



## Docker & Redis

---

![](./image/Docker&Redis.png)

