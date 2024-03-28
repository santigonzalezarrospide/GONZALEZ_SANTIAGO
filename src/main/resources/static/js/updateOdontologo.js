window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_dentist_form');

    formulario.addEventListener('submit', function (event) {
        let dentistId = document.querySelector('#dentist_id').value;

        const formData = {
            id: dentistId,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        const url = '/odontologos';
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

    //Es la funcion que se invoca cuando se hace click sobre el id de una odontologo del listado
    //se encarga de llenar el formulario con los datos de la odontologo
    //que se desea modificar
    function findBy(id) {
          const url = '/odontologos/'+id;

          console.log('Datos url:', url);
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              console.log('Datos recibidos:', data);
              let dentist = data;
              document.querySelector('#dentist_id').value = dentist.id;
              document.querySelector('#nombre').value = dentist.nombre;
              document.querySelector('#apellido').value = dentist.apellido;
              document.querySelector('#matricula').value = dentist.matricula;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_dentist_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }