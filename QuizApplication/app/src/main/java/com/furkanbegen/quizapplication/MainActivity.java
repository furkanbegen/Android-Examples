package com.furkanbegen.quizapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.furkanbegen.quizapplication.R;
import com.furkanbegen.quizapplication.SoruVeCevap;

import java.util.Objects;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    /* Layout.xml de tanımladığımız ve MainActivity de kullanacağımız tasarım elemanları
    *  için değişkenlerimizi tanımlıyoruz.
    */
    private TextView mDisplayQuestion;
    private TextView mDisplayInfo;
    private TextView mDisplayPoints;
    private Button mBtnAnswer1;
    private Button mBtnAnswer2;
    private Button mBtnAnswer3;
    private Button mBtnAnswer4;
    // Soru sayısını tutan değişken
    private int questionCount = 0;
    // Toplam puanımızı tutan değişken
    private int mTotalPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Soruların gösterileceği Textview
        mDisplayQuestion = (TextView) findViewById(R.id.tv_question_display);
        // Yanlış ya da doğru cevap bilgisinin gösterileceği Textview
        mDisplayInfo = (TextView) findViewById(R.id.tv_answer_info);
        // Toplam puanımızın gösterileceği TextView
        mDisplayPoints = (TextView) findViewById(R.id.tv_show_points);

        // Cevap şıkları için butonlarımız
        mBtnAnswer1 = (Button) findViewById(R.id.btn_answer1);
        mBtnAnswer2 = (Button) findViewById(R.id.btn_answer2);
        mBtnAnswer3 = (Button) findViewById(R.id.btn_answer3);
        mBtnAnswer4 = (Button) findViewById(R.id.btn_answer4);

        // Soru ve cevapları getirecek fonksiyonumuzu çağırıyoruz
        prepareQuestion(questionCount);

        /* Butonlarımız tıklandığında işlem yapılabilmesi için bir herbirine
         * birer OnClickListener atıyoruz.
         */
        mBtnAnswer1.setOnClickListener(this);
        mBtnAnswer2.setOnClickListener(this);
        mBtnAnswer3.setOnClickListener(this);
        mBtnAnswer4.setOnClickListener(this);

    }

    /*
    * Bu fonksiyon SoruVeCevap sınıfında yer alan soru ve vevapları alıp Textview ve Butonlara
    * aktarmamızı sağlıyor. questionCount parametresini her sorudan sonra arttırıyoruz ki bir
    * sonraki soruyu ekrana yazdırsım
    * */
    public void prepareQuestion(int questionCount){
        // Sorularımızı getQuestions metodu ile çektik.
        String[][] questions = SoruVeCevap.getQuestion();
        /* questionCount değişkeninin değeri çekilen soruların değerinden küçükse soruları ekrana
         * getiriyor. Aksi taktirde sorulacak soru kalmamış demektir.
         */
        if(questionCount < questions.length){
            /*
            * 2 boyutlu dizimizin ikinci boyutu yani cevaplar kısmındaki veriyi virgül ile ayırıp
            * choices isimli diziye aktarıyoruz
            * */
            String[] choices = questions[questionCount][1].split(",");
            /*
            * Sorumuzu ilgili TextView'e yazdırdık
            * */
            mDisplayQuestion.setText(questions[questionCount][0]);
            /*
            * Cevaplarımızı Butonlara yazdırdık.
            * */
            mBtnAnswer1.setText(choices[0]);
            mBtnAnswer2.setText(choices[1]);
            mBtnAnswer3.setText(choices[2]);
            mBtnAnswer4.setText(choices[3]);
        }else {
            /*
            * Gösterilecek soru kalmadığı için Butonları görünmez yapıyoruz ve soruların yer aldığı
            * TextView'e Testi bitirdiniz yazıyoruz.
            * */
            mBtnAnswer1.setVisibility(View.INVISIBLE);
            mBtnAnswer2.setVisibility(View.INVISIBLE);
            mBtnAnswer3.setVisibility(View.INVISIBLE);
            mBtnAnswer4.setVisibility(View.INVISIBLE);
            mDisplayQuestion.setText("Testi bitirdiniz");
        }
    }

    /*
    * Kullanıcı Butona tıkladığında nelerin olacağını belirlediğimiz fonksiyonumuz.
    * Eğer Butonda yazan değer sorunun cevabına eşitse bilgi için oluşturduğumuz Textview'a
    * doğru cevap yazdırıyoruz ve puanımızı 10 arttırıyoruz, değilse Yanlış cevap yazdırıyoruz.
    * En sonda questionCount değerini 1 arttırıyoruz ve prepareQuestion() fonksiyonunu tekrar
    * çağırıyoruz. Böylece bir sonraki soruyu ve şıkları ekrana yazrırabiliyoruz.
    * */
    @Override
    public void onClick(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        String[] answer = SoruVeCevap.getAnswer();
        if(Objects.equals(buttonText, answer[questionCount])){
            mDisplayInfo.setText("Doğru cevap");
            mTotalPoints = mTotalPoints + 10 ;
        }else {
            mDisplayInfo.setText("Yanlış cevap");
        }
        questionCount++;
        prepareQuestion(questionCount);
        mDisplayPoints.setText("Toplam puanınız " + String.valueOf(mTotalPoints));

    }
}
