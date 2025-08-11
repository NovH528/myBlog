package com.ruoyi.dream.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客媒体对象 blog_media
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public class BlogMedia extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 媒体ID */
    private Long id;

    /** 关联博客ID（逻辑关联blog.id） */
    @Excel(name = "关联博客ID", readConverterExp = "逻=辑关联blog.id")
    private Long blogId;

    /** 媒体URL */
    @Excel(name = "媒体URL")
    private String mediaUrl;

    /** 类型（1-图片，2-视频） */
    @Excel(name = "类型", readConverterExp = "1=-图片，2-视频")
    private Long mediaType;

    /** 文件名字 */
    @Excel(name = "文件名字")
    private String fileName;

    /** 字节数 */
    @Excel(name = "字节数")
    private Long fileSize;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

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

    public void setMediaUrl(String mediaUrl) 
    {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrl() 
    {
        return mediaUrl;
    }

    public void setMediaType(Long mediaType) 
    {
        this.mediaType = mediaType;
    }

    public Long getMediaType() 
    {
        return mediaType;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }

    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("blogId", getBlogId())
            .append("mediaUrl", getMediaUrl())
            .append("mediaType", getMediaType())
            .append("fileName", getFileName())
            .append("fileSize", getFileSize())
            .append("sort", getSort())
            .append("uploadTime", getUploadTime())
            .toString();
    }
}
