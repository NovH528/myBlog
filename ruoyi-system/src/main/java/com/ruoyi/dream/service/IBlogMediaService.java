package com.ruoyi.dream.service;

import java.util.List;
import com.ruoyi.dream.domain.BlogMedia;

/**
 * 博客媒体Service接口
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public interface IBlogMediaService 
{
    /**
     * 查询博客媒体
     * 
     * @param id 博客媒体主键
     * @return 博客媒体
     */
    public BlogMedia selectBlogMediaById(Long id);

    /**
     * 查询博客媒体列表
     * 
     * @param blogMedia 博客媒体
     * @return 博客媒体集合
     */
    public List<BlogMedia> selectBlogMediaList(BlogMedia blogMedia);

    /**
     * 新增博客媒体
     * 
     * @param blogMedia 博客媒体
     * @return 结果
     */
    public int insertBlogMedia(BlogMedia blogMedia);

    /**
     * 修改博客媒体
     * 
     * @param blogMedia 博客媒体
     * @return 结果
     */
    public int updateBlogMedia(BlogMedia blogMedia);

    /**
     * 批量删除博客媒体
     * 
     * @param ids 需要删除的博客媒体主键集合
     * @return 结果
     */
    public int deleteBlogMediaByIds(Long[] ids);

    /**
     * 删除博客媒体信息
     * 
     * @param id 博客媒体主键
     * @return 结果
     */
    public int deleteBlogMediaById(Long id);
}
