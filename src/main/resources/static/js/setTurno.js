window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_turno');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault(); // Evitar el envío del formulario por defecto

        const formData = {
            // Recopilar los datos del formulario
            fecha: document.querySelector('#fecha').value,
            odontologo: obtenerOdontologoSeleccionado(),
            paciente: obtenerPacienteSeleccionado()
        };

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };
        console.log("Datos del formulario:", JSON.stringify(formData));

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno agregado </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();

            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error al agregar el turno: </strong>' + error.message + '</div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            });
    });

    // Función para limpiar el formulario después de crear el turno
    function resetUploadForm() {
        document.querySelector('#fecha').value = "";
        document.querySelector('#odontologos').selectedIndex = 0;
        document.querySelector('#pacientes').selectedIndex = 0;
    }

    // Función para obtener el objeto completo del odontólogo seleccionado
    function obtenerOdontologoSeleccionado() {
        const select = document.getElementById("odontologos");
        const selectedOption = select.options[select.selectedIndex];
        return {
            id: selectedOption.value,
            nombre: selectedOption.textContent.split(' ')[0], // Obtener nombre del odontólogo
            apellido: selectedOption.textContent.split(' ')[1] // Obtener apellido del odontólogo
        };
    }

    // Función para obtener el objeto completo del paciente seleccionado
    function obtenerPacienteSeleccionado() {
        const select = document.getElementById("pacientes");
        const selectedOption = select.options[select.selectedIndex];
        return {
            id: selectedOption.value,
            nombre: selectedOption.textContent.split(' ')[0], // Obtener nombre del paciente
            apellido: selectedOption.textContent.split(' ')[1] // Obtener apellido del paciente
        };
    }

    // Resto del código para cargar odontólogos y pacientes omitido para concisión

      // Función para cargar los odontólogos en el select
        function cargarOdontologos() {
            const url = '/odontologos';
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    const select = document.getElementById("odontologos");
                    // Limpiar opciones existentes del select
                    select.innerHTML = '';
                    // Agregar opción por defecto
                    select.appendChild(document.createElement("option")).textContent = 'Choose...';
                    // Agregar opciones de odontólogos al select
                    data.forEach(dentist => {
                        const option = document.createElement("option");
                        option.value = dentist.id;
                        option.textContent = dentist.nombre + ' ' + dentist.apellido;
                        select.appendChild(option);
                        });
                    })
                    .catch(error => console.error('Error al cargar odontólogos:', error));
                }

        // Función para cargar los pacientes en el select
        function cargarPacientes() {
            const url = '/pacientes';
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    const select = document.getElementById("pacientes");
                    // Limpiar opciones existentes del select
                    select.innerHTML = '';
                    // Agregar opción por defecto
                    select.appendChild(document.createElement("option")).textContent = 'Choose...';
                    // Agregar opciones de paceintes al select
                    data.forEach(paciente => {
                        const option = document.createElement("option");
                        option.value = paciente.id;
                        option.textContent = paciente.nombre + ' ' + paciente.apellido;
                        select.appendChild(option);
                        });
                    })
                    .catch(error => console.error('Error al cargar pacientes:', error));
                }

            cargarOdontologos();
            cargarPacientes();
});
