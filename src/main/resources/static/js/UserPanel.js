$(document).ready(function () {
    const link = "http://localhost:8080/deleteReservation/";
    const getTravels = $('#travels');
    const getUserData = $('#changeName');
    let inputs = function (firstName, lastName, email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    };
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
        url: 'http://localhost:8080/getUserLogin'
    }).done(function (e) {
        $('#welcome').html("<h2>welcome " + e + "</h2>")
    }).fail(function () {
        window.location.replace("http://localhost:8080/login");
    });

    function getTickets() {
        $.get({
            url: "http://localhost:8080/UserTravels"
        }).done(function (result) {
            createTicketTable()
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
                    url: link,
                    contentType: 'application/json',
                    data: JSON.stringify(ticket)
                }).done(function () {
                    removeTicketTable();
                    getTickets();
                    console.log("hurrra!")
                }).fail(function () {
                    console.log("not hurra :(")
                })

            })
        })
    }

    function removeTicketTable() {
        $('#results').remove()
    }

    getTravels.click(function () {
        removeTicketTable();
        getTickets();
        hideData()
    });
    getUserData.click(function () {
        removeTicketTable();
        let travelerDTO = new inputs(
            getVal($('#changeFirstName')),
            getVal($('#changeLastName')),
            getVal($('#changeEmail')));

        hideData();
        });
    function hideData() {
        if (userData.is(':hidden')) {
            userData.show()
        } else {
            userData.hide()
        }
    }
    $('#DataChange').submit(function () {
        let travelerDTO = new inputs(
            getVal($('#changeFirstName')),
            getVal($('#changeLastName')),
            getVal($('#changeEmail')));
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/updateUser',
            data: JSON.stringify(travelerDTO),
            contentType: 'application/json'
        }).done(function (e) {
            console.log(e)
        })
    })

    $.get({
        url: 'http://localhost:8080/userData'
    }).done(function (e) {
        $('#firstName').text(e.firstName);
        $('#lastName').text(e.lastName);
        $('#email').text(e.email);

        $('#changeFirstName').val(e.firstName);
        $('#changeLastName').val(e.lastName);
        $('#changeEmail').val(e.email);
    });
});
