<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>欢迎来我家</title>
	<%@include file="taglib.jsp"%>
    <%
    String name = com.xu.manager.ClassUtil.PropertiesUtil.getName();
    %>
    <script type="text/javascript">
      var name = "<%=name%>";
    </script>
</head>
<body>

</body>
</html>