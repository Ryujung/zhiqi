package com.zhiqi.system.mapper;

import java.util.List;
import com.zhiqi.system.domain.SysNotice;

/**
 * 通知公告Mapper接口
 * 
 * @author ryujung
 * @date 2023-04-25
 */
public interface SysNoticeMapper 
{
    /**
     * 查询通知公告
     * 
     * @param noticeId 通知公告主键
     * @return 通知公告
     */
    public SysNotice selectSysNoticeByNoticeId(Integer noticeId);

    /**
     * 查询通知公告列表
     * 
     * @param sysNotice 通知公告
     * @return 通知公告集合
     */
    public List<SysNotice> selectSysNoticeList(SysNotice sysNotice);

    /**
     * 新增通知公告
     * 
     * @param sysNotice 通知公告
     * @return 结果
     */
    public int insertSysNotice(SysNotice sysNotice);

    /**
     * 修改通知公告
     * 
     * @param sysNotice 通知公告
     * @return 结果
     */
    public int updateSysNotice(SysNotice sysNotice);

    /**
     * 删除通知公告
     * 
     * @param noticeId 通知公告主键
     * @return 结果
     */
    public int deleteSysNoticeByNoticeId(Integer noticeId);

    /**
     * 批量删除通知公告
     * 
     * @param noticeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysNoticeByNoticeIds(Integer[] noticeIds);
}
