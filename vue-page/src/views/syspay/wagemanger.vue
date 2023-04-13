<template>
  <div>
    <el-card  id="search">
      <el-row>
        <el-col :span="20">
          <el-select
            filterable
            :clearable="true"
            v-model="searchModel.userId"
            @blur="myblur"
            @focus="myfocus"
            placeholder="请选择或搜索"
          >
            <el-option
              v-for=" item in userList"
              :key="item.userId"
              :value="item.userId"
              :label="item.name"
            >
            </el-option>
          </el-select>
          <el-button type="primary" @click="getUserwageList" round icon="el-icon-search" >查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button  @click="openEditUI" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table
        :data="userwageList"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}"
        style="width: 100%"

        border="ture"
        fit="ture"
        stripe="true"
        size="mini"
        show-header="true"
        highlight-current-row="ture"
      >
        <el-table-column
          prop="date"
          label="#">
          <template slot-scope="scope">
            {{(searchModel.pageNo-1)*searchModel.pageSize+scope.$index+1}}
          </template>
        </el-table-column>

        <el-table-column
          label="姓名"
          prop="name">
        </el-table-column>

        <el-table-column
          label="基本工资/元"
          :sortable=true
          prop="basewage">
        </el-table-column>
        <el-table-column
          label="备注"
          prop="dsc">
        </el-table-column>

        <el-table-column
          prop="username"
          label="用户名">
          <template slot-scope="scope">
            <el-tag size="small" effect="dark" :type="scope.row.stateuser=='禁用'?'danger':'success'">{{scope.row.username}}</el-tag>
          </template>
        </el-table-column>


        <el-table-column
          prop="depart"
          label="部门">
          <template slot-scope="scope">
            <el-tag size="small" effect="dark" :type="scope.row.stateuser=='禁用'?'danger':'success'" >{{scope.row.depart}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          prop="post_name"
          label="职位"
          width="155">
          <template slot-scope="scope">
            <el-tag effect="dark" size="small" :type="scope.row.stateuser=='禁用'?'danger':'success'">{{scope.row.post_name}}</el-tag>
          </template>
        </el-table-column>




        <el-table-column
          label="操作"
          width="120"
          fit="true"
        >
          <template slot-scope="scope">
            <el-button type="primary" @click="editUserwage(scope.row.userid)" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button type="danger"   @click="delUserwage(scope.row)"   icon="el-icon-delete" size="mini" circle></el-button>
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
      <el-form :model="userwageForm" :rules="rules" ref="userwageFormRef">
        <el-form-item>

          <el-form-item label="请选择职工" :label-width="formLabelWidth">
            <el-select
              :disabled="isuse"
              :clearable=true
              v-model="userwageForm.userid"
              @blur="myblur"
              @focus="myfocus"
              placeholder="请选择或搜索"
            >
              <el-option
                v-for=" item in userList"
                :key="item.userId"
                :value="item.userId"
                :label="item.name"
              >
              </el-option>
            </el-select>
        </el-form-item>
        </el-form-item>
        <el-form-item label="基础工资" :label-width="formLabelWidth"   >
        <el-input v-model="userwageForm.basewage" autocomplete="off" type="number" Style="width: 172px" oninput="if(value<0)value=0"></el-input>

        </el-form-item>




        <el-form-item label=" 备注" :label-width="formLabelWidth"  >
          <el-input v-model="userwageForm.dsc" autocomplete="off"></el-input>
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
  import  depApi from '@/api/depMange'
  import  postApi from '@/api/postMange'
  import userwageApi from "@/api/userwage";
  import listTypeApi from "@/api/listType";

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
      var checkphone = (rule, value, callback) => {
        var reg = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
        if (!reg.test(value)) {
          return callback(new Error('手机号格式错误！'));
        }
        callback();
      };

      var checkidcard= (rule, value, callback) => {
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (!reg.test(value)) {
          return callback(new Error('身份证格式错误！'));
        }
        callback();
      };

      var checkbankno= (rule, value, callback) => {
        var reg = /^[1-9]\d{9,29}$/;
        if (!reg.test(value)) {
          return callback(new Error('银行卡格式错误！'));
        }
        callback();
      };

      return{
        //photo:"https://img1.baidu.com/it/u=4096959686,4145726040&fm=253&fmt=auto&app=138&f=JPEG?sec=1683017670&t=88663d7cd6f8d44dcd99a2d02f38584f",
        roleList: [],
        dscdata:'',
        isload:true,
        isuse:false,
        depList:[],
        table:false,
        postList:[],
        flag1:true,
        listType:[],
        listType1:[],
        direction: 'rtl',//rtl / ltr / ttb / btt
        formLabelWidth:"130px",
        dialogFormVisible:false,
        userwageForm:{
          dsc:"",
          userid:"",
          basewage:"",
        },
        userwageList:[],
        total:0,
        searchModel:{
          pageNo:1,
          pageSize:10,
        },
        userList:[],
        rules:{

        }
      }
    },
    methods:{

      saveUser(){
        console.log(this.userwageForm)
        this.$refs.userwageFormRef.validate((valid) => {

          if (valid&&this.flag1==true) {
            userwageApi.addUserwage(this.userwageForm).then(response=>{
              //成功

              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              this.getUserwageList();
            })

          }
          else if(valid&&this.flag1==false){

            userwageApi.updateUserwage(this.userwageForm).then(response=>{
              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              //
              this.getUserwageList();
            })
          }
          else {
            console.log('error submit!!');
            this.getUserwageList();
            return false;
          }
        });
        this.getUserwageList();
      },
      getlistType(){
        listTypeApi.getlistType(this.searchModel).then(response=>{
          this.listType=response.data.rows;
          this.total=response.data.total;
          this.listType1=response.data.types;
          console.log(this.listType)
        },)
      },
      getDesdata(value){
        this.table=true;
        this.dscdata=value;
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

      getUserwageList(){
      userwageApi.getUserwageList(this.searchModel).then(response=>{

          this.userwageList=response.data.rows;

          this.total=response.data.total;

          console.log(this.userwageList)
      },)
      },
      openEditUI(){
        this.isuse=false;
        this.flag1=true;
        this.title='新增职工工资';
        this.dialogFormVisible=true;
      },
      editUserwage(userId){
        userwageApi.getUserwageById(userId).then(response => {
          this.isuse=true;
          this.flag1=false;
          this.userwageForm = response.data;
        });
        this.title='编辑职工工资'
        this.flag=false;
        this.dialogFormVisible=true;
      },

      delUserwage(user){
        this.$confirm(`是否要删除${user.name}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          userwageApi.delUserwageById(user.userid).then(response => {
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
        this.$refs.userwageFormRef.clearValidate();
        this.userwageForm= {
          userId:"",
          username:"",
          password:"",
          isuse:"",
          stateuser:"",
          phone:"" ,
          email:"",
          name:"",
          age:"",
          depIdList:[],
          postIdList:[],
          roleIdList:[],
          birthday:"",
          remarks:"",
          bankno:"",
          idcardno:"",
          address:"",
          sex:"",
          depName:"",
          postName:""
        };
      },



    },


    created() {
      this.getUserList();
      this.getUserwageList();
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
  .el-descriptions__body .el-descriptions__table .el-descriptions-item__cell {
    text-align: center;

  }
  .el-descriptions__body {
    text-align: center;
  }
</style>
