/**
 * 
 */
function syncDataList()
{
	createTable(null);
	$('#syncErpData').click(function() {
		var plant = $('#selectPlant').val();
		var daterange = $('#reservation').val();
		if(plant == null || plant == '')
		{
			return;
		}
		if(daterange == null || daterange == '')
		{
			alert('请选择时间范围');
			return;
		}
		var data = daterange.split(" - ");
		var startDateParam = data[0];
		var endDateParam = data[1];
		var paramStr = {plant:plant,startDate:startDateParam,endDate:endDateParam};
		$.ajax({
			url: context+'synchdataWeb/invoice/getDataList',
			contentType:"application/x-www-form-urlencoded",
			data:paramStr,
            type: 'POST',
            cache: false,
            success : function(res){
    			if('S' != res.status)
   				{
    				alert(res.msg);
   				}
    			else
   				{
    				var dataTable_re = $('#invoice_table').dataTable();
    				dataTable_re.fnClearTable();
    				var resultData = res.data;
    				dataTable_re.fnAddData(resultData);
   				}
    		}
		});	
	});
}


//创建表单
function createTable()
{
	// begin first table
    $('#invoice_table').dataTable({
        "sDom": "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_ 记录每页",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "oPaginate": {
            	"sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页"
            },
            "sSearch": "搜索:"
        },
        aoColumns: [
	        { "mData": "vbillcode", "sTitle": "单据号"},
	        { "mData": "billtypename", "sTitle": "单据类型"},
	        { "mData": "vbilldate", "sTitle": "单据日期"},
	        { "mData": "vbilldtype", "sTitle": "发货类型"},
	        { "mData": "vbilldpname", "sTitle": "发货计划员"},
	        { "mData": "vbillddname", "sTitle": "发货部门"},
	        { "mData": "vbillvdef2", "sTitle": "锂电订单编号"},
	        { "mData": "vbillrowno", "sTitle": "单据行号"},
	        { "mData": "cuscode", "sTitle": "客户端编码"},
	        { "mData": "cusdescription", "sTitle": "客户名称"},
	        { "mData": "material", "sTitle": "物料编码"},
	        { "mData": "matdescription", "sTitle": "物料名称"},
	        { "mData": "vbilldnum", "sTitle": "主数量"},
	        { "mData": "vbillduname", "sTitle": "主单位"},
	        { "mData": "status", "sTitle": "状态"},       
	    ],
        display:"cell-border",
        "aoColumnDefs": [{
            'bSortable': false,
            'aTargets': [0]
        }]
    });
    jQuery('#invoice_table_wrapper .dataTables_filter input').addClass("form-control"); // modify table search input
    jQuery('#invoice_table_wrapper .dataTables_length select').addClass("form-control"); // modify table per page dropdown

}