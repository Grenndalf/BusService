$(function () {

    const basicURL = "http://localhost:8080";
    const buyTicket ="/buyTicket/";
    const getRailways ="/getRailways";
    const getConnections ="/getConnections";
    function resetTr() {
        let trToRemove = $('#results tr').siblings('tr');
        trToRemove.each(function () {
            if ($(this).attr('id') !== "header") {
                $(this).remove()
            }
        })
    };

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

    function ajaxDropdownList() {
        $.get({
            url: basicURL.concat(getRailways),
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
        $.get({
            url: basicURL.concat(getRailways),
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

    $('#TravelDate').datepicker({
        minDate: 0,
        maxDate: 100,
        dateFormat: 'dd/mm/yy'
    }).datepicker("setDate", new Date());

    ajaxDropdownList2();

    ajaxDropdownList();

    $('#DestinationPointList').selectmenu({ width : 400});

    $('#StartPointList').selectmenu({ width : 400});

    $('#sendMe').submit(function (e) {
        e.preventDefault();

        let StartPoint = getSelectedStartPoint();
        let endPoint = getSelectedEndPoint();

        $.post({
            url:basicURL.concat(getConnections),
            contentType:'application/json',
            data:JSON.stringify({
                "startPoint":{"city":getCity(StartPoint)
                    ,"railwayAddress":getRailwayAddress(StartPoint)},
                "endPoint":{"city":getCity(endPoint),
                    "railwayAddress":getRailwayAddress(endPoint)},
                "travelDate":getVal($('#TravelDate'))
            })
        }).done(function (resultList) {
             resetTr();
            resultList.forEach(function (index) {
                const row = $("<tr>", {
                        class: "result",
                    });
                const column1 = $("<td>",{
                        class: "departure",
                    html: "<b>" + index.startPointCity + "</b>" + ", " + index.startPointRailwayAddress
                    });
                const column2 = $("<td>",{
                        class: "arrivalTo",
                    html: "<b>" + index.endPointCity + "</b>" + ", " + index.endpointRailwayAddress
                });
                const column3 = $("<td>",{
                    class: "travelDay",
                    text: index.travelDate
                });
                const column4 = $("<td>", {
                    class: "travelTime",
                    text: index.departureTime.substring(0, 5)
                });
                const column5 = $("<td>",{
                    class: "seatsAvailable",
                        text: index.seatsAvailable
                });
                const column6 = $("<td>", {
                    class: "ticketLink",
                    html: "<a href=" + basicURL.concat(buyTicket) + index.busId + ">buy ticket</a>"
                });
                $('#results')
                    .append(row
                        .append(column1)
                        .append(column2)
                        .append(column3)
                        .append(column4)
                        .append(column5)
                        .append(column6)
                    )
            });

        }).fail(function (fail) {
            console.log(fail)
        })
    })
});
