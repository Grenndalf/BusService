$(document).ready(function () {

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
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/getRailways',
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
            url: 'http://localhost:8080/getRailways',
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

    ajaxDropdownList2()

    ajaxDropdownList()

    $('#createConnection').submit(function (e) {
        e.preventDefault()

        let StartPoint = getSelectedStartPoint();
        let endPoint = getSelectedEndPoint();
        let departureTime = getVal($('#departureTime'))
        let numberOfSeats = getVal($('#numberOfSeats'))
        $.post({
            url: 'http://localhost:8080/newConnection',
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
            console.log(e)
        }).fail(function (f) {
            console.log(f)
        })
    })
})