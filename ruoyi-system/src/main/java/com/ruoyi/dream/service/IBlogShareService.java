package com.ruoyi.dream.service;

import java.util.List;
import com.ruoyi.dream.domain.BlogShare;

/**
 * 博客分享Service接口
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public interface IBlogShareService 
{
    /**
     * 查询博客分享
     * 
     * @param id 博客分享主键
     * @return 博客分享
     */
    public BlogShare selectBlogShareById(Long id);

    /**
     * 查询博客分享列表
     * 
     * @param blogShare 博客分享
     * @return 博客分享集合
     */
    public List<BlogShare> selectBlogShareList(BlogShare blogShare);

    /**
     * 新增博客分享
     * 
     * @param blogShare 博客分享
     * @return 结果
     */
    public int insertBlogShare(BlogShare blogShare);

    /**
     * 修改博客分享
     * 
     * @param blogShare 博客分享
     * @return 结果
     */
    public int updateBlogShare(BlogShare blogShare);

    /**
     * 批量删除博客分享
     * 
     * @param ids 需要删除的博客分享主键集合
     * @return 结果
     */
    public int deleteBlogShareByIds(Long[] ids);

    /**
     * 删除博客分享信息
     * 
     * @param id 博客分享主键
     * @return 结果
     */
    public int deleteBlogShareById(Long id);
}
