<template>
  <div>
    <el-card  id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.username" placeholder="用户名" clearable></el-input>
          <el-input v-model="searchModel.phone" placeholder="手机号" clearable></el-input>
          <el-button type="primary" round icon="el-icon-search">查询</el-button>
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
        style="width: 100%">
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
          prop="userid"
          label="操作"
          width="180">
          <template slot-scope="scope">
            <el-button type="primary" @click="editUser(scope.row.userId)" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button type="danger" @click="delUser(scope.row.userId)" icon="el-icon-delete" size="mini" circle></el-button>
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
  <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
    <el-form :model="userForm" :rules="rules" ref="userFormRef">
      <el-form-item label="用户名" :label-width="formLabelWidth"  prop="username">
        <el-input v-model="userForm.username" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="登录密码" :label-width="formLabelWidth"  prop="password">
        <el-input type="password" v-model="userForm.password" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="联系电话" :label-width="formLabelWidth">
        <el-input v-model="userForm.userId" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="用户状态" :label-width="formLabelWidth">
        <el-switch
          v-model="userForm.status"
          :active-value="1"
          :inactive-value="0"
          >
        </el-switch>
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
            formLabelWidth:"130px",
            dialogFormVisible:false,
            userForm:{},
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
                { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
              ],
              email: [
                { required: true, message: '请输入电子邮箱', trigger: 'blur' },
                { validator: checkEmail, trigger: 'blur' }
              ],
            }
          }
      },
      methods:{
        delUser(){his.$refs.userFormRef.validate((valid) => {
          if (valid) {
            userApi.addUser(this.userForm).then(response=>{
              //成功
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

          } else {
            console.log('error submit!!');
            return false;
          }
        });},
          saveUser(){
            this.$refs.userFormRef.validate((valid) => {
              if (valid) {
                userApi.addUser(this.userForm).then(response=>{
                  //成功
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

              } else {
                console.log('error submit!!');
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
          });
        },
        openEditUI(){

          this.title='新增用户';
          this.dialogFormVisible=true;
        },
        editUser(userId){
          userApi.getUserById(userId).then(response => {
            this.userForm = response.data;
            console.log(response.data)
          });
          this.title='编辑用户';
          this.dialogFormVisible=true;

          },
        delUser(){

        },
        clearForm(){
         this.$refs.userFormRef.clearValidate();
          this.userForm="";
        },
      },
      created() {
          this.getUserList();
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
