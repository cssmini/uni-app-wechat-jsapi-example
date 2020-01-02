package com.neo.web;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**

 * @author: hxl
 * @create: 2019-12-31 11:15
 **/
@Controller
@RequestMapping("/wechat")
@CrossOrigin
public class WeChatController {

    @Autowired
    WxMpService wxMpService;

    @Value("${projectUrl.url}")
    private String url;

    @Value("${projectUrl.wechatMpAuthorize}")
    private String wechatMpAuthorize;
    
    @RequestMapping("/config")
    @ResponseBody
    public Object createConfigData(String pageUrl) throws Exception {
        WxJsapiSignature signature = wxMpService.createJsapiSignature(pageUrl);
        Map<String, String> config = new HashMap<>(16);
        config.put("appId", signature.getAppId());
        config.put("nonceStr", signature.getNonceStr());
        config.put("timestamp", signature.getTimestamp() + "");
        config.put("signature", signature.getSignature());
        return config;
    }
}
