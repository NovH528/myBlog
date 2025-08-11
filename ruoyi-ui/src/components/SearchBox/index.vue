<template>
  <div class="search-container">
    <div class="search-input-box">
      <input
        type="text"
        v-model="keyword"
        placeholder="搜点什么吧"
        @keyup.enter="handleSearch"
        @focus="showHistory = true"
        @input="handleInput"
      >
      <i class="el-icon-search" @click="handleSearch"></i>
      <i v-if="keyword" class="el-icon-circle-close" @click="keyword = ''"></i>
    </div>

    <div class="search-history" v-if="showHistory && historyList.length">
      <div class="history-header">
        <span>搜索历史</span>
        <i class="el-icon-delete" @click="clearHistory">清空</i>
      </div>
      <ul class="history-list">
        <li
          v-for="(item, index) in historyList"
          :key="index"
          @click="handleHistoryClick(item)"
        >
          <i class="el-icon-time"></i>
          <span>{{ item }}</span>
          <i class="el-icon-close" @click.stop="deleteHistory(item)"></i>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import { debounce } from 'lodash';

export default {
  name: 'SearchBox',
  data() {
    return {
      keyword: '',
      showHistory: false
    };
  },
  computed: {
    ...mapState('searchHistory', ['historyList'])
  },
  created() {
    this.debouncedInput = debounce(this.handleDebounceInput, 300);
  },
  // 合并所有方法到一个methods中（核心修正）
  methods: {
    ...mapActions('searchHistory', ['addHistory', 'clearHistory', 'deleteHistory']),

    // 处理搜索
    handleSearch() {
      const keyword = this.keyword.trim();
      if (!keyword) return;
      this.addHistory(keyword);

      // 方案1：直接跳转到百度搜索结果页
      window.open(`https://www.baidu.com/s?wd=${encodeURIComponent(keyword)}`, '_blank');

      // 方案2：通过后端代理（避免跨域），后续讲解
      // this.$emit('search', keyword);

      this.showHistory = false;
    },

    // 输入事件
    handleInput() {
      this.debouncedInput();
    },

    // 防抖处理
    handleDebounceInput() {},

    // 点击历史记录
    handleHistoryClick(item) {
      this.keyword = item;
      this.handleSearch();
    },

    // 隐藏历史记录弹窗
    hideHistory(e) {
      if (!this.$el.contains(e.target)) {
        this.showHistory = false;
        document.removeEventListener('click', this.hideHistory);
      }
    }
  },
  watch: {
    showHistory(val) {
      if (val) {
        document.addEventListener('click', this.hideHistory);
      }
    }
  },
  beforeDestroy() {
    document.removeEventListener('click', this.hideHistory);
  }
};
</script>

<style scoped>
/* 样式部分不变 */
.search-container {
  position: relative;
  width: 100%;
}

.search-input-box {
  position: relative;
  width: 100%;
}

.search-input-box input {
  width: 100%;
  padding: 18px 45px 18px 25px;
  border: none;
  border-radius: 30px;
  outline: none;
  font-size: 18px;
  box-shadow: 0 4px 15px rgba(0,0,0,0.2);
}

.el-icon-search {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
  font-size: 22px;
  color: #666;
}

.el-icon-circle-close {
  position: absolute;
  right: 50px;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
  font-size: 18px;
  color: #999;
}

.search-history {
  position: absolute;
  top: 60px;
  left: 0;
  width: 100%;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.15);
  padding: 15px;
  z-index: 100;
  color: #333; /* 设置为深色，与背景区分开，可根据实际设计调整 */
}

.history-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
  color: #666;
  font-size: 14px;
}

.history-header .el-icon-delete {
  cursor: pointer;
  font-size: 14px;
}

.history-list {
  list-style: none;
  margin: 0;
  padding: 0;
}

.history-list li {
  display: flex;
  align-items: center;
  padding: 8px 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: background 0.2s;
}

.history-list li:hover {
  background: #f5f7fa;
}

.history-list li .el-icon-time {
  color: #ccc;
  margin-right: 10px;
  font-size: 14px;
}

.history-list li .el-icon-close {
  margin-left: auto;
  color: #ccc;
  font-size: 14px;
  opacity: 0;
  transition: opacity 0.2s;
}

.history-list li:hover .el-icon-close {
  opacity: 1;
}

@media (max-width: 768px) {
  .search-input-box input {
    font-size: 16px;
    padding: 15px 40px 15px 20px;
  }
}
</style>
