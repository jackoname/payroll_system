<template>
  <div>
    <el-card>
      <el-row :gutter="10">
        <el-col :span="3"><div class="grid-content bg-purple">
          <div class="block">
            <el-cascader
              placeholder="试试搜索：奖励"
              v-model="searchModel.option"
              :options="listType2"
              :clearable="true"
              filterable>
            </el-cascader>
          </div>
        </div></el-col>
          <el-col :span="2"><div class="grid-content bg-purple">
          <el-select
            filterable
            :clearable="true"
            v-model="searchModel.userId"

            @focus="myfocus"
            placeholder="请选择或搜索职工"
          >
            <el-option
              v-for=" item in userList"
              :key="item.userId"
              :value="item.userId"
              :label="item.name"
            >
            </el-option>
          </el-select>
        </div></el-col>

        <el-col :span="2.5"><div class="grid-content bg-purple">
          <el-date-picker
            v-model="searchModel.endtime"
            type="month"
            format="yyyy 年 MM 月"
            value-format="yyyy-MM-dd"
            placeholder="选择月">
          </el-date-picker>
        </div></el-col>
        <el-col :span="2.5"><div class="grid-content bg-purple">
          <el-button type="primary" Style="margin-left: 6px" @click="getUserwageList" size="minin"  icon="el-icon-search" round></el-button>
          <el-button type="primary" size="minin"   @click="clearUserwageList"icon="el-icon-refresh-left" round></el-button>
        </div></el-col>
        <el-col :span="12 "><div class="grid-content bg-purple" align="right">
          <el-button  @click="openEditUI" type="primary" icon="el-icon-plus" circle></el-button>
        </div></el-col>
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
          label="其它工资/元"
          :sortable=true
          prop="wage">
        </el-table-column>

        <el-table-column
          label="日期 "
          :sortable=true

          prop="endtime">

          <template slot-scope="scope">
            {{scope.row.endtime[0]}} 年 {{scope.row.endtime[1]}} 月 {{scope.row.endtime[2]}}日
          </template>
        </el-table-column>
        <el-table-column
          label="事项"
          prop="wageName">
        </el-table-column>
        <el-table-column
          label="事项类别"
          prop="cname">
          <template slot-scope="scope">
            <el-tag size="small" effect="dark"  type="info">{{scope.row.cname}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="备注"
          prop="des">
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
            <el-button type="primary" @click="editUserwage(scope.row.id)" icon="el-icon-edit" size="mini" circle></el-button>
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
      <el-form :model="userprewageForm" :rules="rules" ref="userprewageFormRef">

        <el-form-item label="请选择月份" :label-width="formLabelWidth">
          <div class="block"   STYLE="width:200px">
            <el-date-picker

              v-model="userprewageForm.endtime"
              type="date"
              :disabled="disabled2"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </div>
        </el-form-item>

        <el-form-item label="请选择职工" :label-width="formLabelWidth">
          <el-select
            :disabled="isuse"
            :clearable=true
            v-model="userprewageForm.userId"

            placeholder="请选择或搜索"
          >

            <el-option
              cleanable
              v-for=" item in userList"
              :key="item.userId"
              :value="item.userId"
              :label="item.name"
              :disabled="item.disabled"
            >
            </el-option>
          </el-select>

        </el-form-item>

        <el-form-item label="请选择事项类别" :label-width="formLabelWidth">
            <el-cascader
              placeholder="试试搜索：奖励"
              v-model="userprewageForm.option"
              :options="listType2"
              clearable
              :disabled="disabled"
              @change="myblur"
              @focus="myblur"
              filterable></el-cascader>

            <el-switch
              v-model="disabled"
              active-color="#13ce66"d
              inactive-color="#ff4949"
              active-text="自定义事项"
              :disabled="disabled2"

              @change="myblur1"
              inactive-text="选择事项">
            </el-switch>

          </el-form-item>

        <el-form-item label="请输入事项" :label-width="formLabelWidth"   >
          <el-input     placeholder="请输入自定义事项" :disabled="disabled1" v-model="userprewageForm.wageName" autocomplete="off"  Style="width: 172px" oninput="if(value<0)value=0"></el-input>

        </el-form-item>
        <el-form-item label="事项工资" :label-width="formLabelWidth"   >
          <el-input v-model="userprewageForm.wage" autocomplete="off" type="number" Style="width: 172px" ></el-input>

        </el-form-item>
        <el-form-item label=" 备注" :label-width="formLabelWidth"  >
          <el-input v-model="userprewageForm.des" autocomplete="off"></el-input>
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
  import userprewageApi from "@/api/userprewage";
  import userwageApi from "@/api/userwage";
  import listTypeApi from "@/api/listType";
  import safeList from "@/api/listType";

  export default {
    name: "user",
    data(){
      return{
        listType1:[],
        listType2:[],
        disabled:false,
        disabled1:true,
        //photo:"https://img1.baidu.com/it/u=4096959686,4145726040&fm=253&fmt=auto&app=138&f=JPEG?sec=1683017670&t=88663d7cd6f8d44dcd99a2d02f38584f",
        roleList: [],
        dscdata:'',
        isload:true,
        isuse:false,
        depList:[],
        table:false,
        postList:[],
        listType:[],
        flag1:true,
        value1:'',
        myfocus:'',
        direction: 'rtl',//rtl / ltr / ttb / btt
        formLabelWidth:"130px",
        dialogFormVisible:false,
        disabled2:false,
        userprewageForm:{
          des:"",
          userId:"",
          wage:"",

        },
        value: [],
        userwageList:[],
        total:0,
        searchModel:{
          pageNo:1,
          pageSize:10,
         option:[],
        },
        userList:[],
        rules:{


        }
      }
    },
    methods:{
 saveUser(){
        console.log(this.userprewageForm)
        this.$refs.userprewageFormRef.validate((valid) => {

          if (valid&&this.flag1==true) {

          userprewageApi.addUserprewage(this.userprewageForm).then(response=>{
              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              this.dialogFormVisible=false;
              this.getUserwageList();
            })

          }
          else if(valid&&this.flag1==false){

            userprewageApi.updateUserprewage(this.userprewageForm).then(response=>{
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
      clearlistType(){
        this.searchModel.listId=null;
        this.getlistType();
      },
       myfocus(){

         console.log(this.disabled1);
        this.disabled1=false;

      },
      getlistType(){
        console.log(this.searchModel);
        listTypeApi.getlistType(this.searchModel).then(response=>{
          this.listType=response.data.rows;
          this.total=response.data.total;
          this.listType1=response.data.types;
          this.listType2=response.data.values;

          console.log(this.listType2)

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
      myblur(){

      // console.log(this.userprewageForm);

        listTypeApi.getlistTypeById(this.userprewageForm.option[1]).then(response=>{
          this.userprewageForm.wage=response.data.money;
        })


      },
      myblur1(){
      this.disabled==true?this.disabled1=false:this.disabled1=true;
      if (this.disabled1==false){
        this.userprewageForm.wage=null;
      }

      },
      clearUserwageList(){
        this.searchModel.userId=null;
        this.searchModel.endtime=null;
        this.searchModel.option=[];
        this.getUserwageList();
      },
      getUserwageList(){
        console.log(this.searchModel)

        userprewageApi.getUserwageList1(this.searchModel).then(response=>{
          this.userwageList=response.data.rows;
// 使用 toLocaleDateString() 方法将日期转换为本地日期格式
          this.userwageList.forEach(item=>{
            item.endtime=new Date(item.endtime).toLocaleDateString().split("/");
          })
          this.total=response.data.total;

        },)
      },
      openEditUI(){
        this.disabled=false;
        this.disabled2=false;
        this.isuse=false;
        this.flag1=true;
        this.title='新增职工绩效工资';
        this.dialogFormVisible=true;
      },
      editUserwage(id){
        userprewageApi.getUserprewageById(id).then(response => {
          this.isuse=true;
          this.flag1=false;
          this.disabled1=true;
          this.disabled2=true;
          this.disabled=true;
          this.userprewageForm = response.data;
          this.userprewageForm.endtime=new Date(this.userprewageForm.endtime);
        });
        this.title='编辑职工其它工资'
        this.flag=false;
        this.dialogFormVisible=true;
      },

      delUserwage(userprewage){
        this.$confirm(`是否要删除${userprewage.name}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          userprewageApi.delUserprewageById(userprewage.id).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getUserwageList();
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      clearForm(){
        this.$refs.userprewageFormRef.clearValidate();
        this.userprewageForm= {
        };
      },



    },


    created() {
      this.getUserList();
      this.getUserwageList();
      this. getlistType();


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
