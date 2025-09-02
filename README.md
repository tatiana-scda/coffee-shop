# Coffee Shop

This project is a simple coffee shop application, where a client can request any number of available items.  
The system then calculates the total price, applies any relevant discounts, and displays the order summary.

---

## Running

You can start the system with:

- **Unix / Linux / macOS:**

```bash

make run-app
```
- **Windows**:

```bash

make run-appw
```

Then, access the UI in your browser at:

```
http://localhost:8080
```

## Requirements
### Non-functional requirements

    Usable while remaining as simple as possible

    Simple, readable, and maintainable

    Easily extendable

### Functional requirements

    Receive information about an order

    Correctly calculate the final result

    Apply any relevant discounts:

        Green Tea: Buy-one-get-one-free

        Strawberries: Price drops to €4.50 when buying 3 or more

        Coffees: Price of all coffees drops to 2/3 of original price if buying 3 or more

## Architecture

The system follows the Model-View-Controller (MVC) design pattern. MVC separates concerns into three main components, which simplifies system understanding, maintenance, and extension.
System

The service is accessed via a simple UI where clients input their order.
After confirming, they will see a summary of the order and the final price, with all relevant discounts applied.

## Enhancements

There is room to improve system resilience, security, and maintainability. Some possible enhancements include:

### Persistence

    Introducing a database can improve performance and scalability.

    Currently, products are stored in an enum; a database would be more flexible as complexity grows.

### Security

    Adding authentication (e.g., OAuth2) can secure the service.

    Clients would be validated using safe access tokens.

### Audit

    Logging user actions would allow tracking of all changes, linking actions to specific users.

    This guarantees data integrity and provides an audit trail.