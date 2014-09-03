<%-- 
	Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
--%>
<%@include file="/html/gatling/header.jsp"%>

<portlet:actionURL name="editPortletSample" var="editPortletSampleURL"	windowState="pop_up" >
	<portlet:param name="pagePortletId" value="${portletId}" />
	<portlet:param name="requestId" value="${requestId}" />
</portlet:actionURL>

<div class="well well-small">
	<liferay-ui:message key="portlet-edit-sample-details" />
</div>
<c:choose>
	<c:when test="${not empty script && not empty script.get(0) && not empty script.get(0).name && script.get(0).name != ' ' }">
		<aui:form action="" name="addSample" id="addSample">		
		

			<aui:select inlineField="true" label="portlet-edit-sample-select" name="Sample Script" required="true" id="selectScript">
			<!-- Get the list of the different sample scripts available for the Portlet. The list is store in a class named ListScript (package mustache) -->
			  	<c:forEach var="record" items="${script}">
					<aui:option label="${record.name}" value="${record.recordId}" />
				</c:forEach> 
		
			</aui:select>
			 
			<aui:button id="add" onClick="addLine()" value="portlet-edit-sample-add" cssClass="inline-button"/>
		
		</aui:form>
		
		<aui:form action="${editPortletSampleURL}" name="formPortletSample" method="POST">
			<table class="table table-bordered table-scenario">
				<thead>
					<tr>
						<th ><liferay-ui:message key="portlet-edit-sample-name" />
						<th class="small-column"><liferay-ui:message key="scenario-edit-table-header-weight" />
							<liferay-ui:icon-help message="weight-info-help" /></th>
						<th class="small-column"><liferay-ui:icon-help message="percent-info-help" /></th>
						<th class="small-column"><liferay-ui:message key="delete-message" /></th>
					</tr>
				</thead>
				
				<tbody id="bodyEditScript">  
					<c:forEach var="records" items='${ recordAndSampleList.keySet() }' varStatus="status1">
						<c:forEach var="record" items='${ recordAndSampleList.get(records) }' varStatus="status">
							<tr>
								<td title="${ record.recordId}">${records}</td>
								<td ><aui:input name="${ record.linkUsecaseRequestId}weightScenarioSample${ record.recordId}" value="${record.weight}" label="" cssClass="popup_weightPage" onChange="showWeightPopup()"></aui:input></td>
								<td class='popup_percent'></td>
								<td>
									<%--delete button --%>
									<portlet:actionURL var="deleteUseCaseURL" name="removeUseCase">
										<portlet:param name="useCaseId" value="${record.linkUsecaseRequestId }" />
										<portlet:param name="pagePortletId" value="${portletId }" />
										<portlet:param name="requestId" value="${requestId}" />
									</portlet:actionURL>
									<liferay-ui:icon-delete url="${deleteUseCaseURL}" />
								</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>				
			</table>
			
			<aui:button type="submit" id="submitPopupSample"/>		
		</aui:form>
	
	</c:when>
	
	<c:otherwise>
		<div class="alert">
			<liferay-ui:message key="portlet-edit-sample-empty"/>
		</div>	
	</c:otherwise>
</c:choose>
	
<div hidden="true">
<table><tbody id="toPaste">
	<tr>
		<td id="text" title=""></td>
		<td id="weight"><aui:input name="0weightScenarioSample"  label="" value="0.0" cssClass="popup_weightPage" onChange="showWeightPopup()"></aui:input></td>
		<td class='popup_percent'></td>
	</tr>
</tbody></table>
</div>


<script type="text/javascript">

	function addLine() {
		AUI().use('aui-base', 'aui-node-base', function(A){
			var idSelect = "<portlet:namespace/>"+"selectScript";
			var label = document.getElementById(idSelect)[document.getElementById(idSelect).selectedIndex].textContent;
			
			if(label != null && label != "" && label != " "){			
				var value = document.getElementById(idSelect).value;				
				var one = A.one('#toPaste').html();
				var html = A.Node.create(one);
				html.one('#text').html(label);
				html.one('#text').set('title',value); //sampleId
				var weightInput = html.one('#weight').one("input");
				var weightName = weightInput.get('name') + value;
				weightInput.set('name',weightName); //sampleId
				html.appendTo('#bodyEditScript');	
			}
		
		});
	}
	
	
	function showWeightPopup() {
		AUI().use('aui-base', function(A) {
			var totalRate = parseInt(0);
			var listePage = A.all('.popup_weightPage');
			listePage.each(function() {
				if (!(this.val() == "" || isNaN(this.val())) && this.val() > 0) {
					totalRate += parseFloat(this.val());
				}
				else {
					this.val("0.0");
					this.ancestor("tr").addClass("empty-weight-color");
				}
			});

			listePage.each(function() {
				if (!(this.val() == "" || isNaN(this.val()))) {
					var perc = (this.val() / totalRate) * 100;
					//cas du 0/0
					if (isNaN(perc))
						this.ancestor("tr").one(".popup_percent").text("0.00 %");
					else
						this.ancestor("tr").one(".popup_percent").text(perc.toFixed(2) + " %");
				}
			});
		});
	}
	showWeightPopup();

</script>