var table = layui.table;

// 创建渲染实例
var tableIns = table.render({
    elem: '#cartList',
    url:'/cart/list', // 此处为静态模拟数据，实际使用时需换成真实接口
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
        {field:'cartId',  width:100, title: '购物车ID'},
        {field:'userId',width:100,  title: '顾客ID'},
        {field:'bookId',width:100,   title: '图书ID'},
        {field:'quantity',width:300,   title: '总数'},
        {title: '操作',width:250,toolbar:"#barDemo"}

    ]]
});

function query(){
    tableIns.reload({
        page: {
            curr: 1 // 重新从第 1 页开始
        },
        where: {
            cartId:$("#cartId").val(),
            userId:$("#userId").val(),
        } // 搜索的字段
    });
}

function toAdd(){
        zjlOpenPage("/cart/toAdd",'新增用户')

        layui.form.render();

        submit("addSubmit","POST");
}

// 单元格工具事件
table.on('tool(test)', function(obj){
    var data = obj.data; // 得到当前行数据
    var layEvent = obj.event; // 获得元素对应的 lay-event 属性值


    var customerId = data.cartId;

    if(layEvent === 'detail'){ //查看
        zjlOpenPage("/cart/toDetail/"+customerId,"用户详情");

    } else if(layEvent === 'del'){ //删除
        layer.confirm('确定删除吗？', function(index){
            console.log(customerId)
            del('/cart/'+customerId);
            layer.close(index);

        });
    } else if(layEvent === 'edit'){ //编辑
        // do something
        zjlOpenPage("/cart/toUpdate/"+customerId,"修改用户");

        layui.form.render();

        submit("updateSubmit","PUT");
    }
});
