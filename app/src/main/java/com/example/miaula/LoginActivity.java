package com.example.miaula;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;



public class LoginActivity extends AppCompatActivity {

    private Button btnSignGoogle;
    private GoogleSignInClient googleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

//    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
//            new FirebaseAuthUIActivityResultContract(),
//            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
//                @Override
//                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
//                    onSignInResult(result);
//                }
//            }
//    );
//
//    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
//        IdpResponse response = result.getIdpResponse();
//        if (result.getResultCode() == RESULT_OK) {
//            // Successfully signed in
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            System.out.println("Correcto");
//            // ...
//        } else {
//            System.out.println("Error al iniciar");
//            // Sign in failed. If response is null the user canceled the
//            // sign-in flow using the back button. Otherwise check
//            // response.getError().getErrorCode() and handle the error.
//            // ...
//        }
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSignGoogle = findViewById(R.id.btn_sign_google);

        createRequest();

        btnSignGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

//        if (authUser.getCurrentUser() != null){
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//            finish();
//        }else{
//
//        }

//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.GoogleBuilder().build());
//
//        Intent signInIntent = AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
//                .build();
//        signInLauncher.launch(signInIntent);

    }

    private void createRequest(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);
    }

    private void signIn(){
        Intent signIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Resultado devuelto al iniciar la intencion desde GoogleSignInApi.getSignIntent
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Inicio exitoso
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AutenticacionFirebase(account);
            }catch (ApiException e){

            }
        }
    }


    private void AutenticacionFirebase(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Si inicio correctamente
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    if (task.getResult().getAdditionalUserInfo().isNewUser()){
                        String uid = user.getUid();
                        String correo = user.getEmail();
                        String name = user.getDisplayName();
                        String number = user.getPhoneNumber();
                        Uri photo = user.getPhotoUrl();
                        Toast.makeText(getApplicationContext(),"Usuario:"+uid+correo+name,Toast.LENGTH_LONG).show();
                    }
                    //Ir al inicio de la app
                    Intent mainActivityIntent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(mainActivityIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"Hubo un error al iniciar sesión, intente nuevamente!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}