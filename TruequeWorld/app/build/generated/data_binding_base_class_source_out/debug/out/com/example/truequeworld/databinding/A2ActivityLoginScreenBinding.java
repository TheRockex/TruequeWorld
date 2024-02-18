// Generated by view binder compiler. Do not edit!
package com.example.truequeworld.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.truequeworld.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class A2ActivityLoginScreenBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MaterialButton buttonLogin;

  @NonNull
  public final ImageButton closeLoginButton;

  @NonNull
  public final TextView esloganLogin;

  @NonNull
  public final TextInputEditText loginEmail;

  @NonNull
  public final ImageView loginImageIcon;

  @NonNull
  public final ImageView loginImageProfile;

  @NonNull
  public final TextInputEditText loginPassword;

  @NonNull
  public final LinearLayout loginScreen;

  @NonNull
  public final TextView tLogin;

  private A2ActivityLoginScreenBinding(@NonNull LinearLayout rootView,
      @NonNull MaterialButton buttonLogin, @NonNull ImageButton closeLoginButton,
      @NonNull TextView esloganLogin, @NonNull TextInputEditText loginEmail,
      @NonNull ImageView loginImageIcon, @NonNull ImageView loginImageProfile,
      @NonNull TextInputEditText loginPassword, @NonNull LinearLayout loginScreen,
      @NonNull TextView tLogin) {
    this.rootView = rootView;
    this.buttonLogin = buttonLogin;
    this.closeLoginButton = closeLoginButton;
    this.esloganLogin = esloganLogin;
    this.loginEmail = loginEmail;
    this.loginImageIcon = loginImageIcon;
    this.loginImageProfile = loginImageProfile;
    this.loginPassword = loginPassword;
    this.loginScreen = loginScreen;
    this.tLogin = tLogin;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static A2ActivityLoginScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static A2ActivityLoginScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.a2_activity_login_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static A2ActivityLoginScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonLogin;
      MaterialButton buttonLogin = ViewBindings.findChildViewById(rootView, id);
      if (buttonLogin == null) {
        break missingId;
      }

      id = R.id.close_login_button;
      ImageButton closeLoginButton = ViewBindings.findChildViewById(rootView, id);
      if (closeLoginButton == null) {
        break missingId;
      }

      id = R.id.eslogan_login;
      TextView esloganLogin = ViewBindings.findChildViewById(rootView, id);
      if (esloganLogin == null) {
        break missingId;
      }

      id = R.id.login_email;
      TextInputEditText loginEmail = ViewBindings.findChildViewById(rootView, id);
      if (loginEmail == null) {
        break missingId;
      }

      id = R.id.login_image_icon;
      ImageView loginImageIcon = ViewBindings.findChildViewById(rootView, id);
      if (loginImageIcon == null) {
        break missingId;
      }

      id = R.id.login_image_profile;
      ImageView loginImageProfile = ViewBindings.findChildViewById(rootView, id);
      if (loginImageProfile == null) {
        break missingId;
      }

      id = R.id.login_password;
      TextInputEditText loginPassword = ViewBindings.findChildViewById(rootView, id);
      if (loginPassword == null) {
        break missingId;
      }

      LinearLayout loginScreen = (LinearLayout) rootView;

      id = R.id.t_login;
      TextView tLogin = ViewBindings.findChildViewById(rootView, id);
      if (tLogin == null) {
        break missingId;
      }

      return new A2ActivityLoginScreenBinding((LinearLayout) rootView, buttonLogin,
          closeLoginButton, esloganLogin, loginEmail, loginImageIcon, loginImageProfile,
          loginPassword, loginScreen, tLogin);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
