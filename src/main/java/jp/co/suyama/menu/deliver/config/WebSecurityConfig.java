package jp.co.suyama.menu.deliver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                .loginPage("/login")
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
                .and()
            // csrf設定
            // REST APIなので無効
            .csrf().disable()
            /*.csrf(csrf -> csrf
                // csrfトークンをクッキーに保存するように設定
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))*/
            // cors設定
            .cors()
                .configurationSource(this.corsConfigurationSource());
        // @formatter:on
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // corsConfiguration.addAllowedMethod("GET");
        // corsConfiguration.addAllowedOrigin("http://example.com");

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }
}
