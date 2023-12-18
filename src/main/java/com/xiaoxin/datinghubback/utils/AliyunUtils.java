package com.xiaoxin.datinghubback.utils;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import com.aliyun.broadscope.bailian.sdk.AccessTokenClient;
import com.aliyun.broadscope.bailian.sdk.ApplicationClient;
import com.aliyun.broadscope.bailian.sdk.models.BaiLianConfig;
import com.aliyun.broadscope.bailian.sdk.models.CompletionsRequest;
import com.aliyun.broadscope.bailian.sdk.models.CompletionsResponse;
import java.lang.reflect.Type;
import com.alibaba.fastjson.JSON;
import com.aliyun.broadscope.bailian.sdk.ApplicationClient;
/**
 * @author:XIAOXIN
 * @date:2023/11/23
 **/
public class AliyunUtils {
    public static String ai(String prompts) {
        String accessKeyId = "LTAI5tSHVLPN6MZ8FMKMLKN1";
        String accessKeySecret = "HMXfhrI1C2BEZ59DKjwcNvS28ByXpm";
        String agentKey = "241123e8aa9c4a71843efac2293534f3_p_efm";

        AccessTokenClient accessTokenClient = new AccessTokenClient(accessKeyId, accessKeySecret, agentKey);
        String token = accessTokenClient.getToken();
        BaiLianConfig config = new BaiLianConfig()
                .setApiKey(token);
        String appId = "45a7fb012f824a5f9698dec3dd9035c3";
        CompletionsRequest request = new CompletionsRequest()
                .setAppId(appId)
                .setPrompt(prompts);

        ApplicationClient client = new ApplicationClient(config);
        CompletionsResponse response = client.completions(request);
        return response.getData().getText();
    }


}
