
(function($) {
    $(function() {
        $('.c-TpWp-cont').on('click.cgh', '.c-TpBox-avatar', function(e) {
        	var id=$(this).data("id");
        	$.ajax({
        	    type: 'POST',
        	    url: 'getGRSJ?id='+id,
        	    dataType: 'json',
        	    success: function (d) {
        			var html=templateEngine($("#tpl_grsj").html(), d);
                    layer.open({
                    	id:"pc",
                        type: 1,
                        skin: 'c-Layer',
                        area: '700px',
                        title: null,
                        closeBtn: false,
                        shadeClose: true,
                        content: html,
                        success: function(layero, index) {
                        	$(".c-User-detail").find("p").eq(0).after('<img src="../static/'+d.data.original+'" alt="" width="500px" height="360px"  style="margin-top:10px">')
                            $('.c-Dialog-close').on('click.cgh', function(e) {
                                layer.close(index);
                            });
                        },
                        end: function() {
                            $('.c-Dialog-close').off('click.cgh');
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


function tab(){

    var $items = $('.c-Tabs-item'),
        $targets = $('.c-TpBox');
    $items.each(function(i) {
        $(this).data('tab-id', i);
        $targets.eq(i).data('tab-id', i);
    });

    $('.c-Tabs').on('click.cgh', '.c-Tabs-item', function(e) {
        var $this = $(this);
        if (!$this.hasClass('is-cur')) {
            $this.addClass('is-cur').siblings('.c-Tabs-item').removeClass('is-cur');
            var id = $this.data('tab-id'),
                $target = null;
            $targets.each(function() {
                $target = $(this);
                if ($target.data('tab-id') === id && !$target.hasClass('is-cur')) {
                    $target.addClass('is-cur').siblings('.c-TpBox').removeClass('is-cur');
                    return false;
                }
            });
        }
    });
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
