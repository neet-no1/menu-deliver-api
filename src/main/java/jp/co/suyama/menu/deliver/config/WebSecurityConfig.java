package jp.co.suyama.menu.deliver.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * <pre>
 * Spring Securityの設定情報
 * </pre>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 認証不要でアクセスできるパス
    // 例) category配下は認証不要 -> "/category/**"
    private static final String[] NO_AUTH_PATH = {};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            // 認証を必要とする設定
            .authorizeRequests()
                // 特定のパスは認証不要
                .antMatchers(NO_AUTH_PATH).permitAll()
                // 特定のパスは特定の権限のみアクセス可能
                .antMatchers("/admin/**").hasRole("ADMIN")
                // その他は全て認証が必要
                // .anyRequest().authenticated()
                .and()
            // フォームベースの認証
            .formLogin()
                // ログインページのURL
                //.loginPage("/login")
                // ログイン処理を行うURL
                .loginProcessingUrl("/login")
                // ユーザIDのパラメタ名
                .usernameParameter("email")
                // パスワードのパラメタ名
                .passwordParameter("password")
                .permitAll()
                .successHandler(new LoginAuthenticationHandler())
                .failureHandler(new LoginAuthenticationHandler())
                .and()
            // ログアウト設定
            .logout()
                // ログアウトの処理を行うURL
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutHandler())
                .permitAll()
            // csrf設定
            // REST APIなので無効
            .and()
            .csrf().disable()
            /*.csrf(csrf -> csrf
                // csrfトークンをクッキーに保存するように設定
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))*/
            // cors設定
            .cors()
                .configurationSource(this.corsConfigurationSource());
            ;
        // @formatter:on
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // configuration.setAllowedOrigins(Arrays.asList("https://www.menu-deliver.work",
        // "http//localhost:8080"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /**
     * パスワードエンコーダーを生成
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // pbkdf2を使用
        String idForEncode = "pbkdf2";

        // エンコーダー一覧を作成
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }
}
