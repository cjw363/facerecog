webpackJsonp([9],{"5qkj":function(e,t){},"9oQm":function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=i("yyct"),s={name:"DeviceInfo",inject:["reloadDevice"],props:{device:{type:Object,default:function(){return{}},device_sn:"",device_id:"",online:"",arcface_appid:"",register_time:"",activate_time:"",last_online_time:"",last_offline_time:"",sync_success:"",sync_fail:"",without_sync:"",app_version:""}},methods:{checkUpdate:function(){this.$post("/device/check_update",{device_sn:this.device.device_sn}).then(function(e){105===e.code?a.a.info(e.message):a.a.info("未发现新版本")})},deleteDevice:function(){var e=this;a.a.confirm("注意: 删除设备会删除该设备信息以及相关权限数据,确定要删除该设备吗").then(function(){e.$post("/device/delete",{device_id:e.device.device_id}).then(function(t){a.a.info(t.message),e.reloadDevice(),e.$router.push("/device/device_tbl")})})}}},n={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-form",{attrs:{"label-width":"130px"}},[i("el-form-item",{attrs:{label:"设备sn编号"}},[i("span",[e._v(e._s(e.device.device_sn))])]),e._v(" "),i("el-form-item",{attrs:{label:"在线状态"}},[1===e.device.online?i("span",[e._v("在线")]):e._e(),e._v(" "),0===e.device.online?i("span",[e._v("离线")]):e._e()]),e._v(" "),i("el-form-item",{attrs:{label:"授权码"}},[i("span",[e._v(e._s(e.device.arcface_appid))])]),e._v(" "),i("el-form-item",{attrs:{label:"注册时间"}},[i("span",[e._v(e._s(e._f("formatDate")(e.device.register_time)))])]),e._v(" "),i("el-form-item",{attrs:{label:"激活时间"}},[i("span",[e._v(e._s(e._f("formatDate")(e.device.activate_time)))])]),e._v(" "),i("el-form-item",{attrs:{label:"最后一次上线时间"}},[i("span",[e._v(e._s(e._f("formatDate")(e.device.last_online_time)))])]),e._v(" "),i("el-form-item",{attrs:{label:"最后一次下线时间"}},[i("span",[e._v(e._s(e._f("formatDate")(e.device.last_offline_time)))])]),e._v(" "),i("el-form-item",{attrs:{label:"同步成功"}},[i("span",[e._v(e._s(e.device.sync_success))])]),e._v(" "),i("el-form-item",{attrs:{label:"同步失败"}},[i("span",[e._v(e._s(e.device.sync_fail))])]),e._v(" "),i("el-form-item",{attrs:{label:"未同步"}},[i("span",[e._v(e._s(e.device.without_sync))])]),e._v(" "),i("el-form-item",{attrs:{label:"当前app版本"}},[i("span",[e._v(e._s(e.device.app_version))]),e._v(" "),i("el-button",{attrs:{size:"small",type:"success"},on:{click:e.checkUpdate}},[e._v("更新")])],1),e._v(" "),i("el-form-item",{attrs:{label:"操作"}},[i("el-button",{attrs:{size:"small",type:"danger"},on:{click:e.deleteDevice}},[e._v("删除")])],1)],1)},staticRenderFns:[]};var c=i("VU/8")(s,n,!1,function(e){i("5qkj")},"data-v-d0af72e0",null);t.default=c.exports}});