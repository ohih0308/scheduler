function register() {
    let emailPrefix = document.getElementById("emailPrefix").value;
    let emailSuffix = document.getElementById("emailSuffix").value;
    let password = document.getElementById("password").value;
    let username = document.getElementById("username").value;

    $.ajax({
        url: '/register',
        type: 'POST',
        data: JSON.stringify({email: emailPrefix + emailSuffix, password: password, username: username}),
        contentType: 'application/json',
        success: function (userResponse) {
            getMessageByResponseCode(userResponse.responseCode).then(function (message) {
                alert(message);
                window.location.href = userResponse.redirectUrl;
            }).catch(function (error) {
                alert('오류 발생: ' + error);
            });
        },
        error: function (xhr, status, error) {
            alert('오류 발생: ' + error);
        }
    });
}

document.getElementById("submitBtn").addEventListener("click", register);