package pojo;

/**
 * @author yaowenhao
 * @Title Record
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/20 10:53
 */
public class Record {
    private long id;
    private long subjectId;
    private long optionId;
    private long userId;
    private Subject subject;
    private Option option;
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", optionId=" + optionId +
                ", userId=" + userId +
                ", subject=" + subject +
                ", option=" + option +
                ", user=" + user +
                '}';
    }
}
