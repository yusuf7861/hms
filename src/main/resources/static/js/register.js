document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("registerForm");

    form.addEventListener("submit", function (e) {
        e.preventDefault(); // stop default form submit

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        fetch("/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "same-origin",
            body: JSON.stringify({
                email: email,
                password: password
            })
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                return response.text();
            })
            .then(data => {
                alert("Registration successful!");
                // window.location.href = "/login";
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Registration failed: " + error.message);
            });
    });
});
