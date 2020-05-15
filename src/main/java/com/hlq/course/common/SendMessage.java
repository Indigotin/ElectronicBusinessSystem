package com.hlq.course.common;

import com.alibaba.fastjson.JSON;
import com.hlq.course.model.TempItemCart;
import com.hlq.course.model.TempOrderItem;
import com.hlq.course.pojo.Item;
import com.hlq.course.pojo.ItemCart;
import com.hlq.course.pojo.OrderItem;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Len on 2020-05-14
 */
public class SendMessage {
    // 传递信息
    public String sendMessageAddItem(String data) {
        String ip = "129.204.184.133";        // 设置发送地址和端口号  129.204.184.133
        int port = 9999;
        Socket socket = null;
        try {
            // 连接服务器
            socket = new Socket(ip, port);
            System.out.println("server is start ......");
            //输出流
            OutputStream Out = socket.getOutputStream();
            Out.write("START".getBytes()); //发送数据
            //Out.flush();
            Thread.sleep(20);
            Out.write("ADD".getBytes());
            //Out.flush();
            Thread.sleep(20);
            Out.write(data.getBytes()); //发送数据
            /*//Out.flush();
            Thread.sleep(30);
            Out.write("END".getBytes());
            //Out.flush();
            Thread.sleep(30);*/
            //输入流
            BufferedReader In = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           // DataInputStream In = new DataInputStream(socket.getInputStream());
            String result = In.readLine();
            System.out.println(result);
            return result;

        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "defeat";
    }

    public String sendMessageCart(List<ItemCart> clist,Integer userId){
        String ip = "129.204.184.133";        // 设置发送地址和端口号  129.204.184.133
        int port = 9999;
        Socket socket = null;
        List<TempItemCart> list = new ArrayList<>();
        for (ItemCart itCart:clist) {
            TempItemCart t =  new TempItemCart();
            t.setItemId(itCart.getItemId());
            t.setUserId(itCart.getUserId());
            list.add(t);
        }
        //String data = JSON.toJSONString(list);
        System.out.println("clist"+JSON.toJSONString(list));
        try {
            // 连接服务器
            socket = new Socket(ip, port);
            System.out.println("server is start ......");
            //输出流
            OutputStream Out = socket.getOutputStream(); // 发送数据
            //DataOutputStream Out = new DataOutputStream(socket.getOutputStream());
            Out.write("START".getBytes()); //发送数据
            Out.flush();
            Thread.sleep(20);
            Out.write("CART".getBytes());
            Thread.sleep(20);
            Out.flush();
            for (TempItemCart t:list) {
                Out.write(JSON.toJSONString(t).getBytes());
                Thread.sleep(20);
                Out.flush();
            }
            Out.write("END".getBytes());
            Out.flush();
            Thread.sleep(20);
            //输入流
            BufferedReader In = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // DataInputStream In = new DataInputStream(socket.getInputStream());
            String result = In.readLine();
            System.out.println(result);
            return result;

        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "defeat";
    }

    public String sendOrderMessage(List<OrderItem> olist){
        String ip = "129.204.184.133";        // 设置发送地址和端口号  129.204.184.133
        int port = 9999;
        Socket socket = null;
        List<TempOrderItem> list = new ArrayList<>();
        for (OrderItem orItem:olist) {
            TempOrderItem t =  new TempOrderItem();
            t.setId(orItem.getId());
            t.setItemId(orItem.getItemId());
            t.setUserId(orItem.getUserId());
            list.add(t);
        }
        //String data = JSON.toJSONString(list);
        System.out.println("olist"+JSON.toJSONString(list));
        try {
            // 连接服务器
            socket = new Socket(ip, port);
            System.out.println("server is start ......");
            //输出流
            OutputStream Out = socket.getOutputStream(); // 发送数据
            //DataOutputStream Out = new DataOutputStream(socket.getOutputStream());
            Out.write("START".getBytes()); //发送数据
            Out.flush();
            Thread.sleep(20);
            Out.write("ORDER".getBytes());
            Thread.sleep(20);
            Out.flush();
            for (TempOrderItem t:list) {
                Out.write(JSON.toJSONString(t).getBytes());
                Thread.sleep(20);
                Out.flush();
            }
            Out.write("END".getBytes());
            Out.flush();
            Thread.sleep(20);
            //输入流
            BufferedReader In = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // DataInputStream In = new DataInputStream(socket.getInputStream());
            String result = In.readLine();
            System.out.println(result);
            return result;

        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "defeat";
    }
    /*public String sendMessage(byte[] header, byte[] data) {
        String ip = "129.204.184.133";        // 设置发送地址和端口号  129.204.184.133
        int port = 9998;
        Socket socket = null;
        try {
            // 连接服务器
            socket = new Socket(ip, port);
            System.out.println(" server is start ......");
            // 获取输入流
            InputStream in = socket.getInputStream();   //读取数据
            // 获取输出流
            OutputStream out = socket.getOutputStream(); // 发送数据
            // 包装输入流，输出流，包装一下可以直接传输字符串，不包装的话直接使用InputStream和OutputStream只能直接传输byte[]类型数据
            BufferedReader inRead = new BufferedReader(new InputStreamReader(in));
//			PrintWriter outWriter = new PrintWriter(out);

            // 发送数据
            // out.write(header);
            out.write(data);

            // 接受应答
            String result = inRead.readLine();  // 使用了包装后的输入流方便读取消息
            return result;
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "defeat";
    }*/


    public static void main(String[] args){
        Item item = new Item();
        item.setId(0);
        item.setCid(2);
        item.setName("test1");
        item.setSellPoint("iiiii");
        item.setPrice(BigDecimal.valueOf(1000L));
        SendMessage smg = new SendMessage();
        String result = smg.sendMessageAddItem(JSON.toJSONString(item));
        System.out.println("result:"+result);
    }

    /*public static void main(String[] args){
        Item item = new Item();
        item.setId(0);
        item.setCid(2);
        item.setName("test1");
        item.setSellPoint("iiiii");
        item.setPrice(BigDecimal.valueOf(1000L));
        SendMessage smg = new SendMessage();
        System.out.println("result:"+JSON.toJSONString(item));
    }*/

}

/*
public class SendMessage {
    // 传递信息
    public String sendMessage(byte[] header, byte[] data) {
        String ip = "129.204.184.133";        // 设置发送地址和端口号  129.204.184.133
        int port = 9998;
        Socket socket = null;
        try {
            // 连接服务器
            socket = new Socket(ip, port);
            System.out.println(" server is start ......");
            // 获取输入流
            InputStream in = socket.getInputStream();   //读取数据
            // 获取输出流
            OutputStream out = socket.getOutputStream(); // 发送数据
            // 包装输入流，输出流，包装一下可以直接传输字符串，不包装的话直接使用InputStream和OutputStream只能直接传输byte[]类型数据
            BufferedReader inRead = new BufferedReader(new InputStreamReader(in));
//			PrintWriter outWriter = new PrintWriter(out);

            // 发送数据
            // out.write(header);
            out.write(data);

            // 接受应答
            String result = inRead.readLine();  // 使用了包装后的输入流方便读取消息
            return result;
        } catch(UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "defeat";
    }

    public static void main(String[] args){
        Item item = new Item();
        item.setId(0);
        item.setCid(2);
        item.setName("test1");
        item.setSellPoint("iiiii");
        item.setPrice(BigDecimal.valueOf(1000L));
        SendMessage smg = new SendMessage();
        String result = smg.sendMessage("add".getBytes(),JSON.toJSONString(item).getBytes());
        System.out.println("result:"+result);
    }
}
*/
