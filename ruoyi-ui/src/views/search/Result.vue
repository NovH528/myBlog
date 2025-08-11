<template>
  <div class="search-result-container">
    <!-- 导航栏 -->
    <div class="result-header">
      <div class="logo" @click="$router.push('/myHome')">myBlog</div>
      <SearchBox @search="handleSearch" :defaultKeyword="keyword" />
    </div>

    <!-- 搜索结果 -->
    <div class="result-content">
      <div class="result-stats" v-if="total > 0">
        找到约 {{ total }} 个结果（{{ timeCost }} 秒）
      </div>

      <div class="result-item" v-for="(item, index) in results" :key="index">
        <a :href="item.url" target="_blank" class="title">{{ item.title }}</a>
        <div class="url">{{ item.url }}</div>
        <div class="summary" v-html="item.summary"></div>
      </div>

      <!-- 无结果 -->
      <div class="no-result" v-if="results.length === 0 && keyword">
        未找到与 "{{ keyword }}" 相关的结果
      </div>
    </div>
  </div>
</template>

<script>
import SearchBox from '@/components/SearchBox/index.vue';
import { getSearchResult } from '@/api/search';

export default {
  name: 'SearchResult',
  components: { SearchBox },
  data() {
    return {
      keyword: this.$route.query.keyword || '',
      results: [],
      total: 0,
      timeCost: 0
    };
  },
  created() {
    if (this.keyword) {
      this.fetchSearchResult();
    }
  },
  methods: {
    handleSearch(keyword) {
      this.$router.push({ path: '/search/result', query: { keyword } });
      this.keyword = keyword;
      this.fetchSearchResult();
    },
    fetchSearchResult() {
      getSearchResult(this.keyword).then(res => {
        if (res.code === 200) {
          this.results = res.data.results;
          this.total = res.data.total;
          this.timeCost = res.data.timeCost;
        }
      });
    }
  }
};
</script>

<style scoped>
/* 样式根据需求自定义 */
.search-result-container {
  width: 1000px;
  margin: 0 auto;
  padding: 20px 0;
}
.result-header {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 30px;
}
.logo {
  font-size: 24px;
  color: #2b79d9;
  cursor: pointer;
}
.result-content {
  margin-left: 120px;
}
.result-item {
  margin-bottom: 20px;
  padding: 10px;
}
.title {
  font-size: 18px;
  color: #1a0dab;
  text-decoration: none;
  margin-bottom: 5px;
  display: block;
}
.title:hover {
  text-decoration: underline;
}
.url {
  color: #006621;
  font-size: 14px;
  margin-bottom: 5px;
}
.summary {
  color: #545454;
  font-size: 14px;
  line-height: 1.5;
}
</style>
