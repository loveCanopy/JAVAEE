
Ext.onReady(function() {

	var pageSize = 50;
	Ext.QuickTips.init();
	/*
	 * 当前周
	 */
	var today=new Date();
	var d = today.getDate();
	var current_week=Math.floor((d/7+1));
	/** ************************************第几周store*********************************** */

	var week_data = [ [ "1", "1" ], [ "2", "2" ], [ "3", "3" ], [ "4", "4" ] ];
	var record = new Ext.data.Record.create( [ {
		name : "dish_week",
		type : "string",
		mapping : 0
	}, {
		name : "show",
		type : "string",
		mapping : 1
	}

	]);
	var store = new Ext.data.Store( {
		proxy : new Ext.data.MemoryProxy(week_data),
		reader : new Ext.data.ArrayReader( {}, record),
		autoLoad : true
	});
	// 第几周combox
		var query_dish_week = new Ext.form.ComboBox( {
			fieldLabel : "选择",
			name : "query_dish_week",
			store : store,// 数据
			displayField : "show",
			valueField : "dish_week",
			width : 50,
			triggerAction : "all",
			value:current_week
		});
		/** ************************************类型store*********************************** */
		var type_data = [ [ "盖饭", "盖饭" ], [ "炒菜", "炒菜" ] ];
		var type_record = new Ext.data.Record.create( [ {
			name : "dish_type",
			type : "string",
			mapping : 0
		}, {
			name : "show",
			type : "string",
			mapping : 1
		}

		]);
		var type_store = new Ext.data.Store( {
			proxy : new Ext.data.MemoryProxy(type_data),
			reader : new Ext.data.ArrayReader( {}, type_record),
			autoLoad : true
		});
		// 类型combox
		var query_dish_type = new Ext.form.ComboBox( {
			name : "query_dish_type",
			store : type_store,// 数据
			displayField : "show",
			valueField : "dish_type",
			width : 80,
			triggerAction : "all"
		});

		/** ************************************添加菜单*********************************** */
		var add_dish_week = new Ext.form.ComboBox( {
			fieldLabel : "第几周",
			name : "dish_week",
			store : store,// 数据
			displayField : "show",
			valueField : "dish_week",
			width : 80,
			triggerAction : "all",
			regex : /^[1-4]{1}$/,
			allowBlank : false,
			blankText : "不能为空"
		});
		var add_dish_type = new Ext.form.ComboBox( {
			fieldLabel : "类型",
			name : "dish_type",
			store : type_store,// 数据
			displayField : "show",
			valueField : "dish_type",
			width : 80,
			triggerAction : "all",
			allowBlank : false,
			blankText : "类型不能为空"
		});
		// 表单
		var addDishForm = new Ext.form.FormPanel( {
			items : [ {
				xtype : "textfield",
				fieldLabel : "名称",
				name : "dish_name",
				allowBlank : false,
				blankText : "名称不能为空"
			}, {
				xtype : "textfield",
				fieldLabel : "价格",
				name : "dish_price",
				regex : /^\+?[1-9][0-9]*$/,
				regexText : "价格添加错误",
				allowBlank : false,
				blankText : "价格不能为空"
			},  add_dish_type,add_dish_week ],
			buttons : [ {
				text : "添加",
				handler : doAdd
			}, {
				text : "取消",
				handler : function() {
				addWin.hide();
				}
			}
			 ],
			width : 350,
			height : 200,
			labelAlign : "right",
			frame : true,
			buttonAlign : "center",
			style : "padding-top:20px;padding-bottom:20px;",
			border : false
		});
		// 创建窗口
		var addWin = new Ext.Window( {
			title : "添加菜单",
			width : 350,
			height : 200,
			autoHeight : true,
			buttonAlign : "center",
			items : [ addDishForm ],
			closeAction : "hide"

		});
		/** ************************************编辑菜单*********************************** */
		var update_dish_week = new Ext.form.ComboBox( {
			fieldLabel : "第几周",
			name : "dish_week",
			store : store,// 数据
			displayField : "show",
			valueField : "dish_week",
			width : 80,
			triggerAction : "all",
			regex : /^[1-4]{1}$/,
			allowBlank : false,
			blankText : "不能为空"
		});
		var update_dish_type = new Ext.form.ComboBox( {
			fieldLabel : "类型",
			name : "dish_type",
			store : type_store,// 数据
			displayField : "show",
			valueField : "dish_type",
			width : 80,
			triggerAction : "all",
			allowBlank : false,
			blankText : "类型不能为空"
		});
		// 表单
		var updateDishForm = new Ext.form.FormPanel( {
			items : [ 
			{
				xtype:"hidden",
				name : 'id'
			},
			{
				xtype : "textfield",
				fieldLabel : "名称",
				name : "dish_name",
				allowBlank : false,
				blankText : "名称不能为空"
			}, {
				xtype : "textfield",
				fieldLabel : "价格",
				name : "dish_price",
				regex : /^\+?[1-9][0-9]*$/,
				regexText : "价格添加错误",
				allowBlank : false,
				blankText : "价格不能为空"
			},update_dish_type, update_dish_week ],
			buttons : [ {
				text : "修改",
				handler : doUpdate
			}, {
				text : "取消",
				handler : function() {
				updateWin.hide();
				}
			}
			 ],
			width : 350,
			height : 200,
			labelAlign : "right",
			frame : true,
			buttonAlign : "center",
			style : "padding-top:20px;padding-bottom:20px;",
			border : false
		});
		// 创建窗口
		var updateWin = new Ext.Window( {
			title : "编辑菜单",
			width : 350,
			autoHeight : true,
			buttonAlign : "center",
			items : [ updateDishForm ],
			closeAction : "hide"

		});
		/** ************************************点餐窗口*********************************** */
		// 表单
		var addOrderForm = new Ext.form.FormPanel( {
			items : [ 
			{
				xtype:"hidden",
				name : 'id'
			},
			{
				xtype : "textfield",
				fieldLabel : "订餐名称",
				name : "dish_name",
				allowBlank : false,
				readOnly:true
			},{
				xtype : "textfield",
				fieldLabel : "订餐人",
				name : "user_name",
				regex : /^([\u4e00-\u9fa5]){0,10}$/,
				regexText : "只能输入中文",
				allowBlank : false,
				blankText : "订餐人不能为空"
			},{
				xtype : "textfield",
				fieldLabel : "数量",
				name : "dish_number",
				regex : /^[1-9]$/,
				regexText : "数量1-9",
				allowBlank : false,
				blankText : "数量不能为空",
				value:"1"
			}],
			buttons : [ {
				text : "点餐",
				handler : doAddOrder
			}, {
				text : "取消",
				handler : function() {
				addOrderWin.hide();
				}
			}
			 ],
			width : 350,
			height : 200,
			labelAlign : "right",
			frame : true,
			buttonAlign : "center",
			style : "padding-top:20px;padding-bottom:20px;",
			border : false
		});
		// 创建窗口
		var addOrderWin = new Ext.Window( {
			title : "点餐",
			width : 350,
			autoHeight : true,
			buttonAlign : "center",
			items : [ addOrderForm ],
			closeAction : "hide"

		});
		/** ************************************菜单列表*********************************** */
		// Ext.QuickTips.init();
		// 添加checkbox框
		var check = new Ext.grid.CheckboxSelectionModel();

		// 代理
		var proxy = new Ext.data.DWRProxy(DishBiz.getAll, true);
		// 声明记录
		var number = new Ext.data.Record.create( [ {
			name : "id"
		}, {
			name : "dish_name"
		}, {
			name : "dish_week"
		}, {
			name : "dish_price"
		}, {
			name : "dish_type"
		} ]);
		// reader
		var reader = new Ext.data.ListRangeReader( {
			totalProperty : 'rowCount'
		}, number);
		// 创建Store
		var store = new Ext.data.Store( {
			proxy : proxy,// 代理
			reader : reader,// reader
			// autoLoad:true,//自动加载
			remoteSort : true
		});
		doLoad();
		function doLoad() {
			store.load( {
				params : {
					start : 0,
					limit : pageSize
				}
			})
		}

		// 创建列
		var col = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(),
				check, {
					header : "编号",
					sortable : true,
					width : 50,
					dataIndex : "id"
				}, {
					header : "菜名",
					sortable : true,
					width : 100,
					dataIndex : "dish_name"
				}, {
					header : "价格",
					width : 100,
					dataIndex : "dish_price",
					sortable : true
				}, {
					header : "类型",
					width : 100,
					dataIndex : "dish_type",
					sortable : true
				} ,{
					header : "第几周",
					sortable : true,
					width : 100,
					dataIndex : "dish_week",
					id : "demo"
				}, ]);

		// 创建表格
		var grid = new Ext.grid.GridPanel( {
			store : store,
			width : 800,
			cm : col,
			sm : check,
			pageSize : pageSize,
			autoHeight : true,
			renderTo : Ext.getBody(),
			autoExpandColumn : "demo",
			// 顶部工具条
			tbar : [ {
				text : "添加菜单",
				handler : showAdd,// 绑定方法
				scope : this
			}, new Ext.Toolbar.Fill(),'<span style="color:#FF0000;">当前为第'+current_week+'周&nbsp;&nbsp;</span>', 
			"菜名",{
				xtype:"textfield",
				id:"dish_name",
				name:"dish_name",
				width:100
			},
			"类型", query_dish_type, 
			"第", query_dish_week, "周", {
				text : '查询',
				width:50,
				pressed : true,
				handler : doSearch,
				scope : this
			}, {
				text : '查询全部',
				width:50,
				pressed : true,
				handler : getAll,
				scope : this
			} ],
			// 底部工具条
			bbar : new Ext.PagingToolbar( {
				pageSize : pageSize,
				store : store,
				displayInfo : true,
				displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
				emptyMsg : "没有记录"
			}),
			// 监听器
			listeners:{
				"rowContextmenu":function(grid,index,e){
					grid.getSelectionModel().selectRow(index);
					var state = grid.getSelectionModel().getSelected()
							.get('wordNumber');
					// 菜单
					this.menu=new Ext.menu.Menu({
						// 菜单项
						items:[{
							text:"点餐",
							handler:showAddOrder,
							scope:this
						},{
								text:"编辑",
								handler:showUpdate,
								scope:this
							},{
								text:"删除",
								handler:doRemove,
								scope:this
							}]
						});
					// 不显示IE菜单项
					e.preventDefault();
					// 鼠标位置 显示菜单
					this.menu.showAt(e.getXY());
				}
			}
		});
		//显示点餐窗口
		function showAddOrder(){
			// 获取选择数据
			 var record = grid.getSelectionModel().getSelected();
			 if (!record) {
				 Ext.Msg.alert("提示", "未选择菜单");
				 return;
			 }
			 if (grid.getSelectionModel().getCount() > 1) {
				 Ext.Msg.alert("提示", "只能选择1个菜单");
				 return;
			 }
			 // 加载表单数据
			 addOrderForm.form.loadRecord(record);
			 // 显示
			 addOrderWin.show();		
		}
		// 显示添加窗口
		function showAdd() {
			addWin.show();
		}
		// 显示编辑窗口
		 function showUpdate() {
			 if (updateWin.isVisible()) 
				 return;
			 
			 // 获取选择数据
			 var record = grid.getSelectionModel().getSelected();
			 if (!record) {
				 Ext.Msg.alert("提示", "未选择菜单");
				 return;
			 }
			 if (grid.getSelectionModel().getCount() > 1) {
				 Ext.Msg.alert("提示", "只能选择1个菜单");
				 return;
			 }
			 
			 // 加载表单数据
			 updateDishForm.form.loadRecord(record);
			 // 设置窗口标题
			 updateWin.setTitle("修改菜单");
			 // 显示
			 updateWin.show();		 
		 }
		 
		// 删除数据
		function doRemove() {
			// 获取选择数据
			var records = grid.getSelectionModel().getSelections();
			if (records.length == 0) {
				Ext.Msg.alert("提示", "请先选择行");
				return;
			}
			Ext.MessageBox.confirm("确认删除", "确认删除所选数据？", function(button) {
				// Ext.Msg.alert(button);
					if (button == "yes") {
						var list = new Array(records.length);
						// 遍历记录并添加到list中
					Ext.each(records, function(re, i) {
						list[i] = records[i].data;
					});
					// 删除数据
					DishBiz.doDelete(list, function(data) {
						if (data.success) {
							Ext.Msg.alert("提示", data.msg);
							store.reload();// 重新加载数据
						} else
							Ext.Msg.alert("提示", data.msg);

					});
				}
			}, this);
		}
		
		// 添加菜单
		function doAdd() {
			// 验证表单
			if(addDishForm.form.findField("dish_name").validate()
					&&addDishForm.form.findField("dish_price").validate()
					&&addDishForm.form.findField("dish_type").validate()
					&&addDishForm.form.findField("dish_week").validate()){
				
				// 添加菜单
				DishBiz.doAdd(addDishForm.form.getValues(false), function(data) {
					Ext.Msg.alert("提示", data.msg);
					if(data.success==true){
						addDishForm.form.reset();
						addWin.hide();
						store.reload();// 重新加载数据
					}
				});
			}
		}
		//订餐
		function doAddOrder(){
			// 验证表单
			if(addOrderForm.form.findField("user_name").validate()
					&&addOrderForm.form.findField("dish_name").validate()
					&&addOrderForm.form.findField("dish_number").validate()){
				// 订餐
				OrderBiz.doAdd(addOrderForm.form.getValues(false), function(data) {
					Ext.Msg.alert("提示", data.msg);
					if(data.success==true){
						addOrderForm.form.reset();
						addOrderWin.hide();
					}
				});
			}
		}
		// 修改信息
		 function doUpdate() {
			 if (updateDishForm.form.isValid()) {
				 // 修改信息
				 DishBiz.doUpdate(updateDishForm.form.getValues(false),
						 function(data) {
					 if (data.success) {
						 Ext.Msg.alert("提示", data.msg);
						 store.reload();// 重新加载数据
						 updateWin.hide();// 关闭窗口
					 } else
						 Ext.Msg.alert("提示", data.msg);
				 });
			
			 }
		 }
		// 查询用户
		function doSearch() {
			store.baseParams.dish_name = Ext.get("dish_name").getValue();
			store.baseParams.dish_type = query_dish_type.getValue();
			store.baseParams.dish_week = query_dish_week.getValue();
			doLoad();
		}
		//查询全部
		function getAll(){
			store.baseParams.dish_week = "";
			doLoad();
		}
	})