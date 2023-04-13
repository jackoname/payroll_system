<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="3"><div class="grid-content bg-purple">

          <el-select
            filterable
            :clearable="true"
            v-model="searchModel.listId"
            @blur="myblur"
            @focus="myfocus"
            placeholder="请选择或搜索"
          >
            <el-option
              v-for=" item in listType1"
              :key="item.listId"
              :value="item.listId"
              :label="item.name"
            >
            </el-option>
          </el-select>
        </div></el-col>

        <el-col :span="6"><div class="grid-content bg-purple">
          <el-button type="primary" Style="margin-left: 6px" @click="getlistType" size="minin"  icon="el-icon-search" round></el-button>
          <el-button type="primary" size="minin"   @click="clearlistType"icon="el-icon-refresh-left" round></el-button>
        </div></el-col>
        <el-col :span="15"><div class="grid-content bg-purple" align="right">
          <el-button  @click="openEditUI" type="primary" icon="el-icon-folder-add" circle >添加事项</el-button>
          <el-button  @click="table=true" type="primary" icon="el-icon-edit-outline" circle >类别管理</el-button>

        </div></el-col>
      </el-row>
    </el-card>
    <el-card>
      <el-table
        :data="listType"
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
          label="事项"
          prop="name">
        </el-table-column>

        <el-table-column
          label="描述"
          prop="dsc">
        </el-table-column>
        <el-table-column
          label="奖/罚金额（元）"
          prop="money">
        </el-table-column>
        <el-table-column
          label="类别"
          prop="cname">

          <template slot-scope="scope">
            <el-tag size="small" effect="dark"  type="info">{{scope.row.cname}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          width="120"
          fit="true"
        >
          <template slot-scope="scope">
            <el-button type="primary" @click="editUserwage(scope.row.listId)" icon="el-icon-edit" size="mini" circle></el-button>
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

        <el-form-item label="请选择类别" :label-width="formLabelWidth">
          <el-select
            :disabled="isuse"
            :clearable=true
            v-model="userprewageForm.tag"
            @blur="myblur"
            @focus="myfocus"
            placeholder="请选择或搜索"
          >
            <el-option
              v-for=" item in listType1"
              :key="item.listId"
              :value="item.listId"
              :label="item.name"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="事项名称" :label-width="formLabelWidth"  >
          <el-input v-model="userprewageForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="奖/罚金额（元）" :label-width="formLabelWidth"   >
          <el-input v-model="userprewageForm.money" autocomplete="off"  Style="width: 172px" type="number"></el-input>
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



    <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible1"  >
      <el-form :model="listTypeForm" :rules="rules" ref="listTypeFormRef">


        <el-form-item label="类别名称" :label-width="formLabelWidth"   >
          <el-input v-model="listTypeForm.name" autocomplete="off"  Style="width: 172px" oninput="if(value<0)value=0"></el-input>
        </el-form-item>


        <el-form-item label="类别属性" :label-width="formLabelWidth"   >
          <el-radio-group v-model="listTypeForm.flag" size="mini" >
            <el-radio label="999" border>正面</el-radio>
            <el-radio label="-999" border>负面</el-radio>
          </el-radio-group>

        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="addcla">确 定</el-button>
      </div>
    </el-dialog>


    <el-drawer
      title="事项类别管理"
      :visible.sync="table"
      direction="rtl"
      size="50%">
      <el-card>

        <el-table

          :data="listType1"
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
            label="事项类别"
            prop="name">
          </el-table-column>




          <el-table-column
            label="操作"
            width="120"
            fit="true"
          >
            <template slot-scope="scope">

              <el-button type="danger"   @click="delUserwage(scope.row)"   icon="el-icon-delete" size="mini" circle></el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-card >
          <div align="center">
            <el-button  @click="openEditUI1" type="primary" icon="el-icon-plus" >添加类别</el-button>
          </div>
        </el-card>
      </el-card>
    </el-drawer>
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
        direction: 'rtl',//rtl / ltr / ttb / btt
        formLabelWidth:"130px",
        dialogFormVisible:false,
        dialogFormVisible1:false,
        userprewageForm:{
          des:"",
          userId:"",
          wage:"",
        },
        listTypeForm:{

        },

        listType:[],
        listType1:[],
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
      addcla(){
        this.$refs.listTypeFormRef.validate((valid)=>{
          listTypeApi.addlistType(this.listTypeForm).then(response=>{
            //成功
            console.log(this.listTypeForm);
            this.$message(
              {
                message: response.message,
                type: 'success',
              })
            this.dialogFormVisible1=false;
            this.getlistType();
          })
        });
      },
      saveUser(){
        console.log(this.userprewageForm)
        this.$refs.userprewageFormRef.validate((valid) => {

          if (valid&&this.flag1==true) {
            listTypeApi.addlistType(this.userprewageForm).then(response=>{
              //成功
              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              this.getlistType();
            })

          }
          else if(valid&&this.flag1==false){

            listTypeApi.updatelistType(this.userprewageForm).then(response=>{
              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              //
              this.getlistType();
            })
          }
          else {
            console.log('error submit!!');
            this.getlistType();
            return false;
          }
        });
        this.getlistType();
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
      clearlistType(){
        this.searchModel.listId=null;
        this.getlistType();
      },
      getlistType(){
        listTypeApi.getlistType(this.searchModel).then(response=>{
          this.listType=response.data.rows;
          this.total=response.data.total;
          this.listType1=response.data.types;
          console.log(this.listType)
        },)
      },
      openEditUI(){
        this.isuse=false;
        this.flag1=true;
        this.title='新增事项';
        this.dialogFormVisible=true;
      },
      openEditUI1(){
        this.isuse=false;
        this.flag1=true;
        this.title='新增事项类别';
        this.dialogFormVisible1=true;
      },
      editUserwage(ListId){
        listTypeApi.getlistTypeById(ListId).then(response => {
          this.isuse=true;
          this.flag1=false;
          this.userprewageForm = response.data;
        });
        this.title='编辑事项'
        this.flag=false;
        this.dialogFormVisible=true;
      },

      delUserwage(safeList){
        this.$confirm(`是否要删除${safeList.name}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          listTypeApi.dellistTyoeById(safeList.listId).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
      this.getlistType();
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
      this.getlistType();
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
