import request from '@/utils/request'

// 查询特征项匹配详情列表
export function listDetail(query) {
  return request({
    url: '/ctag/detail/list',
    method: 'get',
    params: query
  })
}

// 查询特征项匹配详情详细
export function getDetail(id) {
  return request({
    url: '/ctag/detail/' + id,
    method: 'get'
  })
}

// 新增特征项匹配详情
export function addDetail(data) {
  return request({
    url: '/ctag/detail',
    method: 'post',
    data: data
  })
}

// 修改特征项匹配详情
export function updateDetail(data) {
  return request({
    url: '/ctag/detail',
    method: 'put',
    data: data
  })
}

// 删除特征项匹配详情
export function delDetail(id) {
  return request({
    url: '/ctag/detail/' + id,
    method: 'delete'
  })
}

// 导出特征项匹配详情
export function exportDetail(query) {
  return request({
    url: '/ctag/detail/export',
    method: 'get',
    params: query
  })
}
