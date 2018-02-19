package zjp.translateit.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zjp.translateit.domain.Token;

@Repository
public class TokenRepository {

    private final JdbcTemplate template;

    @Autowired
    public TokenRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void saveToken(Token token) {
        template.update("INSERT INTO token (uid, time, sign, used) VALUES (?, ?, ?, ?)",
                token.getUid(),
                token.getTimestamp(),
                token.getSign(),
                false);
    }

    public int updateToken(Token oldToken, Token newToken) {
        return template.update("UPDATE token SET sign = ? , time = ? " +
                               " WHERE uid = ? AND used = false AND time = ? ",
                newToken.getSign(),
                newToken.getTimestamp(),
                oldToken.getUid(),
                oldToken.getTimestamp());
    }

    public void setAllTokenUsed(long uid) {
        template.update("UPDATE token SET used = ? WHERE uid = ? ",
                true,
                uid);
    }
}
