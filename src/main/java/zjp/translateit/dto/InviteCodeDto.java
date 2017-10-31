package zjp.translateit.dto;

import java.util.Date;

public class InviteCodeDto {

    private final int code;
    private final Date timeModified;
    private final boolean used;

    public InviteCodeDto(int code, Date timeModified, boolean used) {
        this.code = code;
        this.timeModified = timeModified;
        this.used = used;
    }

    public int getCode() {
        return code;
    }

    public Date getTimeModified() {
        return timeModified;
    }

    public boolean isUsed() {
        return used;
    }
}
