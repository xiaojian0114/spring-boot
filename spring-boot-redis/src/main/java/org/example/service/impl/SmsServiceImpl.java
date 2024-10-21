package org.example.service.impl;


import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.CloopenConfig;
import org.example.config.RedisCache;
import org.example.config.RedisKeys;
import org.example.enums.ErrorCode;
import org.example.exception.ServerException;
import org.example.service.SmsService;
import org.example.utils.CommonUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {


    private  final CloopenConfig cloopenConfig;
    private  final RedisCache redisCache;


    @Override
    public void sendSms(String phone){
        int code = CommonUtils.generateCode();
        redisCache.set(RedisKeys.getSmsKey(phone), code);
        boolean flag = send(phone,code);
        if(flag){
            log.info("短信发送成功:{}",code);
        }else {
            log.info("发送失败");
        }

    }

    private boolean send(String phone, int code) {
        try {
            log.info(" ============= 创建短信发送通道中 ============= \nphone is {},code is {}", phone, code);
            String serverIp = cloopenConfig.getServerIp();
            // 请求端口
            String serverPort = cloopenConfig.getPort();
            // 主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
            String accountSId = cloopenConfig.getAccountSId();
            String accountToken = cloopenConfig.getAccountToken();
            // 请使用管理控制台中已创建应用的APPID
            String appId = cloopenConfig.getAppId();
            //创建了一个客户端对象
            CCPRestSmsSDK sdk = new CCPRestSmsSDK();
            //初始化参数
            sdk.init(serverIp, serverPort);
            sdk.setAccount(accountSId, accountToken);
            sdk.setAppId(appId);
            sdk.setBodyType(BodyType.Type_JSON);
            //获取了短信模板
            String templateId = cloopenConfig.getTemplateId();
            String[] datas = {String.valueOf(code), "1"};
            //通过 sendTemplateSMS 方法发送短信
            HashMap<String, Object> result = sdk.sendTemplateSMS(phone, templateId, datas, "1234", UUID.randomUUID().toString());
            if ("000000".equals(result.get("statusCode"))) {
                // 正常返回输出data包体信息（map）
                HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for (String key : keySet) {
                    Object object = data.get(key);
                    log.info("{} = {}", key, object);
                }
            } else {
                // 异常返回输出错误码和错误信息
                log.error("错误码={} 错误信息= {}", result.get("statusCode"), result.get("statusMsg"));
                throw new ServerException(ErrorCode.CODE_SEND_FAIL);
            }
        } catch (Exception e) {
            throw new ServerException(ErrorCode.CODE_SEND_FAIL);
        }
        return true;
    }

}
