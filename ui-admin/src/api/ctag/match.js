import request from '@/utils/request'

// 查询特征项匹配列表
export function listMatch(query) {
  return request({
    url: '/ctag/match/list',
    method: 'get',
    params: query
  })
}

// 查询特征项匹配详细
export function getMatch(id) {
  return request({
    url: '/ctag/match/' + id,
    method: 'get'
  })
}

// 新增特征项匹配
export function addMatch(data) {
  return request({
    url: '/ctag/match',
    method: 'post',
    data: data
  })
}

// 修改特征项匹配
export function updateMatch(data) {
  return request({
    url: '/ctag/match',
    method: 'put',
    data: data
  })
}

// 删除特征项匹配
export function delMatch(id) {
  return request({
    url: '/ctag/match/' + id,
    method: 'delete'
  })
}

// 导出特征项匹配
export function exportMatch(query) {
  return request({
    url: '/ctag/match/export',
    method: 'get',
    params: query
  })
}
