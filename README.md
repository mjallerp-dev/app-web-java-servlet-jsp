# App Web Java Servlet + JSP

Aplicación web desarrollada en **Java (Jakarta EE)** con **Servlets** y **JSP** que implementa un
CRUD de **Usuarios** y **Artículos**, con autenticación por sesión y persistencia en
**Azure Database for PostgreSQL — Servidor Flexible**. Sigue el patrón de arquitectura **MVC**
(Modelo - Vista - Controlador) y se empaqueta como archivo `WAR` para su despliegue en
**Azure App Service** (Apache Tomcat).

**Aplicación en producción:**
[https://app-webjsp-mjaller-frhugrbrg7cuawcu.centralus-01.azurewebsites.net](https://app-webjsp-mjaller-frhugrbrg7cuawcu.centralus-01.azurewebsites.net)

---

## Descripción

El sistema permite iniciar sesión con un usuario registrado y, una vez autenticado, gestionar
usuarios y artículos desde un menú principal. Cada operación (agregar, buscar, modificar,
eliminar y listar) se realiza a través de un Servlet que delega la lógica de acceso a datos en
clases CRUD y renderiza los resultados en páginas JSP.

La aplicación está desplegada en **Azure App Service** (Linux) y se conecta a un
**Servidor Flexible de Azure Database for PostgreSQL**.

## Características

- Inicio y cierre de sesión con control de acceso a las vistas.
- CRUD completo de **Usuarios** (`agregar`, `buscar`, `modificar`, `eliminar`, `listar`).
- CRUD completo de **Artículos** (`agregar`, `buscar`, `modificar`, `eliminar`, `listar`).
- Cálculo automático del **IVA (19%)** a partir del precio de venta.
- Persistencia en **Azure Database for PostgreSQL (Flexible Server)** mediante `PreparedStatement`.
- Conexión configurable por archivo `application.properties`.
- Soporte de **SSL** para la conexión a PostgreSQL (`sslmode=require`).
- Pruebas unitarias con **JUnit 5** y **Mockito**.
- Empaquetado `WAR` y despliegue en **Azure App Service**.

---

## Tecnologías

| Componente | Versión / Detalle |
|---|---|
| Lenguaje | Java 8 (configurado en `pom.xml`) |
| Plataforma | Jakarta EE — Servlet 6.1, JSP 4.0 |
| Hosting | **Azure App Service** Linux (`app-webjsp-mjaller`) |
| Runtime en Azure | Apache Tomcat 10.1 + Java 21 (`TOMCAT\|10.1-java21`) |
| Región App Service | Central US |
| Base de datos | **Azure Database for PostgreSQL — Flexible Server 16** |
| Servidor PostgreSQL | `pg-appwebjsp.postgres.database.azure.com` |
| Región PostgreSQL | East US 2 |
| Driver JDBC | PostgreSQL `42.7.13` |
| Build | Maven (`maven-war-plugin` 3.4.0, `maven-surefire-plugin` 3.5.2) |
| Testing | JUnit Jupiter 5.13.2, Mockito 5.23.0 |

---

## Estructura del proyecto

```
app-web-java-servlet-jsp/
├── pom.xml
├── mvnw / mvnw.cmd                 # Maven Wrapper
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/mjallerp/appwebjavaservletjsp/
│   │   │   ├── drivers/
│   │   │   │   ├── DatabaseConnection.java   # Gestión de la conexión JDBC
│   │   │   │   ├── ServletUser.java          # Controlador de usuarios
│   │   │   │   └── ServletArticle.java       # Controlador de artículos
│   │   │   ├── model/
│   │   │   │   ├── User.java                 # Modelo Usuario
│   │   │   │   ├── Article.java              # Modelo Artículo
│   │   │   │   ├── CRUDUser.java             # Acceso a datos de Usuario
│   │   │   │   └── CRUDArticle.java          # Acceso a datos de Artículo
│   │   │   └── HelloServlet.java             # Servlet de ejemplo
│   │   ├── resources/
│   │   │   └── application.properties        # Configuración de la BD (ignorado por Git)
│   │   └── webapp/
│   │       ├── index.jsp                     # Menú principal
│   │       ├── web/users/*.jsp               # Vistas de usuarios
│   │       ├── web/article/*.jsp             # Vistas de artículos
│   │       └── WEB-INF/web.xml
│   └── test/java/com/mjallerp/appwebjavaservletjsp/
│       ├── drivers/
│       │   ├── DatabaseConnectionTest.java
│       │   └── ServletUserTest.java
│       └── model/
│           ├── UserTest.java
│           ├── CRUDUserTest.java
│           ├── ArticleTest.java
│           └── CRUDArticleTest.java
└── .gitignore
```

---

## Arquitectura (MVC)

| Capa | Clases | Responsabilidad |
|---|---|---|
| **Modelo** | `User`, `Article` | Representan las entidades del dominio. |
| **Datos** | `CRUDUser`, `CRUDArticle` | Operaciones CRUD contra PostgreSQL. |
| **Conexión** | `DatabaseConnection` | Carga propiedades, abre/cierra conexión y ejecuta sentencias. |
| **Controlador** | `ServletUser`, `ServletArticle` | Procesan las peticiones y redirigen a las vistas. |
| **Vista** | Archivos `.jsp` | Formularios y presentación de resultados. |

### Endpoints (Servlets)

| Servlet | URL | Acciones (`accion`) |
|---|---|---|
| `ServletUser` | `/user` | `agregar`, `buscar`, `modificar`, `eliminar`, `listartodo`, `login`, `salir` |
| `ServletArticle` | `/article` | `agregar`, `buscar`, `modificar`, `eliminar`, `listartodo` |

---

## Infraestructura en Azure

| Recurso | Valor |
|---|---|
| Grupo de recursos | `rg-desarrolloweb` |
| App Service | `app-webjsp-mjaller` (Linux, HTTPS) |
| URL | https://app-webjsp-mjaller-frhugrbrg7cuawcu.centralus-01.azurewebsites.net |
| Runtime | Tomcat 10.1 — Java 21 |
| PostgreSQL Flexible Server | `pg-appwebjsp` |
| Host | `pg-appwebjsp.postgres.database.azure.com` |
| Motor | PostgreSQL 16 (SKU Burstable `Standard_B1ms`) |
| Base de datos | `ejercicio_articulo` |
| Puerto | `5432` |
| SSL | obligatorio (`sslmode=require`) |

---

## Base de datos

El proyecto usa la base `ejercicio_articulo` en el **Servidor Flexible de Azure Database for PostgreSQL**
(`pg-appwebjsp.postgres.database.azure.com`) con las siguientes tablas:

```sql
CREATE TABLE users (
    id       VARCHAR(50) PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role     VARCHAR(50)  NOT NULL
);

CREATE TABLE article (
    id            VARCHAR(50) PRIMARY KEY,
    user_id       VARCHAR(50) REFERENCES users(id),
    marca         VARCHAR(100),
    precio_venta  NUMERIC(12,2),
    precio_compra NUMERIC(12,2),
    iva           NUMERIC(12,2),
    modelo        VARCHAR(100),
    proveedor     VARCHAR(100),
    tienda        VARCHAR(100),
    cantidad      INTEGER,
    descripcion   TEXT,
    categoria     VARCHAR(100)
);
```

---

## Configuración

La conexión se lee desde `src/main/resources/application.properties`, que **no se versiona**
(está incluido en `.gitignore`). Crea el archivo apuntando al servidor Flexible de Azure:

```properties
#Database properties
dbName = ejercicio_articulo
dbHost = pg-appwebjsp.postgres.database.azure.com
dbUser = db_admin
dbPort = 5432
dbPassword = <contraseña>
dbSslMode = require
```

> **Nota:** no subas credenciales reales al repositorio. Azure Database for PostgreSQL exige SSL
> (`dbSslMode = require`).

---

## Requisitos

- JDK 8 o superior (en Azure App Service se ejecuta con **Java 21**).
- Maven (o usar el wrapper `mvnw` incluido).
- Acceso al Servidor Flexible de Azure Database for PostgreSQL con las tablas creadas.
- Para ejecución local: Apache Tomcat 10.1+ o un contenedor compatible con Jakarta EE.

---

## Compilación y ejecución

### 1. Compilar y empaquetar

```bash
./mvnw clean package
```

En Windows:

```powershell
.\mvnw.cmd clean package
```

Esto genera el archivo `target/app-web-java-servlet-jsp-1.0-SNAPSHOT.war`.

### 2. Desplegar en Tomcat (local)

Copia el `WAR` en la carpeta `webapps/` de Tomcat y arranca el servidor:

```
http://localhost:8080/app-web-java-servlet-jsp-1.0-SNAPSHOT/
```

### 3. Ejecutar pruebas

```bash
./mvnw test
```

> Las pruebas de `DatabaseConnectionTest` y `CRUDUserTest` requieren el servidor PostgreSQL
> de Azure accesible con la configuración de `application.properties`.

---

## Despliegue en Azure

La aplicación está desplegada en **Azure App Service** como archivo `WAR`.

| Dato | Valor |
|---|---|
| Recurso | `app-webjsp-mjaller` |
| Grupo de recursos | `rg-desarrolloweb` |
| URL pública | https://app-webjsp-mjaller-frhugrbrg7cuawcu.centralus-01.azurewebsites.net |
| Base de datos | Azure Database for PostgreSQL Flexible Server `pg-appwebjsp` |

La configuración de destino de IntelliJ IDEA se encuentra en `.azure/` (ignorada por Git).

Para volver a publicar, genera el `WAR` con Maven y desplégalo sobre el App Service
`app-webjsp-mjaller`.

---

## Licencia y uso académico

Proyecto desarrollado con fines académicos para la asignatura **Desarrollo Web** de la
**Universidad de Cartagena** (periodo 2026-2).

**Autor:** Miguel Angel Jaller Piñeres — Ingeniería de Software.
