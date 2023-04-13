import request from '@/utils/request'

export default {
 getlistType(searchModel){
    return request({
      url:'listType/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        listId:searchModel.listId
      }
    });
  },

  addlistType(listType){
    return request({
      url:'listType',
      method:'post',
      data: listType,
    });
  },

  updatelistType(listType){
    return request({
      url:'listType',
      method:'put',
      data: listType,
    });
  },

  getlistTypeById(listId){
    return request({
      url:`/listType/${listId}`,
      method:'get',
    });
  },
  dellistTyoeById(listId){
    return request({
      url:`/listType/${listId}`,
      method:'delete',
    });
  },
  // 查询所有角色列表


}
