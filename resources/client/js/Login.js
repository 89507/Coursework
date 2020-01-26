function pageLoad1() {

/*}
    if(window.location.search === '?logout') {
        document.getElementById('content').innerHTML = '<h1>Logging out, please wait...</h1>';
        logout();
    } else{ */



        document.getElementById("loginButton").addEventListener("click", login);




function login(event) {
    //debugger;
    event.preventDefault();

    const form = document.getElementById("loginForm");
    const formData = new FormData(form);

    fetch("/Users/login", {method: 'post', body: formData}
    ).then(response => response.json()
).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
        alert(responseData.error);
    } else {
        Cookies.set("Username", responseData.Username)
        Cookies.set("Token", responseData.token);

        window.location.href = "http://localhost:8081/client/Accounthome.html" ;
    }
});
}
function logout() {

    fetch("/Users/logout", {method: 'post'}
    ).then(response => response.json()
).then(responseData => {
        if (responseData.hasOwnProperty('error')) {

            alert(responseData.error);

        } else {

            Cookies.remove("username");
            Cookies.remove("token");

            window.location.href = '/client/index.html';
        }
    });
}
}




