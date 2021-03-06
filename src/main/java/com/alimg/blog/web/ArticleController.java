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
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/a/{id}", method = RequestMethod.GET)
    private String article(@PathVariable("id") int id,Model model) {
        List<Article> topList = articleService.getTopList(0);

        List<Item> items = itemService.getList();
        Article article = articleService.getArticle(id);

        List<TagCloudExecution> tags = tagService.getTagCloud();

        List<Article> notice = articleService.getList(0,3,8,"");

        articleService.updateArticleClick(id);

        model.addAttribute("itemList", items);
        model.addAttribute("article", article);
        model.addAttribute("articleTopList", topList);
        model.addAttribute("tagsCloud", tags);
        model.addAttribute("notice", notice);

        return "article";
    }

}
