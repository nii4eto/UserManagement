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
									"defaultContent" : "<button type=\"submit\" onclick=\"findUserForEdit(this);\" class=\"btn btn-default btn-xs\" aria-label=\"Edit\">"
											+ "<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></button>"
											+ "<button type=\"submit\" onclick=\"deleteAccount(this);\" class=\"btn btn-default btn-xs deleteBtn\" aria-label=\"Delete\">"
											+ "<span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></button>"
								} ]
					});
}

function validateForm(id) {
	var form = document.getElementById(id);
	form.validationMessage = "All fields are required!";
	if (form.checkValidity() == false) {
		$('#errorMessage').show();
        document.getElementById("errorMessage").innerHTML = form.validationMessage;
        return false;
    }
	
	return true;
}

function saveUser() {
	if(!validateForm("createUserForm")) {
		return;
	}
	
	
	var array = $('#createUserForm').serializeArray();
	var myJsonString = JSON.stringify(array);
	$.ajax({
		type : "POST",
		url : "/saveUser",
		contentType : "application/json; charset=utf-8",
		data : getFormData(array),
		success : function(result) {
			console.debug(result);
			redirectToHome();
		},
		error : function(e) {
			console.debug("dasd");
			console.debug(e);
		}
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
	$(location).attr('pathname', '/index.html');
}

function deleteAccount(btn) {
	var id = $(btn).closest("tr").attr("id");
	if (id == null) {
		return;
	}

	$.ajax({
		url : "/users/" + id,
		type : 'DELETE',
		success : function(result) {
			location.reload();
		}
	});
}

function findUserForEdit(btn) {
	var id = $(btn).closest("tr").attr("id");
	if (id == null) {
		return;
	}

	window.location.href = "http://localhost:8181/editUser.html?" + id;
}

function populateUserData(id) {
	$.ajax({
		url : "/users/" + id,
		type : "GET",
		success : function(result) {

			populate('#editUserForm', result);

		},
		error : function(e) {
			console.debug(e);
		}
	});
}

function updateUser() {
	var array = $('#editUserForm').serializeArray();
	var myJsonString = JSON.stringify(array);
	$.ajax({
		type : "PUT",
		url : "/editUser",
		contentType : "application/json; charset=utf-8",
		data : getFormData(array),
		success : function(result) {
			console.debug(result);
			redirectToHome();
		},
		error : function(e) {
			console.debug(e);
		}
	});
}

function populate(frm, data) {
	$.each(data, function(key, value) {
		$('[name=' + key + ']', frm).val(value);
	});
}