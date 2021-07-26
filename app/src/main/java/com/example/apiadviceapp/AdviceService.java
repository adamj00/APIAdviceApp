package com.example.apiadviceapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class AdviceService {
    public static final String HTTPS_API_ADVICESLIP_COM_ADVICE = "https://api.adviceslip.com/advice/";
    Context context;

    public AdviceService(Context context) {
        this.context = context;
    }

    public interface GetRandomAdviceCallback {
        void onError(String message);

        void onResponse(AdviceModel adviceModel);
    }

    public void getRandomAdvice(GetRandomAdviceCallback getRandomAdviceCallback) {
        // generate random id from 1 to 217
        Random random = new Random();
        int id = random.nextInt(216) + 1;
        // wi will use random id number in request url
        String url = HTTPS_API_ADVICESLIP_COM_ADVICE + id;
        // request an advice with randomly generated id
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            String advice = "";
            public void onResponse(String response) {
                JSONObject jsonObject;
                try {
                    // we need to add '}' because of the error in api service
                    jsonObject = new JSONObject(response + "}");
                    try {
                        JSONObject slip = jsonObject.getJSONObject("slip");
                        advice = slip.getString("advice");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    AdviceModel adviceModel = new AdviceModel(id, advice);
                    getRandomAdviceCallback.onResponse(adviceModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // adding request to the queue
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
