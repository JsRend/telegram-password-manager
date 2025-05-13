<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">

    <!-- <meta name="viewport"
              content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/> -->
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="MobileOptimized" content="176"/>
    <meta name="HandheldFriendly" content="True"/>
    <meta name="robots" content="noindex,nofollow"/>

    <script src="https://telegram.org/js/telegram-web-app.js?57"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Меню</title>
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
        .menu-section {
            margin: 1rem 0;
        }
        .menu-card {
            display: flex;
            align-items: center;
            padding: 1rem;
            margin: 0.5rem 1rem;
            border-radius: 10px;
            background-color: #2c2c2e;
            cursor: pointer;
        }
        .menu-card i {
            font-size: 1.5rem;
            margin-right: 1rem;
            width: 24px;
        }
        .menu-label {
            flex-grow: 1;
        }
        .menu-value {
            color: gray;
        }
        .accent-btn {
            margin: 1rem;
            padding: 1rem;
            border-radius: 10px;
            background: linear-gradient(90deg, #4d68ff, #aa4ef5);
            color: white;
            text-align: center;
            font-weight: bold;
        }

        a {
          text-decoration: none;
          color: white;
        }

    </style>
    <script>
        Telegram.WebApp.ready();
        Telegram.WebApp.requestFullscreen();

    </script>
    <script src="https://kit.fontawesome.com/19470bdeb5.js" crossorigin="anonymous"></script>
</head>
<body>

<div class="header">Password Manager</div>

<a href="${pageContext.request.contextPath}/password-generate">
    <div class="accent-btn">Создать пароль</div>
</a>

<div class="menu-section">

    <a href="${pageContext.request.contextPath}/password-storage">
        <div class="menu-card">
            <i class="fas fa-key" style="color: #FFD43B;"></i>
            <div class="menu-label">Хранилище паролей</div>
            <div class="menu-value">${keysCount}</div>
        </div>
    </a>

    <a href="${pageContext.request.contextPath}/change-code-password">
        <div class="menu-card">
            <i class="fas fa-lock" style="color: #f47171;"></i>
            <div class="menu-label">Именить код-пароль</div>
        </div>
    </a>

</div>

<div class="menu-section">
    <a href="https://github.com/JsRend" target="_blank">
        <div class="menu-card">
            <i class="fas fa-question-circle" style="color: #968888;"></i>
            <div class="menu-label">FAQ</div>
        </div>
    </a>
    <a href="https://github.com/JsRend" target="_blank">
        <div class="menu-card">
            <i class="fas fa-comments" style="color: #74C0FC;"></i>
            <div class="menu-label">Поддержка</div>
        </div>
    </a>
</div>

</body>
</html>