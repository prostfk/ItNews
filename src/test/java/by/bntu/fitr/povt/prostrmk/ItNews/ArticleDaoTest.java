package by.bntu.fitr.povt.prostrmk.ItNews;


import by.bntu.fitr.povt.prostrmk.ItNews.config.ItNewsApplication;
import by.bntu.fitr.povt.prostrmk.ItNews.model.entity.Article;
import by.bntu.fitr.povt.prostrmk.ItNews.repository.ArticleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ItNewsApplication.class)
//@Transactional
@WebAppConfiguration
public class ArticleDaoTest {

    @Autowired
    private ArticleRepository articleRepository;

    private Pageable pageable = PageRequest.of(1,5);


    @Test
    public void findArticlesByTypeCorrect() {
        List<Article> programming = articleRepository.findArticlesByType("programming", pageable);
        programming.forEach(article -> {
            assertEquals("programming", article.getType());
        });
        assertNotSame(Collections.EMPTY_LIST, programming);
    }

    @Test
    public void findArticlesByTypeIncorrect() {
        List<Article> programming = articleRepository.findArticlesByType("famous",pageable);
        assertEquals(Collections.EMPTY_LIST, programming);
    }

    @Test
    public void findAll() {
        List<Article> all = articleRepository.findAll();
        assertNotSame(Collections.EMPTY_LIST, all);
        all.forEach(Assert::assertNotNull);
    }

    @Test
    public void findArticleByIdCorrect() {
        Article articleById = articleRepository.findArticleById(1L);
        long id = 1;
        long articleBaseId = articleById.getId();
        assertEquals(id, articleBaseId);
    }

    @Test
    public void findArticleByIdIncorrect() {
        Article articleById = articleRepository.findArticleById(null);
        assertNull(articleById);
    }


}
