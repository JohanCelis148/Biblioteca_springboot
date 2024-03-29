//Cargar de manera automatica los datos regostrados
// Busqueda por id
function findById(id) {
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/libro/' + id,
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (item) {
        $("#id").val(item.id)
        $("#codigo").val(item.codigo)
        $("#descripcion").val(item.descripcion)
        $("#autor").val(item.autor)
        $("#ejemplar").val(item.ejemplar)
        $("#estado").val(item.estado==true?'1':'0')      
    })
}

function loadTable() {
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/libro',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = "";
        items.forEach(function (item, index, array) {
            registros += `

                        <tr class="table-light">
                            <td>`+item.codigo+`</td>
                            <td>`+item.descripcion+`</td>
                            <td>`+item.autor+`</td>
                            <td>`+item.ejemplar+`</td>
                            <td>`+(item.estado==true?'Activo':'Inactivo')+`</td>
                            <td><button class="btnEdit" type="button" onclick="findById(`+item.id+`);"><i class="fi fi-rr-pencil"></i></button></td>
                            <td><button class="btnDelete" type="button" onclick="deleteById(`+item.id+`);"><i class="fi fi-rr-trash"></i></button></td>
                        </tr>
                        `;
        })
        $("#dataResult").html(registros);   
    })
}

//Accion para eliminar un registro seleccionado 
function deleteById(id){
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/libro/' + id,
        method: "delete",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (result) {
        
        loadTable();

        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
          
        Toast.fire({
            icon: 'error',
            title: 'Modulo eliminado',
        })

        
    })
}


//Accion de adicionar un registro
function Add(){
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/libro',
        data: JSON.stringify({
            codigo: $("#codigo").val(),
            descripcion: $("#descripcion").val(),
            autor: $("#autor").val(),
            ejemplar: $("#ejemplar").val(),
            estado: parseInt($("#estado").val()),
            userCreationId: 1,
            dateCreation: new Date()
        }),
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (result) {

        // Si la respuesta es un ok
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
          
        Toast.fire({
            icon: 'success',
            title: 'Registro exitoso',
        })

        //Cargar datos
        loadTable();

        //Limpiar formulario
        clearData();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        // Si la respuesta es un error
        Swal.fire({
            icon: 'error',
            title: "Error",
            text: jqXHR.responseJSON.message,
        })      
    });
}


//Accion de actualizar un registro
function Update(){
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/libro/' + $("#id").val(),
        data: JSON.stringify({
            codigo: $("#codigo").val(),
            descripcion: $("#descripcion").val(),
            autor: $("#autor").val(),
            ejemplar: $("#ejemplar").val(),
            estado: parseInt($("#estado").val()),
        }),
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (result) {

        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 2000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
          
        Toast.fire({
            icon: 'warning',
            title: 'Modificación exitosa',
        })

        //Cargar datos
        loadTable();

        //Limpiar formulario
        clearData();
    })
}

// Función para limpiar datos
function clearData(){
    $("#id").val(""),
    $("#codigo").val(""),
    $("#descripcion").val(""),
    $("#autor").val(""),
    $("#state").val("")
}

// Función datatable
$(document).ready(function() {
    $('#tableData').DataTable();
});