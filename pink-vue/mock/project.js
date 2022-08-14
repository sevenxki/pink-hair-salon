const Mock = require("mockjs");
const data = Mock.mock({
    "items|15": [
        {
            projectId: "@increment",
            projectName: "@ctitle",
            projectNo: '@character("upper")' + "@integer(1, 999)",
            projectPrice: "@integer(1, 999)",
            kindId: "@increment",
        },
    ],
});
module.exports = [
    // 展示数据
    {
        url: "/hsProject/page",
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
];