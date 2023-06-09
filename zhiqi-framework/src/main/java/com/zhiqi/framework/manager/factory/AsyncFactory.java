package com.zhiqi.framework.manager.factory;

import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.utils.ServletUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.common.utils.ip.AddressUtils;
import com.zhiqi.common.utils.ip.IpUtils;
import com.zhiqi.common.utils.spring.SpringUtils;
import com.zhiqi.system.domain.SysLogininfor;
import com.zhiqi.system.domain.SysOperLog;
import com.zhiqi.system.service.SysLogininforService;
import com.zhiqi.system.service.SysOperLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.tomcat.jni.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @author RyuJung
 * @since 2023/4/20-10:22
 */
public class AsyncFactory {

    private static final Logger log = LoggerFactory.getLogger("sys-user");

    public static TimerTask recordLoginInfo(String username, String status, String message, Object... args) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(getBlock(ip));
                s.append(address);
                s.append(getBlock(username));
                s.append(getBlock(status));
                s.append(getBlock(message));
                // 打印信息到日志
                log.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUserName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
                    logininfor.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据 FIXME
                SpringUtils.getBean(SysLogininforService.class).insertLogininfor(logininfor);
            }
        };
        return timerTask;
    }

    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = StringUtils.EMPTY_STR;
        }
        return "[" + msg + "]";
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(SysOperLog operLog) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(SysOperLogService.class).insertSysOperLog(operLog);
            }
        };
        return task;
    }
}
