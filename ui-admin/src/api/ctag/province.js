import request from '@/utils/request'

// 查询省别列表
export function pageProvince(query) {
  return request({
    url: '/ctag/province/pageProvince',
    method: 'get',
    params: query
  })
}

export function listProvince(query) {
  return request({
    url: '/ctag/province/listProvince',
    method: 'get',
    params: query
  })
}

// 查询省别详细
export function getProvince(id) {
  return request({
    url: '/ctag/province/' + id,
    method: 'get'
  })
}

// 新增省别
export function addProvince(data) {
  return request({
    url: '/ctag/province',
    method: 'post',
    data: data
  })
}

// 修改省别
export function updateProvince(data) {
  return request({
    url: '/ctag/province',
    method: 'put',
    data: data
  })
}

// 删除省别
export function delProvince(id) {
  return request({
    url: '/ctag/province/' + id,
    method: 'delete'
  })
}

// 导出省别
export function exportProvince(query) {
  return request({
    url: '/ctag/province/export',
    method: 'get',
    params: query
  })
}
