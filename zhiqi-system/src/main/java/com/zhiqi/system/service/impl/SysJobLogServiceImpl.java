package com.zhiqi.system.service.impl;

import java.util.List;
import com.zhiqi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysJobLogMapper;
import com.zhiqi.system.domain.SysJobLog;
import com.zhiqi.system.service.SysJobLogService;

/**
 * 定时任务调度日志Service业务层处理
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysJobLogServiceImpl implements SysJobLogService
{
    @Autowired
    private SysJobLogMapper sysJobLogMapper;

    /**
     * 查询定时任务调度日志
     * 
     * @param jobLogId 定时任务调度日志主键
     * @return 定时任务调度日志
     */
    @Override
    public SysJobLog selectSysJobLogByJobLogId(Long jobLogId)
    {
        return sysJobLogMapper.selectSysJobLogByJobLogId(jobLogId);
    }

    /**
     * 查询定时任务调度日志列表
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 定时任务调度日志
     */
    @Override
    public List<SysJobLog> selectSysJobLogList(SysJobLog sysJobLog)
    {
        return sysJobLogMapper.selectSysJobLogList(sysJobLog);
    }

    /**
     * 新增定时任务调度日志
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 结果
     */
    @Override
    public int insertSysJobLog(SysJobLog sysJobLog)
    {
        sysJobLog.setCreateTime(DateUtils.getNowDate());
        return sysJobLogMapper.insertSysJobLog(sysJobLog);
    }

    /**
     * 修改定时任务调度日志
     * 
     * @param sysJobLog 定时任务调度日志
     * @return 结果
     */
    @Override
    public int updateSysJobLog(SysJobLog sysJobLog)
    {
        return sysJobLogMapper.updateSysJobLog(sysJobLog);
    }

    /**
     * 批量删除定时任务调度日志
     * 
     * @param jobLogIds 需要删除的定时任务调度日志主键
     * @return 结果
     */
    @Override
    public int deleteSysJobLogByJobLogIds(Long[] jobLogIds)
    {
        return sysJobLogMapper.deleteSysJobLogByJobLogIds(jobLogIds);
    }

    /**
     * 删除定时任务调度日志信息
     * 
     * @param jobLogId 定时任务调度日志主键
     * @return 结果
     */
    @Override
    public int deleteSysJobLogByJobLogId(Long jobLogId)
    {
        return sysJobLogMapper.deleteSysJobLogByJobLogId(jobLogId);
    }
}
