# 📦 Catalog Service (Catálogo de Alta Performance)

Microsserviço de catálogo de produtos e categorias desenvolvido com Spring Boot 4 / Java 21, projetado para alta performance de leitura, cache distribuído com Redis, persistência com PostgreSQL, migrações com Flyway e segurança com Spring Security e JWT.

---

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 4.x** (Spring MVC, Spring Data JPA, Spring Cache, Spring Security, Validation)
- **PostgreSQL 16** (Banco de dados relacional)
- **Flyway** (Controle de versões de schema do banco)
- **Redis 7** (Cache distribuído de alta velocidade)
- **JJWT (io.jsonwebtoken 0.12.6)** (Autenticação Stateless com tokens JWT)
- **Docker & Docker Compose** (Containerização dos serviços locais)
- **JaCoCo & SonarQube** (Cobertura de testes e análise estática de código)

---

## 🔐 Segurança e Autenticação (JWT)

A API adota uma arquitetura **Stateless** protegida por **Spring Security** e tokens **JWT (JSON Web Token)**:

- **Endpoints Públicos (Leitura):**
  - `GET /api/categories/**` — Listagem de categorias (cacheada no Redis).
  - `GET /api/products/**` — Listagem paginada e otimizada de produtos.
- **Endpoints Protegidos (Escrita/Administração):**
  - `POST /api/categories` — Cadastro de novas categorias. Requer cabeçalho `Authorization: Bearer <token>`.
  - `POST /api/products` — Cadastro de produtos. Requer cabeçalho `Authorization: Bearer <token>`.

### Configuração no `application.yml`
```yaml
security:
  jwt:
    secret: ${JWT_SECRET:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}
    expiration: ${JWT_EXPIRATION:86400000} # 24 horas em milissegundos
```

---

## 🐳 Subindo o Ambiente Local (Docker)

Para iniciar os containers do PostgreSQL, Redis e SonarQube:

```bash
docker compose up -d
```

Serviços expostos:
- **PostgreSQL:** `localhost:16432` (database: `catalog_db`, user: `postgres`, pass: `password`)
- **Redis:** `localhost:6379`
- **SonarQube:** `http://localhost:9001`

---

## 🛠️ Compilação e Testes

Compilar o projeto e validar dependências:
```powershell
.\mvnw.cmd clean test-compile
```

Executar os testes unitários e de integração:
```powershell
.\mvnw.cmd test
```

Análise de qualidade com SonarQube:
```powershell
.\mvnw.cmd clean verify sonar:sonar "-Dsonar.projectKey=catalog-service" "-Dsonar.host.url=http://localhost:9001" "-Dsonar.login=SEU_TOKEN"
```

---
