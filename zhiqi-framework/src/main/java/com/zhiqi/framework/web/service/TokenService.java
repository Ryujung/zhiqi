package com.zhiqi.framework.web.service;

import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.core.domain.model.LoginUser;
import com.zhiqi.common.utils.ServletUtils;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.common.utils.ip.AddressUtils;
import com.zhiqi.common.utils.ip.IpUtils;
import com.zhiqi.common.utils.uuid.IdUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author RyuJung
 * @since 2023/4/19-11:37
 */
@Service
public class TokenService {

    private static final long MILLIS_MINUTE_TEN = 10 * 60 * 1000L;
    private static final long MILLIS_TO_MINUTE = 60 * 1000L;

    @Value("${jwt.headerTokenName}")
    private String headerTokenName;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expirationInMinute;

    @Autowired
    private RedisCache redisService;

    /**
     * 从请求中获取登录用户的信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = request.getHeader(headerTokenName);

        if (StringUtils.isNotNull(token) && token.startsWith(Constants.REQUEST_HEADER_TOKEN_PREFIX)) {
            token = token.replace(Constants.REQUEST_HEADER_TOKEN_PREFIX, "");
            LoginUser loginUser = parseJwtToken(token);
            return loginUser;
        }
        return null;
    }

    /**
     * 验证token是否需要刷新
     *
     * @param loginUser 登录用户
     */
    public void verifyToken(LoginUser loginUser) {
        Long expireTime = loginUser.getExpireTime();
        if (expireTime - System.currentTimeMillis() <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    private LoginUser parseJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        String uuid = (String) claims.get(Constants.LOGIN_USER_CLAIMS_TOKEN_NAME);
        String loginUserRedisKey = Constants.LOGIN_USER_REDIS_KEY_PREFIX + uuid;
        LoginUser loginUser = redisService.getCacheObject(loginUserRedisKey, LoginUser.class);

        return loginUser;
    }

    private void refreshToken(LoginUser loginUser) {
        long loginTime = System.currentTimeMillis();
        loginUser.setLoginTime(loginTime);
        loginUser.setExpireTime(loginTime + expirationInMinute * MILLIS_TO_MINUTE);
        String loginUserRedisKey = getLoginUserRedisKey(loginUser);
        redisService.setCacheObject(loginUserRedisKey, loginUser, expirationInMinute, TimeUnit.MINUTES);
    }

    private String getLoginUserRedisKey(LoginUser loginUser) {
        return Constants.LOGIN_USER_REDIS_KEY_PREFIX + loginUser.getToken();
    }

    /**
     * 服务器端 删除用户身份信息
     */
    public void deleteLoginUser(LoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser)) {
            String redisKey = getLoginUserRedisKey(loginUser);
            redisService.deleteObject(redisKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        String uuid = IdUtils.fastUUID();
        loginUser.setToken(uuid);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, uuid);
        return createToken(claims);
    }

    private String createToken(HashMap<String, Object> claims) {
        String token = Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    private void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

}
