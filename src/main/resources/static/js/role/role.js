var table = layui.table;

// 创建渲染实例
var tableIns = table.render({
    elem: '#roleList',
    url: '/role/list', // 此处为静态模拟数据，实际使用时需换成真实接口
    page: true,
    height: '315px',
    parseData: function (res) { // res 即为原始返回的数据
        return {
            "code": res.code, // 解析接口状态
            "msg": res.msg, // 解析提示文本
            "count": res.data.count, // 解析数据长度
            "data": res.data.records // 解析数据列表
        };
    },
    cols: [[
        // 未自定义模板的普通列
        {field: 'roleId', width: 50, title: '序号'},
        {field: 'roleName', width: 100, title: '角色名称'},
        {field: 'remark', width: 200, title: '备注'},
        {field: 'zjlCreateTime', width: 250, title: '创建时间'},
        {field: 'zjlModifiedTime', width: 250, title: '修改时间'},
        {title: '操作', width: 250, toolbar: "#barDemo"}

    ]]
});

function query() {
    tableIns.reload({
        page: {
            curr: 1 // 重新从第 1 页开始
        },
        where: {
            roleName: $("#roleName").val(),
        } // 搜索的字段
    });
}

function toAdd() {
    zjlOpenPage("/role/toAdd", '新增角色')
    Modelstree("/role/models","resource",true)
    //将前端传来的roid以及资源传递到后端
    submit("addSubmit","POST",addResouceIds);
}

// 单元格工具事件
table.on('tool(test)', function(obj){
    var data = obj.data; // 得到当前行数据
    var layEvent = obj.event; // 获得元素对应的 lay-event 属性值
    var roleId = data.roleId;
    console.log("@@@@@@"+roleId)

    if(layEvent === 'detail'){ //查看
        zjlOpenPage("/role/toDetail/"+roleId,"用户详情");
        Modelstree('/role/updateModels/'+roleId,roleId,false);

    } else if(layEvent === 'del'){ //删除
        layer.confirm('确定删除吗？', function(index){
            console.log(roleId)
            del('/role/'+roleId);
            layer.close(index);

        });
    } else if(layEvent === 'edit'){ //编辑
        // do something
        zjlOpenPage("/role/toUpdate/"+roleId,"修改用户");

        Modelstree('/role/updateModels/'+roleId);

        submit("updateSubmit","PUT",addResouceIds);
    }
});




function Modelstree(url,id,showCheckbox){
    if (typeof (showCheckbox) == "undefined"){
        showCheckbox =true
    }

    $.ajax({
        url: url,
        async: false,
        type: "GET",
        success: function (response) {
            console.log(response)
            if (response.code == 0) {
                layui.tree.render({
                    elem: '#resource',
                    data: response.data,
                    id:id,
                    showCheckbox: showCheckbox
                });
            }
        }
    })
}

//递归的概念
var addResouceIds = function(fieId){
    let zjlModelViews = layui.tree.getChecked('resource');
    //模块资源id数组
    fieId.resourceIds = getResourceIds(zjlModelViews,[]);
}

//getResourceIds将所属的几级目录遍历出来 取出每一条目录
function getResourceIds(zjlModelViews,arr){
    for (let i in zjlModelViews){
        //将一级目录的id值取出来
        arr.push(zjlModelViews[i].id)
        //将二级目录的id值取出来
        arr = getResourceIds(zjlModelViews[i].children,arr);

    }
    return arr;
}

//二级目录
