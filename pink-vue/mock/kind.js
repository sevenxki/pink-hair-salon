const Mock = require("mockjs");
const data = Mock.mock({
    "items|15": [
        {
            kindId: "@increment",
            kindName: "@ctitle",
            kindNo: '@character("upper")' + "@integer(1, 999)",
        },
    ],
});
module.exports = [
    // 分页展示数据
    {
        url: "/hsKind/page",
        type: "get",
        response: (req, res) => {
            const items = data.items;
            return {
                code: 200,
                data: {
                    total: items.length,
                    pagetNum: 1,
                    pageSize: 10,
                    list: items,
                },
            };
        },
    },
    // 展示数据
    {
        url: "/hsKind/list",
        type: "get",
        response: (req, res) => {
            const items = data.items;
            return {
                code: 200,
                data: {
                    total: items.length,
                    list: items,
                },
            };
        },
    },
];