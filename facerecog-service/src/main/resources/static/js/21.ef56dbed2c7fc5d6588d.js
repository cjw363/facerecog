webpackJsonp([21],{"1aYA":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={name:"GroupTbl",data:function(){return{tableData:[],searching:!0,currentPage:1,pageSizes:[5,10,20],pageSize:10,total:0,keyword:""}},methods:{get:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{pageNum:this.currentPage,pageSize:this.pageSize,keyword:this.keyword};this.$get("/group/list",t).then(function(t){e.tableData=t.data.list,e.total=t.data.total})},handleChange:function(e){this.get()},searchGroupList:function(){this.get({pageNum:1,pageSize:this.pageSize,keyword:this.keyword})}},created:function(){this.get()}},n={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"box"},[a("el-breadcrumb",{staticStyle:{"margin-bottom":"15px"},attrs:{"separator-class":"el-icon-arrow-right"}},[a("el-breadcrumb-item",[e._v("组别管理")]),e._v(" "),a("el-breadcrumb-item",[e._v("组别列表")])],1),e._v(" "),a("el-form",{attrs:{onsubmit:"return false;"}},[a("el-form-item",[a("el-row",[a("div",{staticStyle:{float:"right"}},[a("el-input",{staticStyle:{width:"200px"},attrs:{size:"small",placeholder:"请输入搜索内容"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.searchGroupList(t)}},model:{value:e.keyword,callback:function(t){e.keyword=t},expression:"keyword"}}),e._v(" "),a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"small"},on:{click:e.searchGroupList}})],1)])],1)],1),e._v(" "),[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,stripe:""}},[a("el-table-column",{attrs:{prop:"group_id",label:"组ID"}}),e._v(" "),a("el-table-column",{attrs:{prop:"group_name",label:"组名"}}),e._v(" "),a("el-table-column",{attrs:{label:"创建时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("i",{staticClass:"el-icon-time"}),e._v(" "),a("span",{staticStyle:{"margin-left":"10px"}},[e._v(e._s(e._f("formatDate")(t.row.create_time)))])]}}])})],1)],e._v(" "),[a("div",{staticClass:"block"},[a("el-pagination",{ref:"pagination",attrs:{"current-page":e.currentPage,"page-size":e.pageSize,"page-sizes":e.pageSizes,"prev-text":"上一页","next-text":"下一页",layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleChange,"current-change":e.handleChange,"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t}}})],1)]],2)},staticRenderFns:[]};var i=a("VU/8")(r,n,!1,function(e){a("Pemv")},"data-v-3f6bd9d1",null);t.default=i.exports},Pemv:function(e,t){}});