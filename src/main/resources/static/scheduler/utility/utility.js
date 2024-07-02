function getMessageByResponseCode(responseCode) {
    console.log("Sending request with responseCode: " + responseCode);
    return $.ajax({
        url: '/get-message',
        type: 'POST',
        data: JSON.stringify(responseCode),
        contentType: 'application/json'
    });
}