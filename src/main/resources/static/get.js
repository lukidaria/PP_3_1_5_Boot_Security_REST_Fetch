async function getAllUsers() {
    let get = await fetch('http://localhost:8080/api/admin');
    let html = ``;
    if (get.ok) {
        const table = document.querySelector("#usersTable tbody")
        let json = await get.json();
        json.forEach(users => {
            html += `
            <tr class="table-light align-middle table-striped" style="height: 50px">
                <td>${users.id}</td>
                <td>${users.firstName}</td>
                <td>${users.lastName}</td>
                <td>${users.age}</td>
                <td>${users.username}</td>
                <td>${users.roles.map(role => role.name)} </td>
                <td>
                    <button class="btn btn-primary" data-userid="${users.id}" data-action="edit" id="getEditModal"
                     type="button" value="edit">edit</button>
                </td>
                <td>
                    <button class="btn btn-danger"  data-userid="${users.id}" data-action="delete" 
                    id="getDeleteModal" type="button" value="delete">delete</button>
                </td>
            </tr>           
           `
        })
        table.innerHTML = html;
    } else {
        alert("Ошибка HTTP" + get.status);
    }



let buttonModal = document.querySelectorAll('#usersTable > tbody > tr > td > button')
buttonModal.forEach(elem => elem.addEventListener('click', function (evt) {
    const myModal = new bootstrap.Modal(document.getElementById('modal'), {
        keyboard: true,
        backdrop: true,
    });
    let targetButton = $(evt.target);
    let buttonUserId = targetButton.attr('data-userid');
    let buttonAction = targetButton.attr('data-action');
    $('#modal').attr('data-userid', buttonUserId);
    $('#modal').attr('data-action', buttonAction);
    myModal.show();
}))

}


async function getUser() {
    let get = await fetch('http://localhost:8080/api/user');
    if (get.ok) {
        const table = document.querySelector("#tableUser tbody")
        let user = await get.json();
        let html = `
            <tr class="table-light align-middle" style="height: 50px">
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.username}</td>
                <td>${user.roles.map(role => " " + role.name)}</td>
            </tr>
        `
        table.innerHTML = html;
    } else {
        alert("Ошибка HTTP" + get.status);
    }
}


//TAB
document.querySelector('#admin-tab').addEventListener("click", async function(){
    document.getElementById("admin-tab").classList.add("active");
    document.getElementById("admin").classList.add("show", "active");
    $('#user').tab('dispose');
    await getAllUsers();

})

document.querySelector('#user-tab').addEventListener('click', async function(){
    document.getElementById("user-tab").classList.add("active");
    document.getElementById("user").classList.add("show", "active");
    $('#admin').tab('dispose');
    // document.getElementById("admin-tab").classList.add("non-active");
    // document.getElementById("admin").classList.add("close", "non-active");
    await getUser();
})




let elemModal = document.querySelector('#modal');

elemModal.addEventListener("show.bs.modal",  async function (evt) {
    let thisModal = $(evt.target);
    let userid = document.querySelector('#modal').getAttribute('data-userid');
    let action = document.querySelector('#modal').getAttribute('data-action');
    if (action === 'edit') {
        await editUser(userid, thisModal);
    } else if (action === 'delete') {
        await deleteUser(userid, thisModal);
    }
})
elemModal.addEventListener("hidden.bs.modal", function (evt) {
    let thisModal = $(evt.target);
    thisModal.attr('data-action', '');
    thisModal.attr('data-userid', '');
    thisModal.find('.modal-title').html(``);
    thisModal.find('.modal-body').html(``);
    thisModal.find('.modal-footer').html(``);
})

elemModal.addEventListener("hidePrevented.bs.modal", function (evt) {
    let thisModal = $(evt.target);
    thisModal.attr('data-action', '')
    thisModal.attr('data-userid', '')
    thisModal.find('.modal-title').html(``);
    thisModal.find('.modal-body').html(``);
    thisModal.find('.modal-footer').html(``);
})
