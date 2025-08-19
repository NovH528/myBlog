package com.ruoyi.dream.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客分享对象 blog_share
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public class BlogShare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分享ID */
    private Long id;

    /** 被分享的博客ID */
    @Excel(name = "被分享的博客ID")
    private Long blogId;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long userId;

    /** wechat/qq/link */
    @Excel(name = "wechat/qq/link")
    private String sharePlatform;

    /** 分享时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "分享时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shareTime;

    /** 分享IP */
    @Excel(name = "分享IP")
    private String shareIp;

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

    public void setSharePlatform(String sharePlatform) 
    {
        this.sharePlatform = sharePlatform;
    }

    public String getSharePlatform() 
    {
        return sharePlatform;
    }

    public void setShareTime(Date shareTime) 
    {
        this.shareTime = shareTime;
    }

    public Date getShareTime() 
    {
        return shareTime;
    }

    public void setShareIp(String shareIp) 
    {
        this.shareIp = shareIp;
    }

    public String getShareIp() 
    {
        return shareIp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("blogId", getBlogId())
            .append("userId", getUserId())
            .append("sharePlatform", getSharePlatform())
            .append("shareTime", getShareTime())
            .append("shareIp", getShareIp())
            .toString();
    }
}
