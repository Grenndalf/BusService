
$(function () {

    function resetTr() {
        let trToRemove = $('#results tr').siblings('tr')
        trToRemove.each(function () {
            if ($(this).attr('id') != "header") {
                $(this).remove()
            }
        })
    };

    function getSelectedEndPoint() {
        return $(':selected')[1];

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
                    })
                ;
                $('#DestinationPointList').append(newOpt)
            });
        });
    }

    function getVal(selector) {
        return selector.val();
    }

    $('#TravelDate').datepicker({
        minDate: 0,
        maxDate: 100,
        dateFormat: 'dd/mm/yy'
    }).datepicker("setDate", new Date());

    ajaxDropdownList2()

    ajaxDropdownList()
    $('#DestinationPointList').selectmenu({ width : 400});

    $('#StartPointList').selectmenu({ width : 400});

    $('#sendMe').submit(function (e) {
        e.preventDefault();

        let StartPoint = getSelectedStartPoint();
        let endPoint = getSelectedEndPoint();

        $.ajax({
            type:'POST',
            url:'http://localhost:8080/getConnections',
            contentType:'application/json',
            data:JSON.stringify({
                "startPoint":{"city":getCity(StartPoint)
                    ,"railwayAddress":getRailwayAddress(StartPoint)},
                "endPoint":{"city":getCity(endPoint),
                    "railwayAddress":getRailwayAddress(endPoint)},
                "travelDate":getVal($('#TravelDate'))
            })
        }).done(function (e) {
             resetTr()
            console.log(e)
            e.forEach(function (f) {
                const row = $("<tr>", {
                        class: "result",
                    });
                const column1 = $("<td>",{
                        class: "departure",
                        text: f.startPoint.city + " " + f.startPoint.railwayAddress
                    });
                const column2 = $("<td>",{
                        class: "arrivalTo",
                        text: f.endPoint.city + " " + f.endPoint.railwayAddress
                    })
                const column3 = $("<td>",{
                        class: "TravelDay",
                        text: f.travelDate
                    })
                const column4 = $("<td>",{
                        class: "TravelDay",
                        text: f.departureTime
                    })
                $('#results').append(row.append(column1).append(column2).append(column3).append(column4))
            });

        }).fail(function (e) {
            console.log(e)
        })
    })

    $('#test').click(function () {
        console.log("hello")
    })

});
