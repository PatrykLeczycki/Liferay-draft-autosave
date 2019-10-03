<%@ page import="com.liferay.portal.kernel.security.auth.AuthTokenUtil" %>
<%@ include file="/init.jsp" %>
Liferay.authToken = '<%= AuthTokenUtil.getToken(request) %>';

<p>
	<b><liferay-ui:message key="journalportletmvc.caption"/></b>
</p>