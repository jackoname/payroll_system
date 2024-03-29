import request from '@/utils/request'

export default {
  getUserList(searchModel){
    return request({
      url:'user/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        username:searchModel.username,
        phone:searchModel.phone,
        roleid:searchModel.roleid
      }
    });
  },

 addUser(user){
    return request({
      url:'user',
      method:'post',
      data: user,
    });
  },

  updateUser(user){
    return request({
      url:'user',
      method:'put',
      data: user,
    });
  },
  getUserById(userId){
    return request({
      url:`/user/${userId}`,
      method:'get',
    });
  },
  delUserById(userId){
    return request({
      url:`/user/${userId}`,
      method:'delete',
    });
  },
  // 查询所有角色列表


}
