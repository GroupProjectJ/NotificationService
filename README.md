# NotificationService

NotificationService is a Spring Boot application that accepts order-related notification requests, publishes them to RabbitMQ, and consumes messages from the notification queue.

## What it does

- Accepts notification payloads over HTTP.
- Publishes events to `notification.direct.exchange` with the routing key `notification.created`.
- Listens to `notification.queue` and writes consumed messages to the application log.
- Routes rejected messages to the dead-letter queue `notification.dlq`.

## Requirements

- Java 17
- Maven
- RabbitMQ running on `localhost:5672`

## Run locally

Start RabbitMQ with Docker Compose:

```bash
docker compose up -d
```

Then start the application:

```bash
mvn spring-boot:run
```

You can also run the test suite first:

```bash
mvn test
```

## Configuration

Default settings live in `src/main/resources/application.yml`.

- Application port: `8080`
- RabbitMQ host: `localhost`
- RabbitMQ port: `5672`
- RabbitMQ username/password: `guest` / `guest`
- Exchange: `notification.direct.exchange`
- Routing key: `notification.created`

If you already have a RabbitMQ queue named `notification.queue`, make sure its arguments match the current configuration. A pre-existing queue without dead-letter settings can cause a `PRECONDITION_FAILED` warning during startup.

## API

### Health check

`GET /api/notifications/health`

Returns a simple status response:

```json
{
	"status": "UP",
	"message": "NotificationService is running"
}
```

### Publish a notification

`POST /api/notifications`

Accepts a notification event and publishes it to RabbitMQ. The endpoint returns `202 Accepted` when the message is queued successfully.

Example request:

```json
{
	"orderId": 1001,
	"customerId": 501,
	"productId": 9001,
	"productName": "Wireless Headphones",
	"quantity": 2,
	"totalPrice": 199.98,
	"orderDate": "2026-06-27T10:15:00Z",
	"status": "CREATED"
}
```

Example response:

```json
{
	"status": "QUEUED",
	"message": "Notification request accepted"
}
```

## RabbitMQ flow

1. The controller receives a request at `/api/notifications`.
2. `NotificationService` serializes the event with Jackson and publishes it to RabbitMQ.
3. `NotificationListener` consumes messages from `notification.queue`.
4. Failed messages can be moved to `notification.dlq` through the configured dead-letter exchange.

## Test

Run the application tests with:

```bash
mvn test
```

The current test suite only verifies that the Spring application context starts successfully.
