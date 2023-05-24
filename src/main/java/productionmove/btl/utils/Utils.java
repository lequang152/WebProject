package productionmove.btl.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import productionmove.btl.entity.CategoryProduct;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Random;

public class Utils {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;


    private static Random generator = new Random();

    public static String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public static Date formatDate(Date date) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        return (Date) fm.parse(String.valueOf(date));
    }

    public static boolean checkDate(String start, String end, String date) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dstart = fm.parse(start);
        java.util.Date dend = fm.parse(end);
        java.util.Date ddate = fm.parse(date);
        if(ddate.equals(dstart)) {
            return true;
        } else if(ddate.equals(dend)){
            return true;
        } else if(ddate.after(dstart) && ddate.before(dend)){
            return true;
        } else {
            return false;
        }
    }

    public static void toJSON(List<CategoryProduct> list){
        JSONArray arr = new JSONArray();
        for(CategoryProduct categoryProduct: list){
            JSONObject details = new JSONObject();
            details.put("category", categoryProduct.getCategory());
            details.put("name", categoryProduct.getName());
            details.put("quantity", categoryProduct.getQuantity());
            details.put("price", categoryProduct.getPrice());
            JSONObject object = new JSONObject();
            object.put("categoryproduct",  details);
            arr.add(object);
        }
        try {
            FileWriter file = new FileWriter("src/main/resources/static/assets/js/data.json");
            file.write(arr.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray toJSONArr(List<CategoryProduct> list){
        JSONArray arr = new JSONArray();
        for(CategoryProduct categoryProduct: list){
            JSONObject details = new JSONObject();
            details.put("category", categoryProduct.getCategory());
            details.put("name", categoryProduct.getName());
            details.put("quantity", categoryProduct.getQuantity());
            details.put("price", categoryProduct.getPrice());
            JSONObject object = new JSONObject();
            object.put("categoryproduct",  details);
            arr.add(object);
        }
        return arr;
    }
}
