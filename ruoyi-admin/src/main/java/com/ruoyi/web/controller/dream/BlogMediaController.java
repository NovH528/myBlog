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
import com.ruoyi.dream.domain.BlogMedia;
import com.ruoyi.dream.service.IBlogMediaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客媒体Controller
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@RestController
@RequestMapping("/dream/media")
public class BlogMediaController extends BaseController
{
    @Autowired
    private IBlogMediaService blogMediaService;

    /**
     * 查询博客媒体列表
     */
    @PreAuthorize("@ss.hasPermi('dream:media:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogMedia blogMedia)
    {
        startPage();
        List<BlogMedia> list = blogMediaService.selectBlogMediaList(blogMedia);
        return getDataTable(list);
    }

    /**
     * 导出博客媒体列表
     */
    @PreAuthorize("@ss.hasPermi('dream:media:export')")
    @Log(title = "博客媒体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogMedia blogMedia)
    {
        List<BlogMedia> list = blogMediaService.selectBlogMediaList(blogMedia);
        ExcelUtil<BlogMedia> util = new ExcelUtil<BlogMedia>(BlogMedia.class);
        util.exportExcel(response, list, "博客媒体数据");
    }

    /**
     * 获取博客媒体详细信息
     */
    @PreAuthorize("@ss.hasPermi('dream:media:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blogMediaService.selectBlogMediaById(id));
    }

    /**
     * 新增博客媒体
     */
    @PreAuthorize("@ss.hasPermi('dream:media:add')")
    @Log(title = "博客媒体", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogMedia blogMedia)
    {
        return toAjax(blogMediaService.insertBlogMedia(blogMedia));
    }

    /**
     * 修改博客媒体
     */
    @PreAuthorize("@ss.hasPermi('dream:media:edit')")
    @Log(title = "博客媒体", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogMedia blogMedia)
    {
        return toAjax(blogMediaService.updateBlogMedia(blogMedia));
    }

    /**
     * 删除博客媒体
     */
    @PreAuthorize("@ss.hasPermi('dream:media:remove')")
    @Log(title = "博客媒体", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogMediaService.deleteBlogMediaByIds(ids));
    }
}
