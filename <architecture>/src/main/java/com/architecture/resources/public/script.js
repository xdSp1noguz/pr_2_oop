// Сюди ми вставимо адресу пізніше (Крок 3.4.2)
const API_URL = 'https://urban-acorn-p4q5qrxxv4vh66w6-8080.app.github.dev/'; 

console.log("Script loaded. API URL:", API_URL);

fetch(API_URL + '/api/models')
    .then(response => {
        console.log("Response status:", response.status);
        return response.json();
    })
    .then(data => {
        console.log("Data received:", data);
        const container = document.getElementById('models-list');
        container.innerHTML = '';
        
        if (data.length === 0) {
            container.innerHTML = '<p>Дані відсутні</p>';
            return;
        }

        data.forEach(model => {
            const div = document.createElement('div');
            div.className = 'card';
            div.innerHTML = `
                <h3>Проект ID: ${model.modelid}</h3>
                <p>Версія: <strong>${model.version}</strong></p>
            `;
            container.appendChild(div);
        });
    })
    .catch(err => {
        console.error('Помилка:', err);
        document.getElementById('models-list').innerText = 'Помилка завантаження даних: ' + err;
    });