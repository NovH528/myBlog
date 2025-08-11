package com.ruoyi.web.controller.dream;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.dream.domain.Blog;
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
import com.ruoyi.dream.service.IBlogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客管理Controller
 *
 * @author ruoyi
 * @date 2025-08-17
 */
@RestController
@RequestMapping("/dream/blog")
public class BlogController extends BaseController
{
    @Autowired
    private IBlogService blogService;

    /**
     * 查询博客管理列表
     */
    @PreAuthorize("@ss.hasPermi('dream:blog:list')")
    @GetMapping("/list")
    public TableDataInfo list(Blog blog)
    {
        startPage();
        List<Blog> list = blogService.selectBlogList(blog);
        return getDataTable(list);
    }

    /**
     * 导出博客管理列表
     */
    @PreAuthorize("@ss.hasPermi('dream:blog:export')")
    @Log(title = "博客管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Blog blog)
    {
        List<Blog> list = blogService.selectBlogList(blog);
        ExcelUtil<Blog> util = new ExcelUtil<Blog>(Blog.class);
        util.exportExcel(response, list, "博客管理数据");
    }

    /**
     * 获取博客管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('dream:blog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blogService.selectBlogById(id));
    }

    /**
     * 新增博客管理
     */
    @PreAuthorize("@ss.hasPermi('dream:blog:add')")
    @Log(title = "博客管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Blog blog)
    {
        return toAjax(blogService.insertBlog(blog));
    }

    /**
     * 修改博客管理
     */
    @PreAuthorize("@ss.hasPermi('dream:blog:edit')")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Blog blog)
    {
        return toAjax(blogService.updateBlog(blog));
    }

    /**
     * 删除博客管理
     */
    @PreAuthorize("@ss.hasPermi('dream:blog:remove')")
    @Log(title = "博客管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogService.deleteBlogByIds(ids));
    }
}
