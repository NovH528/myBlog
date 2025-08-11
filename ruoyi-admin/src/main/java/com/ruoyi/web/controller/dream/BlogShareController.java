package com.ruoyi.web.controller.dream;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dream.domain.BlogShare;
import com.ruoyi.dream.service.IBlogShareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客分享Controller
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@RestController
@RequestMapping("/dream/share")
public class BlogShareController extends BaseController
{
    @Autowired
    private IBlogShareService blogShareService;

    /**
     * 查询博客分享列表
     */
    @PreAuthorize("@ss.hasPermi('dream:share:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogShare blogShare)
    {
        startPage();
        List<BlogShare> list = blogShareService.selectBlogShareList(blogShare);
        return getDataTable(list);
    }

    /**
     * 导出博客分享列表
     */
    @PreAuthorize("@ss.hasPermi('dream:share:export')")
    @Log(title = "博客分享", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogShare blogShare)
    {
        List<BlogShare> list = blogShareService.selectBlogShareList(blogShare);
        ExcelUtil<BlogShare> util = new ExcelUtil<BlogShare>(BlogShare.class);
        util.exportExcel(response, list, "博客分享数据");
    }

    /**
     * 获取博客分享详细信息
     */
    @PreAuthorize("@ss.hasPermi('dream:share:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blogShareService.selectBlogShareById(id));
    }

    /**
     * 新增博客分享
     */
    @PreAuthorize("@ss.hasPermi('dream:share:add')")
    @Log(title = "博客分享", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogShare blogShare)
    {
        return toAjax(blogShareService.insertBlogShare(blogShare));
    }

    /**
     * 修改博客分享
     */
    @PreAuthorize("@ss.hasPermi('dream:share:edit')")
    @Log(title = "博客分享", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogShare blogShare)
    {
        return toAjax(blogShareService.updateBlogShare(blogShare));
    }

    /**
     * 删除博客分享
     */
    @PreAuthorize("@ss.hasPermi('dream:share:remove')")
    @Log(title = "博客分享", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogShareService.deleteBlogShareByIds(ids));
    }
}
