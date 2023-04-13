import request from '@/utils/request'

export default {
  getUserwageList(searchModel){
    return request({
      url:'wageList/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        userId:searchModel.userId,
        endtime:searchModel.endtime
      }
    });
  },
  getUserwageList1(searchModel){
    return request({
      url:'wageList/list1',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        userId:searchModel.userId,
        endtime:searchModel.endtime,
        option:searchModel.option[1]


      }
    });
  },
  addUserprewage(wageList){
    return request({
      url:'wageList',
      method:'post',
      data:  wageList,
    });
  },

  updateUserprewage(wageList){
    return request({
      url:'wageList',
      method:'put',
      data: wageList,
    });
  },
  getUserprewageById(id){
    return request({
      url:`/wageList/${id}`,
      method:'get',
    });
  },
  delUserprewageById(id){
    return request({
      url:`/wageList/${id}`,
      method:'delete',
    });
  },
  // 查询所有角色列表


}
