### login_page.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Sign Up Form by Colorlib</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="main">

    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">

                <div class="signup-form">
                    <h2 class="form-title">로그인</h2>
                    <form method="POST" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="username" id="username" placeholder="nickname"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" id="password" placeholder="Password"/>
                        </div>

                        <div class="form-group form-button">
                            <input type="button" name="signup" id="signup" class="form-submit" onclick="login()" value="로그인"/>

                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure><img src="images/signin-image.jpg" alt="sing up image"></figure>
                    <a href="/join" class="signup-image-link">계정이 없으신가요?</a>
                </div>

            </div>
        </div>
    </section>
</div>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
<script>
    function login(){
        var data = {
            nickname : $('#username').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/login',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function(data,status,xhr){
            let jwtToken = xhr.getResponseHeader('Authorization');
            localStorage.setItem("Authorization", jwtToken);
            location.href = "/home";
        }).fail(function (error) {
            alert("존재하지 않는 회원입니다.");
        });
    }
</script>
</html>
```
