(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0d034af2"],{"5ced":function(t,e,o){},b0c0:function(t,e,o){var i=o("83ab"),a=o("9bf2").f,n=Function.prototype,r=n.toString,l=/^\s*function ([^ (]*)/,s="name";!i||s in n||a(n,s,{configurable:!0,get:function(){try{return r.call(this).match(l)[1]}catch(t){return""}}})},bb51:function(t,e,o){"use strict";o.r(e);var i=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{attrs:{id:"home"}},[o("div",{staticClass:"icon",staticStyle:{display:"flex"}},[o("el-aside",{attrs:{width:"260px"}},[o("span",{staticStyle:{"margin-left":"11px"},on:{click:function(e){t.dialogFormVisible=!0}}},[o("i",{staticClass:"el-icon-circle-plus-outline"}),o("el-button",{attrs:{type:"text"}},[t._v("目录")])],1),o("span",{staticStyle:{"margin-left":"11px"},on:{click:function(e){t.dialogFormVisible1=!0}}},[o("i",{staticClass:"el-icon-circle-plus-outline"}),o("el-button",{attrs:{type:"text"}},[t._v("笔记")])],1)]),o("el-header",{staticStyle:{"text-align":"right","font-size":"12px","flex-grow":"1"}},[o("h2",[t._v("EbbingNote")]),o("el-dropdown",{attrs:{trigger:"click"}},[o("i",{staticClass:"el-icon-setting",staticStyle:{"margin-right":"15px"}}),o("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[o("el-dropdown-item",{nativeOn:{click:function(e){return t.logout(e)}}},[t._v("退出登录")])],1)],1),o("span",{staticStyle:{"font-size":"18px"}},[t._v(t._s(t.userAccountName))])],1)],1),o("el-dialog",{attrs:{title:"新建目录",visible:t.dialogFormVisible,width:"30%"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[o("el-form",{attrs:{model:t.categoryForm}},[o("el-form-item",{attrs:{label:"名称","label-width":t.formLabelWidth}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:t.categoryForm.name,callback:function(e){t.$set(t.categoryForm,"name",e)},expression:"categoryForm.name"}})],1)],1),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取 消")]),o("el-button",{attrs:{type:"primary"},on:{click:t.confirmCategory}},[t._v("确 定")])],1)],1),o("el-dialog",{attrs:{title:"新建笔记",visible:t.dialogFormVisible1,top:"10vh"},on:{"update:visible":function(e){t.dialogFormVisible1=e}}},[o("el-form",{attrs:{model:t.noteForm}},[o("el-form-item",{attrs:{label:"目录","label-width":t.formLabelWidth}},[o("el-select",{attrs:{placeholder:"请选择目录"},model:{value:t.noteForm.categoryId,callback:function(e){t.$set(t.noteForm,"categoryId",e)},expression:"noteForm.categoryId"}},[t._l(t.categories,(function(e){return[-1!=e.id?o("el-option",{key:e.id,attrs:{label:e.categoryName,value:e.id}}):t._e()]}))],2)],1),o("el-form-item",{attrs:{label:"名称","label-width":t.formLabelWidth}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:t.noteForm.noteName,callback:function(e){t.$set(t.noteForm,"noteName",e)},expression:"noteForm.noteName"}})],1),o("el-form-item",{attrs:{label:"内容","label-width":t.formLabelWidth}},[o("el-input",{attrs:{autocomplete:"off",type:"textarea",rows:10,placeholder:"请输入内容"},model:{value:t.noteForm.noteContent,callback:function(e){t.$set(t.noteForm,"noteContent",e)},expression:"noteForm.noteContent"}})],1)],1),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(e){t.dialogFormVisible1=!1}}},[t._v("取 消")]),o("el-button",{attrs:{type:"primary"},on:{click:t.confirmNote}},[t._v("确 定")])],1)],1),o("el-dialog",{attrs:{title:"修改笔记",visible:t.dialogFormVisible2,top:"10vh"},on:{"update:visible":function(e){t.dialogFormVisible2=e}}},[o("el-form",{attrs:{model:t.modifyNoteForm}},[o("el-form-item",{attrs:{label:"目录","label-width":t.formLabelWidth}},[o("el-select",{attrs:{placeholder:"请选择目录"},model:{value:t.modifyNoteForm.categoryId,callback:function(e){t.$set(t.modifyNoteForm,"categoryId",e)},expression:"modifyNoteForm.categoryId"}},[t._l(t.categories,(function(e){return[-1!=e.id?o("el-option",{key:e.id,attrs:{label:e.categoryName,value:e.id}}):t._e()]}))],2)],1),o("el-form-item",{attrs:{label:"名称","label-width":t.formLabelWidth}},[o("el-input",{attrs:{autocomplete:"off"},model:{value:t.modifyNoteForm.title,callback:function(e){t.$set(t.modifyNoteForm,"title",e)},expression:"modifyNoteForm.title"}})],1),o("el-form-item",{attrs:{label:"内容","label-width":t.formLabelWidth}},[o("el-input",{attrs:{autocomplete:"off",type:"textarea",rows:10,placeholder:"请输入内容"},model:{value:t.modifyNoteForm.content,callback:function(e){t.$set(t.modifyNoteForm,"content",e)},expression:"modifyNoteForm.content"}})],1)],1),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(e){t.dialogFormVisible2=!1}}},[t._v("取 消")]),o("el-button",{attrs:{type:"primary"},on:{click:t.confirmModifyNote}},[t._v("确 定")])],1)],1),o("el-container",{staticClass:"container"},[o("el-aside",{staticStyle:{"background-color":"rgb(238, 241, 246)"},attrs:{width:"260px"}},[o("el-menu",t._l(t.categories,(function(e){return o("el-submenu",{key:e.id,attrs:{index:e.id.toString()},nativeOn:{contextmenu:function(o){return o.preventDefault(),t.deleteCategory(e)}}},[o("template",{slot:"title"},[o("i",{staticClass:"el-icon-folder"}),t._v(t._s(e.categoryName)+" ")]),o("el-menu-item-group",t._l(e.notes,(function(e){return o("el-menu-item",{key:e.id,nativeOn:{contextmenu:function(o){return o.preventDefault(),t.deleteNote(e.id)},click:function(o){return t.detail(e)}}},[o("template",{staticStyle:{position:"relative"},slot:"title"},[o("i",{class:e.icon}),t._v(" "+t._s(e.title)+" "),o("span",{staticClass:"noteTime"},[t._v(t._s(e.createdDate))])])],2)})),1)],2)})),1)],1),o("el-container",{staticStyle:{width:"650px",border:"1px solid #eee"},nativeOn:{dblclick:function(e){return t.modifyNote()}}},[o("el-main",{staticStyle:{"white-space":"pre"}},[t._v(" "+t._s(null==t.currentNote?"":t.currentNote.content)+" ")])],1)],1)],1)},a=[],n=(o("b0c0"),o("bc3a")),r=o.n(n),l={data:function(){return{userAccountName:"",userAccountId:this.$route.params.id,categories:[],currentNote:{},dialogFormVisible:!1,dialogFormVisible1:!1,dialogFormVisible2:!1,formLabelWidth:"70px",categoryForm:{name:""},noteForm:{categoryId:"",noteName:"",noteContent:""},modifyNoteForm:{id:"",categoryId:"",title:"",content:""}}},mounted:function(){var t=this,e="useraccount/getCurrentUser",o={data:this.userAccountId};r.a.post(e,o).then((function(e){e&&e.data&&e.data.data&&(t.userAccountName=e.data.data.userName)})),this.listCategory()},methods:{listCategory:function(){var t=this,e="category/list",o={data:this.userAccountId};r.a.post(e,o).then((function(e){var o=e.data;if(o&&null!=o.data){for(var i=[],a=o.data,n=0;n<a.length;n++){var r=new Array,l=a[n].documentList;if(null!=l&&l.length>0)for(var s=0;s<l.length;s++){var c={id:l[s].id,title:l[s].title,status:l[s].status,icon:"el-icon-document",createdDate:new Date(l[s].createdDate).toLocaleDateString()};c.title.length>10&&(c.title=c.title.substr(0,11)+" . . ."),r.push(c)}var d={id:a[n].id,categoryName:a[n].categoryName,notes:r};i.push(d)}t.categories=i}})).catch((function(t){console.log(t)}))},confirmCategory:function(){var t=this,e="category/add",o={userAccountId:this.userAccountId,categoryName:this.categoryForm.name};r.a.post(e,o).then((function(e){e.data.data?(t.$notify({message:"创建成功",type:"success"}),t.listCategory(),t.dialogFormVisible=!1):t.$notify.error({message:"创建失败"})}))},confirmNote:function(){var t=this,e="document/add",o={categoryId:this.noteForm.categoryId,title:this.noteForm.noteName,content:this.noteForm.noteContent};r.a.post(e,o).then((function(e){e.data.data?(t.$notify({message:"创建成功",type:"success"}),t.listCategory(),t.detail(e.data.data),t.dialogFormVisible1=!1):t.$notify.error({message:"创建失败"})}))},detail:function(t){for(var e=this,o=0;o<this.categories.length;o++)for(var i=0;i<this.categories[o].notes.length;i++)"el-icon-document"!=this.categories[o].notes[i].icon&&(this.categories[o].notes[i].icon="el-icon-document");var a="document/detail",n={data:t.id};r.a.post(a,n).then((function(o){e.currentNote=o.data.data,t.icon="el-icon-edit"}))},deleteCategory:function(t){var e=this;if(!(t.notes&&t.notes.length>0)){var o="category/delete",i={id:t.id};this.$confirm("确认删除吗?","提示",{}).then((function(){r.a.post(o,i).then((function(t){t&&t.data&&t.data.data&&(e.listCategory(),e.$notify({message:"删除成功",type:"success"}))}))}))}},deleteNote:function(t){var e=this,o="document/delete",i={id:t};this.$confirm("确认删除吗?","提示",{}).then((function(){r.a.post(o,i).then((function(o){o&&o.data&&o.data.data&&(e.currentNote.id===t&&(e.content=null),e.listCategory(),e.$notify({message:"删除成功",type:"success"}))}))}))},modifyNote:function(){this.currentNote&&this.currentNote.id&&(this.modifyNoteForm=this.currentNote,this.dialogFormVisible2=!0)},confirmModifyNote:function(){var t=this,e="document/modify";r.a.post(e,this.modifyNoteForm).then((function(e){e.data.data?(t.$notify({message:"修改成功",type:"success"}),t.listCategory(),t.detail(e.data.data),t.dialogFormVisible2=!1):t.$notify.error({message:"修改失败"})}))},logout:function(){var t=this;this.$confirm("确认退出吗?","提示",{}).then((function(){sessionStorage.removeItem("user"),t.$router.push("/")})).catch((function(){}))}}},s=l,c=(o("cccb"),o("2877")),d=Object(c["a"])(s,i,a,!1,null,null,null);e["default"]=d.exports},cccb:function(t,e,o){"use strict";var i=o("5ced"),a=o.n(i);a.a}}]);
//# sourceMappingURL=chunk-0d034af2.458dd278.js.map