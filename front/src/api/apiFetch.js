export default async function createBank() {
    const response = await fetch('/api/v1/banks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return await response.json();
}