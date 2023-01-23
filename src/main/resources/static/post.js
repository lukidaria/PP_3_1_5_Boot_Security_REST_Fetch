document.querySelector('#addNewUser').addEventListener("click", async function add(evt) {
    evt.preventDefault();
    let addForm = document.querySelector('#formAddUser');
    let firstname = addForm.addFirstname.value;
    let lastname = addForm.addLastname.value;
    let age = addForm.addAge.value;
    let email = addForm.addEmail.value;
    let password = addForm.addPassword.value;
    let getRole = () => {
        let array = [];
        let role = document.querySelector('#Inputrole');
        console.log(role);
        for (let i = 0; i < role.length; i++) {
            if (role[i].selected) {
                array.push(roleList[i])
            }
        }
        return array;
    }

    let user = {
        firstName: firstname,
        lastName: lastname,
        age: age,
        username: email,
        password: password,
        roles: getRole()
    }

    let addUser = await fetch('http://localhost:8080/api/admin',
        {
            method: "POST",
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json',
            },
            body: JSON.stringify(user)});
    console.log(addUser.ok);
    if (addUser.ok) {
        await getAllUsers();
        let addForm = $('#formAddUser');
        addForm.find('#firstname').val('');
        addForm.find('#lastname').val('');
        addForm.find('#age').val('');
        addForm.find('#email').val('');
        addForm.find('#password').val('');
        addForm.find('#Inputrole').val('');
    }
})