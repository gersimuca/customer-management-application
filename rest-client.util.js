export function assertResponseStatus() {
    const expectedStatus = Array.from(arguments);
    const expectedVerb = expectedStatus.length === 1 ? "is" : "is any of";
    client.test(`Response status ${expectedVerb} ${expectedStatus}`, function () {
        client.assert(expectedStatus.includes(response.status), `Response status is ${response.status}`);
    });
}

export function assertResponseBodyPropertyIsNotEmpty(expectedProperty) {
    client.test(`Response body property ${expectedProperty} is not empty`, function () {
        const expectedMimeTypes = ["application/json", "application/vnd.spring-boot.actuator.v3+json"];
        const mimeType = response.contentType.mimeType;
        client.assert(mimeType && expectedMimeTypes.includes(mimeType) > 0, `Mime Type is ${mimeType} but must be one of: ${expectedMimeTypes}`)

        const value = findValueByPath(response.body, expectedProperty);
        client.assert(value, `Response body property ${expectedProperty} is empty`);
    });
}

export function assertResponseBodyPropertyHasValue(expectedProperty, expectedValue) {
    client.test(`Response body property ${expectedProperty} has value ${expectedValue}`, function () {
        const expectedMimeTypes = ["application/json", "application/vnd.spring-boot.actuator.v3+json"];
        const mimeType = response.contentType.mimeType;
        client.assert(mimeType && expectedMimeTypes.includes(mimeType) > 0, `Mime Type is ${mimeType} but must be one of: ${expectedMimeTypes}`)

        const value = findValueByPath(response.body, expectedProperty);
        client.assert(value === expectedValue, `Response body property ${expectedProperty} does not have value ${expectedValue} but ${value}`);
    });
}

export function assertResponseBodyTextContains(expectedValue) {
    client.test(`Response body contains value ${expectedValue}`, function () {
        const expectedMimeType = "text/plain"
        const mimeType = response.contentType.mimeType;
        client.assert(mimeType && mimeType === expectedMimeType, `Mime Type is ${mimeType} but must be ${expectedMimeType}`)

        const value = response.body;
        client.assert(value && value.indexOf(expectedValue) > 0, `Response body does not contain value ${expectedValue} in ${value}`);
    });
}

function findValueByPath(body, path) {
    let paths = path.split('.'), current = body, i;
    for (i = 0; i < paths.length; ++i) {
        if (current[paths[i]] === undefined) {
            return undefined;
        }
        current = current[paths[i]];
    }
    return current;
}