function logout() {
    var token = localStorage.getItem("token");
    $.ajax({
        url: "/demo/logout",
        type: "get",
        headers: {
            "token": token
        },
        success: function (data) {
            localStorage.removeItem("token");
            location.href = "/demo/";
        }
    });
}
