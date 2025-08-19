package com.ruoyi.dream.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客喜欢对象 blog_like
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public class BlogLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 喜欢ID */
    private Long id;

    /** 被喜欢博客ID */
    @Excel(name = "被喜欢博客ID")
    private Long blogId;

    /** 操作用户ID */
    @Excel(name = "操作用户ID")
    private Long userId;

    /** 点赞时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "点赞时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date likeTime;

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

    public void setLikeTime(Date likeTime) 
    {
        this.likeTime = likeTime;
    }

    public Date getLikeTime() 
    {
        return likeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("blogId", getBlogId())
            .append("userId", getUserId())
            .append("likeTime", getLikeTime())
            .toString();
    }
}
