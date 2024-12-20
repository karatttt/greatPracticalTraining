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
export function load(query) {
  return request({
    url: '/evaluate/analysis/load',
    method: 'get',
    params: query
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

export function searchAnalysis(query) {
  console.log('请求发送:', query);  // 在这里检查是否发出了请求
  return request({
    url: '/evaluate/analysis/search',
    method: 'get',
    params: query
  });
}
// 修改AI功能点分析
export function updateAnalysisAI(data) {
  return request({
    url: '/evaluate/analysis/api/edit',
    method: 'post',
    data: data
  })
}
//前端显示AI
export function viewAnalysisAI(params) {
  return request({
    url: '/evaluate/analysis/api/new',
    method: 'get',
    params: params
  })
}
