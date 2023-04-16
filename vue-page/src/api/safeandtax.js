import request from '@/utils/request'

export default {

  getsafeLis(searchModel){
    return request({
      url:'taxandsafe/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        userId:searchModel.userId
      }
    });
  },
  getsafeLis1(searchModel){
    return request({
      url:'taxandsafe/list1',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        userId:searchModel.userId
      }
    });
  },
  addsafeList(safeList){
    return request({
      url:'taxandsafe',
      method:'post',
      data: safeList,
    });
  },
  addsafeList1(safeList){
    return request({
      url:'taxandsafe/add',
      method:'post',
      data: safeList,
    });
  },
  updatesafeList(safeList){
    return request({
      url:'taxandsafe',
      method:'put',
      data: safeList,
    });
  },
  gettaxlisteById(id){
    return request({
      url:`/taxandsafe/${id}`,
      method:'get',
    });
  },
  deltaxById(id){
    return request({
      url:`/taxandsafe/${id}`,
      method:'delete',
    });
  },
  // 查询所有角色列表


}
