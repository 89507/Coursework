function pageLoadbloodsugar() {


    document.getElementById("bloodsugarsubmitButton").addEventListener("click", bloodsugar);
}

//debugger;
function bloodsugar(event) {
    //debugger;
    event.preventDefault();

    //console.log("got to here 1 ")
    const form = document.getElementById("bloodsugarForm");
    const formData = new FormData(form);

    fetch("/BloodSugarTracker/Add", {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {
        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {

            window.location.href = "http://localhost:8081/client/graphs.html";
            //document.getElementById("Username").innerHTML = 'Add new User:';


            document.getElementById("Date").value = '';
            document.getElementById("Time").value = '';
            document.getElementById("SugarLevel").value = '';




            //const form = document.getElementById("registerform");
            //const formData = new FormData(form);

            let apiPath = '/BloodSugarTracker/Add';

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
    })}

