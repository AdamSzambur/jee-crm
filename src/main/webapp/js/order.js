document.addEventListener("DOMContentLoaded", function(){

    var clientSelect = document.querySelector('#clientSelect');


    clientSelect.addEventListener('change', function(e) {
        console.log(clientSelect.value);
    });


    var employeeSelect = document.querySelector('#employeeSelect');
    var costOfmanHour= document.querySelector('#costOfmanHour');



    employeeSelect.addEventListener('change', function(e) {

        var test = '${employeeList}';

        console.log(test);
        costOfmanHour.value = '${employeeList}';

    })






});