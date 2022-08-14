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
          <el-form-item label="类别编号" prop="kindNo">
            <el-input
              v-model="queryParams.kindNo"
              placeholder="请输入类别编号"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="类别名称" prop="kindName">
            <el-input
              v-model="queryParams.kindName"
              placeholder="请输入类别名称"
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
              v-has="'add'"
              >新增项目类别
            </el-button>
          </el-col>
        </el-row>

        <!-- 数据表格 -->
        <el-table v-loading="loading" :data="hsKindList">
          <!-- 数据表格列 -->
          <el-table-column label="项目类别ID" align="center" prop="kindId" />
          <el-table-column label="类别编号" align="center" prop="kindNo" />
          <el-table-column label="类别名称" align="center" prop="kindName" />
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
      </el-col>
    </el-row>
    <!-- 添加修改hsKind对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <!-- 数据编辑表单组件 -->
        <el-form-item label="类别编号" prop="kindNo">
          <el-input v-model="form.kindNo" placeholder="请输入类别编号" />
        </el-form-item>
        <el-form-item label="类别名称" prop="kindName">
          <el-input v-model="form.kindName" placeholder="请输入类别名称" />
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
  listHsKindByPage,
  getHsKind,
  addHsKind,
  updateHsKind,
  deleteHsKind,
} from "@/api/kind";
import hasDirective from "@/directives/has";
export default {
  name: "HsKind",
  directives: {
    has: hasDirective,
  },
  data() {
    return {
      editAndDelete: true,
      // HsKind类型
      typeOptions: undefined,
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 当前页
      pageNum: 0,
      // 每页数据量
      pageSize: 0,
      // HsKind表格数据
      hsKindList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 数据查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        kindId: undefined,
        kindNo: undefined,
        kindName: undefined,
      },
      // 数据编辑表单
      form: {},
      // 表单数据校验规则
      rules: {
        kindId: [
          { required: true, message: "项目类别ID不能为空", trigger: "blur" },
        ],
        kindNo: [
          { required: true, message: "类别编号不能为空", trigger: "blur" },
        ],
        kindName: [
          { required: true, message: "类别名称不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
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
      listHsKindByPage(this.queryParams).then((response) => {
        // api
        // console.log(response);
        this.hsKindList = response.list;
        this.total = response.total;
        this.pageNum = response.pageNum;
        this.pageSize = response.pageSize;
        this.loading = false;

        // mock
        // this.hsKindList = response.data.list;
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
        kindId: undefined,
        kindNo: undefined,
        kindName: undefined,
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
        kindId: undefined,
        kindNo: undefined,
        kindName: undefined,
      };
      this.getList();
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.resetForm();
      this.open = true;
      this.title = "添加项目类别";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.resetForm();
      getHsKind(row.kindId).then((response) => {
        this.form = response;
        this.open = true;
        this.title = "修改项目类别";
      });
    },

    /** 数据提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.title === "修改项目类别") {
            updateHsKind(this.form).then((response) => {
              this.open = false;
              this.title = "";
              this.getList();
              this.$message.success(response);
            });
          } else {
            addHsKind(this.form).then((response) => {
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
        '是否确认删除名称为"' + row.kindName + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return deleteHsKind(row.kindId);
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
