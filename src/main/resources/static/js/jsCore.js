$(document).ready(function(){

    const login = $('input#login');
    const firstName = $('input#firstName');
    const lastName = $('input#lastName');
    const password = $('input#password');
    const email = $('input#email');

    const basicURL = 'http://localhost:8080/saveTraveler';

    let inputs = function (login, firstName, lastName, password,email) {
        this.firstName = firstName;
        this.login = login;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    };

    function resetComment() {
        let trToRemove = $('.comment')
        trToRemove.each(function () {
                $(this).remove()
        })
    };

    function getVal(selector) {
        return selector.val();
    }

    function passwordValidator() {
        if (getVal($('#password')) !== getVal($('#confirmPassword'))) {
            resetComment()
            const comment1 = $("<p>", {
                id: "comm2",
                class: "comment",
                text: "passwords are not the same!"
            })
            $('#formular').append(comment1)
            return true;
        } else {
            return false;
        }
    }

    $('#formular').submit(function (e) {
        e.preventDefault();
        if(passwordValidator()){
            return;
        }
        let travelerDTO = new inputs(
            getVal(login),
            getVal(firstName),
            getVal(lastName),
            getVal(password),
            getVal(email)
        );
        console.log(travelerDTO)
        $.post({
            url: 'http://localhost:8080/saveTraveler',
            data: JSON.stringify(travelerDTO),
            contentType: 'application/json'
        }).done(function () {
            resetComment()
            window.location.replace("http://localhost:8080/searchConnections");
        }).fail(function () {
            resetComment()
            const comment = $("<p>",{
                id: "comm2",
                class: "comment",
                text: "you have been NOT registered"
            })
            $('#formular').append(comment)
        })
    })
});