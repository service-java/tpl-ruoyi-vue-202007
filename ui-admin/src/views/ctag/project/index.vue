<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="工程编码(唯一)" prop="guid">
        <el-input
          v-model="queryParams.guid"
          placeholder="请输入工程编码(唯一)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工程地市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入工程地市"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年份" prop="year">
        <el-input
          v-model="queryParams.year"
          placeholder="请输入年份"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计价依据" prop="pricingBasis">
        <el-input
          v-model="queryParams.pricingBasis"
          placeholder="请输入计价依据"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="对应本地标准体系" prop="localSysId">
        <el-input
          v-model="queryParams.localSysId"
          placeholder="请输入对应本地标准体系"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态(0: 初始状态；1: 解析成功; 2: 解析失败)" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态(0: 初始状态；1: 解析成功; 2: 解析失败)" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="gmtCreate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.gmtCreate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="gmtModified">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.gmtModified"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="数据上传完成时间" prop="gmtDataUploaded">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.gmtDataUploaded"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择数据上传完成时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ctag:project:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ctag:project:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ctag:project:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ctag:project:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="工程编码(唯一)" align="center" prop="guid" />
      <el-table-column label="项目名称" align="center" prop="name" />
      <el-table-column label="工程地市" align="center" prop="city" />
      <el-table-column label="年份" align="center" prop="year" />
      <el-table-column label="计价依据" align="center" prop="pricingBasis" />
      <el-table-column label="对应本地标准体系" align="center" prop="localSysId" />
      <el-table-column label="状态(0: 初始状态；1: 解析成功; 2: 解析失败)" align="center" prop="status" />
      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="gmtModified" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数据上传完成时间" align="center" prop="gmtDataUploaded" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtDataUploaded, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ctag:project:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ctag:project:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改特征标记工程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工程编码(唯一)" prop="guid">
          <el-input v-model="form.guid" placeholder="请输入工程编码(唯一)" />
        </el-form-item>
        <el-form-item label="项目名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="工程地市" prop="city">
          <el-input v-model="form.city" placeholder="请输入工程地市" />
        </el-form-item>
        <el-form-item label="年份" prop="year">
          <el-input v-model="form.year" placeholder="请输入年份" />
        </el-form-item>
        <el-form-item label="计价依据" prop="pricingBasis">
          <el-input v-model="form.pricingBasis" placeholder="请输入计价依据" />
        </el-form-item>
        <el-form-item label="对应本地标准体系" prop="localSysId">
          <el-input v-model="form.localSysId" placeholder="请输入对应本地标准体系" />
        </el-form-item>
        <el-form-item label="状态(0: 初始状态；1: 解析成功; 2: 解析失败)">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="创建时间" prop="gmtCreate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.gmtCreate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" prop="gmtModified">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.gmtModified"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择修改时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="数据上传完成时间" prop="gmtDataUploaded">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.gmtDataUploaded"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择数据上传完成时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProject, getProject, delProject, addProject, updateProject, exportProject } from "@/api/ctag/project";

export default {
  name: "Project",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 特征标记工程表格数据
      projectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        guid: undefined,
        name: undefined,
        city: undefined,
        year: undefined,
        pricingBasis: undefined,
        localSysId: undefined,
        status: undefined,
        gmtCreate: undefined,
        gmtModified: undefined,
        gmtDataUploaded: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询特征标记工程列表 */
    getList() {
      this.loading = true;
      listProject(this.queryParams).then(response => {
        this.projectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        guid: undefined,
        name: undefined,
        city: undefined,
        year: undefined,
        pricingBasis: undefined,
        localSysId: undefined,
        status: "0",
        gmtCreate: undefined,
        gmtModified: undefined,
        gmtDataUploaded: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加特征标记工程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProject(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改特征标记工程";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateProject(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addProject(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除特征标记工程编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProject(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有特征标记工程数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProject(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
