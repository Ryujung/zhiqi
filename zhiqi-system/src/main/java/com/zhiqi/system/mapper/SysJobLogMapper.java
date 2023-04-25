package com.zhiqi.system.mapper;

import java.util.List;
import com.zhiqi.system.domain.SysJobLog;

/**
 * 定时任务调度日志Mapper接口
 * 
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysJobLogMapper 
{
    /**
     * 查询定时任务调度日志
     * 
     * @param jobLogId 定时任务调度日志主键
     * @return 定时任务调度日志
     */
    public SysJobLog selectSysJobLogByJobLogId(Long jobLogId);

    /**
     * 查询定时任务调度日志列表
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 定时任务调度日志集合
     */
    public List<SysJobLog> selectSysJobLogList(SysJobLog sysJobLog);

    /**
     * 新增定时任务调度日志
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 结果
     */
    public int insertSysJobLog(SysJobLog sysJobLog);

    /**
     * 修改定时任务调度日志
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 结果
     */
    public int updateSysJobLog(SysJobLog sysJobLog);

    /**
     * 删除定时任务调度日志
     * 
     * @param jobLogId 定时任务调度日志主键
     * @return 结果
     */
    public int deleteSysJobLogByJobLogId(Long jobLogId);

    /**
     * 批量删除定时任务调度日志
     * 
     * @param jobLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysJobLogByJobLogIds(Long[] jobLogIds);
}
