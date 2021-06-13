<%--
  Created by IntelliJ IDEA.
  User: best
  Date: 22.03.2020
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type"
        content="text/html; charset=windows-1250">
  <title>Test of the document</title>
  <style>
    .button {
      background-color: #0ec910;
      border: azure;
      color: #fffef0;
      padding: 20px 34px;
      text-align: center;
      text-decoration: #0fff09;
      display: inline-block;
      font-size: 20px;
      margin: 4px 2px;
      cursor: pointer;
    }
    form{
      background: #000000;
      border: #ff0505;
      color: #fbffff;
      padding: 20px 34px;
      text-align: center;
      text-decoration: #f0f8ff;
      display: inline-block;
      font-size: 20px;
      margin: 4px 2px;
    }
  </style>
</head>
<body>

<form method="get" class="form" action="http://localhost:8080/TPO_3/Add">
  <table>
    <tr>
      <td>component1</td>
      <td><input type="number" class="button" size="50" name="num1">
      </td>
    </tr>
    <tr>
      <td>component2</td>
      <td><input type="number" class="button" size="50" name="num2">
      </td>
    </tr>
    <tr>
      <td><button type="submit" class="button" formmethod="get" formaction="/TPO_3/Add">GET</button></td>
      <td><button type="submit" class="button" formmethod="post" formaction="/TPO_3/Add">POST</button></td>
    </tr>
    <tr>
    </tr>
  </table>
</form>
</body>
</html>
