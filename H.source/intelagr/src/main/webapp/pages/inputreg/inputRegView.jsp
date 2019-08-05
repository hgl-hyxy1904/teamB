<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/tags/simple" %>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
	<script>
		var root = "${pageContext.request.contextPath}";
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload.js"></script>
</head>
<body class="easyui-layout">
	<div region="center" border="false" style="padding:5px;">
		<form id="addFrom" class="easyui-form" method="post" data-options="novalidate:true">
			<input type="hidden" name="id" id="id" value="${inputReg.id }"/>
			<fieldset class="fieldset_common_style">
				
			</fieldset>
			<fieldset class="fieldset_common_style">
				<table class="table_common_style">
						<tr>
							<td class="table_common_td_label_style">上报流水号：</td>
							<td colspan="3" class="table_common_td_txt_style">
							<span id="applyBatchNo" name="applyBatchNo" type="text" readonly="readonly" value="${inputReg.applyBatchNo }">${inputReg.applyBatchNo }</span>
							</td>
						</tr>
						<tr>
							<td class="table_common_td_label_style">年度：</td>
							<td class="table_common_td_txt_style ">
<input id="year" name="year" type="text" readonly="readonly" value="${inputReg.year }"></input>
		 <script type="text/javascript">

			 $(document).ready(function(){ 

				 $('#year').combobox({ 

					 onChange:function(newValue,oldValue){

						 return false;

					 } 

				 });


			 });

		</script>

								<span class="span_common_mustinput_style">*</span>
							</td>
							<td class="table_common_td_label_style">企业名称：</td>
							<td class="table_common_td_txt_style">
		    <input type="hidden" id="companyCode_companyName" name="companyName" value="${inputReg.companyCode }">
			<input type="text" id="companyCode_companyName" readonly="readonly" name="companyName" value="${inputReg.companyName }">
		</select>



								<span class="span_common_mustinput_style">*</span>
							</td>
						</tr>
						<tr>
							<td class="table_common_td_label_style">投入品名称：</td>
							<td class="table_common_td_txt_style">
								<input class="easyui-textbox"  readonly="readonly"  type="text" name="inputGoodsName" value="${inputReg.inputGoodsName }" style="width:187px;"></input>
								<span class="span_common_mustinput_style">*</span>
							</td>
							<td class="table_common_td_label_style">采购量：</td>
							<td class="table_common_td_txt_style">
								<input class="easyui-numberbox"  readonly="readonly"  precision="2" min="0.00" max="9999999.99" type="text" name="purchaseQuantity" value="${inputReg.purchaseQuantity }" style="width:100px;"></input>
								

		<select id="inputGoodsUnit" name="inputGoodsUnit" class="easyui-combobox" style="width:85px;height:22px" data-options="editable:true" disabled="disabled">

			<option  readonly="readonly"  value="01">公斤</option>

			<option readonly="readonly"  value="02">斤</option>

			<option readonly="readonly"  value="03">吨</option>

			<option  readonly="readonly" value="04">升</option>

			<option readonly="readonly"  value="05">公升</option>

			<option readonly="readonly"  value="06">克</option>

		</select>

		 <script type="text/javascript">

			 $(document).ready(function(){ 

				 $('#inputGoodsUnit').combobox({ 

					 onChange:function(newValue,oldValue){

						 return false;

					 } 

				 });


			 });

		</script>

								<span class="span_common_mustinput_style">*</span>
							</td>
						</tr>
						<tr>
							<td class="table_common_td_label_style">投入品经销商：</td>
							<td class="table_common_td_txt_style">
								<input class="easyui-textbox" type="text" name="inputGoodsSupplier" readonly="readonly"  value="${inputReg.inputGoodsSupplier }" style="width:187px;"></input>
								<span class="span_common_mustinput_style">*</span>
							</td>
						</tr>
						<tr>
							<td class="table_common_td_label_style">采购人：</td>
							<td class="table_common_td_txt_style">
								<input class="easyui-textbox" type="text" name="purchasePerson" readonly="readonly"  value="${inputReg.purchasePerson }" style="width:187px;"></input>
								<span class="span_common_mustinput_style">*</span>
							</td>
							<td class="table_common_td_label_style">采购日期：</td>
							<td class="table_common_td_txt_style editableFalse">
								<input class="easyui-datebox" name="purchaseDate"  readonly="readonly"  value="${inputReg.purchaseDate }"
            data-options="required:true,showSeconds:false" style="width:187px" editable="false">
								<span class="span_common_mustinput_style">*</span>
							</td>
						</tr>
					</table>
			</fieldset>
			<fieldset class="fieldset_common_style">
				<legend>采购凭证上传</legend>
				<table class="table_common_style">
					<tr>
						<td>
						
								<span class="l-btn-left"><span class="">
								上传采购凭证</span></span>
						
						</td>
					</tr>
					<tr>	
						<td>
							<table height="216px;" id="imgList" width="100%" class="easyui-datagrid" striped="true" singleSelect="true">
								<thead>
									<tr>
										<th field="originalName" width="50%" align="center">采购凭证</td>
										<th field="fileInfo" width="30%" align="center">说明</td>
										<th field="op" width="20%" align="center">操作</td>
									</tr>
								</thead>
							</table>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="fieldset_common_style">
				<legend>采购凭证预览</legend>
				<table id="fileDiv" class="table_common_style">
					<tr>
						<td>
											<!-- 有这个标签里的方法才能在预览的时候图片那一块能显示 -->
		<s:imgView outerDivId="imgPriviewOuter" innerDivId="imgPriviewInner" imgInfoMaps="impPathAndInfoMaps" width="420" height="500" ></s:imgView>
							
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<div id="addDialog"></div>
	<div id="uploadDialog"></div>
</body>

</html>
<script type="text/javascript">
	$(document).ready(function(){
	/* 	提交表单的id这个id得在表单的id那的vaule那把值取出来 */
		var id = $("#id").val(); 
		
		if(id != ""){
			
			showFileList("02", id, '采购凭证', 'imgPriviewOuter', 'imgPriviewInner' );
		}
	});
	</script>

<script type="text/javascript">

</script>