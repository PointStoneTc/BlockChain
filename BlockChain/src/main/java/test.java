
public class test {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("https://www.chainage.jp/wp-json/wp/v2/categories?include=185,196,");
        int index = sb.lastIndexOf(",");
        sb.deleteCharAt(index);
        System.out.println(sb);
    }
}
