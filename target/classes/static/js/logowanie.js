$(document).ready(function () {

    let inputs = function (login, password) {
        this.login = login;
        this.password = password;
    }

    $("#form").on('submit', function abcd(e) {
        e.stopPropagation();
        e.preventDefault()

        var submitThisForm = $(this);
        var someSimpleClassForLogIn = new inputs($("#username").val(), $("#password").val())
        console.log(someSimpleClassForLogIn)
        $.post({

            url: 'http://localhost:8080/hmm',
            contentType: 'application/json',
            data: JSON.stringify(someSimpleClassForLogIn)
        }).done(function () {
            $('#form').off('submit')
            $('#form').submit();
            return false;
        }).fail(function () {
            console.log("doesnt passed")
            return false;
        })
    })
})