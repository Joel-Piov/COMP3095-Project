/*********************************************************************************
 * Project: COMP3095 - Assignment 2
 * Assignment: Assignment 2
 * Author(s):       Joel Piovesan     Rachel Titco
 * Student Number:  101221909         101214347
 * Date: 08/11/2020
 * Description: Provides error code messages to be returned by the RecaptchaService. Followed a tutorial online.
 *********************************************************************************/
package com.assignment2.COMP3095.services;

import java.util.HashMap;
import java.util.Map;

public class RecaptchaUtil {

    public static final Map<String, String> RECAPTCHA_ERROR_CODE = new HashMap<>();
    static {
        RECAPTCHA_ERROR_CODE.put("missing-input-secret",
                "The secret parameter is missing");
        RECAPTCHA_ERROR_CODE.put("invalid-input-secret",
                "The secret parameter is invalid or malformed");
        RECAPTCHA_ERROR_CODE.put("missing-input-response",
                "The response parameter is missing");
        RECAPTCHA_ERROR_CODE.put("invalid-input-response",
                "The response parameter is invalid or malformed");
        RECAPTCHA_ERROR_CODE.put("bad-request",
                "The request is invalid or malformed");
    }
}