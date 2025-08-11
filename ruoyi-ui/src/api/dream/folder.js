import request from '@/utils/request'

// 查询收藏文件夹列表
export function listFolder(query) {
  return request({
    url: '/dream/folder/list',
    method: 'get',
    params: query
  })
}

// 查询收藏文件夹详细
export function getFolder(id) {
  return request({
    url: '/dream/folder/' + id,
    method: 'get'
  })
}

// 新增收藏文件夹
export function addFolder(data) {
  return request({
    url: '/dream/folder',
    method: 'post',
    data: data
  })
}

// 修改收藏文件夹
export function updateFolder(data) {
  return request({
    url: '/dream/folder',
    method: 'put',
    data: data
  })
}

// 删除收藏文件夹
export function delFolder(id) {
  return request({
    url: '/dream/folder/' + id,
    method: 'delete'
  })
}
