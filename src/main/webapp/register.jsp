<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>学生注册</title>
  <style>
    body { text-align: center; margin-top: 100px; font-family: Arial, sans-serif; }
    .box { width: 300px; margin: 0 auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
    input { margin: 10px 0; padding: 5px; width: 90%; }
    button { padding: 5px 20px; background-color: #28a745; color: white; border: none; cursor: pointer; }
    a { font-size: 12px; color: #007bff; text-decoration: none; }
    .error { color: red; font-size: 14px; }
  </style>
</head>
<body>

<div class="box">
  <h2>新同学注册</h2>
  <form action="register" method="post">
    <input type="text" name="username" placeholder="请输入学号/用户名" required><br>
    <input type="password" name="password" placeholder="设置登录密码" required><br>
    <button type="submit">立即注册</button>
  </form>

  <br>
  <a href="index.jsp">已有账号？去登录</a>
</div>

</body>
</html>