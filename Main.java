import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    static int head = -1,idx = 0;
    static int[] next = new int[100010], e = new int[100010];

    static void delete(int i) {
        next[i] = next[next[i]];
    }
    static void add(int i, int x) {
        next[idx] = next[i];
        e[idx] = x;
        next[i] = idx;
        idx++;
    }
    static void addHead(int x) {
        next[idx] = head;
        e[idx] = x;
        head = idx++;
    }
    public static void main(String[] args) throws IOException {
        int n = Reader.nextInt();
        while (n > 0) {
            String s = Reader.next();
            if (s.equals("H")) {
                int x = Reader.nextInt();
                addHead(x);
            } else if (s.equals("I")) {
                int i = Reader.nextInt(), x = Reader.nextInt();
                add(i - 1, x);
            } else {
                int i = Reader.nextInt();
                if (i == 0) head = next[head];
                else delete(i - 1);
            }
            n--;
        }
        StringBuffer sb = new StringBuffer();
        while (head != -1) {
            sb.append(e[head]).append(" ");
            head = next[head];
        }
        System.out.println(sb);
    }
}

class Reader {
    // 读字母或数字，空格、换行被视为分隔符，遇到逗号就比较奇怪
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static String next() throws IOException {
        in.nextToken(); // 读数字开头的就不行，123a这样读到null
        return in.sval;
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    static double nextDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }
}
