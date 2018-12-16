package by.bntu.fitr.povt.prostrmk.ItNews;

import by.bntu.fitr.povt.prostrmk.ItNews.config.ItNewsApplication;
import by.bntu.fitr.povt.prostrmk.ItNews.controller.IndexController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ItNewsApplication.class)
@AutoConfigureMockMvc
public class WebControllerTest {


    @Autowired
    private IndexController indexController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoadsSuccess() {
        assertThat(indexController).isNotNull();
    }

    @Test
    public void viewResolverStatusCheckShouldBe200() throws Exception {
        this.mockMvc.perform(get("/check/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Title")));
    }

    @Test
    public void findArticleByIdRestShouldBeOk() throws Exception {
        this.mockMvc.perform(get("/api/article/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1")));
    }

    @Test
    public void findArticleByIdRestShouldBeFailed() throws Exception {
        this.mockMvc.perform(get("/api/article/-1")).andDo(print()).andExpect(content().string(""));
    }

    @Test
    public void testCreateArticleShouldBeFoundButNotAccessed() throws Exception {
        this.mockMvc.perform(get("/admin/createArticle")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void testWrongUrlShouldReturn200WithErrorWindow() throws Exception {
        this.mockMvc.perform(get("/some/Wrong/Url")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testProcessUserShouldBeForbidden() throws Exception {
        this.mockMvc.perform(post("/admin/processUser/1")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void testSendPostRequestShouldBe404() throws Exception {
        this.mockMvc.perform(post("admin/createUser")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void testAnonInformationNull() throws Exception {
        this.mockMvc.perform(get("/api/checkRole")).andDo(print())
        .andExpect(content().string("{\"principal\":" +
                "\"anonymousUser\"," +
                "\"role\":\"\"," +
                "\"details\":\"org.springframework.security.web.authentication.WebAuthenticationDetails@957e: RemoteIpAddress: 127.0.0.1; SessionId: null\"}"));
    }



}
