<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>修改学生信息</title>
    <style>
        body { font-family: sans-serif; padding: 20px; width: 500px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, select { width: 100%; padding: 8px; box-sizing: border-box; }
        button { background-color: #ffc107; color: black; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>

<h2>修改学生档案</h2>

<form action="student?method=update" method="post">

    <input type="hidden" name="studentId" value="${stu.studentId}">

    <div class="form-group">
        <label>学号 (不可修改)</label>
        <input type="text" name="sno" value="${stu.sno}" readonly style="background: #eee;">
    </div>

    <div class="form-group">
        <label>姓名</label>
        <input type="text" name="name" value="${stu.name}" required>
    </div>

    <div class="form-group">
        <label>性别</label>
        <select name="gender">
            <option value="男" ${stu.gender == '男' ? 'selected' : ''}>男</option>
            <option value="女" ${stu.gender == '女' ? 'selected' : ''}>女</option>
        </select>
    </div>

    <div class="form-group">
        <label>班级</label>
        <input type="text" name="className" value="${stu.className}">
    </div>

    <div class="form-group">
        <label>电话</label>
        <input type="text" name="phone" value="${stu.phone}">
    </div>

    <button type="submit">确认修改</button>
    <a href="student?method=list" style="margin-left: 20px;">取消</a>
</form>

</body>
</html>