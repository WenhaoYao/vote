package pojo;

/**
 * @author yaowenhao
 * @Title Option
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 21:49
 */
public class Option {

    private Long id;
    private String content;
    private int idx;
    private long subjectId;

    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long sujectId) {
        this.subjectId = sujectId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
