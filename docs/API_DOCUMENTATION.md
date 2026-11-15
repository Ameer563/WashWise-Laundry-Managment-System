# WashWise API Documentation

This reference is derived from the controller mappings in the uploaded source code.

**Local base URL:** `http://localhost:9542`

All five controllers declare `@CrossOrigin(origins = "http://localhost:3000")`.

## 1. Customer API

Controller: `CustomerController`

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/customer` | Create a customer |
| GET | `/customer` | List all customers |
| GET | `/customer/{cid}` | Retrieve a customer by ID |
| PUT | `/customer/{cid}` | Update a customer |
| DELETE | `/customer/{cid}` | Delete a customer |

Customer fields in the entity: `cid` (generated ID), `name`, `ph_no`, `address`.

Validation: `name` and `ph_no` must not be blank; `address` has a maximum length of 250 characters.

Example create request:

```http
POST http://localhost:9542/customer
Content-Type: application/json
```

```json
{
  "name": "Sample Customer",
  "ph_no": "9000000000",
  "address": "Sample address"
}
```

## 2. Laundry service API

Controller: `LaundrySeviceController`

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/service` | Create a laundry service |
| GET | `/service` | List all services |
| GET | `/service/{sid}` | Retrieve a service by ID |
| PUT | `/service/{sid}` | Update a service |
| DELETE | `/service/{sid}` | Delete a service |

Laundry service fields: `sid` (generated ID), `serviceName`, `price`.

Validation: `serviceName` must not be blank; `price` must be positive.

Example create request:

```http
POST http://localhost:9542/service
Content-Type: application/json
```

```json
{
  "serviceName": "Wash and Fold",
  "price": 50.0
}
```

## 3. Laundry order API

Controller: `LaundryOrderController`

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/order` | Create an order |
| GET | `/order` | List all orders |
| GET | `/order/{oid}` | Retrieve an order by numeric ID |
| GET | `/order/late` | List late orders |
| PUT | `/order/{oid}` | Update an order |
| DELETE | `/order/{oid}` | Delete an order |
| GET | `/order/{oid}/total` | Recalculate and retrieve order total |
| PUT | `/order/{oid}/status?status={status}` | Update order status |

Allowed status values in the status endpoint: `Pending`, `Processing`, `Ready`, `Delivered`.

When status becomes `Delivered`, the service sets `actualDeliveryDate` to the current date if it is null. When status changes to another value, it clears `actualDeliveryDate`.

Laundry order fields: `oid` (generated ID), `orderDate`, `actualDeliveryDate`, `expectedDeliveryDate`, `status`, `paymentStatus`, `totalAmount`, and `customer`.

The `customer` relationship is many-to-one. A create/update JSON body can represent the customer reference using the nested customer object expected by the entity, for example:

```json
{
  "orderDate": "2026-11-15",
  "expectedDeliveryDate": "2026-11-18",
  "status": "Pending",
  "paymentStatus": "Unpaid",
  "customer": {
    "cid": 1
  }
}
```

Use an existing customer ID. Verify this request against your running API before treating it as a tested example.

Status update example:

```http
PUT http://localhost:9542/order/1/status?status=Processing
```

## 4. Order item API

Controller: `OrderItemController`

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/orderitem` | Create an order item |
| GET | `/orderitem` | List all order items |
| GET | `/orderitem/{itemid}` | Retrieve an order item by ID |
| PUT | `/orderitem/{itemid}` | Update an order item |
| DELETE | `/orderitem/{itemid}` | Delete an order item |

Order item fields: `itemid` (generated ID), `quantity`, `price`, `lo` (LaundryOrder relationship), and `laundryService` (LaundryService relationship).

Validation: `quantity` must be positive.

The service looks up the referenced order and service by ID, calculates item price as `laundryService.price × quantity`, saves the item, and recalculates the related order total.

Example create request:

```http
POST http://localhost:9542/orderitem
Content-Type: application/json
```

```json
{
  "quantity": 2,
  "lo": {
    "oid": 1
  },
  "laundryService": {
    "sid": 1
  }
}
```

The `price` is calculated by the service; it is not necessary to supply it in this create example. Use existing order and service IDs.

## 5. Dashboard API

Controller: `DashBoardController`

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/dashboard` | Retrieve dashboard metrics |

The response map contains these keys:

- `totalOrders`
- `pendingOrders`
- `processingOrders`
- `readyOrders`
- `deliveredOrders`
- `undeliveredOrders`
- `lateOrders`
- `totalRevenue`

`totalRevenue` is calculated from `totalAmount` for orders with `paymentStatus` equal to `Paid`.

## 6. Validation and error responses

`GlobalExceptionHandler` handles validation errors and `ResponseStatusException`.

Validation error response shape:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Validation message",
  "path": "/request/path"
}
```

Response-status error shape:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found",
  "path": "/request/path"
}
```

The exact message depends on the exception.

## Important testing notes

- The tables above reflect controller annotations in the uploaded source. Run each request in Postman to verify behavior against your local database.
- Use test/sample customer details, not real personal data, in screenshots.
- The source returns entity objects directly; nested relationship serialization may include related entity data.
- `GET /order/{oid}` is constrained to numeric IDs by its route pattern.
- No authentication mechanism is present in the reviewed controllers.
