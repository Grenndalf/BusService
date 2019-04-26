$(function () {

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
    });

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
            url:'http://localhost:8080/postForTravels',
            contentType:'application/json',
            data:JSON.stringify({
                "startPoint":{"city":getCity(StartPoint)
                    ,"railwayAddress":getRailwayAddress(StartPoint)},
                "endPoint":{"city":getCity(endPoint),
                    "railwayAddress":getRailwayAddress(endPoint)},
                "travelDate":getVal($('#TravelDate'))
            })
        }).done(function (q) {
            console.log(q)
        })
    })
    $('#test').click(function () {
    })

});