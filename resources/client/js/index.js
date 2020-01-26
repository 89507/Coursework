function pageLoad() {

    let now = new Date();

    let myHTML = '<div style="text-align:left;">'
        + '<div style="color: midnightblue;">'
        + '<h1>GlucoTrak</h1>'
        + '<img src="/client/img/samplelogo.jfif"  alt="Logo" width="100%"/>'
        + '<div style="font-style: italic;">'
        + '<div style="text-align: right;">'
        + 'Generated at ' + now.toLocaleTimeString()
        + '<div style="text-align: center;">'
        + '<h3>Welcome to GlucoTrak the new online diabetes tracker. This website has been created with you in mind. Easy to use and understand, GlucoTrak enables you to log your diabetes data in a logical format and track your blood sugars over time. If you are an existing user (well done you!), please login using the button at the top right of the page. If you are new user, please click on the button below to sign up and use GlucoTrak from today.</h3>'
        + '<div style="text-align: left"'
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;

}
function checkLogin() {

    let username = Cookies.get("Username");

    let logInHTML = '';

    if (username === undefined) {

        let editButtons = document.getElementsByClassName("editButton");
        for (let button of editButtons) {
            button.style.visibility = "visible";
        }

        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.style.visibility = "visible";
        }

        logInHTML = "Not logged in. <a href='/client/Login.html'>Log in</a>";
    } else {

        let editButtons = document.getElementsByClassName("editButton");
        for (let button of editButtons) {
            button.style.visibility = "visible";
        }

        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.style.visibility = "visible";
        }

        logInHTML = "Logged in as " + username + ". <a href='/client/Login.html?logout'>Log out</a>";

    }

    document.getElementById("loggedInDetails").innerHTML = logInHTML;

}

