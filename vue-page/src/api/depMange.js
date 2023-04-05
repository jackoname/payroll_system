import request from '@/utils/request'


export default {
  getRoleList(searchModel){
    return request({
      url:'role/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
       rolename:searchModel.rolename,
      }
    });
  },

  addRole(role){
    return request({
      url:'role',
      method:'post',
      data: role,
    });
  },

  updateRole(role){
    return request({
      url:'role',
      method:'put',
      data: role,
    });
  },
  getRoleById(roleId){
    return request({
      url:`/role/${roleId}`,
      method:'get',
    });
  },
  delRoleById(roleId){
    return request({
      url:`/role/${roleId}`,
      method:'delete',
    });
  },
  getAllDep(){
    return request({
      url: '/department/all',
      method: 'get'
    });
  },
}
