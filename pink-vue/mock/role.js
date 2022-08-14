const Mock = require("mockjs");
const data = Mock.mock({
    "items|15": [
        {
            id: "@increment",
            username: "@string('lower', 1, 8)",
            name: "@string('lower', 2, 4)",
            phoneNumber: /\d{11}/,
            "roleName|+1": ['店长', '发型师', '技师', '收银员', '客户'],
            creationDay: "@date()",
            "roleStatus|+1": ['可用', '不可用'],
        },
    ],
    "roles": [
        {
            roleId: "1",
            roleName: "店长"
        },
        {
            roleId: "2",
            roleName: "发型师"
        },
        {
            roleId: "3",
            roleName: "技师"
        },
        {
            roleId: "4",
            roleName: "收银员"
        },
    ]
});
module.exports = [
    // 分页展示数据
    {
        url: "/hsRole/page",
        type: "get",
        response: (req, res) => {
            const items = data.items;
            return {
                code: 200,
                data: {
                    total: items.length,
                    pagetNum: 1,
                    pageSize: 4,
                    list: items,
                },
            };
        },
    },
    // 获取所有用户类型和roleid
    {
        url: "/hsRole/all",
        type: "get",
        response: (req, res) => {
            const roles = data.roles;
            return {
                code: 200,
                data: {
                    list: roles,
                },
            };
        },
    },
];