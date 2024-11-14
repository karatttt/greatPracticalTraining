import request from '@/utils/request'

// 查询综合评估列表
export function listProductbudget(query) {
  return request({
    url: '/evaluate/productbudget/list',
    method: 'get',
    params: query
  })
}

// 查询综合评估详细
export function getProductbudget(productID) {
  return request({
    url: '/evaluate/productbudget/' + productID,
    method: 'get'
  })
}

// 新增综合评估
export function addProductbudget(data) {
  return request({
    url: '/evaluate/productbudget',
    method: 'post',
    data: data
  })
}

// 修改综合评估
export function updateProductbudget(data) {
  return request({
    url: '/evaluate/productbudget',
    method: 'put',
    data: data
  })
}

// 删除综合评估
export function delProductbudget(productID) {
  return request({
    url: '/evaluate/productbudget/' + productID,
    method: 'delete'
  })
}
