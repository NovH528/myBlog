package com.ruoyi.dream.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dream.mapper.BlogLikeMapper;
import com.ruoyi.dream.domain.BlogLike;
import com.ruoyi.dream.service.IBlogLikeService;

/**
 * 博客喜欢Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@Service
public class BlogLikeServiceImpl implements IBlogLikeService 
{
    @Autowired
    private BlogLikeMapper blogLikeMapper;

    /**
     * 查询博客喜欢
     * 
     * @param id 博客喜欢主键
     * @return 博客喜欢
     */
    @Override
    public BlogLike selectBlogLikeById(Long id)
    {
        return blogLikeMapper.selectBlogLikeById(id);
    }

    /**
     * 查询博客喜欢列表
     * 
     * @param blogLike 博客喜欢
     * @return 博客喜欢
     */
    @Override
    public List<BlogLike> selectBlogLikeList(BlogLike blogLike)
    {
        return blogLikeMapper.selectBlogLikeList(blogLike);
    }

    /**
     * 新增博客喜欢
     * 
     * @param blogLike 博客喜欢
     * @return 结果
     */
    @Override
    public int insertBlogLike(BlogLike blogLike)
    {
        return blogLikeMapper.insertBlogLike(blogLike);
    }

    /**
     * 修改博客喜欢
     * 
     * @param blogLike 博客喜欢
     * @return 结果
     */
    @Override
    public int updateBlogLike(BlogLike blogLike)
    {
        return blogLikeMapper.updateBlogLike(blogLike);
    }

    /**
     * 批量删除博客喜欢
     * 
     * @param ids 需要删除的博客喜欢主键
     * @return 结果
     */
    @Override
    public int deleteBlogLikeByIds(Long[] ids)
    {
        return blogLikeMapper.deleteBlogLikeByIds(ids);
    }

    /**
     * 删除博客喜欢信息
     * 
     * @param id 博客喜欢主键
     * @return 结果
     */
    @Override
    public int deleteBlogLikeById(Long id)
    {
        return blogLikeMapper.deleteBlogLikeById(id);
    }
}
