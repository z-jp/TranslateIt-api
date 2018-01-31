package zjp.translateit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjp.translateit.data.TokenRepository;
import zjp.translateit.domain.Token;
import zjp.translateit.util.EncryptUtil;

@Service
@PropertySource(value = "classpath:application.properties")
@PropertySource(value = "classpath:application-${spring.profiles.active}.properties")
public class TokenService {

    private final TokenRepository repository;
    @Value("${salt.token}")
    private String tokenSalt;

    @Autowired
    public TokenService(TokenRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Token refreshToken(Token oldToken) {
        Token token = generateToken(oldToken.getUid());
        int i = repository.updateToken(oldToken, token);
        if (i == 1) {
            return token;
        } else {
            repository.setTokenUsed(oldToken.getUid());
            return null;
        }
    }

    public Token getNewToken(long uid) {
        Token token = generateToken(uid);
        repository.saveToken(token);
        return token;
    }

    private Token generateToken(long uid) {
        long currentTime = System.currentTimeMillis();
        String key = EncryptUtil.hash(EncryptUtil.Algorithm.SHA256, uid + tokenSalt + currentTime);
        return new Token(uid, currentTime, key);
    }

}
