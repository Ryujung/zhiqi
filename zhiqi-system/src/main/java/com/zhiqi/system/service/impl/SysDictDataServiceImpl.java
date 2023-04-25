package com.zhiqi.system.service.impl;

import java.util.List;
import com.zhiqi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhiqi.system.mapper.SysDictDataMapper;
import com.zhiqi.system.domain.SysDictData;
import com.zhiqi.system.service.SysDictDataService;

/**
 * 字典数据Service业务层处理
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService
{
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询字典数据
     * 
     * @param dictCode 字典数据主键
     * @return 字典数据
     */
    @Override
    public SysDictData selectSysDictDataByDictCode(Long dictCode)
    {
        return sysDictDataMapper.selectSysDictDataByDictCode(dictCode);
    }

    /**
     * 查询字典数据列表
     * 
     * @param sysDictData 字典数据
     * @return 字典数据
     */
    @Override
    public List<SysDictData> selectSysDictDataList(SysDictData sysDictData)
    {
        return sysDictDataMapper.selectSysDictDataList(sysDictData);
    }

    /**
     * 新增字典数据
     * 
     * @param sysDictData 字典数据
     * @return 结果
     */
    @Override
    public int insertSysDictData(SysDictData sysDictData)
    {
        sysDictData.setCreateTime(DateUtils.getNowDate());
        return sysDictDataMapper.insertSysDictData(sysDictData);
    }

    /**
     * 修改字典数据
     * 
     * @param sysDictData 字典数据
     * @return 结果
     */
    @Override
    public int updateSysDictData(SysDictData sysDictData)
    {
        sysDictData.setUpdateTime(DateUtils.getNowDate());
        return sysDictDataMapper.updateSysDictData(sysDictData);
    }

    /**
     * 批量删除字典数据
     * 
     * @param dictCodes 需要删除的字典数据主键
     * @return 结果
     */
    @Override
    public int deleteSysDictDataByDictCodes(Long[] dictCodes)
    {
        return sysDictDataMapper.deleteSysDictDataByDictCodes(dictCodes);
    }

    /**
     * 删除字典数据信息
     * 
     * @param dictCode 字典数据主键
     * @return 结果
     */
    @Override
    public int deleteSysDictDataByDictCode(Long dictCode)
    {
        return sysDictDataMapper.deleteSysDictDataByDictCode(dictCode);
    }
}
