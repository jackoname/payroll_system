import request from '@/utils/request'


export default {
  getPostList(searchModel){
    return request({
      url:'post/list',
      method:'get',
      params:{
        pageNo:searchModel.pageNo,
        pageSize:searchModel.pageSize,
        post_name:searchModel.post_name,
        id:searchModel.id,
      }
    });
  },

  addPost(post){
    return request({
      url:'post',
      method:'post',
      data: post,
    });
  },

  updatePost(post){
    return request({
      url:'post',
      method:'put',
      data: post,
    });
  },
  getPostById(postId){
    return request({
      url:`/post/${postId}`,
      method:'get',
    });
  },

  delPostById(postId){
    return request({
      url:`/post/${postId}`,
      method:'delete',
    });
  },
  getAllPost(){
    return request({
      url: '/post/all',
      method: 'get'
    });
  },

  getPostListByDepId(depIdList){
    return request({
      url: '/post/alllbyDid',
      method: 'get',
      params: {
        depIdList: depIdList,
      }
    });
  },
}
