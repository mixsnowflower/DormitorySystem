<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>学生管理</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        /* 表格样式 */
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }
        th { background-color: #f2f2f2; }

        /* === 弹窗的核心样式 === */
        #editModal {
            display: none; /* 默认隐藏 */
            position: fixed; top: 0; left: 0; width: 100%; height: 100%;
            background-color: rgba(0,0,0,0.5); /* 半透明黑背景 */
            justify-content: center; align-items: center;
        }
        .modal-content {
            background-color: white; padding: 30px; border-radius: 8px; width: 400px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
            position: relative; top: 100px; margin: 0 auto;
        }
        .modal-content input { width: 100%; padding: 8px; margin: 10px 0; box-sizing: border-box; }
        .close-btn { float: right; cursor: pointer; font-weight: bold; font-size: 20px; }
    </style>
</head>
<body>

<h2>🎓 学生信息列表</h2>
<a href="student_add.jsp" style="background: #28a745; color: white; padding: 8px 15px; text-decoration: none;">+ 添加学生</a>

<table>
    <thead>
    <tr>
        <th>ID</th><th>学号</th><th>姓名</th><th>性别</th><th>班级</th><th>电话</th><th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="stu">
        <tr>
            <td>${stu.studentId}</td>
            <td>${stu.sno}</td>
            <td>${stu.name}</td>
            <td>${stu.gender}</td>
            <td>${stu.className}</td>
            <td>${stu.phone}</td>
            <td>
                <button onclick="openEditModal('${stu.studentId}', '${stu.name}', '${stu.gender}', '${stu.className}', '${stu.phone}')">
                    ✏️ 编辑
                </button>

                <a href="student?method=delete&id=${stu.studentId}" onclick="return confirm('确认删除？')" style="color: red; margin-left: 10px;">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div id="editModal">
    <div class="modal-content">
        <span class="close-btn" onclick="closeEditModal()">×</span>
        <h3>修改学生信息</h3>

        <form id="editForm">
            <input type="hidden" id="edit_id" name="studentId">

            姓名：<input type="text" id="edit_name" name="name"><br>
            性别：<input type="text" id="edit_gender" name="gender"><br>
            班级：<input type="text" id="edit_class" name="className"><br>
            电话：<input type="text" id="edit_phone" name="phone"><br>

            <button type="button" onclick="submitEdit()"
                    style="width: 100%; background: #007bff; color: white; padding: 10px; border: none; cursor: pointer; margin-top: 10px;">
                保存修改
            </button>
        </form>
    </div>
</div>

<script>
    // 打开弹窗，并自动填入数据
    function openEditModal(id, name, gender, className, phone) {
        // 1. 把数据显示在弹窗的输入框里
        document.getElementById('edit_id').value = id;
        document.getElementById('edit_name').value = name;
        document.getElementById('edit_gender').value = gender;
        document.getElementById('edit_class').value = className;
        document.getElementById('edit_phone').value = phone;

        // 2. 显示弹窗
        document.getElementById('editModal').style.display = 'block';
    }

    // 关闭弹窗
    function closeEditModal() {
        document.getElementById('editModal').style.display = 'none';
    }
    // ... 原有的 openEditModal 和 closeEditModal 代码 ...

    // 🆕 新增：异步提交修改
    function submitEdit() {
        // 1. 获取表单数据
        var form = document.getElementById("editForm");
        var formData = new FormData(form);

        // 2. 将数据转换为 URL 查询参数格式 (因为 Servlet 习惯读 key=value)
        var searchParams = new URLSearchParams(formData);

        // 3. 使用 fetch 发送请求 (这就是 AJAX)
        fetch('student?method=update', {
            method: 'POST',
            body: searchParams
        })
            .then(response => {
                if (response.ok) {
                    // 4. 重点在这里：收到成功回应后，自动刷新页面！
                    alert("修改成功！");
                    window.location.reload(); // 🔄 这行代码代替了你的F5
                } else {
                    alert("修改失败，服务器发生错误");
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("网络请求失败");
            });
    }
</script>

</body>
</html>