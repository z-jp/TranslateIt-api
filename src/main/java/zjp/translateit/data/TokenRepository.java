package zjp.translateit.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zjp.translateit.web.domain.Token;

@Repository
public class TokenRepository {

    private final JdbcTemplate template;

    @Autowired
    public TokenRepository(JdbcTemplate template) {
        this.template = template;
    }

    public Boolean isTokenUsed(Token token) {
        try {
            return template.queryForObject("select used from token where sign = ? ", Boolean.TYPE, token.getKey());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addToken(Token token) {
        template.update("insert into token (uid, time, sign, used) values (?, ?, ?, ?)",
                token.getId(),
                token.getTimestamp(),
                token.getKey(),
                false);
    }

    public void setTokenUsed(Token token) {
        template.update("update token set used = ? where sign = ? ",
                true,
                token.getKey());
    }

    public void setAllTokenUsed(long uid) {
        template.update("update token set used = ? where uid = ? ",
                true,
                uid);
    }
}