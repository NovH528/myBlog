import request from '@/utils/request'

// 查询博客收藏列表
export function listCollect(query) {
  return request({
    url: '/dream/collect/list',
    method: 'get',
    params: query
  })
}

// 查询博客收藏详细
export function getCollect(id) {
  return request({
    url: '/dream/collect/' + id,
    method: 'get'
  })
}

// 新增博客收藏
export function addCollect(data) {
  return request({
    url: '/dream/collect',
    method: 'post',
    data: data
  })
}

// 修改博客收藏
export function updateCollect(data) {
  return request({
    url: '/dream/collect',
    method: 'put',
    data: data
  })
}

// 删除博客收藏
export function delCollect(id) {
  return request({
    url: '/dream/collect/' + id,
    method: 'delete'
  })
}
