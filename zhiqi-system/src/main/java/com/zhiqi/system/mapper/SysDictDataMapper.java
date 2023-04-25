package com.zhiqi.system.mapper;

import java.util.List;
import com.zhiqi.system.domain.SysDictData;

/**
 * 字典数据Mapper接口
 * 
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysDictDataMapper 
{
    /**
     * 查询字典数据
     * 
     * @param dictCode 字典数据主键
     * @return 字典数据
     */
    public SysDictData selectSysDictDataByDictCode(Long dictCode);

    /**
     * 查询字典数据列表
     * 
     * @param sysDictData 字典数据
     * @return 字典数据集合
     */
    public List<SysDictData> selectSysDictDataList(SysDictData sysDictData);

    /**
     * 新增字典数据
     * 
     * @param sysDictData 字典数据
     * @return 结果
     */
    public int insertSysDictData(SysDictData sysDictData);

    /**
     * 修改字典数据
     * 
     * @param sysDictData 字典数据
     * @return 结果
     */
    public int updateSysDictData(SysDictData sysDictData);

    /**
     * 删除字典数据
     * 
     * @param dictCode 字典数据主键
     * @return 结果
     */
    public int deleteSysDictDataByDictCode(Long dictCode);

    /**
     * 批量删除字典数据
     * 
     * @param dictCodes 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDictDataByDictCodes(Long[] dictCodes);
}
