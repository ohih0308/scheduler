document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('loginBtn').addEventListener('click', function () {
        login();
    })
})

function login() {
    let emailPrefix = document.getElementById("emailPrefix").value;
    let emailSuffix = document.getElementById("emailSuffix").value;
    let password = document.getElementById("password").value;


    $.ajax({
        url: '/',
        type: 'POST',
        data: JSON.stringify({email: emailPrefix + emailSuffix, password: password}),
        contentType: 'application/json',
        success: function (userResponse) {
            getMessageByResponseCode(userResponse.responseCode).then(function (message) {
                alert(message);
                var currentDate = new Date();
                var month = currentDate.getMonth() + 1;
                var year = currentDate.getFullYear();
                window.location.href = userResponse.redirectUrl + "?month=" + month + "&year=" + year;

            }).catch(function (error) {
                alert('오류 발생: ' + error);
            });
        },
        error: function (xhr, status, error) {
            alert('오류 발생: ' + error);
        }
    });
}