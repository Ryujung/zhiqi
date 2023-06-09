package com.zhiqi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysLogininforMapper;
import com.zhiqi.system.domain.SysLogininfor;
import com.zhiqi.system.service.SysLogininforService;

/**
 * 系统访问记录Service业务层处理
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysLogininforServiceImpl implements SysLogininforService
{
    @Autowired
    private SysLogininforMapper sysLogininforMapper;

    /**
     * 查询系统访问记录
     * 
     * @param infoId 系统访问记录主键
     * @return 系统访问记录
     */
    @Override
    public SysLogininfor selectSysLogininforByInfoId(Long infoId)
    {
        return sysLogininforMapper.selectSysLogininforByInfoId(infoId);
    }

    /**
     * 查询系统访问记录列表
     * 
     * @param sysLogininfor 系统访问记录
     * @return 系统访问记录
     */
    @Override
    public List<SysLogininfor> selectSysLogininforList(SysLogininfor sysLogininfor)
    {
        return sysLogininforMapper.selectSysLogininforList(sysLogininfor);
    }

    /**
     * 新增系统访问记录
     * 
     * @param sysLogininfor 系统访问记录
     * @return 结果
     */
    @Override
    public int insertSysLogininfor(SysLogininfor sysLogininfor)
    {
        return sysLogininforMapper.insertSysLogininfor(sysLogininfor);
    }

    /**
     * 修改系统访问记录
     * 
     * @param sysLogininfor 系统访问记录
     * @return 结果
     */
    @Override
    public int updateSysLogininfor(SysLogininfor sysLogininfor)
    {
        return sysLogininforMapper.updateSysLogininfor(sysLogininfor);
    }

    /**
     * 批量删除系统访问记录
     * 
     * @param infoIds 需要删除的系统访问记录主键
     * @return 结果
     */
    @Override
    public int deleteSysLogininforByInfoIds(Long[] infoIds)
    {
        return sysLogininforMapper.deleteSysLogininforByInfoIds(infoIds);
    }

    /**
     * 删除系统访问记录信息
     * 
     * @param infoId 系统访问记录主键
     * @return 结果
     */
    @Override
    public int deleteSysLogininforByInfoId(Long infoId)
    {
        return sysLogininforMapper.deleteSysLogininforByInfoId(infoId);
    }

    @Override
    public void insertLogininfor(SysLogininfor logininfor) {
        sysLogininforMapper.insertSysLogininfor(logininfor);
    }
}
