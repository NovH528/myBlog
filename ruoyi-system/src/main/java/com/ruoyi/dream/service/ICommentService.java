package com.ruoyi.dream.service;

import java.util.List;
import com.ruoyi.dream.domain.Comment;

/**
 * 博客评论Service接口
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public interface ICommentService 
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
     * 批量删除博客评论
     * 
     * @param ids 需要删除的博客评论主键集合
     * @return 结果
     */
    public int deleteCommentByIds(Long[] ids);

    /**
     * 删除博客评论信息
     * 
     * @param id 博客评论主键
     * @return 结果
     */
    public int deleteCommentById(Long id);
}
