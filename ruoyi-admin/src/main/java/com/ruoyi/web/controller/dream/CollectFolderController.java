package com.ruoyi.web.controller.dream;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dream.domain.CollectFolder;
import com.ruoyi.dream.service.ICollectFolderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收藏文件夹Controller
 * 
 * @author ruoyi
 * @date 2025-08-17
 */
@RestController
@RequestMapping("/dream/folder")
public class CollectFolderController extends BaseController
{
    @Autowired
    private ICollectFolderService collectFolderService;

    /**
     * 查询收藏文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('dream:folder:list')")
    @GetMapping("/list")
    public TableDataInfo list(CollectFolder collectFolder)
    {
        startPage();
        List<CollectFolder> list = collectFolderService.selectCollectFolderList(collectFolder);
        return getDataTable(list);
    }

    /**
     * 导出收藏文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('dream:folder:export')")
    @Log(title = "收藏文件夹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CollectFolder collectFolder)
    {
        List<CollectFolder> list = collectFolderService.selectCollectFolderList(collectFolder);
        ExcelUtil<CollectFolder> util = new ExcelUtil<CollectFolder>(CollectFolder.class);
        util.exportExcel(response, list, "收藏文件夹数据");
    }

    /**
     * 获取收藏文件夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('dream:folder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(collectFolderService.selectCollectFolderById(id));
    }

    /**
     * 新增收藏文件夹
     */
    @PreAuthorize("@ss.hasPermi('dream:folder:add')")
    @Log(title = "收藏文件夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CollectFolder collectFolder)
    {
        return toAjax(collectFolderService.insertCollectFolder(collectFolder));
    }

    /**
     * 修改收藏文件夹
     */
    @PreAuthorize("@ss.hasPermi('dream:folder:edit')")
    @Log(title = "收藏文件夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CollectFolder collectFolder)
    {
        return toAjax(collectFolderService.updateCollectFolder(collectFolder));
    }

    /**
     * 删除收藏文件夹
     */
    @PreAuthorize("@ss.hasPermi('dream:folder:remove')")
    @Log(title = "收藏文件夹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(collectFolderService.deleteCollectFolderByIds(ids));
    }
}
