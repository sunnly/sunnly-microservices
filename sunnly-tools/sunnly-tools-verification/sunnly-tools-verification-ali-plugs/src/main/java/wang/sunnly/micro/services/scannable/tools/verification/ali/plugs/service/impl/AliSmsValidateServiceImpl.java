package wang.sunnly.micro.services.scannable.tools.verification.ali.plugs.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.ServletWebRequest;
import wang.sunnly.micro.services.scannable.tools.verification.core.service.SmsValidateService;

import java.util.HashMap;

/**
 * @author Sunnly
 * @ClassName AliSmsValidateServiceImpl
 **/
public class AliSmsValidateServiceImpl implements SmsValidateService {
    Logger logger = LoggerFactory.getLogger(getClass());
    private static final String SMS_SEND_SUCCESS = "OK";
    private static final String SMS_SEND_CODE = "Code";

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.sms.accessSecret}")
    private String accessSecret;
    @Value("${aliyun.sms.domain:dysmsapi.aliyuncs.com}")
    private String domain;
    @Value("${aliyun.sms.signName}")
    private String signName;
    @Value("${aliyun.sms.templateCode}")
    private String templateCode;

    @Override
    public boolean sender(ServletWebRequest request, String phone, String code) {
        logger.info(String.format("向手机用户【%s】发送短信验证码：%s", phone, code));

        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest aliRequest = new CommonRequest();
        aliRequest.setMethod(MethodType.POST);
        aliRequest.setDomain(domain);
        aliRequest.setVersion("2017-05-25");
        aliRequest.setAction("SendSms");
        aliRequest.putQueryParameter("RegionId", "default");
        aliRequest.putQueryParameter("SignName", signName);
        aliRequest.putQueryParameter("TemplateCode", templateCode);
        aliRequest.putQueryParameter("TemplateParam", String.format("{\"code\": \"%s\"}", code));
        aliRequest.putQueryParameter("PhoneNumbers", phone);
        try {
            CommonResponse aliResponse = client.getCommonResponse(aliRequest);
            HashMap<String, String> data = new Gson().fromJson(aliResponse.getData(),
                    new TypeToken<HashMap<String, String>>() {
                    }.getType());
            if (SMS_SEND_SUCCESS.equals(data.get(SMS_SEND_CODE))) {
                return true;
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
