package com.zhiqi.system.service;

import java.util.List;
import com.zhiqi.system.domain.SysOperLog;

/**
 * 操作日志记录Service接口
 * 
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysOperLogService
{
    /**
     * 查询操作日志记录
     * 
     * @param operId 操作日志记录主键
     * @return 操作日志记录
     */
    public SysOperLog selectSysOperLogByOperId(Long operId);

    /**
     * 查询操作日志记录列表
     * 
     * @param sysOperLog 操作日志记录
     * @return 操作日志记录集合
     */
    public List<SysOperLog> selectSysOperLogList(SysOperLog sysOperLog);

    /**
     * 新增操作日志记录
     * 
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    public int insertSysOperLog(SysOperLog sysOperLog);

    /**
     * 修改操作日志记录
     * 
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    public int updateSysOperLog(SysOperLog sysOperLog);

    /**
     * 批量删除操作日志记录
     * 
     * @param operIds 需要删除的操作日志记录主键集合
     * @return 结果
     */
    public int deleteSysOperLogByOperIds(Long[] operIds);

    /**
     * 删除操作日志记录信息
     * 
     * @param operId 操作日志记录主键
     * @return 结果
     */
    public int deleteSysOperLogByOperId(Long operId);
}
