package com.example.demo.entity;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HttpResponseTests {

    public HttpResponse httpResponse;
    public static final int HTTP_STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();
    public static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    public static final String REASON = "This is an internal server error";
    public static final String MESSAGE = "An error occurred while processing the request";

    @BeforeEach
    void setUp(){
        httpResponse = new HttpResponse(HTTP_STATUS_CODE, HTTP_STATUS, REASON, MESSAGE);
    }

    @Test
    public void getHttpStatusCodeTest()
    {
        Assert.assertEquals(HTTP_STATUS_CODE, httpResponse.getHttpStatusCode());
    }

    @Test
    public void setHttpStatusCodeTest()
    {
        //arrange
        int newStatusCode = HttpStatus.FORBIDDEN.value();
        //act
        httpResponse.setHttpStatusCode(newStatusCode);
        //assert
        Assert.assertEquals(newStatusCode, httpResponse.getHttpStatusCode());
    }

    @Test
    public void getHttpStatusTest()
    {
        Assert.assertEquals(HTTP_STATUS, httpResponse.getHttpStatus());
    }

    @Test
    public void setHttpStatusTest()
    {
        //arrange
        HttpStatus newStatus = HttpStatus.FORBIDDEN;
        //act
        httpResponse.setHttpStatus(newStatus);
        //assert
        Assert.assertEquals(newStatus, httpResponse.getHttpStatus());
    }

    @Test
    public void getReasonTest()
    {
        Assert.assertEquals(REASON, httpResponse.getReason());
    }

    @Test
    public void setReasonTest()
    {
        //arrange
        String newReason = "This is a new reason";
        //act
        httpResponse.setReason(newReason);
        //assert
        Assert.assertEquals(newReason, httpResponse.getReason());
    }

    @Test
    public void getMessageTest()
    {
        Assert.assertEquals(MESSAGE, httpResponse.getMessage());
    }

    @Test
    public void setMessageTest()
    {
        //arrange
        String newMessage = "This is a new message";
        //act
        httpResponse.setMessage(newMessage);
        //assert
        Assert.assertEquals(newMessage, httpResponse.getMessage());
    }
}
