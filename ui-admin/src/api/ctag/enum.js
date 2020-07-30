import request from '@/utils/request'

// 查询特征项枚举列表
export function listEnum(query) {
  return request({
    url: '/ctag/enum/list',
    method: 'get',
    params: query
  })
}

// 查询特征项枚举详细
export function getEnum(id) {
  return request({
    url: '/ctag/enum/' + id,
    method: 'get'
  })
}

// 新增特征项枚举
export function addEnum(data) {
  return request({
    url: '/ctag/enum',
    method: 'post',
    data: data
  })
}

// 修改特征项枚举
export function updateEnum(data) {
  return request({
    url: '/ctag/enum',
    method: 'put',
    data: data
  })
}

// 删除特征项枚举
export function delEnum(id) {
  return request({
    url: '/ctag/enum/' + id,
    method: 'delete'
  })
}

// 导出特征项枚举
export function exportEnum(query) {
  return request({
    url: '/ctag/enum/export',
    method: 'get',
    params: query
  })
}
