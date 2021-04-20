$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/greeting-cors"
    }).then(function(data, status, jqxhr) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
       console.log(jqxhr);
    });
    $.ajax({
        url: "http://localhost:8080/greeting-javaconfig"
    }).then(function(data, status, jqxhr) {
       $('.greeting-id2').append(data.id);
       $('.greeting-content2').append(data.content);
       console.log(jqxhr);
    });
});