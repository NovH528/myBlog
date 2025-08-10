import storage from '@/utils/storage';

const state = {
  // 从本地存储初始化历史记录
  historyList: storage.getHistory()
};

const mutations = {
  // 更新历史记录列表
  UPDATE_HISTORY(state, list) {
    state.historyList = list;
  }
};

const actions = {
  // 添加搜索历史
  addHistory({ commit }, keyword) {
    storage.saveHistory(keyword);
    commit('UPDATE_HISTORY', storage.getHistory());
  },

  // 清空搜索历史
  clearHistory({ commit }) {
    storage.clearHistory();
    commit('UPDATE_HISTORY', []);
  },

  // 删除单条历史
  deleteHistory({ commit }, keyword) {
    storage.deleteHistory(keyword);
    commit('UPDATE_HISTORY', storage.getHistory());
  }
};

export default {
  namespaced: true, // 开启命名空间，避免状态冲突
  state,
  mutations,
  actions
};
