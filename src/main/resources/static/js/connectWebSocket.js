var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = 'empty';
}

function connect() {
    var socket = new SockJS('http://localhost:8080/importadorPedidos/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            console.log("succesfull suscribed to /topic/messages");
            showMessageOutput(JSON.parse(messageOutput.body));
        });




    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    var from = document.getElementById('from').value;
    var text = document.getElementById('text').value;
    var message = JSON.stringify({ 'name': from, 'text': text });

    stompClient.send("/app/status", {},
        message)

    console.log("enviando mensaje " + message);

}

function showMessageOutput(messageOutput) {
    console.log(messageOutput.from);
    var response = document.getElementById('response');
    var p = document.createElement('p');
    //p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(messageOutput.name + ": " +
        messageOutput.text + " (" + messageOutput.time + ")"));
    response.appendChild(p);
}