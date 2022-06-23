
$(document).ready(function () {
    iniciarInterfaz();
});

function iniciarInterfaz() {
    $("#btnConsultar").on("click", consultarClientes)
}

function consultarClientes() {
    $.ajax({
        method: "POST",
        url: "./ServletTest",
        data: {accion: 1}
    }).done(function (res) {
        var data = res.data;
        data.map((item) => {
            var tr = $("<tr/>");
            tr.append($("<td/>").html(item.idcliente));
            tr.append($("<td/>").html(item.nombre));
            tr.append($("<td/>").html(item.apellido));
            tr.append($("<td/>").html(item.edad));
            tr.append($("<td/>").html(item.documento));
            tr.append($("<td/>").html(item.fecharegistro));
            tr.append($("<td/>").html((item.estado ? "Activo" : "Inactivo")));
            $("#tbodyClientes").append(tr);
        });
    });
}
