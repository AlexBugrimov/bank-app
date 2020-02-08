package dev.bug.bankapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bug.bankapp.dto.BankDto;
import dev.bug.bankapp.model.Bank;
import dev.bug.bankapp.services.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankController.class)
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

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
                        .content(new ObjectMapper().writeValueAsString(bank)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bankId").exists())
                .andExpect(jsonPath("$.name").value("VTB"));
    }

    @Test
    void getClientNames() throws Exception {
    }
}