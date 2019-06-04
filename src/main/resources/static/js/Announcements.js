$(function () {
    const basicURL = "http://localhost:8080";
    const getAnnoucements = "/getAllAnnoucements";
    $.get({
        url: basicURL.concat(getAnnoucements),
    }).done(function (resultList) {
        //reset pól
        resultList.forEach(function (result) {
            const title = $("<div>", {
                class: "title",
                text: result.title
            });
            const date = $("<div>", {
                class: "date",
                text: result.createdAt
            });
            const content = $("<div>", {
                class: "content",
                html: result.content
            })
            const lineSeparator = $("<div>", {
                class: "line2",
            });
            $('.announContainer').append(title).append(date).append(lineSeparator).append(content)
        })
    })
});