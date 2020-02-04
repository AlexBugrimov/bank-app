
export default async function createBank() {
    try {
        const response = await fetch('/api/v1/banks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return await response.json();
    } catch (error) {
        console.error('Ошибка:', error);
    }
}