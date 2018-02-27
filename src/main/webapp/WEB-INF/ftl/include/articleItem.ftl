<li>
    <a href="/u/${article.authorId}" class="fly-avatar"> <img src="${article.user.img}" alt="${article.user.name}"> </a>
    <h2>
    <#list article.tag as tag>
        <a class="layui-badge" href="/t/${tag.name}">
        ${tag.name}
        </a>
    </#list>
        <a href="/a/${article.id}">${article.title}</a> </h2>
    <div class="fly-list-info">
        <a href="/u/${article.authorId}" link="">
            <cite>${article.user.name}</cite>
            <i class="iconfont icon-renzheng" title="认证信息：${article.user.info}"></i>
        </a> <span>5天前</span>
        <span class="fly-list-kiss layui-hide-xs" title="阅读量">
                                <i class="iconfont icon-liulanyanjing"></i> ${article.click}
                            </span>
    <#--<span class="fly-list-nums"> <i class="iconfont icon-pinglun1" title="回答"></i> 92 </span>-->
    </div>
    <div class="fly-list-badge"> </div>
</li>