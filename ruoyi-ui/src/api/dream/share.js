import request from '@/utils/request'

// 查询博客分享列表
export function listShare(query) {
  return request({
    url: '/dream/share/list',
    method: 'get',
    params: query
  })
}

// 查询博客分享详细
export function getShare(id) {
  return request({
    url: '/dream/share/' + id,
    method: 'get'
  })
}

// 新增博客分享
export function addShare(data) {
  return request({
    url: '/dream/share',
    method: 'post',
    data: data
  })
}

// 修改博客分享
export function updateShare(data) {
  return request({
    url: '/dream/share',
    method: 'put',
    data: data
  })
}

// 删除博客分享
export function delShare(id) {
  return request({
    url: '/dream/share/' + id,
    method: 'delete'
  })
}
