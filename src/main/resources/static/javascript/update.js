document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("update");

    form.addEventListener("submit", async function (event) {
        event.preventDefault(); // stoppar vanlig submit

        document.getElementById("emailError").textContent = "";
        document.getElementById("phoneError").textContent = "";

        const formData = new FormData(form);
        const token = localStorage.getItem("jwt");
        const data = Object.fromEntries(formData);

        console.log("\n token" + token + "\n")

        const response = await fetch("/connect/update", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        if (response.status === 401) {
            window.location.href = "/login";
            return;
        }

        const responseData = await response.json();

        if (responseData.emailError) {
            document.getElementById("emailError").textContent = responseData.emailError;
        }

        if (responseData.phoneError) {
            document.getElementById("phoneError").textContent = responseData.phoneError;
        }

        if (responseData.success) {
            window.location.href = "/mypage";
        }
    });
});
