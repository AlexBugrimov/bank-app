package dev.bug.bankapp.integrations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = {"classpath:test_data/banks.sql", "classpath:test_data/clients.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BankRestTest extends BaseTest {

    @Test
    public void getClientNamesByBankId() throws Exception {
        String bankId = "1020";

        List<String> names = getListApi("/banks/" + bankId + "/client_names");
        assertThat(names.size())
                .isEqualTo(2);
        assertThat(names)
                .contains("Иван Хабаров", "Александр Бугримов");
    }
}
