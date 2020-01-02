<template>
	<view class="content">
		11111
	</view>
</template>

<script>
	var config;
	var wx = require('@/common/jweixin-module')
	export default {
		data() {
			return {
				api: 'http://12323.51vip.biz',
			}
		},
		created() {
			let origin = window.location.origin;
			let _href = window.location.href;
			if(_href.indexOf('index/index') <= -1){
				window.location.replace(origin + '/h5/pages/index/index')
			}
		},
		onLoad() {
			this.getSign();
		},
		methods: {
			getSign(){
				//console.log(window.location.href.split('#')[0]);
				let that = this;
				let config = {};
				config.url = window.location.href;
				uni.request({
					url: that.api+'/wechat/config',
					method: 'GET',
					data: {
						pageUrl: window.location.href.split('#')[0]
					},
					success: res => {
						config = res.data
						config.debug = true;
						config.jsApiList = ['hideMenuItems','getNetworkType'];
						wx.config(config);
						// ready 页面加载完成后执行
						wx.ready(function(){
							wx.hideMenuItems({
								menuList: ["menuItem:share:timeline", "menuItem:copyUrl", "menuItem:share:appMessage", "menuItem:share:qq", "menuItem:share:weiboApp", "menuItem:favorite", "menuItem:share:facebook", "menuItem:share:QZone", "menuItem:editTag", "menuItem:delete", "menuItem:copyUrl", "menuItem:originPage", "menuItem:readMode", "menuItem:openWithQQBrowser", "menuItem:openWithSafari", "menuItem:share:email", "menuItem:share:brand"] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮
							});
							wx.getNetworkType({
							  success: function (res) {
								  console.log('getNetworkType :', res);
							    var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
							  }
							});
						})
					}
				});
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
