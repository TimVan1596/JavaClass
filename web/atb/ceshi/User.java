package web.atb.ceshi;
/**
 * @author 安天宝
 * JAVA一班
 * 9月16日
 *
 * 用户信息
 */
public class User {

    private String name;
    private String Tupian;
    public User(String name, String Tupian) {
        this.name = name;
        this.Tupian = Tupian;
    }
    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTupian() {
        return Tupian;
    }

    public void setTupian(String tupian) {
        Tupian = tupian;
    }
}
