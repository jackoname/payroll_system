 <template>
  <div>
    <el-card  id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.post_name" placeholder="职位名称" clearable></el-input>

          <el-select
            :clearable="true"
            v-model="searchModel.id"
            @focus="getDepList"
            @blur="myblur"
            placeholder="请选择"
            :loading="isload">
            <el-option
              v-for=" item in departmentList"
              :key="item.id"
              :value="item.id"
              :label="item.depart"
            >
            </el-option>
          </el-select>

          <el-button type="primary" @click="getPostList"   round icon="el-icon-search">查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button  @click="openEditUI" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table
        :data="postList"
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
<!--          prop="postId"-->
<!--          label="职位标志"-->
<!--          width="160">-->
<!--          <template slot-scope="scope">-->
<!--            <el-tag size="small" effect="dark" type="success">{{scope.row.postId}}</el-tag>-->
<!--          </template>-->
<!--        </el-table-column>-->



        <el-table-column
          prop="postName"
          label="职位名称"
          width="160">
          <template slot-scope="scope">
            <el-tag size="small" effect="dark" type="success">{{scope.row.postName}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          prop="dsc"
          label="职位描述"
          width="160"
        >
        </el-table-column>

        <el-table-column
          prop="depName"
          label="所属部门"
          width="560">
          <template slot-scope="scope">
            <el-tag size="small" effect="dark" type="success">{{scope.row.depName}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="editpost(scope.row.postId)" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button type="danger"   @click="delPost(scope.row)"   icon="el-icon-delete" size="mini" circle></el-button>
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
      <el-form :model="postForm" :rules="rules" ref="postFormRef">

        <el-form-item label="职位名称" :label-width="formLabelWidth"  prop="postName">
          <el-input v-model="postForm.postName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="职位描述" :label-width="formLabelWidth" >
          <el-input v-model="postForm.dsc" autocomplete="off" ></el-input>
        </el-form-item>

      <el-form-item label="所属部门" :label-width="formLabelWidth" prop="postName">

        <el-select
          v-model="postForm.depid"
          @focus="getDepList"
          @blur="myblur"
          placeholder="请选择"
          :loading="isload">
          <el-option
            v-for=" item in departmentList"
            :key="item.id"
            :value="item.id"
            :label="item.depart"
          >
          </el-option>
        </el-select>
      </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="savePost">确 定</el-button>
      </div>
    </el-dialog>
  </div>


</template>

<script>
  import postApi from '@/api/postMange';
  import depApi from '@/api/depMange';
  export default {
    name: "post",
    data(){
      return{
        menuList:[],
        menuProps:{
          children:'children',
          label:'title',
        },

        isload:true,
        departmentList:[],
        flag1:true,
        formLabelWidth:"130px",
        dialogFormVisible:false,
        postForm:{},
        title:"",
        total:0,
        searchModel:{
          pageNo:1,
          pageSize:10,
        },
        postList:[],
        depid:'',
        rules:{
          postName: [
            { required: true, message: '不能为空', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1到 50 个字符', trigger: 'blur' }
          ],

        }
      }
    },
    methods:{
      savePost(){
        this.$refs.postFormRef.validate((valid) => {
          if (valid&&this.flag1) {
            postApi.addPost(this.postForm).then(response=>{
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
        else  if(valid&&!this.flag1){
            postApi.updatePost(this.postForm).then(response=>{

              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              //
              this.getPostList();
            })
          }
          else {
            console.log('error submit!!');
            this.getPostList();
            return false;
          }

          this.getPostList();
        });
      },
      handleSizeChange(pageSize){
        this.searchModel.pageSize=pageSize;
        this.getPostList();
      },
      currentPage4(){},
      handleCurrentChange(pageNo){
        this.searchModel.pageNo=pageNo;
        this.getPostList();
      },

      getPostList(){
        postApi.getPostList(this.searchModel).then(response=>{
          this.postList=response.data.rows;
          this.total=response.data.total;
        });

      },
      getDepList(){
        depApi.getAllDep().then(response=>{
          this.departmentList=response.data;
        })
        this.isload=false;
      },
      openEditUI(){
        this.flag1=true;
        this.title='新增职位';
        this.dialogFormVisible=true;
      },
      editpost(postId){
        this.flag1=false;
        postApi.getPostById(postId).then(response => {
          this.postForm = response.data;
        });
        this.title='编辑职位'
        this.dialogFormVisible=true;
      },
      myblur(){

        console.log(this.postForm)
      },
      delPost(post){
        this.$confirm(`是否要删除${post.postName}的职位?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          postApi.delPostById(post.postId).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getPostList();
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
        this.getPostList();
      },
      clearForm(){
        this.$refs.postFormRef.clearValidate();
        this.postForm= {
          postId:"",
          dsc:"",
          postName:"",

        };
      },

    },
    created() {
      this.getPostList();
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
