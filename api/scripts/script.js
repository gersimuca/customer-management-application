const {execSync} = require('child_process');
const path = require('path');

function execute(message, command) {
    console.log(message);
    execSync(command, {stdio: 'inherit', env: env()});
}

function env() {
    return {
        ...process.env,
        DB_JDBC_URL: "jdbc:sqlserver://localhost:1433;database=erp_db;encrypt=false;trustServerCertificate=false;",
        DB_USERNAME: "erp_db_user",
        DB_PASSWORD: "secretL0calPassword",
        DB_JDBC_DRIVER: "com.microsoft.sqlserver.jdbc.SQLServerDriver",
        SSO_CLIENT_ID: "erp",
        CORS_ALLOWED_ORIGINS: "http://localhost:4200",
        SSO_JWK_SET_URI: "http://localhost:8180/auth/realms/ERP/protocol/openid-connect/certs"
    }
}

function run() {
    const rootPath = path.resolve(__dirname, "..");
    const dockerPath = path.resolve(rootPath, "docker");

    execute("Stopping erp-app services...", `docker compose -f ${dockerPath}/docker-compose.yml down`);
    execute("Starting erp-app services...", `docker compose -f ${dockerPath}/docker-compose.yml up -d`);
    execute("Update Git Submodule...", `cd ${rootPath} && git submodule update --init --recursive`);
    execute("Starting erp-api-service...", `cd ${rootPath} && mvn clean install -DskipTests spring-boot:run`);
}

run();