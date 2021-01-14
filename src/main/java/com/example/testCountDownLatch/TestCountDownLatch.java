package com.example.testCountDownLatch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        TestCountDownLatch testCountDownLatch=new TestCountDownLatch();
        testCountDownLatch.test();
    }
    public void test(){
        //初始化计数器值为1
        CountDownLatch countDownLatch=new CountDownLatch(1);
        for(int i=0;i<200;i++){//创建200个线程
            new Thread(new Run(countDownLatch)).start();

        }
        //计数器减1，所有线程释放，开始并发访问
        countDownLatch.countDown();
    }
    private class Run implements Runnable{
        private final CountDownLatch countDownLatch;
        public Run(CountDownLatch countDownLatch){
            this.countDownLatch=countDownLatch;
        }
        public void run(){
            try {
                countDownLatch.await();//所有线程等待，直到计数器为0
                //ResponseEntity<String> result=restTemplate.postForEntity("http://172.16.9.23:8080/lims_v2/qiyeweixin/getUserList","",String.class);
                /*String urlPath="http://172.16.9.23:8080/lims_v2/qiyeweixin/getUserList";
                List<String> list=new ArrayList<>();
                list.add("zhou.peng@genecast.com.cn");
                list.add("liu.xing@genecast.com.cn");
                //list.add("zhou.bapi@genecast.com.cn");
                //list.add("zhansannnn@genecast.com.cn");
                String stringList= String.join(",",list);
                JSONObject paramJson=new JSONObject();
                paramJson.put("userInfo",stringList);
                String paramString=paramJson.toString();
                System.out.println("paramString"+paramString);
                String result = "";
                BufferedReader in = null;
                HttpURLConnection conn=null;
                try {
                    URL url = new URL(urlPath);
                    conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    // 设置文件类型:
                    conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                    conn.setRequestProperty("accept","application/json");
                    //发送数据
                    if (paramString != null) {
                        //byte[] writebytes = param.getBytes();
                        // 设置文件长度
                        conn.setRequestProperty("Content-Length", String.valueOf(paramString.getBytes("UTF-8").length));
                        OutputStream outwritestream = conn.getOutputStream();
                        outwritestream.write(paramString.getBytes("UTF-8"));
                        outwritestream.flush();
                        outwritestream.close();
                    }
                    in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                    String line;
                    while ((line = in.readLine()) != null) {
                        result += line;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                conn.disconnect();
                JSONObject resultJson=JSONObject.parseObject(result);
                System.out.println("result:"+resultJson.get("newUserInfo"));*/


                String string="http://localhost:8088/info/getEmpById?id=1";
                String result="";
                URL url = null;
                try {
                    url = new URL(string);
                    System.out.println("url:"+url);
                    //打开连接
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                    // 设置通用的请求属性
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type","text/plain;charset=UTF-8");
                    connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9, image/webp,image/apng,*/*;q=0.8");
                    connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
                    connection.setRequestProperty("cache-control", "max-age=0");
                    connection.setRequestProperty("connection", "Keep-Alive");
                    connection.setRequestProperty("Accept-Charset", "UTF-8");
                    connection.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.　　　　　　89 Safari/537.36");
                    // 建立实际的连接
                    connection.connect();

                    // 定义 BufferedReader输入流来读取URL的响应
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));


                    //写出数据
                    String line;
                    while ((line = in.readLine()) != null) {
                        result += line;
                    }
                    //关流
                    in.close();
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("result:"+result);

            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            }

        }

    }

}
