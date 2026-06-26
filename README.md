# NotificationService
Listens to order events and sends notifications

## Run

Start a RabbitMQ instance on `localhost:5672`, then run the service with:

```bash
mvn spring-boot:run
```

Or start RabbitMQ with Docker Compose:

```bash
docker compose up -d
```

Then run the service:

```bash
mvn spring-boot:run
```

## API

- `GET /api/notifications/health` returns the app status.
- `POST /api/notifications` accepts a notification request and publishes it to RabbitMQ.
