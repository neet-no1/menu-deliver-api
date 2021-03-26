package jp.co.suyama.menu.deliver.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import jp.co.suyama.menu.deliver.exception.EmptyAuthenticationException;
import jp.co.suyama.menu.deliver.mapper.UsersMapperImpl;
import jp.co.suyama.menu.deliver.model.db.Users;

/**
 * <pre>
 * Spring Securityの認証処理の実装
 * </pre>
 */
@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /**
     * パスワードエンコーダー
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * ユーザ情報テーブル
     */
    @Autowired
    UsersMapperImpl usersMapper;

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * <pre>
     * 認証処理
     * DBからメールアドレスとパスワードで認証する
     * </pre>
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // メールアドレス
        String loginEmail = (String) authentication.getPrincipal();
        // パスワード
        String loginPassword = (String) authentication.getCredentials();

        // 入力が空でないことを確認
        if (StringUtils.isEmpty(loginEmail) || StringUtils.isEmpty(loginPassword)) {
            throw new EmptyAuthenticationException("メールアドレスまたはパスワードが未入力です。");
        }

        // ユーザ情報取得
        Users users = usersMapper.selectEmail(loginEmail);

        // ユーザが存在していることを確認
        if (users == null) {
            throw new UsernameNotFoundException("ユーザが見つかりません。email=[" + loginEmail + "]");
        }

        // ユーザが論理削除されていないこと
        if (users.getDeleted()) {
            throw new UsernameNotFoundException("ユーザが削除されています。email=[" + loginEmail + "]");
        }

        // パスワードを検証
        try {
            if (!passwordEncoder.matches(loginPassword, users.getPassword())) {
                throw new BadCredentialsException("パスワードが一致しません。");
            }
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("認証エラー", e);
        }

        // 権限を設定
        Collection<GrantedAuthority> authorityList = new ArrayList<>();

        if (users.getRole() == null) {
            throw new BadCredentialsException("権限が設定されていません。");
        }

        // メールアドレス認証を確認
        if (!users.getAvailable().booleanValue()) {
            throw new BadCredentialsException("メールアドレスが認証されていません。");
        }

        authorityList.add(new SimpleGrantedAuthority(users.getRole()));

        return new UsernamePasswordAuthenticationToken(loginEmail, users.getPassword(), authorityList);
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
