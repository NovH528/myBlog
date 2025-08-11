package com.ruoyi.dream.mapper;

import java.util.List;
import com.ruoyi.dream.domain.BlogLike;

/**
 * 博客喜欢Mapper接口
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public interface BlogLikeMapper 
{
    /**
     * 查询博客喜欢
     * 
     * @param id 博客喜欢主键
     * @return 博客喜欢
     */
    public BlogLike selectBlogLikeById(Long id);

    /**
     * 查询博客喜欢列表
     * 
     * @param blogLike 博客喜欢
     * @return 博客喜欢集合
     */
    public List<BlogLike> selectBlogLikeList(BlogLike blogLike);

    /**
     * 新增博客喜欢
     * 
     * @param blogLike 博客喜欢
     * @return 结果
     */
    public int insertBlogLike(BlogLike blogLike);

    /**
     * 修改博客喜欢
     * 
     * @param blogLike 博客喜欢
     * @return 结果
     */
    public int updateBlogLike(BlogLike blogLike);

    /**
     * 删除博客喜欢
     * 
     * @param id 博客喜欢主键
     * @return 结果
     */
    public int deleteBlogLikeById(Long id);

    /**
     * 批量删除博客喜欢
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogLikeByIds(Long[] ids);
}
