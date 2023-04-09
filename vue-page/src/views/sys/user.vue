  <template>
    <div>
      <el-card  id="search">
        <el-row>
          <el-col :span="20">
            <el-input v-model="searchModel.username" placeholder="用户名" clearable></el-input>
            <el-select
              :clearable="true"
              v-model="searchModel.roleid"
              @focus="getDepList"

              placeholder="请选择"
             >
              <el-option
                v-for=" item in roleList"
                :key="item.id"
                :value="item.id"
                :label="item.rolename"
              >
              </el-option>
            </el-select>
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
          label="头像"
          prop="name">
            <el-avatar icon="el-icon-user-solid"></el-avatar>

          </el-table-column>

          <el-table-column
            prop="username"
            label="用户名">
            <template slot-scope="scope">
              <el-tag size="small" effect="dark" :type="scope.row.stateuser=='禁用'?'danger':'success'">{{scope.row.username}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名">
            <template slot-scope="scope">
              <el-tag size="small" effect="dark" :type="scope.row.stateuser=='禁用'?'danger':'success'">{{scope.row.name}}</el-tag>
            </template>
          </el-table-column>

          <el-table-column
            prop="depName"
            label="部门">
            <template slot-scope="scope">
              <el-tag size="small" effect="dark" :type="scope.row.stateuser=='禁用'?'danger':'success'" >{{scope.row.depName}}</el-tag>
            </template>
          </el-table-column>

          <el-table-column
            prop="postName"
            label="职位"
            width="155">
            <template slot-scope="scope">
              <el-tag effect="dark" size="small" :type="scope.row.stateuser=='禁用'?'danger':'success'">{{scope.row.postName}}</el-tag>
            </template>
          </el-table-column>

          <el-table-column
            prop="postName"
            label="角色"
            width="155">
            <template slot-scope="scope">
              <el-tag effect="dark" size="small" :type="scope.row.stateuser=='禁用'?'danger':'success'">
                {{scope.row.roleName}}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="statesex"
            label="性别">
          </el-table-column>

          <el-table-column
            prop="age"
            label="年龄">
          </el-table-column>
          <el-table-column
            prop="stateuser"
            label="状态">
            <template slot-scope="scope">
              <el-tag size="small" effect="dark"  :type="scope.row.stateuser=='禁用'?'danger':'success'">{{scope.row.stateuser}}</el-tag>
            </template>
          </el-table-column>


<!--          <el-table-column-->
<!--            prop="phone"-->
<!--            label="电话"-->
<!--            width="125">-->
<!--          </el-table-column>-->

<!--          <el-table-column-->
<!--            prop="email"-->
<!--            label="电子邮件"-->
<!--          width="155">-->
<!--          </el-table-column>-->



<!--          <el-table-column-->
<!--            prop="bankno"-->
<!--            label="银行卡号"-->
<!--          width="165">-->
<!--          </el-table-column>-->

<!--          <el-table-column-->
<!--            prop="idcardno"-->
<!--            label="身份证"-->
<!--          width="165">-->
<!--          </el-table-column>-->

<!--          <el-table-column-->
<!--            prop="birthday"-->
<!--            label="出生日期"-->
<!--          width="100">-->
<!--          </el-table-column>-->

<!--          <el-table-column-->
<!--            prop="address"-->
<!--            label="地址">-->
<!--          </el-table-column>-->

          <el-table-column
            label="详细信息">
            <template slot-scope="scope">
              <el-button type="text" size="mini" @click="getDesdata(scope.row)">
                <i class="el-icon-view el-icon--right" ></i> 查看
              </el-button>
            </template>
          </el-table-column>

          <el-table-column
            label="操作"
            width="120"
            fit="true"
            >
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
        <el-form-item>

        </el-form-item>
        <el-form-item label="用户名" :label-width="formLabelWidth"  prop="username">
          <el-input v-model="userForm.username" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="登录密码" :label-width="formLabelWidth"  prop="password"  v-show="flag1">
          <el-input type="password" v-model="userForm.password" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth"  prop="name">
          <el-input v-model="userForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="userForm.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电子邮件" :label-width="formLabelWidth"  prop="email">
          <el-input v-model="userForm.email" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="性   别" :label-width="formLabelWidth" >
          <template>
            <el-radio v-model="userForm.sex" label="1">男</el-radio>
            <el-radio v-model="userForm.sex" label="2" >女</el-radio>
          </template>
        </el-form-item>


        <el-form-item label="年   龄" :label-width="formLabelWidth" >
          <el-input v-model="userForm.age" autocomplete="off" type="number" width="20 "></el-input>
        </el-form-item>

        <el-form-item label="出生日期" :label-width="formLabelWidth"  >
          <el-date-picker
            v-model="userForm.birthday"
            type="date"
            value-format="yyyy-MM-dd"
            @input="$forceUpdate()"
            placeholder="选择日期">
          </el-date-picker>

        </el-form-item>
        <el-form-item label="地   址" :label-width="formLabelWidth"  >
          <el-input v-model="userForm.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="银行卡号" :label-width="formLabelWidth"  prop="bankno"  >
          <el-input v-model="userForm.bankno" autocomplete="off" type="number"></el-input>
        </el-form-item>
        <el-form-item label=" 身份证 " :label-width="formLabelWidth"   prop="idcardno" >
          <el-input v-model="userForm.idcardno" autocomplete="off" type="number"></el-input>
        </el-form-item>

        <el-form-item label=" 备注" :label-width="formLabelWidth"  >
          <el-input v-model="userForm.remarks" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户角色" :label-width="formLabelWidth">
          <el-checkbox-group
            style="width: 85%"
            v-model="userForm.roleIdList"
            :max="1">
            <el-checkbox v-for=" role in roleList" :label="role.id" :key="role.id">{{role.rolename}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>

        <el-form-item label="部门" :label-width="formLabelWidth">
<!--          <el-checkbox-group-->
<!--            style="width: 85%"-->
<!--            v-model="userForm.depIdList"-->
<!--            :max="1">-->
<!--            <el-checkbox v-for=" department in depList " :label="department.id" :key="department.id">{{department.depart}}</el-checkbox>-->
<!--          </el-checkbox-group>-->
          <el-select
            :clearable="true"
            v-model="userForm.depIdList[0]"
            @blur="myblur"
            @focus="myfocus"
            placeholder="请选择"
            >
            <el-option
              v-for=" item in depList"
              :key="item.id"
              :value="item.id"
              :label="item.depart"
            >
            </el-option>
          </el-select>

        </el-form-item>


        <el-form-item label="职位" :label-width="formLabelWidth">
<!--          <el-checkbox-group-->
<!--            style="width: 85%"-->
<!--            v-model="userForm.postIdList"-->
<!--            :max="1">-->
<!--            <el-checkbox v-for=" post in postList" :label="post.postId" :key="post.postId">{{post.postName}}</el-checkbox>-->
<!--          </el-checkbox-group>-->
          <el-select
            :clearable="true"
            v-model="userForm.postIdList[0]"
            @focus="myblur"
            placeholder="请选择">
            <el-option

              v-for=" item in postList"
              :key="item.postId"
              :value="item.postId"
              :label="item.postName"

            >
            </el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="用户状态" :label-width="formLabelWidth">
          <el-switch
            v-model="userForm.isuse"
            :active-value="1"
            :inactive-value="0"
          >
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUser">确 定</el-button>
      </div>
    </el-dialog>

    <el-drawer
      :visible.sync="table"
      :direction="direction"
      size="40%">

      <el-divider></el-divider>
      <el-card class="box-card" shadow="away">
      <el-descriptions   class="margin-top"  :column="1" size="mini" border="true">
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-picture-outline"></i>
            头像
          </template>
          <el-avatar icon="el-icon-user"></el-avatar>
        </el-descriptions-item>


        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            用户名
          </template>
         {{dscdata.username}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
           姓名
          </template>
          {{dscdata.name}}
        </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          年龄
        </template>
        {{dscdata.age}}
      </el-descriptions-item>


      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          性别
        </template>
        {{dscdata.statesex}}
      </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            出生日期
          </template>
          {{dscdata.birthday}}
        </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-suitcase"></i>
          部门
        </template>
        {{dscdata.depName}}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-monitor"></i>
          职位
        </template>
        {{dscdata.postName }}
      </el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-message"></i>
          邮箱
        </template>
        {{dscdata.email}}
      </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号
          </template>
          {{dscdata.phone}}
        </el-descriptions-item>


        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-postcard"></i>
            身份证号
          </template>
          {{dscdata.idcardno}}
        </el-descriptions-item>

      <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-bank-card"></i>
        银行卡号
      </template>
      {{dscdata.bankno}}
      </el-descriptions-item>





      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline"></i>
          居住地
        </template>
        {{dscdata.address}}
      </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>
            联系地址
          </template>
          {{dscdata.address}}
        </el-descriptions-item>
        <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          备注
        </template>
        {{dscdata.remarks}}
        </el-descriptions-item>
      </el-descriptions>
      </el-card>
      <el-divider></el-divider>
    </el-drawer>

    </div>
  </template>

  <script>
    import userApi from '@/api/userMange'
    import  roleApi from '@/api/roleMange'
    import  depApi from '@/api/depMange'
    import  postApi from '@/api/postMange'
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
              depList:[],
              table:false,
              postList:[],
              flag1:true,
              direction: 'rtl',//rtl / ltr / ttb / btt
              formLabelWidth:"130px",
              dialogFormVisible:false,
              userForm:{
                roleIdList:[],
                depIdList:[],
                postIdList:[],
                table: false,
                dialog: false,
                loading: false,
                birthday:"",
              },
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
               password:[
                  { required: true, message: '请输入登录初始密码', trigger: 'blur' },
                  { min: 6, max: 5000, message: '长度在 6 到 500个字符', trigger: 'blur' }
                ],
                email:[
                  { required: true, message: '请输入电子邮箱', trigger: 'blur' },
                  { validator: checkEmail, trigger: 'blur' }
                ],

                idcardno:[
                  { required: true, message: '请输入身份证', trigger: 'blur' },
                  { validator: checkidcard, trigger: 'blur' }
                ],
                bankno:[
                  { required: true, message: '请输入银行卡号', trigger: 'blur' },
                  { validator: checkbankno, trigger: 'blur' }

                ],
               phone:[
                  { required: true, message: '请输入手机', trigger: 'blur' },
                  { validator: checkphone, trigger: 'blur' }
                  ]
              }
            }
        },
        methods:{

            saveUser(){
              console.log(this.userForm)
              this.$refs.userFormRef.validate((valid) => {

                if (valid&&this.flag1==true) {
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
          myblur(){
            this.loading=false;
            this.getPostListByDepId(this.userForm.depIdList[0]);
          },
          myfocus(){
              this.userForm.postIdList[0]="";
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
              this.userList.forEach(item=>{
                if(item.isuse==0){
                  item.stateuser="禁用";
                }
                else {
                 item.stateuser="正常";
                }

                if(item.sex==1){
                  item.statesex="男";
                }
                else if(item.sex==2){
                  item.statesex="女";
                }
                item.birthday=this.formatDate(item.birthday);
                console.log(item);
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
              this.userForm.birthday=this.formatDate(this.userForm.birthday);
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

          getAllRoleList(){
            roleApi.getAllRole().then(response => {
              this.roleList = response.data;
              console.log(this.roleList);
            });
          },
          getAllDepList(){
            depApi.getAllDep().then(response => {
              this.depList = response.data;

              console.log( this.depList);
            });
          },
          getAllPostList(){
            postApi.getAllPost().then(response => {
              this.postList = response.data;

            });
          },

          getPostListByDepId(depIdList){

            postApi.getPostListByDepId(depIdList).then(response => {
              console.log(depIdList+"----")
              this.postList = response.data;
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
            this.getAllRoleList();
            this.getAllDepList();
            this.getAllPostList();
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
