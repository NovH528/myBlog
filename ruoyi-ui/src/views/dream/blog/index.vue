<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="博客标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入博客标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="封面图URL" prop="coverUrl">
        <el-input
          v-model="queryParams.coverUrl"
          placeholder="请输入封面图URL"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者ID" prop="authorId">
        <el-input
          v-model="queryParams.authorId"
          placeholder="请输入作者ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="置顶时间" prop="topTime">
        <el-date-picker clearable
          v-model="queryParams.topTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择置顶时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="浏览量" prop="viewCount">
        <el-input
          v-model="queryParams.viewCount"
          placeholder="请输入浏览量"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="喜欢数" prop="likeCount">
        <el-input
          v-model="queryParams.likeCount"
          placeholder="请输入喜欢数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收藏数" prop="collectCount">
        <el-input
          v-model="queryParams.collectCount"
          placeholder="请输入收藏数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分享数" prop="shareCount">
        <el-input
          v-model="queryParams.shareCount"
          placeholder="请输入分享数"
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
          v-hasPermi="['dream:blog:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['dream:blog:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['dream:blog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['dream:blog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="blogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="博客ID" align="center" prop="id" />
      <el-table-column label="博客标题" align="center" prop="title" />
      <el-table-column label="博客内容" align="center" prop="content" />
      <el-table-column label="摘要" align="center" prop="summary" />
      <el-table-column label="封面图URL" align="center" prop="coverUrl" />
      <el-table-column label="作者ID" align="center" prop="authorId" />
      <el-table-column label="1-公共，2-私人" align="center" prop="visibility" />
      <el-table-column label="是否置顶" align="center" prop="isTop" />
      <el-table-column label="置顶时间" align="center" prop="topTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.topTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="浏览量" align="center" prop="viewCount" />
      <el-table-column label="喜欢数" align="center" prop="likeCount" />
      <el-table-column label="收藏数" align="center" prop="collectCount" />
      <el-table-column label="分享数" align="center" prop="shareCount" />
      <el-table-column label="0-草稿，1-发布，2-下架" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['dream:blog:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['dream:blog:remove']">删除</el-button>
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

    <!-- 添加或修改博客管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="blogRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="博客标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入博客标题" />
        </el-form-item>
        <el-form-item label="博客内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="摘要" prop="summary">
          <el-input v-model="form.summary" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="封面图URL" prop="coverUrl">
          <el-input v-model="form.coverUrl" placeholder="请输入封面图URL" />
        </el-form-item>
        <el-form-item label="作者ID" prop="authorId">
          <el-input v-model="form.authorId" placeholder="请输入作者ID" />
        </el-form-item>
        <el-form-item label="置顶时间" prop="topTime">
          <el-date-picker clearable
            v-model="form.topTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择置顶时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="浏览量" prop="viewCount">
          <el-input v-model="form.viewCount" placeholder="请输入浏览量" />
        </el-form-item>
        <el-form-item label="喜欢数" prop="likeCount">
          <el-input v-model="form.likeCount" placeholder="请输入喜欢数" />
        </el-form-item>
        <el-form-item label="收藏数" prop="collectCount">
          <el-input v-model="form.collectCount" placeholder="请输入收藏数" />
        </el-form-item>
        <el-form-item label="分享数" prop="shareCount">
          <el-input v-model="form.shareCount" placeholder="请输入分享数" />
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

<script setup name="Blog">
import { listBlog, getBlog, delBlog, addBlog, updateBlog } from "@/api/dream/blog"

const { proxy } = getCurrentInstance()

const blogList = ref([])
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
    title: null,
    content: null,
    summary: null,
    coverUrl: null,
    authorId: null,
    visibility: null,
    isTop: null,
    topTime: null,
    viewCount: null,
    likeCount: null,
    collectCount: null,
    shareCount: null,
    status: null,
  },
  rules: {
    title: [
      { required: true, message: "博客标题不能为空", trigger: "blur" }
    ],
    content: [
      { required: true, message: "博客内容不能为空", trigger: "blur" }
    ],
    authorId: [
      { required: true, message: "作者ID不能为空", trigger: "blur" }
    ],
    visibility: [
      { required: true, message: "1-公共，2-私人不能为空", trigger: "change" }
    ],
    isTop: [
      { required: true, message: "是否置顶不能为空", trigger: "change" }
    ],
    status: [
      { required: true, message: "0-草稿，1-发布，2-下架不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询博客管理列表 */
function getList() {
  loading.value = true
  listBlog(queryParams.value).then(response => {
    blogList.value = response.rows
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
    title: null,
    content: null,
    summary: null,
    coverUrl: null,
    authorId: null,
    visibility: null,
    isTop: null,
    topTime: null,
    viewCount: null,
    likeCount: null,
    collectCount: null,
    shareCount: null,
    status: null,
    createTime: null,
    updateTime: null
  }
  proxy.resetForm("blogRef")
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
  title.value = "添加博客管理"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getBlog(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改博客管理"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["blogRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateBlog(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addBlog(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除博客管理编号为"' + _ids + '"的数据项？').then(function() {
    return delBlog(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('dream/blog/export', {
    ...queryParams.value
  }, `blog_${new Date().getTime()}.xlsx`)
}

getList()
</script>
