function loadTable() {
    // Obtener el ID del usuario almacenado en localStorage
    var userId = localStorage.getItem('userId');

    // Realizar solicitud AJAX para obtener los libros prestados del usuario
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/prestamo/usuario/' + userId,
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function (items) {
        var registros = "";
        var data = "";
        items.forEach(function (item, index, array) {
            registros += `
            
                <tr class="table-light">
                    <td>` + item.fechaPrestamo + `</td>
                    <td>` + item.fechaEntrega + `</td>
                    <td>` + item.libroId.descripcion + `</td>
                    <td>` + item.totalPagar + `</td>
                    <td>` + (item.estado ? 'Activo' : 'Inactivo') + `</td>
                    <td><button class="btnDelete" type="button" onclick="deleteById(` + item.id + `);"><i class="fi fi-rr-trash"></i></button></td>
                </tr>
            `;

            data += `
            <h2>HOLA👋 ` + item.usuarioId.nombre + ` ` + item.usuarioId.apellido + `</h2>
            <br>
            <p>Ahora puedes observar tus prestamos de libros aqui!</p>
            `;
        });
        $('#dataResult').html(registros);
        $('#datos').html(data);
        console.log(items);
    });
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
        
        loadTable();

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
            title: 'Registro eliminado',
        })

        
    })
}