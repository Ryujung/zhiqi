package com.zhiqi.common.utils.file;

import com.zhiqi.common.config.ZhiQiConfig;
import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.utils.StringUtils;

import java.io.File;

/**
 * @author RyuJung
 * @since 2023/4/21-17:54
 */
public class FileUploadUtils {

    public static File getAbsoluteFile(String uploadDir, String fileName) {
        File file = new File(uploadDir + File.separator + fileName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static String getPathFileName(String uploadDir, String fileName) {
        int dirLastIndex = ZhiQiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        return pathFileName;
    }
}
