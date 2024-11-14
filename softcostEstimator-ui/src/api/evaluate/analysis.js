import request from '@/utils/request'

// 查询功能点分析列表
export function listAnalysis(query) {
  return request({
    url: '/evaluate/analysis/list',
    method: 'get',
    params: query
  })
}

// 查询功能点分析详细
export function getAnalysis(projectId) {
  return request({
    url: '/evaluate/analysis/' + projectId,
    method: 'get'
  })
}

// 新增功能点分析
export function addAnalysis(data) {
  return request({
    url: '/evaluate/analysis',
    method: 'post',
    data: data
  })
}

// 修改功能点分析
export function updateAnalysis(data) {
  return request({
    url: '/evaluate/analysis',
    method: 'put',
    data: data
  })
}

// 删除功能点分析
export function delAnalysis(projectId) {
  return request({
    url: '/evaluate/analysis/' + projectId,
    method: 'delete'
  })
}
