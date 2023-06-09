//Cargar de manera automatica los datos regostrados
// Busqueda por id
function findById(id) {
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/usuario/' + id,
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (item) {
        $("#id").val(item.id)
        $("#tipodocumento").val(item.tipoDocumento)
        $("#documento").val(item.documento)
        $("#nombre").val(item.nombre)
        $("#apellido").val(item.apellido)
        $("#edad").val(item.edad)
        $("#genero").val(item.genero==true?'1':'0')
        $("#correo").val(item.correo)
        $("#contrasenia").val(item.contrasenia)
        $("#tipo").val(item.tipo)
        $("#telefono").val(item.celular)
        $("#direccion").val(item.direccion)
        $("#estado").val(item.estado==true?'1':'0')      
    })
}

function loadTable() {
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/usuario',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = "";
        items.forEach(function (item, index, array) {
            registros += `

                        <tr class="table-light">
                            <td>`+item.tipoDocumento+`</td>
                            <td>`+item.documento+`</td>
                            <td>`+item.nombre+`</td>
                            <td>`+item.apellido+`</td>
                            <td>`+item.edad+`</td>
                            <td>`+(item.genero==true?'Masculino':'Femenino')+`</td>
                            <td>`+item.correo+`</td>
                            <td>**********</td>
                            <td>`+item.tipo+`</td>
                            <td>`+item.celular+`</td>
                            <td>`+item.direccion+`</td>
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
        url: 'http://localhost:9000/backed-service/api/security/usuario/' + id,
        method: "delete",
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
            icon: 'error',
            title: 'Persona eliminada',
        });
        loadTable();
    })
}


//Accion de adicionar un registro
function Add(){
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/usuario',
        data: JSON.stringify({
            tipoDocumento: $("#tipodocumento").val(),
            documento: $("#documento").val(),
            nombre: $("#nombre").val(),
            apellido: $("#apellido").val(),
            edad: parseInt($("#edad").val()),
            genero: parseInt($("#genero").val()),
            correo: $("#correo").val(),
            contrasenia: $("#contrasenia").val(),
            tipo: $("#tipo").val(),
            celular: $("#telefono").val(),
            direccion: $("#direccion").val(),
            estado: parseInt($("#estado").val())
        }),
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (result) {

        //Cargar datos
        loadTable();

        //Limpiar formulario
        clearData();

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
        url: 'http://localhost:9000/backed-service/api/security/usuario/' + $("#id").val(),
        data: JSON.stringify({
            tipoDocumento: $("#tipodocumento").val(),
            documento: $("#documento").val(),
            nombre: $("#nombre").val(),
            apellido: $("#apellido").val(),
            edad: parseInt($("#edad").val()),
            genero: parseInt($("#genero").val()),
            correo: $("#correo").val(),
            contrasenia: $("#contrasenia").val(),
            tipo: $("#tipo").val(),
            celular: $("#telefono").val(),
            direccion: $("#direccion").val(),
            estado: parseInt($("#estado").val())
        }),
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (result) {
        //Cargar datos
        loadTable();

        //Limpiar formulario
        clearData();

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
    })
}

// Función para limpiar datos
function clearData(){
    $("#id").val(""),
    $("#tipodocumento").val(""),
    $("#documento").val(""),
    $("#nombre").val(""),
    $("#apellido").val(""),
    $("#edad").val(""),
    $("#genero").val(""),
    $("#correo").val(""),
    $("#contrasenia").val(""),
    $("#tipo").val(""),
    $("#telefono").val(""),
    $("#direccion").val(""),
    $("#estado").val("")
}