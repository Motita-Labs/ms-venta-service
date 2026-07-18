# ms-venta-service

Microservicio de ventas / arriendos (Spring Boot 4, Java 17). Productor de la cola SQS `arriendos-queue`.

## Endpoints

Base: `/api/v1/venta`

| Método | Ruta               | Descripción       |
|--------|--------------------|-------------------|
| POST   | /api/v1/venta      | Crear venta       |
| GET    | /api/v1/venta      | Listar ventas     |
| GET    | /api/v1/venta/{id} | Obtener por id    |
| DELETE | /api/v1/venta/{id} | Eliminar venta    |

Puerto: `8081`. Health: `/actuator/health`.

## Integración con SQS

Al crear una venta, si `AWS_SQS_ENABLED=true`, se publica un mensaje en la cola con el detalle del arriendo. La función serverless `procesar-arriendo` lo consume de forma asíncrona.

Variables: `AWS_SQS_ENABLED`, `AWS_SQS_QUEUE_URL`, `AWS_REGION`, `AWS_SQS_ENDPOINT` (opcional, pruebas locales).

## Build y test

```bash
mvn clean verify
```

## Docker

```bash
docker build -t arriendos/ms-venta:latest .
docker run -p 8081:8081 -e DB_URL=jdbc:mysql://host:3306/venta arriendos/ms-venta:latest
```

Variables: `DB_URL`, `DB_USER`, `DB_PASSWORD`.

## CI/CD

`.github/workflows/ci-cd.yml`: build + test (`mvn verify`) y publicación de imagen en GHCR.
