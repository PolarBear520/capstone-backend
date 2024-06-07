
# API Endpoints

## Authentication

### User Registration
- **Endpoint**: `/api/register`
- **Method**: POST
- **Description**: Registers a new user.
- **Request Body**:
  ```json
  {
    "username": "string",
    "email": "string",
    "password": "string"
  }

### User Login
- **Endpoint**: `/api/login`
- **Method**: POST
- **Description**: Authenticates a user and returns a token.
- **Request Body**:
  ```json
  {
    "email": "string",
    "password": "string"
  }

### Reset Password
- **Endpoint**: `/api/reset-password`
- **Method**: POST
- **Description**: Sends a password reset email to the user.
- **Request Body**:
  ```json
    {
    "email": "string"
    }


## Products

### Get All Products
- **Endpoint**: `/api/products`
- **Method**: GET
- **Description**: Retrieves a list of all products.
- **Request Body**:
  ```json
    [
    {
        "id": "integer",
        "name": "string",
        "description": "string",
        "price": "number",
        "category": "string"
    }
    ]


### Get Product by ID
- **Endpoint**: `/api/products/{id}`
- **Method**: GET
- **Description**: Retrieves a product by its ID.
- **Request Body**:
  ```json
    {
    "id": "integer",
    "name": "string",
    "description": "string",
    "price": "number",
    "category": "string"
    }

### Search Products
- **Endpoint**: `/api/products/search`
- **Method**: GET
- **Description**: Searches for products based on query parameters.
- **Query Parameters**:
    - **q**: Search term
    - **category**: Filter by category
- **Request Body**:
  ```json
    [
    {
        "id": "integer",
        "name": "string",
        "description": "string",
        "price": "number",
        "category": "string"
    }
    ]

## Orders
### Create Order
- **Endpoint**: `/api/orders`
- **Method**: POST
- **Description**: Creates a new order.
- **Request Body**:
    ```json
    {
    "user_id": "integer",
    "product_ids": ["integer"],
    "total_price": "number"
    }


### Get Orders by User ID
- **Endpoint**: `/api/orders/user/{user_id}`
- **Method**: GET
- **Description**: Retrieves all orders for a specific user.
- **Response**:
    ```json
    [
    {
        "order_id": "integer",
        "user_id": "integer",
        "products": [
        {
            "id": "integer",
            "name": "string",
            "price": "number"
        }
        ],
        "total_price": "number",
        "order_date": "string"
    }
    ]

## User Management
### Get User Profile
- **Endpoint**: `/api/users/{id}`
- **Method**: GET
- **Description**: Retrieves the profile information of a user.
- **Response**:
    ```json
    {
    "id": "integer",
    "username": "string",
    "email": "string",
    "roles": ["string"]
    }

### Update User Profile
- **Endpoint**: `/api/users/{id}`
- **Method**: PUT
- **Description**: Updates the profile information of a user.
- **Request Body**:
    ```json
    {
    "username": "string",
    "email": "string",
    "roles": ["string"]
    }

## Messages
### Send Message
- **Endpoint**: `/api/messages`
- **Method**: POST
- **Description**: Sends a message to another user.
- **Request Body**:
    ```json
    {
    "sender_id": "integer",
    "receiver_id": "integer",
    "content": "string"
    }

### Get Messages for User
- **Endpoint**: `/api/messages/user/{user_id}`
- **Method**: GET
- **Description**: Retrieves all messages for a specific user.
- **Response**:
    ```json
    [
    {
        "message_id": "integer",
        "sender_id": "integer",
        "receiver_id": "integer",
        "content": "string",
        "timestamp": "string"
    }
    ]

    


