<template>
  <div class="app-container">
    <el-row :gutter="10">
      <div :span="24" :xs="24">
        <!-- 数据筛选表单 -->
        <el-form
          ref="queryForm"
          :model="queryParams"
          :inline="true"
          label-width="100px"
          class="center"
        >
          <!-- 数据筛选表单组件 -->
          <el-col :span="14">
            <el-form-item label="项目编号" prop="projectNo">
              <el-input
                v-model="queryParams.projectNo"
                placeholder="请输入项目编号"
                clearable
                size="small"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="项目名称" prop="projectName">
              <el-input
                v-model="queryParams.projectName"
                placeholder="请输入项目名称"
                clearable
                size="small"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="项目类别" prop="kindId">
              <!--                  <el-input-->
              <!--                    v-model="queryParams.kindId"-->
              <!--                    placeholder="请输入项目类别"-->
              <!--                    clearable-->
              <!--                    size="small"-->
              <!--                    @keyup.enter.native="handleQuery"-->
              <!--                  />-->
              <el-select
                v-model="queryParams.kindId"
                placeholder="请选择"
                style="width: 195px"
              >
                <el-option
                  v-for="item in typeOptions"
                  :key="item.kindId"
                  :label="item.kindName"
                  :value="item.kindId"
                  @keyup.enter.native="handleQuery"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="价格区间" prop="projectPrice">
              <el-col :span="16">
                <el-input
                  :span="18"
                  v-model="queryParams.projectPrice"
                  placeholder="请输入价格区间"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery"
                />
              </el-col>
              <el-col :span="8"> 以下 </el-col>
            </el-form-item>
          </el-col>
          <!-- 数据筛选操作按钮 -->
          <el-col :span="4">
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
          </el-col>
        </el-form>
        <el-row :gutter="10" class="mb8">
          <el-col :span="3">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="small"
              @click="handleAdd"
              v-has="'add'"
              >新增项目
            </el-button>
          </el-col>
        </el-row>

        <!-- 数据表格 -->
        <el-table v-loading="loading" :data="hsProjectList">
          <!-- 数据表格列 -->
          <el-table-column label="项目序号" align="center" prop="projectId" />
          <el-table-column label="项目编号" align="center" prop="projectNo" />
          <el-table-column label="项目名称" align="center" prop="projectName" />
          <el-table-column
            label="项目价格"
            align="center"
            prop="projectPrice"
          />
          <el-table-column label="项目类别" align="center" prop="kindId" />
          <!-- 数据行操作按钮 -->
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
            v-if="editAndDelete"
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
      </div>
    </el-row>
    <!-- 添加修改hsProject对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <!-- 数据编辑表单组件 -->
        <el-form-item label="项目编号" prop="projectNo">
          <el-input v-model="form.projectNo" placeholder="请输入项目编号" />
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目价格" prop="projectPrice">
          <el-input v-model="form.projectPrice" placeholder="请输入项目价格" />
        </el-form-item>
        <el-form-item label="项目类别" prop="kindId">
          <!--          <el-input v-model="form.kindId" placeholder="请输入项目类别" />-->
          <el-select v-model="form.kindId" placeholder="请选择">
            <el-option
              style="height: 200px"
              v-for="item in typeOptions"
              :key="item.kindId"
              :label="item.kindName"
              :value="item.kindId"
              @keyup.enter.native="handleQuery"
            >
            </el-option>
          </el-select>
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
  listHsProjectByPage,
  getHsProject,
  addHsProject,
  updateHsProject,
  deleteHsProject,
  getHsProjectByPrice,
} from "@/api/project";
import { listHsKind } from "@/api/kind";
import hasDirective from "@/directives/has";

export default {
  name: "HsProject",
  directives: {
    has: hasDirective,
  },
  data() {
    return {
      editAndDelete: true,
      // HsProject类型
      typeOptions: undefined,
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 当前页
      pageNum: 0,
      // 每页数据量
      pageSize: 0,
      // HsProject表格数据
      hsProjectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        projectId: undefined,
        projectNo: undefined,
        projectName: undefined,
        kindId: undefined,
        projectPrice: undefined,
      },
      // 数据编辑表单
      form: {},
      // 表单数据校验规则
      rules: {
        projectNo: [
          { required: true, message: "项目类别ID不能为空", trigger: "blur" },
        ],
        projectName: [
          { required: true, message: "类别编号不能为空", trigger: "blur" },
        ],
        projectPrice: [
          { required: true, message: "类别编号不能为空", trigger: "blur" },
        ],
        kindId: [
          { required: true, message: "类别名称不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
    // 查询所有类别赋值typeOptions
    listHsKind().then((response) => {
      this.typeOptions = response;
    });
    // 设置页面级别权限
    if (
      !(
        localStorage.getItem("permissions") &&
        JSON.parse(localStorage.getItem("permissions")).includes(
          "editAndDelete"
        )
      )
    ) {
      this.editAndDelete = false;
    }
  },
  methods: {
    /** 查询HsKind数据列表 */
    getList() {
      this.loading = true;
      listHsProjectByPage(this.queryParams).then((response) => {
        // console.log(response);
        this.hsProjectList = response.list;
        this.total = response.total;
        this.pageNum = response.pageNum;
        this.pageSize = response.pageSize;
        this.loading = false;

        // // mock
        // this.hsProjectList = response.data.list;
        // this.total = response.data.total;
        // this.pageNum = response.data.pageNum;
        // this.pageSize = response.data.pageSize;
        // this.loading = false;
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
        projectNo: undefined,
        projectName: undefined,
        kindId: undefined,
        projectPrice: undefined,
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
        projectId: undefined,
        projectNo: undefined,
        projectName: undefined,
        kindName: undefined,
        projectPrice: undefined,
      };
      this.getList();
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.resetForm();
      this.open = true;
      this.title = "添加项目";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.resetForm();
      getHsProject(row.projectNo).then((response) => {
        this.form = response;
        this.open = true;
        this.title = "修改项目";
      });
    },

    /** 数据提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.title === "修改项目") {
            updateHsProject(this.form).then((response) => {
              this.open = false;
              this.title = "";
              this.getList();
              this.$message.success(response);
            });
          } else {
            addHsProject(this.form).then((response) => {
              this.open = false;
              this.title = "";
              this.getList();
              this.$message.success(response);
            });
          }
        }
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm(
        '是否确认删除名称为"' + row.projectName + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return deleteHsProject(row.projectId);
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
