
Ext.onReady(function() {
	Ext.QuickTips.init();
	// 表单
	var loginDishForm = new Ext.form.FormPanel( {
		items : [ {
			xtype : "textfield",
			fieldLabel : "用户名",
			name : "username",
			allowBlank : false,
			blankText : "用户名不能为空"
		}, {
			xtype : "textfield",
			inputType : "password",
			fieldLabel : "密码",
			name : "password",
			allowBlank : false,
			blankText : "密码不能为空"
		}],
		buttons : [ {
			text : "登陆",
			handler : doLogin
		}, {
			text : "重置",
			handler : function() {
				loginDishForm.form.reset();
			}
		}
		 ],
		width : 300,
		height : 150,
		labelAlign : "right",
		frame : true,
		buttonAlign : "center",
		style : "padding-top:20px;padding-bottom:20px;",
		border : false
	});
	// 创建窗口
	var loginWin = new Ext.Window( {
		title : "登陆",
		width : 300,
		height : 200,
		autoHeight : true,
		buttonAlign : "center",
		items : [ loginDishForm ]

	});
	loginWin.show();
	//登陆
	function doLogin(){
		// 验证表单
		if(loginDishForm.form.findField("username").validate()
				&&loginDishForm.form.findField("password").validate()){
			
			// 添加菜单
			UserBiz.login(loginDishForm.form.getValues(false), function(data) {
				Ext.Msg.alert("提示", data.msg);
				if(data.success==true){
					loginWin.hide();
					loginDishForm.form.reset();
				}
			});
		}
	}
	})