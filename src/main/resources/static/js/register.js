$(document).ready(function () {
    const form = $('#form');
    const login = $('input#login');
    const firstName = $('input#firstName');
    const lastName = $('input#lastName');
    const password = $('input#password');
    const email = $('input#email');
    const basicURL = 'http://localhost:8080';
    const saveTraveler = '/saveTraveler';
    const userPanel = '/UserPanel';

    let inputs = function (login, firstName, lastName, password, email) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    };

    function resetComment() {
        let commentToRemove = $('.comment');
        commentToRemove.each(function () {
            $(this).remove()
        })
    }

    function getVal(selector) {
        return selector.val();
    }

    form.submit(function (sendForm) {
        sendForm.preventDefault();
        let travelerDTO = new inputs(
            getVal(login),
            getVal(firstName),
            getVal(lastName),
            getVal(password),
            getVal(email)
        );
        $.post({
            url: basicURL.concat(saveTraveler),
            data: JSON.stringify(travelerDTO),
            contentType: 'application/json'
        }).done(function () {
            resetComment();
            window.location.href="/";
        }).fail(function () {
            resetComment();
            const comment = $("<p>", {
                id: "comm2",
                class: "comment",
                text: "you have been NOT registered"
            });
            form.append(comment)
        })
    })
});