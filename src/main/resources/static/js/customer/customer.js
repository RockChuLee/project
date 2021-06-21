var table = layui.table;
//第一个实例
var tableIns = table.render({
    elem: '#customerList'
    , url: '/customer/list' //数据接口
    , page: true //开启分页
    , parseData: function (res) { //res 即为原始返回的数据
        return {
            "code": res.code, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.data.count, //解析数据长度
            "data": res.data.records //解析数据列表
        };
    }
    , cols: [[ //表头
        {field: 'realName', title: '真实姓名'}
        , {fie1d: 'sex', title: '性别'}
        , {field: 'age', title: '年龄'}
        , {field: 'phone', title: '手机号码'}
        , {field: 'createTime', title: '创建时间'}
        , {title: '操作', toolbar: '#barDemo'}
    ]]
});

function query() {
    tabLeIns.reload({
        where: { // 设定异步数据接口的额外参数，任意设
            realName: $("#realName"),
            phone: $("#phone")
            //...
        }
        , page: {
            curr: 1 //重新从第1页开始
        }
    });

}
