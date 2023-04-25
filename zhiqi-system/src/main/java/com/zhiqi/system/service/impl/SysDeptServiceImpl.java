package com.zhiqi.system.service.impl;

import java.util.List;

import com.zhiqi.common.core.domain.entity.SysDept;
import com.zhiqi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysDeptMapper;
import com.zhiqi.system.service.SysDeptService;

/**
 * 部门Service业务层处理
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysDeptServiceImpl implements SysDeptService
{
    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询部门
     * 
     * @param deptId 部门主键
     * @return 部门
     */
    @Override
    public SysDept selectSysDeptByDeptId(Long deptId)
    {
        return sysDeptMapper.selectSysDeptByDeptId(deptId);
    }

    /**
     * 查询部门列表
     * 
     * @param sysDept 部门
     * @return 部门
     */
    @Override
    public List<SysDept> selectSysDeptList(SysDept sysDept)
    {
        return sysDeptMapper.selectSysDeptList(sysDept);
    }

    /**
     * 新增部门
     * 
     * @param sysDept 部门
     * @return 结果
     */
    @Override
    public int insertSysDept(SysDept sysDept)
    {
        sysDept.setCreateTime(DateUtils.getNowDate());
        return sysDeptMapper.insertSysDept(sysDept);
    }

    /**
     * 修改部门
     * 
     * @param sysDept 部门
     * @return 结果
     */
    @Override
    public int updateSysDept(SysDept sysDept)
    {
        sysDept.setUpdateTime(DateUtils.getNowDate());
        return sysDeptMapper.updateSysDept(sysDept);
    }

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的部门主键
     * @return 结果
     */
    @Override
    public int deleteSysDeptByDeptIds(Long[] deptIds)
    {
        return sysDeptMapper.deleteSysDeptByDeptIds(deptIds);
    }

    /**
     * 删除部门信息
     * 
     * @param deptId 部门主键
     * @return 结果
     */
    @Override
    public int deleteSysDeptByDeptId(Long deptId)
    {
        return sysDeptMapper.deleteSysDeptByDeptId(deptId);
    }
}
