async function registerCustomer() {

    const firstname = document.getElementById("firstname").value;
    const lastname = document.getElementById("lastname").value;
    const identificationNumber = document.getElementById("id_number").value;
    const email = document.getElementById("email").value;
    const phoneNumber = document.getElementById("phonenumber").value;
    const password = document.getElementById("password").value;

    //Clears old errors
    document.getElementById("firstname_error").innerText = "";
    document.getElementById("lastname_error").innerText = "";
    document.getElementById("identificationNumber_error").innerText = "";
    document.getElementById("email_error").innerText = "";
    document.getElementById("phoneNumber_error").innerText = "";
    document.getElementById("password_error").innerText = "";
    document.getElementById("result_message").innerText="";

    const response = await fetch("/connect/create", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            firstname,
            lastname,
            identificationNumber,
            email,
            password,
            phoneNumber
        })
    });

    const data = await response.json();

    if (response.ok) {
        window.location.href = "/login";
        return;
    }

    //Validation errors
    if (response.status === 400) {
        for (const field in data) {
            const errorDiv = document.getElementById(`${field}_error`);
            if (errorDiv) {
                errorDiv.innerHTML = data[field];
            }
        }
        return;
    } else if (response.status === 503) {
        document.getElementById("result_message").innerText = "The server is temporarily down. Please try again later.";
        return;
    }

    if (!response.ok) {
        document.getElementById("error_message").innerText = data.error || "Unexpected error occur";
    }

    document.getElementById("result_message").innerText = data.message || "failed to register";
}