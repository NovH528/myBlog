package com.ruoyi.dream.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dream.mapper.CollectFolderMapper;
import com.ruoyi.dream.domain.CollectFolder;
import com.ruoyi.dream.service.ICollectFolderService;

/**
 * 收藏文件夹Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@Service
public class CollectFolderServiceImpl implements ICollectFolderService 
{
    @Autowired
    private CollectFolderMapper collectFolderMapper;

    /**
     * 查询收藏文件夹
     * 
     * @param id 收藏文件夹主键
     * @return 收藏文件夹
     */
    @Override
    public CollectFolder selectCollectFolderById(Long id)
    {
        return collectFolderMapper.selectCollectFolderById(id);
    }

    /**
     * 查询收藏文件夹列表
     * 
     * @param collectFolder 收藏文件夹
     * @return 收藏文件夹
     */
    @Override
    public List<CollectFolder> selectCollectFolderList(CollectFolder collectFolder)
    {
        return collectFolderMapper.selectCollectFolderList(collectFolder);
    }

    /**
     * 新增收藏文件夹
     * 
     * @param collectFolder 收藏文件夹
     * @return 结果
     */
    @Override
    public int insertCollectFolder(CollectFolder collectFolder)
    {
        collectFolder.setCreateTime(DateUtils.getNowDate());
        return collectFolderMapper.insertCollectFolder(collectFolder);
    }

    /**
     * 修改收藏文件夹
     * 
     * @param collectFolder 收藏文件夹
     * @return 结果
     */
    @Override
    public int updateCollectFolder(CollectFolder collectFolder)
    {
        return collectFolderMapper.updateCollectFolder(collectFolder);
    }

    /**
     * 批量删除收藏文件夹
     * 
     * @param ids 需要删除的收藏文件夹主键
     * @return 结果
     */
    @Override
    public int deleteCollectFolderByIds(Long[] ids)
    {
        return collectFolderMapper.deleteCollectFolderByIds(ids);
    }

    /**
     * 删除收藏文件夹信息
     * 
     * @param id 收藏文件夹主键
     * @return 结果
     */
    @Override
    public int deleteCollectFolderById(Long id)
    {
        return collectFolderMapper.deleteCollectFolderById(id);
    }
}
