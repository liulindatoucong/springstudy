/**
 * 
 */

function createTable()
{
	// begin first table
    var table = $('#upload_table').dataTable({
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
	        { "mData": "itemCode", "sTitle": "物料编码"},
	        { "mData": "itemDesc", "sTitle": "物料描述"},
	        { "mData": "version", "sTitle": "物料版本"},
	        { "mData": "orderNum", "sTitle": "生产订单号"},
	        { "mData": "barCode", "sTitle": "条码号"},
	        { "mData": "packTheoryNum", "sTitle": "电池包理论数量"},
	        { "mData": "packActualNum", "sTitle": "电池包实际数量"},
	        { "mData": "moduleTheoryNum", "sTitle": "模块码理论数量"},
	        { "mData": "moduleActualNum", "sTitle": "模块码实际数量"},
	        { "mData": "batteryTheoryNum", "sTitle": "电芯码理论数量"},
	        { "mData": "batteryActualNum", "sTitle": "电芯码实际数量"}      
	    ],
        display:"cell-border",
        "aoColumnDefs": [{
            'bSortable': false,
            'aTargets': [0]
        }]
    });
    
	$('#upload_table tbody').on( 'click', 'tr', function () {
		var rowData = table.fnGetData(this);
		if(rowData.packTheoryNum != packTheoryNum.packActualNum || rowData.moduleTheoryNum!= rowData.moduleActualNum || rowData.batteryTheoryNum != rowData.batteryActualNum)
		{
			alert('条码数量不正确，不能选择');
			return;
		}
        $(this).toggleClass('selectedtr');
    } );
    jQuery('#upload_table_wrapper .dataTables_filter input').addClass("form-control"); // modify table search input
    jQuery('#upload_table_wrapper .dataTables_length select').addClass("form-control"); // modify table per page dropdown
}

//创建多列选择框
function createAutoComplete()
{
	var deliveryData = $("#deliveryCode").tautocomplete({
		columns: ['id','单据号', '单据行项目号', '物料编码', '物料描述', '客户编码', '客户描述','发货单数量','关联条码数量'],
		ajax: {
            url: context+"uploadInfo/getdeliverystable",
            contentType:"application/x-www-form-urlencoded",
            type: 'POST',
            data: function () {
            	var plant = $('#selectPlant').val();
                return { plant: plant,delivery: deliveryData.searchdata() };
            },
            success: function (data) {
            	if('S' != data.status)
   				{
    				alert(data.msg);
   				}
    			else
    			{
    				var info = JSON.parse(data.data);
    				var filterData = [];

                    var searchData = eval("/" + deliveryData.searchdata() + "/gi");

                    $.each(info, function (i, v) {
                        if (v.vbillcode.search(new RegExp(searchData)) != -1) {
                            filterData.push(v);
                        }
                    });
                    return filterData;
    			}
            }
        },
        onchange: function () {
        	var obj = deliveryData.all();
        	$('#rowNum').val(obj.单据行项目号);
        	$('#custInfo').val(obj.客户描述+'('+obj.客户编码+')');
        	$('#itemCode').val(obj.物料编码);
        	$('#itemDesc').val(obj.物料描述);
        	$('#delinum').val(obj.发货单数量);
        	$('#barcodenum').val(obj.关联条码数量);
        }
	});
	
}

//建立查询web概要信息按钮
function createMEQueryBtn()
{
	$('#queryMesNumBtn').click(function() {
		var itemCode = $('#itemCode').val();
		var plant = $('#selectPlant').val();
		if(plant == null || plant == '')
		{
			alert('请先选择站点');
			return ;
		}
		if(itemCode == null || itemCode == '')
		{
			alert('请先选择一个单据号进行填充');
			return;
		}
		var paramStr = {plant:plant,itemCode:itemCode};
		$.ajax({
			url: context+"uploadInfo/getNoSendBtsPack",
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
    				var dataTable_re = $('#upload_table').dataTable();
    				dataTable_re.fnClearTable();
    				var resultData = res.data;
    				dataTable_re.fnAddData(resultData);
   				}
    		}
		});	
	});
	
}
