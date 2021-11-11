package name.alp.sbwebtesttemplate;

import name.alp.sbwebtesttemplate.dbo.Classification;
import name.alp.sbwebtesttemplate.dbo.ClassificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SbWebTestTemplateApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private ClassificationRepository classificationRepository;

    @Test()
    @Tag("UnitTest")
    @DisplayName("POST /api/classification")
    public void postClassification() {
        Classification classification = new Classification(
                "0201", "02", "Suspicious behaviour", "PS", "0201", 1
        );

        Classification savedClassification = new Classification(
                "0201", "02", "Suspicious behaviour", "PS", "0201", 1
        );

        // Mock Repository
        when(classificationRepository.save(classification)).thenReturn(savedClassification);
        when(classificationRepository.existsById("02")).thenReturn(true);
        when(classificationRepository.existsById("0201")).thenReturn(true);


        HttpEntity<Classification> request = new HttpEntity<>(classification);
        ResponseEntity<Classification> result = testRestTemplate.exchange("/api/classification/",
                HttpMethod.POST, request, new ParameterizedTypeReference<Classification>() {
                });

        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());

        //Verify response contains correct id
        Assertions.assertEquals("0201", result.getBody().getId());
    }

}
