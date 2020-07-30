import request from '@/utils/request'

// 查询工程与OSS文件匹配列表
export function listOssproject(query) {
  return request({
    url: '/ctag/ossproject/list',
    method: 'get',
    params: query
  })
}

// 查询工程与OSS文件匹配详细
export function getOssproject(id) {
  return request({
    url: '/ctag/ossproject/' + id,
    method: 'get'
  })
}

// 新增工程与OSS文件匹配
export function addOssproject(data) {
  return request({
    url: '/ctag/ossproject',
    method: 'post',
    data: data
  })
}

// 修改工程与OSS文件匹配
export function updateOssproject(data) {
  return request({
    url: '/ctag/ossproject',
    method: 'put',
    data: data
  })
}

// 删除工程与OSS文件匹配
export function delOssproject(id) {
  return request({
    url: '/ctag/ossproject/' + id,
    method: 'delete'
  })
}

// 导出工程与OSS文件匹配
export function exportOssproject(query) {
  return request({
    url: '/ctag/ossproject/export',
    method: 'get',
    params: query
  })
}
