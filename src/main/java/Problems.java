import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

public class Problems {

    private static java.util.List<Problem> problemList = new ArrayList<>();
    private static int i = 0;
    private static final Problems problems = new Problems();

    private Problems() {
        StringBuilder sb = new StringBuilder();
        try (Reader reader = new InputStreamReader(Problems.class.getResourceAsStream("problem.json"))) {

            int len;
            char[] buffer = new char[1024];
            while ((len = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        problemList = JSONArray.parseArray(sb.toString(), Problem.class);
        //洗牌算法，打乱题目顺序
        Collections.shuffle(problemList);

    }

    public static Problem get() {

        if (problemList.get(i) != null) {
            return problemList.get(i++);
        } else {
            return null;
        }
    }

    public static void init() {
        i = 0;
    }

}

class Problem {
    private String problem;
    private String a;
    private String b;
    private String c;
    private String d;
    private String rightItem;

    public Problem() {
    }

    public Problem(String problem, String a, String b, String c, String d, String rightItem) {
        this.problem = problem;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.rightItem = rightItem;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getRightItem() {
        return rightItem;
    }

    public void setRightItem(String rightItem) {
        this.rightItem = rightItem;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problem='" + problem + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", rightItem='" + rightItem + '\'' +
                '}';
    }
}