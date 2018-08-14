package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.CommentDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @GetMapping(value = "/article/{id}")
    public ModelAndView checkArticle(@PathVariable String id){
        long l_id = Long.parseLong(id);
        Article articleById = articleDao.findArticleById(l_id);
        return new ModelAndView("singleArticle", "article", articleById);
    }

    @Secured("ROLE_USER")
    @PostMapping(value = "/article/{id}/comment")
    public String addComment(@PathVariable String id, @RequestParam("comment-content")String comment){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name!=null){
            Long long_id = Long.parseLong(id);
            Comment com = new Comment(name,comment,new Date().toString(),long_id);
            commentDao.save(com);
        }
        return "redirect:/article/" + id;
    }

}
