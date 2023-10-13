package pachong;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageCrawl {
  //private static final String url="https://www.nipic.com/topic/show_28084_1.html";

  public static void ImagesCrawl(String url) throws IOException {
    //apacheHttpClient();
    Document document = Jsoup.connect(url).get();
    //爬取文字
    Elements textElements = document.select("p, h1, h2");
    File file = new File("src\\text.txt");
    FileWriter fileWriter = new FileWriter(file);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    for (Element element : textElements) {
      String temp = element.text();
      bufferedWriter.write(temp);
      bufferedWriter.newLine();
    }
    bufferedWriter.close();

    //爬取图片
    /*Elements element=document.select("li.new-search-works-item");
    for(int i=0;i<element.size();i++){
      Elements imgElement=element.get(i).select("a > img");
      String imageUrl = imgElement.attr("src");
      if (!imageUrl.startsWith("http://") && !imageUrl.startsWith("https://")) {
        imageUrl = "https:" + imageUrl;
      }
      Connection.Response response=Jsoup.connect(imageUrl)
          .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
          .ignoreContentType(true).execute();
      if(response.statusCode()==200){
        String name =imgElement.attr("alt");
        ByteArrayInputStream stream=new ByteArrayInputStream(response.bodyAsBytes());
        FileUtils.copyInputStreamToFile(stream,new File("D://fileitem//"+name+".png"));
        System.out.println("已添加"+name+"至文件夹中");
      }else{
        System.out.println("Failed to save");
      }

    }*/

  }

  /*private static void apacheHttpClient(){
    HttpClient client = HttpClients.createDefault();
    HttpGet httpGet=new HttpGet(url);
    //httpGet.setHeader("");
    HttpResponse response=null;
    try{
      response=client.execute((httpGet));
      HttpEntity entity=response.getEntity();
      EntityUtils.toString(entity);
      System.out.println(entity);
    }catch(Exception e){

    }finally {

    }
  }*/
  public static void main(String[] args) throws IOException {
    ImagesCrawl("https://www.gerenjianli.cn/fwdq/duhougan/10135824.html");
  }

}
