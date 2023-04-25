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
import com.zhiqi.system.domain.SysPost;
import com.zhiqi.system.service.SysPostService;
import com.zhiqi.common.utils.poi.ExcelUtil;
import com.zhiqi.common.core.page.TableDataInfo;

/**
 * 岗位信息Controller
 * 
 * @author ryujung
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController
{
    @Autowired
    private SysPostService sysPostService;

    /**
     * 查询岗位信息列表
     */
    @PreAuthorize("@ss.hasPerm('system:post:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPost sysPost)
    {
        startPage();
        List<SysPost> list = sysPostService.selectSysPostList(sysPost);
        return getDataTable(list);
    }

    /**
     * 导出岗位信息列表
     */
    @PreAuthorize("@ss.hasPerm('system:post:export')")
    @Log(title = "岗位信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public CommonResult export(SysPost sysPost)
    {
        List<SysPost> list = sysPostService.selectSysPostList(sysPost);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        return util.exportExcel(list, "岗位信息数据");
    }

    /**
     * 获取岗位信息详细信息
     */
    @PreAuthorize("@ss.hasPerm('system:post:query')")
    @GetMapping(value = "/{postId}")
    public CommonResult getInfo(@PathVariable("postId") Long postId)
    {
        return CommonResult.success(sysPostService.selectSysPostByPostId(postId));
    }

    /**
     * 新增岗位信息
     */
    @PreAuthorize("@ss.hasPerm('system:post:add')")
    @Log(title = "岗位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public CommonResult add(@RequestBody SysPost sysPost)
    {
        return toAjax(sysPostService.insertSysPost(sysPost));
    }

    /**
     * 修改岗位信息
     */
    @PreAuthorize("@ss.hasPerm('system:post:edit')")
    @Log(title = "岗位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public CommonResult edit(@RequestBody SysPost sysPost)
    {
        return toAjax(sysPostService.updateSysPost(sysPost));
    }

    /**
     * 删除岗位信息
     */
    @PreAuthorize("@ss.hasPerm('system:post:remove')")
    @Log(title = "岗位信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postIds}")
    public CommonResult remove(@PathVariable Long[] postIds)
    {
        return toAjax(sysPostService.deleteSysPostByPostIds(postIds));
    }
}
