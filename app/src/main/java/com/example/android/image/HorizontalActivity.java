package com.example.android.image;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HorizontalActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageResultAdapterH mImageResultAdapterH;
    private ArrayList<ImageResult> mImageResult;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                HorizontalActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false));

        mImageResult = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    public void parseJson() {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String imageUrl = hit.getString("webformatURL");
                                String creatorName = hit.getString("user");

                                int likes = hit.getInt("likes");
                                mImageResult.add(new ImageResult(imageUrl, creatorName, likes));
                            }
                            mImageResultAdapterH = new ImageResultAdapterH(HorizontalActivity.this, mImageResult);

                            mRecyclerView.setAdapter(mImageResultAdapterH);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}