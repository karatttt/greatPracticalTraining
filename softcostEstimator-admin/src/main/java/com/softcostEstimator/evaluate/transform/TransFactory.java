package com.softcostEstimator.evaluate.transform;

public class TransFactory {
    public static TransUtil newInstance(String type){
        switch (type){
            case "html":
                return new TransToHtml();
            case "word":
                return new TransToWord();
            default:
                return new TransToWord();
        }
    }
}
