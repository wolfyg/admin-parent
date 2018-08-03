/**
 * @Author yg
 */
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    
    //自定义选择器插件
    (function($) {
    	$.extend($.expr[":"], {
    		//选择器对象
    		//a:htmlDom对象，i:htmlDom对象下标，m。
    		validInput : function ( a, i, m) {
    			if(a.value != "" && a.value != undefined)
    				return true;
    		}
    	});
    })(jQuery);
})
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#dataTable').bootstrapTable({
            url: dataUrl,     					//请求后台的URL（*）
            method: 'get',           			//请求方式（*）
            toolbar: '#tableToolbar',        	//工具按钮用哪个容器
            striped: true,           			//是否显示行间隔色
            cache: false,            			//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,          			//是否显示分页（*）
            sortable: false,           			//是否启用排序
            sortOrder: "asc",          			//排序方式
            escape:true,                		//转义HTML字符串，替换 &, <, >, ", `, 和 ' 字符.
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",      		//分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,            			//初始化加载第一页，默认第一页
            pageSize: 10,            			//每页的记录行数（*）
            pageList: [5,10, 25, 50, 100],    	//可供选择的每页的行数（*）
            showColumns: true,                  //是否显示所有的列
            showRefresh:true,                   //显示刷新
            strictSearch: true,
            clickToSelect: true,        		//是否启用点击选中行
            uniqueId: "id",           			//每一行的唯一标识，一般为主键列
            cardView: false,          			//是否显示详细视图
            detailView: false,          		//是否显示父子表
            showExport: true,                   //是否显示导出
            exportDataType: "basic",            //basic', 'all', 'selected'.
            columns: dataColumns,
            iconSize: "outline",
            icons: {
                refresh: "glyphicon-repeat",
                toggle: "glyphicon-list-alt",
                columns: "glyphicon-list"
            }
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        return dataQueryParams(params);
    };
    
    return oTableInit;
};

var tableList = "#dataTable";
function btn_add(title,url){
	openUrl(title,url,null);
}
function btn_edit(title,url){
    var arrselections = $(tableList).bootstrapTable('getSelections');
    if (arrselections.length > 1) {
    	layer.msg("只能编辑一条数据", {icon: 1});
        return;
    }
    if (arrselections.length <= 0) {
    	layer.msg('请选择一条数据',{icon: 5});
        return;
    }
    var id = arrselections[0].id;
    openUrl(title,url,id);
}


function btn_delete(url){
    var arrselections = $(tableList).bootstrapTable('getSelections');
    if (arrselections.length <= 0) {
    	layer.msg('请选择一条数据',{icon: 5});
        return;
    }
    var id=[];
    $.each(arrselections,function(index,item){
    	id.push(item.id);
    })
    layer.confirm('确定删除吗？', {
        btn: ['确定', '取消'] //按钮
     }, function () {
    	 $.ajax({
    	     type: "POST",
    	     url: BaseUrl+url+"?ids="+id.join(","),
    	     datatype: "json",
    	     success: function (data) {
    	    	 var bool=stateUtil(data);
    	    	 if(bool){
    	    		 $('#dataTable').bootstrapTable('refresh');
    	    	 }
    	     }
    	 });
     }, function () {
       return;
    });    
}


function openUrl(title,url,id){
	if(id!=null){
		url+="?id="+id;
	}
	layer.open({
        title:title,
        type: 2,
        area: ['700px', '530px'],
        fix: false, //不固定
        maxmin: true,
        content: BaseUrl+url
    });
}
