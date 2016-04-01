
Ext.onReady(function() {
	var pageSize = 50;
		
		/** ************************************菜单列表*********************************** */
		 Ext.QuickTips.init();
		//订餐时间
		var create_time=new Ext.form.DateField({
			fieldLabel:"订餐时间",
			name:"create_time",
			format:"Y-m-d",
			allowBlank:false,
			minvalue:new Date()
		});
		// 添加checkbox框
		var check = new Ext.grid.CheckboxSelectionModel();

		// 代理
		var proxy = new Ext.data.DWRProxy(OrderBiz.getAll, true);
		// 声明记录
		var number = new Ext.data.Record.create( [ {
			name : "id"
		}, {
			name : "dish_name"
		}, {
			name : "user_name"
		}, {
			name : "dish_number"
		}, {
			name : "create_time"
		},{
			name:"isPayment"
		} ]);
		// reader
		var reader = new Ext.data.ListRangeReader( {
			totalProperty : 'rowCount'
		}, number);
		// 创建Store
		var store = new Ext.data.Store( {
			proxy : proxy,// 代理
			reader : reader,// reader
			baseParams : {
				id : "",
				dish_name : "",
				user_name : "",
				dish_number : "",
				create_time : "",
				isPayment:""
			},
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
					header : "订饭人",
					width : 100,
					dataIndex : "user_name",
					sortable : true
				}, {
					header : "数量",
					width : 100,
					dataIndex : "dish_number",
					sortable : true
				},{
					header : "是否付款",
					width : 100,
					dataIndex : "isPayment",
					renderer : getIsPayment,
					sortable : true
				} ,{
					header : "时间",
					sortable : true,
					width : 100,
					dataIndex : "create_time",
					renderer:function(value){ return value.format("Y-m-d");},
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
			tbar : [
			    new Ext.Toolbar.Fill(), 
			    "订餐时间",create_time, {
				text : '查询',
				width:50,
				pressed : true,
				handler : doSearch,
				scope : this
			    },{
	    			xtype : 'button',
	    			text : '今日订餐统计EXCEL',
	    			handler : function(){
			    	OrderBiz.doExportAssessmentExcel({
						    callback : function(data){
						         dwr.engine.openInDownload(data);
						      },
						    async : false
						});
	    			}
	    		}
			 ],
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
					// 菜单
					this.menu=new Ext.menu.Menu({
						// 菜单项
						items:[{
							text:"修改已付款",
							handler:modifyPayment,
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
		/* ***是否付款颜色*** */
		function getIsPayment(isPayment) {
			var colors = ['red','#888888'];
			var isPayments = ['未付款','已付款'];
			if(isPayment!="是")
				return "<span style='color:" + colors[0] + ";'>" + isPayments[0]
					+ "</span>";
			else
				return "<span style='color:" + colors[1] + ";'>" + isPayments[1]+ "</span>";
		}
		// 删除数据
		function doRemove() {
			// 获取选择数据
			var records = grid.getSelectionModel().getSelections();
			if (records.length == 0) {
				Ext.Msg.alert("提示", "请先选择菜单");
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
						OrderBiz.doDelete(list, function(data) {
							if (data.success) {
								Ext.Msg.alert("提示", data.msg);
								store.reload();// 重新加载数据
							} else
								Ext.Msg.alert("提示", data.msg);
	
						});
				}
			}, this);
		}
		//修改已付款
		function modifyPayment(){
			// 获取选择数据
			var records = grid.getSelectionModel().getSelections();
			if (records.length == 0) {
				Ext.Msg.alert("提示", "请先选择菜单");
				return;
			}
			Ext.Msg.confirm("提示", "确认修改为已付款？该操作不可逆.",function(button){
					if(button=="yes"){
						var list = new Array(records.length);
						// 遍历记录并添加到list中
						Ext.each(records, function(re, i) {
							list[i] = records[i].data;
						});
						// 修改已付款
						OrderBiz.doModifyPayment(list, function(data) {
							if (data.success) {
								Ext.Msg.alert("提示", data.msg);
								store.reload();// 重新加载数据
							} else
								Ext.Msg.alert("提示", data.msg);

						});
					}
			})
			
		}
		// 查询用户
		function doSearch() {
			store.baseParams.create_time = create_time.getValue();
			doLoad();
		}
	})