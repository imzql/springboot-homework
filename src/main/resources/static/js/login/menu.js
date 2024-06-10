function showTab(id,name,url){
    //查看标签的id
    let length = $("li[lay-id="+id+"]").length;

    let element = layui.element;

    if (length == 0){
        let fullUrl = "/" + url;
        let  height = $(window).height()-185;
        let content = '<iframe src="'+fullUrl+'" frameborder="0" scrolling="no" style="width: 100%;height: '+height+'px"></iframe>';

        element.tabAdd('menu', {
            title:name,
            content:content,
            id:id
        });
    }

    element.tabChange('menu', id);
}