<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="24" :xs="24">
        <!-- 数据筛选表单 -->
        <el-form
          ref="queryForm"
          :model="queryParams"
          :inline="true"
          label-width="100px"
          class="center"
        >
          <!-- 数据筛选表单组件 -->
          <el-form-item label="角色编号" prop="roleNo">
            <el-input
              v-model="queryParams.roleNo"
              placeholder="请输入角色编号"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="角色名称" prop="roleName">
            <el-input
              v-model="queryParams.roleName"
              placeholder="请输入角色名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

          <!-- 数据筛选操作按钮 -->
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleQuery"
              >搜索
            </el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
              >重置
            </el-button>
          </el-form-item>
        </el-form>
        <el-row :gutter="10" class="mb8">
          <el-col :span="3">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="small"
              @click="handleAdd"
              >新增角色
            </el-button>
          </el-col>
        </el-row>

        <!-- 数据表格 -->
        <el-table v-loading="loading" :data="hsRoleList">
          <!-- 数据表格列 -->
          <el-table-column label="角色ID" align="center" prop="roleId" />
          <el-table-column label="角色名字" align="center" prop="roleName" />
          <el-table-column label="角色编号" align="center" prop="roleNo" />
          <el-table-column label="角色描述" align="center" prop="remark" />
          <el-table-column label="创建时间" align="center" prop="createTime" />
          <el-table-column
            label="修改时间"
            align="center"
            prop="modifiedTime"
          />
          <el-table-column label="是否启用" align="center" prop="roleStatus">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.roleStatus"
                :active-value="1"
                :inactive-value="0"
                active-color="#13ce66"
                inactive-color="#ff4949"
                @change="changeSwitch(scope.row)"
              />
            </template>
          </el-table-column>
          <!-- 数据行操作按钮 -->
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="small"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                >修改
              </el-button>
              <el-button
                size="small"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 数据分页加载 -->
        <el-pagination
          :current-page="pageNum"
          :page-sizes="[5, 10, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          @pagination="getList"
        />
      </el-col>
    </el-row>
    <!-- 添加修改角色对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <!-- 数据编辑表单组件 -->
        <el-form-item label="角色名字" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名字" />
        </el-form-item>
        <el-form-item label="角色编号" prop="roleNo">
          <el-input v-model="form.roleNo" placeholder="请输入角色编号" />
        </el-form-item>
        <el-form-item label="角色描述" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入角色描述" />
        </el-form-item>
      </el-form>
      <!-- 数据提交操作按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
/** 导入axios Api */
import {
  listHsRoleByPage,
  getHsRole,
  addHsRole,
  updateHsRole,
  deleteHsRole,
} from "@/api/role";
export default {
  name: "HsRole",
  data() {
    return {
      // 角色类型
      typeOptions: undefined,
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 当前页
      pageNum: 0,
      // 每页数据量
      pageSize: 0,
      // 表格数据
      hsRoleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        roleName: undefined,
        roleNo: undefined,
      },
      // 数据编辑表单
      form: {},
      // 表单数据校验规则
      rules: {
        roleName: [
          { required: true, message: "角色名字不能为空", trigger: "blur" },
        ],
        roleNo: [
          { required: true, message: "角色编号不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询HsRole数据列表 */
    getList() {
      this.loading = true;
      listHsRoleByPage(this.queryParams).then((response) => {
        this.hsRoleList = response.list;
        this.total = response.total;
        this.pageNum = response.pageNum;
        this.pageSize = response.pageSize;
        this.loading = false;
      });
    },
    /** 处理分页 */
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getList();
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getList();
    },

    /** 数据编辑对话框取消按钮 */
    cancel() {
      this.open = false;
      this.resetForm();
    },

    /** 数据编辑表单清空 */
    resetForm() {
      this.form = {
        roleId: undefined,
        roleName: undefined,
        roleNo: undefined,
        remark: undefined,
      };
    },

    /** 数据筛选参数重置按钮 */
    resetQueryParams() {
      this.queryParams.pageNum = 1;
      this.queryParams.pageSize = 5;
    },

    /** 数据筛选搜索按钮 */
    handleQuery() {
      this.resetQueryParams();
      this.getList();
      this.$message.success("查询成功");
    },

    /** 数据筛选重置按钮 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 5,
        roleName: undefined,
        roleNo: undefined,
      };
      this.getList();
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.resetForm();
      this.open = true;
      this.title = "添加角色";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.resetForm();
      getHsRole(row.roleId).then((response) => {
        this.form = response;
        this.open = true;
        this.title = "修改角色";
      });
    },

    /** 修改启用状态 */
    changeSwitch(row) {
      this.resetForm();
      getHsRole(row.roleId).then((response) => {
        this.form = response;
        this.form.roleStatus = row.roleStatus;
        updateHsRole(this.form).then(() => {
          this.getList();
        });
      });
    },

    /** 数据提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.title === "修改角色") {
            updateHsRole(this.form).then((response) => {
              this.open = false;
              this.title = "";
              this.getList();
              this.$message.success(response.data);
            });
          } else {
            addHsRole(this.form).then((response) => {
              this.open = false;
              this.title = "";
              this.getList();
              this.$message.success(response.data);
            });
          }
        }
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm(
        '是否确认删除名称为"' + row.roleName + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return deleteHsRole(row.roleId);
        })
        .then(() => {
          this.getList();
          this.$message.success("删除成功");
        })
        .catch(() => {});
    },
  },
};
</script>

<style scoped>
.center,
.el-pagination {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>