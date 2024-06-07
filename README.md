
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



