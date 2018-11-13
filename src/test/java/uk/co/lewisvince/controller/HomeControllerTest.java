package uk.co.lewisvince.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeControllerTest {

    @Test
    public void testHomeGetRequestReturnsCorrectHomeMessage() {
        HomeController homeController = new HomeController();
        String result = homeController.home();
        assertEquals("Home Page", result);
    }
}
