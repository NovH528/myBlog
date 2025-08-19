package com.ruoyi.dream.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dream.mapper.CommentMapper;
import com.ruoyi.dream.domain.Comment;
import com.ruoyi.dream.service.ICommentService;

/**
 * 博客评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@Service
public class CommentServiceImpl implements ICommentService 
{
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询博客评论
     * 
     * @param id 博客评论主键
     * @return 博客评论
     */
    @Override
    public Comment selectCommentById(Long id)
    {
        return commentMapper.selectCommentById(id);
    }

    /**
     * 查询博客评论列表
     * 
     * @param comment 博客评论
     * @return 博客评论
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增博客评论
     * 
     * @param comment 博客评论
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment)
    {
        comment.setCreateTime(DateUtils.getNowDate());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改博客评论
     * 
     * @param comment 博客评论
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除博客评论
     * 
     * @param ids 需要删除的博客评论主键
     * @return 结果
     */
    @Override
    public int deleteCommentByIds(Long[] ids)
    {
        return commentMapper.deleteCommentByIds(ids);
    }

    /**
     * 删除博客评论信息
     * 
     * @param id 博客评论主键
     * @return 结果
     */
    @Override
    public int deleteCommentById(Long id)
    {
        return commentMapper.deleteCommentById(id);
    }
}
