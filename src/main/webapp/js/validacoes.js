$(document).ready(function () {
    $(".numeros").keydown(function (e) {
        let tecla = e.which
        let isNumber = (tecla > 47 && tecla < 58 || tecla > 95 && tecla < 106)
        let isOther = (",8,46,37,38,39,40".indexOf("," + tecla + ",") > -1)
        if (isNumber || isOther)
            return true
        return false
    });

    $(".celular").mask("(99) 9.9999-9999");
    $(".fixo").mask("(99) 9999-9999");



})

// apenas numeros
function SomenteNumero(e) {

}
