package com.zhiqi.web.controller.system;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhiqi.common.annotation.Log;
import com.zhiqi.common.core.controller.BaseController;
import com.zhiqi.common.core.domain.CommonResult;
import com.zhiqi.common.enums.BusinessType;
import com.zhiqi.system.domain.SysNotice;
import com.zhiqi.system.service.SysNoticeService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 通知公告Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    @Autowired
    private SysNoticeService sysNoticeService;

    /**
     * 查询通知公告列表
     */
    @PreAuthorize("@ss.hasPerm('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNotice sysNotice)
    {
        startPage();
        List<SysNotice> list = sysNoticeService.selectSysNoticeList(sysNotice);
        return getDataTable(list);
    }

    /**
     * 导出通知公告列表
     */
    @PreAuthorize("@ss.hasPerm('system:notice:export')")
    @Log(title = "通知公告", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysNotice sysNotice)
    {
        List<SysNotice> list = sysNoticeService.selectSysNoticeList(sysNotice);
        ExcelUtil<SysNotice> util = new ExcelUtil<SysNotice>(SysNotice.class);
        return util.exportExcel(list, "通知公告数据");
    }

    /**
     * 获取通知公告详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public CommonResult getInfo(@PathVariable("noticeId") Integer noticeId)
    {
        return CommonResult.success(sysNoticeService.selectSysNoticeByNoticeId(noticeId));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@ss.hasPerm('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysNotice sysNotice)
    {
        return toAjax(sysNoticeService.insertSysNotice(sysNotice));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@ss.hasPerm('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysNotice sysNotice)
    {
        return toAjax(sysNoticeService.updateSysNotice(sysNotice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@ss.hasPerm('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    public CommonResult remove(@PathVariable Integer[] noticeIds)
    {
        return toAjax(sysNoticeService.deleteSysNoticeByNoticeIds(noticeIds));
    }
}
