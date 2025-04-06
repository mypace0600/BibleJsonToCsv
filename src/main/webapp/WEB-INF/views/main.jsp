<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Excel 생성</title>
</head>
<body>
<h1>MAIN PAGE</h1>
<form action="/export" method="get">
    <input type="text" name="jsonPath" placeholder="파일 경로를 입력하세요" required />
    <button type="submit">excel 파일 생성</button>
</form>
</body>
</html>
