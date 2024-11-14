import request from '@/utils/request'

// 查询造价评估列表
export function listProductbudget(query) {
  return request({
    url: '/evaluate/productbudget/list',
    method: 'get',
    params: query
  })
}

// 查询造价评估详细
export function getProductbudget(productID) {
  return request({
    url: '/evaluate/productbudget/' + productID,
    method: 'get'
  })
}

// 新增造价评估
export function addProductbudget(data) {
  return request({
    url: '/evaluate/productbudget',
    method: 'post',
    data: data
  })
}

// 修改造价评估
export function updateProductbudget(data) {
  return request({
    url: '/evaluate/productbudget',
    method: 'put',
    data: data
  })
}

// 删除造价评估
export function delProductbudget(productID) {
  return request({
    url: '/evaluate/productbudget/' + productID,
    method: 'delete'
  })
}
