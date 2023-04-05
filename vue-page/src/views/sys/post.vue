+ <template>
  <div>
    <el-card  id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.rolename" placeholder="角色名称" clearable></el-input>

          <el-button type="primary" @click="getRoleList"   round icon="el-icon-search">查询</el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button  @click="openEditUI" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table
        :data="roleList"
        stripe
        style="width: 100%">
        <el-table-column
          prop="date"
          label="#"
          width="80"
        >
          <template slot-scope="scope">
            {{(searchModel.pageNo-1)*searchModel.pageSize+scope.$index+1}}
          </template>
        </el-table-column>


        <el-table-column
          prop="rolename"
          label="角色名称"
          width="160">
        </el-table-column>

        <el-table-column
          prop="roledec"
          label="角色描述"
          width="760"
        >
        </el-table-column>
        <el-table-column

          label="操作"
        >
          <template slot-scope="scope">
            <el-button type="primary" @click="editRole(scope.row.id)" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button type="danger"   @click="delRole(scope.row)"   icon="el-icon-delete" size="mini" circle></el-button>
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
      <el-form :model="roleForm" :rules="rules" ref="roleFormRef">

        <el-form-item label="角色名称" :label-width="formLabelWidth"  prop="rolename">
          <el-input v-model="roleForm.rolename" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="角色描述" :label-width="formLabelWidth"  prop="roletag">
          <el-input v-model="roleForm.roledec" autocomplete="off" type="number"></el-input>
        </el-form-item>


        <el-form-item
          prop="roleDesc"
          label="权限设置"
          :label-width="formLabelWidth"
        >
          <el-tree
            ref="menuRef"
            :data="menuList"
            :props="menuProps"
            node-key="menuid"
            show-checkbox
            style="width:85%"
            default-expand-all

          ></el-tree>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRole">确 定</el-button>
      </div>
    </el-dialog>
  </div>


</template>

<script>
  import roleApi from '@/api/roleMange';
  import menuApi from "@/api/menuMange";
  export default {
    name: "role",
    data(){
      return{
        menuList:[],
        menuProps:{
          children:'children',
          label:'title',
        },
        flag1:true,
        formLabelWidth:"130px",
        dialogFormVisible:false,
        roleForm:{},
        title:"",
        total:0,
        searchModel:{
          pageNo:1,
          pageSize:10,
        },
        roleList:[],
        rules:{

          rolename: [
            { required: true, message: '请输入角色描述', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1到 50 个字符', trigger: 'blur' }
          ],

        }
      }
    },
    methods:{
      saveRole(){
        this.$refs.roleFormRef.validate((valid) => {
          let che = this.$refs.menuRef.getCheckedKeys();
          let halfche = this.$refs.menuRef.getHalfCheckedKeys();
          this.roleForm.menuidList=che.concat(halfche);
          if (valid&&this.flag1) {
            roleApi.addRole(this.roleForm).then(response=>{
              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              //
              this.getRoleList();
            })
          }
          else if(valid&&!this.flag1){
            roleApi.updateRole(this.roleForm).then(response=>{
              //成功
              let che = this.$refs.menuRef.getCheckedKeys();
              let halfche = this.$refs.menuRef.getHalfCheckedKeys();
              this.roleForm.menuidList=che.concat(halfche);
              console.log("oyhj:"+this.roleForm.menuidList)

              this.$message(
                {
                  message: response.message,
                  type: 'success',
                })
              //
              this.dialogFormVisible=false;
              //
              this.getRoleList();
            })
          }
          else {
            console.log('error submit!!');
            this.getRoleList();
            return false;
          }
        });
      },
      handleSizeChange(pageSize){
        this.searchModel.pageSize=pageSize;
        this.getRoleList();
      },
      currentPage4(){},
      handleCurrentChange(pageNo){
        this.searchModel.pageNo=pageNo;
        this.getRoleList();
      },

      getRoleList(){
        roleApi.getRoleList(this.searchModel).then(response=>{
          this.roleList=response.data.rows;
          this.total=response.data.total;
        });
      },
      openEditUI(){
        this.flag1=true;
          this.title='新增角色';
        this.dialogFormVisible=true;
      },
      editRole(id){
        this.flag1=false;
        roleApi.getRoleById(id).then(response => {
          this.roleForm = response.data;


        });
        this.title='编辑角色'
        roleApi.getRoleById(id).then((response)=>{
          this.$refs.menuRef.setCheckedKeys(response.data.menuidList);
        });

        this.dialogFormVisible=true;
      },

      delRole(role){
        this.$confirm(`是否要删除${role.rolename}角色?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          roleApi.delRoleById(role.id).then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getRoleList();
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      clearForm(){
        this.$refs.roleFormRef.clearValidate();
        this.roleForm= {
          roleId:"",
          roledec:"",
          rolename:"",

        };
        this.$refs.menuRef.setCheckedKeys([]);

      },
      getAllmenu(){
        menuApi.getAllMenu().then(response=>{
          this.menuList=response.data;
        })
      }
    },
    created() {
      this.getRoleList();
      this.getAllmenu()
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
