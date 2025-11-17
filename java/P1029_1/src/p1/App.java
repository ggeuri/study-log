package p1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        {
            // 관리자가 회원 정보 목록을 원하는 경우 
            List<User> list = new ArrayList<>();
        }
        {
            // 관리자가 모든 회원 정보와 그 회원이 쓴 글의 모든 정보를 보고 싶다.
            //Map으로 엮을 수 있다 
            List<Map<String,Object>> totalDataList = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>(); 
            map1.put("userInfo", new User()); // 오브젝트니까 new User()가능
            List<Article> articleList = new ArrayList<>(); 
            articleList.add(new Article());
            articleList.add(new Article());
            articleList.add(new Article());
            map1.put("articleList", articleList); // 엮기 

            totalDataList.add(map1);

            

        }
        {
            // 글 상세 페이지 
            // 글 정보 - 유저 정보 (이럴 때 Map으로 묶어주면 된다)
            Map<String, Object> data = new HashMap<>(); //순서 상관없이 다른 형태의 데이터를 묶고싶은 경우가 대부분이라 Object..
            data.put("writer", new User());
            data.put("article", new Article());
            
            List<Map<String,Object>> commentDataList = new ArrayList<>();
            data.put("commentDataList", commentDataList);
            
            Map<String, Object> commentData1 = new HashMap<>(); //순서 상관없이 다른 형태의 데이터를 묶고싶은 경우가 대부분이라 Object..
            commentData1.put("comment", new Comment());
            commentData1.put("writer", new User());
            commentDataList.add(commentData1);
        }
        
    }

}

class User {
    String nickname;
    List<Article> list; 

    //유저정보 

}

class Article {
    String title;
    String content;
    List<Comment> list;
    //...
}

class Comment {
    String text;
    //
}