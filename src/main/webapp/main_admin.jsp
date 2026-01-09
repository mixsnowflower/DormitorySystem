<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 权限验证：如果没有登录，直接踢回登录页 --%>
<%
    if (session.getAttribute("currentUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html>
<head>
    <title>宿舍管理系统 - 管理员后台</title>
    <style>
        body { margin: 0; font-family: sans-serif; display: flex; height: 100vh; }
        /* 左侧菜单样式 */
        .sidebar { width: 220px; background-color: #2c3e50; color: white; padding-top: 20px; }
        .sidebar h3 { text-align: center; margin-bottom: 30px; }
        .sidebar a { display: block; padding: 15px 20px; color: #bdc3c7; text-decoration: none; border-bottom: 1px solid #34495e; }
        .sidebar a:hover { background-color: #34495e; color: white; }
        /* 右侧内容样式 */
        .content { flex: 1; padding: 20px; background-color: #ecf0f1; }
        .header { background: white; padding: 15px; margin-bottom: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.05); display: flex; justify-content: space-between;}
    </style>
</head>
<body>

<div class="sidebar">
    <h3>管理系统后台</h3>
    <a href="student?method=list" target="contentFrame">👤 人员管理</a>
    <a href="record?method=list" target="contentFrame">🛡️ 门禁出入</a>
    <a href="#">📝 访客登记</a>
    <a href="#">🛏️ 宿舍资源</a>
    <a href="#">🔧 报修处理</a>

    <a href="logout" style="color: #e74c3c;">🚪 退出登录</a>
</div>

<div class="content">
    <div class="header">
        <span>当前时间：<%= new java.util.Date().toString() %></span>
        <span>欢迎您，管理员：${currentUser.username}</span>
    </div>

    <div style="background: white; padding: 40px; border-radius: 8px;">
        <h2>欢迎进入系统后台</h2>
        <p>请点击左侧菜单进行操作。</p>
    </div>
</div>

</body>
</html>