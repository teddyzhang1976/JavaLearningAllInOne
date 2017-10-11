    
    var namespace='log';
    var action='operate-log';
    
    var category="operateType";
    var chartDataURL=contextPath+'/'+namespace+'/'+action+'!chart.action?category=';
    
    OperateLogChart=function(){
        return{
            show : function(url,category,queryString,type){
                var dataTypeLabel='数据类型'
                var dataTypeItems=[
                                    {boxLabel: '用户', name : "dataType", inputValue: 'user',checked:category=='user'},
                                    {boxLabel: '操作类型', name : "dataType", inputValue: 'operateType',checked:category=='operateType'}
                              ];
                var dataTypeColumns=10;
                var dataTypeHeight=20;

                MultiSeriesChartBaseModel.show("增删改统计","cud",100,url, category, queryString, type, dataTypeLabel, dataTypeItems, dataTypeColumns, dataTypeHeight);
            }
        }
    }();
    //统计图
    ChartModel= function(){
        return{
            show: function(){
                OperateLogChart.show(chartDataURL,category,GridBaseModel.queryString,"MSColumn3D"); 
            }
        }
    }();
    //高级搜索
    AdvancedSearchModel = function() {
        return {
            //搜索表单
            getItems : function(){
                var items=[{
                        xtype: 'combo',
                        id:'search_ownerUser_username',
                        store:userStore,
                        emptyText:'请选择',
                        mode:'remote',
                        valueField:'value',
                        displayField:'text',
                        triggerAction:'all',
                        forceSelection: true,
                        editable:       false,
                        fieldLabel: '用户名'
                    },{
                        xtype:'datefield',
                        format:"Y-m-d",
                        id:'search_startOperatingTime',
                        editable:false,
                        fieldLabel: '开始日期'
                    },{
                        xtype:'datefield',
                        format:"Y-m-d",
                        id:'search_endOperatingTime',
                        editable:false,
                        fieldLabel: '结束日期'
                    },{
                        xtype: 'combo',
                        id:'search_operatingType',
                        store : new Ext.data.SimpleStore({
                           fields : ['value', 'text'],
                           data:[['增加','增加'],['删除','删除'],['修改','修改']]
                        }),
                        emptyText:'请选择',
                        mode:'local',
                        valueField:'value',
                        displayField:'text',
                        triggerAction:'all',
                        forceSelection: true,
                        editable:       false,
                        fieldLabel: '操作类型'
                    },{
                        xtype: 'combo',
                        id:'search_operatingModel',
                        store:modelStore,
                        emptyText:'请选择',
                        mode:'remote',
                        valueField:'value',
                        displayField:'text',
                        triggerAction:'all',
                        forceSelection: true,
                        editable:       false,
                        fieldLabel: '模型名称'
                    }];
                return items;
            },
            //点击搜索之后的回调方法
            callback : function(){               
                    var data=[];

                    var search_ownerUser_username=parent.Ext.getCmp('search_ownerUser_username').getValue();
                    if(search_ownerUser_username!=""){
                        search_ownerUser_username=' +ownerUser_username:'+search_ownerUser_username;
                        data.push(search_ownerUser_username);
                    }

                    var search_operatingType=parent.Ext.getCmp('search_operatingType').getValue();
                    if(search_operatingType!=""){
                        search_operatingType=' +operatingType:'+search_operatingType;
                        data.push(search_operatingType);
                    }

                    var search_operatingModel=parent.Ext.getCmp('search_operatingModel').getValue();
                    if(search_operatingModel!=""){
                        search_operatingModel=' +operatingModel:'+search_operatingModel;
                        data.push(search_operatingModel);
                    }

                    var search_startOperatingTime=parent.Ext.getCmp('search_startOperatingTime').value;
                    var search_endOperatingTime=parent.Ext.getCmp('search_endOperatingTime').value;
                    var search_operatingTime="";
                    if(search_startOperatingTime!=undefined && search_startOperatingTime!="" && search_endOperatingTime!=undefined && search_endOperatingTime!=""){
                        search_operatingTime=' +operatingTime:['+search_startOperatingTime+' TO '+search_endOperatingTime+']';
                        data.push(search_operatingTime);
                    }

                    AdvancedSearchBaseModel.search(data, "OperateLog");
            },
            
            show: function() {
                AdvancedSearchBaseModel.show('高级搜索',"cud", 420, 240, this.getItems(), this.callback);
            }
        };
    } ();
    //表格
    GridModel = function() {
        return {
            getFields: function(){
                var fields=[
 				{name: 'id'},
				{name: 'ownerUser'},
				{name: 'loginIP'},
				{name: 'serverIP'},
				{name: 'operatingTime'},
 				{name: 'operatingType'},
				{name: 'operatingModel'},
				{name: 'operatingID'}
			];
               return fields;     
            },
            getColumns: function(){
                var columns=[
 				{header: "编号", width: 10, dataIndex: 'id', sortable: true},
				{header: "用户名称", width: 20, dataIndex: 'ownerUser', sortable: true},
				{header: "登录IP地址", width: 20, dataIndex: 'loginIP', sortable: true},
				{header: "服务器IP地址", width: 20, dataIndex: 'serverIP', sortable: true},
				{header: "操作时间", width: 20, dataIndex: 'operatingTime', sortable: true},
 				{header: "操作类型", width: 10, dataIndex: 'operatingType', sortable: true},
				{header: "操作模型", width: 20, dataIndex: 'operatingModel', sortable: true},
				{header: "模型ID", width: 20, dataIndex: 'operatingID', sortable: true}
                            ];
                return columns;           
            },
            show: function(){
                var pageSize=17;
                
                var commands=["search","query","chart"];
                var tips=['高级搜索(S)','显示全部(A)','图表(T)'];
                var callbacks=[GridBaseModel.advancedsearch,GridBaseModel.showall,GridBaseModel.chart];
            
                GridBaseModel.show(contextPath, namespace, action, pageSize, this.getFields(), this.getColumns(), commands,tips,callbacks);
            }
        }
    } ();

Ext.onReady(function(){
        GridModel.show();
});