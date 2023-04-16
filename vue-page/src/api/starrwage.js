import request from '@/utils/request'

export default {
  getUserwageList(searchModel){
    return request({
      url:'userBasewage/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        userId:searchModel.userId
      }
    });
  },
  getculWage(searchModel){
    return request({
      url:'staffWage/greanwage',
      method:'post',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        gentime :searchModel.endtime
      }
    });
  },
  addUserwage(userBasewage){
    return request({
      url:'userBasewage',
      method:'post',
      data: userBasewage,
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
