package com.zhiqi.system.service.impl;

import java.util.List;
import com.zhiqi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysJobMapper;
import com.zhiqi.system.domain.SysJob;
import com.zhiqi.system.service.SysJobService;

/**
 * 定时任务调度Service业务层处理
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysJobServiceImpl implements SysJobService
{
    @Autowired
    private SysJobMapper sysJobMapper;

    /**
     * 查询定时任务调度
     * 
     * @param jobId 定时任务调度主键
     * @return 定时任务调度
     */
    @Override
    public SysJob selectSysJobByJobId(Long jobId)
    {
        return sysJobMapper.selectSysJobByJobId(jobId);
    }

    /**
     * 查询定时任务调度列表
     * 
     * @param sysJob 定时任务调度
     * @return 定时任务调度
     */
    @Override
    public List<SysJob> selectSysJobList(SysJob sysJob)
    {
        return sysJobMapper.selectSysJobList(sysJob);
    }

    /**
     * 新增定时任务调度
     * 
     * @param sysJob 定时任务调度
     * @return 结果
     */
    @Override
    public int insertSysJob(SysJob sysJob)
    {
        sysJob.setCreateTime(DateUtils.getNowDate());
        return sysJobMapper.insertSysJob(sysJob);
    }

    /**
     * 修改定时任务调度
     * 
     * @param sysJob 定时任务调度
     * @return 结果
     */
    @Override
    public int updateSysJob(SysJob sysJob)
    {
        sysJob.setUpdateTime(DateUtils.getNowDate());
        return sysJobMapper.updateSysJob(sysJob);
    }

    /**
     * 批量删除定时任务调度
     * 
     * @param jobIds 需要删除的定时任务调度主键
     * @return 结果
     */
    @Override
    public int deleteSysJobByJobIds(Long[] jobIds)
    {
        return sysJobMapper.deleteSysJobByJobIds(jobIds);
    }

    /**
     * 删除定时任务调度信息
     * 
     * @param jobId 定时任务调度主键
     * @return 结果
     */
    @Override
    public int deleteSysJobByJobId(Long jobId)
    {
        return sysJobMapper.deleteSysJobByJobId(jobId);
    }
}
