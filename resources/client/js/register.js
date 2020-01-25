function pageLoadregister() {
//debugger;
    function register(event) {
        //debugger;
        event.preventDefault();

        const form = document.getElementById("registerform");
        const formData = new FormData(form);

        fetch("/Users/add", {method: 'post', body: formData}
        ).then(response => response.json()
        ).then(responseData => {

            document.getElementById("Username").value = FormData.Username;
            document.getElementById("FirstName").value = FormData.FirstName;
            document.getElementById("LastName").value = FormData.LastName;
            document.getElementById("DOB").value = FormData.DOB;
            document.getElementById("Email").value = FormData.Email;
            document.getElementById("Gender").value = FormData.Gender;
            document.getElementById("Password").value = FormData.Password;


            const id = document.getElementById("Username").value;
            const form = document.getElementById("registerform");
            const fromData = new FormData(form);

            let apiPath = '/Users/add';

            fetch(apiPath, {method: 'post', body:formData}
            ).then(response => response.json()
            ). then(responseData => {

                if(responseData.hasOwnProperty('error')) {
                    alert(responseData.error);
                }else {
                    document.getElementById("registerDiv".style.display = 'block')
                    pageLoadregister()
                }


            }
        });
    }
}