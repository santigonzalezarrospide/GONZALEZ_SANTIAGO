window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_dentist');

    formulario.addEventListener('submit', function (event) {

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        const url = '/odontologos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        console.log("Datos del formulario:", JSON.stringify(formData));

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                '<strong></strong> Odontólogo agregado </div>';
                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
                })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                '<strong> Error al agregar el Odontólogo: </strong>' + error.message + '</div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            });
    });


    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }

});