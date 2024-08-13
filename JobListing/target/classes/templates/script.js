document.addEventListener("DOMContentLoaded", () => {
    const apiList = document.getElementById("api-list");
    const endpointSelect = document.getElementById("endpoint");
    const apiForm = document.getElementById("api-form");
    const responseDiv = document.getElementById("response");

    fetch("http://localhost:8080/v2/api-docs")
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            const paths = data.paths;
            for (const path in paths) {
                const listItem = document.createElement("li");
                listItem.textContent = `${path}: ${Object.keys(paths[path]).join(", ")}`;
                apiList.appendChild(listItem);

                const option = document.createElement("option");
                option.value = path;
                option.textContent = path;
                endpointSelect.appendChild(option);
            }
        })
        .catch(error => {
            console.error("Error fetching API docs:", error);
            responseDiv.textContent = "Error fetching API docs. Please try again later.";
        });

    apiForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const endpoint = endpointSelect.value;
        const method = document.getElementById("method").value;
        const data = document.getElementById("data").value;

        let fetchOptions = {
            method: method,
            headers: {
                "Content-Type": "application/json"
            }
        };

        if (method === "POST" || method === "PUT") {
            fetchOptions.body = data;
        }

        fetch(`http://localhost:8080${endpoint}`, fetchOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Network response was not ok: ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                responseDiv.textContent = JSON.stringify(data, null, 2);
            })
            .catch(error => {
                console.error("Error performing API request:", error);
                responseDiv.textContent = `Error performing API request: ${error.message}`;
            });
    });
});
