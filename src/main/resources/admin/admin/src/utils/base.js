const base = {
    get() {
        return {
            url : "http://localhost:8080/shequyiyuan/",
            name: "shequyiyuan",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/shequyiyuan/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "社区医院管理系统"
        } 
    }
}
export default base
