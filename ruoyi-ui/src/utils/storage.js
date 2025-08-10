// 存储键名（避免重复）
const SEARCH_HISTORY_KEY = 'blog_search_history';

export default {
  // 保存历史记录（最多10条，去重）
  saveHistory(keyword) {
    if (!keyword) return;
    const history = this.getHistory() || [];
    // 去重（如果已存在，移到最前面）
    const index = history.indexOf(keyword);
    if (index > -1) {
      history.splice(index, 1);
    }
    // 插入到开头
    history.unshift(keyword);
    // 限制最多10条
    if (history.length > 10) {
      history.pop();
    }
    localStorage.setItem(SEARCH_HISTORY_KEY, JSON.stringify(history));
  },

  // 获取历史记录
  getHistory() {
    const history = localStorage.getItem(SEARCH_HISTORY_KEY);
    return history ? JSON.parse(history) : [];
  },

  // 清空历史记录
  clearHistory() {
    localStorage.removeItem(SEARCH_HISTORY_KEY);
  },

  // 删除单条历史记录
  deleteHistory(keyword) {
    const history = this.getHistory();
    const newHistory = history.filter(item => item !== keyword);
    localStorage.setItem(SEARCH_HISTORY_KEY, JSON.stringify(newHistory));
  }
};
