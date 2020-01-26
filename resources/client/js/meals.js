function pageLoadmeals() {

    document.getElementById("mealssubmitButton").addEventListener("click", meals);
}

//debugger;
function meals(event) {
    //debugger;
    event.preventDefault();

    //console.log("got to here 1 ")
    const form = document.getElementById("mealsForm");
    const formData = new FormData(form);

    fetch("/Meals/Add", {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {
        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {

            window.location.href = "http://localhost:8081/client/graphs.html";
            //document.getElementById("Username").innerHTML = 'Add new User:';


            document.getElementById("Name").value = '';
            document.getElementById("Posrtionsize").value = '';
            document.getElementById("carbs").value = '';
            document.getElementById("Custom").value = '';



            //const form = document.getElementById("registerform");
            //const formData = new FormData(form);

            let apiPath = '/Meals/Add';

            fetch(apiPath, {method: 'post', body: formData}
            ).then(response => response.json()
            ).then(responseData => {

                /*   if (responseData.hasOwnProperty('error')) {
                       alert(responseData.error);
                   } else {*/
                //document.getElementById("registerDiv".style.display = 'block');
                //pageLoadregister()
                window.location.href = "http://localhost:8081/client/graphs.html"
            });
        }
    })


}