(function($) {
    $(function() {
        fristLoadData(1);//加载首页数据
        
        $('.c-TpBox').on('click.cgh', '.c-Candidates-avatar', function(eve) {
        	var id=$(this).data("id");

        	$.ajax({
        	    type: 'POST',
        	    url: 'getGRSJ?id='+id,
        	    dataType: 'json',
        	    success: function (d) {
        			var html=templateEngine($("#tpl_tanchuang").html(), d);
                	layer.open({
                		id:"wx",
                        type: 1,
                        area: ['87vw', '90vh'],
                        title: null,
                        closeBtn: false,
                        skin: 'c-Layer',
                        shadeClose: true,
                        content: html,
                        success: function(layero, index) {
                        	$(".c-User-detail").find("p").eq(0).after('<img src="../static/'+d.data.original+'" alt="">')
                            $("html,body").css({ overflow: "hidden" });
                            $('.c-Dialog-close').on('click.cgh', function() {
                                layer.close(index);
                            });
                        },
                        end: function() {
                            $("html,body").css({ overflow: "auto" });
                        }
                    });
        	    },
        	    error: function (xhr, type) {
        	        //网络异常
        	    }
        	})
        });

    });
})(jQuery);

function firstPage(){
    var box= $('.c-Boxes-item.c-Boxes-item--sy');
	box.addClass('is-cur').siblings('.c-Boxes-item').removeClass('is-cur');
	
	var toggle=$('.c-Toggle-item.c-Toggle-item--sy');
     toggle.addClass('is-cur').siblings('.c-Toggle-item').removeClass('is-cur');
}

function tab(){
	// 首页、规则切换
    var $boxes = $('.c-Boxes-item'),
        $toggles = $('.c-Toggle-item');

    $toggles.each(function(i) {
        $(this).data('tab-id', i);
        $boxes.eq(i).data('tab-id', i);
    });

    $toggles.on('click.cgh', function(eve) {
        var $this = $(this),
            $siblings = $this.siblings('.c-Toggle-item');
        if (!$this.hasClass('is-cur')) {
            $this.addClass('is-cur');
            $siblings.removeClass('is-cur');
            var id = $this.data('tab-id');
            $boxes.each(function() {
                if ($(this).data('tab-id') === id) {
                    $(this).addClass('is-cur').siblings('.c-Boxes-item').removeClass('is-cur');
                    console.log($('html').outerHeight());
                    $('body').scrollTop($('html').outerHeight());
                    return false;
                }
            });
        }
    });

    // 称号页切换
    var $tabs = $('.c-Tabs-item'),
        $tpboxes = $('.c-TpBox-item');

    $tabs.each(function(i) {
        $(this).data('tab-id', i);
        $tpboxes.eq(i).data('tab-id', i);
    });
    $tabs.on('click.cgh', function(eve) {
        var $this = $(this),
            $siblings = $this.siblings('.c-Tabs-item')
            type=$this.data("type");
        loadData(type);
        if (!$this.hasClass('is-cur')) {
            $this.addClass('is-cur');
            $siblings.removeClass('is-cur');
            var id = $this.data('tab-id');
            $tpboxes.each(function() {
                if ($(this).data('tab-id') === id) {
                    $(this).addClass('is-cur').siblings('.c-TpBox-item').removeClass('is-cur');

                    $('body').scrollTop($('html').outerHeight());
                    return false;
                }
            });
        }
    });
}

//根据类型加载数据
function loadData(type){
	$.ajax({
		   type: 'POST',
		   url: 'getDataByType',
		   data: {
			   type:type
		   },
		   dataType: 'json',
		   success: function (d) {
				var record=d.data.voteRecord;
				$("#totalVote").html(d.data.totalVote);
				$("#votePeopleNum").html(d.data.votePeopleNum);
				var html=templateEngine($("#tpl_tab").html(), d);
				$(".c-TpBox").html(html);
				$.each(record,function(i,item){
					$("#btn"+item.dataid).css("backgroundColor","#C1A38D");
					$("#btn"+item.dataid).removeAttr("onclick");
					$("#btn"+item.dataid).html("已投");
				})
		   	},error: function (xhr, type) {
		   		layer.msg("网络异常请稍后");
		 	}
	})
}

//根据类型加载数据
function fristLoadData(type){
	$.ajax({
		   type: 'POST',
		   url: 'getDataByType',
		   data: {
			   type:type
		   },
		   dataType: 'json',
		   success: function (d) {
				var record=d.data.voteRecord;
				$("#totalVote").html(d.data.totalVote);
				$("#votePeopleNum").html(d.data.votePeopleNum);
			   var html=templateEngine($("#tpl_tab").html(), d);
			   $(".c-TpBox").html(html);

				$.each(record,function(i,item){
					$("#btn"+item.dataid).css("backgroundColor","#C1A38D");
					$("#btn"+item.dataid).removeAttr("onclick");
					$("#btn"+item.dataid).html("已投");
				})
				if(d.isStartVote==="notstart"){
					$(".c-Djs").html('<div style="text-align: center; width: 100%; margin-left: -4.7vw;">活动还未开始</div>');
				}else if(d.isStartVote==="endstart"){
					$(".c-Djs").html('<div style="text-align: center; width: 100%; margin-left: -4.7vw;">活动已经结束</div>');
				}else if(d.isStartVote==="ingstart"){
					setInterval(function() {
						GetRTime(d.msg);
					}, 1000);
				}
				
		   	},error: function (xhr, type) {
		   		layer.msg("网络异常请稍后");
		 	}
	})
}

//模板解析器
function templateEngine(html, options) {
    var re = /<#([^#>]+)?#>/g,
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
}

test = {
		GetRTime: function (type) {
            console.log(type);
        }
    };

function GetRTime(time){    
	var EndTime= new Date(time);
	var NowTime = new Date();
	var t =EndTime.getTime() - NowTime.getTime();
    var d=0;
    var h=0;
    var m=0;
    var s=0;
    if(t>=0){
      d=Math.floor(t/1000/60/60/24)+'';
      h=Math.floor(t/1000/60/60%24)+'';
      m=Math.floor(t/1000/60%60)+'';
      s=Math.floor(t/1000%60)+'';
    }

    if(d<10){
    	d="0"+d;
    }if(h<10){
    	h="0"+h;
    }if(m<10){
    	m="0"+m
    }if(s<10){
    	s="0"+s;
    }
    var time=d+h+m+s;
    var timeArr=time.split('');
    
    var timeHtml='<div class="c-Djs-time" data-text="天">'+
        '<span class="c-Djs-num">'+timeArr[0]+'</span>'+
        '<span class="c-Djs-num">'+timeArr[1]+'</span>'+
    '</div>'+
    '<div class="c-Djs-time" data-text=":">'+
        '<span class="c-Djs-num">'+timeArr[2]+'</span>'+
        '<span class="c-Djs-num">'+timeArr[3]+'</span>'+
    '</div>'+
    '<div class="c-Djs-time" data-text=":">'+
        '<span class="c-Djs-num">'+timeArr[4]+'</span>'+
        '<span class="c-Djs-num">'+timeArr[5]+'</span>'+
    '</div>'+
    '<div class="c-Djs-time">'+
        '<span class="c-Djs-num">'+timeArr[6]+'</span>'+
        '<span class="c-Djs-num">'+timeArr[7]+'</span>'+
    '</div>';
    $(".c-Djs").html(timeHtml);
  }



function startTime(Millisecond){
	var _self = this,
	interval = 1000,
	count = 0,
	ms=Millisecond,
	startTime = new Date().getTime();

	if (ms >= 0) {
		var timeCounter = setTimeout(function(){
			countDownStart(startTime,count,interval,ms);
		}, interval);
	}
}
function countDownStart(startTime,count,interval,ms) {
    count++;
    var offset = new Date().getTime() - (startTime + count * interval);
    var nextTime = interval - offset;
    if (nextTime < 0) {
        nextTime = 0
    };
    ms -= interval;
    if (ms < 0) {
        clearTimeout(timeCounter);
    } else {
        var d=0;
        var h=0;
        var m=0;
        var s=0;
        if(ms>=0){
          d=Math.floor(ms/1000/60/60/24);
          h=Math.floor(ms/1000/60/60%24);
          m=Math.floor(ms/1000/60%60);
          s=Math.floor(ms/1000%60);
        }

        if(d<9){
        	d="0"+d;
        }if(h<9){
        	h="0"+h;
        }if(m<9){
        	m="0"+m
        }if(s<9){
        	s="0"+s;
        }
        var time=d+h+m+s;
        var timeArr=time.split('');
        
        var timeHtml='<div class="c-Djs-time" data-text="天">'+
            '<span class="c-Djs-num">'+timeArr[0]+'</span>'+
            '<span class="c-Djs-num">'+timeArr[1]+'</span>'+
        '</div>'+
        '<div class="c-Djs-time" data-text=":">'+
            '<span class="c-Djs-num">'+timeArr[2]+'</span>'+
            '<span class="c-Djs-num">'+timeArr[3]+'</span>'+
        '</div>'+
        '<div class="c-Djs-time" data-text=":">'+
            '<span class="c-Djs-num">'+timeArr[4]+'</span>'+
            '<span class="c-Djs-num">'+timeArr[5]+'</span>'+
        '</div>'+
        '<div class="c-Djs-time">'+
            '<span class="c-Djs-num">'+timeArr[6]+'</span>'+
            '<span class="c-Djs-num">'+timeArr[7]+'</span>'+
        '</div>';
        
        $(".c-Djs").html(timeHtml);
        console.log(timeHtml);
        timeCounter = setTimeout(countDownStart(startTime,count,interval,ms), nextTime);
    }
}
