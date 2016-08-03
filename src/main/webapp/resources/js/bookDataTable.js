/**
 * Created by Vladimir_Sentso on 01.08.2016.
 */
var form;
var userName;
$(function getUserName() {
    userName = $('#loggedUser').text();
})

function makeEditable() {
    form = $('#bookForm');

    form.submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
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
            form.find("input[name='" + key + "']").val(
                key === "dateTime" ? value.replace('T', ' ').substr(0, 16) : value
            );
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
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

function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="updateRow(' + row.id + ');">Edit</a>';
    }
    return data;
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id + ');">Delete</a>';
    }
    return data;
}
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
    return false;
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        return date.replace('T', ' ').substr(0, 16);
                    }
                    return date;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDeleteBtn

            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass(data.exceed ? 'exceeded' : 'normal');
        },
        "initComplete": function () {
            $('#filter').submit(function () {
                updateTable();
                return false;
            });

            var startDate = $('#startDate');
            var endDate = $('#endDate');

            startDate.datetimepicker({
                timepicker: false,
                format: 'Y-m-d',
                lang: 'ru',
                formatDate: 'Y-m-d',
                onShow: function (ct) {
                    this.setOptions({
                        maxDate: endDate.val() ? endDate.val() : false
                    })
                }
            });
            endDate.datetimepicker({
                timepicker: false,
                format: 'Y-m-d',
                lang: 'ru',
                formatDate: 'Y-m-d',
                onShow: function (ct) {
                    this.setOptions({
                        minDate: startDate.val() ? startDate.val() : false
                    })
                }
            });

            $('.time-picker').datetimepicker({
                datepicker: false,
                format: 'H:i',
                lang: 'ru'
            });

            $('#dateTime').datetimepicker({
                format: 'Y-m-d H:i',
                lang: 'ru'
            });

            makeEditable();
        }
    });
});

