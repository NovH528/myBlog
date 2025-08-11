package com.ruoyi.dream.mapper;

import java.util.List;
import com.ruoyi.dream.domain.Comment;

/**
 * 博客评论Mapper接口
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public interface CommentMapper 
{
    /**
     * 查询博客评论
     * 
     * @param id 博客评论主键
     * @return 博客评论
     */
    public Comment selectCommentById(Long id);

    /**
     * 查询博客评论列表
     * 
     * @param comment 博客评论
     * @return 博客评论集合
     */
    public List<Comment> selectCommentList(Comment comment);

    /**
     * 新增博客评论
     * 
     * @param comment 博客评论
     * @return 结果
     */
    public int insertComment(Comment comment);

    /**
     * 修改博客评论
     * 
     * @param comment 博客评论
     * @return 结果
     */
    public int updateComment(Comment comment);

    /**
     * 删除博客评论
     * 
     * @param id 博客评论主键
     * @return 结果
     */
    public int deleteCommentById(Long id);

    /**
     * 批量删除博客评论
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommentByIds(Long[] ids);
}
