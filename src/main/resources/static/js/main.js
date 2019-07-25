var rootURL = "http://localhost:8080/pt11/cars";

$(document).ready(function () {
    findAll();
    $('#btnUpdate').hide();
});

$('#btnSearch').click(function () {
    search($('#searchKey').val());
    return false;
});

$('#btnSave').click(function () {
    addCar();
    findAll();
    return false;
});

$('#btnUpdate').click(function () {
    updateCar($('#id').val)
    findAll();
    return false;
});

$('#carList').on('click', 'a', function () {
    console.log('inside carlist');
    findById($(this).data('identity'));
    return false;
});

function search(colour) {
    findByColour(colour);
}

function findAll() {
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: carList
    });
}

function findByColour(colour) {
    $.ajax({
        type: 'GET',
        url: rootURL + '/byColour/' + colour,
        dataType: "json",
        success: carList,
        error: function () {
            findAll();
        }
    });
}

function addCar() {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL,
        dataType: "json",
        data: formToJSON(),
        success:
            function (car) {
                $('#cars_table_body').append(
                    '<tr>' +
                    '<td>' + car.id + ' </td>' +
                    '<td style="width: 600px"><a href="#" data-identity="' + car.id + '">' + car.model + '</td>' +
                    '<td style="width: 600px"><a href="#" data-identity="' + car.id + '">' + car.colour + '</td>' +
                    '<td style="width: 100px"><button class=\'btn btn-danger \' onclick="removeCar(' + car.id + ', this)">Remove</button></td>' +
                    '</tr>');
                $('#btnUpdate').hide();
                renderDetailsEmpty();
            },
        error: function () {
            Swal.fire({
                type: 'error',
                title: 'Oops...',
                text: 'Something went wrong!',
            })
        }
    });
}

function updateCar(id) {
    const row = "row";
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL + '/' + $('#id').val(),
        dataType: "json",
        data: updateFormToJSON(),
        success: function (car) {
            $('#' + row + car.id).replaceWith(
                $('<tr id=' + row + car.id + '/>').append(
                    '<td>' + car.id + ' </td>' +
                    '<td style="width: 600px"><a href="#" data-identity="' + car.id + '">' + car.model + '</td>' +
                    '<td style="width: 600px"><a href="#" data-identity="' + car.id + '">' + car.colour + '</td>' +
                    '<td style="width: 100px"><button class=\'btn btn-danger \' onclick="removeCar(' + car.id + ', this)">Remove</button></td>' +
                    '</tr>'),
            )
        },
        error: function () {
            Swal.fire({
                type: 'error',
                title: 'Oops...',
                text: 'Something went wrong!',
            })
        }
    });
}

function findById(id) {
    console.log('findById: ' + id);
    $.ajax({
        type: 'GET',
        url: rootURL + '/' + id,
        dataType: "json",
        success: function (car) {
            console.log('findById success: ' + car.model);
            renderDetails(car);
            $('#btnUpdate').show();
        }
    });
}

function renderDetails(car) {
    $('#id').val(car.id);
    $('#model').val(car.model);
    $('#colour').val(car.colour);
}

function renderDetailsEmpty() {
    $('#id').val("");
    $('#model').val("");
    $('#colour').val("RED");
}

function removeCar(id, th) {
    $.ajax({
        type: 'DELETE',
        url: rootURL + '/' + id,
        success: function () {
            $('#btnUpdate').hide();
            $(th).closest('tr').hide();
            renderDetailsEmpty()
        },
        error: function () {
            Swal.fire({
                type: 'error',
                title: 'Oops...',
                text: 'Something went wrong!',
            })
        }
    });
}

function formToJSON(id) {
    return JSON.stringify({
        "id": id == "" ? null : id,
        "model": $('#model').val(),
        "colour": $('#colour').val()
    });
}

function updateFormToJSON(id) {
    return JSON.stringify({
        "id": $('#id').val(),
        "model": $('#model').val(),
        "colour": $('#colour').val()
    });
}

function carList(cars) {
    var tableBody = $('#cars_table_body');
    tableBody.hide();
    tableBody.empty();
    const row = "row";
    if (cars && cars.length) {
        cars.forEach(function (car) {
            tableBody.append(
                '<tr id="' + row + car.id + '">' +
                '<td>' + car.id + '</td>' +
                '<td style="width: 600px"><a href="#" data-identity="' + car.id + '">' + car.model + '</td>' +
                '<td style="width: 600px"><a href="#" data-identity="' + car.id + '">' + car.colour + '</td>' +
                '<td style="width: 100px"><button class=\'btn btn-danger \' onclick="removeCar(' + car.id + ', this)">Remove</button></td>' +
                '</tr>')
        });
    } else {
        tableBody.append('<tr><td colspan="4" align="center">No matching cars found</td></tr>')
    }
    tableBody.show();
}