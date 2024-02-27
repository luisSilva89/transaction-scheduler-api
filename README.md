# About The Project

## Transaction Scheduler App

An API that enables the saving, editing, deletion, and updating of scheduled bank transactions.
For each submitted scheduled transaction, a fee will be applied based on the amount and the scheduling date.

## Built With

- Technologies/Frameworks:
    - Java 17
    - Spring Boot
    - Spring Data JPA
    - Spring Web
    - PostgreSQL

- Tools:
    - Maven

- Development Tools:
    - Lombok
    - PgAdmin4
    - Postman

- Testing Frameworks:
    - JUnit

- Other Libraries:
    - ModelMapper

# Getting Started

## Setting the Program Running

## Using the Program

To interact with the application, you can use Postman to submit various requests:

-  **Read All Scheduled Transactions**

Retrieve all scheduled transactions associated with a specific client account ID.

**Method:** GET

**Endpoint:**

`http://localhost:8080/v1/transaction/allTransactions/<client_account_id>`

Replace <client_account_id> with the desired client account ID.

- **Add A New Scheduled Transaction**

Add a new scheduled transaction for a specific client account.

**Method:** POST

**Endpoint:**

`http://localhost:8080/v1/transaction`

**Request Body:**

```json
{
  "clientAccountId": "<client_account_id>",
  "amount": "<your_desired_amount>",
  "dueDate": "<your_desired_due_date>",
  "transactionType": "Transfer"
}
```

Replace <client_account_id>, <your_desired_amount>, and <your_desired_due_date> with appropriate values.   
<your_desired_due_date> must be in "yyyy-mm-dd format", for example: 2024-03-14.

- **Modify A Scheduled Transaction**

Modifies a scheduled transaction, as long as it is with status "Pending" and not "Executed"

**Method:** POST

**Endpoint**

`http://localhost:8080/v1/transaction`

**Request Body**

```json
{
  "id": "<scheduled_transaction_id>",
  "clientAccountId": "<client_account_id>",
  "amount": "<your_desired_amount>",
  "dueDate": "<your_desired_due_date>",
  "transactionType": "Transfer"
}
```

Replace <scheduled_transaction_id> with the desired scheduled transaction ID.  
Replace <client_account_id> with the desired client account ID.  
Replace <your_desired_amount>, and <your_desired_due_date> with appropriate values.  
<your_desired_due_date> must be in "yyyy-mm-dd" format, for example: 2024-03-14.
  
- **Delete A Scheduled Transaction**

Removes a scheduled transaction from a client account that does not have status "Executed".

**Method:** DELETE

**Endpoint:**

`http://localhost:8080/v1/transaction/<id>/client-account/<client_account_id>`

Replace id with the desired scheduled transaction ID.  
Replace <client_account_id> with the desired client account ID.