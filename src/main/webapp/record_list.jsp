<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>出入记录</title>
    <style>
        body { font-family: "微软雅黑", sans-serif; padding: 20px; background-color: #f5f5f5; }
        .container { display: flex; gap: 20px; }

        /* 左侧表格区域 */
        .main-panel { flex: 3; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border-bottom: 1px solid #eee; padding: 12px; text-align: left; }
        th { background-color: #f8f9fa; color: #666; }

        /* 状态标签样式 */
        .tag { padding: 4px 8px; border-radius: 4px; font-size: 12px; color: white; }
        .tag-in { background-color: #28a745; } /* 绿色代表到达 */
        .tag-out { background-color: #ffc107; color: black; } /* 黄色代表离开 */

        /* 右侧操作区域 */
        .side-panel { flex: 1; background: white; padding: 20px; border-radius: 8px; height: fit-content; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        select, input, textarea { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
        button { width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>

<h2>🚪 出入登记管理</h2>

<div class="container">
    <div class="main-panel">
        <h3>📜 历史记录</h3>
        <table>
            <thead>
            <tr>
                <th>时间</th>
                <th>姓名</th>
                <th>学号</th>
                <th>状态</th>
                <th>备注</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${recordList}" var="r">
                <tr>
                    <td>${r.time}</td>
                    <td>${r.studentName}</td>
                    <td>${r.sno}</td>
                    <td>
                                <span class="tag ${r.type == '到达' ? 'tag-in' : 'tag-out'}">
                                        ${r.type}
                                </span>
                    </td>
                    <td style="color: #888; font-size: 13px;">${r.remark}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="side-panel">
        <h3>⏱️ 模拟打卡</h3>
        <p style="font-size: 12px; color: #666;">管理员可手动添加记录</p>

        <form action="record?method=add" method="post">
            <div class="form-group">
                <label>选择学生</label>
                <select name="studentId">
                    <c:forEach items="${studentList}" var="s">
                        <option value="${s.studentId}">${s.name} (${s.sno})</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label>类型</label>
                <select name="type">
                    <option value="离开">🏃 离开宿舍</option>
                    <option value="到达">🏠 回到宿舍</option>
                </select>
            </div>

            <div class="form-group">
                <label>备注 (可选)</label>
                <input type="text" name="remark" placeholder="例如：外出购物">
            </div>

            <button type="submit">确认登记</button>
        </form>
    </div>
</div>

</body>
</html>