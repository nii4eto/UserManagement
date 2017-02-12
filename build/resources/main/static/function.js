$(document).ready(function () {

    findAllUsers();
    console.log("dasdd");

});

function findAllUsers() {
	$.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (users) {
        	$.each(users, function(i, user) {
                var $tr = $('<tr>').append(
                    $('<td>').text(user.id),
                    $('<td>').text(user.firstName),
                    $('<td>').text(user.lastName),
                    $('<td>').text(user.email),
                    $('<td>').text(user.dateOfBirth)
                ); //.appendTo('#records_table');
                console.log($tr.wrap('<p>').html());
            });

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
}