package com.ruoyi.dream.service;

import java.util.List;
import com.ruoyi.dream.domain.CollectFolder;

/**
 * 收藏文件夹Service接口
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
public interface ICollectFolderService 
{
    /**
     * 查询收藏文件夹
     * 
     * @param id 收藏文件夹主键
     * @return 收藏文件夹
     */
    public CollectFolder selectCollectFolderById(Long id);

    /**
     * 查询收藏文件夹列表
     * 
     * @param collectFolder 收藏文件夹
     * @return 收藏文件夹集合
     */
    public List<CollectFolder> selectCollectFolderList(CollectFolder collectFolder);

    /**
     * 新增收藏文件夹
     * 
     * @param collectFolder 收藏文件夹
     * @return 结果
     */
    public int insertCollectFolder(CollectFolder collectFolder);

    /**
     * 修改收藏文件夹
     * 
     * @param collectFolder 收藏文件夹
     * @return 结果
     */
    public int updateCollectFolder(CollectFolder collectFolder);

    /**
     * 批量删除收藏文件夹
     * 
     * @param ids 需要删除的收藏文件夹主键集合
     * @return 结果
     */
    public int deleteCollectFolderByIds(Long[] ids);

    /**
     * 删除收藏文件夹信息
     * 
     * @param id 收藏文件夹主键
     * @return 结果
     */
    public int deleteCollectFolderById(Long id);
}
