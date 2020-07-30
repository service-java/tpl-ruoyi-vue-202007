import request from '@/utils/request'

// 查询特征项列表
export function listSysitem(query) {
  return request({
    url: '/ctag/sysitem/list',
    method: 'get',
    params: query
  })
}

// 查询特征项详细
export function getSysitem(id) {
  return request({
    url: '/ctag/sysitem/' + id,
    method: 'get'
  })
}

// 新增特征项
export function addSysitem(data) {
  return request({
    url: '/ctag/sysitem',
    method: 'post',
    data: data
  })
}

// 修改特征项
export function updateSysitem(data) {
  return request({
    url: '/ctag/sysitem',
    method: 'put',
    data: data
  })
}

// 删除特征项
export function delSysitem(id) {
  return request({
    url: '/ctag/sysitem/' + id,
    method: 'delete'
  })
}

// 导出特征项
export function exportSysitem(query) {
  return request({
    url: '/ctag/sysitem/export',
    method: 'get',
    params: query
  })
}
