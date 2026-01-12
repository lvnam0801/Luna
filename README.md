# Luna WMS

Luna is a comprehensive Warehouse Management System (WMS) designed to handle inventory, product management, and import/export operations.

## Project Structure

The project is organized into the following modules:

*   **`1-Database/`**: Database schema and management scripts.
    *   **Core**: Tables for Products, Manufacturers, Warehouses, Parties (Users/Roles), etc.
    *   **Import/Export**: Tables handling receipt headers, line items, shipments, and activity logs.
    *   **InventoryChecks**: Modules for stock auditing.
    *   **Tech**: MySQL (indicated by `AUTO_INCREMENT` and SQL syntax).
*   **`2-Backend/`**: The core application server.
    *   **Project**: `Luna`
    *   **Tech Stack**: Java 17, Spring Boot 3.4.2, Gradle (Kotlin DSL).
    *   **Key Libraries**: Spring Data JDBC, Spring Security, Lombok, JJWT (JWT Auth).
*   **`3-Frontend/`**: The web user interface (Work In Progress).
    *   **Project**: `LunaWebServer`
    *   **Tech Stack**: Node.js, TypeScript, React, Vite, Tailwind CSS.
    *   **Note**: Currently scaffolded with Drizzle/Postgres dependencies, which may need reconciliation with the MySQL-based backend.
*   **`5-SeedingData/`**: Documentation and data files (Word documents) for populating the system with demo data.

## Prerequisites

*   **Java**: JDK 17
*   **Node.js**: LTS version
*   **Database**: MySQL Server 8.0+

## Getting Started

### Backend
1.  Navigate to `2-Backend/Luna`.
2.  Configure database connections in `src/main/resources/application.properties` (or equivalent).
3.  Run the application:
    ```bash
    ./gradlew bootRun
    ```

### Frontend
1.  Navigate to `3-Frontend/LunaWebServer`.
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the development server:
    ```bash
    npm run dev
    ```

## Database Setup
Execute the scripts in `1-Database` to initialize your MySQL instance. Start with the `Core` module scripts, followed by `Import`, `Export`, etc.