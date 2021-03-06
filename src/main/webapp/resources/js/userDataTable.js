/**
 * Created by Vladimir_Sentso on 01.08.2016.
 */
var form;
var userName;
var ajaxUrl = 'ajax/users/';
var datatableApi;

//Устанвливаем имя текущего пользователя
$(function getUserName() {
    userName = $('#loggedUser').text();
});

$(document).on('click', '.editReference', function (e) {
    var t = $(this);
    var id = t.attr('href');
    updateRow(id);
});
//Устанавливаем обработчик формы
function makeEditable() {
    form = $('#userForm');

    form.submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

}

function add() {
    form.find(":input").val("");
    $('#id').val(0);
    $('#editRow').modal();
}

function updateRow(id) {
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

var updateTable = function () {
    $.ajax({
        url: ajaxUrl,
        success: updateTableByData
    });
    return false;
}

function deleteRow(id) {
    result = confirm("Do you want delete user");
    if (result) {
        $.ajax({
            url: ajaxUrl + id,
            type: 'DELETE',
            success: function () {
                updateTable();
                successNoty('Deleted');
            }
        });
    }
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });

}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>" + errorInfo.cause + "<br>" + errorInfo.detail,
        type: 'error',
        layout: 'bottomRight'
    });
}

function renderEditReference(data, type, row) {
    var userName = row.userName;
    if (type == 'display') {
        return '<a onclick="return false" class = "editReference" href="' + row.id + '">' + userName + '</a>';
    }
    return data;
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id + ');">Delete</a>';
    }
    return data;
}


jQuery(function () {
    datatableApi = jQuery('#userTable').DataTable(
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "columns": [
                {
                    "data": "userName",
                    "render": renderEditReference
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                },

            ],
            "order": [
                [
                    1,
                    "asc"
                ]
            ],
            "initComplete": makeEditable
        });
});



