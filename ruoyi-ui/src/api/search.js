import request from '@/utils/request';

// 获取搜索结果（对接后端代理接口）
export function getSearchResult(keyword) {
  return request({
    url: '/search/proxy',
    method: 'get',
    params: { keyword }
  });
}
