$.ajax({
        method: "POST",
        url: "./Peticiones",
        data: {accion: 1}
    }).done(function (res) {
        var data = res.data;
        data.map((item) => {
            var tr = $("<tr/>");
            tr.append($("<td/>").html(item.descripcion));
            tr.append($("<td/>").html(item.identificacion));
            tr.append($("<td/>").html(item.nombres));
            tr.append($("<td/>").html(item.apellidos));
            tr.append($("<td/>").html(item.login));
            tr.append($("<td/>").html(item.direccion));
            tr.append($("<td/>").html(item.emailcontacto));
            $("#tbodyUsuarios").append(tr);
        });
    });
