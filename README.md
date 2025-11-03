# Kafka Kotlin Project (Producer -> Consumer -> MongoDB)

Multi-module Kotlin (Spring Boot) example using Gradle.

Modules:
- producer: exposes a REST API `/api/messages` (POST) that accepts a JSON `{ "payload": "..."}`
  and forwards that payload to Kafka topic `messages`.
- consumer: listens to Kafka topic `messages`, attaches a GUID, and writes to MongoDB.

Included:
- `docker-compose.yml` to run Kafka, Zookeeper, and MongoDB and build the producer & consumer images.
- Dockerfiles for both modules (multi-stage using Gradle image).

How to run (basic):
1. Install Docker & Docker Compose.
2. From the project root run:
   ```bash
   docker compose up --build
   ```
3. The producer API: `http://localhost:8080/api/messages` (POST JSON `{ "payload": "hello" }`)
   The consumer writes to MongoDB at `mongodb://mongo:27017` (docker network). We expose
   MongoDB on the host at default port 27017 so you can inspect it locally.

Notes:
- This is a minimal example to get you started. Adjust versions and configuration to suit your environment.
- Gradle wrapper is not included; use your local Gradle or add a wrapper if you prefer.
