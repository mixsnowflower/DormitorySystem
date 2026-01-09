<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("currentUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html>
<head>
    <title>学生个人中心</title>
    <style>
        body { margin: 0; font-family: sans-serif; background-color: #f4f6f9; }
        .navbar { background-color: #007bff; color: white; padding: 15px; display: flex; justify-content: space-between; align-items: center;}
        .container { width: 80%; margin: 30px auto; display: flex; gap: 20px; }
        .menu-card { background: white; padding: 20px; border-radius: 8px; width: 250px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .menu-card a { display: block; padding: 10px; color: #333; text-decoration: none; border-bottom: 1px solid #eee; }
        .menu-card a:hover { background-color: #f8f9fa; color: #007bff; }
        .main-card { flex: 1; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    </style>
</head>
<body>

<div class="navbar">
    <div style="font-size: 20px; font-weight: bold;">学生服务平台</div>
    <div>
        欢迎同学：${currentUser.username} |
        <a href="logout" style="color: white; text-decoration: underline;">退出</a>
    </div>
</div>

<div class="container">
    <div class="menu-card">
        <h4>功能菜单</h4>
        <a href="#">🏠 个人中心 </a>
        <a href="#">🔧 在线报修 </a>
        <a href="#">📢 查看公告 </a>
    </div>

    <div class="main-card">
        <h3>欢迎回来！</h3>
        <p>这里是你的个人中心，你可以在这里查询宿舍信息、申请报修等。</p>
    </div>
</div>

</body>
</html>