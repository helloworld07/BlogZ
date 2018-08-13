# BlogZ

1.web:
    欢迎页，错误页 √
    
2.login：
    登录界面改造 √
    登录功能实现: √
        建表  √
        登录逻辑/业务 :一般方式 √  /shiro方式 企业级配置，太麻烦了，得不偿失 ×
    注册功能实现：
        逻辑实现 :注册校验：重复用户名校验，密码、邮箱、电话号码校验。 √
    博客页面：
        博客主页：画模板，规划板块（核心不在多，拥有可扩展性，预留空间）
            首页模块 ：
                        首页轮播图 √
                        首页新闻页：(只显示有限条数)
                                    数据抓取
                                    新闻列表
                                    新闻详情
            博客文章 : 文章列表页+分页ajax √
                       文章分类栏后台自动获取，分类分页功能完整 √
                       文章私密隐藏 √
                       文章详细界面:显示详情 √
                                    评论 ：在文章详情库里新建评论字段，显示字段flag
                                           有作者隐藏功能
                                           收藏，分享
                       博客编写页面：基本功能 √ 
                                    UEditor设置 √
                       
                       



`tips:
1.默认静态资源访问在static路径下，所以引用时直接static后的路径。
2.需要数据库的项目启动需要配置数据源信息，此项目用的Druid，相关配置在login的config下
3.yml类型的文件在@configuration的读取时可能会读不出来，这里还是改用老式的properties型
4.一般登录逻辑采用二次跳转：登录成功跳转页面，页面再次请求获取session，是否有潜在问题
5.用的自己写的比较老套的一套注册校验，在代码层面只强行规定三项不能为空，其他都属于页面校验，可能出现页面破解安全隐患
6.实现了一个比较原始的blog显示文章界面，用的ajax调用，返回list再进行html语言穿插和拼接，
    并不是很利于后期的维护和更改，比较麻烦，html不能用jsp的${}造成了很多困扰
7.添加了pagehelper分页插件，需要依赖、配置文件、service层和controller层多加代码，页面实现控制:
    页面用的ajax分页，好麻烦
8.引入了ueditor富文本编辑空间，官网上说不要用于生产环境~~，功能很多(static文件夹)，
    与springboot结合得并不是那么完美，需要手动写不少文件：https://www.cnblogs.com/liter7/p/7745606.html
    主要用到：config.json(springboot中用在了BlogConroller里)、ueditor.config.js(配置加载config.json)、BlogController.java、blogwrite.html(配置请求路径)
9.引入toastr提示控件(static文件夹)，需结合jquery,且在jquery后使用,在blogwrite.html中有使用
10.springboot在每次启动后都会在本地重新创建路径，这一点对图片想保持到项目来说不好办啊，如果需要部署服务器，上传和显示图片
    方面还得改路径
11.全局项目用的session传递用户信息
12.templates里一般用的是thymeleaf模板写的，static/page里一般用的ajax写的比较麻烦，但支持异步加载
13.引入了pace.js自动进度条，需要导包pace,需要引用三行script头文件，
    get/post方法都可监控需在js文件之前引入
14.`
