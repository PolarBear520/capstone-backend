
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



