/**
 * 日期格式化
 * @param time
 * @returns {*}
 */
function formatDate(time) {
    if (time == '' || time == null || time == undefined) return '';
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

/**
 * 日期格式化模板
 * @param time: 要操作的时间
 * 		  type: 1 样式为"2016-09-05 00:00"
 * @returns {*}
 */
function formatDate(time,type) {
    if (time == '' || time == null || time == undefined) return '';
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    if(type == 1){
    	return year + "-" + month + "-" + date + " " + hour + ":" + minute;
    }else if(type == 2){
    	return year + "-" + month + "-" + date;
    }else{
    	return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
    }
}

function stateUtil(obj){
	if(typeof obj=='string'){
		obj = JSON.parse(obj);
	}
	if(obj.result===-1){
		layer.msg(obj.msg, {icon: obj.icon});
		setTimeout(function(){
			location.href=BaseUrl+"/manger/main";
		},1000)
		return false;
	}else if(obj.result===1){
		layer.msg(obj.msg, {icon: obj.icon});
		return true;
	}else{
		setTimeout(function(){
			layer.msg(obj.msg, {icon: obj.icon});
		},500)
		return false;
	}
}

/**
 * 默认单选框选中 适合 数据为 1 0
 * @param b
 * @param name
 * @returns
 */
function fillRadio(b, name) {
    if(b) {
        $(":radio[name='"+name+"'][value='1']").iCheck('check');
    }else{
        $(":radio[name='"+name+"'][value='0']").iCheck('check');
    }
}

/**
 * 下拉框选中
 * @param id
 * @param data
 * @returns
 */
function fillSelect(id,data){
	$("#"+id+" option[value='"+data+"']").attr("selected","selected");
}

/**
 * 单选框选中
 * @param name
 * @param data
 * @returns
 */
function radioCheck(name,data){
	$(":radio[name='"+name+"'][value='"+data+"']").iCheck('check');
}
