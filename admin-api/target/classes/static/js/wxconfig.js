		function WeChat(url,title,img,desc){
		 $.get("sign",{url:window.location.href},function(data) {
			 wx.config({
				debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId: data.appid, // 必填，公众号的唯一标识
				timestamp: data.timestamp, // 必填，生成签名的时间戳
				nonceStr: data.nonceStr, // 必填，生成签名的随机串
				signature: data.signature,// 必填，签名，见附录1
				jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareQZone'] 
			});
			wx.ready(function () {
			// 分享朋友圈
			wx.onMenuShareTimeline({
				title: title, // 分享标题
				link: url, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: img, // 分享图标
				success: function () { 
					// 用户确认分享后执行的回调函数
				},
				cancel: function () { 
					// 用户取消分享后执行的回调函数
				}
			});
			//分享朋友
			wx.onMenuShareAppMessage({
				title: title, // 分享标题
				desc: desc, // 分享描述
				link: url, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: img, // 分享图标
				type: '', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function () { 
					// 用户确认分享后执行的回调函数
				},
				cancel: function () { 
					// 用户取消分享后执行的回调函数
				}
			});
			//分享到qq
			wx.onMenuShareQQ({
				title: title, // 分享标题
				desc: desc, // 分享描述
				link: url, // 分享链接
				imgUrl: img, // 分享图标
				success: function () { 
				   // 用户确认分享后执行的回调函数
				},
				cancel: function () { 
				   // 用户取消分享后执行的回调函数
				}
			});
			//分享到qq空间
			wx.onMenuShareQZone({
				title: title, // 分享标题
				desc: desc, // 分享描述
				link: url, // 分享链接
				imgUrl: img, // 分享图标
				success: function () { 
				   // 用户确认分享后执行的回调函数
				},
				cancel: function () { 
					// 用户取消分享后执行的回调函数
				}
			});
			wx.error(function (res) {
	 
			alert(res.errMsg);  //打印错误消息。及把 debug:false,设置为debug:ture就可以直接在网页上看到弹出的错误提示
	 
			});
			});
		},
		"json"
		);
	}
	