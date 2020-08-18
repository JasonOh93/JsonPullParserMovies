package com.jasonoh.jsonpullparsermoviesex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Member> members = new ArrayList<>();
    MoviesAdapter adapter;

    String keyName = "a6674e6a6b1460e7db396feb9fe986cd";
    String nowDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //테스트용
        //members.add( new Member("1", "skdltm", "ddd", "dd", "dd") );

        recyclerView = findViewById(R.id.recycler);
        adapter = new MoviesAdapter(this, members);
        recyclerView.setAdapter( adapter );

    }

    public void loadData(){
        Date date = new Date();
        date.setTime( date.getTime() - (1000*60*60*24) ); //현재 시간 가져오기
        nowDate = new SimpleDateFormat( "yyyyMMdd" ).format( date );
        RetrofitService retrofitService = RetrofitHelper.getInstance().create( RetrofitService.class );
        //todo : 이부분 부터 다시 알아보기!!!
        //        call 불러오는 것이 Not Found 로 나오므로 서버에 접속이 안되고 있는 것이다..
        //          MAP방식 생각해보기!!!
        //Call<ArrayList<Member>> call = retrofitService.loadDataFromKobis( keyName, nowDate );

        //todo : 여기부터 다시 하기!!

        //Call<Map<String, Object>> call = retrofitService.loadDataFromKobis("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=a6674e6a6b1460e7db396feb9fe986cd&targetDt=20200622");
        Call<Map<String, Object>> call = retrofitService.loadDataFromKobis(keyName, nowDate);
            call.enqueue(new Callback<Map<String, Object>>() {
                @Override
                public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                    //Toast.makeText(MainActivity.this, "Response : " + response.body(), Toast.LENGTH_SHORT).show();

                    if(response.isSuccessful()) {
                        Map<String, Object> boxOfficeResult = (Map<String, Object>) response.body().get("boxOfficeResult");
                        ArrayList<Map<String, Object>> boxOfficeList = (ArrayList<Map<String, Object>>) boxOfficeResult.get("dailyBoxOfficeList");
                        //Toast.makeText(MainActivity.this, "" + boxOfficeList.toString(), Toast.LENGTH_SHORT).show();

                        for(int i = 0; i < boxOfficeList.size(); i++) {
                            members.add( new Member( boxOfficeList.get(i).get("rank").toString(),
                                    boxOfficeList.get(i).get("movieNm").toString(),
                                    boxOfficeList.get(i).get("openDt").toString(),
                                    boxOfficeList.get(i).get("audiCnt").toString(),
                                    boxOfficeList.get(i).get("audiAcc").toString() ) );
                            adapter.notifyItemInserted(i);
                        }


                    }

                }

                @Override
                public void onFailure(Call<Map<String, Object>> call, Throwable t) {

                }
            });

    }

    public void clickLoad(View view) {
        loadData();
    }
}
