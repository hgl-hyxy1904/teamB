<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@taglib uri="/tags/simple" prefix="s" %>
<!DOCTYPE html>
<html style="width:100%;height:100%;overflow:hidden">
<head>
<meta charset="utf-8">

<title>用户管理-五常优质水稻溯源监管平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/table.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/color.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/form2js.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>

</head>
<body class="easyui-layout">
	<div region="center" border="false" style="padding:5px;">
		 
			<form id="inputFrom" name="inputFrom" method="post" action="">
				 <input type="hidden" name="id" id="id" value="">
				 <input type="hidden" name="archiveAcreage" id="archiveAcreage">
				 <input type="hidden" name="operatorName" id="operatorName" value="">
				 <input type="hidden" name="operatorDate" id="operatorDate" value="">
				 <!-- 承包方类型 -->
				 <input type="hidden" name="" id="contractorTypeTmp" value="">
				 <!-- 证件类型 -->
				 <input type="hidden" name="idType" id="idTypeTmp" value="">
				 <!-- 证件号码 -->
				 <input type="hidden" name="contractorID" id="contractorIDTmp" value="">
				 <!-- 承包人 -->
				 <input type="hidden" name="contractorName" value="contractorName">
				 <!-- 联系方式 -->
				 <input type="hidden" name="contractorTelTmp" id="contractorTelTmp" value="">
				 
				 <input type="hidden" id="applyBatchNo" name="applyBatchNo" value="${ApplyBatchNo }"/>
				 <input type="hidden" name="townCode" value="1">
				 <input type="hidden" name="countryCode" value="2">
				 <input type="hidden" name="groupName" value="3">
				 <input type="hidden" name="status" id="status" value="01">
			<fieldset id="queryBlock" class="fieldset_common_style">
			<table class="table_common_style">
				<tr>
					<td class="table_common_td_label_style">申请批次号：</td>
					<td class="table_common_td_txt_style">
						<span  id="ApplyBatchNo"  >${ApplyBatchNo }</span>
					</td>
				</tr>
				<tr>
					<td class="table_common_td_label_style">年度：</td>
					<td class="table_common_td_txt_style editableFalse">
						

				<s:select name="year" id="year" entityName="yearcode" value="${geneyear }" readOnly="true" canEdit="false"></s:select>				
					</td>
					<td class="table_common_td_label_style">企业：</td>
					<td class="table_common_td_txt_style editableFalse">
				<s:select name="companyCode" id="companyCode" entityName="company" value="${companyCode }" readOnly="true" canEdit="false"></s:select>				
					</td>
				</tr>
			</table>
			</fieldset>	
			<fieldset id="toolBlock" class="fieldset_common_style">
			</fieldset>
		<!-- Modify By WolfSoul Begin -->
		<table id="geneLandData" class="easyui-datagrid" striped="true" singleSelect="false" style="table-layout:fixed;border-collapse: collapse;">
			<thead> 
				<tr> 
					<th field="contractorValue" width="30" align="center" checkbox="true">选择</th>
					<th field="contractorType" align="center" hidden="true"></th>
					<th field="contractorText" width="120" align="center" nowrap="nowrap">承包方类型</th>
					<th field="idType" align="center" hidden="true"></th>
					<th field="idTypeText" width="120" align="center" nowrap="nowrap">证件类型</th>
					<th field="contractorID" width="150" align="center" nowrap="nowrap">证件号码</th>
					<th field="contractorName" width="120" align="center" nowrap="nowrap">承包方</th>
					<th field="contractorTel" align="center" hidden="true"></th>
					<th field="cityCode" align="center" hidden="true"></th>
					<th field="townCode" align="center" hidden="true"></th>
					<th field="townText" width="120" align="center" nowrap="nowrap">所在乡</th>
					<th field="countryCode" align="center" hidden="true"></th>
					<th field="countryText" width="120" align="center" nowrap="nowrap">所在村</th>
					<th field="groupName" width="200" align="center" nowrap="nowrap">住址</th>
					<th field="zmj" width="130" align="center" nowrap="nowrap">承包总面积（亩）</th>
					<th field="yba" width="130" align="center" nowrap="nowrap">已备案面积（亩）</th>
					<th field="kba" width="130" align="center" nowrap="nowrap">可备案面积（亩）</th>
					<th field="archiveAcreage" width="130" align="center" nowrap>本次备案面积（亩）</th>
					<th field="operatorName" width="130" align="center" nowrap="nowrap">经办人</th>
					<th field="operatorDate" width="120" align="center" nowrap="nowrap">经办日期</th> 
				</tr>
			</thead>
			<tbody id="dataBody">
					 <c:forEach items="${geneLandRegDList }" var="g">
					<tr>
					<td field="contractorValue" width="30" align="center" checkbox="true">${g.contractorType },${g.idType },${g.contractorID },${g.archiveAcreage },${g.operatorName },${g.operatorDate },${g.contractorName },${g.contractorTel },${g.townCode },${g.countryCode },${g.groupName },${g.id }</td>
					<td field="contractorType" align="center" hidden="true">${g.contractorType }</td>
					<td field="contractorText" width="120" align="center" nowrap="nowrap">${g.contractorTypeName }</td>
					<td field="idType" align="center" hidden="true">${g.idType }</td>
					<td field="idTypeText" width="120" align="center" nowrap="nowrap">${g.idName }</td>
					<td field="contractorID" width="150" align="center" nowrap="nowrap">${g.contractorID }</td>
					<td field="contractorName" width="120" align="center" nowrap="nowrap">${g.contractorName }</td>
					<td field="contractorTel" align="center" hidden="true">${g.contractorTel }</td>
					<td field="cityCode" align="center" hidden="true">${g.cityCode }</td>
					<td field="townCode" align="center" hidden="true">${g.townCode }</td>
					<td field="townText" width="120" align="center" nowrap="nowrap">${g.townName }</td>
					<td field="countryCode" align="center" hidden="true">${g.countryCode }</td>
					<td field="countryText" width="120" align="center" nowrap="nowrap">${g.countryName }</td>
					<td field="groupName" width="200" align="center" nowrap="nowrap">${g.groupName }</td>
					<td field="zmj" width="130" align="center" nowrap="nowrap">${g.zmj }</td>
					<td field="yba" width="130" align="center" nowrap="nowrap">${g.yba }</td>
					<td field="kba" width="130" align="center" nowrap="nowrap">${g.kba }</td>
					<td field="archiveAcreage" width="130" align="center" nowrap>${g.archiveAcreage }</td>
					<td field="operatorName" width="130" align="center" nowrap="nowrap">${g.operatorName }</td>
					<td field="operatorDate" width="120" align="center" nowrap="nowrap"><f:formatDate value="${g.operatorDate }" pattern="yyyy-MM-dd"/></td> 
					</tr>
					</c:forEach> 
			</tbody>
		</table>
		
		</form>
	</div>
	<div id="addDialog"></div>
	<div id="importDialog"></div>
	
<script type="text/javascript">
	

$(document).ready(function(){
	 var winHeight = $(window).height();
     var queryBlockHeight = $("#queryBlock").height();
     var toolBlock = $("#toolBlock").height();
	//Modify By WolfSoul Begin
	$("#geneLandData").datagrid({ 
        height:winHeight -queryBlockHeight -toolBlock - 50,
        rownumbers: true,
        fitColumns: false
	});
	//Modify By WolfSoul End
});
</script>
</body>
</html>