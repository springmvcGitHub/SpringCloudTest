import cn.cjn.test2.config.App;
import cn.cjn.test2.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author: 东星耀阳
 * @Description:
 * @Date: 2018-08-31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class FeignTest {

    private MockMvc mvc;

    @Before
    public void set(){
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testHello() throws Exception {
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(String.valueOf(equals("11"))));
        System.out.println(result);
    }

}
