document.addEventListener('DOMContentLoaded', function () {
    let loginBtn = document.getElementById('loginBtn');
    let signupBtn = document.getElementById('signupBtn');
    let logoutBtn = document.getElementById('logoutBtn');
    let schedulerBtn = document.getElementById('schedulerBtn');

    if (loginBtn) {
        loginBtn.addEventListener("click", function () {
            location.href = "/login";
        });
    }

    if (signupBtn) {
        signupBtn.addEventListener('click', function () {
            location.href = "/register";
        });
    }

    if (logoutBtn) {
        logoutBtn.addEventListener('click', function () {
            $.ajax({
                url: '/logout',
                type: 'POST',
                success: function (response) {
                    console.log(response);
                    window.location.reload();  // 로그아웃 후 페이지 새로고침
                },
                error: function (xhr, status, error) {
                    alert('오류 발생: ' + error);
                }
            });
        });
    }

    if (schedulerBtn) {
        schedulerBtn.addEventListener('click', function () {
            var currentDate = new Date();
            var month = currentDate.getMonth() + 1; // getMonth() returns 0-based index, so add 1
            var year = currentDate.getFullYear();
            var url = "/scheduler?month=" + month + "&year=" + year;
            window.location.href = url;
        });
    }
});
