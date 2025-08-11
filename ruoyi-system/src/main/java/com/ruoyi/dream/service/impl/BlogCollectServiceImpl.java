package com.ruoyi.dream.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dream.mapper.BlogCollectMapper;
import com.ruoyi.dream.domain.BlogCollect;
import com.ruoyi.dream.service.IBlogCollectService;

/**
 * 博客收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@Service
public class BlogCollectServiceImpl implements IBlogCollectService 
{
    @Autowired
    private BlogCollectMapper blogCollectMapper;

    /**
     * 查询博客收藏
     * 
     * @param id 博客收藏主键
     * @return 博客收藏
     */
    @Override
    public BlogCollect selectBlogCollectById(Long id)
    {
        return blogCollectMapper.selectBlogCollectById(id);
    }

    /**
     * 查询博客收藏列表
     * 
     * @param blogCollect 博客收藏
     * @return 博客收藏
     */
    @Override
    public List<BlogCollect> selectBlogCollectList(BlogCollect blogCollect)
    {
        return blogCollectMapper.selectBlogCollectList(blogCollect);
    }

    /**
     * 新增博客收藏
     * 
     * @param blogCollect 博客收藏
     * @return 结果
     */
    @Override
    public int insertBlogCollect(BlogCollect blogCollect)
    {
        return blogCollectMapper.insertBlogCollect(blogCollect);
    }

    /**
     * 修改博客收藏
     * 
     * @param blogCollect 博客收藏
     * @return 结果
     */
    @Override
    public int updateBlogCollect(BlogCollect blogCollect)
    {
        return blogCollectMapper.updateBlogCollect(blogCollect);
    }

    /**
     * 批量删除博客收藏
     * 
     * @param ids 需要删除的博客收藏主键
     * @return 结果
     */
    @Override
    public int deleteBlogCollectByIds(Long[] ids)
    {
        return blogCollectMapper.deleteBlogCollectByIds(ids);
    }

    /**
     * 删除博客收藏信息
     * 
     * @param id 博客收藏主键
     * @return 结果
     */
    @Override
    public int deleteBlogCollectById(Long id)
    {
        return blogCollectMapper.deleteBlogCollectById(id);
    }
}
