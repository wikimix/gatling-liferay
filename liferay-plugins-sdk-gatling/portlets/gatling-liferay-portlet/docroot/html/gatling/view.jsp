<%@include file="/html/gatling/header.jsp" %>

<%
	String redirect = PortalUtil.getCurrentURL(renderRequest);	
%>

<div class="right_column">
<aui:button-row>
	<portlet:renderURL var="addSimulationURL">
		<portlet:param name="mvcPath" value="/html/gatling/addSimulation.jsp" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="p_p_isolated" value="1" />
	</portlet:renderURL>

	<aui:button value="add-simulation" onClick="<%= addSimulationURL.toString() %>"/>
</aui:button-row>
</div>
<div class="left_column">
<%-- 	<%@include file="/html/gatling/viewSimulationsList.jsp" %> --%>
</div>