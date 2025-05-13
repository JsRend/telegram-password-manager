<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">

    <meta name="viewport"
              content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="MobileOptimized" content="176"/>
    <meta name="HandheldFriendly" content="True"/>
    <meta name="robots" content="noindex,nofollow"/>
    <title>Введите код</title>
    <script src="https://telegram.org/js/telegram-web-app.js?57"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
            background: #1c1c1e;;
            color: white;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

    </style>

</head>
<body>

    <form action="${pageContext.request.contextPath}/check-user" id="form" method="post">
        <input type="hidden" name="userId" id="userId" required>
    </form>

    <script>

            Telegram.WebApp.ready();
            Telegram.WebApp.requestFullscreen();

            let initData = Telegram.WebApp.initData || '';
            let initDataUnsafe = Telegram.WebApp.initDataUnsafe || {};

            let userId = initDataUnsafe.user.id;

            const form = document.getElementById("form");

            document.getElementById("userId").value = userId;

            form.submit();

    </script>

</body>
</html>
