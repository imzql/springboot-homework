var laydate = layui.laydate;

laydate.render({
    elem: '#createTimeRange',
    range: true
});

var table = layui.table;

// 创建渲染实例
var tableIns = table.render({
    elem: '#accountList',
    url: '/account/list', // 此处为静态模拟数据，实际使用时需换成真实接口
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
        {field: 'zjlUsername', width: 120, title: '用户名'},
        {field: 'zjlRealName', width: 120, title: '真实姓名'},
        {field: 'zjlSex', width: 80, title: '性别'},
        {field: 'zjlEmail', width: 200, title: '邮箱'},
        // {field:'zjlPhone',width:150,  title: '手机号码'},
        // {field:'zjlAddress',  width:150, title: '地址'},
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
            zjlRealName: $("#realName").val(),
            zjlEmail: $("#email").val(),
            createTimeRange: $("#createTimeRange").val()
        } // 搜索的字段
    });
}

function toAdd() {
    zjlOpenPage("/account/toAdd", '新增账号')

    layui.form.render();

    submit("addSubmit", "POST");
}

// 单元格工具事件
table.on('tool(test)', function (obj) {
    var data = obj.data; // 得到当前行数据
    var layEvent = obj.event; // 获得元素对应的 lay-event 属性值
    var zjlAccountId = data.zjlAccountId;

    if (layEvent === 'detail') { //查看
        zjlOpenPage("/account/toDetail/" + zjlAccountId, "账号详情");

    } else if (layEvent === 'del') { //删除
        layer.confirm('确定删除吗？', function (index) {
            del('/account/' + zjlAccountId);
            layer.close(index);

        });
    } else if (layEvent === 'edit') { //编辑
        // do something
        zjlOpenPage("/account/toUpdate/" + zjlAccountId, "修改账号");

        layui.form.render();

        submit("updateSubmit", "PUT");
    }
});

layui.form.verify({
    checkUsername: function (value, elem) {
        let error = null;
        let url = "/account/" + value;
        let accountId = $('input[name="zjlAccountId"]').val();
        //判断改弹窗页面是新增页面还是修改页面
        //修改页面
        if (typeof (accountId) != "undefined") {
            url += "/" + accountId;
        }

        $.ajax({
            url: url,
            async: false,
            type: "GET"
            , success: function (response) {
                console.log(response)
                if (response.code == 0) {
                    if (response.data > 0) {
                        error = "用户名存在";
                    }
                } else {
                    error = "用户名检测出错"
                }
            }, error: function () {
                error = "用户名检测出现"
            }
        });
        if (error != null) {
            return error;
        }
    }
});