<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="被收藏博客ID" prop="blogId">
        <el-input
          v-model="queryParams.blogId"
          placeholder="请输入被收藏博客ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入操作用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收藏时间" prop="collectTime">
        <el-date-picker clearable
          v-model="queryParams.collectTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择收藏时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="收藏文件夹ID" prop="folderId">
        <el-input
          v-model="queryParams.folderId"
          placeholder="请输入收藏文件夹ID"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['dream:collect:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['dream:collect:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['dream:collect:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['dream:collect:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="collectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收藏ID" align="center" prop="id" />
      <el-table-column label="被收藏博客ID" align="center" prop="blogId" />
      <el-table-column label="操作用户ID" align="center" prop="userId" />
      <el-table-column label="收藏时间" align="center" prop="collectTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.collectTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收藏文件夹ID" align="center" prop="folderId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['dream:collect:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['dream:collect:remove']">删除</el-button>
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

    <!-- 添加或修改博客收藏对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="collectRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="被收藏博客ID" prop="blogId">
          <el-input v-model="form.blogId" placeholder="请输入被收藏博客ID" />
        </el-form-item>
        <el-form-item label="操作用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入操作用户ID" />
        </el-form-item>
        <el-form-item label="收藏时间" prop="collectTime">
          <el-date-picker clearable
            v-model="form.collectTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择收藏时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收藏文件夹ID" prop="folderId">
          <el-input v-model="form.folderId" placeholder="请输入收藏文件夹ID" />
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

<script setup name="Collect">
import { listCollect, getCollect, delCollect, addCollect, updateCollect } from "@/api/dream/collect"

const { proxy } = getCurrentInstance()

const collectList = ref([])
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
    userId: null,
    collectTime: null,
    folderId: null
  },
  rules: {
    blogId: [
      { required: true, message: "被收藏博客ID不能为空", trigger: "blur" }
    ],
    userId: [
      { required: true, message: "操作用户ID不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询博客收藏列表 */
function getList() {
  loading.value = true
  listCollect(queryParams.value).then(response => {
    collectList.value = response.rows
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
    userId: null,
    collectTime: null,
    folderId: null
  }
  proxy.resetForm("collectRef")
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
  title.value = "添加博客收藏"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getCollect(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改博客收藏"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["collectRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCollect(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addCollect(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除博客收藏编号为"' + _ids + '"的数据项？').then(function() {
    return delCollect(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('dream/collect/export', {
    ...queryParams.value
  }, `collect_${new Date().getTime()}.xlsx`)
}

getList()
</script>
