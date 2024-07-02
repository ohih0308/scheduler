function getMessageByResponseCode(responseCode) {
    return $.ajax({
        url: '/get-message',
        type: 'POST',
        data: JSON.stringify(responseCode),
        contentType: 'application/json'
    });
}