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
				<table class="table_common_style">
					<tr>
						<td>
							<a href="#" class="easyui-linkbutton"  onclick="return save();">
								<span class="l-btn-left">
									<span class="l-btn-text icon-save l-btn-icon-left">
									保存</span></span>
							</a>
							
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="fieldset_common_style">
				<table class="table_common_style">
						<tr>
							<td class="table_common_td_label_style">上报流水号：</td>
							<td colspan="3" class="table_common_td_txt_style">
							<input id="applyBatchNo" name="applyBatchNo" type="hidden" readonly="readonly" value="${applyBatchNo }">${applyBatchNo }</input>
							</td>
						</tr>
						<tr>
							<td class="table_common_td_label_style">年度：</td>
							<td class="table_common_td_txt_style editableFalse">
				
		<s:select id="year" name="year" entityName="yearcode"  hasPleaseSelectOption="true"></s:select>

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
								
			<s:select name="companyCode" id="companyCode" entityName="company" value="${companyCode }" codeKey="" hasPleaseSelectOption="true"></s:select>	
		    <input type="hidden" id="companyCode_companyName" name="companyName" value="">

		</select>

		 <input type="hidden" id="companyCode_companyName" name="companyName" value="">

		 <script type="text/javascript">

			 $(document).ready(function(){ 

				 $('#companyCode_companyName').val($('#companyCode option:selected').text());

				 $('#companyCode').combobox({ 

					 onChange:function(newValue,oldValue){

						 var ops = document.getElementById('companyCode').options;

						 for(var i=0;i<ops.length;i++){ 

							 if(ops[i].value == newValue){ 

								 $('#companyCode_companyName').val(ops[i].text); 

								 break; 

							 } 

						 } 

						 return false;; 

					 } 

				 });


				 $('#companyCode').combobox('textbox').bind('focus',function(){
					 var value = $('#companyCode').combobox('getValue');
					 var opts = $('#companyCode').combobox('getData');
						 if(value != ''){
							 var findFlag = false;
							 for(var i=0; i<opts.length; i++){
								 if(opts[i].value == value){
									 findFlag = true;
									 break;
								 }
							 }
							 if(!findFlag){
								 value = '';
								 $('#companyCode').combobox('setValue', '');
							 }
						 }
					 if(value == ''){
						 $('#companyCode').combobox('setText','');
					 }
				 }); 
				 $('#companyCode').combobox('textbox').bind('blur',function(){  
					 var value = $('#companyCode').combobox('getValue');
					 if(value == ''){
						 $('#companyCode').combobox('setText','-=请选择=-');
					 }
				 });
			 });

		</script>



								<span class="span_common_mustinput_style">*</span>
							</td>
						</tr>
						<tr>
							<td class="table_common_td_label_style">投入品名称：</td>
							<td class="table_common_td_txt_style">
								<input class="easyui-textbox"  type="text" name="inputGoodsName" value="" style="width:187px;"></input>
								<span class="span_common_mustinput_style">*</span>
							</td>
							<td class="table_common_td_label_style">采购量：</td>
							<td class="table_common_td_txt_style">
								<input class="easyui-numberbox" precision="2" min="0.00" max="9999999.99" type="text" name="purchaseQuantity" value="" style="width:100px;"></input>
							<s:select name="inputGoodsUnit" id="inputGoodsUnit" entityName="commondata" codeKey="InputMaterialUnit"></s:select>	

		

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
								<input class="easyui-textbox" type="text" name="inputGoodsSupplier" value="" style="width:187px;"></input>
								<span class="span_common_mustinput_style">*</span>
							</td>
						</tr>
						<tr>
							<td class="table_common_td_label_style">采购人：</td>
							<td class="table_common_td_txt_style">
								<input class="easyui-textbox" type="text" name="purchasePerson" value="" style="width:187px;"></input>
								<span class="span_common_mustinput_style">*</span>
							</td>
							<td class="table_common_td_label_style">采购日期：</td>
							<td class="table_common_td_txt_style editableFalse">
								<input class="easyui-datebox" name="purchaseDate"  value="Sun May 21 00:00:00 CST 2017"
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
							<a href="#" class="easyui-linkbutton" onclick="showUploadDialog('02', '', '采购凭证', 'imgPriviewOuter', 'imgPriviewInner');">
								<span class="l-btn-left"><span class="l-btn-text icon-docupload l-btn-icon-left">
								上传采购凭证</span></span>
							</a>
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
		var id = $("#id").val(); 
		if(id != ""){
			showFileList("02", id , '采购凭证', 'imgPriviewOuter', 'imgPriviewInner' );
		}
	});
	
	//动态调整预览图片位置
	var width = $("#fileDiv").width();
	var picWidth = $("#imgPriviewInner").width();
	var paddingLeft = (width - picWidth)/2 + "px";
	$("#fileDiv").css("padding-left", paddingLeft);

	function save(){
		if(!check()) return ;
		var a = $('#addFrom').toObject();
		var retFlag = '';
		alert("--a-a-a--a-a");
		Public.ajaxPost('${pageContext.request.contextPath}/inputReg/save', 
				JSON.stringify(a),
				function(e) {
			if (200 == e.status) {
				$.messager.alert('提示','保存成功。','info', function(){
					if(retFlag == '1'){
						retList();
						//window.document.location.href = "${pageContext.request.contextPath}/inputReg/list";
					}else{
						window.document.location.href = "${pageContext.request.contextPath}/inputReg/editInput";
					}
				});
			} else {
				$.messager.alert('错误','保存失败！' + e.msg,'error');
			}
		});
	}
	function retList(){
		var url = "${pageContext.request.contextPath}/inputReg/list";
		url += "?year=";
		url += "&page=";
		url += "&pageSize=";
		url += "&companyCode=";
		url += "&beginDate=";
		url += "&endDate=";
		
		window.document.location.href = url;
	}
	function closeEdiDialog(){
		$('#addDialog').dialog('close');
	}
	function check(){
		if( $('#companyCode').combobox('getValue') == "" ){
			$.messager.alert('警告','请选择企业。','warning');
			return false;
		}
		if($("input[name='inputGoodsName']").val()==""){
			$.messager.alert('警告','请填写投入品名称。','warning');
			return false;
		}
		if( $("input[name='inputGoodsName']").val().length > 50 ){
			$.messager.alert('警告','投入品名称长度不能超过50。','warning');
			return false;
		}
		if( $("input[name='inputGoodsSupplier']").val().length > 100 ){
			$.messager.alert('警告','投入品经销商长度不能超过100。','warning');
			return false;
		}
		if($("input[name='purchaseQuantity']").val()==""){
			$.messager.alert('警告','请填写采购量。','warning');
			return false;
		}else if (!isNumber($("input[name='purchaseQuantity']").val())){
			$.messager.alert('警告','采购量请输入数字。','warning');
			return false;
		}
		if($("input[name='purchasePerson']").val()==""){
			$.messager.alert('警告','请填写采购人。','warning');
			return false;
		}
		if( $("input[name='purchasePerson']").val().length > 20 ){
			$.messager.alert('警告','采购人长度不能超过20。','warning');
			return false;
		}
		
		return true;
	}
	function showVideoList(o, d){}
</script>