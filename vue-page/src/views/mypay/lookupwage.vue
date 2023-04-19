<template>
  <div>


    <el-card>
      <el-row :gutter="20">
        <el-col :span="3"><div class="grid-content bg-purple">
          <el-select
            filterable
            :clearable="true"
            v-model="searchModel.userId"
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
        </div></el-col>
        <el-col :span="4.5"><div class="grid-content bg-purple">
          <el-date-picker
            v-model="searchModel.endtime"
            type="month"
            format="yyyy 年 MM 月"
            value-format="yyyy-MM-dd"
            placeholder="选择月">
          </el-date-picker>
        </div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-button type="primary" Style="margin-left: 6px" @click="getUserwageList" size="minin"  icon="el-icon-search" round></el-button>
          <el-button type="primary" size="minin"   @click="clearUserwageList"icon="el-icon-refresh-left" round></el-button>
        </div></el-col>
        <el-col :span="10"><div class="grid-content bg-purple" align="right">
          <el-button  @click="openEditUI" type="primary" icon="el-icon-plus" circle></el-button>
        </div></el-col>
      </el-row>
    </el-card>
    <el-card>
      <el-table
        :data="staffList"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}"
        style="width: 100%"

        border="ture"
        stripe="true"
        size="mini"
        fit
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
          fixed
          prop="name">
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
          label="基本工资"
          :sortable=true
          prop="basicWage">
        </el-table-column>
        <el-table-column
          label="绩效工资"
          :sortable=true
          prop="performance">
        </el-table-column>
        <el-table-column
          label="奖励"
          :sortable=true
          prop="bonus">
        </el-table-column>
        <el-table-column
          label="扣除"
          :sortable=true
          prop="fine">
        </el-table-column>
        <el-table-column
          label="补贴"
          :sortable=true
          prop="wage">
        </el-table-column>

        <el-table-column
          label="应发工资"
          :sortable=true
          prop="wage">
        </el-table-column>
        <el-table-column
          label="个税"
          :sortable=true
          prop="perInTax">
        </el-table-column>
        <el-table-column
          label="基本养老保险"
          :sortable=true
          prop="endowmentIn">
        </el-table-column>
        <el-table-column
          label="基本医疗保险"
          :sortable=true
          prop="endowmentIn">
        </el-table-column>
        <el-table-column
          label="失业保险"
          :sortable=true
          prop="endowmentIn">
        </el-table-column>
        <el-table-column
          label="工伤保险"
          :sortable=true
          prop="employmentInjuryIn">
        </el-table-column>
        <el-table-column
          label="生育保险"
          :sortable=true
          prop="maternityIn">
        </el-table-column>
        <el-table-column
          label="住房公积金"
          :sortable=true
          prop="housingAccFund">
        </el-table-column>
        <el-table-column
          label="社保"
          :sortable=true
          prop="allInsure">
        </el-table-column>
        <el-table-column
          label="应扣总额"
          :sortable=true
          prop="wage">
        </el-table-column>
        <el-table-column
          label="实发工资"
          :sortable=true
          prop="comprehensiveSalary">
        </el-table-column>

        <el-table-column
          label="月份 "
          :sortable=true
          fixed
          prop="endtime">
          <template slot-scope="scope">
            <el-tag effect="dark" size="small" :type="scope.row.stateuser=='禁用'?'danger':'success'">{{formatDate(scope.row.endtime)}}</el-tag>
          </template>
          formatDate(value)
        </el-table-column>



        <el-table-column
          label="状态 "
          :sortable=true

          prop="state">
          <template slot-scope="scope">
            <el-tag effect="dark" size="small" :type="scope.row.stateuser=='禁用'?'danger':'success'">{{scope.row.statename}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          label="审核"
          width="160"
          fit="true"
        >
          <template slot-scope="scope">
            <el-button type="success"  @click="editUserwage(scope.row)"  plain size="mini">通过</el-button>
            <el-button type="danger"   plain   size="mini"  @click="delOne(scope.row)">驳回</el-button>
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
              @blur="myblur"
              v-model="userprewageForm.endtime"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择月">
            </el-date-picker>
          </div>
        </el-form-item>

        <el-form-item label="请选择职工" :label-width="formLabelWidth">
          <el-select
            :disabled="isuse"
            :clearable=true
            v-model="userprewageForm.userId"
            @blur="myblur"
            @focus="myblur"
            placeholder="请选择或搜索"
          >

            <el-option
              v-for=" item in userList"
              :key="item.userId"
              :value="item.userId"
              :label="item.name"
              :disabled="item.disabled"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="绩效工资" :label-width="formLabelWidth"   >
          <el-input v-model="userprewageForm.wage" autocomplete="off" type="number" Style="width: 172px" oninput="if(value<0)value=0"></el-input>
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
import starrwageApi from "@/api/starrwage";
  export default {
    name: "user",
    data(){
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
        value1:'',
        loading:false,
        direction: 'rtl',//rtl / ltr / ttb / btt
        formLabelWidth:"130px",
        dialogFormVisible:false,
        isclose:false,
        userprewageForm:{
          des:"",
          userId:"",
          wage:"",
          tag:1,

        },
        staffList:[],

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
      getStaffList(){
        starrwageApi.getstaffwageList(this.searchModel).then(response=>{
          this.staffList=response.data.rows;
          this.total=response.data.total;
        })
      },

      saveUser(){
        this.$refs.userprewageFormRef.validate((valid) => {

          if (valid&&this.flag1==true) {

            this.userprewageForm.tag=1;
            userprewageApi.addUserprewage(this.userprewageForm).then(response=>{
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
      clearUserwageList(){
        this.searchModel.userId=null;
        this.getUserwageList();
      },
      getUserwageList(){
        this.staffList1=this.staffList1;
      },
      getUserwageList(){

        userprewageApi.getUserwageList(this.searchModel).then(response=>{
          this.userwageList=response.data.rows;
          this.userwageList.forEach(item=>{
            item.endtime=new Date(item.endtime).toLocaleDateString().split("/");
          })
          this.total=response.data.total;

          console.log(this.userwageList)
        },)
      },
      openEditUI(){
        this.isuse=false;
        this.flag1=true;
        this.title='新增职工绩效工资';
        this.dialogFormVisible=true;
      },
      editUserwage(staff){
        this.$confirm(`确认审核通过${staff.name}的工资?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          starrwageApi.editUserwage(staff.wageId).then(response => {
            this.$message({
              type: 'success',
              message: '确认成功!'
            });
            this.getStaffList();
          })
        })

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
      delOne(staff){
        this.$confirm(`确认驳回${staff.name}的工资?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          starrwageApi.delOne(staff.wageId).then(response => {
            this.$message({
              type: 'success',
              message: '确认成功!'
            });
            this.getStaffList();
          })
        })
  },
      saveOne(staff){
        this.$confirm(`确认${staff.name}的工资无误?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          starrwageApi.saveOnestaff(staff).then(response => {
            this.$message({
              type: 'success',
              message: '确认成功!'

            });
            staff.isclose=true;
            this.getUserwageList();
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      formatDate(value) { // 时间戳转换日期格式方法
        if (value == null) {
          return ''
        } else {
          const date = new Date(value)
          const y = date.getFullYear()// 年
          let MM = date.getMonth() + 1 // 月
          MM = MM < 10 ? ('0' + MM) : MM
          let d = date.getDate() // 日
          d = d < 10 ? ('0' + d) : d
          return y + '-' + MM + '-' + d
        }
      }
    },
    created() {
      this.getUserList();
      this.getUserwageList();
      this.getStaffList();
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
