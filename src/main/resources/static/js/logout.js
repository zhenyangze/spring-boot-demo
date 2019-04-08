function logout() {
    var token = localStorage.getItem(storage_token_key);
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
