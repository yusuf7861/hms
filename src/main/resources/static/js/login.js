document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('loginForm');
    const errorMessage = document.getElementById('error-message');

    loginForm.addEventListener('submit', function (e) {
        e.preventDefault(); // Stop normal form submission

        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value.trim();

        const loginData = {
            email: email,
            password: password
        };

        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'same-origin', // To include session cookie
            body: JSON.stringify(loginData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Invalid login');
                }
                return response.text(); // Expecting "Login successful"
            })
            .then(data => {
                console.log(data);
                window.location.href = '/dashboard'; // Redirect after successful login
            })
            .catch(error => {
                errorMessage.style.display = 'block';
                errorMessage.textContent = 'Invalid email or password';
                console.error('Login failed:', error);
            });
    });

    // Optional: Show logout message if redirected after logout
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('logout') === 'true') {
        document.getElementById('logout-message').style.display = 'block';
    }
});
