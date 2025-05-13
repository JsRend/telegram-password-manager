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
                    background: #1c1c1e;
                    color: white;
                    height: 100vh;
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    justify-content: center;
                }

        .lock-icon {
            font-size: 48px;
            margin-bottom: 20px;
        }

        .title {
            font-size: 18px;
            margin-bottom: 20px;
        }

        .dots {
            display: flex;
            gap: 10px;
            margin-bottom: 30px;
        }

        .dot {
            width: 12px;
            height: 12px;
            border-radius: 50%;
            background-color: rgba(255, 255, 255, 0.3);
        }

        .dot.filled {
            background-color: white;
        }

        .numpad {
            display: grid;
            grid-template-columns: repeat(3, 80px);
            gap: 15px;
        }

        .key {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background-color: rgba(255, 255, 255, 0.1);
            color: white;
            font-size: 24px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            cursor: pointer;
            user-select: none;
            transition: background-color 0.2s;
        }

        .key:hover {
            background-color: rgba(255, 255, 255, 0.2);
        }

        .footer {
            margin-top: 20px;
            font-size: 14px;
            opacity: 0.8;
            cursor: pointer;
        }

        .fingerprint {
            font-size: 32px;
            margin-top: 10px;
        }

        a {
            text-decoration: none;
        }

    </style>
    <script>

        Telegram.WebApp.ready();

        let initData = Telegram.WebApp.initData || '';
        let initDataUnsafe = Telegram.WebApp.initDataUnsafe || {};
        Telegram.WebApp.requestFullscreen();

        let userId = initDataUnsafe.user.id;

        let input = [];

        if(${param.error != code}) {
            alert('Не правильный код-пароль!');
        }

        function press(num) {
            if (input.length < 5) {
                input.push(num);
                updateDots();
            }
            if (input.length === 5) {

                const form = document.getElementById("form");

                document.getElementById("userId").value = userId;
                document.getElementById("codeInput").value = input.join('');

                form.submit();
            }
        }

        function erase() {
            input.pop();
            updateDots();
        }

        function updateDots() {
            const dots = document.querySelectorAll('.dot');
            dots.forEach((dot, index) => {
                dot.classList.toggle('filled', index < input.length);
            });
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
    <form action="${pageContext.request.contextPath}/login" id="form" method="post">
        <input type="hidden" name="code" id="codeInput" required>
        <input type="hidden" name="userId" id="userId" required>
    </form>


    <div align="center" class="lock-icon">
        <i class="fas fa-lock" style="color: #FFD43B;"></i>
        <div class="title">Введите код-пароль</div>
    </div>


    <div class="dots">
        <div class="dot"></div>
        <div class="dot"></div>
        <div class="dot"></div>
        <div class="dot"></div>
        <div class="dot"></div>
    </div>

    <a href="javascript:Telegram.WebApp.HapticFeedback.impactOccurred('medium');">
        <div class="numpad">

            <div class="key" onclick="press(1)">1</div>
            <div class="key" onclick="press(2)">2<br><small>ABC</small></div>
            <div class="key" onclick="press(3)">3<br><small>DEF</small></div>

            <div class="key" onclick="press(4)">4<br><small>GHI</small></div>
            <div class="key" onclick="press(5)">5<br><small>JKL</small></div>
            <div class="key" onclick="press(6)">6<br><small>MNO</small></div>

            <div class="key" onclick="press(7)">7<br><small>PQRS</small></div>
            <div class="key" onclick="press(8)">8<br><small>TUV</small></div>
            <div class="key" onclick="press(9)">9<br><small>WXYZ</small></div>

            <div class="key"><i class="fa-regular fa-face-smile"></i></div>
            <div class="key" onclick="press(0)">0</div>
            <div class="key" onclick="erase()">⌫</div>
        </div>
    </a>

</body>
</html>
