//Cargar de manera automatica los datos regostrados
// Busqueda por id
function findById(id) {
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/prestamo/' + id,
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (item) {
        $("#id").val(item.id)
        $("#usuarioId").val(item.usuarioId.id)
        $("#libroId").val(item.libroId.id)
        $("#fechaprestamo").val(item.fechaPrestamo)
        $("#fechaentrega").val(item.fechaEntrega)
        $("#estado").val(item.estado==true?'1':'0')      
    })
}

function loadTable() {
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/prestamo',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = "";
        items.forEach(function (item, index, array) {
            registros += `

                        <tr class="table-light">
                            <td>`+item.usuarioId.nombre+`</td>
                            <td>`+item.libroId.descripcion+`</td>
                            <td>`+item.fechaPrestamo+`</td>
                            <td>`+item.fechaEntrega+`</td>
                            <td>`+item.totalPagar+`</td>
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
        url: 'http://localhost:9000/backed-service/api/security/prestamo/' + id,
        method: "delete",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (result) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 4000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
          
        Toast.fire({
            icon: 'error',
            title: 'Usuario eliminado',
        });
        loadTable();
    })
}


//Accion de adicionar un registro
function Add(){

    var fechaPrestamo = document.getElementById('fechaprestamo');
    var fechaEntrega = document.getElementById('fechaentrega');
    var fechaValor1 = fechaPrestamo.value;
    var fechaValor2 = fechaEntrega.value;
    var fecha1 = new Date(fechaValor1);
    var fecha2 = new Date(fechaValor2);
    var dia1 = fecha1.getDate();
    var dia2 = fecha2.getDate();
    var valorDia = 2000;
    var valorPagar= (dia2 - dia1) * valorDia;


    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/prestamo',
        data: JSON.stringify({
            usuarioId: {
                id:$("#usuarioId").val()
            },
            libroId: {
                id:$("#libroId").val()
            },
            fechaPrestamo: $("#fechaprestamo").val(),
            fechaEntrega: $("#fechaentrega").val(),  
            estado: parseInt($("#estado").val()),
            totalPagar: valorPagar
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
            timer: 4000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
          
        Toast.fire({
            icon: 'success',
            title: 'Registro exitoso',
        });
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

    var fechaPrestamo = document.getElementById('fechaprestamo');
    var fechaEntrega = document.getElementById('fechaentrega');
    var fechaValor1 = fechaPrestamo.value;
    var fechaValor2 = fechaEntrega.value;
    var fecha1 = new Date(fechaValor1);
    var fecha2 = new Date(fechaValor2);
    var dia1 = fecha1.getDate();
    var dia2 = fecha2.getDate();
    var valorDia = 2000;
    var valorPagar= (dia2 - dia1) * valorDia;

    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/prestamo/' + $("#id").val(),
        data: JSON.stringify({
            usuarioId: {
                id:$("#usuarioId").val()
            },
            libroId: {
                id:$("#libroId").val()
            },
            fechaPrestamo: $("#fechaprestamo").val(),
            fechaEntrega: $("#fechaentrega").val(),  
            estado: parseInt($("#estado").val()),
            totalPagar: valorPagar
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
            timer: 4000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener('mouseenter', Swal.stopTimer)
              toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
          
        Toast.fire({
            icon: 'warning',
            title: 'Modificación exitosa',
        });
    })
}

// Función para limpiar datos
function clearData(){
    $("#id").val(""),
    $("#usuarioId").val(""),
    $("#libroId").val(""),
    $("#fechaprestamo").val(""),
    $("#fechaentrega").val(""),
    $("#estado").val("")
}