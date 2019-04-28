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
        console.log(travelerDTO)
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/saveTraveler',
            data: JSON.stringify(travelerDTO),
            contentType: 'application/json'
        }).done(function (e) {
            console.log(e)
        }).fail(function (d) {
            console.log(d)
        })
    })
});