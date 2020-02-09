package dev.bug.bankapp.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bug.bankapp.dto.BankDto;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.services.BankService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankController.class)
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createdBankTest() throws Exception {

        Bank bank = new Bank().setName("VTB");

        BankDto bankDto = new BankDto()
                .setBankId(100)
                .setName("VTB");

        when(bankService.createBank(any(Bank.class))).thenReturn(bankDto);

        mockMvc.perform(
                post("/banks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bank)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bankId").exists())
                .andExpect(jsonPath("$.name").value("VTB"));
    }

    @Test
    void getClientNames() throws Exception {
        long bankId = 100;
        when(bankService.getClientNames(bankId)).thenReturn(Collections.singletonList("Иванов Иван"));

        MvcResult result = mockMvc.perform(
                get("/banks/" + bankId + "/client_names")
        ).andExpect(status().is2xxSuccessful())
                .andReturn();
        List<String> names = objectMapper.readValue(
                result.getResponse().getContentAsByteArray(),
                new TypeReference<List<String>>() {
                });
        assertThat(names)
                .contains("Иванов Иван");
        assertThat(names.size())
                .isEqualTo(1);
    }

}