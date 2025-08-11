package com.ruoyi.dream.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dream.mapper.BlogMediaMapper;
import com.ruoyi.dream.domain.BlogMedia;
import com.ruoyi.dream.service.IBlogMediaService;

/**
 * 博客媒体Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@Service
public class BlogMediaServiceImpl implements IBlogMediaService 
{
    @Autowired
    private BlogMediaMapper blogMediaMapper;

    /**
     * 查询博客媒体
     * 
     * @param id 博客媒体主键
     * @return 博客媒体
     */
    @Override
    public BlogMedia selectBlogMediaById(Long id)
    {
        return blogMediaMapper.selectBlogMediaById(id);
    }

    /**
     * 查询博客媒体列表
     * 
     * @param blogMedia 博客媒体
     * @return 博客媒体
     */
    @Override
    public List<BlogMedia> selectBlogMediaList(BlogMedia blogMedia)
    {
        return blogMediaMapper.selectBlogMediaList(blogMedia);
    }

    /**
     * 新增博客媒体
     * 
     * @param blogMedia 博客媒体
     * @return 结果
     */
    @Override
    public int insertBlogMedia(BlogMedia blogMedia)
    {
        return blogMediaMapper.insertBlogMedia(blogMedia);
    }

    /**
     * 修改博客媒体
     * 
     * @param blogMedia 博客媒体
     * @return 结果
     */
    @Override
    public int updateBlogMedia(BlogMedia blogMedia)
    {
        return blogMediaMapper.updateBlogMedia(blogMedia);
    }

    /**
     * 批量删除博客媒体
     * 
     * @param ids 需要删除的博客媒体主键
     * @return 结果
     */
    @Override
    public int deleteBlogMediaByIds(Long[] ids)
    {
        return blogMediaMapper.deleteBlogMediaByIds(ids);
    }

    /**
     * 删除博客媒体信息
     * 
     * @param id 博客媒体主键
     * @return 结果
     */
    @Override
    public int deleteBlogMediaById(Long id)
    {
        return blogMediaMapper.deleteBlogMediaById(id);
    }
}
