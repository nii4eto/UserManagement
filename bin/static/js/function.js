$(document).ready(function() {
	findAllUsers();
	createUser();
	redirectToHome();
});

function findAllUsers() {
	var table = $('#usersTable')
			.DataTable(
					{
						"sAjaxSource" : "/users",
						"sAjaxDataProp" : "",
						"order" : [ [ 0, "asc" ] ],
						"rowId" : "id",
						"aoColumns" : [
								{
									"mData" : "id"
								},
								{
									"mData" : "firstName"
								},
								{
									"mData" : "lastName"
								},
								{
									"mData" : "email"
								},
								{
									"mData" : "dateOfBirth"
								},
								{
									"defaultContent" : "<button type=\"button\" class=\"btn btn-default btn-xs\" aria-label=\"Edit\">"
											+ "<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></button>"
											+ "<button type=\"submit\" onclick=\"deleteAccount(this);\" class=\"btn btn-default btn-xs deleteBtn\" aria-label=\"Delete\">"
											+ "<span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></button>"
								} ]
					});

	deleteAccount();
}

function createUser() {
	$('#createUser').click(function(e) {
		e.preventDefault();

		var array = $('#createUserForm').serializeArray();
		var myJsonString = JSON.stringify(array);
		$.ajax({
			type : "POST",
			url : "/saveUser",
			contentType : "application/json; charset=utf-8",
			data : getFormData(array),
			success : function(result) {
				console.debug(result);
			},
			error : function(e) {
				console.debug(e);
			}
		});
	});
}

function getFormData(array) {
	var indexed_array = {};

	$.map(array, function(n, i) {
		indexed_array[n['name']] = n['value'];
	});

	return JSON.stringify(indexed_array);
}

function redirectToHome() {
	$('#cancel').click(function(e) {
		$(location).attr('pathname', '/index.html')
	});
}

function deleteAccount(btn) {
	var id = $(btn).closest("tr").attr("id");
	if(id == null) {
		return;
	}
	
	$.ajax({
	    url: "/users/" + id ,
	    type: 'DELETE',
	    success: function(result) {
			location.reload();
		}
	});

}