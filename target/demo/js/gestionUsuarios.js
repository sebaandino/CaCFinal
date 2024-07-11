function loadUsers() {
    fetch("loadUsers")
        .then(response => response.json())
        .then(data => {
            let usersTableContent = '';
            data.forEach(user => {
                usersTableContent += `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>
                        <button class="delete-btn" data-id="${user.id}"><i class="fa fa-trash"></i></button>
                        <button class="update-btn" data-id="${user.id}"><i class="fa fa-edit"></i></button>
                    </td>
                </tr>`;
            });
            document.getElementById("usersTable").innerHTML = usersTableContent;

            document.querySelectorAll(".delete-btn").forEach(button => {
                button.addEventListener("click", handleDelete);
            });

            document.querySelectorAll(".update-btn").forEach(button => {
                button.addEventListener("click", handleUpdate);
            });
        });
}

async function handleDelete(event) {
    const userId = event.target.closest("button").getAttribute("data-id");
    console.log(`Eliminar usuario con ID: ${userId}`);

    try {
        const response = await fetch(`userOptions?id=${userId}`);
        const data = await response.json();
        console.log(data);

        if (response.ok) {
            location.reload();
        } else {
            console.error("Error al eliminar el usuario");
        }
    } catch (error) {
        console.error("Error de red:", error);
    }
}

function handleUpdate(event) {
    const userId = event.target.closest("button").getAttribute("data-id");
    console.log(`Actualizar usuario con ID: ${userId}`);
}

window.onload = loadUsers;
