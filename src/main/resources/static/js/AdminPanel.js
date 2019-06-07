$(document).ready(function () {

    const basicURL = "http://localhost:8080";
    const railways = "/getRailways";
    const newConnection = "/newConnection";



    function getSelectedEndPoint() {
        return $(':selected')[1];
    }

    function getVal(selector) {
        return selector.val();
    }

    function getSelectedStartPoint() {
        return $(':selected')[0];
    }

    function getCity(point) {
        return point.innerText.split(", ")[0];
    }

    function getRailwayAddress(point) {
        return point.innerText.split(", ")[1];
    }

    $('#TravelDate').datepicker({
        minDate: 0,
        maxDate: 100,
        dateFormat: 'dd/mm/yy'
    }).datepicker("setDate", new Date());

    function ajaxDropdownList() {
        $.get({
            url: basicURL.concat(railways),
            contentType: 'application/json',
        }).done(function (jsons) {
            jsons.forEach(function (json) {
                const newOpt = $("<option>", {
                        class: "List",
                        text: json.city + ", " + json.railwayAddress
                    })
                ;
                $('#StartPointList').append(newOpt)
            });
        });
    }

    function ajaxDropdownList2() {
        $.ajax({
            type: 'GET',
            url: basicURL.concat(railways),
            contentType: 'application/json',
        }).done(function (jsons) {
            jsons.forEach(function (json) {
                const newOpt = $("<option>", {
                    class: "List",
                    text: json.city + ", " + json.railwayAddress
                });
                $('#DestinationPointList').append(newOpt)
            });
        });
    }

    function resetComment() {
        let trToRemove = $('.comment');
        trToRemove.each(function () {
            $(this).remove()
        })
    }

    ajaxDropdownList2();

    ajaxDropdownList();

    $('#createConnection').submit(function (e) {
        e.preventDefault();

        let StartPoint = getSelectedStartPoint();
        let endPoint = getSelectedEndPoint();
        let departureTime = getVal($('#departureTime'));
        let numberOfSeats = getVal($('#numberOfSeats'));
        $.post({
            url: basicURL.concat(newConnection),
            contentType: 'application/json',
            data: JSON.stringify({
                "startPoint": {
                    "city": getCity(StartPoint)
                    , "railwayAddress": getRailwayAddress(StartPoint)
                },
                "endPoint": {
                    "city": getCity(endPoint),
                    "railwayAddress": getRailwayAddress(endPoint)
                },
                "travelDate": getVal($('#TravelDate')),
                "departureTime": departureTime,
                "maxNumberOfSeatsAvailable": numberOfSeats
            })
        }).done(function (e) {
            resetComment();
            const comment1 = $("<p>", {
                id: "comm1",
                class: "comment",
                text: e
            });
            comment1.insertAfter($('#createConnection'))
        }).fail(function (fail) {
            console.log(fail);
            resetComment();
            const comment2 = $("<p>", {
                id: "comm2",
                class: "comment",
                text: "connection was not saved"
            });
            comment2.insertAfter($('#createConnection'))
        })
    })
});