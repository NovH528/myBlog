package com.ruoyi.dream.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客管理对象 blog
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public class Blog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 博客ID */
    private Long id;

    /** 博客标题 */
    @Excel(name = "博客标题")
    private String title;

    /** 博客内容 */
    @Excel(name = "博客内容")
    private String content;

    /** 摘要 */
    @Excel(name = "摘要")
    private String summary;

    /** 封面图URL */
    @Excel(name = "封面图URL")
    private String coverUrl;

    /** 作者ID */
    @Excel(name = "作者ID")
    private Long authorId;

    /** 1-公共，2-私人 */
    @Excel(name = "1-公共，2-私人")
    private Long visibility;

    /** 是否置顶（0-不置顶，1-置顶） */
    @Excel(name = "是否置顶", readConverterExp = "0=-不置顶，1-置顶")
    private Long isTop;

    /** 置顶时间（用于排序） */
    @Excel(name = "置顶时间", readConverterExp = "用=于排序")
    private Date topTime;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long viewCount;

    /** 喜欢数 */
    @Excel(name = "喜欢数")
    private Long likeCount;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Long collectCount;

    /** 分享数 */
    @Excel(name = "分享数")
    private Long shareCount;

    /** 0-草稿，1-发布，2-下架 */
    @Excel(name = "0-草稿，1-发布，2-下架")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }

    public void setCoverUrl(String coverUrl) 
    {
        this.coverUrl = coverUrl;
    }

    public String getCoverUrl() 
    {
        return coverUrl;
    }

    public void setAuthorId(Long authorId) 
    {
        this.authorId = authorId;
    }

    public Long getAuthorId() 
    {
        return authorId;
    }

    public void setVisibility(Long visibility) 
    {
        this.visibility = visibility;
    }

    public Long getVisibility() 
    {
        return visibility;
    }

    public void setIsTop(Long isTop) 
    {
        this.isTop = isTop;
    }

    public Long getIsTop() 
    {
        return isTop;
    }

    public void setTopTime(Date topTime) 
    {
        this.topTime = topTime;
    }

    public Date getTopTime() 
    {
        return topTime;
    }

    public void setViewCount(Long viewCount) 
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount() 
    {
        return viewCount;
    }

    public void setLikeCount(Long likeCount) 
    {
        this.likeCount = likeCount;
    }

    public Long getLikeCount() 
    {
        return likeCount;
    }

    public void setCollectCount(Long collectCount) 
    {
        this.collectCount = collectCount;
    }

    public Long getCollectCount() 
    {
        return collectCount;
    }

    public void setShareCount(Long shareCount) 
    {
        this.shareCount = shareCount;
    }

    public Long getShareCount() 
    {
        return shareCount;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("summary", getSummary())
            .append("coverUrl", getCoverUrl())
            .append("authorId", getAuthorId())
            .append("visibility", getVisibility())
            .append("isTop", getIsTop())
            .append("topTime", getTopTime())
            .append("viewCount", getViewCount())
            .append("likeCount", getLikeCount())
            .append("collectCount", getCollectCount())
            .append("shareCount", getShareCount())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
