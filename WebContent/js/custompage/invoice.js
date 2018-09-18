/**
 * 
 */

function syncDataList()
{
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
            dataType: 'json',
            cache: false,
            success : function(res){
    			if('S' != res.status)
   				{
    				alert(res.msg);
   				}
    			else
   				{
    				var resultData = res.data;
    				$('#invoice_table').DataTable({
    				    data: resultData,
    				    columns: [
    				        { data: "vbillcode" },
    				        { data: "billtypename" },
    				        { data: "vbilldate" },
    				        { data: "vbilldtype" },
    				        { data: "vbilldpname" },
    				        { data: "vbillvdef2" },
    				        { data: "vbillrowno" },
    				        { data: "cuscode" },
    				        { data: "cusdescription" },
    				        { data: "material" },
    				        { data: "matdescription" },
    				        { data: "vbilldnum" },
    				        { data: "vbillduname" },
    				        { data: "status" },       
    				    ]
    				});
    				
   				}
    		}
		});
		
		
		
	});
}