webpackJsonp([12],{O89M:function(e,t){},WPK8:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n={name:"GroupPersonList",components:{DialogGroupAddPerson:a("OH6f").default},props:{group:{}},data:function(){return{tableData1:[],currentPage1:1,pageSizes1:[5,10,20],pageSize1:10,total1:0}},methods:{openDialogAddPerson:function(){this.$refs.refDialogGroupAddPerson.openDialogGroupAddPerson()},deleteGroupPerson:function(e){var t=this;this.$post("/group/delete_person",{group_id:this.group.group_id,person_id:e.person_id}).then(function(a){t.$message.success(a.message),t.$utils.arrayRemoveObj(t.tableData1,e),t.total1--,t.updateGroupGrantData()})},get:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{pageNum:this.currentPage1,pageSize:this.pageSize1,group_id:this.group.group_id};this.$get("/person/list_by_group",t).then(function(t){e.tableData1=t.data.list,e.total1=t.data.total})},handleChange1:function(){this.get()},updateGroupGrantData:function(){this.$emit("updateGroupGrantData")}},created:function(){this.get()},watch:{group:function(){this.get()}}},r={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-form",[a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-plus",size:"small"},on:{click:e.openDialogAddPerson}},[e._v("添加")])],1)],1),e._v(" "),[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData1,stripe:""}},[a("el-table-column",{attrs:{prop:"person_id",label:"人员ID"}}),e._v(" "),a("el-table-column",{attrs:{prop:"person_name",label:"姓名"}}),e._v(" "),a("el-table-column",{attrs:{prop:"emp_number",label:"工号"}}),e._v(" "),a("el-table-column",{attrs:{label:"进组时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{staticClass:"el-icon-time"}),e._v(" "),a("span",{staticStyle:{"margin-left":"10px"}},[e._v(e._s(e._f("formatDate")(t.row.add_group_time)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){return e.deleteGroupPerson(t.row)}}},[e._v("移除")])]}}])})],1)],e._v(" "),[a("div",{staticClass:"block"},[a("el-pagination",{attrs:{"current-page":e.currentPage1,"page-size":e.pageSize1,"page-sizes":e.pageSizes1,"prev-text":"上一页","next-text":"下一页",layout:"total, sizes, prev, pager, next, jumper",total:e.total1},on:{"size-change":e.handleChange1,"current-change":e.handleChange1,"update:currentPage":function(t){e.currentPage1=t},"update:current-page":function(t){e.currentPage1=t},"update:pageSize":function(t){e.pageSize1=t},"update:page-size":function(t){e.pageSize1=t}}})],1)],e._v(" "),a("DialogGroupAddPerson",{ref:"refDialogGroupAddPerson",attrs:{group:e.group}})],2)},staticRenderFns:[]};var o=a("VU/8")(n,r,!1,function(e){a("O89M")},"data-v-8951b6bc",null);t.default=o.exports}});