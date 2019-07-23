var rootURL = "http://localhost:8080/pt11/cars";

var currentCar;

$(document).ready(function () {
    findAll();
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

function search(colour) {
    findByColour(colour);
    return false;
}

// function newCar() {
//     currentCar = {};
//     renderDetails(currentCar);
// }

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderList
    });
}

function findByColour(colour) {
    console.log('findByColour: ' + colour);
    $.ajax({
        type: 'GET',
        url: rootURL + '/byColour/' + colour,
        dataType: "json",
        success: renderList,
        error: function () {
            findAll();
        }
    });
}

function addCar() {
    console.log('addCar');
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
                    '<td contenteditable="true">' + car.model + '</td>' +
                    '<td contenteditable="true">' + car.colour + '</td>' +
                    '<td style="width: 100px"><button class=\'btn btn-danger \' onclick="removeCar(' + car.id + ', this)">Remove</button></td>' +
                    '<td style="width: 100px"><button class=\'btn btn-primary \' onclick="updateCar(' + car.id + ', this)">Update</button></td>' +
                    '</tr>');
            },
        error: function (jqXHR, textStatus) {
            alert('addCar: ' + textStatus);
        }
    });
}

function updateCar(id, th) {
    console.log('updateCar');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL + '/' + id,
        dataType: "json",
        data: formToJSON(id),
        success: function () {
            findAll();
        },
        error: function (jqXHR, textStatus,) {
            alert('updateCar: ' + textStatus);
        }
    });
}

// function updateCar2(car) {
//     console.log('updateCar');
//     $.ajax({
//         type: 'PUT',
//         contentType: 'application/json',
//         url: rootURL + '/' + car.id,
//         dataType: "json",
//         data: renderDetails(),
//         data: formToJSON2(car),
//         success: function () {
//             findAll();
//         },
//         error: function (jqXHR, textStatus,) {
//             alert('updateCar: ' + textStatus);
//         }
//     });
// }

function removeCar(id, th) {
    console.log('removeCar');
    $.ajax({
        type: 'DELETE',
        url: rootURL + '/' + id,
        success: function () {
            $(th).closest('tr').hide();
        },
        error: function (jqXHR, textStatus,) {
            alert('deleteCar: ' + textStatus);
        }
    });
}

// function renderDetails(car) {
//     $('#id').val(car.id);
//     $('#model').val(car.model);
//     $('#colour').val(car.colour);
// }

function formToJSON(id) {
    return JSON.stringify({
        "id": id == "" ? null : id,
        "model": $('#model').val(),
        "colour": $('#colour').val()
    });
}

// function formToJSON2(id, model, colour) {
//     return JSON.stringify({
//         "id": id == "" ? null : id,
//         "model": model,
//         "colour": colour
//     });
// }

function renderList(cars) {
    var tableBody = $('#cars_table_body');
    tableBody.hide();
    tableBody.empty();
    if (cars && cars.length) {
        cars.forEach(function (car) {
            tableBody.append(
                '<tr>' +
                '<td>' + car.id + '</td>' +
                '<td contenteditable="true">' + car.model + '</td>' +
                '<td contenteditable="true">' + car.colour + '</td>' +
                '<td style="width: 100px"><button class=\'btn btn-danger \' onclick="removeCar(' + car.id + ', this)">Remove</button></td>' +
                '<td style="width: 100px"><button class=\'btn btn-primary \' onclick="updateCar(' + car.id + ', this)">Update</button></td>' +
                // '<td style="width: 100px"><button class=\'btn btn-primary \' onclick="updateCar2('+car+')">Update</button></td>' +
                '</tr>')
        });
    } else {
        tableBody.append('<tr><td colspan="4" align="center">No matching records found</td></tr>')
    }
    tableBody.show();
}