package output;

public class OutputHandler {
    public static final int STDOUT = 1;
    public static final int NOTHING = 0;
    public static final int STRINGBUILDER = 2;

    public static int outputMode;
    private static StringBuilder sb = new StringBuilder();

    public static void setMode(int mode) {
        outputMode = mode;
    }

    public static void outputText(String text) {
        switch (outputMode) {
            case STDOUT:
                sb.append(text);
                sb.append("\n");
                System.out.println(text);
                break;
            case STRINGBUILDER:
                sb.append(text);
                sb.append("\n");
                break;
        }
    }

    public static String getBattleLog() {
        return sb.toString();
    }
}
