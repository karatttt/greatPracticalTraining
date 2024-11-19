import request from '@/utils/request'
export function pollReport() {
  return request({
    url: '/evaluate/productbudget/pollReport',
    method: 'get'
  })
}
export function createReport(id) {
  return request({
    url: '/evaluate/productbudget/generate/report',
    method: 'get',
    params: { id }
  })
}
export function longPolling() {
  return request({
    url: '/chat', // 请求的接口路径（基于你的后端）
    method: 'get'
  });
}
export function send(data) {
  return request({
    url: '/sendMsg', // 请求的接口路径（基于你的后端）
    method: 'post',
    data : data
  });
}
export function longPolling2() {
  return request({
    url: '/chat2', // 请求的接口路径（基于你的后端）
    method: 'get'
  });
}
export function send2(data) {
  return request({
    url: '/sendMsg2', // 请求的接口路径（基于你的后端）
    method: 'post',
    data : data
  });
}
// 查询项目信息列表
export function listProject(query) {
  return request({
    url: '/evaluate/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目信息详细
export function getProject(projectID) {
  return request({
    url: '/evaluate/project/' + projectID,
    method: 'get'
  })
}

// 新增项目信息
export function addProject(data) {
  return request({
    url: '/evaluate/project',
    method: 'post',
    data: data
  })
}

// 修改项目信息
export function updateProject(data) {
  return request({
    url: '/evaluate/project',
    method: 'put',
    data: data
  })
}

// 删除项目信息
export function delProject(projectID) {
  return request({
    url: '/evaluate/project/' + projectID,
    method: 'delete'
  })
}
