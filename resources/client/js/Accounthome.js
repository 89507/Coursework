function pageLoadaccounthome() {

    let now = new Date();

    let myHTML = '<div style="text-align:left;">'
        + '<div style="color: midnightblue;">'
        + '<h1>GlucoTrak</h1>'
        + '<h2>Welcome to your GlucoTrak account page. From here you can choose what information you want to store or choose to look at your data history by clicking on the graph button above right.</h2>'
        + '<img style="float: left; margin: 0px 15px 15px 0px;" src="/client/img/BloodSugarImage.jpg"  alt="Logo" width="25%"/>'
        + '<img style="float: left; margin: 0px 15px 15px 0px;" src="/client/img/MealsImage.jpg"   alt="Logo" width="22%"/>'
        + '<img style="float: left; margin: 0px 15px 15px 0px;" src="/client/img/InsulinImage1.jpg"   alt="Logo" width="22%"/>'
        + '<img style="position: absolute; margin: 0px 0px 0px 0px;" src="/client/img/ConversionImage1.jpg"   alt="Logo" width="25%"/>'
        //+ '<img src="/client/img/ConversionImage.jpg"  alt="Logo" width="20%"/>'
        + '<div style="font-style: italic;">'
        + '<div style="text-align: right;">'
        + '<div style="text-align: center;">'
        + '<div style="text-align: left"'
        + '<div id="footer">'
        + '<h5></h5>'
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;

}


    /*addEventListener("open", getCookie);
    function getCookie(Username) {
        let name = Username + "=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);

            }
        }
        return "";
    }
}*/





     /*let Usernamehtml = '<table>' +
         '<tr>' +
         '<th>Username</th>' +
         '</tr>';
    fetch('Users/list', {method: get}
    )*/