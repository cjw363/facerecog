webpackJsonp([19],{CjXA:function(e,o){},P7ry:function(e,o,t){"use strict";Object.defineProperty(o,"__esModule",{value:!0});var r={name:"Login",data:function(){var e=function(e,o,t){/^[0-9a-zA-Z]{4,12}$/.test(o)?t():t(new Error("需要4个字符以上，并且只能是数字或者字母组成"))};return{loginModel:{name:"",password:""},loginRules:{name:[{required:!0,message:"请输入账号",trigger:"blur"},{validator:e,trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{validator:e,trigger:"blur"}]},dropdownTitle:"中文"}},methods:{onClickLogin:function(e){var o=this;this.$refs[e].validate(function(t){var r=o.$refs[e].model;t&&o.$post("/user/login",{name:r.name,password:r.password}).then(function(e){o.$store.commit("user/setUser",e.data),o.$router.push("/web")})})},handleCommand:function(e){}}},n={render:function(){var e=this,o=e.$createElement,t=e._self._c||o;return t("el-container",[t("el-header",[e._v("我的开发练习分享")]),e._v(" "),t("el-main",[t("el-form",{ref:"loginForm",staticClass:"demo-ruleForm login-container",attrs:{model:e.loginModel,rules:e.loginRules}},[t("p",{staticClass:"title"},[e._v("个人登录")]),e._v(" "),t("el-form-item",{attrs:{prop:"name"}},[t("el-input",{attrs:{type:"text",autocomplete:"off",placeholder:"请输入账号",clearable:""},model:{value:e.loginModel.name,callback:function(o){e.$set(e.loginModel,"name",o)},expression:"loginModel.name"}})],1),e._v(" "),t("el-form-item",{attrs:{prop:"password"}},[t("el-input",{attrs:{type:"password",autocomplete:"off",placeholder:"请输入密码",clearable:""},nativeOn:{keyup:function(o){return!o.type.indexOf("key")&&e._k(o.keyCode,"enter",13,o.key,"Enter")?null:e.onClickLogin("loginForm")}},model:{value:e.loginModel.password,callback:function(o){e.$set(e.loginModel,"password",o)},expression:"loginModel.password"}})],1),e._v(" "),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(o){return e.onClickLogin("loginForm")}}},[e._v("登录\n          ")])],1)],1)],1),e._v(" "),t("el-footer",[e._v("许可证编号："),t("a",{attrs:{href:"http://beian.miitbeian.gov.cn"}},[e._v("粤ICP备19097013号")])])],1)},staticRenderFns:[]};var l=t("VU/8")(r,n,!1,function(e){t("CjXA")},"data-v-479117e1",null);o.default=l.exports}});