<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="MobileOptimized" content="176"/>
    <meta name="HandheldFriendly" content="True"/>
    <meta name="robots" content="noindex,nofollow"/>
    <title>Подробнее</title>
    <script src="https://telegram.org/js/telegram-web-app.js?57"></script>
    <style>
        body {
            margin: 0;
            background-color: #1c1c1e;
            color: white;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
        }
        .header {
            padding: 2rem;
            background-color: #2c2c2e;
            text-align: center;
            font-weight: bold;
            font-size: 1.2rem;

        }
        a {
            text-decoration: none;
            color: white;
        }

        .back-btn {
            margin: 1rem;
            padding: 1rem;
            border-radius: 10px;
            background: linear-gradient(90deg, #4d68ff, #aa4ef5);
            color: white;
            text-align: center;
            font-weight: bold;
        }


        .delete-btn {
            margin: 1rem;
            padding: 1rem;
            border-radius: 10px;
            background: linear-gradient(90deg, #800020, #C04000);
            text-align: center;
            font-weight: bold;
        }

        .container {
          padding: 20px;
        }

        .input {
          display: flex;
          padding: 14px;
          border-radius: 12px;
          border: none;
          background-color: #2c2c2e;
          color: white;
          text-align: center;
          font-size: 16px;
          margin-bottom: 20px;
        }

        .display {
          background-color: #2c2c2e;
          padding: 16px;
          font-size: 22px;
          text-align: center;
          border-radius: 12px;
          margin-top: 20px;
          margin-bottom: 20px;
        }

    </style>
    <script>

        Telegram.WebApp.ready();

        let initData = Telegram.WebApp.initData || '';
        let initDataUnsafe = Telegram.WebApp.initDataUnsafe || {};
        Telegram.WebApp.requestFullscreen();

        document.addEventListener('DOMContentLoaded', function() {
            document.addEventListener('click', function(event) {
                const item = event.target.closest('.delete-btn');
                if (item) {
                    const key = item.getAttribute('data-id');
                    const form = document.getElementById("form");

                    document.getElementById("keyId").value = key;
                    form.submit();
                }
            });
        });

    </script>
    <script src="https://kit.fontawesome.com/19470bdeb5.js" crossorigin="anonymous"></script>
    <script type="application/javascript">
        const DemoApp = {
            showAlert(message) {
                Telegram.WebApp.showAlert(message);
            }
        }
    </script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/password-delete" id="form" method="post">
        <input type="hidden" name="key" id="keyId">
    </form>

    <div class="header">Подробнее</div>
    <div class="display">${keyName}</div>
    <div class="display">${key}</div>

    <div class="delete-btn" data-id="${keyId}">Удалить</div>
    <footer>
        <a href="${pageContext.request.contextPath}/menu">
            <div class="back-btn">Назад</div>
        </a>
    </footer>
</body>
</html>