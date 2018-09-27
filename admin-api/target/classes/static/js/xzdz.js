$(function () {
    main();
    function main() {

        // 初始化地图
        var ret = initMap();

        // 监听地图移动事件
        AMap.event.addListener(ret.map, 'moveend', function (e) {
            getPos(ret.map, ret.geocoder);
        });

        // 绑定事件返回原先页面
        var $btn = $('#js_btn');
        $btn.one('click.cgh', function (e) {
            e.stopPropagation();
            var $this = $(this),
            	data=JSON.parse(getAddr());
            console.log("默认定位",data);
            var addr = parent.document.getElementById('addr');
            var lng = parent.document.getElementById('lng');
            var lat = parent.document.getElementById('lat');
            var province = parent.document.getElementById('province');
            var city = parent.document.getElementById('city');
            var area = parent.document.getElementById('area');
            var js_locationText = parent.document.getElementById('js_locationText');
            addr.value=data.formattedAddress;
            lng.value=data.position.lng;
            lat.value=data.position.lat;
            province.value=data.addressComponent.province;
            city.value=data.addressComponent.city;
            area.value=data.addressComponent.district;
            js_locationText.innerHTML=data.formattedAddress;
            //关闭窗口
            parent.layer.closeAll();
        });
    }

    function initMap() {
        // 默认定位点在蚌埠
        var defaultPos = {
            addressComponent: {
                province: '安徽省',
                city: '蚌埠市',
                district: '蚌山区'
            },
            position: {
                lng: 117.363228,
                lat: 32.939667
            },
            formattedAddress: '安徽省蚌埠市蚌山区青年街道胜利中路89号瀚城酒店(百合店)'
        };

        var pos = window.sessionStorage && window.sessionStorage.getItem('_cgh_pos') ? JSON.parse(window.sessionStorage.getItem('_cgh_pos')) : defaultPos;

        // 保存到本地
        savePos(pos);

        var map = new AMap.Map('container', {
            // 监控地图容器尺寸变化
            resizeEnable: true,
            // 移动端，最大可以放大到19
            zoom: 17,
            center: [pos.position.lng, pos.position.lat]
        });

        // 加载地理编码插件
        var geocoder = new AMap.Geocoder();
        map.plugin(geocoder);

        var $address = $('#js_address');
        $address.html(pos.formattedAddress);

        // 暴露对象供getPos使用
        return {
            map: map,
            geocoder: geocoder
        };
    }

    function getPos(map, geocoder) {
        var center = map.getCenter();
        var $address = $('#js_address');
        var $btn = $('#js_btn');

        // 定位期间，返回按钮不可点击
        $btn.off('click.cgh');
        $address.append('<img src="static/img/loading.gif"/>');

        geocoder.getAddress([center.lng, center.lat], function (status, result) {

            if (status === 'complete' && result.info === 'OK') {

                var data = result.regeocode;

                var pos = {
                    addressComponent: {
                        province: data.addressComponent.province,
                        city: data.addressComponent.city,
                        district: data.addressComponent.district
                    },
                    position: {
                        lng: center.lng,
                        lat: center.lat
                    },
                    formattedAddress: data.formattedAddress
                };

                $address.html(pos.formattedAddress);

                // 保存地址到本地
                savePos(pos);

                // 重新绑定返回事件
                $btn.one('click.cgh', function (e) {
                    e.stopPropagation();
                    var $this = $(this),
                		data=JSON.parse(getAddr());
                    console.log("重新选址",data);
                    var addr = parent.document.getElementById('addr');
                    var lng = parent.document.getElementById('lng');
                    var lat = parent.document.getElementById('lat');
                    var province = parent.document.getElementById('province');
                    var city = parent.document.getElementById('city');
                    var area = parent.document.getElementById('area');
                    var js_locationText = parent.document.getElementById('js_locationText');
                    addr.value=data.formattedAddress;
                    lng.value=data.position.lng;
                    lat.value=data.position.lat;
                    province.value=data.addressComponent.province;
                    city.value=data.addressComponent.city;
                    area.value=data.addressComponent.district;
                    js_locationText.innerHTML=data.formattedAddress;
                    
                    //关闭窗口
                    parent.layer.closeAll();
                });
            }
        });
    }

    function savePos(pos) {
        if (window.sessionStorage) {
            window.sessionStorage.setItem('_cgh_pos', JSON.stringify(pos));
        }
    }

    function getAddr() {
        if (window.sessionStorage) {
            return window.sessionStorage.getItem('_cgh_pos');
        }
    }
});