# ERP Api Service App

## Getting Started

To start your own new application clone this repository.

### Authorization

Authorization is done using the OpenID connect support of *Spring* security and the LHT *Keycloak*.
The `erp-api-service` uses a configured `erp` client that has multiple users.

Check out the `requests.http` file where you can authenticate as one of the two and fire up requests against the API.

### Health Services

This application uses *Spring Boot Actuator* to provide basic health checks.

| ***Route***                    | ***Description***                           |
|--------------------------------|---------------------------------------------|
| ```/status/health/liveness```  | The application is up and running.          |
| ```/status/health/readiness``` | The application is ready to serve requests. |

### Databases

We use *Flyway* for the database migrations. For database changes, please create a new migration file
with a version, it will be applied at the next app start.

To run a *SQL Sever* instance locally just run ```$ docker-compose -f docker/docker-compose.yml up -d```.
This database is getting pre-filled at application start up.

### OpenAPI Documentation

*OpenAPI* documentation is generated via [springdoc](https://github.com/springdoc/springdoc-openapi).
The JSON specification is available
at [repository](../openapi/erp-openapi-contract.yaml)

## Configuration

The application should be configured via environment variables.
Environment variables will overwrite certain stage/deployment specific properties in the `application.yaml`.
In OpenShift the environment variables are provided via the `DeploymentConfig`.

| Variable               | Effect                                                                                | Value for local dev                                                                           |
|------------------------|---------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| `DB_JDBC_URL`          | JDBC connection string.                                                               | `jdbc:sqlserver://localhost:1433;database=erp_db;encrypt=false;trustServerCertificate=false;` |
| `DB_USERNAME`          | Database username.                                                                    | `erp_db_user`                                                                                 |
| `DB_PASSWORD`          | Database password.                                                                    | `secretL0calPassword`                                                                         |
| `DB_JDBC_DRIVER`       | The JDBC driver, such as \*com.microsoft.sqlserver.jdbc.SQLServerDriver\* (Azure DB). | `com.microsoft.sqlserver.jdbc.SQLServerDriver`                                                |
| `SSO_CLIENT_ID`        | The SSO client.                                                                       | `erp`                                                                                         |
| `SSO_JWK_SET_URI`      | URL to the SSO certificate endpoint.                                                  | `http://localhost:8180/auth/realms/ERP/protocol/openid-connect/certs`                         |
| `CORS_ALLOWED_ORIGINS` | Comma separated allowed CORS origin URLs.                                             | `http://localhost:4200`                                                                       |
| `LOG_LEVEL`            | Sets the \*Spring\* root log level.                                                   |                                                                                               | |
| `SSO_JWT_TOKEN_URI`    | SSO Token URI.                                                                        | `https://sso-kons.app.lhtcloud.com/auth/realms/ERP/protocol/openid-connect/token`             |
| `FLYWAY_LOCATION`      | Flyway folders to be imported                                                         | `db/migration,db/importMigration`                                                             |

## Development

### Starting local infrastructure

It is recommended to develop against the local *SQL Server* instance. To make it available, execute the following
command:

- `docker compose -f docker/docker-compose.yml up -d` (macOS and Linux)
- `docker compose -f docker\docker-compose.yml up -d` (Windows)

If you want to use a *Local SSO* instance, please refer to the Git
repository [erp-local-sso](./docker/sso/import/erp-realm.json) and
follow the instructions.

### Starting the service

#### IntelliJ (for development only)

If you are using IntelliJ, you should create run configurations and make sure to configure the
appropriate [environment variables](#Configuration) to each configuration.

#### Automated start script (for testing purposes only)

If you only want to start the service for testing purposes (e.g. if you want to connect the frontend to it), you can use
an automated script that starts the service and initializes the local SQL Server. Simply execute the following command:

- `node scripts/start.js [use-local-sso]` (macOS and Linux)
- `node scripts\start.js [use-local-sso]` (Windows)

Note that this requires Node.js to be installed and configured correctly on your local development machine. The
parameter `use-local-sso` starts the service using
the [erp-local-sso](./docker/sso/import/erp-realm.json).


### Logging

The stash logging in live environments can be enabled by setting the correct values for the Openshift environment
properties, as per the below table.
The configuration is done in [logback.xml](src/main/resources/logback.xml).

## SonarQube Analysis

The project uses the `jacoco` plugin for code coverage and integrates with SonarQube for static code analysis.
Make sure your local SonarQube server is running at http://localhost:9000.
You need to create your own SONAR_TOKEN in your SonarQube account settings.
To perform a local analysis and send the report to SonarQube, run the following command:

```bash
mvn clean install -Pcoverage sonar:sonar \
  -Dsonar.projectKey=ERP \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=SONAR_TOKEN

