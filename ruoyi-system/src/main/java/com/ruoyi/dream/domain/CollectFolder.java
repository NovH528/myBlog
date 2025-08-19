package com.ruoyi.dream.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收藏文件夹对象 collect_folder
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public class CollectFolder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件夹ID */
    private Long id;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long userId;

    /** 文件夹名称（如“技术教程”） */
    @Excel(name = "文件夹名称", readConverterExp = "如=“技术教程”")
    private String name;

    /** 父文件夹ID（0-顶级文件夹） */
    @Excel(name = "父文件夹ID", readConverterExp = "0=-顶级文件夹")
    private Long parentId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("name", getName())
            .append("parentId", getParentId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
