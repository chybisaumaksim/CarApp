var rootURL = "http://localhost:8080/pt11/cars";

var currentCar;

findAll();

// $('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function () {
    search($('#searchKey').val());
    return false;
});

// // Trigger search when pressing 'Return' on search key input field
// $('#searchKey').keypress(function (e) {
//     if (e.which == 13) {
//         search($('#searchKey').val());
//         e.preventDefault();
//         return false;
//     }
// });

$('#btnAdd').click(function () {
    newCar();
    return false;
});

$('#btnSave').click(function () {
    addCar();
    findAll();
    return false;
});
//
// $('#btnSave').click(function () {
//     if ($('#id').val() == '')
//         addCar();
//     else
//         updateWine();
//     return false;
// });


function btnDelete(id) {
    $('#btnDelete').click(function () {
        deleteCar(id);
        return false;
    });
}

$('#btnUpdate').click(function () {
    updateCar();
    return false;
});

// $('#carList a').live('click', function () {
//     removeCar(a);
// });


function search(colour) {
    findByColour(colour);
    return false;
}

function newCar() {
    currentCar = {};
    renderDetails(currentCar);
}

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
        success: renderList
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
            function (car, textStatus, jqXHR) {
                $('#cars_table_body').append(
                    '<tr>' +
                    '<td>' + car.id + '</td>' +
                    '<td>' + car.model + '</td>' +
                    '<td>' + car.colour + '</td>' +
                    '<td style="width: 100px"><button class=\'btn btn-primary \' onclick="removeCar(' + car.id + ', this)">Remove</button></td>' +
                    '<td style="width: 100px"><button class=\'btn btn-primary \' onclick="updateCar(' + car.id + ')">Update</button></td>' +
                    '</tr>');
                renderList;
            },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addCar: ' + textStatus);
        }
    });
}

function updateCar() {
    console.log('updateCar');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL + '/' + $('#id').val(),
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            $(th).closest('tr').hide();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateCar: ' + textStatus);
        }
    });
}

// function deleteCar(id) {
//     console.log('deleteCar');
//     $.ajax({
//         type: 'DELETE',
//         url: rootURL + '/' + $('#id').val(),
//         url: rootURL + '/' + id,
//         success: function (data, textStatus, jqXHR) {
//         },
//         error: function (jqXHR, textStatus, errorThrown) {
//             alert('deleteCar: ' + textStatus);
//         }
//     });
// }

function deleteCar() {
    console.log('deleteCar');
    $.ajax({
        type: 'DELETE',
        url: rootURL + '/' + $('#id').val(),
        success: function (data, textStatus, jqXHR) {
            alert('Wine deleted successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteWine error');
        }
    });
}

function removeCar(id, th) {
    $.ajax({
        type: 'DELETE',
        url: rootURL + '/' + $('#id').val(),
        success: function (data, textStatus, jqXHR) {
            $(th).closest('tr').hide();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteWine error');
        }
    });
}

// function renderList(cars) {
//     var list = cars == null ? [] : (cars instanceof Array ? cars : [cars]);
//     $('#carList li').remove();
//     $.each(list, function (index, car) {
//         $('#carList').append('<li><a href="#" cars-identity="' + car.id + '">' + car.model + ' ' + car.colour + '<input type="button" value="Delete Row" onclick="SomeDeleteRowFunction(this)"></li></a>');
//         // $('#carList').append('<input type="button" value="Delete Row" onclick="SomeDeleteRowFunction(this)"></li>');
//     });
// }

function renderDetails(car) {
    $('#id').val(car.id);
    $('#model').val(car.model);
    $('#colour').val(car.colour);
}

function formToJSON() {
    var id = $('#id').val();
    return JSON.stringify({
        "id": id == "" ? null : id,
        "model": $('#model').val(),
        "colour": $('#colour').val()
    });
}

function renderList(cars) {
    var tableBody = $('#cars_table_body');
    tableBody.hide();
    tableBody.empty();
    if (cars && cars.length) {
        cars.forEach(function (car) {
            tableBody.append(
                '<tr>' +
                '<td>' + car.id + '</td>' +
                '<td>' + car.model + '</td>' +
                '<td>' + car.colour + '</td>' +
                '<td style="width: 100px"><button class=\'btn btn-primary \' onclick="removeCar(' + car.id + ', this)">Remove</button></td>' +
                '<td style="width: 100px"><button class=\'btn btn-primary \' onclick="updateCar(' + car.id + ', this)">Update</button></td>' +
                '</tr>')
        });
    } else {
        tableBody.append('<tr><td colspan="4">No matching records found</td></tr>')
    }

    tableBody.show();
}