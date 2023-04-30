package com.zhiqi.common.utils.http;

import com.zhiqi.common.filter.RepeatableReadRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author RyuJung
 * @since 2023/4/30-23:04
 */
public class HttpHelper {

    private static final Logger log = LoggerFactory.getLogger(HttpHelper.class);

    public static String getRequestBodyString(RepeatableReadRequestWrapper requestWrapper) {
        StringBuilder sb = new StringBuilder();
        try (
                ServletInputStream inputStream = requestWrapper.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            String lineStr;
            while ((lineStr = bufferedReader.readLine()) != null) {
                sb.append(lineStr);
            }
        } catch (IOException e) {
            log.warn("读取请求体字符串异常！原因：{}", e.getMessage());
        }

        return null;
    }
}
