function loadLibro(){ 
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/libro',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function(items){
        var registros = `<option selected="" selected disabled hidden>--- Seleccione ---</option>`;
        items.forEach(function(item, index, array){
            registros += `
                <option value="`+item.id+`">`+item.codigo+` - `+item.descripcion+`</option>
            `;
        })
        $("#libroId").html(registros);
    })
}

function loadRole(){
    $.ajax({
        url: 'http://localhost:9000/backend-service/api/security/role',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function(items){
        var registros = `<option selected="" selected disabled hidden>--- Seleccione ---</option>`;
        items.forEach(function(item, index, array){
            registros += `
                <option value="`+item.id+`">`+item.code+` - `+item.description+`</option>
            `;
        })
        $("#roleId").html(registros);
    })
}

function loadView(){
    $.ajax({
        url: 'http://localhost:9000/backend-service/api/security/view',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function(items){
        var registros = `<option selected="" selected disabled hidden>--- Seleccione ---</option>`;
        items.forEach(function(item, index, array){
            registros += `
                <option value="`+item.id+`">`+item.label+`</option>
            `;
        })
        $("#viewId").html(registros);
    })
}

function loadPerson(){
    $.ajax({
        url: 'http://localhost:9000/backed-service/api/security/usuario',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function(items){
        var registros = `<option selected="" selected disabled hidden>--- Seleccione ---</option>`;
        items.forEach(function(item, index, array){
            registros += `
                <option value="`+item.id+`">`+item.documento+` - `+item.nombre+`</option>
            `;
        })
        $("#usuarioId").html(registros);
    })
}

function loadUser(){
    $.ajax({
        url: 'http://localhost:9000/backend-service/api/security/user',
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).done(function(items){
        var registros = `<option selected="" selected disabled hidden>--- Seleccione ---</option>`;
        items.forEach(function(item, index, array){
            registros += `
                <option value="`+item.id+`">`+item.user+`</option>
            `;
        })
        $("#userId").html(registros);
    })
}