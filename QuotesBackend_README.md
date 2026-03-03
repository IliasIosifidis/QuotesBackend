# Quotes of the Ancients — Backend API

A REST API serving philosophical quotes from Greek, Latin, and Chinese traditions, with user authentication, community-submitted quotes, and a voting system.

Built with **Kotlin**, **Spring Boot**, **Spring Security**, and **MySQL**.

## Features

- **Curated quote collections** — Browse Greek, Latin, and Chinese philosophical quotes, each with their own endpoint and data model. Latin quotes include original text, translation, and author. Chinese quotes include the original line, English translation, explanation, and source.
- **User-submitted quotes** — Authenticated users can submit their own quotes, which are stored separately from the curated collections.
- **Voting system** — Upvote/downvote with toggle logic: vote once to cast, vote again to undo, switch to flip. Score updates are transactional.
- **JWT authentication** — Stateless auth with token-based login. Tokens are issued on login and validated via a custom Spring Security filter.
- **Role-based access control** — Three-tier role hierarchy (User → Admin → Creator). The Creator account is bootstrapped on startup via environment variables. Only the Creator can delete users or promote Admins.
- **Pagination** — User-submitted quotes are served with Spring Data `Page` support.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| Framework | Spring Boot |
| Security | Spring Security, JWT (jjwt) |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Deployment | Railway |

## API Endpoints

### Curated Quotes (public, no auth required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/greeks` | All Greek quotes |
| `GET` | `/api/greeks/random` | Random Greek quote |
| `GET` | `/api/latins` | All Latin quotes |
| `GET` | `/api/latins/random` | Random Latin quote |
| `GET` | `/api/chinese` | All Chinese quotes |
| `GET` | `/api/chinese/random` | Random Chinese quote |

### Authentication

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/auth/register` | Register new user | — |
| `POST` | `/api/auth/login` | Login, returns JWT | — |
| `GET` | `/api/auth/me` | Current user info | Required |
| `DELETE` | `/api/auth/users/{id}` | Delete user | Admin/Creator |

### User Quotes

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/user-quotes?page=0&size=20` | List quotes (paginated) | — |
| `POST` | `/api/user-quotes` | Submit a quote | Required |
| `POST` | `/api/user-quotes/{id}/upvote` | Upvote | Required |
| `POST` | `/api/user-quotes/{id}/downvote` | Downvote | Required |
| `DELETE` | `/api/user-quotes/{id}` | Delete a quote | Admin/Creator |

## Getting Started

### Prerequisites

- JDK 17+
- MySQL 8+
- Gradle

### Local Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/IliasIosifidis/QuotesBackend.git
   cd QuotesBackend
   ```

2. Create a MySQL database:
   ```sql
   CREATE DATABASE ancients;
   ```

3. Set environment variables:
   ```bash
   export JWT_SECRET=your-secret-key-at-least-32-characters
   export CREATOR_USERNAME=creator
   export CREATOR_EMAIL=creator@example.com
   export CREATOR_PASSWORD=your-creator-password
   ```

4. Run with the local profile:
   ```bash
   ./gradlew bootRun --args='--spring.profiles.active=local'
   ```

   The local profile connects to `localhost:3306/ancients` with `ddl-auto=update`, so tables are created automatically on first run.

5. The API is available at `http://localhost:8080`.

### Production

The app reads database credentials from environment variables (`SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`) and uses `ddl-auto=validate`, so the schema must already exist.

## Project Structure

```
src/main/kotlin/org/quotes/ancients/
├── comon/config/          # Security config, JWT service, creator bootstrap
├── quotes/
│   ├── greek/             # Greek quotes: entity, repo, service, controller, DTOs
│   ├── latin/             # Latin quotes: entity, repo, service, controller, DTOs
│   └── chinese/           # Chinese quotes: entity, repo, service, controller, DTOs
└── users/
    ├── users/             # Auth, user management, roles
    └── quotes/            # User-submitted quotes & voting
```

## Related

- **Android Widget Client** — [QuotesWidget](https://github.com/IliasIosifidis/QuotesWidget) (currently in Google Play closed testing)
