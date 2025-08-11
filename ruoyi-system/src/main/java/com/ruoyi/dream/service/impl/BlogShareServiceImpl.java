package com.ruoyi.dream.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dream.mapper.BlogShareMapper;
import com.ruoyi.dream.domain.BlogShare;
import com.ruoyi.dream.service.IBlogShareService;

/**
 * 博客分享Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@Service
public class BlogShareServiceImpl implements IBlogShareService 
{
    @Autowired
    private BlogShareMapper blogShareMapper;

    /**
     * 查询博客分享
     * 
     * @param id 博客分享主键
     * @return 博客分享
     */
    @Override
    public BlogShare selectBlogShareById(Long id)
    {
        return blogShareMapper.selectBlogShareById(id);
    }

    /**
     * 查询博客分享列表
     * 
     * @param blogShare 博客分享
     * @return 博客分享
     */
    @Override
    public List<BlogShare> selectBlogShareList(BlogShare blogShare)
    {
        return blogShareMapper.selectBlogShareList(blogShare);
    }

    /**
     * 新增博客分享
     * 
     * @param blogShare 博客分享
     * @return 结果
     */
    @Override
    public int insertBlogShare(BlogShare blogShare)
    {
        return blogShareMapper.insertBlogShare(blogShare);
    }

    /**
     * 修改博客分享
     * 
     * @param blogShare 博客分享
     * @return 结果
     */
    @Override
    public int updateBlogShare(BlogShare blogShare)
    {
        return blogShareMapper.updateBlogShare(blogShare);
    }

    /**
     * 批量删除博客分享
     * 
     * @param ids 需要删除的博客分享主键
     * @return 结果
     */
    @Override
    public int deleteBlogShareByIds(Long[] ids)
    {
        return blogShareMapper.deleteBlogShareByIds(ids);
    }

    /**
     * 删除博客分享信息
     * 
     * @param id 博客分享主键
     * @return 结果
     */
    @Override
    public int deleteBlogShareById(Long id)
    {
        return blogShareMapper.deleteBlogShareById(id);
    }
}
