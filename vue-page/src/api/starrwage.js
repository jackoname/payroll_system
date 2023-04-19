import request from '@/utils/request'

export default {
  getstaffwageList(searchModel) {
    return request({
      url: 'staffWage/list',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        wageId: searchModel.wageId
      }
    })
  },
  getstaffwageList1(searchModel) {
    return request({
      url: 'staffWage/list1',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        wageId: searchModel.wageId
      }
    })
  },
  saveOnestaff(staff) {
    return request({
      url: 'staffWage/saveOne',
      method: 'post',
      data: staff
    })
  },
  editUserwage(wageId) {
    return request({
      url: `/staffWage/${wageId}`,
      method: 'put'
    })
  },
  getculWage(searchModel) {
    return request({
      url: 'staffWage/greanwage',
      method: 'post',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        gentime: searchModel.endtime
      }
    })
  },
  addUserwage(userBasewage) {
    return request({
      url: 'userBasewage',
      method: 'post',
      data: userBasewage
    })
  },

  updateUserwage(userwage) {
    return request({
      url: 'userBasewage',
      method: 'put',
      data: userwage
    })
  },
  getUserwageById(userId) {
    return request({
      url: `/userBasewage/${userId}`,
      method: 'get'
    })
  },
  delOne(wageId) {
    return request({
      url: `/staffWage/${wageId}`,
      method: 'delete'
    })
  }
  // 查询所有角色列表

}
