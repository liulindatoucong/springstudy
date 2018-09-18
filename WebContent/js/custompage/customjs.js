/* 设置iframe高度 */  
function iframeLoad()
{
	$("#inner_iframe").on("load",function() {
	var height = document.getElementById("inner_iframe").contentWindow.document.body.scrollHeight;
	$('#iframeContainer').height(height);
	});
}

/* 配置需要进入的界面按钮事件 */
function click2page()
{
	$('#invoiceBtn').click(function(e) {
		$("#inner_iframe").attr("src","synchdataWeb/invoice");
	});
}