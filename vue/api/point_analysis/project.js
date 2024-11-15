import request from '@/utils/request'

// 查询Function_point_analysis列表
export function listProject(query) {
  return request({
    url: '/point_analysis/project/list',
    method: 'get',
    params: query
  })
}

// 查询Function_point_analysis详细
export function getProject(projectId) {
  return request({
    url: '/point_analysis/project/' + projectId,
    method: 'get'
  })
}

// 新增Function_point_analysis
export function addProject(data) {
  return request({
    url: '/point_analysis/project',
    method: 'post',
    data: data
  })
}

// 修改Function_point_analysis
export function updateProject(data) {
  return request({
    url: '/point_analysis/project',
    method: 'put',
    data: data
  })
}

// 删除Function_point_analysis
export function delProject(projectId) {
  return request({
    url: '/point_analysis/project/' + projectId,
    method: 'delete'
  })
}
