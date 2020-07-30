<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="168px">

      <el-form-item label="省别" prop="provinceId">

        <el-select v-model="queryParams.provinceId" :clearable="true">
          <el-option
            v-for="item in provinceList"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否挂靠了特征标准" prop="hasMatch">

        <el-select v-model="queryParams.hasMatch">
          <el-option
            v-for="item in matchList"
            :key="item.value"
            :label="item.title"
            :value="item.value">
          </el-option>
        </el-select>

      </el-form-item>

      <el-form-item prop="sysName">
        <el-input
          v-model="queryParams.sysName"
          placeholder="（支持按照品茗特征体系进行模糊查询）"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ctag:sys:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ctag:sys:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ctag:sys:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ctag:sys:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="sysList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="省别" align="center" prop="provinceName" />
      <el-table-column label="体系名" align="center" prop="sysName" />
      <el-table-column label="体系编码" align="center" prop="sysCode" />
      <el-table-column label="修改时间" align="center" prop="gmtModified" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ctag:sys:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ctag:sys:remove']"
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

    <!-- 添加或修改特征体系对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="省别" prop="provinceId">
          <el-select v-model="form.provinceId">
            <el-option
              v-for="item in provinceList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="体系名" prop="sysName">
          <el-input v-model="form.sysName" placeholder="请输入体系名" />
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
import { listSys, getSys, delSys, addSys, updateSys, exportSys } from "@/api/ctag/sys";
import {listProvince} from "../../../api/ctag/province";
import {MATCH_LIST, SYS_TYPE} from "../../../utils/constant";

export default {
  name: "Sys",
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
      // 特征体系表格数据
      sysList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: SYS_TYPE.PM,
        provinceId: undefined,
        provinceName: undefined,
        sysName: undefined,
        sysCode: undefined,
        isDeleted: undefined,
        gmtCreate: undefined,
        gmtModified: undefined
      },
      matchList: MATCH_LIST,
      provinceList: [],
      // 表单参数
      form: {
        type: SYS_TYPE.PM,
      },
      // 表单校验
      rules: {
        type: [
          { required: true, message: "系统类型(1: 品茗标准体系;2: 本地标准体系)不能为空", trigger: "blur" }
        ],
        provinceId: [
          { required: true, message: "品茗标准体系省别ID/本地标准体系的特征标准不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.fetchProvinceSelect()
  },
  methods: {
    /** 查询特征体系列表 */
    getList() {
      this.loading = true;
      listSys(this.queryParams).then(response => {
        this.sysList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 获取省份列表
    async fetchProvinceSelect()  {
      const resp = await listProvince()
      this.provinceList = resp
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
        type: undefined,
        provinceId: undefined,
        provinceName: undefined,
        sysName: undefined,
        sysCode: undefined,
        isDeleted: undefined,
        gmtCreate: undefined,
        gmtModified: undefined
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
      this.title = "添加特征体系";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSys(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改特征体系";
      });
    },
    /** 提交按钮 */
    submitForm () {
      const that  = this
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (that.form.id != undefined) {
            updateSys(that.form).then(response => {
              if (response.code === 200) {
                that.msgSuccess("修改成功");
                that.open = false;
                that.getList();
              }
            });
          } else {

            that.form.type = SYS_TYPE.PM
            addSys(that.form).then(response => {
              if (response.code === 200) {
                that.msgSuccess("新增成功");
                that.open = false;
                that.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除特征体系编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSys(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有特征体系数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSys(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
