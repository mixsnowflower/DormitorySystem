<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
    <style>
        body { font-family: sans-serif; padding: 20px; width: 500px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, select { width: 100%; padding: 8px; box-sizing: border-box; }
        button { background-color: #007bff; color: white; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>

<h2>录入新学生</h2>
<form action="student?method=add" method="post">
    <div class="form-group">
        <label>学号 (将作为登录账号)</label>
        <input type="text" name="sno" required>
    </div>
    <div class="form-group">
        <label>姓名</label>
        <input type="text" name="name" required>
    </div>
    <div class="form-group">
        <label>性别</label>
        <select name="gender">
            <option value="男">男</option>
            <option value="女">女</option>
        </select>
    </div>
    <div class="form-group">
        <label>班级</label>
        <input type="text" name="className">
    </div>
    <div class="form-group">
        <label>电话</label>
        <input type="text" name="phone">
    </div>

    <button type="submit">保存</button>
    <a href="student?method=list" style="margin-left: 20px;">返回列表</a>
</form>

</body>
</html>