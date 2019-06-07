

$(document).ready(function () {
    const basicURL = "http://localhost:8080";
    const deleteReservation = "/deleteReservation/";
    const getUserLogin = "/getUserLogin";
    const loginPage ="/login";
    const userTravels ="/UserTravels";
    const updateUser ="/updateUser";
    const userFields ="/userData";
    const updateFirstName ="/updateUserFirstName";
    const updateLastName ="/updateUserLastName";
    const firstNameChangeField =$('#firstNameField');
    const lastNameChangeField =$('#lastNameField');
    const firstNameChangeForm = $('#firstNameChangeForm');
    const lastNameChangeForm = $('#lastNameChangeForm');

    const getTravels = $('#travels');
    const getUserData = $('#changeName');

    function useDataTableFields(basicURL, userFields) {
        $.get({
            url: basicURL.concat(userFields),
            contentType: 'application/json'
        }).done(function (e) {
            $('#firstName').text(e.firstName);
            $('#lastName').text(e.lastName);
            $('#email').text(e.email);
        });
    }

    let userData = $('#UserData');

    function getVal(selector) {
        return selector.val();
    }

    function createTicketTable() {
        const tab = $("<table>", {
            id: "results",
            class: "table table-striped"
        });
        const tableHeader = $("<tr>", {
            id: "headerCells"
        });
        const column1 = $("<th>", {
            class: "departure",
            html: "<b>" + "Departure from" + "</b>"
        });
        const column2 = $("<th>", {
            class: "arrivalTo",
            html: "<b>" + "Arrival to" + "</b>"
        });
        const column3 = $("<th>", {
            class: "travelDay",
            html: "<b>" + "Travel Date" + "</b>"
        });
        const column4 = $("<th>", {
            class: "travelTime",
            html: "<b>" + "Travel time" + "</b>"
        });
        const column5 = $("<th>", {
            class: "seatNumber",
            html: "<b>" + "seat number reserved" + "</b>"
        });
        const column6 = $("<th>", {
            class: "LinkForDeleting",
            html: "<b>" + "resignation of ticket" + "</b>"
        });
        tab.insertAfter(getTravels);
        tab.append(tableHeader
            .append(column1)
            .append(column2)
            .append(column3)
            .append(column4)
            .append(column5)
            .append(column6))
    }

    $.get({
        url: basicURL.concat(getUserLogin)
    }).done(function (e) {
        $('#welcome').html("<h2>welcome " + e + "</h2>")
    }).fail(function () {
        window.location.replace(basicURL.concat(loginPage));
    });

    function getTickets() {
        $.get({
            url: basicURL.concat(userTravels)
        }).done(function (result) {
            createTicketTable();
            result.forEach(function (index) {
                const row = $("<tr>", {
                    class: "result",
                });
                const col1 = $("<td>", {
                    class: "departure",
                    html: "<b>" + index.startPointCity + "</b>" + ", " + index.spAddress
                });
                const col2 = $("<td>", {
                    class: "arrivalTo",
                    html: "<b>" + index.endPointCity + "</b>" + ", " + index.epAddress
                });
                const col3 = $("<td>", {
                    class: "travelDay",
                    text: index.travelDate
                });
                const col4 = $("<td>", {
                    class: "travelTime",
                    text: index.departureTime.substring(0, 5)
                });
                const col5 = $("<td>", {
                    class: "seatsAvailable",
                    text: index.seatNumber
                });
                const col6 = $("<td>", {
                    class: "ticketLink",
                    html: "<a class='ticketLink2' href=" + index.ticketId + ">remove this ticket</a>"
                });
                $('#results')
                    .append(row
                        .append(col1)
                        .append(col2)
                        .append(col3)
                        .append(col4)
                        .append(col5)
                        .append(col6)
                    )
            });
            $('a.ticketLink2').on('click', function (event) {
                event.preventDefault();
                let ticket = $(this).attr('href');
                $.post({
                    url: basicURL.concat(deleteReservation),
                    contentType: 'application/json',
                    data: JSON.stringify(ticket)
                }).done(function () {
                    removeTicketTable();
                    getTickets();
                }).fail(function (fail) {
                    console.log(fail)
                })
            })
        })
    }

    function removeTicketTable() {
        $('#results').remove()
    }

    getTravels.click(function () {
        userData.hide();
        removeTicketTable();
        getTickets();
    });
    getUserData.click(function () {
        removeTicketTable();
        userData.show()
        });

    useDataTableFields(basicURL, userFields);

    firstNameChangeForm.submit(function (send) {
        send.preventDefault();
        let firstName = getVal(firstNameChangeField);
        $.post({
            url:basicURL.concat(updateFirstName),
            contentType: "application/json",
            data: JSON.stringify(firstName)
        }).done(function () {
            resetComment();
            const comment = $("<p>", {
                id: "comm1",
                class: "comment",
                text: "First name changed successfully"
            });
            firstNameChangeForm.append(comment);
            useDataTableFields(basicURL, userFields);
        }).fail(function () {
            resetComment();
            const comment = $("<p>", {
                id: "comm2",
                class: "comment",
                text: "First name has been NOT changed" +
                    " - 3 to 10 letters ONLY!"
            });
            firstNameChangeForm.append(comment);
        })
    });
    lastNameChangeForm.submit(function (send) {
        send.preventDefault();
        let lastName = getVal(lastNameChangeField);
        $.post({
            url:basicURL.concat(updateLastName),
            contentType: "application/json",
            data: JSON.stringify(lastName)
        }).done(function () {
            resetComment();
            const comment = $("<p>", {
                id: "comm1",
                class: "comment",
                text: "Last name changed successfully"
            });
            lastNameChangeForm.append(comment);
            useDataTableFields(basicURL, userFields);
        }).fail(function () {
            resetComment();
            const comment = $("<p>", {
                id: "comm2",
                class: "comment",
                text: "Last name has been NOT changed" +
                    " - 3 to 10 letters ONLY!"
            });
            lastNameChangeForm.append(comment);
        })
    });
    function resetComment() {
        let commentToRemove = $('.comment');
        commentToRemove.each(function () {
            $(this).remove()
        })
    }
});
