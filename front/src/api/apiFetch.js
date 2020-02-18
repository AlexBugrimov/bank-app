
export async function postData(url = '', data = {}) {
    return await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => response.json());
}

export async function  getAllData(url = '') {
    const responsePromise = await fetch('/api/v1/banks', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    });
    return responsePromise;
}