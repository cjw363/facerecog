webpackJsonp([8],{ZfNX:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var c=a("yyct"),i={name:"DeviceInactDetail",inject:["reloadDevice"],data:function(){return{device:"",model:{arcface_appid:"",arcface_sdkkey:"",arcface_activekey:"",baidu_tts_appid:"",baidu_tts_appkey:"",baidu_tts_secretkey:""},rule:{arcface_appid:[{required:!0,message:"请填入授权码",trigger:"blur"}],arcface_sdkkey:[{required:!0,message:"请填入授权码",trigger:"blur"}]}}},methods:{get:function(){var e=this;this.$post("/device/inact_detail",{device_sn:this.$route.query.device_sn}).then(function(t){e.device=t.data,e.model.arcface_appid=e.device.arcface_appid,e.model.arcface_sdkkey=e.device.arcface_sdkkey})},onClick:function(){var e=this;this.$refs.form.validate(function(t){t&&e.$post("/device/activate",{device_sn:e.$route.query.device_sn,arcface_appid:e.model.arcface_appid,arcface_sdkkey:e.model.arcface_sdkkey,arcface_activekey:e.model.arcface_activekey,baidu_tts_appid:e.model.baidu_tts_appid,baidu_tts_appkey:e.model.baidu_tts_appkey,baidu_tts_secretkey:e.model.baidu_tts_secretkey}).then(function(t){c.a.success(t.message),e.reloadDevice()})})}},created:function(){this.get()},watch:{$route:"get"}},l={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-breadcrumb",{attrs:{"separator-class":"el-icon-arrow-right"}},[a("el-breadcrumb-item",[e._v("设备管理")]),e._v(" "),a("el-breadcrumb-item",[e._v("设备详情")]),e._v(" "),a("el-breadcrumb-item",[e._v(e._s(e.device.device_name))])],1),e._v(" "),a("el-form",{ref:"form",attrs:{"label-width":"170px",model:e.model,rules:e.rule}},[a("el-form-item",{attrs:{label:"设备sn编号"}},[a("span",[e._v(e._s(e.device.device_sn))])]),e._v(" "),a("el-form-item",{attrs:{label:"ARCFACE_APPID",prop:"arcface_appid"}},[a("el-input",{attrs:{type:"text",autocomplete:"off",size:"small"},model:{value:e.model.arcface_appid,callback:function(t){e.$set(e.model,"arcface_appid",t)},expression:"model.arcface_appid"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"ARCFACE_SDKKEY",prop:"arcface_sdkkey"}},[a("el-input",{attrs:{type:"text",autocomplete:"off",size:"small"},model:{value:e.model.arcface_sdkkey,callback:function(t){e.$set(e.model,"arcface_sdkkey",t)},expression:"model.arcface_sdkkey"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"ARCFACE_ACTIVEKEY"}},[a("el-input",{attrs:{type:"text",autocomplete:"off",size:"small"},model:{value:e.model.arcface_activekey,callback:function(t){e.$set(e.model,"arcface_activekey",t)},expression:"model.arcface_activekey"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"BAIDU_TTS_APPID"}},[a("el-input",{attrs:{type:"text",autocomplete:"off",size:"small"},model:{value:e.model.baidu_tts_appid,callback:function(t){e.$set(e.model,"baidu_tts_appid",t)},expression:"model.baidu_tts_appid"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"BAIDU_TTS_APPKEY"}},[a("el-input",{attrs:{type:"text",autocomplete:"off",size:"small"},model:{value:e.model.baidu_tts_appkey,callback:function(t){e.$set(e.model,"baidu_tts_appkey",t)},expression:"model.baidu_tts_appkey"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"BAIDU_TTS_SECRETKEY"}},[a("el-input",{attrs:{type:"text",autocomplete:"off",size:"small"},model:{value:e.model.baidu_tts_secretkey,callback:function(t){e.$set(e.model,"baidu_tts_secretkey",t)},expression:"model.baidu_tts_secretkey"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"注册时间"}},[a("span",[e._v(e._s(e._f("formatDate")(e.device.register_time)))])]),e._v(" "),a("el-form-item",{attrs:{label:"在线状态"}},[1===e.device.online?a("span",[e._v("在线")]):e._e(),e._v(" "),0===e.device.online?a("span",[e._v("离线")]):e._e()]),e._v(" "),a("el-form-item",{attrs:{label:"激活状态"}},[1===e.device.status?a("span",[e._v("已激活")]):e._e(),e._v(" "),0===e.device.status?a("div",[a("span",[e._v("未激活")]),e._v(" "),a("el-button",{attrs:{type:"primary",round:"",id:"btn_active",size:"small"},on:{click:e.onClick}},[e._v("激活")])],1):e._e()])],1)],1)},staticRenderFns:[]};var s=a("VU/8")(i,l,!1,function(e){a("nm94")},"data-v-1a860b98",null);t.default=s.exports},nm94:function(e,t){}});