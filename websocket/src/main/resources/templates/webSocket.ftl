<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>轻尘客户端</title>

    <script type="text/javascript">
        function WebSocketTest() {

            var socket;
            if (typeof (WebSocket) == "undefined") {
                console.log("您的浏览器不支持WebSocket");
            } else {
                console.log("您的浏览器支持WebSocket");
                //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
                socket = new WebSocket("ws://localhost/websocket/${id}");
                //打开事件
                socket.onopen = function () {
                    console.log("Socket 已打开");
                };
                //获得消息事件
                socket.onmessage = function (msg) {
                    console.log(msg.data);
                };
                //关闭事件
                socket.onclose = function () {
                    console.log("Socket已关闭");
                };
                //发生了错误事件
                socket.onerror = function () {
                    alert("Socket发生了错误");
                }
            }
        }
    </script>

</head>
<body>

<div id="sse">
    <a href="javascript:WebSocketTest()">按F12，然后点击我，查看效果</a>
</div>

</body>
</html>
