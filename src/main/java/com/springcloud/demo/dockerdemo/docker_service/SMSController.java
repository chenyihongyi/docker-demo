package com.springcloud.demo.dockerdemo.docker_service;

import com.alibaba.fastjson.JSON;
import com.demo.utils.PropertiesUtil;
import com.demo.web.ErrorInfoEnum;
import com.demo.web.TopController;
import com.demo.web.resp.Resp;
import com.demo.request.SmsSendRequest;
import com.demo.sms.model.response.SmsSendResponse;
import com.demo.sms.util.ChuangLanSmsUtil;
import com.demo.swagger.annotations.Api;

import com.demo.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;

/**
 * @Author: Elvis
 * @Description:
 * @Date: 2019/11/13 21:46
 */
@Api(value = "注册短信发送接口", description = "注册操作短信发送", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class SMSController extends TopController {

    private Logger logger = LoggerFactory.getLogger(SMSController.class);

    private static Properties properties = null;
    private static String account = null;
    private static String password = null;
    private static String smsSingleRequestServerUrl = null;

    // 加载
    static {
        properties = PropertiesUtil.loadProperties("spring/sms.properties");
        account = properties.getProperty("sms.account");
        password = properties.getProperty("sms.password");
        smsSingleRequestServerUrl = properties.getProperty("sms.url");
    }

    @ApiOperation(value = "短信发送接口", notes = "输入字段：phone（手机号），auth(随机数),password（密码），introduce_m_id（介绍人ID）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "m/sendsms", method = RequestMethod.POST)
    @ResponseBody
    public Resp SMSSender(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession httpSession,
                          String phone,
                          String email) {
        int auth=(int)((Math.random()*9+1)*1000);
        httpSession.setAttribute("auth", auth);
        httpSession.setAttribute("phone", phone);
        int state = smsPhone(phone,auth);
        if(state>0){
            return new Resp(Resp.FAIL, ErrorInfoEnum.LANG_MAIL_NULL_TIP.getErrorENMsg());
        }
         return new Resp(Resp.SUCCESS, Resp.SUCCESS_MSG);
    }

      private int smsPhone(String phone,int auth) {
          // 短信内容
          String msg = "【公司名】您的验证码是"+auth+"，3分钟之内有效！";

          //状态报告
          String report= "true";
          SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, msg, phone,report);
          String requestJson = JSON.toJSONString(smsSingleRequest);
          String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
          SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
          return Integer.valueOf(smsSingleResponse.getCode());
      }

}
