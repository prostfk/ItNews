package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import by.bntu.fitr.povt.prostrmk.ItNews.model.util.FileUtil;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.ArticleRepository;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/createArticle")
    public ModelAndView createArticleGet() {
        return new ModelAndView("createArticle", "article", new Article());
    }

    @PostMapping(value = "/createArticle")
    @ResponseBody
    public String createArticlePost(Article article, MultipartFile file) {
        FileUtil.saveFile(file);
        articleRepository.save(article);
        return "<i>success</i>";
    }

    @GetMapping(value = "/edit")
    public ModelAndView editArticleList(){
        List<Article> allReversed = articleRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("editArticles", "articles", allReversed);
        modelAndView.addObject("link", "/admin/edit-reversed");
        return modelAndView;
    }

    @GetMapping(value = "/edit-reversed")
    public ModelAndView editArticleListReversed(){
        List<Article> allReversed = articleRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("editArticles", "articles", allReversed);
        modelAndView.addObject("link", "/admin/edit");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editArticle(@PathVariable String id){
        Article articleById = articleRepository.findArticleById(Long.parseLong(id));
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
                    article.setPathToFile(FileUtil.saveFile(file));
                }else{
                    article.setPathToFile(articleRepository.findArticleById(Long.parseLong(id)).getPathToFile());
                }
                articleRepository.save(article);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/")
    public ModelAndView getUsers(Pageable pageable){
        List<User> all = userRepository.findAll(pageable);
        return new ModelAndView("adminIndex","users",all);
    }

    @PostMapping(value = "/processUser/{id}")
    public String processUser(@PathVariable("id") String userId){
        long id = Long.parseLong(userId);
        User userById = userRepository.findUserById(id);
        userById.setStatus(!userById.getStatus());
        userRepository.save(userById);
        return "redirect:/admin";
    }


}
