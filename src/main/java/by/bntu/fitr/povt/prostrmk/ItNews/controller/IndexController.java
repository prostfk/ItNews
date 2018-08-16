package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationPage() {
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/registration")
    public String savePerson(User user) {
        if (userDao.validateUser(user)) {
            user.setPassword(encoder.encode(user.getPassword()));
            userDao.save(user);
        }
        return "redirect:/me";
    }

    @GetMapping(value = "/auth")
    public ModelAndView getAuthPage() {
        return new ModelAndView("auth", "user", new User());
    }


    @GetMapping(value = "/")
    public ModelAndView getIndexPage() {
        return new ModelAndView("indexTest", "articles", articleDao.findAllReversed());
    }

    @GetMapping(value = "/search")
    public ModelAndView search(@RequestParam(name = "searchString") String searchString) {
        List<Article> articles = articleDao.findArticlesWhereTitleLikeIgnoreCase(searchString);
        return new ModelAndView("indexTest", "articles", articles);
    }


    //    AJAX
    @GetMapping(value = "/checkPassword")
    @ResponseBody
    public String checkPassword(String password) {
        if (password.length() < 3) {
            return "Weak password";
        } else if (password.length() > 3 && password.length() < 6) {
            return "Good password";
        } else {
            return "Perfect password";
        }
    }

    @GetMapping(value = "/checkUsername")
    @ResponseBody
    public String checkUsername(String username) {
        if (userDao.findUserByUsername(username) == null && username.length() > 3) {
            return "Good username";
        } else {
            return "User with such username already exists";
        }
    }


}
