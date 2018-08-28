package by.bntu.fitr.povt.prostrmk.ItNews.controller;

import by.bntu.fitr.povt.prostrmk.ItNews.dao.ArticleDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.MessageDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dao.UserPostsDao;
import by.bntu.fitr.povt.prostrmk.ItNews.dto.UserDto;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Message;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Secured("ROLE_USER")
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired private UserDao userDao;

    @Autowired private ArticleDao articleDao;

    @Autowired private UserPostsDao userPostsDao;

    @Autowired private MessageDao messageDao;

    @GetMapping(value = "/me")
    public ModelAndView getUserPage(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userDao.findUserByUsername(name);
        UserDto user;
        if (userEntity!=null){
            user = new UserDto(userEntity, userPostsDao.findArticlesByUser(userEntity.getId()));
        }else{
            return new ModelAndView("redirect:/auth");
        }
        return new ModelAndView("userPage", "user", user);
    }


    @GetMapping(value = "/addArticleToMe/{id}")
    public String addArticleToMyCollection(@PathVariable("id") String idValue){
        long id = Long.parseLong(idValue);
        User user = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (!userPostsDao.findArticlesByUser(user.getId()).contains(articleDao.findArticleById(id))){
            userPostsDao.save(user.getId(), id);
        }
        return "redirect:/user/me";
    }

    @GetMapping(value = "/deleteMyArticle/{id}")
    public String deleteMyArticle(@PathVariable("id")String idValue){
        long id = Long.parseLong(idValue);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = userDao.findUserByUsername(name);
        if (userByUsername!=null){
            userPostsDao.delete(userByUsername.getId(), id);
        }
        return "redirect:/user/me";
    }

    @GetMapping(value = "/im")
    public ModelAndView dialogs(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findUserByUsername(name);
        List<String> conversations = messageDao.findConversations(user.getId());
        return new ModelAndView("im", "conversations", conversations);
    }

    @GetMapping(value = "/sendMessageTo{username}")
    public ModelAndView sendMessage(@PathVariable("username") String username){
        User receiver = userDao.findUserByUsername(username);
        if (receiver!=null){
            User sender = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            List<Message> messages = messageDao.findMessagesBySenderAndReceiver(sender.getId(), receiver.getId());
            ModelAndView modelAndView = new ModelAndView("sendMessageTo", "messages", messages);
            modelAndView.addObject("receiver", receiver.getUsername());
            modelAndView.addObject("senderId", sender.getId());
            return modelAndView;
        }
        return new ModelAndView("redirect:/user");
    }

    @PostMapping(value = "/sendMessageTo{username}")
    public String sendPostMessage(@PathVariable("username") String username, @RequestParam("messageText") String text){
        if (text.equals("")){
            return "redirect:/error?wrong+message";
        }
        User receiver = userDao.findUserByUsername(username);
        User sender = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        messageDao.save(new Message(
                sender.getId(),receiver.getId(),text
        ));
        return "redirect:/user/sendMessageTo" + username;
    }

    @GetMapping(value = "/im/search")
    public ModelAndView searchUser(@RequestParam("searchUser") String username){
        List<User> users = userDao.findUsersByUsernameLike(username);
        return new ModelAndView("userSeach", "users", users);
    }

//    AJAX

    @PostMapping(value = "/user/update")
    public String updateMessage(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> messagesBySenderId = messageDao.findMessagesBySenderId(userDao.findUserByUsername(username).getId());
        Message message = messagesBySenderId.get(messagesBySenderId.size() - 1);
        return message.getText();
    }

}
