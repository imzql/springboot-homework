var table = layui.table;

// 创建渲染实例
var tableIns = table.render({
    elem: '#customerList',
    url:'/customer/list', // 此处为静态模拟数据，实际使用时需换成真实接口
    page: true,
    height: '315px',
    parseData: function(res){ // res 即为原始返回的数据
        return {
            "code": res.code, // 解析接口状态
            "msg": res.msg, // 解析提示文本
            "count": res.data.count, // 解析数据长度
            "data": res.data.records // 解析数据列表
        };
    },
    cols: [[
        // 未自定义模板的普通列
        {field:'zjlCustomerId',  width:50, title: '序号'},
        {field:'zjlRealName',width:100,  title: '真实姓名'},
        {field:'zjlSex',width:80,   title: '性别'},
        {field:'zjlAge',width:80,   title: '年龄'},
        {field:'zjlEmail',width:150,  title: '邮箱'},
        {field:'zjlPhone',width:150,  title: '手机号码'},
        {field:'zjlAddress',  width:150, title: '地址'},
        {field:'zjlCreateTime',  width:250, title: '创建时间'},
        {field:'zjlModifiedTime',  width:250, title: '修改时间'},
        {title: '操作',width:250,toolbar:"#barDemo"}

    ]]
});

function query(){
    tableIns.reload({
        page: {
            curr: 1 // 重新从第 1 页开始
        },
        where: {
            zjlRealName:$("#realName").val(),
            zjlPhone:$("#phone").val(),
        } // 搜索的字段
    });
}

function toAdd(){
        zjlOpenPage("/customer/toAdd",'新增用户')

        layui.form.render();

        submit("addSubmit","POST");
}

// 单元格工具事件
table.on('tool(test)', function(obj){
    var data = obj.data; // 得到当前行数据
    var layEvent = obj.event; // 获得元素对应的 lay-event 属性值


    var customerId = data.zjlCustomerId;

    if(layEvent === 'detail'){ //查看
        zjlOpenPage("/customer/toDetail/"+customerId,"用户详情");

    } else if(layEvent === 'del'){ //删除
        layer.confirm('确定删除吗？', function(index){
            console.log(customerId)
            del('/customer/'+customerId);
            layer.close(index);

        });
    } else if(layEvent === 'edit'){ //编辑
        // do something
        zjlOpenPage("/customer/toUpdate/"+customerId,"修改用户");

        layui.form.render();

        submit("updateSubmit","PUT");
    }
});
