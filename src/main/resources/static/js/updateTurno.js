window.addEventListener('load', function () {

    const formulario = document.querySelector('#div_turno_updating');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;

        const formData = {
            id: turnoId,
            odontologo: document.querySelector('#odontologo').value,
            paciente: document.querySelector('#paciente').value,
            fecha: document.querySelector('#fecha').value,
        };

        console.log("FormData: " , formData)

        const url = '/turnos';

        console.log("URL: " + url)
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })



    function findBy(id) {
          const url = '/turnos/'+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#paciente').value = turno.paciente.nombre;
              document.querySelector('#odontologo').value = turno.odontologo.nombre;
              document.querySelector('#fecha').value = turno.fecha;

              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }


       // Función para obtener el objeto completo del odontólogo seleccionado
          function obtenerOdontologoSeleccionado() {
              const select = document.getElementById("odontologo");
              const selectedOption = select.options[select.selectedIndex];
              return {
                  id: selectedOption.value,
                  nombre: selectedOption.textContent.split(' ')[0], // Obtener nombre del odontólogo
                  apellido: selectedOption.textContent.split(' ')[1] // Obtener apellido del odontólogo
              };
          }

          // Función para obtener el objeto completo del paciente seleccionado
          function obtenerPacienteSeleccionado() {
              const select = document.getElementById("paciente");
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
                          const select = document.getElementById("odontologo");
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
                          const select = document.getElementById("paciente");
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