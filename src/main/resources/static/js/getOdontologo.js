window.addEventListener('load', function () {
    (function(){
            const url = '/odontologos';
            const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {

            for(dentist of data){

                var table = document.getElementById("dentistTable");
                var dentistRow = table.insertRow();
                let tr_id = 'tr_' + dentist.id;
                dentistRow.id = tr_id;

                 let deleteButton = '<button' + ' id=' + '\"' + 'btn_delete_' + dentist.id + '\"' + ' type="button" onclick="deleteBy('+dentist.id+')" class="btn btn-danger btn_delete">' + '&times' + '</button>';
                 let updateButton = '<button' + ' id=' + '\"' + 'btn_id_' + dentist.id + '\"' + ' type="button" onclick="findBy('+dentist.id+')" class="btn btn-info btn_id">' + dentist.id + '</button>';

                 dentistRow.innerHTML =
                                    '<td class=\"td_id\">' + dentist.id + '</td>' +
                                    '<td class=\"td_nombre\">' + dentist.nombre.toUpperCase() + '</td>' +
                                    '<td class=\"td_apellido\">' + dentist.apellido.toUpperCase() + '</td>' +
                                    '<td class=\"td_matricula\">' + dentist.matricula + '</td>' +
                                    '<td>' + updateButton + deleteButton + '</td>';
            };

        })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/odontologoGet.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })

})