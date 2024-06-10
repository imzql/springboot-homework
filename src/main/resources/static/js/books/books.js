var table = layui.table;

// 创建渲染实例
var tableIns = table.render({
    elem: '#booksList',
    url:'/books/list', // 此处为静态模拟数据，实际使用时需换成真实接口
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
        {field:'bookId',  width:80, title: '图书ID'},
        {field:'title',width:200,  title: '图书名称'},
        {field:'author',width:200,   title: '作者'},
        {field:'publicationYear',width:100,   title: '出版年份'},
        {field:'publisher',width:200,  title: '出版社'},
        {field:'isbn',width:200,  title: 'SN码'},
        {field:'genre',  width:120, title: '品类'},
        {field:'quantity',  width:80, title: '库存'},
        {field:'availableQuantity',  width:80, title: '库存剩余'},
        {title: '操作',width:250,toolbar:"#barDemo"}
    ]]
});

function query(){
    tableIns.reload({
        page: {
            curr: 1 // 重新从第 1 页开始
        },
        where: {
            bookId:$("#bookId").val(),
            title:$("#title").val(),
        } // 搜索的字段
    });
}

function toAdd(){
        zjlOpenPage("/books/toAdd",'新增图书')

        layui.form.render();

        submit("addSubmit","POST");
}

// 单元格工具事件
table.on('tool(test)', function(obj){
    var data = obj.data; // 得到当前行数据
    var layEvent = obj.event; // 获得元素对应的 lay-event 属性值


    var customerId = data.bookId;

    if(layEvent === 'detail'){ //查看
        zjlOpenPage("/books/toDetail/"+customerId,"图书详情");

    } else if(layEvent === 'del'){ //删除
        layer.confirm('确定删除吗？', function(index){
            console.log(customerId)
            del('/books/'+customerId);
            layer.close(index);

        });
    } else if(layEvent === 'edit'){ //编辑
        // do something
        zjlOpenPage("/books/toUpdate/"+customerId,"修改图书");

        layui.form.render();

        submit("updateSubmit","PUT");
    }
});
