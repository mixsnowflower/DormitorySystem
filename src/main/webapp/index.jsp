<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>宿舍管理系统 - 登录</title>
    <style>
        body { text-align: center; margin-top: 100px; font-family: "微软雅黑", Arial, sans-serif; background-color: #f0f2f5; }
        .login-box {
            width: 350px;
            margin: 0 auto;
            padding: 30px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h2 { color: #333; margin-bottom: 20px; }
        input {
            display: block;
            width: 100%;
            margin: 15px 0;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover { background-color: #0056b3; }
        .link-group { margin-top: 15px; font-size: 14px; }
        a { color: #007bff; text-decoration: none; }
    </style>
</head>
<body>

<div class="login-box">
    <h2>宿舍管理系统</h2>

    <form action="login" method="post">
        <input type="text" name="username" placeholder="请输入用户名 / 学号" required>
        <input type="password" name="password" placeholder="请输入密码" required>
        <button type="submit">登 录</button>
    </form>

    <div class="link-group">
        <a href="register.jsp">注册新账号</a>
    </div>
</div>

<script type="text/javascript">
    // 获取后台传来的 msg
    var msg = "<%= request.getAttribute("msg") == null ? "" : request.getAttribute("msg") %>";
    // 如果 msg 不为空，就弹出警告框
    if (msg != "") {
        alert(msg);
    }
</script>

</body>
</html>