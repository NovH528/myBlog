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
import com.ruoyi.dream.domain.BlogLike;
import com.ruoyi.dream.service.IBlogLikeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客喜欢Controller
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@RestController
@RequestMapping("/dream/like")
public class BlogLikeController extends BaseController
{
    @Autowired
    private IBlogLikeService blogLikeService;

    /**
     * 查询博客喜欢列表
     */
    @PreAuthorize("@ss.hasPermi('dream:like:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogLike blogLike)
    {
        startPage();
        List<BlogLike> list = blogLikeService.selectBlogLikeList(blogLike);
        return getDataTable(list);
    }

    /**
     * 导出博客喜欢列表
     */
    @PreAuthorize("@ss.hasPermi('dream:like:export')")
    @Log(title = "博客喜欢", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlogLike blogLike)
    {
        List<BlogLike> list = blogLikeService.selectBlogLikeList(blogLike);
        ExcelUtil<BlogLike> util = new ExcelUtil<BlogLike>(BlogLike.class);
        util.exportExcel(response, list, "博客喜欢数据");
    }

    /**
     * 获取博客喜欢详细信息
     */
    @PreAuthorize("@ss.hasPermi('dream:like:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blogLikeService.selectBlogLikeById(id));
    }

    /**
     * 新增博客喜欢
     */
    @PreAuthorize("@ss.hasPermi('dream:like:add')")
    @Log(title = "博客喜欢", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogLike blogLike)
    {
        return toAjax(blogLikeService.insertBlogLike(blogLike));
    }

    /**
     * 修改博客喜欢
     */
    @PreAuthorize("@ss.hasPermi('dream:like:edit')")
    @Log(title = "博客喜欢", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogLike blogLike)
    {
        return toAjax(blogLikeService.updateBlogLike(blogLike));
    }

    /**
     * 删除博客喜欢
     */
    @PreAuthorize("@ss.hasPermi('dream:like:remove')")
    @Log(title = "博客喜欢", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogLikeService.deleteBlogLikeByIds(ids));
    }
}
