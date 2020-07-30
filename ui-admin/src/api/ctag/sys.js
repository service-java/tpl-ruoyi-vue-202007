import request from '@/utils/request'

// 查询特征体系列表
export function listSys(query) {
  return request({
    url: '/ctag/sys/list',
    method: 'get',
    params: query
  })
}

// 查询特征体系详细
export function getSys(id) {
  return request({
    url: '/ctag/sys/' + id,
    method: 'get'
  })
}

// 新增特征体系
export function addSys(data) {
  return request({
    url: '/ctag/sys',
    method: 'post',
    data: data
  })
}

// 修改特征体系
export function updateSys(data) {
  return request({
    url: '/ctag/sys',
    method: 'put',
    data: data
  })
}

// 删除特征体系
export function delSys(id) {
  return request({
    url: '/ctag/sys/' + id,
    method: 'delete'
  })
}

// 导出特征体系
export function exportSys(query) {
  return request({
    url: '/ctag/sys/export',
    method: 'get',
    params: query
  })
}
