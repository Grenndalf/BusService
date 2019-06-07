$(document).ready(function () {
    const requiredFieldText = "this field is required";
    $('form').validate({
        rules: {
            login: {
                required: true,
                minlength: 3,
                maxlength: 10,
            },
            firstName: {
                required: true,
                minlength: 3,
                maxlength: 10,
            },
            lastName: {
                required: true,
                minlength: 3,
                maxlength: 10,
            }, password: {
                required: true,
                minlength: 3,
                maxlength: 10,
            }, confirmPassword: {
                required: true,
                equalTo: "#password"
            },
            email: {

                required: requiredFieldText,
                email: "please insert a proper email address"
            },
        },
        messages: {
            login: {
                required: requiredFieldText,
                minlength: "login has to be at least 3 long",
                maxlength: "login can be max 10 characters long",
            },
            firstName: {
                required: requiredFieldText,
                minlength: "first name has to be at least 3 long",
                maxlength: "first name can be max 10 characters long"
            },
            lastName: {
                required: requiredFieldText,
                minlength: "last name has to be at least 3 long",
                maxlength: "last name can be max 10 characters long"
            },
            password: {
                required: requiredFieldText,
                minlength: "password has to be at least 3 characters long",
                maxlength: "password name can be max 10 characters long",
            },
            confirmPassword: {
                required: requiredFieldText,
                equalTo:  "passwords has to be the same!"
            },
            email: {
                required: requiredFieldText,
                email: "please enter a valid email"
            },
        }
    });


});