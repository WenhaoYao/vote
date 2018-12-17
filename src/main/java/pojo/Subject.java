package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaowenhao
 * @Title SubjectDao
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/13 21:39
 */
public class Subject {

    private Long id;
    private String title;
    private Integer number;
    private long startTime;
    private long endTime;
    private User user;
    private List<Option> optionList;

    public static final int SINGLE = 1;
    public static final int MULTI = 2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public Subject() {
        super();
        this.optionList = new ArrayList<>();
    }
}
