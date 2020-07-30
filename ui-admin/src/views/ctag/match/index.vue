<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="本地标准体系ID" prop="localSysId">
        <el-input
          v-model="queryParams.localSysId"
          placeholder="请输入本地标准体系ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品茗标准体系ID" prop="pmSysId">
        <el-input
          v-model="queryParams.pmSysId"
          placeholder="请输入品茗标准体系ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否更新(0: 否；1:是)" prop="isUpdated">
        <el-input
          v-model="queryParams.isUpdated"
          placeholder="请输入是否更新(0: 否；1:是)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标准更新时间" prop="gmtStandardUpdated">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.gmtStandardUpdated"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择标准更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="匹配更新时间" prop="gmtMatchModified">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.gmtMatchModified"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择匹配更新时间">
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
          v-hasPermi="['ctag:match:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ctag:match:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ctag:match:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ctag:match:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="matchList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="本地标准体系ID" align="center" prop="localSysId" />
      <el-table-column label="品茗标准体系ID" align="center" prop="pmSysId" />
      <el-table-column label="是否更新(0: 否；1:是)" align="center" prop="isUpdated" />
      <el-table-column label="标准更新时间" align="center" prop="gmtStandardUpdated" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtStandardUpdated, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="匹配更新时间" align="center" prop="gmtMatchModified" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtMatchModified, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ctag:match:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ctag:match:remove']"
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

    <!-- 添加或修改特征项匹配对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="本地标准体系ID" prop="localSysId">
          <el-input v-model="form.localSysId" placeholder="请输入本地标准体系ID" />
        </el-form-item>
        <el-form-item label="品茗标准体系ID" prop="pmSysId">
          <el-input v-model="form.pmSysId" placeholder="请输入品茗标准体系ID" />
        </el-form-item>
        <el-form-item label="是否更新(0: 否；1:是)" prop="isUpdated">
          <el-input v-model="form.isUpdated" placeholder="请输入是否更新(0: 否；1:是)" />
        </el-form-item>
        <el-form-item label="标准更新时间" prop="gmtStandardUpdated">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.gmtStandardUpdated"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择标准更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="匹配更新时间" prop="gmtMatchModified">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.gmtMatchModified"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择匹配更新时间">
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
import { listMatch, getMatch, delMatch, addMatch, updateMatch, exportMatch } from "@/api/ctag/match";

export default {
  name: "Match",
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
      // 特征项匹配表格数据
      matchList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        localSysId: undefined,
        pmSysId: undefined,
        isUpdated: undefined,
        gmtStandardUpdated: undefined,
        gmtMatchModified: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        localSysId: [
          { required: true, message: "本地标准体系ID不能为空", trigger: "blur" }
        ],
        pmSysId: [
          { required: true, message: "品茗标准体系ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询特征项匹配列表 */
    getList() {
      this.loading = true;
      listMatch(this.queryParams).then(response => {
        this.matchList = response.rows;
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
        localSysId: undefined,
        pmSysId: undefined,
        isUpdated: undefined,
        gmtStandardUpdated: undefined,
        gmtMatchModified: undefined
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
      this.title = "添加特征项匹配";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMatch(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改特征项匹配";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateMatch(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addMatch(this.form).then(response => {
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
      this.$confirm('是否确认删除特征项匹配编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMatch(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有特征项匹配数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMatch(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
