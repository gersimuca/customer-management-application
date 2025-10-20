const {execSync, spawn} = require('child_process');
const path = require('path');

function execute(message, command) {
    console.log(message);
    execSync(command, {stdio: 'inherit', env: env()});
}


function executeSpawn(message, command, args, cwd) {
    console.log(message);
    const child = spawn(command, args, {
        stdio: 'inherit',
        env: env(),
        cwd: cwd || process.cwd(),
    });

    child.on('close', (code) => {
        console.log(`${message} exited with code ${code}`);
    });

    return child;
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
    const frontendPath = path.resolve(rootPath, "user-interface");

    execute("Stopping erp-app services...", `docker compose -f ${dockerPath}/docker-compose.yml down`);
    execute("Starting erp-app services...", `docker compose -f ${dockerPath}/docker-compose.yml up -d`);
    execute("Update Git Submodule...", `cd ${rootPath} && git submodule update --init --recursive`);
    executeSpawn("Starting erp-api-service...", `cd ${rootPath} && mvn clean install -DskipTests spring-boot:run`);
    execute("Installing frontend dependencies...", `cd ${frontendPath} && npm install`);
    execute("Starting frontend (Angular)...", `cd ${frontendPath} && npm run start`);
}

run();