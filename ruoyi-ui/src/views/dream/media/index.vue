<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关联博客ID" prop="blogId">
        <el-input
          v-model="queryParams.blogId"
          placeholder="请输入关联博客ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="媒体URL" prop="mediaUrl">
        <el-input
          v-model="queryParams.mediaUrl"
          placeholder="请输入媒体URL"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件名字" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入文件名字"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="字节数" prop="fileSize">
        <el-input
          v-model="queryParams.fileSize"
          placeholder="请输入字节数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入排序"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" prop="uploadTime">
        <el-date-picker clearable
          v-model="queryParams.uploadTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['dream:media:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['dream:media:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['dream:media:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['dream:media:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mediaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="媒体ID" align="center" prop="id" />
      <el-table-column label="关联博客ID" align="center" prop="blogId" />
      <el-table-column label="媒体URL" align="center" prop="mediaUrl" />
      <el-table-column label="类型" align="center" prop="mediaType" />
      <el-table-column label="文件名字" align="center" prop="fileName" />
      <el-table-column label="字节数" align="center" prop="fileSize" />
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['dream:media:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['dream:media:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改博客媒体对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="mediaRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联博客ID" prop="blogId">
          <el-input v-model="form.blogId" placeholder="请输入关联博客ID" />
        </el-form-item>
        <el-form-item label="媒体URL" prop="mediaUrl">
          <el-input v-model="form.mediaUrl" placeholder="请输入媒体URL" />
        </el-form-item>
        <el-form-item label="文件名字" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名字" />
        </el-form-item>
        <el-form-item label="字节数" prop="fileSize">
          <el-input v-model="form.fileSize" placeholder="请输入字节数" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="上传时间" prop="uploadTime">
          <el-date-picker clearable
            v-model="form.uploadTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择上传时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Media">
import { listMedia, getMedia, delMedia, addMedia, updateMedia } from "@/api/dream/media"

const { proxy } = getCurrentInstance()

const mediaList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    blogId: null,
    mediaUrl: null,
    mediaType: null,
    fileName: null,
    fileSize: null,
    sort: null,
    uploadTime: null
  },
  rules: {
    blogId: [
      { required: true, message: "关联博客ID不能为空", trigger: "blur" }
    ],
    mediaUrl: [
      { required: true, message: "媒体URL不能为空", trigger: "blur" }
    ],
    mediaType: [
      { required: true, message: "类型不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询博客媒体列表 */
function getList() {
  loading.value = true
  listMedia(queryParams.value).then(response => {
    mediaList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    blogId: null,
    mediaUrl: null,
    mediaType: null,
    fileName: null,
    fileSize: null,
    sort: null,
    uploadTime: null
  }
  proxy.resetForm("mediaRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加博客媒体"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getMedia(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改博客媒体"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["mediaRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMedia(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addMedia(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除博客媒体编号为"' + _ids + '"的数据项？').then(function() {
    return delMedia(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('dream/media/export', {
    ...queryParams.value
  }, `media_${new Date().getTime()}.xlsx`)
}

getList()
</script>
