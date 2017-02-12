$(document).ready(function () {
	findAllUsers();

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