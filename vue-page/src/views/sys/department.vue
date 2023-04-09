+ <template>
  <div>
    <el-card  id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.depart" placeholder="部门名称" clearable></el-input>

          <el-button type="primary" @click="getDepList"   round icon="el-icon-search">查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button  @click="openEditUI" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <el-table
        :data="depList"
        stripe
        style="width: 100%"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}"
        border="ture"
        fit="ture"
        stripe="true"
        size="mini"
        show-header="true"
        highlight-current-row="ture">
        <el-table-column
          prop="date"
          label="#"
          width="80"
        >
          <template slot-scope="scope">
            {{(searchModel.pageNo-1)*searchModel.pageSize+scope.$index+1}}
          </template>
        </el-table-column>

  <!--        <el-table-column-->
  <!--          prop="id"-->
  <!--          label="部门编号"-->
  <!--          width="160">-->
  <!--        </el-table-column>-->

        <el-table-column
          prop="depart"
          label="部门名称"
          width="160">
          <template slot-scope="scope">
            <el-tag size="small" effect="dark" type="success">{{scope.row.depart}}</el-tag>
          </template>
        </el-table-column>


        <el-table-column
          prop="departBoss"
          label="部门主管"
          width="160">
          <template slot-scope="scope">
            <el-tag size="small" effect="dark" type="success">{{scope.row.departBoss}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          prop="dsc"
          label="部门描述"
          width="560"
        >
        </el-table-column>
        <el-table-column

          label="操作"
        >
          <template slot-scope="scope">
            <el-button type="primary" @click="editDep(scope.row.id)" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button type="danger"   @click="delDep(scope.row)"   icon="el-icon-delete" size="mini" circle></el-button>
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
      <el-form :model="depForm" :rules="rules" ref="depFormRef">

        <el-form-item label="部门名称" :label-width="formLabelWidth"  prop="depart">
          <el-input v-model="depForm.depart" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="部门主管" :label-width="formLabelWidth"  prop="departBoss">
          <el-input v-model="depForm.departBoss" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="部门描述" :label-width="formLabelWidth"  prop="dsc ">
          <el-input v-model="depForm.dsc" autocomplete="off" ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveDep">确 定</el-button>
      </div>
    </el-dialog>
  </div>


</template>

<script>
  import depApi from '@/api/depMange';

  export default{
    name: "dep",
    data(){
      return{
        flag1:true,
        formLabelWidth:"130px",
        dialogFormVisible:false,
        depForm:{},
        title:"",
        total:0,
        searchModel:{
          pageNo:1,
          pageSize:10,
        },
        depList:[],
        rules:{

          depart: [
            { required: true, message: '请输入部门描述', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1到 50 个字符', trigger: 'blur' }
          ],
          departBoss: [
            { required: true, message: '请输入部门描述', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1到 50 个字符', trigger: 'blur' }
          ],


        }
      }
    },
    methods:{
      saveDep(){
        this.$refs.depFormRef.validate((valid) => {
          if (valid&&this.flag1) {
            depApi.addDep(this.depForm).then(response=>{
              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              //
              this.getDepList();
            })
          }
          else if(valid&&!this.flag1){
            depApi.updateDep(this.depForm).then(response=>{


              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              //
              this.getDepList();
            })
          }
          else {
            console.log('error submit!!');
            this.getDepList();
            return false;
          }
        });
      },
      handleSizeChange(pageSize){
        this.searchModel.pageSize=pageSize;
        this.getDepList();
      },


      getDepList(){
        depApi.getDepList(this.searchModel).then(response=>{
          this.depList=response.data.rows;
          this.total=response.data.total;
        });

      },
      openEditUI(){
        this.flag1=true;
        this.title='新增部门';
        this.dialogFormVisible=true;
      },
      editDep(id){
        this.flag1=false;
        depApi.getDepById(id).then(response => {
          this.depForm = response.data;
        });
        this.title='编辑部门'
        depApi.getDepById(id).then((response)=>{
          this.$refs.menuRef.setCheckedKeys(response.data.menuidList);
        });

        this.dialogFormVisible=true;
      },

      delDep(dep){
        this.$confirm(`是否要删除${dep.depart}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          depApi.delDepById(dep.id).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getDepList();
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      clearForm(){
        this.$refs.depFormRef.clearValidate();
        this.depForm= {
         departBoss:"",
          dsc:"",
          depatr:"",

        };
      },

    },
    created() {

      this.getDepList();

    },

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
