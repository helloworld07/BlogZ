![BlogZ](https://socialify.git.ci/helloworld07/BlogZ/image?description=1&font=Bitter&owner=1&pattern=Signal&stargazers=1&theme=Dark)
# BlogZ

> part 1 的基本完成，其他模块继续更新

----------
**模块划分：**

 1. 欢迎页，错误页 √
 
 2. 登录界面改造 √
 
 3. 登录功能实现: √
 
        建表  √
        登录逻辑/业务 :一般方式 √ (没有使用shiro的方式，在后来权限配置角色划分上出现很多麻烦)
                      优化登录逻辑：多次操作刷新过期时间（这个是自动的）√
                      rememberME功能实现(已经实现30分钟登录有效) √
        忘记密码：验证邮箱 √
                  发送邮件，重置密码 √
    
 4. 注册功能实现： √

        逻辑实现 :注册校验：重复用户名校验，密码、邮箱、电话号码校验。 √
    
 5. 博客页面： √

        博客主页：
            首页模块 ：
                        首页轮播图 √
                        首页新闻页：(只显示有限条数)
                                    数据抓取 √
                                    新闻列表 √
                                    新闻详情 √
            博客文章 : 文章列表页+分页ajax √
                       文章分类栏后台自动获取，分类分页功能完整 √
                                    分类新增：我的博客 √
                       文章私密隐藏 √
                       文章详细界面:显示详情 √
                                    评论 ：评论列表，分页 √
                                           作者权限：删除评论 √
                                                    回复评论 √
                                                    显示回复 √
                                                    点赞文章 √
                                           查看评论者详情页：跳转至某人信息页 √
                       博客编写页面：基本功能 √ 
                                    UEditor设置 √
                       个人信息页：信息展示 √
                                  修改页面 √
                                   某人信息页：某人信息（公开否）+某人文章 √
            
 6. 文章收藏： √
 
            详情页收藏：标记文章 √
            文章列表页提供收藏查看入口 √
                      
 7. 详情页分享：√
 
                分享插件 √
            
 8. 用户行为轨迹：  
 
                AOP编入所有controller √
                取得信息存入数据库 √
                行为查看、分析
                       
    
           
*bug
打包有错误，程序包xxx不存在
    解决步骤：BlogZ->clean->Reimport->启动
    已修复：将所有依赖集中在BlogZ的pom下，打包成功*


`tips`:

    1.默认静态资源访问在static路径下，所以引用时直接static后的路径。
    2.需要数据库的项目启动需要配置数据源信息，此项目用的Druid，相关配置在login的config下
    3.yml类型的文件在@configuration的读取时可能会读不出来，这里还是改用老式的properties型
    4.一般登录逻辑采用二次跳转：登录成功跳转页面，页面再次请求获取session，是否有潜在问题
    5.用的自己写的一套注册校验，在代码层面只要求三项不能为空，其他都属于页面校验，可能出现页面破解安全隐患
        已经升级为validation式注解验证机制，现只在方法上进行注解验证（刚开始设计登录注册机制的遗留问题），
        最好在实体类上做验证，可减少代码重复
    6.实现了一个比较原始的blog显示文章界面，用的ajax调用，返回list再进行html语言穿插和拼接，
        并不是很利于后期的维护和更改，比较麻烦
    7.添加了pagehelper分页插件，需要依赖、配置文件、service层和controller层多加代码，页面实现控制:
        页面用的ajax分页，需要拼接html，增加代码量
    8.引入了ueditor富文本编辑空间，官网上说不要用于生产环境~~，功能很多(static文件夹)，
        与springboot结合得并不是那么完美，需要手动写不少文件：
        https://www.cnblogs.com/liter7/p/7745606.html
        主要用到：config.json(springboot中用在了BlogConroller里)、ueditor.config.js(配置加载config.json)、BlogController.java、blogwrite.html(配置请求路径)
    9.引入toastr提示控件(static文件夹)，需结合jquery,且在jquery后使用
    10.springboot在每次启动后都会在本地重新创建路径，这一点对图片想保持到项目来说不好办啊，如果需要部署服务器，上传和显示图片
        方面还得改路径
    11.全局项目用的session传递用户信息(session在使用过程中注意有时候需要刷新同步数据)
    12.templates里一般用的是thymeleaf模板写的，static/page里一般用的ajax写的比较麻烦，但支持异步加载
    13.引入了pace.js自动进度条，需要导包pace,需要引用三行script头文件，
        get/post方法都可监控需在js文件之前引入
    14.数据库里存timestamp类型数据，可用String类型直接得到，传给前台时会自动进行时间格式转换
    15.评论模块用的是纯thymeleaf的方式进行分页及显示的，写起来比ajax的方式方便很多，但是在用户操作体验上没有异步方式好，所有的跳转都重新加载了页面，需要注意页面信息的传递
    16.后台管理员权限未规划（在人员表中加一个角色字段用于分权），导致管理权限缺失，这部分在权限框架里应该有很好的设定
    17.评论回复这个功能比想象中要复杂，因为多个回复对应的多个评论，最后还是用ajax多次通过传评论id去取对应的回复id来进行分类显示(将回复挂载在评论上)。这里做的功能只涉及一级回复。
    18.利用自动点击的方式回传参数获取回复评论
    19.点赞的功能考虑到实用和性能，做了一个只会加且可重复加的效果
    20.安全隐患：密码没有做加密处理
    21.因为启动项类是在web包里的，所以相关的配置文件应该也放在web包下的properties中
    22.引入redis缓存，set key and value 的方式存爬虫数据入缓存中，用scheduel定时爬去数据，set的key相同会覆盖上次value,达到更新数据的效果
    23.引入freemarker，maven包、properties即可
    24.不能用new直接实例化ContentPipline,需要用Autowire的方式注入才行，否则会报Nullpoint：
    因为ContentPipline中有springmvc注入的类，如果直接实例化，则没有用到依赖注入，所以ConetentPipline里的注入无效，导致这边也会nullpoint
    25.新闻页这块采用的WebMagic爬虫去爬取CSDN中首页的内容，存入redis缓存中，整合数据后直接用freemarker模板传回前台显示
    26.配置测试好了RabbitMQ,但还没有地方可以使用，相关内容在rabbitmq包里，配置文件在web里
        添加赞的功能加入了rabbitmq，
        尝试将刷新缓存加入队列，但发现request无法在队列接收方优雅的获取
        http://localhost:15672
    27.博客详情页初始设计时功能并没那么多，后面添加共那功能模块时候出现排版问题
    28.用js有点本末倒置了，多数逻辑判断应该放在java后台里去判断，最终只用传一个值给前台即可
    29.分享插件里微信弹出二维码显示不完整
    30.往往一些小的功能，不起眼的内容，需要的逻辑却不是那么简单
    31.利用AOP，编织代码入所有controller中，获取用户的行为轨迹，存入数据库
    32.将session中的数据存入了redis，便于在大数据量情况下多台服务器之间的session共享
        这里用到了设置了@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)过期时间为30分钟
        每次request请求会刷新过期时间
        （这里注意数据需要序列化才可存入redis）
    33.Druid数据源监控：http://localhost:8080/druid/login.html
        admin/admin
    34.添加密码重置功能，验证用户邮箱并发送验证码，redis存下验证码，验证通过即可重置密码。
    35.修复打包bug，将原来分散各个模块的依赖集中到总父模块中，打包即可成功，以前报错是报的包找不到，
        可能与此有些依赖找不到所以其他模块的包导不进有关。
    36.
        

