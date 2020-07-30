import request from '@/utils/request'

// 查询特征标记工程列表
export function listProject(query) {
  return request({
    url: '/ctag/project/list',
    method: 'get',
    params: query
  })
}

// 查询特征标记工程详细
export function getProject(id) {
  return request({
    url: '/ctag/project/' + id,
    method: 'get'
  })
}

// 新增特征标记工程
export function addProject(data) {
  return request({
    url: '/ctag/project',
    method: 'post',
    data: data
  })
}

// 修改特征标记工程
export function updateProject(data) {
  return request({
    url: '/ctag/project',
    method: 'put',
    data: data
  })
}

// 删除特征标记工程
export function delProject(id) {
  return request({
    url: '/ctag/project/' + id,
    method: 'delete'
  })
}

// 导出特征标记工程
export function exportProject(query) {
  return request({
    url: '/ctag/project/export',
    method: 'get',
    params: query
  })
}
