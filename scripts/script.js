const { execSync, spawn } = require('child_process');
const path = require('path');
const fs = require('fs');

function envFilePath() {
    const rootPath = path.resolve(__dirname, '..');
    return path.join(rootPath, 'ansible', '.runtime', 'erp', '.env');
}

function loadEnvFile() {
    const file = envFilePath();
    if (!fs.existsSync(file)) {
        throw new Error(`Missing generated .env file. Expected: ${file} Generate it first: ansible-playbook -i ansible/inventories/local/hosts.yml ansible/playbooks/local.yml `);
    }

    const content = fs.readFileSync(file, 'utf8');

    return Object.fromEntries(
        content
            .split(/\r?\n/)
            .map(line => line.trim())
            .filter(line => line && !line.startsWith('#'))
            .map(line => {
                const index = line.indexOf('=');

                return [
                    line.slice(0, index).trim(),
                    line.slice(index + 1).trim(),
                ];
            })
    );
}

function env() {
    return {
        ...process.env,
        ...loadEnvFile(),
    };
}

function execute(message, command, cwd = process.cwd()) {
    console.log(`\n${message}`);

    execSync(command, {
        stdio: 'inherit',
        cwd,
        env: env(),
    });
}

function executeSpawn(message, command, args, cwd = process.cwd()) {
    console.log(`\n${message}`);

    const child = spawn(command, args, {
        stdio: 'inherit',
        cwd,
        env: env(),
        shell: process.platform === 'win32',
    });

    child.on('close', code => {
        console.log(`${message} exited with code ${code}`);

        if (code !== 0) {
            process.exit(code);
        }
    });

    return child;
}

function run() {
    const rootPath = path.resolve(__dirname, '..');
    const dockerPath = path.join(rootPath, 'docker');
    const frontendPath = path.join(rootPath, 'user-interface');

    execute('Generating local environment...', 'ansible-playbook -i ansible/inventories/local/hosts.yml ansible/playbooks/local.yml', rootPath);
    execute('Stopping ERP services...', `docker compose -f "${path.join(dockerPath, 'docker-compose.yml')}" down`, rootPath );
    execute('Starting ERP infrastructure...', `docker compose -f "${path.join(dockerPath, 'docker-compose.yml')}" --profile init --profile core --profile auth up -d`, rootPath);
    execute('Updating Git submodules...','git submodule update --init --recursive', rootPath);
    executeSpawn('Starting ERP API...', 'mvn', ['clean', 'install', '-DskipTests', 'spring-boot:run'], rootPath);
    execute('Installing frontend dependencies...', 'npm install', frontendPath);
    execute('Starting frontend...', 'npm run start', frontendPath);
}

run();