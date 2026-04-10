const API = "http://localhost:8080/api/items";

async function addItem() {
    const name = document.getElementById("name").value;
    const value = document.getElementById("value").value;

    await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, value })
    });

    loadData();
}

async function loadData() {
    const order = document.getElementById("order").value;

    const res = await fetch(`${API}/sorted?order=${order}`);
    const data = await res.json();

    const table = document.getElementById("tableBody");
    table.innerHTML = "";

    data.forEach(item => {
        table.innerHTML += `
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.value}</td>
            </tr>
        `;
    });
}