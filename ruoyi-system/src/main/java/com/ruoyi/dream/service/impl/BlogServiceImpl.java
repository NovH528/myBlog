package com.ruoyi.dream.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dream.mapper.BlogMapper;
import com.ruoyi.dream.domain.Blog;
import com.ruoyi.dream.service.IBlogService;

/**
 * 博客管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@Service
public class BlogServiceImpl implements IBlogService 
{
    @Autowired
    private BlogMapper blogMapper;

    /**
     * 查询博客管理
     * 
     * @param id 博客管理主键
     * @return 博客管理
     */
    @Override
    public Blog selectBlogById(Long id)
    {
        return blogMapper.selectBlogById(id);
    }

    /**
     * 查询博客管理列表
     * 
     * @param blog 博客管理
     * @return 博客管理
     */
    @Override
    public List<Blog> selectBlogList(Blog blog)
    {
        return blogMapper.selectBlogList(blog);
    }

    /**
     * 新增博客管理
     * 
     * @param blog 博客管理
     * @return 结果
     */
    @Override
    public int insertBlog(Blog blog)
    {
        blog.setCreateTime(DateUtils.getNowDate());
        return blogMapper.insertBlog(blog);
    }

    /**
     * 修改博客管理
     * 
     * @param blog 博客管理
     * @return 结果
     */
    @Override
    public int updateBlog(Blog blog)
    {
        blog.setUpdateTime(DateUtils.getNowDate());
        return blogMapper.updateBlog(blog);
    }

    /**
     * 批量删除博客管理
     * 
     * @param ids 需要删除的博客管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogByIds(Long[] ids)
    {
        return blogMapper.deleteBlogByIds(ids);
    }

    /**
     * 删除博客管理信息
     * 
     * @param id 博客管理主键
     * @return 结果
     */
    @Override
    public int deleteBlogById(Long id)
    {
        return blogMapper.deleteBlogById(id);
    }
}
