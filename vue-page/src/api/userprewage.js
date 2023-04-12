import request from '@/utils/request'

export default {
  getUserwageList(searchModel){
    return request({
      url:'wageList/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        userId:searchModel.userId
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

  updateUserwage(userwage){
    return request({
      url:'userBasewage',
      method:'put',
      data: userwage,
    });
  },
  getUserwageById(userId){
    return request({
      url:`/userBasewage/${userId}`,
      method:'get',
    });
  },
  delUserwageById(userid){
    return request({
      url:`/userBasewage/${userid}`,
      method:'delete',
    });
  },
  // 查询所有角色列表


}
