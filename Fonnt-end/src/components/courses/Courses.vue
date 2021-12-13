<template>
  <div>
    <Topbar></Topbar>
    <div class="courses">
      <div class="handle">
        <!-- 查询 -->
        <div class="menu">
          <el-timeline>
            <el-timeline-item placement="top">
              <el-card>
                <h4>课程查询</h4>
                <el-input
                  placeholder="请输入课程名"
                  class="searchName"
                  v-model="searchName"
                >
                  <!-- <el-button slot="append" type="primary" icon="el-icon-search">搜索</el-button> -->
                </el-input>
                <el-button
                  type="primary"
                  icon="el-icon-search"
                  @click="searchCourse"
                  round
                  >搜索</el-button
                >
              </el-card>
            </el-timeline-item>
            <el-timeline-item placement="top">
              <el-card>
                <el-form
                  :model="courseForm"
                  :rules="rules"
                  ref="courseForm"
                  label-width="80px"
                  class="coursesForm"
                >
                  <el-form-item label="课程名" prop="C_name">
                    <el-input
                      v-model="courseForm.C_name"
                      placeholder="请输入课程名"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="上课时间" prop="time">
                    <el-input
                      v-model="courseForm.time"
                      placeholder="请输入上课时间"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="上课教师" prop="teacher">
                    <el-input
                      v-model="courseForm.teacher"
                      placeholder="请输入上课教师"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="课程id" prop="cid">
                    <el-input
                      v-model="courseForm.cid"
                      placeholder="请输入课程id"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="课程简介" prop="Introduction">
                    <el-input
                      type="textarea"
                      v-model="courseForm.Introduction"
                      placeholder="请填写课程信息"
                      maxlength="50"
                      :autosize="{ minRows: 4, maxRows: 6 }"
                      show-word-limit
                    ></el-input>
                  </el-form-item>

                  <el-form-item>
                    <el-button
                      type="primary"
                      @click="submitCourse('courseForm')"
                      >立即添加</el-button
                    >
                    <el-button @click="resetForm('courseForm')">重置</el-button>
                  </el-form-item>
                </el-form>
              </el-card>
            </el-timeline-item>
            <el-timeline-item placement="top"></el-timeline-item>
          </el-timeline>
        </div>
      </div>
      <div class="formData">
        <!-- v-loading="loading"
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading" -->
        <el-table
          :data="coursesData"
          border
          style="width: 100%"
          min-height="650"
          max-height="650"
        >
          <el-table-column
            prop="className"
            label="课程名"
            width="150"
          ></el-table-column>
          <el-table-column
            prop="teacher"
            label="授课教师"
            width="100"
          ></el-table-column>
          <!-- <el-table-column
          prop="Class"
          label="选修班级"
          width="auto"
        ></el-table-column> -->
          <!-- <el-table-column
          prop="S_Class"
          label="上课教室"
          width="auto"
        ></el-table-column> -->
          <el-table-column
            prop="time"
            label="上课时间"
            width="auto"
          ></el-table-column>
          <el-table-column prop="info" label="课程简介"></el-table-column>
          <el-table-column fixed="right" label="操作" width="250">
            <template slot-scope="scope">
              <el-button
                @click="handleClick(scope.row)"
                type="warning"
                size="small"
                >添加学生</el-button
              >
              <el-button
                @click="handleLookClick(scope.row)"
                type="warning"
                size="small"
                >查看详情</el-button
              >
              <el-tooltip
                content="删除这门课程？"
                placement="top"
                effect="light"
                :hide-after="1000"
                :enterable="false"
              >
                <el-button
                  @click="handleDeleteCourse(scope.row)"
                  type="danger"
                  size="small"
                  icon="el-icon-delete"
                  circle
                  plain
                ></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>

        <!-- 添加学生 -->
        <el-dialog title="课程信息" :visible.sync="dialogFormVisible">
          <el-form
            :model="updateForm"
            class="content"
            :rules="rules"
            ref="updateForm"
          >
            <!-- 学生信息 -->
            <el-form-item label="学生姓名" :lable-width="formLabelWidth">
              <el-input v-model="updateForm.Name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学生学号" :lable-width="formLabelWidth">
              <el-input
                v-model="updateForm.Stu_Id"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item label="学生班级" :lable-width="formLabelWidth">
              <el-input
                v-model="updateForm.Stu_Class"
                autocomplete="off"
              ></el-input>
            </el-form-item>

            <h4>批量上传学生</h4>
            <!-- 文件导入 -->
            <!-- <el-upload
            class="upload-demo"
            ref="upload"
            action="https://hailicy.xyz/clasip/uploadcsv"
            name="csvFile"
            :multiple="false"
            :limit="1"
            :on-exceed="handleExceed"
            :before-upload="beforeAvatarUpload"
            :file-list="fileList"
            :auto-upload="false"
          > -->
            <!-- :http-request="uploadFile" -->
            <!-- <el-button slot="trigger" size="small" type="primary"
              >选取文件</el-button
            >
            <el-button
              style="margin-left: 10px"
              size="small"
              type="success"
              @click="submitUpload"
              >上传到服务器</el-button
            >
            <div slot="tip" class="el-upload__tip">
              <p>只能上传csv文件</p>
            </div>
          </el-upload> -->
            <form
              action="https://hailicy.xyz/clasip/uploadcsv"
              method="post"
              enctype="multipart/form-data"
            >
              <input type="file" name="csvFile" />
              <button type="submit">上传csv</button>
            </form>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="updateStudent('updateForm')"
              >确 定</el-button
            >
            <el-button @click="dialogFormVisible = false">取 消</el-button>
          </div>
        </el-dialog>

        <el-dialog title="学生列表" :visible.sync="dialogStuVisible">
          <el-card>
            <el-table
              :data="stuData"
              border
              style="width: 100%"
              min-height="650"
              max-height="650"
            >
              <el-table-column
                type="index"
                :index="indexMethod"
              ></el-table-column
              ><!--感觉是键值-->
              <el-table-column
                prop="name"
                label="学生姓名"
                width="150"
              ></el-table-column>
              <el-table-column
                prop="sid"
                label="学号"
                width="150"
              ></el-table-column>
              <el-table-column
                prop="major"
                label="选修班级"
                width="200"
              ></el-table-column>
              <el-table-column fixed="right" label="操作" width="auto">
                <template slot-scope="scope">
                  <el-tooltip
                    content="删除这个学生？"
                    placement="top"
                    effect="light"
                    :hide-after="1000"
                    :enterable="false"
                  >
                    <el-button
                      @click="handleDeleteStu(scope.row)"
                      type="danger"
                      size="small"
                      icon="el-icon-delete"
                      circle
                      plain
                    ></el-button>
                  </el-tooltip>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import qs from "qs";
import Topbar from "../Topbar.vue";
export default {
  components: { Topbar },
  name: "course",
  data() {
    return {
      courseForm: {
        C_name: "",
        cid: "",
        teacher: "",
        Introduction: "",
        time: "",
      },
      editCourseDialog: false,
      name: "liudaxia",
      searchName: "",
      coursesData: [
        // {
        //   ID: 12345,
        //   Title: "课程设计",
        //   Teacher: "张三",
        //   Class: "计科1801",
        //   S_Class: "教4",
        //   Time: "时间",
        //   Introduction: "无",
        // }
      ],
      loading: true,
      // 增加课程 表单项设置
      ruleForm: {
        Title: "",
        Number: "",
        Introduction: "",
      },
      // 修改时课程信息 表单项设置
      updateForm: {
        // Id: "",
        // Title: "",
        // Introduction: "",
        Name: "",
        Stu_Id: "",
        Stu_Class: "",
        cid: "",
      },
      currentCid: "",
      // 校验规则
      rules: {
        Title: [
          { required: true, message: "请输入课程名", trigger: "change" },
          { min: 2, max: 10, message: "长度在 2到 10 个字符", trigger: "blur" },
        ],
        Introduction: [
          {
            required: true,
            message: "请填写课程信息,否则无法通过喔~",
            trigger: "change",
          },
        ],
      },
      dialogFormVisible: false,
      fileList: [],
      fakeDate: [],
      formLabelWidth: "6em",
      dialogStuVisible: false,
      // 点击查看详情后上传的信息
      // {
      //   C_ID: "123",//课程id
      //   C_Class: "123"//选修班级名称
      // }
      showStuCourseData: [],
      stuData: [],
    };
  },
  computed: {
    // showData() {
    //   const input = this.searchName;
    //   if (input) {
    //     console.log("input输入的搜索内容：" + this.input);
    //     return this.coursesData.filter((Title) => {
    //       console.log("object:" + Object.keys(Title));
    //       return Object.keys(Title).some((key) => {
    //         return String(Title[key]).toLowerCase().indexOf(input) > -1;
    //       });
    //     });
    //   }
    //   return this.coursesData;
    // },
  },
  methods: {
    //获取接口
    searchCourse() {
      this.getAllcourses();
      let url = "https://hailicy.xyz/clasip/search/" + this.searchName;
      console.log(url);
      this.axios
        .get(url)
        .then((response) => {
          this.coursesData = response.data.data;
          console.log("搜索结果：", this.coursesData);
        })
        .catch((error) => {
          console.log("发生错误的原因:", error);
        });
    },

    //添加课程函数
    submitCourse(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 表单验证成功
          var strData = {
            className: this.courseForm.C_name,
            cid: this.courseForm.cid,
            teacher: this.courseForm.teacher,
            time: this.courseForm.time,
            info: this.courseForm.Introduction,
          };
          // 改变post的编码格式，适应后台
          let url =
            "https://hailicy.xyz/clasip/admin/addClass/" +
            strData.cid +
            "/" +
            strData.className +
            "/" +
            strData.teacher +
            "/" +
            strData.time +
            "/" +
            strData.info;
          this.axios
            .get(url)
            .then((result) => {
              if (result.status == 200 || result.status == 302) {
                this.$message({
                  message: "添加成功!",
                  type: "success",
                });
                this.resetForm("courseForm");
              }
              this.getAllcourses();
            })
            .catch((err) => {
              console.log(err);
              this.$message({
                message: "添加失败",
                type: "danger",
              });
            });
        } else {
          this.$message.error("表单填写不完整，无法提交！");
          // console.log("error submit!!");
          return false;
        }
      });
    },

    handleExceed() {
      this.$notify({
        title: "警告",
        message: "只能上传一个文件哦!,若需要上传其他文件请先移除已选择的文件",
        type: "warning",
      });
    },
    uploadFile(item) {
      //我的项目要求formdata上传数据
      // const form = new FormData();
      // form.append('FireAlarmDeviceSn',this.networkPage.DeviceSn)
      // form.append('file',item.file)

      this.$axios
        .post("https://hailicy.xyz/clasip/uploadcsv", item)
        .then((res) => {
          console.log("res", res);
          if (res.code == 200) {
            this.$message({
              message: "导入数据成功!",
              type: "success",
            });
            this.GetDeviceDetectorList(); //获取联网部件tablelist
          } else {
            this.$message({
              showClose: true,
              message: "导入失败",
              type: "error",
              // duration:'6000'
            });
          }
          this.fileList = [];
          this.$refs.network_importDialog.dialogVisible = false;
        })
        .catch((err) => {
          console.log("err", err);
        });
    },
    //上传之前的判断文件大小和格式
    beforeAvatarUpload(file) {
      const extension = file.name.split(".")[1] === "csv";
      if (!extension) {
        alert("上传模板只能是csv格式!");
      }
    },
    submitUpload() {
      var that = this;
      this.$refs.upload
        .submit()
        .then((res) => {
          console.log("res", res);
          if (res.code == 200) {
            that.$message({
              message: "导入数据成功!",
              type: "success",
            });
            that.GetDeviceDetectorList(); //获取联网部件tablelist
          } else {
            that.$message({
              showClose: true,
              message: "导入失败",
              type: "error",
              // duration:'6000'
            });
          }
        })
        .catch(() => {
          that.$message({
            showClose: true,
            message: "网络错误",
            type: "error",
            // duration:'6000'
          });
        });
    },

    changeTheFold(val) {
      console.log(val);
    },

    // 实现添加功能
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 表单验证成功
          var strData = {
            Title: this.ruleForm.Title,
            Introduction: this.ruleForm.Introduction,
          };
          // 改变post的编码格式，适应后台
          this.axios
            .post("http://localhost:8004/Courses/create", qs.stringify(strData))
            .then((result) => {
              if (result.status == 200 || result.status == 302) {
                this.$message({
                  message: "添加成功(*￣︶￣)，",
                  type: "success",
                });
                this.getAllcourses();
              }
            })
            .catch((err) => {
              this.$message({
                message: "添加失败o(╥﹏╥)o",
                type: "danger",
              });
            });
        } else {
          this.$message.error("阁下填写不完整喔！刘大侠我快马加鞭前来提示！");
          return false;
        }
      });
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //为表格添加序号
    indexMethod(index) {
      return index + 1;
    },

    // 实现删除功能
    handleDeleteCourse(row) {
      console.log(row.cid);
      // console.log(index, row);
      this.$confirm("此操作将删除该课程所有信息, 是否继续?", "提示", {
        cancelButtonText: "取消",
        confirmButtonText: "确定",
        type: "warning",
      })
        .then(() => {
          this.axios
            .get("https://hailicy.xyz/clasip/admin/deleteClassByCid/" + row.cid)
            .then((result) => {
              if (status == 200) {
                this.$message({
                  message: "删除成功",
                  type: "success",
                });
              }
              this.getAllcourses();
            })
            .catch((err) => {
              this.$message({
                message: "删除失败!",
                type: "danger",
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    handleDeleteStu(row) {
      // console.log(index, row);
      console.log("当前课程id" + this.currentCid);
      this.$confirm("此操作将删除该学生, 是否继续?", "提示", {
        cancelButtonText: "取消",
        confirmButtonText: "确定",
        type: "warning",
      })
        .then(() => {
          let url =
            "https://hailicy.xyz/clasip/student/delete/" +
            row.sid +
            "/" +
            this.currentCid;
          console.log(url);
          console.log(row.sid);
          this.axios
            .get(url)
            .then((result) => {
              console.log(status);
              if (result.status == 200 || result.status == 302) {
                this.$message({
                  message: "删除成功",
                  type: "success",
                });
              }
              this.getCourseStudent(this.currentCid);
            })
            .catch((err) => {
              this.$message({
                message: "删除失败!",
                type: "danger",
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    handleClick(row) {
      // 数据回显
      console.log(row.cid);
      this.updateForm.cid = row.cid;
      // this.resetForm(updateForm);
      // this.updateForm.Name = row.Title;
      // this.updateForm.Stu_Class = row.Introduction;
      // this.updateForm.Stu_Id = row.Id;
      this.dialogFormVisible = true;
    },
    //课程详情
    handleLookClick(row) {
      var c_id = row.cid;
      this.currentCid = c_id;
      console.log("course_id:" + c_id);
      this.dialogStuVisible = true;
      this.getCourseStudent(c_id);
    },
    //得到具体学生表单
    getCourseStudent(param) {
      console.log("学生数据" + this.fakeDate[0]);
      this.stuData = [];
      var url =
        "https://hailicy.xyz/clasip/student/getAllStudentByCid/" + param;
      //param为课程ID
      this.axios
        .get(url)
        .then((res) => {
          console.log(res);
          if (res.data.data.length == 0) {
            this.stuData = [];
            this.stuData.push(this.fakeDate[0]);
            console.log("学生数据" + this.fakeDate[0]);
            this.fakeDate = [];
            console.log("数据长度" + this.fakeDate.length);
          } else {
            this.stuData = res.data.data;
            this.stuData.push(this.fakeDate[0]);
            console.log("学生数据" + this.fakeDate[0]);
            this.fakeDate = [];
            console.log("数据长度" + this.fakeDate.length);
          }
          if (status == 200) {
            console.log("fuck");
            this.stuData = res.data.data;

            //console.log(res.data.code);
            this.stuData.forEach((item) => {
              console.log(item.Id);
              if (item.Introduction == null) {
                item.Introduction == "暂无介绍";
              }
              // item.Introduction==null ? "0" : item.Introduction
            });
            //this.loading = false;
          }
        })
        .catch((err) => {
          console.log("你妈妈的吻");
          console.log(err);
          this.stuData = [];
          this.$message({
            message: "获取表单失败！",
            type: "danger",
          });
          this.stuData.push(this.fakeDate[0]);
            console.log("学生数据" + this.fakeDate[0]);
            this.fakeDate = [];
            console.log("数据长度" + this.fakeDate.length);
        });
    },
    // 单独添加学生
    updateStudent(formName) {
      let url =
        "http://hailicy.xyz/clasip/admin/addStudentToClass4/" +
        this.updateForm.Stu_Id +
        "/" +
        this.updateForm.cid;
      // this.$refs[formName].validate((valid) => {
      //   if (valid) {
      //     var updateData = {
      //       Id: this.updateForm.Stu_Id,
      //       Stu_Class: this.updateForm.Stu_Class,
      //       Name: this.updateForm.Name,
      //     };
      //     this.dialogFormVisible = false;
      //     //  改变post的编码格式，适应后台  修改！
      //     this.axios
      //       .get(url)
      //       .then((result) => {
      //         if (result.status == 200 || result.status == 302) {
      //           this.$notify({
      //             id: "",
      //             title: "修改成功",
      //             message: "信息已修改完成！请查看",
      //             type: "success",
      //           });
      //           this.getAllcourses();
      //         }
      //       })
      //       .catch((err) => {
      //         console.log(err);
      //         this.$message({
      //           message: "添加失败o(╥﹏╥)o",
      //           type: "danger",
      //         });
      //       });
      //   } else {
      //     this.$message.error("阁下网络错误！刘大侠我快马加鞭前来提示！");
      //     return false;
      //   }
      // });
      this.fakeDate.push({index: this.stuData.length+1,
      name: this.updateForm.Name,
      sid: this.updateForm.Stu_Id,
      major: this.updateForm.Stu_Class
      });

      console.log("学生数据" + this.fakeDate[0]);
      this.$message({
        message: "添加成功！",
        type: "success"
      })
    },

    // 查验简介是否为空
    checkNull(row, column) {
      // console.log(row)
      // return row.info == null
      //   ? "该课程暂无介绍信息 ￣□￣｜｜"
      //   : row.Introduction;
    },
    // 获取全部的课程数据
    getAllcourses() {
      this.axios
        .get("https://hailicy.xyz/clasip/admin/getAllClass")
        .then((result) => {
          // console.log(result.data.code);
          // console.log(result.status);
          if (result.status == 200) {
            console.log(result.data.data);
            this.coursesData = result.data.data;
            this.coursesData.forEach((item) => {
              console.log(item.Id);
              if (item.Introduction == null) {
                item.Introduction == "无";
              }
              // item.Introduction==null ? "0" : item.Introduction
            });
            // this.loading = false;
          }
        });
    },
  },
  mounted() {
    this.getAllcourses();
  },
};
</script>
<style lang="less" scoped>
.courses {
  display: flex;
  height: 42em;
  .handle {
    flex: 1.5;
    // height: 50em;
    // background-color: lightblue;
    .searchName {
      width: 70%;
    }
    .menu {
      padding: 5% 10% 0 10%;
      h4 {
        margin: 0.2em;
        padding-bottom: 0.5em;
      }
      .stydentForm {
        .el-form-item__content {
          // margin: 0 !important;
          .el-input__suffix {
            right: 0 !important;
          }
          .el-input {
            width: 70%;
          }
        }
      }
    }
  }
  .formData {
    flex: 3;
    text-align: center;
    .el-table {
      text-align: center !important;
      float: right;
      // padding: 5%
      margin: 2%;
    }
    // background-color: lightpink;
  }
}
</style>