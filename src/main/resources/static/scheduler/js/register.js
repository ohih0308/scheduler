function register() {
    let email = document.getElementById("newEmailPrefix").value;
    let domain = document.getElementById("domain").value;
    let password = document.getElementById("newPassword").value;
    let username = document.getElementById("newName").value;

    $.ajax(
        {
            url: '/register',
            type: 'POST',
            data: JSON.stringify({email: email + domain, password: password, username: username}),
            contentType: 'application/json',
            success: function (response) {
                alert('응답 메시지: ' + response.message);
            },
            error: function (xhr, status, error) {
                alert('오류 발생: ' + error);
            }
        }
    )
}

document.getElementById("submitBun").addEventListener("click", register);