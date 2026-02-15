<img src="https://raw.githubusercontent.com/Fabinatix97/vuedolist/main/frontend/public/logo.png" width="180" alt="VueDoList Frontend Logo">

# VueDoList

VueDoList is a minimalist todo app with a Vue.js frontend and a Spring Boot + PostgreSQL backend. It provides a clean, intuitive interface with a RESTful API for managing todo lists and todos.

## Features

- **RESTful API** for CRUD operations on lists and todos
- **Minimalist UI** with multi-theme support
- **PostgreSQL integration** via Spring Data JPA
- **Environment-based configuration** for secure local dev & deployment

## Tech Stack

- Frontend: Vue 3, Vite
- Backend: Spring Boot, Maven
- Database: PostgreSQL

## Getting Started

### Containerized Setup (Recommended)

```bash
# Copy the example env file and fill in your actual values
cp .env.example .env

# Load environment variables
set -a; source .env; set +a

# Start both frontend and backend using Docker...
docker-compose up -d

# ... or Podman
podman-compose up -d

# Open the app in your browser at http://localhost:8080
```

### Manual Setup

```bash
cd backend/

# Copy the example env file and fill in your actual values
cp .env.example .env

# Load environment variables
set -a; source .env; set +a

# Start PostgreSQL using Docker...
docker-compose up -d

# ... or Podman
podman-compose up -d

# Install dependencies and run the backend
mvn clean install
mvn spring-boot:run

# Open a new terminal and navigate to the frontend directory
cd ./frontend/

# Install dependencies and run the frontend
npm install
npm run dev

# Open the app in your browser at http://localhost:5173
```

## Detailled Information

For detailed setup and usage instructions, please refer to the individual README files in the [frontend](./frontend/README.md) and [backend](./backend/README.md) directories.

## Screenshots

| Light Mode    | Dark Mode     |
| ------------- | ------------- |
| ![list-view-light](https://raw.githubusercontent.com/Fabinatix97/vuedolist/main/frontend/public/list-view-light.jpg)  | ![list-view-dark](https://raw.githubusercontent.com/Fabinatix97/vuedolist/main/frontend/public/list-view-dark.jpg)  |
| ![task-view-light](https://raw.githubusercontent.com/Fabinatix97/vuedolist/main/frontend/public/task-view-light.jpg)  | ![task-view-dark](https://raw.githubusercontent.com/Fabinatix97/vuedolist/main/frontend/public/task-view-dark.jpg)  |

Thank you for checking out VueDoList! 🎉
