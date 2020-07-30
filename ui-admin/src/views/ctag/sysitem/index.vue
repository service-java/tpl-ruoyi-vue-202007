<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="特征项名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入特征项名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父级ID" prop="pid">
        <el-input
          v-model="queryParams.pid"
          placeholder="请输入父级ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="层级" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入层级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="特征项类型(1: 字符串;2: 数值;3:枚举)" prop="itemType">
        <el-select v-model="queryParams.itemType" placeholder="请选择特征项类型(1: 字符串;2: 数值;3:枚举)" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="特征项备注" prop="itemRemark">
        <el-input
          v-model="queryParams.itemRemark"
          placeholder="请输入特征项备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="字符串类型的长度限制" prop="itemStrLengthLimit">
        <el-input
          v-model="queryParams.itemStrLengthLimit"
          placeholder="请输入字符串类型的长度限制"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否允许有小数(0: 否;1: 是)" prop="itemNumAllowDecimal">
        <el-input
          v-model="queryParams.itemNumAllowDecimal"
          placeholder="请输入是否允许有小数(0: 否;1: 是)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="枚举型是否允许多选" prop="itemEnumAllowMulti">
        <el-input
          v-model="queryParams.itemEnumAllowMulti"
          placeholder="请输入枚举型是否允许多选"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属的体系" prop="sysId">
        <el-input
          v-model="queryParams.sysId"
          placeholder="请输入所属的体系"
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
          v-hasPermi="['ctag:sysitem:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ctag:sysitem:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ctag:sysitem:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ctag:sysitem:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="sysitemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="特征项名称" align="center" prop="name" />
      <el-table-column label="父级ID" align="center" prop="pid" />
      <el-table-column label="层级" align="center" prop="level" />
      <el-table-column label="特征项类型(1: 字符串;2: 数值;3:枚举)" align="center" prop="itemType" />
      <el-table-column label="特征项备注" align="center" prop="itemRemark" />
      <el-table-column label="字符串类型的长度限制" align="center" prop="itemStrLengthLimit" />
      <el-table-column label="是否允许有小数(0: 否;1: 是)" align="center" prop="itemNumAllowDecimal" />
      <el-table-column label="枚举型是否允许多选" align="center" prop="itemEnumAllowMulti" />
      <el-table-column label="所属的体系" align="center" prop="sysId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ctag:sysitem:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ctag:sysitem:remove']"
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

    <!-- 添加或修改特征项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="特征项名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入特征项名称" />
        </el-form-item>
        <el-form-item label="父级ID" prop="pid">
          <el-input v-model="form.pid" placeholder="请输入父级ID" />
        </el-form-item>
        <el-form-item label="层级" prop="level">
          <el-input v-model="form.level" placeholder="请输入层级" />
        </el-form-item>
        <el-form-item label="特征项类型(1: 字符串;2: 数值;3:枚举)">
          <el-select v-model="form.itemType" placeholder="请选择特征项类型(1: 字符串;2: 数值;3:枚举)">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="特征项备注" prop="itemRemark">
          <el-input v-model="form.itemRemark" placeholder="请输入特征项备注" />
        </el-form-item>
        <el-form-item label="字符串类型的长度限制" prop="itemStrLengthLimit">
          <el-input v-model="form.itemStrLengthLimit" placeholder="请输入字符串类型的长度限制" />
        </el-form-item>
        <el-form-item label="是否允许有小数(0: 否;1: 是)" prop="itemNumAllowDecimal">
          <el-input v-model="form.itemNumAllowDecimal" placeholder="请输入是否允许有小数(0: 否;1: 是)" />
        </el-form-item>
        <el-form-item label="枚举型是否允许多选" prop="itemEnumAllowMulti">
          <el-input v-model="form.itemEnumAllowMulti" placeholder="请输入枚举型是否允许多选" />
        </el-form-item>
        <el-form-item label="所属的体系" prop="sysId">
          <el-input v-model="form.sysId" placeholder="请输入所属的体系" />
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
import { listSysitem, getSysitem, delSysitem, addSysitem, updateSysitem, exportSysitem } from "@/api/ctag/sysitem";

export default {
  name: "Sysitem",
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
      // 特征项表格数据
      sysitemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        pid: undefined,
        level: undefined,
        itemType: undefined,
        itemRemark: undefined,
        itemStrLengthLimit: undefined,
        itemNumAllowDecimal: undefined,
        itemEnumAllowMulti: undefined,
        sysId: undefined
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
    /** 查询特征项列表 */
    getList() {
      this.loading = true;
      listSysitem(this.queryParams).then(response => {
        this.sysitemList = response.rows;
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
        name: undefined,
        pid: undefined,
        level: undefined,
        itemType: undefined,
        itemRemark: undefined,
        itemStrLengthLimit: undefined,
        itemNumAllowDecimal: undefined,
        itemEnumAllowMulti: undefined,
        sysId: undefined
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
      this.title = "添加特征项";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSysitem(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改特征项";
      });
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateSysitem(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addSysitem(this.form).then(response => {
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
      this.$confirm('是否确认删除特征项编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSysitem(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有特征项数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSysitem(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
