import request from '@/utils/request'

// 查询博客媒体列表
export function listMedia(query) {
  return request({
    url: '/dream/media/list',
    method: 'get',
    params: query
  })
}

// 查询博客媒体详细
export function getMedia(id) {
  return request({
    url: '/dream/media/' + id,
    method: 'get'
  })
}

// 新增博客媒体
export function addMedia(data) {
  return request({
    url: '/dream/media',
    method: 'post',
    data: data
  })
}

// 修改博客媒体
export function updateMedia(data) {
  return request({
    url: '/dream/media',
    method: 'put',
    data: data
  })
}

// 删除博客媒体
export function delMedia(id) {
  return request({
    url: '/dream/media/' + id,
    method: 'delete'
  })
}
