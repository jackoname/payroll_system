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
          <el-button  @click="openEditUI" type="primary" icon="el-icon-plus" circle ></el-button>

        </div></el-col>
      </el-row>
    </el-card>
    <el-card>
      <el-table
        :data="safeList"
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
          label="个人税率"
          prop="taxMe">
        </el-table-column>
        <el-table-column
          label="公司税率"
          prop="taxCom">
        </el-table-column>
        <el-table-column
          label="描述"
          prop="dsc">
        </el-table-column>
        <el-table-column
          label="事项"
          prop="name">
          <template slot-scope="scope">
            <el-tag size="small" effect="dark"  type="info">{{scope.row.name}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          width="120"
          fit="true"
        >
          <template slot-scope="scope">
            <el-button type="primary" @click="editUserwage(scope.row.id)" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button type="danger"   @click="delsafeList(scope.row)"   icon="el-icon-delete" size="mini" circle></el-button>
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



        <el-form-item label="个人税率" :label-width="formLabelWidth"   >
        <el-input v-model="userprewageForm.taxMe" autocomplete="off"  Style="width: 172px" type="number"></el-input>
      </el-form-item>

        <el-form-item label="公司税率" :label-width="formLabelWidth"   >
          <el-input v-model="userprewageForm.taxCom" autocomplete="off"  Style="width: 172px" type="number"></el-input>
        </el-form-item>
        <el-form-item label="事项" :label-width="formLabelWidth"   >
          <el-input v-model="userprewageForm.name" autocomplete="off"  Style="width: 172px" ></el-input>
        </el-form-item>

        <el-form-item label="描述" :label-width="formLabelWidth"  >
          <el-input v-model="userprewageForm.dsc" autocomplete="off"></el-input>
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
  import safeandtaxApi from "@/api/safeandtax";
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

        safeList:[],
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

      saveUser(){
        console.log(this.userprewageForm)
        this.$refs.userprewageFormRef.validate((valid) => {

          if (valid&&this.flag1==true) {
           safeandtaxApi.addsafeList1(this.userprewageForm).then(response=>{
              //成功
              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
             this.getsafeList();
            })

          }
          else if(valid&&this.flag1==false){

            safeandtaxApi.updatesafeList(this.userprewageForm).then(response=>{
              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              //
              this.getsafeList();
            })
          }
          else {
            console.log('error submit!!');
            this.getlistType();
            return false;
          }
        });
        this.getsafeList();
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
      getsafeList(){
       safeandtaxApi.getsafeLis1(this.searchModel).then(response=>{
          this.safeList=response.data.rows;
          this.total=response.data.total;
          console.log(this.safeList)
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
      editUserwage(id){
       safeandtaxApi.gettaxlisteById(id).then(response => {
          this.isuse=true;
          this.flag1=false;
          this.userprewageForm = response.data;
        });
        this.title='编辑事项'
        this.flag=false;
        this.dialogFormVisible=true;
      },

      delsafeList(safeList){
        this.$confirm(`是否要删除${safeList.name}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
         safeandtaxApi.deltaxById(safeList.id).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
           this.getsafeList();
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

      this.getsafeList();
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
