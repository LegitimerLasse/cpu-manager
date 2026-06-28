# CPU Manager

A web application for managing a list of CPUs. CPUs can be viewed and edited via a simple interface.

## Tech Stack

- **Backend:** Spring Boot 3.5, Java 21, Gradle, Spring Data JPA, Flyway, MySQL 8
- **Frontend:** React 18, TypeScript, Vite

## Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Node.js](https://nodejs.org/) (for the frontend)

## Running the App

**Backend + Database (build once, then start):**
```bash
docker compose up --build
```

**Frontend (separate terminal):**
```bash
cd frontend
npm install
npm run dev
```

## URLs

| Service  | URL                               |
|----------|-----------------------------------|
| Frontend | http://localhost:5173             |
| Backend  | http://localhost:8081/api         |
| Sockets  | http://localhost:8081/api/sockets |

## API Endpoints

| Method | Path           | Description          |
|--------|----------------|----------------------|
| GET    | /api/cpus      | List all CPUs        |
| GET    | /api/cpus/{id} | Get CPU details      |
| PUT    | /api/cpus/{id} | Update a CPU         |
| GET    | /api/sockets   | List sockets (dropdown data) |

## Architecture

The project is split into two independent applications.

**Backend** (`/backend`) is a Spring Boot REST API:
- Controllers receive HTTP requests and delegate to the service layer
- The service holds all business logic and is wrapped in `@Transactional`
- Repositories are Spring Data JPA interfaces — SQL is generated automatically
- Entities map to database tables via JPA annotations; `@Version` enables optimistic locking
- Flyway manages the database schema with versioned SQL migration files (`V1`, `V2`, `V3`)
- DTOs separate the internal data model from the API contract — the list endpoint returns a slim summary, the detail endpoint returns all fields

**Frontend** (`/frontend`) is a React single-page application:
- `api.ts` centralizes all fetch calls and TypeScript types
- `CpuList` fetches and displays the CPU table
- `CpuEdit` loads a single CPU and its available sockets, then submits changes via PUT
- Routing is handled by a single `selectedId` state in `App.tsx` — no router library needed

**Database** runs in Docker via MySQL 8. Schema and seed data are managed entirely by Flyway — Hibernate only validates that the schema matches the entities on startup.
