<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="被分享的博客ID" prop="blogId">
        <el-input
          v-model="queryParams.blogId"
          placeholder="请输入被分享的博客ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入操作人ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="wechat/qq/link" prop="sharePlatform">
        <el-input
          v-model="queryParams.sharePlatform"
          placeholder="请输入wechat/qq/link"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分享时间" prop="shareTime">
        <el-date-picker clearable
          v-model="queryParams.shareTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择分享时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="分享IP" prop="shareIp">
        <el-input
          v-model="queryParams.shareIp"
          placeholder="请输入分享IP"
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
          v-hasPermi="['dream:share:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['dream:share:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['dream:share:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['dream:share:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="shareList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="分享ID" align="center" prop="id" />
      <el-table-column label="被分享的博客ID" align="center" prop="blogId" />
      <el-table-column label="操作人ID" align="center" prop="userId" />
      <el-table-column label="wechat/qq/link" align="center" prop="sharePlatform" />
      <el-table-column label="分享时间" align="center" prop="shareTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.shareTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分享IP" align="center" prop="shareIp" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['dream:share:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['dream:share:remove']">删除</el-button>
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

    <!-- 添加或修改博客分享对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="shareRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="被分享的博客ID" prop="blogId">
          <el-input v-model="form.blogId" placeholder="请输入被分享的博客ID" />
        </el-form-item>
        <el-form-item label="操作人ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入操作人ID" />
        </el-form-item>
        <el-form-item label="wechat/qq/link" prop="sharePlatform">
          <el-input v-model="form.sharePlatform" placeholder="请输入wechat/qq/link" />
        </el-form-item>
        <el-form-item label="分享时间" prop="shareTime">
          <el-date-picker clearable
            v-model="form.shareTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择分享时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="分享IP" prop="shareIp">
          <el-input v-model="form.shareIp" placeholder="请输入分享IP" />
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

<script setup name="Share">
import { listShare, getShare, delShare, addShare, updateShare } from "@/api/dream/share"

const { proxy } = getCurrentInstance()

const shareList = ref([])
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
    sharePlatform: null,
    shareTime: null,
    shareIp: null
  },
  rules: {
    blogId: [
      { required: true, message: "被分享的博客ID不能为空", trigger: "blur" }
    ],
    userId: [
      { required: true, message: "操作人ID不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询博客分享列表 */
function getList() {
  loading.value = true
  listShare(queryParams.value).then(response => {
    shareList.value = response.rows
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
    sharePlatform: null,
    shareTime: null,
    shareIp: null
  }
  proxy.resetForm("shareRef")
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
  title.value = "添加博客分享"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getShare(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改博客分享"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["shareRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateShare(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addShare(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除博客分享编号为"' + _ids + '"的数据项？').then(function() {
    return delShare(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('dream/share/export', {
    ...queryParams.value
  }, `share_${new Date().getTime()}.xlsx`)
}

getList()
</script>
