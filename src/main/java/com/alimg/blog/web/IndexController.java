package com.alimg.blog.web;

import com.alimg.blog.dto.Result;
import com.alimg.blog.dto.TagCloudExecution;
import com.alimg.blog.entity.Article;
import com.alimg.blog.entity.Item;
import com.alimg.blog.entity.User;
import com.alimg.blog.service.ArticleService;
import com.alimg.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String list(HttpServletRequest req,Model model) {

        List<Article> topList = articleService.getTopList(0);

        List<Item> items = itemService.getList();

        int articleCount = articleService.getArticleCount(0,"");

        User user = (User)req.getSession().getAttribute("user");

        List<TagCloudExecution> tags = tagService.getTagCloud();

        List<Article> notice = articleService.getList(0,3,8,"");

        model.addAttribute("itemList", items);

        model.addAttribute("articleTopList", topList);

        model.addAttribute("articleTopList", topList);

        model.addAttribute("tagsCloud", tags);

        model.addAttribute("articleCount", articleCount);

        model.addAttribute("notice", notice);

        return "index";
    }

    @RequestMapping(value = "/page/article/{page}", method = RequestMethod.POST,produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    private Result<List<Article>> pageArticle(@PathVariable("page") Integer page, @RequestParam("item") int item, @RequestParam("search") String search){

        if (page == null) {
            return new Result<List<Article>>(false,"参数错误");
        }
        try {
            List<Article> articleList = articleService.getList(8*(page-1),8,item,search);

            return new Result<List<Article>>(true,articleList);
        }catch (Exception e){
            return new Result<List<Article>>(false,"查询异常");
        }
    }
}
