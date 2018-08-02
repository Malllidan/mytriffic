package com.triffic.huawei;
import java.sql.Connection;
import java.util.*;

public class huaweitest {


        public static void main(String...args) {


            Scanner in = new Scanner(System.in);
            while (in.hasNext()) {//注意while处理多个case
                String myString = in.next();
                System.out.println(myString);
                if (myString == null) {
                    System.out.println(myString);
                } else {
                    //char[] mycharlist=new char[myString.length()];
                    List<Character> mycharlist = new ArrayList<>();
                    List<Integer>mycharlistnum=new ArrayList<>();
                    int num=0;
                    for (int i=0;i<myString.length();i++){

                        char mychar=myString.charAt(i);
                        num++;
                        if(!mycharlist.contains(mychar)&&mychar!=myString.charAt(i-1)){
                            mycharlist.add(mychar);
                            if(i!=0){
                                mycharlistnum.add(num);
                            }

                            num=0;
                        }

                    }
                    mycharlistnum.add(num+1);
                  System.out.println(mycharlist);
                    System.out.println(mycharlistnum);
                    int maxlenthindex=mycharlistnum.indexOf(Collections.max(mycharlistnum));
                    System.out.println(maxlenthindex);
                    char newchar=mycharlist.get(maxlenthindex);
                    String newstring="";

                    for(int i=0;i<Collections.max(mycharlistnum);i++){
                        newstring+=newchar;
                    }
                    System.out.println(newstring);



        }


            }
    }
}


