document.addEventListener('contextmenu', function(event) {
    event.preventDefault();
});

document.addEventListener('selectstart', function(event) {
    event.preventDefault();
});

document.addEventListener('dragstart', function(event) {
    event.preventDefault();
});

function getMessageByResponseCode(responseCode) {
    return $.ajax({
        url: '/get-message',
        type: 'POST',
        data: JSON.stringify(responseCode),
        contentType: 'application/json'
    });
}