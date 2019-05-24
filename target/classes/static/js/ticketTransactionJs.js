$(document).ready(function () {

    function resetComment() {
        let trToRemove = $('.comment');
        trToRemove.each(function () {
            $(this).remove()
        })
    }

    function TicketInfo() {
        $.get({
            url: 'http://localhost:8080/getData',
            contentType: 'application/json',
        }).done(function (railways) {
            console.log(railways)
            $('#destination').html("<b>" + railways.endPoint.city + "</b>" + ", " + railways.endPoint.railwayAddress);
            $('#startPoint').html("<b>" + railways.startPoint.city + "</b>" + ", " + railways.startPoint.railwayAddress);
            $('#travelDate').html(railways.travelDate);
            $('#travelTime').html(railways.departureTime);
        }).fail(function (fail) {
            console.log(fail)
        });

        $.get({
            url: 'http://localhost:8080/getUser',
            contentType: 'application/json',
        }).done(function (user) {
            $('#firstName').html(user.firstName);
            $('#lastName').html(user.lastName);
        }).fail(function (fail) {
        })
    }

    TicketInfo();

    function resetSeat() {
        let trToRemove = $('.seat');
        trToRemove.each(function () {
                $(this).remove()
        })
    }

    function getSeats() {
        $.get({
            url: 'http://localhost:8080/seats',
            contentType: 'application/json',
        }).done(function (seatList) {
            seatList.forEach(function (elem) {
                const newOption = $("<option>", {
                    class: "seat",
                    text: elem
                });
                $('#SpotNumber').append(newOption)
            });
        });
    }

    getSeats();

    $('#spotSelect').submit(function (p) {

        p.preventDefault();
        let seat = $(':selected').val();
        $.post({
            url: 'http://localhost:8080/TicketPurchase',
            contentType: 'application/json',
            data: JSON.stringify(seat)
        }).done(function () {
            resetSeat();
            getSeats();
            resetComment();
            const comment1 = $("<p>", {
                id: "comm1",
                class: "comment",
                text: "selected seat was booked for you"
            });
            $('#spotSelect').append(comment1)
        }).fail(function () {
            resetComment();
            const comment2 = $("<p>", {
                id: "comm2",
                class: "comment",
                text: "selected seat was NOT booked for you"
            });
            $('#spotSelect').append(comment2)
        })
    });
});