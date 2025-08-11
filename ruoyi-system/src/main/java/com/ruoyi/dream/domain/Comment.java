package com.ruoyi.dream.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客评论对象 comment
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Long id;

    /** 关联博客ID */
    @Excel(name = "关联博客ID")
    private Long blogId;

    /** 评论用户ID */
    @Excel(name = "评论用户ID")
    private Long userId;

    /** 文字内容 */
    @Excel(name = "文字内容")
    private String content;

    /** 用户上传的图片URL */
    @Excel(name = "用户上传的图片URL")
    private String imageUrls;

    /** 父评论ID */
    @Excel(name = "父评论ID")
    private Long parentId;

    /** 被回复用户ID */
    @Excel(name = "被回复用户ID")
    private Long replyToUserId;

    /** 评论点赞数 */
    @Excel(name = "评论点赞数")
    private Long likeCount;

    /** 0-正常，1-删除 */
    @Excel(name = "0-正常，1-删除")
    private Long isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setBlogId(Long blogId) 
    {
        this.blogId = blogId;
    }

    public Long getBlogId() 
    {
        return blogId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setImageUrls(String imageUrls) 
    {
        this.imageUrls = imageUrls;
    }

    public String getImageUrls() 
    {
        return imageUrls;
    }

    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    public void setReplyToUserId(Long replyToUserId) 
    {
        this.replyToUserId = replyToUserId;
    }

    public Long getReplyToUserId() 
    {
        return replyToUserId;
    }

    public void setLikeCount(Long likeCount) 
    {
        this.likeCount = likeCount;
    }

    public Long getLikeCount() 
    {
        return likeCount;
    }

    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("blogId", getBlogId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("imageUrls", getImageUrls())
            .append("parentId", getParentId())
            .append("replyToUserId", getReplyToUserId())
            .append("likeCount", getLikeCount())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .toString();
    }
}
