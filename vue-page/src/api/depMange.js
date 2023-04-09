import request from '@/utils/request'
export default {
  getDepList(searchModel){
    return request({
      url:'department/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        depart:searchModel.depart,
      }
    });
  },
  getAllDep(){
    return request({
      url: '/department/all',
      method: 'get',

    });
  },


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

  addDep(dep){
    return request({
      url:'department',
      method:'post',
      data: dep,
    });
  },

  updateDep(dep){
    return request({
      url:'department',
      method:'put',
      data:dep,
    });
  },
  getDepById(depId){
    return request({
      url:`/department/${depId}`,
      method:'get',
    });
  },
  delDepById(depId){
    return request({
      url:`/department/${depId}`,
      method:'delete',
    });
  },

}
