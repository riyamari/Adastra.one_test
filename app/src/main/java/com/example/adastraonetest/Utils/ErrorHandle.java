package com.example.adastraonetest.Utils;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class ErrorHandle {
    Context cntxt;

    public ErrorHandle(Context cntxt) {
        this.cntxt = cntxt;

    }

    public String respMessage(int respCode) {
        String message = "";
        switch (respCode) {
                case 200:
                    message = "success";
                    break;
                case 404:
                    message = "not found";
                    break;
                case 500:
                    message = "server broken";
                    break;

                default:
                    message = "unknown error";
                    break;
            }
            return message;
    }
    }
