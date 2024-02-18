package com.example.truequeworld;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;


import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truequeworld.Class.User;
import com.example.truequeworld.Interface.UserServiceApi;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartScreen extends AppCompatActivity {
    MaterialButton LoginDesplegable;
    MaterialButton RegisterDesplegable;
    /**LOGIN GOOGLE**/
    private SignInClient oneTapClient;
    private BeginSignInRequest signUpRequest;
    private UserServiceApi userServiceApi;
    private ImageView imageView;
    User user;
    private static final int REQ_ONE_TAP = 16;
    private boolean showOneTapUI = true;


    /**FIN**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3_activity_start_screen);
        /**Luca**/

        LoginDesplegable = findViewById(R.id.loginTW);
        RegisterDesplegable = findViewById(R.id.registerTW);
        /** ESTO 2 TEXT INPUT SE UTILIZARÍAN PARA EL LOGIN SCREEN NO PARA


        LA START SCREEN AHORA MISMO NO LO EMPLEAMOS
          FALTAN COSAS

        TextInputEditText emailEditText = findViewById(R.id.login_email);
        TextInputEditText passwordEditText = findViewById(R.id.login_password);
         **/

        ImageView logoUpStart = findViewById(R.id.logoUpStart);
        ImageView logoDownStart = findViewById(R.id.logoDownStart);
        TextView appNameStart = findViewById(R.id.appNameStart);
        TextView appSlogan = findViewById(R.id.sloganStart);
        MaterialButton google = findViewById(R.id.loginGoogle);
        MaterialButton login = findViewById(R.id.loginTW);
        MaterialButton register = findViewById(R.id.registerTW);
        ImageView googleImg = findViewById(R.id.logoGoogle);
        ImageView twImg1 = findViewById(R.id.logoLoginTW);
        ImageView twImg2 = findViewById(R.id.logoRegisterTW);

        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.go_right_start);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.go_left_start);
        Animation apName = AnimationUtils.loadAnimation(this, R.anim.appear_start);
        Animation upButton1 = AnimationUtils.loadAnimation(this, R.anim.go_up);
        Animation upButton2 = AnimationUtils.loadAnimation(this, R.anim.go_up);
        Animation upButton3 = AnimationUtils.loadAnimation(this, R.anim.go_up);

        google.setVisibility(View.INVISIBLE);
        googleImg.setVisibility(View.INVISIBLE);
        login.setVisibility(View.INVISIBLE);
        twImg1.setVisibility(View.INVISIBLE);
        register.setVisibility(View.INVISIBLE);
        twImg2.setVisibility(View.INVISIBLE);

        appNameStart.setAnimation(apName);
        appSlogan.setAnimation(apName);
        logoUpStart.setAnimation(anim2);
        logoDownStart.setAnimation(anim1);
        google.setVisibility(View.VISIBLE);
        googleImg.setVisibility(View.VISIBLE);
        google.setAnimation(upButton1);
        googleImg.setAnimation(upButton1);

        upButton1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                login.setVisibility(View.VISIBLE);
                login.setAnimation(upButton2);
                twImg1.setVisibility(View.VISIBLE);
                twImg1.setAnimation(upButton2);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        upButton2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                register.setVisibility(View.VISIBLE);
                register.setAnimation(upButton3);
                twImg2.setVisibility(View.VISIBLE);
                twImg2.setAnimation(upButton3);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        /**Luca**/
        LoginDesplegable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginContent();

            }
        });
        RegisterDesplegable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterContent();

            }
        });

        /****/

        /*google.postDelayed(new Runnable() {
            @Override
            public void run() {
                google.setVisibility(View.VISIBLE);
                google.setAnimation(upButton);
                googleImg.setVisibility(View.VISIBLE);
                googleImg.setAnimation(upButton);

            }
        }, 0);

        login.postDelayed(new Runnable() {
            @Override
            public void run() {
                login.setVisibility(View.VISIBLE);
                login.setAnimation(upButton);
                twImg1.setVisibility(View.VISIBLE);
                twImg1.setAnimation(upButton);
                register.setVisibility(View.VISIBLE);
                register.setAnimation(upButton);
                twImg2.setVisibility(View.VISIBLE);
                twImg2.setAnimation(upButton);
            }
        }, 1000);

        /*register.postDelayed(new Runnable() {
            @Override
            public void run() {
                register.setVisibility(View.VISIBLE);
                register.setAnimation(upButton);
                twImg2.setVisibility(View.VISIBLE);
                twImg2.setAnimation(upButton);
            }
        }, 2000);

        /*google.setVisibility(View.VISIBLE);
        googleImg.setVisibility(View.VISIBLE);
        google.setAnimation(upButton);
        googleImg.setAnimation(upButton);


        login.setVisibility(View.VISIBLE);
        twImg1.setVisibility(View.VISIBLE);
        Animation upButtonWithDelay = AnimationUtils.loadAnimation(this, R.anim.go_up);
        upButtonWithDelay.setStartOffset(1000); // 1 second delay
        login.setAnimation(upButtonWithDelay);S
        twImg1.setAnimation(upButtonWithDelay);

        register.setVisibility(View.VISIBLE);
        twImg2.setVisibility(View.VISIBLE);
        upButtonWithDelay = AnimationUtils.loadAnimation(this, R.anim.go_up);
        upButtonWithDelay.setStartOffset(2000); // 2 seconds delay
        register.setAnimation(upButtonWithDelay);
        twImg2.setAnimation(upButtonWithDelay);*/

        /**LOGIN GOOGLE**/
        //IDENTIFICAR ONE TAP PARA INICIAR SESIÓN
        oneTapClient = Identity.getSignInClient(StartScreen.this);
        // ENVIAR REQUEST PARA INICIAR SESION
        signUpRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        //SE EMPLEA EL SERVIDOR DEL ID CLIENTE NO DEL ANDROID
                        .setServerClientId(getString(R.string.weblogingoogleid))
                        // Show all accounts on the device.
                        //MOSTRAR TODAS LAS CUENTAS
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();

        google.setOnClickListener(new View.OnClickListener() {
            ActivityResultLauncher<IntentSenderRequest> activityResultLauncher =
                    //INICIO DE PARA ENVIAR LA SOLICITUD DE INICIAR SESIÓN UNA VEZ VERIFICADOS DE QUE LOS ID SON CORRECTOS
                    registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            try {
                                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                                String idToken = credential.getGoogleIdToken();
                                if (idToken !=  null) {
                                    // Got an ID token from Google. Use it to authenticate
                                    // with your backend.
                                    String email = credential.getId();
                                    String username = credential.getDisplayName();
                                    Toast.makeText(getApplicationContext(),"Email : "+email+" Name : "+username, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "INICIO SESIÓN EXITOSO", Toast.LENGTH_SHORT).show();
                                    //
                                    //Log.d("TAG", "Got ID token.");
                                    Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                                    // now by putExtra method put the value in key, value pair key is
                                    // user_name by this key we will receive the value, and put the string
                                    intent.putExtra("user_name", username);
                                    // start the Intent
                                    startActivity(intent);
                                }
                            } catch (ApiException e) {
                                // Manejar la excepción de manera más precisa
                                // EN EL CASO DE QUE HAYA FALLADO ALGO
                                Log.e("TAG", "ApiException: " + e.getStatusCode(), e);

                                if (e.getStatusCode() == 16) {
                                    // Muestra un Toast indicando que el usuario canceló la autenticación
                                    Toast.makeText(getApplicationContext(), "FALLO DURANTE EL PROCESO INTENTELO DE NUEVO", Toast.LENGTH_SHORT).show();
                                    // El usuario canceló la autenticación
                                    Log.d("TAG", "El usuario canceló la autenticación");
                                } else {
                                    // Otra excepción, manejar según sea necesario
                                    Log.e("TAG", "Otra excepción al procesar el resultado", e);
                                }


                            }
                        }
                    });

            @Override
            public void onClick(View view) {
                //INICIA LA SOLICITUD CUANDO SE PULSA
                oneTapClient.beginSignIn(signUpRequest)
                        .addOnSuccessListener(StartScreen.this, new OnSuccessListener<BeginSignInResult>() {
                            @Override
                            public void onSuccess(BeginSignInResult result) {
                                IntentSenderRequest intentSenderRequest =
                                        new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                                activityResultLauncher.launch(intentSenderRequest);
                            }
                        })
                        .addOnFailureListener(StartScreen.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // No Google Accounts found. Just continue presenting the signed-out UI.
                                Log.d("TAG", e.getLocalizedMessage());
                            }
                        });
            }
        });
        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.30:8086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear instancia de la interfaz
        userServiceApi = retrofit.create(UserServiceApi.class);
        /**fin**/
    }
    /**Luca**/
    private void showLoginContent() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Configurar el fondo con esquinas redondeadas
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.a2_activity_login_screen);
        LinearLayout mainLayout = dialog.findViewById(R.id.login_screen);
        MaterialButton button = dialog.findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(dialog);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            mainLayout.setBackground(ContextCompat.getDrawable(StartScreen.this, R.drawable.desplegable));
        } else {
            mainLayout.setBackgroundResource(R.drawable.desplegable);
        }
        //Cerrar el desplegable desde la loginscreen ya que el boton close no se encuentra en la start screen
        ImageButton closeButton = dialog.findViewById(R.id.close_login_button);


        // Configurar OnClickListener para cerrar el diálogo al hacer clic en el botón de cierre
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

//

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        dialog.show();
    }
    /*JOHAAWDAWDN*/
    public void Login(Dialog dialog){
        imageView = dialog.findViewById(R.id.login_image_profile);
        TextView emailTextView = dialog.findViewById(R.id.login_email);
        TextView contraTextView = dialog.findViewById(R.id.login_password);

        String emailString = emailTextView.getText().toString();
        String contraString = contraTextView.getText().toString();

        // Ejemplo de llamada a getUserId
        Call<User> call = userServiceApi.getUser(emailString, contraString);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user  != null) {
                        SharedPreferences sharedPreferences = getSharedPreferences("UsuarioID", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("userId", user.getId());
                        editor.apply();
                        if(user.getImgPerfil() != null){
                            Bitmap bitmap = base64ToBitmap(user.getImgPerfil());
                            imageView.setImageBitmap(bitmap);
                        }
                        Toast.makeText(StartScreen.this, "Bienvenido de vuelta " + user.getName(), Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                final Intent mainIntent = new Intent(StartScreen.this, MainScreen.class);
                                startActivity(mainIntent, ActivityOptions.makeCustomAnimation(StartScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
                            }
                        }, 2000);

                    } else {
                        // El userId es nulo, maneja el caso según tus necesidades
                        Toast.makeText(StartScreen.this, "ID de Usuario nulo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Manejar el error de respuesta
                    Toast.makeText(StartScreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Manejar el fallo de la llamada
                Toast.makeText(StartScreen.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Bitmap base64ToBitmap(String base64Image) {
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
    private void showRegisterContent() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Configurar el fondo con esquinas redondeadas
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setContentView(R.layout.a4_activity_register_screen);
        MaterialButton button = dialog.findViewById(R.id.buttonRegistrer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserExists(dialog);
            }
        });
        LinearLayout mainLayout = dialog.findViewById(R.id.register_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            mainLayout.setBackground(ContextCompat.getDrawable(StartScreen.this, R.drawable.desplegable));
        } else {
            mainLayout.setBackgroundResource(R.drawable.desplegable);
        }
        //Cerrar el desplegable desde la loginscreen ya que el boton close no se encuentra en la start screen
        ImageButton closeButton = dialog.findViewById(R.id.close_register_button);


        // Configurar OnClickListener para cerrar el diálogo al hacer clic en el botón de cierre
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

//

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        dialog.show();
    }
     /** EJEMPLO UTIL TANTO PARA LOGIN O REGISTER O COMO PARA SU DISEÑO
     *  private void showDialog() {
     *
     *         final Dialog dialog = new Dialog(this);
     *         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
     *         dialog.setContentView(R.layout.bottomsheetlayout);
     *
     *         LinearLayout editLayout = dialog.findViewById(R.id.layoutEdit);
     *         LinearLayout shareLayout = dialog.findViewById(R.id.layoutShare);
     *         LinearLayout uploadLayout = dialog.findViewById(R.id.layoutUpload);
     *         LinearLayout printLayout = dialog.findViewById(R.id.layoutPrint);
     *
     *         editLayout.setOnClickListener(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View v) {
     *
     *                 dialog.dismiss();
     *                 Toast.makeText(MainActivity.this,"Edit is Clicked",Toast.LENGTH_SHORT).show();
     *
     *             }
     *         });
     *
     *         shareLayout.setOnClickListener(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View v) {
     *
     *                 dialog.dismiss();
     *                 Toast.makeText(MainActivity.this,"Share is Clicked",Toast.LENGTH_SHORT).show();
     *
     *             }
     *         });
     *
     *         uploadLayout.setOnClickListener(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View v) {
     *
     *                 dialog.dismiss();
     *                 Toast.makeText(MainActivity.this,"Upload is Clicked",Toast.LENGTH_SHORT).show();
     *
     *             }
     *         });
     *
     *         printLayout.setOnClickListener(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View v) {
     *
     *                 dialog.dismiss();
     *                 Toast.makeText(MainActivity.this,"Print is Clicked",Toast.LENGTH_SHORT).show();
     *
     *             }
     *         });
     *
     *         dialog.show();
     *         dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
     *         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
     *         dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
     *         dialog.getWindow().setGravity(Gravity.BOTTOM);
     *
     *     }
     * **/

    public void Registrer(Dialog dialog){

        TextView nombreTextView = dialog.findViewById(R.id.register_name);
        TextView apellidosTextView = dialog.findViewById(R.id.register_surname);
        TextView emailTextView = dialog.findViewById(R.id.register_email);
        TextView contraTextView = dialog.findViewById(R.id.register_pass);

        String nombreString = nombreTextView.getText().toString();
        String apellidosString = apellidosTextView.getText().toString();
        String emailString = emailTextView.getText().toString();
        String contraString = contraTextView.getText().toString();

        // Crear un nuevo usuario para la inserción
        User newUser = new User(null,nombreString,nombreString,apellidosString,emailString,contraString,0,null,null);

        // Ejemplo de llamada a insertUser
        Call<User> call = userServiceApi.insertUser(newUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User insertedUser = response.body();
                    if (insertedUser != null) {
                        Intent intent = new Intent(StartScreen.this, Preference_screen.class);
                        intent.putExtra("email", emailString);
                        intent.putExtra("contra", contraString);
                        startActivity(intent, ActivityOptions.makeCustomAnimation(StartScreen.this, R.anim.fade_in, R.anim.fade_out).toBundle());
                    } else {
                        // El usuario insertado es nulo, maneja el caso según tus necesidades
                        Toast.makeText(StartScreen.this, "Usuario insertado nulo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Manejar el error de respuesta
                    Toast.makeText(StartScreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Manejar el fallo de la llamada
                Toast.makeText(StartScreen.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void UserExists(Dialog dialog){
        TextView emailTextView = dialog.findViewById(R.id.register_email);
        TextView contraTextView = dialog.findViewById(R.id.register_pass);
        TextView contraconfirmTextView = dialog.findViewById(R.id.register_confpass);

        String emailString = emailTextView.getText().toString();
        String contraString = contraTextView.getText().toString();
        String contraconfirmString = contraconfirmTextView.getText().toString();

        if(contraString.equals(contraconfirmString)){
            Call<User> call = userServiceApi.getUser(emailString, contraString);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User userId = response.body();
                        // Manejar el resultado
                        if (userId != null) {
                            Toast.makeText(StartScreen.this, "El usuario existe", Toast.LENGTH_SHORT).show();
                        } else {
                            // El userId es nulo, maneja el caso según tus necesidades
                            Toast.makeText(StartScreen.this, "ID de Usuario nulo", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Manejar el error de respuesta
                        Toast.makeText(StartScreen.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Registrer(dialog);
                }
            });

        }else{
            Toast.makeText(this, "La contraseña no es igual", Toast.LENGTH_SHORT).show();
        }
    }
}