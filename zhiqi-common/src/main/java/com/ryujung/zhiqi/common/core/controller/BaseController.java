package com.ryujung.zhiqi.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryujung.zhiqi.common.contant.HttpStatus;
import com.ryujung.zhiqi.common.core.domain.CommonResult;
import com.ryujung.zhiqi.common.core.domain.model.LoginUser;
import com.ryujung.zhiqi.common.core.domain.page.PageDomain;
import com.ryujung.zhiqi.common.core.domain.page.TableDataInfo;
import com.ryujung.zhiqi.common.utils.DateUtils;
import com.ryujung.zhiqi.common.utils.SecurityUtils;
import com.ryujung.zhiqi.common.utils.StringUtils;
import com.ryujung.zhiqi.common.utils.sql.SqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author RyuJung
 * @since 2023/4/14-21:44
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void intiBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    Date date = DateUtils.parseDate(text);
                    setValue(date);
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = PageDomain.build();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setCode(HttpStatus.SUCCESS);
        tableDataInfo.setMsg("查询成功");
        tableDataInfo.setRows(list);
        tableDataInfo.setTotal(new PageInfo(list).getTotal());
        return tableDataInfo;
    }

    /**
     * 返回成功
     */
    public CommonResult success() {
        return CommonResult.success();
    }

    /**
     * 返回失败消息
     */
    public CommonResult error() {
        return CommonResult.error();
    }

    /**
     * 返回成功消息
     */
    public CommonResult success(String message) {
        return CommonResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public CommonResult error(String message) {
        return CommonResult.error(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected CommonResult toResult(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 影响行数
     * @return 操作结果
     */
    protected CommonResult toResult(boolean result) {
        return result ? success() : error();
    }

    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    public LoginUser getLoginUser() {
        return SecurityUtils.getLoginUser();
    }

    public Long getUserId() {
        return getLoginUser().getUserId();
    }

    public Long getDeptId() {
        return getLoginUser().getDeptId();
    }

    public String getUsername() {
        return getLoginUser().getUsername();
    }
}
