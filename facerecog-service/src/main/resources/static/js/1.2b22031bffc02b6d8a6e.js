webpackJsonp([1],{"0jR8":function(e,t){},ZYSA:function(e,t){},w0Nl:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a={entity:null,childs:[{entity:{id:1,parentMenuId:0,name:"Device-Manage",icon:"el-icon-mobile-phone",alias:"设备管理",state:"ENABLE",value:"/device/device",type:"LINK"},childs:null},{entity:{id:2,parentMenuId:0,name:"Person-Manage",icon:"el-icon-service",alias:"人员管理",state:"ENABLE",value:null,type:"NONE"},childs:[{entity:{id:20,parentMenuId:2,name:"Person-List",icon:"el-icon-phone-outline",alias:"人员列表",state:"ENABLE",value:"person/person",type:"LINK"},childs:null},{entity:{id:21,parentMenuId:2,name:"Batch-Upload",icon:"el-icon-picture",alias:"批量上传",state:"ENABLE",value:"person/person_batch_upload",type:"LINK"},childs:null}]},{entity:{id:3,parentMenuId:0,name:"Grant-Manage",icon:"el-icon-printer",alias:"权限管理",state:"ENABLE",value:"grant/grant",type:"LINK"},childs:null},{entity:{id:4,parentMenuId:0,name:"Group-Manage",icon:"el-icon-menu",alias:"分组管理",state:"ENABLE",value:"group/group",type:"LINK"},childs:null},{entity:{id:5,parentMenuId:0,name:"Record-Manage",icon:"el-icon-document",alias:"记录管理",state:"ENABLE",value:"record/record",type:"LINK"},childs:null},{entity:{id:6,parentMenuId:0,name:"Attend-Manage",icon:"el-icon-bell",alias:"考勤管理",state:"ENABLE",value:"attend/attend",type:"LINK"},childs:null}]},l={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"navMenu"},e._l(e.menuData,function(t){return n("label",[null==t.childs&&t.entity&&(e.showEnableOnly&&"ENABLE"===t.entity.state||!e.showEnableOnly)?n("el-menu-item",{key:t.entity.id,attrs:{data:t,index:t.entity.name,route:t.entity.value}},[n("i",{class:t.entity.icon,style:{fontSize:e.iconSize}}),e._v(" "),n("span",{attrs:{slot:"title"},slot:"title"},[e._v(e._s(t.entity.alias))])]):e._e(),e._v(" "),t.childs&&t.entity&&(e.showEnableOnly&&"ENABLE"===t.entity.state||!e.showEnableOnly)?n("el-submenu",{key:t.entity.id,attrs:{data:t,index:t.entity.name}},[n("template",{slot:"title"},[n("i",{class:t.entity.icon,style:{fontSize:e.iconSize}}),e._v(" "),n("span",[e._v(" "+e._s(t.entity.alias))])]),e._v(" "),n("NavMenu",{attrs:{menuData:t.childs,showEnableOnly:e.showEnableOnly,iconSize:e.iconSize}})],2):e._e()],1)}),0)},staticRenderFns:[]};var i={name:"Web",data:function(){return{menuData:a,props:{menuData:{type:Array}}}},components:{NavMenu:n("VU/8")({name:"NavMenu",props:["menuData","showEnableOnly","textColor","iconColor","iconSize"],data:function(){return{}},methods:{}},l,!1,function(e){n("x8uT")},"data-v-1365cf21",null).exports}},s={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-container",[n("el-header",[n("el-row",{attrs:{gutter:0}},[n("el-col",{attrs:{xs:8,sm:6,md:6,lg:5,xl:5}},[e._v("人脸识别管理系统")]),e._v(" "),n("el-col",{attrs:{xs:4,sm:9,md:9,lg:13,xl:13}},[n("div",{staticStyle:{height:"60px"}})]),e._v(" "),n("el-col",{attrs:{xs:5,sm:4,md:4,lg:3,xl:3}},[n("a",{attrs:{href:"#"}},[e._v("xx , 你好")])]),e._v(" "),n("el-col",{attrs:{xs:4,sm:3,md:3,lg:2,xl:2}},[n("a",{attrs:{href:"#"}},[e._v("修改密码")])]),e._v(" "),n("el-col",{attrs:{xs:3,sm:2,md:2,lg:1,xl:1}},[n("a",{attrs:{href:"#"}},[e._v("注销")])])],1)],1),e._v(" "),n("el-container",[n("el-aside",{attrs:{width:"250px"}},[n("el-menu",{staticClass:"el-menu-vertical-demo",staticStyle:{height:"100%"},attrs:{"default-active":this.$router.path,router:""}},[n("NavMenu",{attrs:{menuData:e.menuData.childs}})],1)],1),e._v(" "),n("el-main",[n("router-view")],1)],1)],1)},staticRenderFns:[]};var r=n("VU/8")(i,s,!1,function(e){n("ZYSA"),n("0jR8")},"data-v-4fa15e63",null);t.default=r.exports},x8uT:function(e,t){}});