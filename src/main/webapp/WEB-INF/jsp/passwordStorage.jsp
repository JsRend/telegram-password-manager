<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <title>Хранилище паролей</title>
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
        .storage-item {
            background-color: #2c2c2e;
            color: white;
            margin: 0.5rem 1rem;
            padding: 1rem;
            border-radius: 10px;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
        }
        .domain {
            font-weight: bold;
            margin-bottom: 0.5rem;
        }
        .info-row {
            margin-top: 10px;
            display: flex;
            flex-direction: row;
            justify-content: center;
            font-size: 0.9rem;
            align-items: center;
        }
        .password {
            flex: auto;
        }
        .date {
            text-align: start;
            color: gray;
        }
        .strength {
            flex: 1;
            text-align: right;
            color: gold;
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

    </style>
    <script>
        Telegram.WebApp.ready();
        Telegram.WebApp.requestFullscreen();

        document.addEventListener('DOMContentLoaded', function() {
            document.addEventListener('click', function(event) {
                const item = event.target.closest('.storage-item');
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
</head>
<body>
    <form action="${pageContext.request.contextPath}/password-detail" id="form" method="post">
        <input type="hidden" name="key" id="keyId">
    </form>

    <div class="header">Хранилище паролей</div>

    <c:forEach var="key" items="${userKeys}">
        <div class="storage-item" data-id="${key.id}">

            <div class="domain"><i class="fa-solid fa-globe" style="color: #B197FC;"></i> ${key.keyName} </div>

            <span class="password"><i class="fas fa-key" style="color: #FFD43B;"></i> ${key.userKey} </span>

            <div class="info-row">
                <span class="date"><i class="fa-solid fa-calendar-days" style="color: #63E6BE;"></i> ${key.createDate} </span>
                <span class="strength">${key.difficult}</span>
            </div>

        </div>
    </c:forEach>

    <footer>
        <a href="${pageContext.request.contextPath}/menu">
            <div class="back-btn">Назад</div>
        </a>
    </footer>
</body>
</html>