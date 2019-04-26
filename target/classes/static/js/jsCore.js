$(document).ready(function(){

    const login = $('input#login');
    const firstName = $('input#firstName');
    const lastName = $('input#lastName');
    const password = $('input#password');

    const get = 'GET';
    const deleteItem = 'DELETE';
    const post = 'POST';
    const basicURL = 'http://localhost:8080/saveTraveler';

    let inputs = function (login, firstName, lastName, password) {
        this.firstName = firstName;
        this.login = login;
        this.lastName = lastName;
        this.password = password;
    };

    function getVal(selector) {
        return selector.val();
    }

    $('#formular').submit(function (e) {
        e.preventDefault();
        let travelerDTO = new inputs(
            getVal(login),
            getVal(firstName),
            getVal(lastName),
            getVal(password)
        );
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/saveTraveler',
            data: JSON.stringify(travelerDTO),
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (errors) {
            console.log(errors)
            console.log("was OK")
        }).fail(function (s) {
            console.log(s)
        })
    })
});