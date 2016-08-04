/**
 * Created by Vladimir_Sentso on 01.08.2016.
 */
var form;
var userName;
var ajaxUrl = 'ajax/books/';
var datatableApi;
var failedNote;
var limit = 5;
var offset = 0;

$(function () {
    userName = $('#loggedUser').text();

})

$(document).on('click', '.editReference', function (e) {
    var t = $(this);
    var attr = t.attr('href');
    updateRow(attr);
});

function showMore() {
    limit += 5;
    updateTable();
}

function makeEditable() {
    form = $('#bookForm');
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

function deleteRow(id) {
    result = confirm("You want delete book with " + id);
    if (result) {
        $.ajax({
            url: ajaxUrl + id,
            type: 'DELETE',
            success: function () {
                updateTable;
                successNoty('Deleted');
            }
        });
    }
}

function revertBook(id) {
    $.ajax({

        url: ajaxUrl + id,
        type: 'PUT',
        success: function () {
            updateTable();
            successNoty('Reverted');
        }
    });
}

function takeBook(id) {
    $.ajax({
        url: ajaxUrl + id + '/' + userName,
        type: 'PUT',
        success: function () {
            updateTable;
            successNoty('Taked');
        }
    });
}

function updateTableByData(data) {
    console.log(data + "");
    datatableApi.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable()
            successNoty('Saved');
        }
    });
}


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
    var ISBN = row.ISBN;
    if (type == 'display') {
        return '<a  onclick="return false" class = "editReference" href="' + row.id + '">' + ISBN + '</a>';
    }
    return data;
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id + ');">Delete</a>';
    }
    return data;
}

function renderTakeBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="takeBook(' + row.id + ');">Take</a>';
    }
    return data;
}

function renderRevertBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="revertBook(' + row.id + ');">Revert</a>';
    }
    return data;
}


var updateTable = function () {
    $.ajax({
        url: ajaxUrl + userName + '/' + limit + '/' + offset,
        success: updateTableByData(msg),
    });
    return false;
}

$(function () {
    datatableApi = $('#bookTable').DataTable({
        "ajax": {
            "url": ajaxUrl + '/' + userName + '/' + limit + '/' + offset,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "ISBN",
                "render": renderEditReference,
                "orderable": false
            },
            {
                "data": "authorName"
            },
            {
                "data": "title"
            },
            {
                "data": "status",
                "render": function (data, type, row) {
                    if (type = "display") {
                        if (row.status === "isFree") {
                            return renderTakeBtn(data, type, row);
                        }
                        else if (row.status === "usedLoggedUser") {
                            return renderRevertBtn(data, type, row)
                        } else {
                            return row.status;
                        }
                    }
                    return data;
                }
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDeleteBtn
            },

        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "initComplete": function () {
            makeEditable();
        }
    });
});

