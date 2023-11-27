package es.leanmind.eitherspringboot.infrastructure;

import es.leanmind.eitherspringboot.domain.ApplicationService;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class ControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService appService;

    @Test
    public void returnBadRequestWhenServiceReturnsLeft() throws Exception {
        Mockito.when(appService.wrap(any(), any()))
                .thenReturn(Either.left("error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/wrap-text")
                .param("text", "some text")
                .param("columnWidth", "wrong!"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("error"));
    }

    @Test
    public void returnOKWhenServiceReturnsRight() throws Exception {
        Mockito.when(appService.wrap(any(), any()))
                .thenReturn(Either.right("hello\nworld"));

        mockMvc.perform(MockMvcRequestBuilders.get("/wrap-text")
                        .param("text", "hello world")
                        .param("columnWidth", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello\nworld"));
    }
}
