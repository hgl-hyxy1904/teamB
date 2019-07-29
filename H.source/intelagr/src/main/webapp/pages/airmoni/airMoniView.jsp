<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/tags/simple" prefix="s" %>
<!DOCTYPE html>
<html style="width:100%;height:100%;overflow:hidden">
<head>
<title>用户管理-五常优质水稻溯源监管平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/index.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/color.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/form2js.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
	<div region="center" border="false" style="padding: 5px;">
		<form id="addFrom" class="easyui-form" method="post" data-options="novalidate:true">
			<input name="id" value="${airMoni.id }" type="hidden">
			<fieldset id="queryBlock" class="fieldset_common_style">
				<table class="table_common_style">
					<tr>
		    			<td class="table_common_td_label_style">监测日期：</td>
		    			<td class="table_common_td_txt_style">
		    				<input class="easyui-datebox" id="monitorDate" name="monitorDate"  value="${date }" 
	            data-options="showSeconds:false" style="width:170px" editable="false">
		    			</td>
	    			</tr>
	    			<tr>
						<td class="table_common_td_label_style">监测点位：</td>
		    			<td class="table_common_td_txt_style">
		    				<s:select name="monitorPointCode" id="monitorPointCode" entityName="commondata" codeKey="MonitorPointType" value="${airMoni.monitorPointCode }" hasPleaseSelectOption="true"></s:select>
		    			</td>
	    			</tr>
	    			<tr>
		    			<td class="table_common_td_label_style">TSP(mg/m³)：</td>
		    			<td class="table_common_td_txt_style">
		    				<input class="easyui-numberbox" type="text" id="tsp" name="tsp" value="${airMoni.tsp }"  data-options="min:0,max:999999999.99,precision:2,groupSeparator:','"style="width:170px;"></input>
		    				<span class="span_common_mustinput_style">*</span>
		    			</td>
	    			</tr>
	    			<tr>
		    			<td class="table_common_td_label_style">SO2(mg/m³)：</td>
		    			<td class="table_common_td_txt_style">
		    				<input class="easyui-numberbox" type="text" id="so2" name="so2" value="${airMoni.so2 }"  data-options="min:0,max:999999999.99,precision:3,groupSeparator:','" style="width:170px;"></input>
		    				<span class="span_common_mustinput_style">*</span>
		    			</td>
	    			</tr>
	    			<tr>
		    			<td class="table_common_td_label_style">NO2(mg/m³)：</td>
		    			<td class="table_common_td_txt_style">
		    				<input class="easyui-numberbox" type="text" id="no2" name="no2" value="${airMoni.no2 }"  data-options="min:0,max:999999999.99,precision:3,groupSeparator:','" style="width:170px;"></input>
		    				<span class="span_common_mustinput_style">*</span>
		    			</td>
	    			</tr>
	 
				</table>
			</fieldset>
		</form>
	</div>
	<script type="text/javascript">
	


</script>
</body>
</html>