<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importAll()">一键导入商品数据到索引库</a>
</div>
<script type="text/javascript">
	
	function importAll() {
		$.ajax({
			url:"/index/imporAll",
			type:"GET",
			success:function(data){
				//taotaoresult
				if (data.tatus==200) {
					alert("导入成功");
				}else {
					alert("导入成功");
				}
			}
		});
	}
</script>