package com.ruoyi.dream.service;

import java.util.List;
import com.ruoyi.dream.domain.BlogCollect;

/**
 * 博客收藏Service接口
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public interface IBlogCollectService 
{
    /**
     * 查询博客收藏
     * 
     * @param id 博客收藏主键
     * @return 博客收藏
     */
    public BlogCollect selectBlogCollectById(Long id);

    /**
     * 查询博客收藏列表
     * 
     * @param blogCollect 博客收藏
     * @return 博客收藏集合
     */
    public List<BlogCollect> selectBlogCollectList(BlogCollect blogCollect);

    /**
     * 新增博客收藏
     * 
     * @param blogCollect 博客收藏
     * @return 结果
     */
    public int insertBlogCollect(BlogCollect blogCollect);

    /**
     * 修改博客收藏
     * 
     * @param blogCollect 博客收藏
     * @return 结果
     */
    public int updateBlogCollect(BlogCollect blogCollect);

    /**
     * 批量删除博客收藏
     * 
     * @param ids 需要删除的博客收藏主键集合
     * @return 结果
     */
    public int deleteBlogCollectByIds(Long[] ids);

    /**
     * 删除博客收藏信息
     * 
     * @param id 博客收藏主键
     * @return 结果
     */
    public int deleteBlogCollectById(Long id);
}
