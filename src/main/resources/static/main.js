let roleList = [
    {id: 1, name: "ROLE_ADMIN"},
    {id: 2, name: "ROLE_USER"}
]

let isUser = true;
//Проверка роли
document.addEventListener("DOMContentLoaded", async function verification() {
    const roles = await fetch('http://localhost:8080/api/user');
    let role = "";
    let json = await roles.json();
    console.log(json);
    for (let i = 0; i < json.roles.length; i++) {
        role = json.roles[i].name;
        if (role === "ROLE_ADMIN") {
            isUser = false;
        }
    }
    if (isUser) {
        document.getElementById("user-tab").classList.add("active");
        document.getElementById("user").classList.add("show", "active");
        await getUser();
    } else {
        document.getElementById("admin-tab").classList.add("active");
        document.getElementById("admin").classList.add("show", "active");
        await getAllUsers();
    }
})
//Заполнение Header
document.addEventListener("DOMContentLoaded", async function header() {
    let html = ``;
    const infoUser = document.querySelector('#getUser');
    const response = await fetch('http://localhost:8080/api/user')
        .then(resp => {
            return resp.json()
        });
    const user = response;
    console.log(user);
    html += `<span>${user.username} </span>
<span>with roles </span>
<span>${user.roles.map(r => r.name)}</span>`
    infoUser.innerHTML = html;

})

