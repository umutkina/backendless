package com.umutkina.backendlessexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.umutkina.backendlessexample.comment.Comment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final IDataStore<Comment> DATA_STORE = Backendless.Persistence.of(Comment.class);
    private static final String YOUR_APP_ID = "B7478AEA-FDED-9FBA-FFC9-DEF521906F00";
    private static final String YOUR_SECRET_KEY = "C857834E-AAE6-D64B-FF32-78214B347D00";
    private static final BackendlessDataQuery backendlessDataQuery = new BackendlessDataQuery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appVersion = "v1";
        Backendless.initApp(this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion);


        findEntities(new AsyncCallback<List<Comment>>() {
            @Override
            public void handleResponse(List<Comment> comments) {


                Toast.makeText(MainActivity.this, comments.get(0).getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(MainActivity.this, backendlessFault.toString(), Toast.LENGTH_SHORT).show();

            }
        });

//        Backendless.Persistence.save( new Comment( "I'm in!", "denem" ), new BackendlessCallback<Comment>()
//        {
//            @Override
//            public void handleResponse( Comment comment )
//            {
//                Log.i( "Comments", "Got new comment from " +"denem");
//            }
//        } );
    }

    public static void findEntities(final AsyncCallback<List<Comment>> callback) {
        DATA_STORE.find(backendlessDataQuery, new AsyncCallback<BackendlessCollection<Comment>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<Comment> response) {
                        callback.handleResponse(response.getCurrentPage());
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        callback.handleFault(fault);
                    }
                }

        );
    }
}
