/**
 * 公共弹窗,弹出增加.修改,删除 xxx 数据库表
 * @param url
 * @param title
 */
function zjlOpenPage(url,title){
    $.ajaxSettings.async = false;
    $.get(url,function (response) {
        layer.open({
            type: 1, // page 层类型
            area: ['800px', '450px'],
            title: title,
            content: response
        });
    })
    $.ajaxSettings.async = true;
}

/**
* 弹出弹框,输入信息之后,将客户的信息提交到后端
 */
function submit(filter,type,func){
    // 提交事件
    layui.form.on('submit('+filter+')', function(data){
        //有资源模块的时候 我才传入资源模块的id值
        if (typeof (func) != 'undefined'){
            func(data.field)
        }
        console.log(data)
        // 此处可执行 Ajax 等操作s
        $.ajax({
            url: data.form.action,
            async: true,
            type: type,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data.field),success:function(response){
                console.log(response)
                if (response.code == 0 ){
                    layer.closeAll();
                    query();
                }else{
                    layer.alert(response.msg)
                }
            }})
        return false; // 阻止默认 form 跳转
    });
}

/**
 * 删除功能
 * @param url
 */

function del(url){
    // 此处可执行 Ajax
    $.ajax({
        url: url,
        async: false,
        type: "DELETE",
        success:function(response){
            console.log(response)
            if (response.code == 0 ){
                query();
            }else{
                layer.alert(response.msg)
            }
        }})
}

