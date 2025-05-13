<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Создание пароля</title>
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

        .container {
          padding: 20px;
        }

        .input {
            width: 100%;
            box-sizing: border-box;
            padding: 14px;
            border-radius: 12px;
            border: none;
            background-color: #2c2c2e;
            color: white;
            text-align: center;
            font-size: 16px;
            margin-bottom: 20px;
        }

        .password-display {
            background-color: #2c2c2e;
            padding: 16px;
            font-size: 22px;
            text-align: center;
            border-radius: 12px;
            margin-bottom: 20px;
        }

        .options {
            background-color: #2c2c2e;
            border-radius: 12px;
            padding: 16px;
            margin-bottom: 20px;
        }

        .option-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 12px 0;
        }

        .length-control {
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .length-btn {
            background-color: #3a3a3c;
            border: none;
            color: white;
            padding: 6px 12px;
            font-size: 18px;
            border-radius: 8px;
            cursor: pointer;
        }

        .length-value {
            font-size: 16px;
            min-width: 24px;
            text-align: center;
        }

        .switch {
            position: relative;
            display: inline-block;
            width: 42px;
            height: 24px;
        }

        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #767577;
            transition: .4s;
            border-radius: 24px;
        }

        .slider:before {
            position: absolute;
            content: "";
            height: 18px;
            width: 18px;
            left: 3px;
            bottom: 3px;
            background-color: white;
            transition: .4s;
            border-radius: 50%;
        }

        input:checked + .slider {
            background-color: #30d158;
        }

        input:checked + .slider:before {
            transform: translateX(18px);
        }

        .buttons {
            display: flex;
            justify-content: space-between;
            gap: 12px;
        }

        .btn {
            flex: 1;
            padding: 14px;
            border-radius: 12px;
            border: none;
            font-weight: bold;
            font-size: 16px;
            cursor: pointer;
        }

        .generate {
            background-color: #0a84ff;
            color: white;
        }

        .save {
            background-color: #30d158;
            color: white;
        }
    </style>
    <script>

        Telegram.WebApp.ready();

        let initData = Telegram.WebApp.initData || '';
        let initDataUnsafe = Telegram.WebApp.initDataUnsafe || {};
        Telegram.WebApp.requestFullscreen();


        let passwordLength = 13;

         function updateLength(action) {

            if (action === '-' && passwordLength > 4) {
                passwordLength--;
            }
            if (action === '+' && passwordLength < 32) {
                passwordLength++;
            }

            const lengthValue = document.getElementById("length-value");
            lengthValue.textContent = passwordLength;
         }

         function generate() {
            const formGenerate = document.getElementById("formGenerate");

            document.getElementById("lengthId").value = passwordLength;


            document.getElementById("useNumbersId").value = document.getElementById("numberSwitch").checked;

            document.getElementById("useSpecialСharId").value = document.getElementById("specialСharSwitch").checked;

            document.getElementById("useUppercaseId").value = document.getElementById("uppercaseSwitch").checked;

            let domainValue = document.getElementById("domainInputId");

            if (!domainValue.value) {
                alert("Не задано поле названия пароля!");
                return;
            } else {
                document.getElementById("domainId").value = domainValue.value;
            }

            formGenerate.submit();
         }

         function save() {
            const formSave = document.getElementById("formSave");

            let userDomain = document.getElementById("domainInputId");

            if(!userDomain.value) {
                alert("Не задано поле названия пароля!");
                return;
            } else {
               document.getElementById("userDomainId").value = userDomain.value;
               document.getElementById("userPasswordId").value = "<c:out value='${password}'/>";
            }

            formSave.submit();
         }



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

    <form action="${pageContext.request.contextPath}/password-generate" id="formGenerate" method="post">
        <input type="hidden" name="length" id="lengthId">
        <input type="hidden" name="useNumbers" id="useNumbersId">
        <input type="hidden" name="useSpecialСhar" id="useSpecialСharId">
        <input type="hidden" name="useUppercase" id="useUppercaseId">
        <input type="hidden" name="domain" id="domainId">
    </form>

    <form action="${pageContext.request.contextPath}/password-save" id="formSave" method="post">
        <input type="hidden" name="userDomain" id="userDomainId">
        <input type="hidden" name="userPassword" id="userPasswordId">
    </form>

<div class="header">Создание пароля</div>

    <div class="container">

        <input type="text" class="input" id="domainInputId" value="${contextDomain}" placeholder="example.com" maxlength="32">

        <div class="password-display">${password}</div>

        <div class="options">
          <div class="option-row">
            <label>Длина:</label>
            <div class="length-control">
              <button class="length-btn" onclick="updateLength('-')">−</button>
              <span class="length-value" id="length-value">13</span>
              <button class="length-btn" onclick="updateLength('+')">+</button>
            </div>
          </div>

          <div class="option-row">
            <label>Вкл. цифры</label>
            <label class="switch"><input type="checkbox" id="numberSwitch" checked><span class="slider"></span></label>
          </div>
          <div class="option-row">
            <label>Спецсимволы</label>
            <label class="switch"><input type="checkbox" id="specialСharSwitch" checked><span class="slider"></span></label>
          </div>
          <div class="option-row">
            <label>Заглавные</label>
            <label class="switch"><input type="checkbox" id="uppercaseSwitch" checked><span class="slider"></span></label>
          </div>
        </div>

        <div class="buttons">
          <button class="btn generate" onclick="generate()">Сгенерировать</button>
          <button class="btn save" onclick="save()">Сохранить пароль</button>
        </div>

    </div>

    <footer>
        <a href="${pageContext.request.contextPath}/menu">
            <div class="back-btn">Назад</div>
        </a>
    </footer>
</body>
</html>