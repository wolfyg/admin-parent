/**
 * 工具类
 * auth:yg
 * date:2018/4/4
 */
Utils={
		GeoLocation:function geolocation(tag,callback){//定位
				  var map, geolocation;
				    //加载地图，调用浏览器定位服务
				    map = new AMap.Map('container', {
				        resizeEnable: true
				    });
				    map.plugin('AMap.Geolocation', function() {
				        geolocation = new AMap.Geolocation({
				            enableHighAccuracy: true,//是否使用高精度定位，默认:true
				            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
				            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
				            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
				            buttonPosition:'RB'
				        });
				        map.addControl(geolocation);
				        geolocation.getCurrentPosition();
				        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
				        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
				    });
				    //解析定位结果
				    function onComplete(data) {
				        $(tag).attr('data-province',data.addressComponent.province);
				        $(tag).attr('data-city',data.addressComponent.city);
				        $(tag).attr('data-town',data.addressComponent.district);
				        $(tag).attr('data-lng',data.position.lng);
				        $(tag).attr('data-lat',data.position.lat);
				        if(callback!=null){
				        	callback(data);
				        }
				    }
				    //解析定位错误信息
				    function onError(data) {
				        $(tag).attr('data-province','未知省份');
				        $(tag).attr('data-city','定位失败');
				        $(tag).attr('data-town','未知区域');
				        $(tag).attr('data-lng',0);
				        $(tag).attr('data-lat',0);
				    }
			},
			templateEngine:function templateEngine(html, options) {//html模板解析器
		        var re = /<%([^%>]+)?%>/g,
		            reExp = /(^( )?(if|for|else|switch|case|formatDate|break|{|}))(.*)?/g,
		            code = 'var r=[];\n',
		            cursor = 0;
		        var add = function(line, js) {
		            js ? (code += line.match(reExp) ? line + '\n' : 'r.push(' + line + ');\n') :
		                (code += line != '' ? 'r.push("' + line.replace(/"/g, '\\"') + '");\n' : '');
		            return add;
		        }
		        while (match = re.exec(html)) {
		            add(html.slice(cursor, match.index))(match[1], true);
		            cursor = match.index + match[0].length;
		        }
		        add(html.substr(cursor, html.length - cursor));
		        code += 'return r.join("");';
		        return new Function(code.replace(/[\r\t\n]/g, '')).apply(options);
		},
		adv:function(id){//广告渲染
			$.ajax({
				type:'POST',
				data:{
					id:id,
				},
				url:'http://xx.xx.xx/xx/xx/xx/xx',
		        datatype: "JSON",
				success:function(d){
					console.log('加载页面广告',d)
					var html=Utils.templateEngine($('#adv').html(),d);
					$('#js_adv').html(html);
					//轮播图
					Utils.swiperScroll();
				}
			})
		},
		swiperScroll:function(){//轮播图滚动
			var swiper = new Swiper('.swiper-container', {
		            init: false,
		            autoplay: true,
		            loop: true,
		            pagination: {
		                el: '.swiper-pagination',
		            }
		        });
	        setTimeout(function () {
	            swiper.init();
	        }, 0);
		},
		weather:function(){//天气预报
			$.ajax({
				type:'POST',
				data:'params={"city" : "蚌埠市"}',
				url:'http://xx.xx.xx/xx/xx/xx',
		        datatype: "JSON",
				success:function(d){
					console.log('加载天气',d)
					var text='';
					$.each(d.data,function(index,item){
						if(index==0){
							text='今天：'+item.week+'，'+item.weather+'，温度：'+item.temperature+'，'+item.wind+'，'+item.xczs+'；'
						}else{
							text+=item.rq+'，'+item.week+'，'+item.weather+'，温度：'+item.temperature+'，'+item.wind+'；'
						}
					})
					$('.c-Weather-text').html(text);
				}
			})
		},
		formatTime:function (fmt) { 
			var date = new Date(fmt);
		  	var nian =	date.getFullYear()+'';
			var yue = (date.getMonth() + 1)<10?'0'+(date.getMonth() + 1):(date.getMonth() + 1); //月份 
			var ri = date.getDate()<10?'0'+date.getDate():date.getDate(); //日 
			var shi = date.getHours()<10?'0'+date.getHours():date.getHours(); //小时 
			var fen = date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes(); //分 
			var t =nian+"-"+yue+"-"+ri+" "+shi+":"+fen;
		    return t ;
		}

}