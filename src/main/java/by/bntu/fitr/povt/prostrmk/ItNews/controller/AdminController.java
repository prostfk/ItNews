package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/createArticle")
    public ModelAndView createArticleGet() {
        return new ModelAndView("createArticle", "article", new Article());
    }

    @PostMapping(value = "/createArticle")
    @ResponseBody
    public String createArticlePost(Article article, MultipartFile file) {
        articleDao.save(article, file);
        return "<i>success</i>";
    }

    @GetMapping(value = "/edit")
    public ModelAndView editArticleList(){
        List<Article> allReversed = articleDao.findAll();
        ModelAndView modelAndView = new ModelAndView("editArticles", "articles", allReversed);
        modelAndView.addObject("link", "/admin/edit-reversed");
        return modelAndView;
    }

    @GetMapping(value = "/edit-reversed")
    public ModelAndView editArticleListReversed(){
        List<Article> allReversed = articleDao.findAllReversed();
        ModelAndView modelAndView = new ModelAndView("editArticles", "articles", allReversed);
        modelAndView.addObject("link", "/admin/edit");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editArticle(@PathVariable String id){
        Article articleById = articleDao.findArticleById(Long.parseLong(id));
        if (articleById!=null) {
            return new ModelAndView("editArticle", "article", articleById);
        }
        return new ModelAndView("redirect:/");
    }

    @PostMapping(value = "/edit/{id}")
    public String updateArticlePost(@PathVariable String id, Article article, MultipartFile file){
        if (article!=null){
            try {
                if (!file.getOriginalFilename().equals("")){
                    article.setPathToFile(articleDao.saveFile(file));
                }else{
                    article.setPathToFile(articleDao.findArticleById(Long.parseLong(id)).getPathToFile());
                }
                articleDao.update(Long.parseLong(id), article);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "redirect:/admin";
    }

    @GetMapping(value = "")
    public ModelAndView getUsers(){
        List<User> all = userDao.findAll();
        return new ModelAndView("adminIndex","users",all);
    }

}
