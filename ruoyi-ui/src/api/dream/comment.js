import request from '@/utils/request'

// 查询博客评论列表
export function listComment(query) {
  return request({
    url: '/dream/comment/list',
    method: 'get',
    params: query
  })
}

// 查询博客评论详细
export function getComment(id) {
  return request({
    url: '/dream/comment/' + id,
    method: 'get'
  })
}

// 新增博客评论
export function addComment(data) {
  return request({
    url: '/dream/comment',
    method: 'post',
    data: data
  })
}

// 修改博客评论
export function updateComment(data) {
  return request({
    url: '/dream/comment',
    method: 'put',
    data: data
  })
}

// 删除博客评论
export function delComment(id) {
  return request({
    url: '/dream/comment/' + id,
    method: 'delete'
  })
}
