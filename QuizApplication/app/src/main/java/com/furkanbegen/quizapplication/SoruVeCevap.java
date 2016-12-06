package com.furkanbegen.quizapplication;

public class SoruVeCevap {

    /*
    * getSoru isimli static bir fonksiyonumuz var ve geriye 2 boyutlu bir dizi döndürüyor.
    * Dizinin ilk boyutu sorular ikinci boyutu ise cevaplar için
    **/
    public static String [][] getQuestion(){
        return new String[][]{
                {"Android işletim sistemini hangi firma geliştirmektedir ?", "Apple,Microsoft,Google,Oracle"},
                {"IOS işletim sistemini hangi firma geliştirmektedir ?" ,"Microsoft,Apple,Google,Facebook"},
                {"Google Andorid'i hangi yıl satın almıştır","2003,2004,2006,2007"}
        };
    }

    /*
    * getCevap isimli static bir fonksiyonumuz var ve geriye cevapların olduğu bir dizi döndürüyor
    * getSoru fonksiyonunun geriye döndürdüğü dizideki ilk indisin cevabı getCevap fonksiyonunun
    * geriye döndürdüğü dizinin ilk elemanı
    * */
    public static String[] getAnswer(){
        return new String[]{
                "Google",
                "Apple",
                "2007"
        };
    }
}
