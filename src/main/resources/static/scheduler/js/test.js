$(document).ready(function(){
    $("#submitBtn").click(function(){
        // 각 필드의 값을 가져와서 자바스크립트 객체에 담기
        var eventData = {
            title: $("#title").val(),
            authorId: $("#authorId").val(),
            location: $("#location").val(),
            description: $("#description").val(),
            participants: $("#participants").val().split(','), // 쉼표로 구분된 문자열을 배열로 변환
            status: $("#status").val(),
            startDate: $("#startDate").val(),
            endDate: $("#endDate").val(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            allDay: $("#allDay").val() === 'true' // 문자열 'true'를 불리언 값으로 변환
        };

        // AJAX 요청을 통해 서버로 데이터 전송
        $.ajax({
            url: '/create-event',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(eventData),
            success: function(response) {
                console.log('Event created successfully:', response);
            },
            error: function(error) {
                console.error('Error creating event:', error);
            }
        });
    });
});