function Login() {
    let user = document.getElementById('txtuser').value;
    let password = document.getElementById('txtpassword').value;

    // Realizar solicitud AJAX para obtener los usuarios del endpoint
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/usuario',
        method: 'GET',
        success: function (response) {
            // Verificar si el usuario existe y obtener su rol
            const encontrarUsuario = response.find(item => item.documento === user && item.contrasenia === password);
            
            if (encontrarUsuario) {
                // Almacenar el ID del usuario en el localStorage
                localStorage.setItem('userId', encontrarUsuario.id);
                const admin = encontrarUsuario.tipo;

                Swal.fire({
                    icon: 'success',
                    title: 'Bienvenido',
                    showDenyButton: true,
                    showCancelButton: true,
                    confirmButtonText: 'Continuar',
                    denyButtonText: `No continuar`,
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Redirigir a diferentes dashboards según el rol del usuario
                        if (admin == "Administrador") {
                            window.location.assign('Dashboard.html');
                        } else {
                            window.location.assign('Cliente.html');
                        }
                    } else if (result.isDenied) {
                        Swal.fire('Se canceló el ingreso', '', 'info');
                        Clear();
                    }
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error en la autenticación',
                    text: 'Revisa que los datos ingresados sean correctos',
                });
                Clear();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            Swal.fire({
                icon: 'error',
                title: 'Error al obtener los usuarios',
                text: 'No se pudo obtener la lista de usuarios',
            });
        }
    });
}


function SignOut() {
    Swal.fire({
        title: '¡Hasta pronto!',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'Cerrar Sesión',
        denyButtonText: `Continuar en la Sesión`,
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire('Saved!', '', 'success');
            window.location.assign('login.html');
        } else if (result.isDenied) {
            Swal.fire('Se continúa en la sesión', '', 'info');
            Clear();
        }
    });
}

function Clear() {
    document.getElementById('txtuser').value = '';
    document.getElementById('txtPassword').value = '';
}



//pintar permiso

function loadPermission(){
    
    $.ajax({
        url: 'http://localhost:9000/backend-service/api/security/user/permission/J12/1234',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
        
    }).done(function (items) {
        var permission = `
                <span class="modulo">Seguridad</span>
                <li>
                    <a href="Board.html" target="workSpace">
                        <i class="fi fi-rr-layout-fluid"></i>
                        <span class="links_name">Tablero</span>
                    </a>
                    <span class="tooltip">Tablero</span>
                </li>
                <li class="profile">
                    <div class="profile-details">
                        <img src="../Asset/Img/myAvatar.png" alt="profileImg">
                        <div class="name_job">
                            <div class="name">Johan Celis</div>
                            <div class="job">Administrador</div>
                        </div>
                        
                    </div>
                    <a id="log_out" name='cerrar' onclick="SignOut()"><i class='bx bx-log-out bx-lg'  ></i></a>
                </li>
        `;
        items.forEach(function (item, index, array) {
            permission += `
                <li>
                    <a href="`+item.moduleRoute+`/`+item.viewRoute+`" target="workSpace">
                        <i class="`+item.viewIcon+`"></i>
                        <span class="links_name">`+item.viewLabel+`</span>
                    </a>
                    <span class="tooltip">`+item.viewLabel+`</span>
                </li>
            `;
        })
        $("#dataPermission").html(permission);   
    })
}