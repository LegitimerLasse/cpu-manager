# CPU Manager

Webapp zur Verwaltung einer CPU-Liste. CPUs können angezeigt und bearbeitet werden.

## Stack

- **Backend:** Spring Boot 3.5, Java 21, Gradle, Spring Data JPA, Flyway, MySQL 8
- **Frontend:** React 18, TypeScript, Vite

## Voraussetzungen

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Node.js](https://nodejs.org/) (für das Frontend)

## Starten

**Backend + Datenbank (einmalig bauen, dann starten):**
```bash
docker compose up --build
```

**Frontend (separates Terminal):**
```bash
cd frontend
npm install
npm run dev
```

## URLs

| Dienst   | URL                        |
|----------|----------------------------|
| Frontend | http://localhost:5173       |
| Backend  | http://localhost:8081/api   |
| Sockets  | http://localhost:8081/api/sockets |

## API-Endpunkte

| Methode | Pfad             | Beschreibung          |
|---------|------------------|-----------------------|
| GET     | /api/cpus        | CPU-Liste             |
| GET     | /api/cpus/{id}   | CPU-Details           |
| PUT     | /api/cpus/{id}   | CPU bearbeiten        |
| GET     | /api/sockets     | Sockel-Dropdown       |
