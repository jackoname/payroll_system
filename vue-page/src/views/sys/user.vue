<template>
  <div>
    <el-card  id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.username" placeholder="用户名" clearable></el-input>
          <el-input v-model="searchModel.phone" placeholder="手机号" clearable></el-input>
          <el-button type="primary" @click="getUserList" round icon="el-icon-search" >查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button  @click="openEditUI" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table
        :data="userList"
        stripe
        style="width: 100%"
        :cell-style="cellStyle"
       >
        <el-table-column
          prop="date"
          label="#"
          width="80">
          <template slot-scope="scope">
          {{(searchModel.pageNo-1)*searchModel.pageSize+scope.$index+1}}
          </template>
        </el-table-column>

        <el-table-column
          prop="userId"
          label="用户ID"
          width="180">
        </el-table-column>

        <el-table-column
          prop="username"
          label="用户名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="电话"
          width="180">
        </el-table-column>

        <el-table-column
          prop="email"
          label="电子邮件">
        </el-table-column>

        <el-table-column
          prop="stateuser"
          label="状态"
          >
        </el-table-column>

        <el-table-column
          label="操作"
          width="180">
          <template slot-scope="scope">
            <el-button type="primary" @click="editUser(scope.row.userId)" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button type="danger"   @click="delUser(scope.row)"   icon="el-icon-delete" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage4"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>

  <!--用户对话框-->
  <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible"  >
    <el-form :model="userForm" :rules="rules" ref="userFormRef">
      <el-form-item label="用户名" :label-width="formLabelWidth"  prop="username">
        <el-input v-model="userForm.username" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="登录密码" :label-width="formLabelWidth"  prop="password"  v-show="flag1">
        <el-input type="password" v-model="userForm.password" autocomplete="off" ></el-input>
      </el-form-item>

      <el-form-item label="联系电话" :label-width="formLabelWidth">
        <el-input v-model="userForm.phone" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="用户状态" :label-width="formLabelWidth">
        <el-switch
          v-model="userForm.isuse"
          :active-value="1"
          :inactive-value="0"
          >
        </el-switch>
      </el-form-item>
      <el-form-item label="用户角色" :label-width="formLabelWidth">
        <el-checkbox-group
          style="width: 85%"
          v-model="userForm.roleIdList"
          :max="1">
          <el-checkbox v-for=" role in roleList" :label="role.id" :key="role.id">{{role.rolename}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="电子邮件" :label-width="formLabelWidth"  prop="email">
        <el-input v-model="userForm.email" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="saveUser">确 定</el-button>
    </div>
  </el-dialog>
  </div>
</template>

<script>
  import userApi from '@/api/userMange'
  import  roleApi from '@/api/roleMange'
    export default {
        name: "user",
      data(){
          var checkEmail = (rule, value, callback) => {
          var reg = /^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,6}$/;
          if (!reg.test(value)) {
            return callback(new Error('邮箱格式错误！'));
          }
                callback();
        };
          return{
            roleList: [],
            flag1:true,
            formLabelWidth:"130px",
            dialogFormVisible:false,
            userForm:{
              roleIdList:[]
            },
            title:"",
            total:0,
            searchModel:{
              pageNo:1,
              pageSize:10,
            },

            userList:[],
            rules:{
              username: [
                { required: true, message: '请输入用户名', trigger: 'blur' },
                { min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
              ],
             password: [
                { required: true, message: '请输入登录初始密码', trigger: 'blur' },
                { min: 6, max: 5000, message: '长度在 6 到 500个字符', trigger: 'blur' }
              ],
              email: [
                { required: true, message: '请输入电子邮箱', trigger: 'blur' },
                { validator: checkEmail, trigger: 'blur' }
              ],
            }
          }
      },
      methods:{

          saveUser(){
            this.$refs.userFormRef.validate((valid) => {

              if (valid&&this.flag1==true) {
                userApi.addUser(this.userForm).then(response=>{
                  //成功
                  console.log(this.userForm)
                  this.$message(
                  {
                    message: response.message,
                     type: 'success',
                  })
                  //
                  this.dialogFormVisible=false;
                  //
                  this.getUserList();
                })

              }
              else if(valid&&this.flag1==false){
                  userApi.updateUser(this.userForm).then(response=>{
                this.$message(
                  {
                    message: response.message,
                    type: 'success',
                  })
                //
                this.dialogFormVisible=false;
                //
                this.getUserList();
              })
              }
              else {
                console.log('error submit!!');
                this.getUserList();
                return false;
              }
            });
          },
        handleSizeChange(pageSize){
          this.searchModel.pageSize=pageSize;
          this.getUserList();
        },
        currentPage4(){},
        handleCurrentChange(pageNo){
          this.searchModel.pageNo=pageNo;
          this.getUserList();
        },

        getUserList(){
          userApi.getUserList(this.searchModel).then(response=>{
            this.userList=response.data.rows;
            this.total=response.data.total;
            this.userList.forEach(item=>{
              if(item.isuse==0){
                item.stateuser="禁用";
              }
              else {
               item.stateuser="正常";
              }
            });

          });

        },
        openEditUI(){
          this.flag1=true,
            this.userForm.password="@123456"
          this.title='新增用户';
          this.dialogFormVisible=true;
        },
        editUser(userId){

          userApi.getUserById(userId).then(response => {
            this.flag1=false;
            this.userForm = response.data;
            console.log(this.userForm.roleIdList)

          });
          this.title='编辑用户'
          this.flag=false;
          this.dialogFormVisible=true;
          },
        delUser(user){
          this.$confirm(`是否要删除${user.username}?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            userApi.delUserById(user.userId).then(response => {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.getUserList();
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        clearForm(){
         this.$refs.userFormRef.clearValidate();
          this.userForm= {
            userId:"",
            username:"",
            password:"",
            isuse:"",
            stateuser:"",
            phone:"" ,
            email:"",
            roleIdList:[],
          };
        },
        cellStyle({ row, column, rowIndex, columnIndex }) {

          if ((columnIndex === 5||columnIndex === 2) &&row.isuse==0 ) {
            return "color:#FF6100 ";
          }
          else if ((columnIndex === 5 ||columnIndex === 2)&&row.isuse==1  )
          return "color:#67C23A";
        },
        getAllRoleList(){
          roleApi.getAllRole().then(response => {
            this.roleList = response.data;
            console.log( this.roleList);
          });
        },
      },


      created() {
          this.getUserList();
          this.getAllRoleList();
      }
    };
</script>

<style >
  #search .el-input{
    width: 200px;
    margin-right: 10px;
  }
  .el-card{
    margin: 10px;
  }
  .el-dialog .el-input{
    width: 85%;
  }
</style>
