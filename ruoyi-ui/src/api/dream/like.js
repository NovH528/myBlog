import request from '@/utils/request'

// 查询博客喜欢列表
export function listLike(query) {
  return request({
    url: '/dream/like/list',
    method: 'get',
    params: query
  })
}

// 查询博客喜欢详细
export function getLike(id) {
  return request({
    url: '/dream/like/' + id,
    method: 'get'
  })
}

// 新增博客喜欢
export function addLike(data) {
  return request({
    url: '/dream/like',
    method: 'post',
    data: data
  })
}

// 修改博客喜欢
export function updateLike(data) {
  return request({
    url: '/dream/like',
    method: 'put',
    data: data
  })
}

// 删除博客喜欢
export function delLike(id) {
  return request({
    url: '/dream/like/' + id,
    method: 'delete'
  })
}
