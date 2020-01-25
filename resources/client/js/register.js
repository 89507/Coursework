function pageLoadregister() {

    {
        document.getElementById("registersubmitButton").addEventListener("click", register);
    }

//debugger;
    function register(event) {
        debugger;
        event.preventDefault();

console.log("got to here 1 ")
        //const form = document.getElementById("registerform");
        //const formData = new FormData(form);

        /*fetch("/Users/Add", {method: 'post', body: formData}
        ).then(response => response.json()
        ).then(responseData => {*/

        //document.getElementById("Username").innerHTML = 'Add new User:';

        //document.getElementById("Username").value = '';
        //document.getElementById("FirstName").value = '';
        //document.getElementById("LastName").value = '';
        //document.getElementById("DOB").value = '';
        //document.getElementById("Email").value = '';
        //document.getElementById("Gender").value = '';
        //document.getElementById("Password").value = '';



console.log("got to here 2");

        const form = document.getElementById("registerform");
        const formData = new FormData(form);

        let apiPath = '/Users/Add';

        fetch(apiPath, {method: 'post', body: formData}
        ).then(response => response.json()
        ).then(responseData => {

         /*   if (responseData.hasOwnProperty('error')) {
                alert(responseData.error);
            } else {*/
                //document.getElementById("registerDiv".style.display = 'block');
                //pageLoadregister()
            window.location.href = "http://localhost:8081/client/Accounthome.html"
            });
        }
    }


