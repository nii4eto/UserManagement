$(document).ready(function () {
	findAllUsers();
	createUser();

});

function findAllUsers() {
	var table = $('#usersTable').DataTable({
		"sAjaxSource": "/users",
		"sAjaxDataProp": "",
		"order": [[ 0, "asc" ]],
		"aoColumns": [
		      { "mData": "id"},
	          { "mData": "firstName" },
			  { "mData": "lastName" },
			  { "mData": "email" },
			  { "mData": "dateOfBirth" }
		]
 });
}

function createUser() {
	$('#createUser').click(function(e) {
		    e.preventDefault();

		var array = $('#createUserForm').serializeArray();
		var myJsonString = JSON.stringify(array);
		$.ajax({type: "POST",
            url: "/saveUser",
            contentType: "application/json; charset=utf-8",
            data: getFormData(array),
            success:function(result){
            	console.debug(result);
    },
    error: function(e) {
    	console.debug(e);
     }
		});
});
}

function getFormData(array){
    var indexed_array = {};

    $.map(array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return JSON.stringify(indexed_array);
}