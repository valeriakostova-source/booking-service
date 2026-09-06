async function loadMyPage() {

    const token = localStorage.getItem("jwt");

    const response = await fetch("/connect/info", {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    const data = await response.json();

    document.getElementById("firstname").innerText = data.firstname;
    document.getElementById("lastname").innerText = data.lastname;
    document.getElementById("email").innerText = data.email;
    document.getElementById("phone").innerText = data.phoneNumber;
}

document.addEventListener("DOMContentLoaded", loadMyPage);
