<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="工程ID" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入工程ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属的本地标准体系ID(冗余)" prop="localSysId">
        <el-input
          v-model="queryParams.localSysId"
          placeholder="请输入所属的本地标准体系ID(冗余)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="本地标准体系特征项ID" prop="localSysItemId">
        <el-input
          v-model="queryParams.localSysItemId"
          placeholder="请输入本地标准体系特征项ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品茗标准体系特征项ID" prop="pmSysItemId">
        <el-input
          v-model="queryParams.pmSysItemId"
          placeholder="请输入品茗标准体系特征项ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="填充值" prop="ctagValue">
        <el-input
          v-model="queryParams.ctagValue"
          placeholder="请输入填充值"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['ctag:detail:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ctag:detail:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ctag:detail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ctag:detail:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="detailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="工程ID" align="center" prop="projectId" />
      <el-table-column label="所属的本地标准体系ID(冗余)" align="center" prop="localSysId" />
      <el-table-column label="本地标准体系特征项ID" align="center" prop="localSysItemId" />
      <el-table-column label="品茗标准体系特征项ID" align="center" prop="pmSysItemId" />
      <el-table-column label="填充值" align="center" prop="ctagValue" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ctag:detail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ctag:detail:remove']"
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

    <!-- 添加或修改特征项匹配详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工程ID" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入工程ID" />
        </el-form-item>
        <el-form-item label="所属的本地标准体系ID(冗余)" prop="localSysId">
          <el-input v-model="form.localSysId" placeholder="请输入所属的本地标准体系ID(冗余)" />
        </el-form-item>
        <el-form-item label="本地标准体系特征项ID" prop="localSysItemId">
          <el-input v-model="form.localSysItemId" placeholder="请输入本地标准体系特征项ID" />
        </el-form-item>
        <el-form-item label="品茗标准体系特征项ID" prop="pmSysItemId">
          <el-input v-model="form.pmSysItemId" placeholder="请输入品茗标准体系特征项ID" />
        </el-form-item>
        <el-form-item label="填充值" prop="ctagValue">
          <el-input v-model="form.ctagValue" placeholder="请输入填充值" />
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
import { listDetail, getDetail, delDetail, addDetail, updateDetail, exportDetail } from "@/api/ctag/detail";

export default {
  name: "Detail",
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
      // 特征项匹配详情表格数据
      detailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: undefined,
        localSysId: undefined,
        localSysItemId: undefined,
        pmSysItemId: undefined,
        ctagValue: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectId: [
          { required: true, message: "工程ID不能为空", trigger: "blur" }
        ],
        localSysItemId: [
          { required: true, message: "本地标准体系特征项ID不能为空", trigger: "blur" }
        ],
        pmSysItemId: [
          { required: true, message: "品茗标准体系特征项ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询特征项匹配详情列表 */
    getList() {
      this.loading = true;
      listDetail(this.queryParams).then(response => {
        this.detailList = response.rows;
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
        projectId: undefined,
        localSysId: undefined,
        localSysItemId: undefined,
        pmSysItemId: undefined,
        ctagValue: undefined,
        remark: undefined
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
      this.title = "添加特征项匹配详情";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDetail(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改特征项匹配详情";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateDetail(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addDetail(this.form).then(response => {
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
      this.$confirm('是否确认删除特征项匹配详情编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDetail(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有特征项匹配详情数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDetail(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
