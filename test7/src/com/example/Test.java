package com.example;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Test {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/gifts.json", new MyHandler());
        server.createContext("/index.html", new IndexHandler());
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "{\n" +
                    "  \"gifts\":[\n" +
                    "         {\"name\":\"gift1\",\"price\":10,\"size\":\"10cm*10cm\",\"color\":\"red\"},\n" +
                    "         {\"name\":\"gift2\",\"price\":20,\"size\":\"20cm*10cm\",\"color\":\"green\"},\n" +
                    "         {\"name\":\"gift3\",\"price\":30,\"size\":\"30cm*10cm\",\"color\":\"yellow\"}\n" +
                    "         ]\n" +
                    "}";

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class IndexHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
//            String response = "<!DOCTYPE html>\n" +
//                    "<html>\n" +
//                    "<body>\n" +
//                    "\n" +
//                    "<h2>Use the XMLHttpRequest to get the content of a file.</h2>\n" +
//                    "<p>The content is written in JSON format, and can easily be converted into a JavaScript object.</p>\n" +
//                    "\n" +
//                    "<p id=\"demo\"></p>\n" +
//                    "\n" +
//                    "<script>\n" +
//                    "\n" +
//                    "var xmlhttp = new XMLHttpRequest();\n" +
//                    "xmlhttp.onreadystatechange = function() {\n" +
//                    "    if (this.readyState == 4 && this.status == 200) {\n" +
////                    "console.log(this.responseText); var myObj = JSON.parse('{}');\n" +
//
//                    "        var myObj = JSON.parse(this.responseText);\n" +
//                    "        document.getElementById(\"demo\").innerHTML = \"this is our gifts.json <br>\";\n" +
//                    "        document.getElementById(\"demo\").innerHTML += myObj.gifts[0].name + \", price:\" + myObj.gifts[0].price + \"<br>\";\n" +
//                    "        document.getElementById(\"demo\").innerHTML += myObj.gifts[1].name + \", price:\" + myObj.gifts[1].price + \"<br>\";\n" +
//                    "        document.getElementById(\"demo\").innerHTML += myObj.gifts[2].name + \", price:\" + myObj.gifts[2].price + \"<br>\";\n" +
////                    "        document.getElementById(\"demo\").innerHTML += \", price:\" + \"<br>\";\n" +
//                    "    }\n" +
//                    "};\n" +
//                    "xmlhttp.open(\"GET\", \"gifts.json\", true);\n" +
//                    "xmlhttp.send();\n" +
//                    "\n" +
//                    "</script>\n" +
//                    "\n" +
//                    "<p>Take a look at <a href=\"json_demo.txt\" target=\"_blank\">json_demo.txt</a></p>\n" +
//                    "\n" +
//                    "</body>\n" +
//                    "</html>\n";

            String response = null;
            try {
                String content = new Scanner(new File("src/com/example/index.html")).useDelimiter("\\Z").next();
                response = content;
                //            System.out.println(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println("Working Directory = " +
//                    System.getProperty("user.dir"));
//            System.out.println("test");

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}