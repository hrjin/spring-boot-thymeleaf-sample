package com.example.springboot.thymeleaf.sample.common;

import com.example.springboot.thymeleaf.sample.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * Rest Template Service 클래스
 *
 * @author hrjin
 * @version 1.0
 * @since 2021.08.06
 */
@Service
public class RestTemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateService.class);
    private static final String CONTENT_TYPE = "Content-Type";

    private final RestTemplate restTemplate;

    private String baseUrl = "http://dummy.restapiexample.com/api/v1";


    /**
     * Instantiates a new Rest template service
     * @param restTemplate                   the rest template
     */
    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public <T> T send(String reqUrl, HttpMethod httpMethod, Object bodyObject, Class<T> responseType) {
        return send(reqUrl, httpMethod, bodyObject, responseType, "application/json", "application/json");
    }



    /**
     * t 전송(Send t)
     *
     * @param <T>          the type parameter
     * @param reqUrl       the req url
     * @param httpMethod   the http method
     * @param bodyObject   the body object
     * @param responseType the response type
     * @param acceptType   the accept type
     * @param contentType  the content type
     * @return the t
     */
    public <T> T send(String reqUrl, HttpMethod httpMethod, Object bodyObject, Class<T> responseType, String acceptType, String contentType) {
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(CONTENT_TYPE, contentType);
        reqHeaders.add("ACCEPT", acceptType);
        reqHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
                        " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<Object> reqEntity;
        if (bodyObject == null) {
            reqEntity = new HttpEntity<>(reqHeaders);
        } else {
            reqEntity = new HttpEntity<>(bodyObject, reqHeaders);
        }

        LOGGER.info("<T> T SEND :: REQUEST: {} BASE-URL: {}, CONTENT-TYPE: {}", httpMethod, reqUrl, reqHeaders.get(CONTENT_TYPE));
        ResponseEntity<T> resEntity = null;
        try {
            LOGGER.info("Full Url ::: " + baseUrl + reqUrl);
            resEntity = restTemplate.exchange(baseUrl + reqUrl, httpMethod, reqEntity, responseType);
            LOGGER.info("resEntity.toString() ::: " + resEntity.toString());
            LOGGER.info("resEntity.getBody() ::: " + resEntity.getBody());
        } catch (HttpStatusCodeException exception) {
            LOGGER.info("HttpStatusCodeException API Call URL : {}, errorCode : {}, errorMessage : {}", reqUrl, exception.getRawStatusCode(), exception.getMessage());
            throw new MyException(exception.getRawStatusCode(), exception.getMessage());
        }

        if (resEntity != null) {
            LOGGER.info("RESPONSE-TYPE: {}", resEntity.getBody().getClass());
        } else {
            LOGGER.info("RESPONSE-TYPE: RESPONSE BODY IS NULL");
        }

        return resEntity.getBody();
    }
}
